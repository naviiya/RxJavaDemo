package com.kuai.app.retrofit.rx;

import android.util.Log;

import com.kuai.app.retrofit.bean.JokeResult;
import com.kuai.app.retrofit.manager.JokeAlgorithmManager;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func2;


/**
 * 文字笑话和趣图融合逻辑
 */

public class JokeFunc2 implements Func2<List<JokeResult.ResultBean.Joke>,
        List<JokeResult.ResultBean.Joke>,List<JokeResult.ResultBean.Joke>> {

    private static final String TAG = JokeFunc2.class.getSimpleName();
    @Override
    public List<JokeResult.ResultBean.Joke> call(List<JokeResult.ResultBean.Joke> jokes, List<JokeResult.ResultBean.Joke> jokes2) {
        int total = jokes.size() + jokes2.size();
        List<JokeResult.ResultBean.Joke> list = new ArrayList<>(total);
        String jokeRule = JokeAlgorithmManager.getJokeRule();
        for (int i = 0; i < jokeRule.length(); i++) {
            String num = i == jokeRule.length() - 1 ?
                    jokeRule.substring(i) : jokeRule.substring(i,i+1);
            if(num.equals("0")){
                list.add(jokes.remove(0));
            }else {
                list.add(jokes2.remove(0));
            }
        }
        return list;
    }

}
