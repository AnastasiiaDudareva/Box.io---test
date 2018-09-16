package com.box.io.rest.parser;

import com.box.io.Keys;
import com.box.io.models.Box;
import com.fasterxml.jackson.databind.JsonNode;

public class BoxParser extends JsonParser<Box> {

    @Override
    public Box parse(JsonNode jsonNode) {
        Box box = new Box();
        if (jsonNode.hasNonNull(Keys.WIDTH))
            box.width = jsonNode.get(Keys.WIDTH).asInt();
        if (jsonNode.hasNonNull(Keys.HEIGHT))
            box.height = jsonNode.get(Keys.HEIGHT).asInt();
        if (jsonNode.hasNonNull(Keys.DEPTH))
            box.depth = jsonNode.get(Keys.DEPTH).asInt();

        return box;

    }
}
