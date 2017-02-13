package com.kuai.app.retrofit;

import rx.Subscription;

/**
 * Created by tstau on 2017/2/13.
 */

public interface ILifeCycle {

    void subscribe(Subscription subscription);

    void unSubscribe();
}
