package messages;

import java.util.ArrayList;

public abstract class DigestMessage implements Message<ArrayList<String>> {
    private final ArrayList<String> message;

    protected DigestMessage() {
        message = new ArrayList<>();
    }

    @Override
    public ArrayList<String> getContent() {
        return this.message;
    }

    @Override
    public String getSender() {
        return "";
    }

    @Override
    public String getReceiver() {
        return "";
    }
    public void addMessage(String message) {
        this.message.add(message);
    }
}
