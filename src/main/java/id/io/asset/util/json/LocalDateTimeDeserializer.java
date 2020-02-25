/**
  * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
  *
  * Copyright (c) 2019 IO-Teknologi Indonesia, and individual contributors
  * as indicated by the @author tags. All Rights Reserved
  *
  * The contents of this file are subject to the terms of the
  * Common Development and Distribution License (the License).
  *
  * Everyone is permitted to copy and distribute verbatim copies
  * of this license document, but changing it is not allowed.
  *
  */
package id.io.asset.util.json;  

import java.io.IOException;
import java.time.Instant; 
import java.time.LocalDateTime;
import java.time.ZoneId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext dc) throws IOException {
        ObjectCodec codec = jp.getCodec();
        TextNode node = codec.readTree(jp);
        String dateString = node.textValue();
        Instant instant = Instant.parse(dateString);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
