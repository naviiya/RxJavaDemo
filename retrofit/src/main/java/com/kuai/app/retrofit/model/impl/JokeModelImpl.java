package com.kuai.app.retrofit.model.impl;

import android.util.Log;

import com.kuai.app.retrofit.api.JokeApiService;
import com.kuai.app.retrofit.bean.JokeResult;
import com.kuai.app.retrofit.exception.ApiException;
import com.kuai.app.retrofit.manager.ServiceFactory;
import com.kuai.app.retrofit.model.JokeModel;
import com.kuai.app.retrofit.rx.JokeFunc1;
import com.kuai.app.retrofit.rx.JokeFunc2;
import com.kuai.app.retrofit.rx.RxTransformManager;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * 笑话数据实现类
 */

public class JokeModelImpl implements JokeModel<List<JokeResult.ResultBean.Joke>> {

    private static final String TAG = JokeModelImpl.class.getSimpleName();

    @Override
    public void getJokeList(Subscriber<List<JokeResult.ResultBean.Joke>> subscriber, int page, int pageSize) {
        try {
            JokeApiService service = ServiceFactory.getInstance().createService(JokeApiService.class);
            // 随机拼接文字和图片笑话
            Observable.zip(service.getImgJokeList(page, pageSize).flatMap(new JokeFunc1()),
                            service.getTxtJokeList(page, pageSize).flatMap(new JokeFunc1()),
                    // 拼接逻辑
                            new JokeFunc2())
            .compose(RxTransformManager.<List<JokeResult.ResultBean.Joke>>normalTransform())
                    .subscribe(subscriber);
        } catch (ApiException e) {
            Log.e(TAG, "getJokeList: ", e);
            subscriber.onError(e);
        }
    }
}
