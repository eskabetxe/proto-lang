package pro.boto.protolang.domain.utils;

import static org.junit.Assert.*;
import org.junit.Test;
import pro.boto.protolang.utils.Classifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class ClassifierTest {

    private final static int INT = 1;
    private final static long LONG = 1L;
    private final static double DOUBLE = 1D;
    private final static float FLOAT = 1F;
    private final static boolean BOOL = true;
    private final static byte BYTE = 1;
    private final static short SHORT = 1;
    private final static char CHAR = '1';

    private final static String STRING = "1";
    private final static Date DATE = new Date();


    @Test
    public void isInteger(){
        assertTrue(Classifier.isInteger(INT));
        assertFalse(Classifier.isInteger(LONG));
        assertFalse(Classifier.isInteger(DOUBLE));
        assertFalse(Classifier.isInteger(FLOAT));
        assertFalse(Classifier.isInteger(BOOL));
        assertFalse(Classifier.isInteger(BYTE));
        assertFalse(Classifier.isInteger(SHORT));
        assertFalse(Classifier.isInteger(CHAR));
        assertFalse(Classifier.isInteger(STRING));
    }

    @Test
    public void isLong(){
        assertFalse(Classifier.isLong(INT));
        assertTrue(Classifier.isLong(LONG));
        assertFalse(Classifier.isLong(DOUBLE));
        assertFalse(Classifier.isLong(FLOAT));
        assertFalse(Classifier.isLong(BOOL));
        assertFalse(Classifier.isLong(BYTE));
        assertFalse(Classifier.isLong(SHORT));
        assertFalse(Classifier.isLong(CHAR));
        assertFalse(Classifier.isLong(STRING));
    }

    @Test
    public void isDouble(){
        assertFalse(Classifier.isDouble(INT));
        assertFalse(Classifier.isDouble(LONG));
        assertTrue(Classifier.isDouble(DOUBLE));
        assertFalse(Classifier.isDouble(FLOAT));
        assertFalse(Classifier.isDouble(BOOL));
        assertFalse(Classifier.isDouble(BYTE));
        assertFalse(Classifier.isDouble(SHORT));
        assertFalse(Classifier.isDouble(CHAR));
        assertFalse(Classifier.isDouble(STRING));
    }

    @Test
    public void isFloat(){
        assertFalse(Classifier.isFloat(INT));
        assertFalse(Classifier.isFloat(LONG));
        assertFalse(Classifier.isFloat(DOUBLE));
        assertTrue(Classifier.isFloat(FLOAT));
        assertFalse(Classifier.isFloat(BOOL));
        assertFalse(Classifier.isFloat(BYTE));
        assertFalse(Classifier.isFloat(SHORT));
        assertFalse(Classifier.isFloat(CHAR));
        assertFalse(Classifier.isFloat(STRING));
    }

    @Test
    public void isBoolean(){
        assertFalse(Classifier.isBoolean(INT));
        assertFalse(Classifier.isBoolean(LONG));
        assertFalse(Classifier.isBoolean(DOUBLE));
        assertFalse(Classifier.isBoolean(FLOAT));
        assertTrue(Classifier.isBoolean(BOOL));
        assertFalse(Classifier.isBoolean(BYTE));
        assertFalse(Classifier.isBoolean(SHORT));
        assertFalse(Classifier.isBoolean(CHAR));
        assertFalse(Classifier.isBoolean(STRING));
    }

    @Test
    public void isByte(){
        assertFalse(Classifier.isByte(INT));
        assertFalse(Classifier.isByte(LONG));
        assertFalse(Classifier.isByte(DOUBLE));
        assertFalse(Classifier.isByte(FLOAT));
        assertFalse(Classifier.isByte(BOOL));
        assertTrue(Classifier.isByte(BYTE));
        assertFalse(Classifier.isByte(SHORT));
        assertFalse(Classifier.isByte(CHAR));
        assertFalse(Classifier.isByte(STRING));
    }

    @Test
    public void isShort(){
        assertFalse(Classifier.isShort(INT));
        assertFalse(Classifier.isShort(LONG));
        assertFalse(Classifier.isShort(DOUBLE));
        assertFalse(Classifier.isShort(FLOAT));
        assertFalse(Classifier.isShort(BOOL));
        assertFalse(Classifier.isShort(BYTE));
        assertTrue(Classifier.isShort(SHORT));
        assertFalse(Classifier.isShort(CHAR));
        assertFalse(Classifier.isShort(STRING));
    }

    @Test
    public void isChar(){
        assertFalse(Classifier.isChar(INT));
        assertFalse(Classifier.isChar(LONG));
        assertFalse(Classifier.isChar(DOUBLE));
        assertFalse(Classifier.isChar(FLOAT));
        assertFalse(Classifier.isChar(BOOL));
        assertFalse(Classifier.isChar(BYTE));
        assertFalse(Classifier.isChar(SHORT));
        assertTrue(Classifier.isChar(CHAR));
        assertFalse(Classifier.isChar(STRING));
    }

    @Test
    public void isString(){
        assertFalse(Classifier.isString(INT));
        assertFalse(Classifier.isString(LONG));
        assertFalse(Classifier.isString(DOUBLE));
        assertFalse(Classifier.isString(FLOAT));
        assertFalse(Classifier.isString(BOOL));
        assertFalse(Classifier.isString(BYTE));
        assertFalse(Classifier.isString(SHORT));
        assertFalse(Classifier.isString(CHAR));
        assertTrue(Classifier.isString(STRING));
    }

    @Test
    public void isDate(){
        assertFalse(Classifier.isDate(INT));
        assertFalse(Classifier.isDate(LONG));
        assertFalse(Classifier.isDate(DOUBLE));
        assertFalse(Classifier.isDate(FLOAT));
        assertFalse(Classifier.isDate(BOOL));
        assertFalse(Classifier.isDate(BYTE));
        assertFalse(Classifier.isDate(SHORT));
        assertFalse(Classifier.isDate(CHAR));
        assertFalse(Classifier.isDate(STRING));
        assertTrue(Classifier.isDate(DATE));
    }

}
