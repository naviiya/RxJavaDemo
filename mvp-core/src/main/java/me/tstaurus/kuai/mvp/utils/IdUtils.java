package me.tstaurus.kuai.mvp.utils;

import java.util.UUID;


public class IdUtils {

    public static String getLoadTaskId(){
        return UUID.randomUUID().toString();
    }

}
