package pro.boto.protolang.domain;

@SuppressWarnings("serial")
class TestObject extends ProtoObject<TestObject> {
    protected Integer intVal;
    protected String stringVal;
    protected Long longVal;

    protected TestObject(){}

    protected TestObject(Integer iVal, String sVal, Long lVal){
        intVal = iVal;
        stringVal = sVal;
        longVal = lVal;
    }
}
