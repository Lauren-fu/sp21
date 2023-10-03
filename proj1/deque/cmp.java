package deque;
import java.util.Comparator;

public class cmp<T> implements Comparator<T> {
    public int compare(T o1, T o2) {
        return (int)o1 - (int)o2;
    }
}
