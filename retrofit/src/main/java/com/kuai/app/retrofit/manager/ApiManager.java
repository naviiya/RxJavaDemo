package com.kuai.app.retrofit.manager;

import com.kuai.app.retrofit.MyApplication;
import com.kuai.app.retrofit.api.ApiService;
import com.kuai.app.retrofit.api.JokeApiService;
import com.kuai.app.retrofit.util.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 服务器请求管理类
 */

public class ApiManager {

    private JokeApiService mJokeApiService;

    private ApiManager(){}

    public static ApiManager getInstance(){
        return SingletonApi.INSTANCE;
    }

    private static class SingletonApi{
        private static final ApiManager INSTANCE = new ApiManager();
    }
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (NetWorkUtil.isNetWorkAvailable(MyApplication.getCtx())) {
                int maxAge = 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    private static Cache sCache = new Cache(new File(MyApplication.getCtx().getCacheDir(),"kuaiLifeCache"),
            100 * 1024 * 1024);//缓存大小100Mb
    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)//连接超时1分钟
            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .cache(sCache)
            .build();

    private Object mJokeLock = new Object();
    /**
     * 笑话api接口
     */
    public JokeApiService jokeApi(){
        if(mJokeApiService == null){
            synchronized (mJokeLock){
                if(mJokeApiService == null){
                    mJokeApiService = new Retrofit.Builder()
                            .baseUrl(JokeApiService.BASE_URI)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(CLIENT)
                            .build()
                            .create(JokeApiService.class);
                }
            }
        }
        return mJokeApiService;
    }

    public ApiService getApiService(Class api){
        return null;
    }
}
