import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class FileSystemNode {
   protected String name;
   protected String path;
   protected Permission permissions;
   protected String owner;
    protected String group;

    protected FileSystemNode(String name, String path, String user, String group)
    {

        this.path=path;
        this.permissions=new Permission();
        this.owner = user;
        this.group=group;
        try {
            this.setName(name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }




    }

    public Permission getPermissions() {
        return permissions;
    }

    public void setPermissions(Permission permissions) {
        this.permissions = permissions;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }


   public String getPath(){
       return path;
   }
   public void setPath(String path){
       this.path=path;
   }
    public String getName(){
        return this.name;
    }
    public void setName(String name) throws IllegalArgumentException{
        if(name.equals("root"))
        {
            throw new IllegalArgumentException("Can't name the folder root");
        }

        this.name = name;
    }
    public void updatePermission(Permission nPermission){
      permissions=nPermission;
    }

    public abstract FileType getType();
    public abstract List<String> ls();
    public abstract NestedList openAll();
    public abstract long getSize();

}
