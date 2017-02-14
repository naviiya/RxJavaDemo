package com.kuai.app.retrofit.view;

import com.kuai.app.retrofit.bean.JokeResult;

import java.util.List;

/**
 * Created by tstau on 2017/2/13.
 */

public interface IJokeView extends IView{

//    void onSuccess(JokeResult result);

       void  getJokeList(List<JokeResult.ResultBean.Joke> jokes);

}
