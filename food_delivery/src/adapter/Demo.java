package adapter;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Demo {
    Map<String, Integer> blMap = Map.of(
            "electronics",1,
            "grocery",2,
            "pharmacy",3,
            "restaurant",4
    );
    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.validateCoupons(
                new String[]{"Qf8NjqOTYp","w4xOTEM20C"},new String[]{"pharmacy","pharmacy"},new boolean[]{true , true}
        );

    }


        public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {

            SortedSet<Integer> set = new TreeSet<>((a, b)->{

                int diff = blCompare(businessLine[a] , businessLine[b]);
                if(diff == 0)
                {

                    return businessLine[a].compareTo(businessLine[b]);
                }
                return diff;
            });
            for(int i = 0;i<code.length;i++)
            {
                String c = code[i];
                String bl = businessLine[i];
                boolean active = isActive[i];
                if(c.length() == 0 || !active || !blMap.containsKey(bl)) continue;
                if(isValid(c))
                {
                    set.add(i);
                }
            }
            return set.stream().map(i->code[i]).toList();
        }
        public boolean isValid(String a)
        {
            for(int i = 0;i<a.length();i++)
            {
                char c = a.charAt(i);
                if(c == '_') continue;
                if(!Character.isLetterOrDigit(c)) return false;
            }
            return true;
        }
        public int blCompare(String a , String b)
        {

            int diff =  blMap.get(a)-blMap.get(b);
            if(diff == 0) return 0;
            return diff < 0 ? -1 : 1;
        }
    }

