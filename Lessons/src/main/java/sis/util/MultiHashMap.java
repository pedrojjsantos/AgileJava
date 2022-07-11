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

    public interface Filter<T> {
        boolean apply(T item);
    }

    public static <K,V> void filter(
            final MultiHashMap<K, ? super V> target,
            final MultiHashMap<K, V> source,
            final Filter<? super V> filter) {
        for (K key : source.keys()) {
            final List<V> values = source.get(key);
            for (V value : values)
                if (filter.apply(value))
                    target.put(key, value);
        }
    }

    private Set<K> keys() {
        return map.keySet();
    }
}
