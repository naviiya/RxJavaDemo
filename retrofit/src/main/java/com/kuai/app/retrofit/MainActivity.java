package com.kuai.app.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kuai.app.retrofit.api.JokeApiService;
import com.kuai.app.retrofit.bean.JokeResult;
import com.kuai.app.retrofit.manager.ApiManager;

import java.util.List;

import retrofit2.adapter.rxjava.Result;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        query();
    }

    private void query() {
      ApiManager.getInstance().
                jokeApi().getTxtJokeList(JokeApiService.KEY, 0, 10)
                .subscribeOn(Schedulers.io())
//                .flatMap(new Func1<Result<JokeResult>, Observable<?>>() {
//                    @Override
//                    public Observable<?> call(Result<JokeResult> jokeResultResult) {
////                        boolean error = jokeResultResult.isError();
////                        if(!error){
//                            JokeResult body = jokeResultResult.response().body();
//
////                        }
//                        return null;
//                    }
//                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<JokeResult>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onNext(Result<JokeResult> jokeResultResult) {
                        Log.i(TAG, "onNext: " + jokeResultResult.error());
                        List<JokeResult.ResultBean.Joke> jokes = jokeResultResult.response().body().getResult().getData();
                        for (int i = 0; i < jokes.size(); i++) {
                            Log.i(TAG, "onNext: " + jokes.get(i));
                        }
                    }
                });
    }
}
