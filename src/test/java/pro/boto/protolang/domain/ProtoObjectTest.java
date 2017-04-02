package pro.boto.protolang.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProtoObjectTest {

    public TestObject initializeTestObject(){
        return new TestObject(new Integer(1),new String("abc"),new Long(2));
    }

    @Test
    public void cloneObject(){
        TestObject test = initializeTestObject();
        TestObject cloned = test.clone();

        assertEquals("integer value not expected",test.intVal, cloned.intVal);
        assertEquals("string value not expected",test.stringVal, cloned.stringVal);
        assertEquals("long value not expected",test.longVal, cloned.longVal);

        assertNotSame("same memory reference not expected",test, cloned);
    }

    @Test
    public void equalObject() {
        TestObject test = initializeTestObject();
        TestObject cloned = test.clone();

        assertTrue("equals value not expected", cloned.equals(test));
        cloned.intVal = 3;
        assertFalse("equals value not expected", cloned.equals(test));
        cloned.intVal = test.intVal;
        assertTrue("equals value not expected", cloned.equals(test));
    }

    @Test
    public void compareObject() {
        TestObject test = initializeTestObject();
        TestObject cloned = test.clone();

        assertEquals("compare value not expected", 0, cloned.compareTo(test));
        cloned.intVal = 3;
        assertNotEquals("compare value not expected", 0, cloned.compareTo(test));
        cloned.intVal = test.intVal;
        assertTrue("compare value not expected", cloned.equals(test));
    }

    @Test
    public void others() {
        TestObject test = initializeTestObject();

        assertNotNull("hashCode value not expected", test.hashCode());
        assertNotNull("toString value not expected", test.toString());

        assertFalse("hasInfo value not expected", (new TestObject()).hasInfo());
        assertTrue("hasInfo value not expected", test.hasInfo());

    }

}
