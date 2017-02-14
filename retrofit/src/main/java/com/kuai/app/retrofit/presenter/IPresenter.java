package com.kuai.app.retrofit.presenter;

import rx.Subscriber;

/**
 *  presenter基类
 */

public interface IPresenter<IView> {

    void onViewAttached(Subscriber subscriber);

    void onViewDestroyed();
}
