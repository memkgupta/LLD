package exceptions;

public class BufferOutOfBoundException extends Exception {
    private final int offset;

    public BufferOutOfBoundException(int offset) {
        super("Buffer out of bound");
        this.offset = offset;
    }
    public int getOffset() {
        return offset;
    }
}
