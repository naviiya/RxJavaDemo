package me.tstaurus.kuai.mvp.utils;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 *
 */
public class BeanUtils {
    public static final String TAG = "BeanUtils";

    // o1 --> old o2 --> new
    public static void copy(Object oldO, Object newO) throws IllegalAccessException {
        HashMap<String,Object> map = new HashMap<String,Object>();
        Class<?> o1Class = oldO.getClass();
        Class<?> o2Class = newO.getClass();
        if(o1Class == o2Class){
            throw new RuntimeException("The two objects are the implements of the same class");
        }
        Field[] fields = o1Class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            field.setAccessible(true);
            Object obj = field.get(oldO);
            map.put(fieldName,obj);
        }
        fields = o2Class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if(map.containsKey(field.getName())){
                field.setAccessible(true);
                String type = field.getType().toString();
                if(type.endsWith("String")){
                    field.set(newO,map.get(field.getName()));
                }else if(type.equals("int")){
                    field.set(newO,map.get(field.getName()));
                }
                else{
                    Log.d(TAG,"Unknown Type :" + type);
                }
            }
        }
        map.clear();
    }
}
