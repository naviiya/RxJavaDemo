package com.kuai.app.retrofit.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.kuai.app.retrofit.AppConfig;
import com.kuai.app.retrofit.MyApplication;

/**
 *  笑话算法管理类
 */

public class JokeAlgorithmManager {


    private static final int JOKE_COUNT = 40;

    private static void saveJokeRule(String serialNumber){
        MyApplication.getCtx().getSharedPreferences(AppConfig.Algorithm.ALGORITHM_CONFIG, Context.MODE_PRIVATE)
                .edit().putString(AppConfig.Algorithm.JOKE_KEY,serialNumber).apply();
    }

    /**
     * 初始化笑话算法，并存入sp
     * @return
     */
    private static String initJokeRule(){
        String rule = create(JOKE_COUNT);
        saveJokeRule(rule);
        return rule;
    }


    private static String create(int total){
        StringBuilder sb = new StringBuilder(total);
        int odd = 0;//奇数个数计数器，存入值为“1”
        int even = 0;//偶数个数计数器，存入值为“0”
        for (int i = 0; i < total; i++) {
            if(odd == 20 || even == 20){
                break;
            }
            if(getRandomInt() % 2 == 0){
                even ++;
                sb.append("0");
            }else {
                odd ++;
                sb.append("1");
            }
        }
        if(odd > even){
            for (int i = 0; i < odd - even; i++) {
                sb.append("0");
            }
        }else {
            for (int i = 0; i < even - odd; i++) {
                sb.append("1");
            }
        }
        return sb.toString();
    }


    /**
     * 获取当前的笑话算法，如果没有则初始化一个
     * @return
     */
    public static String getJokeRule(){
        SharedPreferences sp =
                MyApplication.getCtx().getSharedPreferences(AppConfig.Algorithm.ALGORITHM_CONFIG, Context.MODE_PRIVATE);
        if(TextUtils.isEmpty(sp.getString(AppConfig.Algorithm.JOKE_KEY,""))){
           return initJokeRule();
        }
        else {
            return sp.getString(AppConfig.Algorithm.JOKE_KEY,getDefaultRule());
        }
    }


    private static String getDefaultRule(){
        StringBuilder sb = new StringBuilder(JOKE_COUNT);
        for (int i = 0; i < JOKE_COUNT; i++) {
            if(i % 2 == 0){
                sb.append("0");
            }else {
                sb.append("1");
            }
        }
        return sb.toString();
    }


    /**
     *
     * @return 0 - 9
     */
    private static int getRandomInt(){
        double d = Math.random();// 0.0 - 1.0
        return (int) (d * 100);
    }

}
