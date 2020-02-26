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
package id.io.asset.util.http.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HTTPHeader { 
    private Map<String, String> map;

    public HTTPHeader() {
        map = new HashMap<>();
    }

    public void addHeader(String key, String value) {
        map.put(key, value);
    }

    public void addHeaders(HTTPHeader header) {
        map.putAll(header.map);
    }

    public void removeHeader(String key) {
        if (map.containsKey(key)) {
            map.remove(key);
        }
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    public void clear() {
        map.clear();
    }
}
