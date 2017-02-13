package com.kuai.app.retrofit.rx;

import com.kuai.app.retrofit.exception.ApiException;

import retrofit2.adapter.rxjava.Result;
import rx.Subscriber;

/**
 * 封装Subscriber
 */

public abstract class HttpResultSubscriber<T> extends Subscriber<Result<T>> {
    @Override
    public void onCompleted() {
        onFinished();
    }

    @Override
    public void onError(Throwable e) {
        onResultError(new ApiException(e));
    }

    @Override
    public void onNext(Result<T> tResult) {
        if(tResult.isError()){
            onResultError(new ApiException(tResult.error()));
        }else {
            onSuccess(tResult.response().body());
        }
    }

    public abstract void onFinished();

    public abstract void onResultError(Exception e);

    public abstract void onSuccess(T t);
}
