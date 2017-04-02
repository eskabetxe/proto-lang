package pro.boto.protolang.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("serial")
public abstract class ProtoList<T extends ProtoObject<T>> extends ProtoObject<T>  {

	private List<T> thisList;
	protected ProtoList(){
		thisList = new ArrayList<>();
    }
	
	protected ProtoList(Collection<T> col){
    	thisList = new ArrayList<>(col);
    }
    
    @SafeVarargs
    protected ProtoList(T... objs){
    	thisList = new ArrayList<>();
        thisList.addAll(Arrays.asList(objs));
	}

    protected boolean add(T bean){
        return thisList.add(bean);
    }

    protected boolean addAll(Collection<T> bean){
        return thisList.addAll(bean);
    }

    protected boolean clean(){
        thisList.clear();
        return true;
    }
    
    public int size(){
    	return thisList.size();
    }

    public boolean isEmpty(){
    	return thisList.isEmpty();
    }

    public boolean isNotEmpty(){
        return !isEmpty();
    }

    @Override
    public boolean hasInfo() {
        if(isEmpty()) return false;
        return super.hasInfo();
    }

    public List<T> obtain(){
    	return new ArrayList<>(thisList);
    }

    public T obtain(int index){
		if(index < 0 || index >= size()) return null;
		return thisList.get(index);
	}

    public List<T> subList(int fromIndex, int toIndex){
        if(fromIndex < 0 || fromIndex > toIndex || toIndex > size()) return null;
    	return new ArrayList<>(thisList.subList(fromIndex,  Math.min(toIndex, size())));
    }

}
