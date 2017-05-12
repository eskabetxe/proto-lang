package pro.boto.protolang.utils;

import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cleaner {
	
	public static String trim(String value){
		String toTrim = StringUtils.trim(value);
		toTrim = StringUtils.strip(toTrim);
		if(StringUtils.isEmpty(toTrim)) return null;
		return StringUtils.trim(toTrim);
	}
	
	protected static String toNumber(String value){
		String toNumber = trim(value);
		return StringUtils.replace(toNumber, " ", "");
	}
	
	public static String cleanDoubleSpaces(String value){
		while(value.contains("  ")){
			value = value.replaceAll("  ", " ");
		}
		return value;
	}
	
	private static Pattern INTEGER = Pattern.compile("^((-|\\+)?(\\d+))((,|\\.)(\\d+))?$");
	public static String toInteger(String value){
		if(StringUtils.isBlank(value)) return null;
		Matcher matcher = INTEGER.matcher(toNumber(value));
		if(!matcher.matches()) return null;
		return matcher.replaceAll("$1");
	}
	
	private static Pattern DOUBLE = Pattern.compile("^((-|\\+)?(\\d+)((,|\\.)(\\d+))?)$");
	public static String toDouble(String value){
		if(StringUtils.isBlank(value)) return null;
		Matcher matcher = DOUBLE.matcher(toNumber(value));
		if(!matcher.matches()) return null;
		return matcher.replaceAll("$1").replace(",", ".");
	}
	
	public static String unicodeCharacters(String value) {
		if(StringUtils.isBlank(value)) return null;
		return value.replaceAll("\\p{Cntrl}", "");
	}
	
	public static String toMaxBytes(final String str, final Charset charset, final Integer maxBytes) {
		if(StringUtils.isNotBlank(str) && str.getBytes(charset).length > maxBytes){
			// Ensure truncating by having byte buffer 
 		   	ByteBuffer bb = ByteBuffer.wrap(str.getBytes(charset), 0, maxBytes); 
 		   	CharBuffer cb = CharBuffer.allocate(maxBytes); 
 		   	// Ignore an incomplete character
 		   	CharsetDecoder cd = charset.newDecoder().onMalformedInput(CodingErrorAction.IGNORE);
 		   	cd.decode(bb, cb, true); 
 		   	cd.flush(cb);
 		   	return new String(cb.array(), 0, cb.position());
 	   	}else{
 	   		return str;
 	   	}
    } 
	
	public static String cleanXmlProblemChars(String str){
		if(StringUtils.isBlank(str)) return null;
		str = str.replaceAll("\uFEFF", ""); // UTF-8 BOM chars
		str = str.replaceAll("&#x1f", "");
		str = str.replaceAll("&#x1", "");
		str = str.replaceAll("&#xb", "");
		str = str.replaceAll("&#xB", "");
		str = str.replaceAll("",""); // to remove "…"
		return str;
	}
}
