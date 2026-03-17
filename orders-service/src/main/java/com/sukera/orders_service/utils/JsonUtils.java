package com.sukera.orders_service.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    static final ObjectMapper mapper = new ObjectMapper();

    static public String toJson(Object obj){
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
           throw new RuntimeException("Error converting to JSON", e);
        }
    }

    static public <T> T fromJson(String json, Class<T> clazz){
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
           throw new RuntimeException("Error converting from JSON", e);
        }
    }

}
