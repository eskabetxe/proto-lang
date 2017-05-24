package pro.boto.protolang.domain.utils;

import org.junit.Assert;
import org.junit.Test;
import pro.boto.protolang.utils.Cleaner;
import pro.boto.protolang.utils.Parser;

public class CleanerTest {

    @Test
    public void toNumberWithoutDecimals(){
        assertNumberWithoutDecimals("1", "1");
        assertNumberWithoutDecimals("1", "1.6");
        assertNumberWithoutDecimals("1", "1,6");
    }
    private void assertNumberWithoutDecimals(String expected, String actual){
        Assert.assertEquals(expected, Cleaner.toNumberWithoutDecimals(actual));
    }

    @Test
    public void toNumberWithDecimals(){
        assertNumberWithoutDecimals("1", "1");
        assertNumberWithDecimals("1.6", "1.6");
        assertNumberWithDecimals("1.6", "1,6");
    }
    private void assertNumberWithDecimals(String expected, String actual){
        Assert.assertEquals(expected, Cleaner.toNumberWithDecimals(actual));
    }



}
