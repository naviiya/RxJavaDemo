package com.kuai.app.retrofit.manager;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kuai.app.retrofit.util.ScreenUtils;


/**
 *  glide 图片视频动图使用管理类
 */

public class MediaManager {

    private static final String TAG = MediaManager.class.getSimpleName();

    public static void loadOriginResourceToImageView(String uri, int errResourceId, ImageView iv,
                                                     Activity activity){
        int screenWidth = ScreenUtils.getScreenWidth(activity);
        int screenHeight = ScreenUtils.getScreenHeight(activity);
        Glide.with(activity)
                .load(uri)
                .error(errResourceId)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
//                .fitCenter()
//                .centerCrop()
                .override(screenWidth,screenHeight)
                .into(iv);
    }

    public static void loadThumbnailToImageView(String uri, int defaultResourceId, ImageView iv,
                            Activity activity){
        if(isGif(uri)){
            loadThumbnailGifToImageView(uri,defaultResourceId,iv,activity);
        }else {
            loadThumbnailImgToImageView(uri,defaultResourceId,iv,activity);
        }
    }

    private static boolean isGif(String uri) {
        boolean isGif = false;
        try{
            isGif = uri.substring(uri.lastIndexOf(".") + 1).equalsIgnoreCase("gif");
        }catch (Exception e){
            Log.e(TAG, "isGif: ", e);
        }
        return isGif;
    }

    private static void loadThumbnailImgToImageView(String uri, int defaultResourceId, ImageView iv,
                            Activity activity){
        Glide.with(activity).load(uri)
                .centerCrop()
                .crossFade()
                .placeholder(defaultResourceId)
                .into(iv);
    }

    private static void loadThumbnailGifToImageView(String uri, int defaultResourceId, ImageView iv,
                                                    Activity activity){
        Glide.with(activity).load(uri)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(defaultResourceId)
                .into(iv);
    }
}
