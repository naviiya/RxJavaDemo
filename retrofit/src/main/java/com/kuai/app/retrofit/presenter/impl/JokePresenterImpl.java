package com.kuai.app.retrofit.presenter.impl;

import android.util.Log;

import com.kuai.app.retrofit.bean.JokeResult;
import com.kuai.app.retrofit.exception.ApiException;
import com.kuai.app.retrofit.model.JokeModel;
import com.kuai.app.retrofit.model.impl.JokeModelImpl;
import com.kuai.app.retrofit.presenter.JokePresenter;
import com.kuai.app.retrofit.view.IJokeView;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class JokePresenterImpl implements JokePresenter<IJokeView>{

    private static final String TAG = JokePresenterImpl.class.getSimpleName();

    private CompositeSubscription mSubscription;

    private IJokeView mJokeView;

    private JokeModel<List<JokeResult.ResultBean.Joke>> mJokeModel;

    public JokePresenterImpl(IJokeView jokeView){
        mJokeView = jokeView;
        mJokeModel = new JokeModelImpl();
    }

    @Override
    public void getJokeList() {
        mJokeView.onPrepare();
        Subscriber<List<JokeResult.ResultBean.Joke>> subscriber = new Subscriber<List<JokeResult.ResultBean.Joke>>() {
            @Override
            public void onCompleted() {
                mJokeView.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                mJokeView.error(new ApiException(e));
            }

            @Override
            public void onNext(List<JokeResult.ResultBean.Joke> jokes) {
                mJokeView.getJokeList(jokes);
            }
        };
        mJokeModel.getJokeList(subscriber,1,2);
        onViewAttached(subscriber);
    }

    @Override
    public void onViewAttached(Subscriber subscriber) {
        Log.i(TAG, "onViewAttached: ");
        subscribe(subscriber);
    }

    @Override
    public void onViewDestroyed() {
        Log.i(TAG, "onViewDestroyed: ");
        unSubscribe();
    }


    private void subscribe(Subscription subscription) {
        if(mSubscription == null){
            mSubscription = new CompositeSubscription();
        }
        mSubscription.add(subscription);
    }


    private void unSubscribe() {
        if(mSubscription != null && mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }
}
