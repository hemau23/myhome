package com.analytique.util;

import java.util.HashMap;
import java.util.Map;


public class MapBuilder {
    private Map<String, Object> parameters = null;

    public static final String KEY_DELIMITER = "-";

    private MapBuilder(String name, Object value) {
        this.parameters = new HashMap<String, Object>();
        this.parameters.put(name, value);

    }

    public static MapBuilder with(String name, Object value) {
        return new MapBuilder(name, value);
    }

    public MapBuilder and(String name, Object value) {
        this.parameters.put(name, value);
        return this;
    }

    public Map<String, Object> get() {
        return this.parameters;
    }

    public static <T> String buildKey(T record, String... keyFields) {
        StringBuilder builder = new StringBuilder();

        for (String keyField : keyFields) {
            if (builder.length() != 0) {
                builder.append(KEY_DELIMITER);
            }

            builder.append(ReflectionUtil.getField(record, keyField));
        }

        return builder.toString();
    }
}
