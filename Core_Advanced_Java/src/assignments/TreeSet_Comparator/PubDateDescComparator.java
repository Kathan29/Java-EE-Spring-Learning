package assignments.TreeSet_Comparator;

import java.util.Comparator;

public class PubDateDescComparator implements Comparator{
    public int compare(Object o1,Object o2){
        int res = Integer.valueOf(((Book)o2).getYear()).compareTo(Integer.valueOf(((Book)o1).getYear()));
        
        if(res==0){
            return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());
        }
        
        return res;
    }    
}
