package com.kuai.app.retrofit.view;

/**
 * Created by tstau on 2017/2/14.
 */

public interface IView {

    void onPrepare();

    void dismiss();

    void error(Exception e);
}
