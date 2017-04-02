package pro.boto.protolang.domain;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public abstract class ProtoMap<T,S> extends ProtoObject<ProtoMap<T,S>> {
    
    private Map<T,S> thisMap;
    
    protected ProtoMap(){
        this(new HashMap<>());
    }
    
    protected ProtoMap(Map<T,S> map) {
        thisMap = new HashMap<>();
        thisMap.putAll(map);
    }

    protected S add(T key, S value){
        return thisMap.put(key,value);
    }

    protected void addAll(Map<T,S> map){
        thisMap.putAll(map);
    }

    public S obtain(T key){
        return thisMap.get(key);
    }

    public int size(){
        return thisMap.size();
    }
    
    public boolean isEmpty(){
        return thisMap.isEmpty();
    }

    public boolean isNotEmpty(){
        return !isEmpty();
    }

    @Override
    public boolean hasInfo() {
        if(isEmpty()) return false;
        return super.hasInfo();
    }

    public boolean contains(T key){
        return thisMap.containsKey(key);
    }

    public Map<T,S> obtain(){
        return new HashMap<>(thisMap);
    }

    @SuppressWarnings("unchecked")
    public Map<T,S> obtainKeys(T... keys){
        Map<T,S> newMap = new HashMap<>();
        for(T key : keys) {
            if(contains(key)){
                newMap.put(key, obtain(key));
            }
        }
        return newMap;
    }
    
}
