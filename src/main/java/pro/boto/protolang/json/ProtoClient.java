package pro.boto.protolang.json;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

/**
 * @author eskabetxe
 */
public abstract class ProtoClient {

    private static ObjectMapper MAPPER;
    private static ObjectMapper MAPPER_PRETTY;

    static {
    	MAPPER = new ObjectMapper();
    	MAPPER.registerModule(new AfterburnerModule());
    	
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    	MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    	
    	MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    	MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    	MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        MAPPER_PRETTY = MAPPER.copy();
        MAPPER_PRETTY.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private final static ObjectMapper obtainPrettyMapper(){
        return MAPPER_PRETTY;
    }
    private final static ObjectMapper obtainMapper(){
        return MAPPER;
    }
    
    public static String obtainJson(Object object) throws IOException{
    	if(object==null) return StringUtils.EMPTY;
        return obtainMapper().writeValueAsString(object);
    }
    
    public static String obtainJsonPretty(Object object) throws IOException{
    	if(object==null) return StringUtils.EMPTY;
        return obtainPrettyMapper().writeValueAsString(object);
    }

    public static <T> T obtainObject(Class<T> clazz, String json) throws IOException{
        return obtainMapper().readValue(json, clazz);
    }

}
