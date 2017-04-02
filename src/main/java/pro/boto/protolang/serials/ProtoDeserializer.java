package pro.boto.protolang.serials;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pro.boto.protolang.domain.ProtoObject;


public abstract class ProtoDeserializer<T> extends com.fasterxml.jackson.databind.JsonDeserializer<T> {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	protected ObjectCodec codec; 
	protected JsonNode node;
	protected DeserializationContext context;
	
	@Override
	final public T deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
		
		if(!parser.hasCurrentToken()) return null;
		
		this.codec = parser.getCodec();
		this.node  = parser.readValueAsTree();
		this.context = ctx;
		
		return deserialize();
	}
	
	protected abstract T deserialize() throws IOException;
	
	protected <O extends ProtoObject<?>> O deserializeObject(ProtoField field, Class<O> clazz) throws IOException{
		JsonNode node = deserialize(field);
		if(node.isMissingNode()) {
			return null;
		}
		JsonParser parser = deserialize(field).traverse(codec);
		O bean = parser.readValueAs(clazz);
		return bean;
	}
	
	protected <O extends ProtoObject<?>> O deserializeObject(Class<O> clazz) throws IOException{
		O bean = context.getParser().readValueAs(clazz);
		return bean;
	}
	
	protected <O extends ProtoObject<?>> List<O> deserializeList(Class<O> clazz) throws IOException{
		JavaType type = context.getTypeFactory().constructCollectionType(List.class, clazz);
		JsonParser parser = node.traverse(codec);
		
		List<O> bean = ((ObjectMapper)codec).readValue(parser, type);
		return bean;
	}
	
	private JsonNode deserialize(ProtoField field) {
		return node.path(field.property());
	}
	
	protected String deserializeString(ProtoField field) {
		return deserialize(field).textValue();
	}
	protected String deserializeString(String field) {
		return node.path(field).textValue();
	}
	
	protected Integer deserializeInteger(ProtoField field) {
		Number number = deserialize(field).numberValue();
		if(number==null) return null;
		return number.intValue();
	}
	
	protected Double deserializeDouble(ProtoField field) {
		Number number = deserialize(field).numberValue();
		if(number==null) return null;
		return number.doubleValue();
	}
	
	protected Boolean deserializeBoolean(ProtoField field) {
		String bool = deserialize(field).asText();
		if(StringUtils.isBlank(bool)) return null;
		return BooleanUtils.toBooleanObject(bool);
	}
	
	protected Date deserializeDate(ProtoField field, String format) {
		String date = deserialize(field).asText();
		if(StringUtils.isBlank(date)) return null;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			LOG.error("Error parsing date '" + date + "' to format '" + format + "'");
			return null;
		}
	}
	
	public <E extends Enum<E>> E deserializeEnum(ProtoField field, Class<E> eClazz){
		String value = deserializeString(field);
		if(StringUtils.isBlank(value)) return null;
		
		return Enum.valueOf(eClazz, value.toUpperCase());
	}
	
	
	@SuppressWarnings("rawtypes")
	public Map deserializeMap(ProtoField field) throws IOException{
		JsonNode node =  deserialize(field);
		if(node.size()==0)return null;
		
		JavaType type = context.getTypeFactory().constructType(Map.class);
		JsonParser parser = node.traverse(codec);
		Object bean =  ((ObjectMapper)codec).readValue(parser, type);
		return (Map) bean;
	}
	
	@SuppressWarnings({ "unchecked" })
	protected <P extends Object> List<P> deserializeList(ProtoField field, Class<P> clazz) throws IOException {
		JsonNode node =  deserialize(field);
		if(!node.isArray())return null;
		
		JavaType type = context.getTypeFactory().constructCollectionType(List.class, clazz);
		JsonParser parser = node.traverse(codec);
		Object bean =  ((ObjectMapper)codec).readValue(parser, type);
		return (List<P>) bean;
	}
	@SuppressWarnings({ "unchecked" })
	protected <P extends Object> LinkedList<P> deserializeLinkedList(ProtoField field, Class<P> clazz) throws IOException {
		JsonNode node =  deserialize(field);
		if(!node.isArray())return null;
		
		JavaType type = context.getTypeFactory().constructCollectionType(LinkedList.class, clazz);
		JsonParser parser = node.traverse(codec);
		Object bean =  ((ObjectMapper)codec).readValue(parser, type);
		return (LinkedList<P>) bean;
	}
	
	
}
