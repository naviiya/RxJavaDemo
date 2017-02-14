package com.kuai.app.retrofit.rx;

import com.kuai.app.retrofit.bean.JokeResult;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func2;

/**
 * Created by tstau on 2017/2/14.
 */

public class JokeFunc2 implements Func2<List<JokeResult.ResultBean.Joke>,
        List<JokeResult.ResultBean.Joke>,List<JokeResult.ResultBean.Joke>> {
    @Override
    public List<JokeResult.ResultBean.Joke> call(List<JokeResult.ResultBean.Joke> jokes, List<JokeResult.ResultBean.Joke> jokes2) {
        int total = jokes.size() + jokes2.size();
        List<JokeResult.ResultBean.Joke> list = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
           if(getRandomInt() % 2 == 0){
               if(jokes.size() > 0) {
                   list.add(jokes.remove(0));
               }
               else if(jokes2.size() > 0) {
                   list.add(jokes2.remove(0));
               }
           }else if(getRandomInt() % 2 != 0){
               if(jokes2.size() > 0) {
                   list.add(jokes2.remove(0));
               }else if(jokes.size() > 0){
                   list.add(jokes.remove(0));
               }
           }
        }
        return list;
    }

    /**
     *
     * @return 0 - 9
     */
    private int getRandomInt(){
        double d = Math.random();// 0.0 - 1.0
        return (int) (d * 100);
    }
}
