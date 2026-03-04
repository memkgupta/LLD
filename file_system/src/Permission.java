public class Permission {

    boolean[] owner;

    boolean[] group;

    boolean[] other;

    public Permission() {
        this.owner=new boolean[]{true , true , true};
        this.group=new boolean[3];
        this.other=new boolean[3];
    }
    public Permission(boolean[] owner, boolean[] group, boolean[] other){
        this.owner=owner;
        this.group=group;
        this.other=other;
    }

}