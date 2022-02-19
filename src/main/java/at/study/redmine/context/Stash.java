package at.study.redmine.context;

import javax.jws.Oneway;
import java.util.HashMap;
import java.util.Map;

public class Stash {

    private Map<String, Object> enities = new HashMap<>();

    public void put(String key, Object object){
        enities.put(key, object);
    }

    public Object get(String key){
        return enities.get(key);
    }

    public <T> T get(String key, Class<T> clazz){
        return (T) enities.get(key);
    }

    public boolean contains(String key){
        return enities.containsKey(key);
    }
}
