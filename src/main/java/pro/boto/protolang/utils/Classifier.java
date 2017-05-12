package pro.boto.protolang.utils;

import org.apache.commons.lang3.ClassUtils;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Classifier {

    protected static boolean isAssignable(Object value, Class<?> eClazz){
        return ClassUtils.isAssignable(value.getClass(), eClazz);
    }


    public static boolean isInteger(Object value){
        return isAssignable(value, Integer.class);
    }
    public static boolean isAtomicInteger(Object value){
        return isAssignable(value, AtomicInteger.class);
    }

    public static boolean isLong(Object value){
        return isAssignable(value, Long.class);
    }

    public static boolean isDouble(Object value){
        return isAssignable(value, Double.class);
    }

    public static boolean isFloat(Object value){
        return isAssignable(value, Float.class);
    }

    public static boolean isBoolean(Object value){
        return isAssignable(value, Boolean.class);
    }

    public static boolean isByte(Object value){
        return isAssignable(value, Byte.class);
    }

    public static boolean isShort(Object value){
        return isAssignable(value, Short.class);
    }

    public static boolean isChar(Object value){
        return isAssignable(value, Character.class);
    }

    public static boolean isString(Object value){
        return isAssignable(value, String.class);
    }


    public static boolean isDate(Object value){
        return isAssignable(value, Date.class);
       // return Date.class.isAssignableFrom(value.getClass());
    }

}
