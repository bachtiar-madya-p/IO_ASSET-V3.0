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
package id.io.asset.util.csv;

import java.util.HashMap;
import java.util.Map;
import java.util.Set; 

public class CSVRecord { 

    private Map<String, String> attributeMap;

    public CSVRecord() {
        attributeMap = new HashMap<String, String>();
    }

    public void put(String key, String value) {
        attributeMap.put(key, value);
    }

    public String get(String key) {
        return attributeMap.getOrDefault(key, "");
    }

    public boolean contains(String key) {
        return attributeMap.containsKey(key);
    }

    public Set<String> getHeaders() {
        return attributeMap.keySet();
    }

    public Map<String, String> getAttributeMap() {
        return attributeMap;
    }
}
