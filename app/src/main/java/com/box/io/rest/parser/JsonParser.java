package com.box.io.rest.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public abstract class JsonParser<T>  {

    public T parse(String data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(data);
            return parse(jsonNode);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public T parse(JsonNode jsonNode) {
        return (T) jsonNode;
    }
}
