package com.equipeglr;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FXUtils {
    public static Object invokeGetMethodValue(Class clazz, Object obj, String getTextLayout) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method method = clazz.getDeclaredMethod(getTextLayout, null);
        method.setAccessible(true);
        return method.invoke(obj, null);
    }
}
