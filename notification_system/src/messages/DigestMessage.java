package messages;

import java.util.ArrayList;
import java.util.List;

public abstract class DigestMessage extends Message<List<StringMessage>> {


    protected DigestMessage(String userId) {
        super(new ArrayList<>(), userId);

    }
    public void addMessage(StringMessage message) {
        this.content.add(message);
    }
}
