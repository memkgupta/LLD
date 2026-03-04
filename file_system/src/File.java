import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class File extends FileSystemNode{

    private long size;
    private List<Byte> content;
    private FileMetadata metaData;
    protected File(String name,String path,String user , String group) {
        super(name,path,user , group);

    }
    public void setContent(List<Byte> content) {
        this.content = content;
    }
    public FileMetadata getMetaData() {
        return metaData;
    }
    public void setMetaData(FileMetadata metaData) {
        this.metaData = metaData;
    }

    public List<Byte> getContent() {
        return content;
    }
    public void setSize(long size) {
        this.size = size;
    }
    public Byte read(int offset)
    {
        return content.get(offset);
    }
    public void addContent(Byte content)
    {
        this.content.add(content);
        size++;
    }
    public void setContent(byte[] content) {
        Byte[] boxed = new Byte[content.length];

        for (int i = 0; i < content.length; i++) {
            boxed[i] = content[i];   // auto boxing
        }
        this.content = Arrays.asList(boxed);
        this.size = content.length;
    }

    @Override
    public FileType getType() {
        return FileType.FILE;
    }

    @Override
    public List<String> ls() {
        return List.of();
    }

    @Override
    public NestedList openAll() {
        throw new RuntimeException("Invalid method");
    }

    @Override
    public long getSize() {
        return this.size;
    }
}
