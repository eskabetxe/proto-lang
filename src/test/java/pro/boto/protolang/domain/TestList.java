package pro.boto.protolang.domain;

@SuppressWarnings("serial")
class TestList extends ProtoList<TestObject> {
    public TestList(TestObject... objects){
        super(objects);
    }
}
