package messages;

import java.util.ArrayList;
import java.util.List;

public abstract class DigestMessage extends Message<List<String>> {


    protected DigestMessage(String userId) {
        super(new ArrayList<>(), userId);

    }
    public void addMessage(String message) {
        this.content.add(message);
    }
}
