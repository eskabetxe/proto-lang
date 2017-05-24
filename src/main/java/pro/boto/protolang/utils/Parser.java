package pro.boto.protolang.utils;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static pro.boto.protolang.utils.Classifier.*;

public class Parser {
    
    private static Logger log = LoggerFactory.getLogger(Parser.class);

    public static Integer toInteger(Object value){
        if (value == null) return null;
        if (isInteger(value)) return (Integer) value;
        if (isAtomicInteger(value)) return ((AtomicInteger) value).get();
        if (isNumber(value)){
            Number n = (Number) value;
            return ((Number)Math.round(n.doubleValue())).intValue();
        }

        String sValue = Cleaner.toNumberWithoutDecimals(String.valueOf(value));
        if (StringUtils.isBlank(sValue)) return null;
        
        try {
            return Integer.parseInt(sValue);
        } catch (Exception e) {
            log.error("parsing to integer: "+value+" - "+e.getMessage());
            return null;
        }
    }


    public static Long toLong(Object value){
        if (value == null) return null;
        if (isLong(value)) return (Long) value;
        if (isNumber(value)){
            Number n = (Number) value;
            return ((Number)Math.round(n.doubleValue())).longValue();
        }

        String sValue = Cleaner.toNumberWithoutDecimals(String.valueOf(value));
        if (StringUtils.isBlank(sValue)) return null;

        try {
            return Long.parseLong(sValue);
        } catch (Exception e) {
            log.error("parsing to long: "+value+" - "+e.getMessage());
            return null;
        }
    }

    public static Float toFloat(Object value){
        if (value == null) return null;
        if (isFloat(value)) return (Float) value;
        if (isNumber(value)){
            Number n = (Number) value;
            return ((Number)Math.round(n.doubleValue())).floatValue();
        }

        String sValue = Cleaner.toNumberWithDecimals(String.valueOf(value));
        if (StringUtils.isBlank(sValue)) return null;

        try {
            return Float.parseFloat(sValue);
        } catch (Exception e) {
            log.error("parsing to float: "+value+" - "+e.getMessage());
            return null;
        }
    }
    

    public static Double toDouble(Object value){
        if (value == null) return null;
        if (isDouble(value)) return (Double) value;
        if (isLong(value)) return toLong(value).doubleValue();
        if (isInteger(value)) return toInteger(value).doubleValue();
        
        String sValue = Cleaner.toNumberWithDecimals(String.valueOf(value));
        if (StringUtils.isBlank(sValue)) return null;
        
        try {
            return Double.parseDouble(sValue);
        } catch (Exception e) {
            log.error("parsing to double: "+value+" - "+e.getMessage());
            return null;
        }
    }
    

    public static Boolean toBoolean(Object value){
        if (value == null) return null;
        if (isBoolean(value)) return (Boolean) value;
        
        String sValue = Cleaner.trim(String.valueOf(value));
        if (StringUtils.isBlank(sValue)) return null;
        
        return BooleanUtils.toBooleanObject(sValue);
    }    
    

    public static String toString(Object value){
        if (value == null) return null;
        if (isString(value)) return (String) value;
        
        if(isDouble(value)){
            DecimalFormat decimalFormat = new DecimalFormat("0.0###################");
            return decimalFormat.format(value).replaceAll(",", ".");
        }
        return Cleaner.trim(String.valueOf(value));
    }
    

    
    public static Date toDate(Object value){
        return toDate(value, "yyyy/MM/dd");
    }
    
    public static Date toDate(Object value, String dateFormat){
        if (value == null) return null;
        if (isDate(value)) return (Date) value;
        
        String sValue = Cleaner.trim(String.valueOf(value));
        if (StringUtils.isBlank(sValue)) return null;
        
        try {
            DateFormat format = new SimpleDateFormat(dateFormat);
            return format.parse(sValue);
        } catch (Exception e) {
            log.error("parsing to date: "+value+" - "+e.getMessage());
            return null;
        }
    }
        
    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> T toEnum(Object value, Class<T> eClazz){
        if (value == null) return null;
        if (isAssignable(value, eClazz)) return (T) value;
        
        return Enum.valueOf(eClazz, value.toString().toUpperCase());
    }
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> toList(Object value, Class<T> eClazz){
        if (value == null) return null;
        if (isAssignable(value, List.class)) return (List<T>) value;
        
        return (List<T>) value;
    }
    @SuppressWarnings("unchecked")
    public static <T> LinkedList<T> toLinkedList(Object value, Class<T> eClazz){
        if (value == null) return null;
        if (isAssignable(value, LinkedList.class)) return (LinkedList<T>) value;
        
        return (LinkedList<T>) value;
    }    
}
