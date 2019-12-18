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
