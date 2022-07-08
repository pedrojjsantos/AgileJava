package sis.util;

import java.util.*;

public class MultiHashMap<K, V> {
    protected Map<K, List<V>> map = new HashMap<>();

    public int size() {
        return map.size();
    }

    public void put(K key, V value) {
        List<V> col =
                map.computeIfAbsent(key, k -> new ArrayList<>());
        col.add(value);
    }

    public List<V> get(K key) {
        return map.get(key);
    }
}
