package memento;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private Map<String,String> map;
    public Database(){
        this.map = new HashMap<>();
    }
    public void create(String key,String value){
        if(this.map.containsKey(key)){
            throw new IllegalArgumentException("key already exists");
        }
        this.map.put(key,value);
    }
    public String read(String key){
        return this.map.get(key);
    }

    public void update(String key,String value){
        if(!this.map.containsKey(key)){
            throw new IllegalArgumentException("Key dont exist");
        }
        this.map.put(key,value);
    }
    public void delete(String key){
        if(this.map.containsKey(key)){
            this.map.remove(key);
        }
        else throw new IllegalArgumentException("Key dont exist");

    }
    public Memento save(){
        return new Memento(this.map);
    }
    public void restore(Memento memento){
        this.map = memento.getState();
    }

    public Map<String, String> getMap() {
        return map;
    }
}
