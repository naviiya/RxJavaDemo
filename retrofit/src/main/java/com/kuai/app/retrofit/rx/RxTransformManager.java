package com.kuai.app.retrofit.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * rx 线程切换管理
 */

public class RxTransformManager {

    public static <T> Observable.Transformer<T,T> normalTransform(){
        return new Observable.Transformer<T,T>(){
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
