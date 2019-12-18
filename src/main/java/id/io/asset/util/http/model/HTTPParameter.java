package id.io.asset.util.http.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HTTPParameter {

    private Map<String, String> map;

    public HTTPParameter() {
        map = new HashMap<>();
    }

    public void addParameter(String key, String value) {
        map.put(key, value);
    }

    public void removeParameter(String key) {
        if (map.containsKey(key)) {
            map.remove(key);
        }
    }

    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
    }
}
