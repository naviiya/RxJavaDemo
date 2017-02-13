package com.kuai.app.retrofit.view;

import com.kuai.app.retrofit.bean.JokeResult;

/**
 * Created by tstau on 2017/2/13.
 */

public interface IJokeView extends IView{

    void onError(Exception e);

    void onSuccess(JokeResult result);

    void onPrepare();
}
