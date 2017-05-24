package pro.boto.protolang.domain.utils;

import org.junit.Assert;
import org.junit.Test;
import pro.boto.protolang.utils.Parser;

public class ParserTest {

    @Test
    public void toInteger(){
        assertInteger(1, 1);
        assertInteger(1, 1L);
        assertInteger(1, 1.4D);
        assertInteger(2, 1.6D);
        assertInteger(1, 1.4F);
        assertInteger(2, 1.6F);
        assertInteger(1, "1");
        assertInteger(1, "1.6");
    }
    private void assertInteger(Integer expected, Object actual){
        Assert.assertEquals(expected, Parser.toInteger(actual));
    }

    //@Test
    public void nnnn(){
        Float dd = new Float(23.523454321);
        System.out.println(dd.intValue());
        System.out.println(dd.doubleValue());
        System.out.println(dd.longValue());
        System.out.println(dd.floatValue());
        System.out.println(dd.shortValue());
        System.out.println(dd.byteValue());
    }

}
