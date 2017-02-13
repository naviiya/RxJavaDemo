package com.kuai.app.retrofit.manager;

import android.text.TextUtils;
import android.util.Log;

import com.kuai.app.retrofit.exception.ApiException;
import com.kuai.app.retrofit.MyApplication;
import com.kuai.app.retrofit.util.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Api 工厂类
 */

public class ServiceFactory {

    private static final String TAG = ServiceFactory.class.getSimpleName();

    private ServiceFactory(){}
    public static ServiceFactory getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder{
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public <T> T createService(Class<T> serviceClass) throws ApiException {
        String baseUri = null;
        try {
            Field baseUriF = serviceClass.getField("BASE_URI");
            baseUri = (String) baseUriF.get(serviceClass);
        } catch (NoSuchFieldException e) {
            Log.i(TAG, "createService: " + e.getMessage());
            throw new ApiException("Error BASE_URI value in api service");
        } catch (IllegalAccessException e) {
            Log.i(TAG, "createService: " + e.getMessage());
            throw new ApiException("Error BASE_URI value in api service");
        }
        if(TextUtils.isEmpty(baseUri)){
            throw new ApiException("Null BASE_URI value! in api service");
        }
        return new Retrofit.Builder()
                .baseUrl(baseUri)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(serviceClass);
    }

    private static final long DEFAULT_TIMEOUT = 10;//10分钟
    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES);
        builder.readTimeout(DEFAULT_TIMEOUT,TimeUnit.MINUTES);
        builder.writeTimeout(DEFAULT_TIMEOUT,TimeUnit.MINUTES);

        builder.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
        Cache cache = new Cache(new File(MyApplication.getCtx().getCacheDir(),"kuailifecache"),
                100 * 1024 * 1024);
        builder.cache(cache);
        return builder.build();
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
}
