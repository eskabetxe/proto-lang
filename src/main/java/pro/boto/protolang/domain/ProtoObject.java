package pro.boto.protolang.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.*;

@SuppressWarnings("serial")
public class ProtoObject<T extends ProtoObject<T>> implements Serializable, Cloneable, Comparable<T> {
    
    @SuppressWarnings("unchecked")
    @Override
	public T clone() throws SerializationException {
        try {
        	T clone = (T)super.clone();
        	for (Field cField : processFields()) {
		    	Object value = cField.get(this);
		    	if(value!=null && ClassUtils.isAssignable(value.getClass(), ProtoObject.class)){
	    			cField.set(clone, ((ProtoObject<?>)value).clone());
		    	}else if(value!=null && ClassUtils.isAssignable(value.getClass(), Cloneable.class)){
		    		cField.set(clone, ObjectUtils.clone(value));
		    	}
			}
			return clone;
		} catch (Exception e) {
			throw new SerializationException(e);
		}
    }
    
    private List<Field> processFields(){
    	List<Field> pFields = new ArrayList<Field>();
		for (Class<?> c = getClass(); c != null; c = c.getSuperclass()) {
	        Field[] fields = c.getDeclaredFields();
	        for (Field cField : fields) {
	        	if (!Modifier.isStatic(cField.getModifiers())
                        && !Modifier.isTransient(cField.getModifiers())) {
	        	    cField.setAccessible(true);
		        	pFields.add(cField);
	        	}
	        }
	    }
		return pFields;
	}
    
    @Override
	public boolean equals(Object o) {
	    if(o == null) return false;
	    if(this == o) return true;
	    if(this.getClass() != o.getClass()) return false;	   
	
	    EqualsBuilder equals =  new EqualsBuilder();
	    for (Field cField : processFields()) {
			try {
				equals.append(cField.get(this), cField.get(o));
			} catch (IllegalAccessException e) {
				return false;
	        }
		}
	    return equals.isEquals();
	}
    
    @Override
    public int hashCode(){
	    HashCodeBuilder hash =  new HashCodeBuilder(17, 37);
	    for (Field cField : processFields()) {
    		try {
				hash.append(cField.get(this));
			} catch (IllegalAccessException e) {
				return -1;
			}
		}
	    return hash.toHashCode();
    }
    
    @Override
    public String toString(){
    	ToStringBuilder tostring = new ToStringBuilder(this, ToStringStyle.JSON_STYLE);
	    for (Field cField : processFields()) {
    		try {
    			tostring.append(cField.getName(), cField.get(this));
			} catch (IllegalAccessException e) {
				return StringUtils.EMPTY;
			}
		}
	    return tostring.toString();
    }

    @Override
    public int compareTo(T obj) {
        if(obj == null) return 1;
        if(this.equals(obj)) return 0;

        CompareToBuilder compare = new CompareToBuilder();
        for (Field cField : processFields()) {
            try {
                compare.append(cField.get(this),cField.get(obj));
            } catch (IllegalAccessException e) {
                return -1;
            }
        }
        return compare.toComparison();
    }
    
    public boolean hasNotInfo() {
    	return !hasInfo();
    }
    @SuppressWarnings("rawtypes")
	public boolean hasInfo() {
    	try{
	    	boolean info = false; 
		    for (Field cField : processFields()) {
		    	Object value = cField.get(this);
		    	if(value!=null && ClassUtils.isAssignable(value.getClass(), ProtoObject.class)){
		    		info = info || ((ProtoObject)value).hasInfo();
		    	}else{
		    		info = info || (value!= null);
		    	}
			}
		    return info;
    	}catch (Exception e){
    		return false;
		}
    }

}
