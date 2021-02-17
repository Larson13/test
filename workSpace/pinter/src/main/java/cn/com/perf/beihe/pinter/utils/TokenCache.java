package cn.com.perf.beihe.pinter.utils;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum TokenCache {
    INSTANCE;

    public Map<String, String> cache;

    TokenCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    public void put(String key, String value) {
        this.cache.put(key, value);
    }

    public String get(String key) {
        return this.cache.get(key);
    }

    public String remove(String key) {
        return this.cache.remove(key);
    }

    public boolean containsKey(String key) {
        return this.cache.containsKey(key);
    }
}
