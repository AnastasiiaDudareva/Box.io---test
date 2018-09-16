package com.box.io.rest.parser;

import com.box.io.Keys;
import com.box.io.models.User;
import com.fasterxml.jackson.databind.JsonNode;

public class UserParser extends JsonParser<User> {

    @Override
    public User parse(JsonNode jsonNode) {
        User user = new User();
        if (jsonNode.hasNonNull(Keys.NAME))
            user.name = jsonNode.get(Keys.NAME).asText();
        if (jsonNode.hasNonNull(Keys.EMAIL))
            user.email = jsonNode.get(Keys.EMAIL).asText();
        if (jsonNode.hasNonNull(Keys.USER_INFO))
            user.userInfo = jsonNode.get(Keys.USER_INFO).asText();

        return user;

    }
}
