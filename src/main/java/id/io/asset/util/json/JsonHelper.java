package id.io.asset.util.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.io.asset.util.log.BaseLogger;

public class JsonHelper {

    private static final BaseLogger log = new BaseLogger(JsonHelper.class);
    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static ObjectMapper getMapper() {
        return OBJECT_MAPPER;
    }

    public static String toJson(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("toJson", e);
        }
        return "";
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            log.error("fromJson", e);
        }

        try {
            return clazz.getConstructor().newInstance();
        } catch (Exception ex) {
            log.error("fromJson", "Could not invoke Default Constructor", ex);
        }

        return null;
    }

    public static <T> T fromJson(String json, TypeReference<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            log.error("fromJson", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
        try {
            Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + clazz.getName() + ";");
            T[] objects = OBJECT_MAPPER.readValue(json, arrayClass);
            return Arrays.asList(objects);
        } catch (ClassNotFoundException | IOException e) {
            log.error("fromJsonArray", e);
        }
        return new ArrayList<>();
    }
}
