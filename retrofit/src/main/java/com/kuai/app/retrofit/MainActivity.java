package com.kuai.app.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kuai.app.retrofit.bean.JokeResult;
import com.kuai.app.retrofit.presenter.JokePresenter;
import com.kuai.app.retrofit.presenter.impl.JokePresenterImpl;
import com.kuai.app.retrofit.view.IJokeView;

public class MainActivity extends AppCompatActivity implements IJokeView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private JokePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new JokePresenterImpl(this);
        query();
    }

    private void query() {
        if(mPresenter != null) {
            mPresenter.getJokeList();
        }
    }


    @Override
    public void onError(Exception e) {
        Log.e(TAG, "onError: ", e);
    }

    @Override
    public void onSuccess(JokeResult result) {
        Log.i(TAG, "onSuccess: " + result.getResult().getData());
    }

    @Override
    public void onPrepare() {
        Log.i(TAG, "onPrepare: ");
    }

    @Override
    protected void onDestroy() {
        if(mPresenter != null){
            mPresenter.onViewDestroyed();
        }
        super.onDestroy();
    }
}
