package pro.boto.protolang.serials;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import pro.boto.protolang.domain.ProtoList;
import pro.boto.protolang.domain.ProtoObject;


public abstract class ProtoSerializer<T> extends com.fasterxml.jackson.databind.JsonSerializer<T> {
	
	private JsonGenerator generator; 
	private SerializerProvider serializers;
	
	@Override
	final public void serialize(T value, JsonGenerator gen, SerializerProvider serializers)	throws IOException {
		
		this.generator = gen;
		this.serializers = serializers;
		
		if(value instanceof ProtoList<?>){
			serialize(value);
		}else{
			generator.writeStartObject();
			serialize(value);
			generator.writeEndObject();
		}
	}
	
	protected abstract void serialize(T value) throws IOException;
	
	protected ObjectNode obtainNode(){
		return JsonNodeFactory.instance.objectNode();
	}
	
	protected void serialize(ProtoField field, Object value) throws IOException{
		serialize(field.property(),value);
	}
	protected void serialize(String field, Object value) throws IOException{
		if(value == null) return;
		if(ClassUtils.isAssignable(value.getClass(), ProtoObject.class) 
				&& ((ProtoObject<?>)value).hasNotInfo()) return;
		generator.writeFieldName(field);
		serializers.defaultSerializeValue(value, generator);
	}
	
	protected void serialize(ProtoField field, Integer value) throws IOException{
		if(value == null) return;
		generator.writeNumberField(field.property(), value);
	}
	
	protected void serialize(ProtoField field, Double value) throws IOException{
		if(value == null) return;
		generator.writeNumberField(field.property(), value);
	}
	
	protected void serialize(ProtoField field, String value) throws IOException{
		if(StringUtils.isBlank(value)) return;
		generator.writeStringField(field.property(), value);
	}
	
	protected void serialize(ProtoField field, Boolean value) throws IOException{
		if(value==null) return;
		generator.writeBooleanField(field.property(), value);
	}
	
	protected void serialize(ProtoField field, Date value, String format) throws IOException{
		if(value==null) return;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String sValue = dateFormat.format(value);
		generator.writeStringField(field.property(), sValue);
	}
	
	protected void serialize(ProtoField field, Enum<?> value) throws IOException{
		if(value==null) return;
		serialize(field, value.name());
	}
	
	protected void serialize(ProtoField field, Map<?,?> value) throws IOException{
		if(value==null || value.isEmpty()) return;
		generator.writeFieldName(field.property());
		serializers.defaultSerializeValue(value, generator);
	}
	
	protected <O extends ProtoObject<?>> void serializeList(List<O> value) throws IOException{
		if(value==null) return;
		generator.writeObject(value);
	}
	protected void serializeObject(ProtoObject<?> value) throws IOException{
		if(value==null) return;
		generator.writeObject(value);
	}
	
	protected void serialize(ProtoField field, List<?> value) throws IOException{
		if(value==null || value.isEmpty()) return;
		generator.writeFieldName(field.property());
		serializers.defaultSerializeValue(value, generator);
	}
}
