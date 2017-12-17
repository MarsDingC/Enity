import java.util.Comparator;

/**
 * Created by Tim on 2017/12/13
 */
public class MyEnityComparator implements Comparator<MyEnity> {
    @Override
    public int compare(MyEnity o1, MyEnity o2) {
        return o1.getDepth() - o2.getDepth();
    }
}
