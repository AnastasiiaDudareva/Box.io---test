package com.box.io.rest.parser;

import android.graphics.Color;

import com.box.io.Keys;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ColorsParser extends JsonParser<List<Integer>> {

    @Override
    public List<Integer> parse(JsonNode jsonNode) {
        List<Integer> list = new ArrayList<>();
        if (jsonNode.hasNonNull(Keys.COLORS)) {
            Iterator<JsonNode> iterator = jsonNode.path(Keys.COLORS).elements();
            while (iterator.hasNext()) {
                JsonNode node = iterator.next();
                try {
                    list.add(Color.parseColor(node.asText()));
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return list;
    }

}
