package com.box.io.rest.parser;

import com.box.io.Keys;
import com.box.io.models.UserBox;
import com.fasterxml.jackson.databind.JsonNode;

public class UserBoxParser extends JsonParser<UserBox> {
    private BoxParser parser = new BoxParser();

    @Override
    public UserBox parse(JsonNode jsonNode) {
        UserBox box = new UserBox();
        box.box = parser.parse(jsonNode);
        if (jsonNode.hasNonNull(Keys.NAME))
            box.name = jsonNode.get(Keys.NAME).asBoolean();
        if (jsonNode.hasNonNull(Keys.EMAIL))
            box.email = jsonNode.get(Keys.EMAIL).asText();
        if (jsonNode.hasNonNull(Keys.COLOR))
            box.color = jsonNode.get(Keys.COLOR).asInt();
        return box;

    }
}
