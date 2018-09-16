package com.box.io.rest.parser;

import com.box.io.Keys;
import com.box.io.models.AvailBoxWrapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayBoxParser extends JsonParser<List<AvailBoxWrapper>> {
    private BoxParser boxParser = new BoxParser();
    private ColorsParser colorsParser = new ColorsParser();

    @Override
    public List<AvailBoxWrapper> parse(JsonNode jsonNode) {
        List<AvailBoxWrapper> list = new ArrayList<>();
        if (jsonNode.hasNonNull(Keys.AVAIL_BOX)) {
            Iterator<JsonNode> iterator = jsonNode.path(Keys.AVAIL_BOX).elements();
            while (iterator.hasNext()) {
                JsonNode node = iterator.next();
                AvailBoxWrapper wrapper = new AvailBoxWrapper();
                wrapper.box = boxParser.parse(node);
                wrapper.colors = colorsParser.parse(node);
                list.add(wrapper);
            }
        }
        return list;

    }
}
