package org.yaukie.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	private static final ObjectMapper om = new ObjectMapper();
	
	public static <T> String object2json(T obj){
		String str ="";
		try {
			str = om.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
}
