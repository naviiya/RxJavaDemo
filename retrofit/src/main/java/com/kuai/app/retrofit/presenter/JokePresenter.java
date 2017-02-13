package com.kuai.app.retrofit.presenter;

import com.kuai.app.retrofit.view.IView;

/**
 *
 */

public interface JokePresenter<T extends IView> extends IPresenter<IView> {

    void getJokeList();
}
