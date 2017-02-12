package com.kuai.app.retrofit.api;

import com.kuai.app.retrofit.bean.JokeResult;

import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 *  笑话接口
 */

public interface JokeApiService extends ApiService {

    String BASE_URI = "http://japi.juhe.cn/joke/";

    String KEY = "5f3325abd04d378c6e2e343d685fd627";

    // 获取最新笑话列表
    @GET("content/text.from")
    Observable<Result<JokeResult>> getTxtJokeList(@Query("key") String key, @Query("page") int page,@Query("pagesize") int pageSize);
    // 获取最新趣图列表
    @GET("img/text.from")
    Observable<Result<JokeResult>> getImgJokeList(@Query("key") String key, @Query("page") int page,@Query("pagesize") int pageSize);

}
