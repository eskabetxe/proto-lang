package pro.boto.protolang.domain;

import static org.junit.Assert.*;
import org.junit.Test;


public class ProtoListTest {

    @Test
    public void creation(){
        TestList list1 = new TestList();
        assertNotNull(list1);
        assertEquals("size of new list is not the expected", 0, list1.size());

        TestList list2 = new TestList(new TestObject());
        assertNotNull(list2);
        assertEquals("size of new list is not the expected", 1, list2.size());
    }
}
