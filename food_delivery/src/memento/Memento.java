package memento;

import java.util.HashMap;
import java.util.Map;

public class Memento {
    private Map<String,String> state;
    public Memento(Map<String,String> state) {
        this.state = new HashMap<String,String>(state);
    }

    public Map<String, String> getState() {
        return state;
    }

    public void setState(Map<String, String> state) {
        this.state = state;
    }
}
