package com.kuai.app.retrofit.model;

import rx.Subscriber;

/**
 * Created by tstau on 2017/2/14.
 */
public interface JokeModel<T> extends IModel{

    void getJokeList(Subscriber<T> subscriber,int page, int pageSize);

}
