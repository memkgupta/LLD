import java.util.ArrayList;
import java.util.List;

public class Folder extends FileSystemNode{
    private String name;
    private List<FileSystemNode> children;
    public Folder(String name, String path,String user , String group)
    {
        super(name , path, user , group);
        this.name = name;
        this.children = new ArrayList<>();
    }
    public List<FileSystemNode> getChildren() {
        return children;
    }
    public void addChild(FileSystemNode child) {
        this.children.add(child);
    }
    public void removeChild(String name) {
        this.children.removeIf(f -> f.getName().equals(name));
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public FileType getType() {
        return FileType.FOLDER;
    }

    @Override
    public List<String> ls() {
      List<String> lst = new ArrayList<>();
        for (FileSystemNode node : children) {

            lst.add(node.getName());
        }
        return lst;
    }

    @Override
    public NestedList openAll() {
        return openAllPvt(this);
    }

    private NestedList openAllPvt(FileSystemNode root )
    {
        if(root.getType().equals(FileType.FILE))
        {
            return new NestedList(root.getName());
        }
        Folder folder = (Folder) root;
        NestedList list = new NestedList();
        list.add(new NestedList(folder.getName()));

        for(FileSystemNode child : folder.getChildren())
        {
            list.add(openAllPvt(child));
        }
        return list;
    }

    @Override
    public long getSize() {
        return children.stream().mapToLong(FileSystemNode::getSize).sum();
    }
}
