package com.kuai.app.retrofit.presenter.impl;

import android.util.Log;

import com.kuai.app.retrofit.api.JokeApiService;
import com.kuai.app.retrofit.bean.JokeResult;
import com.kuai.app.retrofit.exception.ApiException;
import com.kuai.app.retrofit.manager.ServiceFactory;
import com.kuai.app.retrofit.presenter.JokePresenter;
import com.kuai.app.retrofit.rx.HttpResultSubscriber;
import com.kuai.app.retrofit.rx.RxTransformManager;
import com.kuai.app.retrofit.view.IJokeView;

import retrofit2.adapter.rxjava.Result;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class JokePresenterImpl implements JokePresenter<IJokeView>{

    private static final String TAG = JokePresenterImpl.class.getSimpleName();

    private CompositeSubscription mSubscription;

    private IJokeView mJokeView;

    public JokePresenterImpl(IJokeView jokeView){
        mJokeView = jokeView;
    }

    @Override
    public void getJokeList() {
        mJokeView.onPrepare();
        try {
            Subscriber<Result<JokeResult>> subscriber = new HttpResultSubscriber<JokeResult>() {
                @Override
                public void onFinished() {
//                            Log.i(TAG, "onFinished: ");
                }

                @Override
                public void onResultError(Exception e) {
//                            Log.e(TAG, "onResultError: ", e);
                    mJokeView.onError(e);
                }

                @Override
                public void onSuccess(JokeResult jokeResult) {
//                            Log.i(TAG, "onSuccess: " + jokeResult.getResult().getData());
                    mJokeView.onSuccess(jokeResult);
                }
            };
            ServiceFactory.getInstance().createService(JokeApiService.class)
                    .getImgJokeList(0,100)
                    .compose(RxTransformManager.<Result<JokeResult>>normalTransform())
                    .subscribe(subscriber);
            subscribe(subscriber);
        } catch (ApiException e) {
            e.printStackTrace();
        }
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
