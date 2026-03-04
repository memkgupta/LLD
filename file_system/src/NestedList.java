import java.util.*;

public class NestedList {

    private String value;
    private List<NestedList> list;

    // constructor for single string
    public NestedList(String value) {
        this.value = value;
        this.list = null;
    }

    // constructor for nested list
    public NestedList() {
        this.list = new ArrayList<>();
        this.value = null;
    }

    public boolean isValue() {
        return value != null;
    }

    public String getValue() {
        return value;
    }

    public List<NestedList> getList() {
        return list;
    }

    public void add(NestedList element) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(element);
    }
    public void print() {
        if (isValue()) {
            System.out.print(value);
        } else {
            System.out.print("[");
            for (int i = 0; i < list.size(); i++) {
                list.get(i).print();
                if (i < list.size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("]");
        }
    }
}