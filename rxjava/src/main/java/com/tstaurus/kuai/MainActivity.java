package com.tstaurus.kuai;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.list_view)
    ListView mListView;
    @BindView(R.id.loading)
    ProgressBar mLoadingView;
    @BindView(R.id.btn_query)
    Button mQueryButton;

    private boolean inQuery = false;
    private ArrayList<AppInfo> mAppInfos;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mAppInfos = new ArrayList<>();
        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);
        mQueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(inQuery){
            backToOrigin();
        }else {
            super.onBackPressed();
        }
    }

    private void backToOrigin() {
        inQuery = false;
        mListView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mQueryButton.setVisibility(View.VISIBLE);
        mAppInfos.clear();
    }

    private void query() {
        inQuery = true;
        Observable.create(new Observable.OnSubscribe<ApplicationInfo>() {
            @Override
            public void call(Subscriber<? super ApplicationInfo> subscriber) {
                List<ApplicationInfo> allApps = getAllApps();
                for (int i = 0; i < allApps.size(); i++) {
                    subscriber.onNext(allApps.get(i));
                }
                subscriber.onCompleted();
            }
        })
                .filter(new Func1<ApplicationInfo, Boolean>() {
            @Override
            public Boolean call(ApplicationInfo applicationInfo) {
                return true;
            }
        })
                .map(new Func1<ApplicationInfo, AppInfo>() {
            @Override
            public AppInfo call(ApplicationInfo applicationInfo) {
                String appName = (String) applicationInfo.
                        loadLabel(MainActivity.this.getPackageManager());
                Drawable icon = applicationInfo.
                        loadIcon(MainActivity.this.getPackageManager());
                return new AppInfo(appName,icon);
            }
        })
                .subscribeOn(Schedulers.newThread())
                .onBackpressureBuffer(1000)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mQueryButton.setVisibility(View.GONE);
                        mLoadingView.setVisibility(View.VISIBLE);
                    }
                })
                .subscribe(new Subscriber<AppInfo>() {
                    @Override
                    public void onCompleted() {
                        mLoadingView.setVisibility(View.GONE);
                        mListView.setVisibility(View.VISIBLE);
                        mAdapter.notifyDataSetInvalidated();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onNext(AppInfo appInfo) {
                        mAppInfos.add(appInfo);
                    }
                });
    }

    private List<ApplicationInfo> getAllApps() {
        return getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mAppInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return mAppInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = null;
            ViewHolder holder = null;
            if(convertView == null){
                v = LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.list_item,parent,false);
                holder = new ViewHolder();
                holder.appName = (TextView) v.findViewById(R.id.tv_app_name);
                holder.icon = (ImageView) v.findViewById(R.id.iv_app_icon);
                v.setTag(holder);
            }else {
                v = convertView;
                holder = (ViewHolder) v.getTag();
            }
            AppInfo appInfo = mAppInfos.get(position);
            holder.appName.setText(appInfo.getAppName());
            holder.icon.setImageDrawable(appInfo.getIcon());
            return v;
        }

        class ViewHolder{
            TextView appName;
            ImageView icon;
        }
    }
}
