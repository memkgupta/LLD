import java.util.List;

public class FileSystem {
    private FileSystemNode root;
    public FileSystem()
    {
        this.root = new Folder("root","/" , "root","root");
    }
    public void createFile(String path)
    {

    }
    public byte[] readFile(String path)
    {

    }
    public void writeFile(String path,byte[] data)
    {

    }
    public void appendFile(String path,byte[] data)
    {

    }
    public void deleteFile(String path)
    {

    }
    public void mkdir(String path)
    {

    }
    public void rmdir(String path)
    {

    }
    public List<String> ls(String path)
    {

    }

    public void move(String from, String to)
    {

    }
    public void rename(String path , String newName)
    {

    }
    public void chmod(String path , String perm)
    {

    }
    public FileSystemNode resolvePath(String path)
    {

    }

}
