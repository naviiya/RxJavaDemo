package com.kuai.app.retrofit.rx;

import com.kuai.app.retrofit.bean.JokeResult;
import com.kuai.app.retrofit.exception.ApiException;

import java.util.List;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.functions.Func1;

/**
 *  解析服务器返回的数据
 */

public class JokeFunc1 implements Func1<Result<JokeResult>, Observable<List<JokeResult.ResultBean.Joke>>> {


    private final static String TAG = JokeFunc1.class.getSimpleName();
    @Override
    public Observable<List<JokeResult.ResultBean.Joke>> call(Result<JokeResult> jokeResultResult) {
        if(jokeResultResult.isError() || jokeResultResult.response().body() == null){
            return Observable.error(jokeResultResult.error());
        }
        if(jokeResultResult.response().body().getErrorCode() != 0 ||
                jokeResultResult.response().body().getResult() == null){
            Observable.error(new ApiException(jokeResultResult.response().body().getReason()));
        }
        return Observable.just(jokeResultResult.response().body().getResult().getData());
    }
}
