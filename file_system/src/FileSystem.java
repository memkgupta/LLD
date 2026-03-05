import java.util.Arrays;
import java.util.List;

public class FileSystem {
    private FileSystemNode root;
    private final String user;
    private final String group;
    public FileSystem(String user, String group)
    {
        this.user = user;
        this.group = group;
        this.root = new Folder("root","/" , "root","root");
    }
    public void createFile(String path)
    {
        String[] splittedPath = getSplittedPath(path);
        String fileName = splittedPath[splittedPath.length-1];
        FileSystemNode parentFolder = resolvePath(splittedPath,root,0,splittedPath.length-2);
        if(! (parentFolder instanceof Folder))
        {
            throw new IllegalArgumentException("Invalid path");
        }

        FileSystemNode file = new File(fileName , path , user , group);
        ((Folder) parentFolder).addChild(file);
    }
    public Byte[] readFile(String path , int offset , int length)
    {
        String[] splittedPath = getSplittedPath(path);
        FileSystemNode resolved = resolvePath(splittedPath, root,0,splittedPath.length-1);
        if(! (resolved instanceof File file))
        {
            throw new IllegalArgumentException("Invalid file");
        }
        return file.read(offset,length);
    }
    public Byte[] readFile(String path , int offset)
    {
        String[] splittedPath = getSplittedPath(path);
        FileSystemNode resolved = resolvePath(splittedPath, root,0,splittedPath.length-1);
        if(! (resolved instanceof File file))
        {
            throw new IllegalArgumentException("Invalid file");
        }
        return file.read(offset,(int)file.getSize());
    }
    public void writeFile(String path,byte[] data)
    {
        String[] splittedPath = getSplittedPath(path);
        FileSystemNode resolved = resolvePath(splittedPath, root,0,splittedPath.length-1);
        if(! (resolved instanceof File file))
        {
            throw new IllegalArgumentException("Invalid file");
        }
        file.setContent(data);
    }
    public void appendFile(String path,byte[] data)
    {
        String[] splittedPath = getSplittedPath(path);
        FileSystemNode resolved = resolvePath(splittedPath,root,0,splittedPath.length-1);
        if(! (resolved instanceof File file))
        {
            throw new IllegalArgumentException("Invalid file");
        }
        for(int i = 0; i < splittedPath.length; i++)
        {
            file.addContent(data[i]);
        }

    }
    public void deleteFile(String path)
    {
        String[] splittedPath = getSplittedPath(path);

        FileSystemNode resolved = resolvePath(splittedPath,root,0,splittedPath.length-2);
        if(!(resolved instanceof Folder folder))
        {
            throw new IllegalArgumentException("Invalid path");
        }
        folder.removeChild(splittedPath[splittedPath.length-1]);
    }
    public void mkdir(String path)
    {
        String[] splittedPath = getSplittedPath(path);
        String name = splittedPath[splittedPath.length-1];
        if(name.contains("."))
        {
            throw new IllegalArgumentException("Invalid folder name");
        }
        FileSystemNode resolved = resolvePath(splittedPath , root,0 , splittedPath.length-2);
        if(!(resolved instanceof Folder folder))
        {
            throw new IllegalArgumentException("Invalid path");
        }
        folder.addChild(new Folder(name,path,user,group));
    }

    private static String[] getSplittedPath(String path) {
        String[] splittedPath = path.split("/");
        splittedPath[0] = "root";
        return splittedPath;
    }

    public void rmdir(String path)
    {
        String[] splittedPath = getSplittedPath(path);
        String name = splittedPath[splittedPath.length-1];
        if(name.contains("."))
        {
            throw new IllegalArgumentException("Invalid folder name");
        }
        FileSystemNode resolved = resolvePath(splittedPath , root,0 , splittedPath.length-2);
        if(!(resolved instanceof Folder folder))
        {
            throw new IllegalArgumentException("Invalid path");
        }
        folder.removeChild(name);

    }
    public List<String> ls(String path)
    {
        String[] splittedPath = getSplittedPath(path);
        FileSystemNode folder = resolvePath(splittedPath , root,0 , splittedPath.length-1);
        return folder.ls();
    }

    public void move(String from, String to)
    {
        String[] splittedPathFrom = getSplittedPath(from);
        FileSystemNode node = resolvePath(splittedPathFrom,root,0,splittedPathFrom.length-1);
        String[] splittedPathTo = getSplittedPath(to);
        FileSystemNode nodeTo = resolvePath(splittedPathTo,root,0,splittedPathTo.length-1);
        if(!(nodeTo instanceof Folder tofolder))
        {
            throw new IllegalArgumentException("Invalid path to");
        }
        FileSystemNode fromParent = resolvePath(splittedPathFrom,root,0,splittedPathFrom.length-2);
        if(!(fromParent instanceof Folder fromParentFolder))
        {
            throw new IllegalArgumentException("Invalid path from");
        }
        (fromParentFolder).removeChild(node.getName());
        (tofolder).addChild(node);
    }
    public void rename(String path , String newName)
    {
        String[] splittedPath = getSplittedPath(path);
        FileSystemNode node = resolvePath(splittedPath,root,0,splittedPath.length-1);
        node.setName(newName);
    }

    public FileSystemNode resolvePath(String[] path, FileSystemNode node ,int index,int end)
    {

        if(index>end)
        {
            return node;
        }
      if(index==end)
      {
          if(!node.getName().equals(path[end]))
          {
              throw new IllegalArgumentException("File not found");
          }
          return node;
      }
        if(! (node instanceof Folder folder))
        {
            throw new IllegalArgumentException("Invalid path");
        }
        String childName = path[index+1];
        for(FileSystemNode child : folder.getChildren())
        {
            if(child.getName().equals(childName))
            {
                return resolvePath(path,child,index+1,end);
            }
        }
        throw new IllegalArgumentException("File not found");
    }

}
