package top.auntie;
import com.alibaba.fastjson.JSON;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class Params implements Map<String, Object> {
    private Map<String, Object> map = new HashMap<>();

    public Params(Map<String, Object> map) {
        this.map = map;
    }

    public String toJSONString() {
        return JSON.toJSONString(this.map);
    }

    public Params replaceParam(String oldKey, String newKey) {
        if (this.map.containsKey(oldKey))
            this.map.put(newKey, this.map.remove(oldKey));
        return this;
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean containsKey(Object o) {
        return this.map.containsKey(o);
    }

    public boolean containsValue(Object o) {
        return this.map.containsValue(o);
    }

    public Object get(Object o) {
        return this.map.get(o);
    }

    public Object put(String s, Object o) {
        return this.map.put(s, o);
    }

    public Object remove(Object o) {
        return this.map.remove(o);
    }

    public void putAll(Map<? extends String, ?> map) {
        this.map.putAll(map);
    }

    public void clear() {
        this.map.clear();
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    public Collection<Object> values() {
        return this.map.values();
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    public String toString() {
        return toJSONString();
    }

    public Params() {}
}
