package com.sangupta.jerry.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

public class ReflectionMapper {

    public static <T> T from(Map<String, Object> map, Class<T> classOfT) {
        if(map == null) {
            return null;
        }
        
        T instance;
        try {
            instance = classOfT.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException("Unable to instantiate instance of class: " + classOfT, e);
        }
        
        Set<String> keySet = map.keySet();
        for(String key : keySet) {
//            ReflectionUtils.setField(instance, key, map.get(key));
        }
        
        return instance;
    }
}
