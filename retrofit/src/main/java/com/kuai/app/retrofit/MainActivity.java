package com.kuai.app.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kuai.app.retrofit.bean.JokeResult;
import com.kuai.app.retrofit.presenter.JokePresenter;
import com.kuai.app.retrofit.presenter.impl.JokePresenterImpl;
import com.kuai.app.retrofit.view.IJokeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IJokeView {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.rv_list)
    RecyclerView mJokeRecyclerView;
    @BindView(R.id.pb_loading)
    ProgressBar mLoadingView;

    private JokePresenter mPresenter;
    private JokeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        mPresenter = new JokePresenterImpl(this);
        query();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mJokeRecyclerView.setLayoutManager(manager);
        mJokeRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        List<JokeResult.ResultBean.Joke> jokes = new ArrayList<>();
        mAdapter = new JokeAdapter();
        mJokeRecyclerView.setAdapter(mAdapter);
    }

    private void query() {
        if (mPresenter != null) {
            mPresenter.getJokeList();
        }
    }

    @Override
    public void onPrepare() {
        Log.i(TAG, "onPrepare: ");
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismiss() {
        Log.i(TAG, "dismiss: ");
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void error(Exception e) {
        Log.e(TAG, "error: " + e);
        Toast.makeText(this,"error",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onViewDestroyed();
        }
        super.onDestroy();
    }

    @Override
    public void getJokeList(List<JokeResult.ResultBean.Joke> jokes) {
//        Log.i(TAG, "getJokeList: " + jokes);
        for (int i = 0; i < jokes.size(); i++) {
            JokeResult.ResultBean.Joke joke = jokes.get(i);
            if (joke.getUrl() != null) {
                Log.i(TAG, "getJokeList img:" + joke);
            } else {
                Log.i(TAG, "getJokeList: txt" + joke);
            }
        }
        mAdapter.refresh(jokes);
    }
}
