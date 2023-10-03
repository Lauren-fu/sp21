package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    /* creates a MaxArrayDeque with the given Comparator
     */

    public Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c) {
        comp = c;
    }

    /*returns the maximum element in the deque as governed by the previous given comparator
     */
    public T max() {
        if(super.isEmpty()) {
            return null;
        }
        int maxElement = 0;
        for (int i = 1; i < super.size(); i += 1) {
            if(comp.compare(super.get(maxElement),super.get(i)) < 0) {
                maxElement = i;
            }
        }
        return super.get(maxElement);
    }

    /*returns the maximum element in the deque as governed by the given parameter comparator
     */
    public T max(Comparator<T> c) {
        comp = c;
        T value = max();
        return value;
    }

    public static void main(String[] args) {
        cmp cmptor = new cmp();
        MaxArrayDeque<Integer> lld = new MaxArrayDeque<>(cmptor);
        lld.addFirst(1);
        lld.addFirst(5);
        lld.addFirst(11);
        lld.addFirst(12);
        lld.addLast(13);
        lld.addFirst(104);
        lld.addFirst(105);
        lld.addFirst(106);
        lld.addFirst(100);
        System.out.println(lld.max());
    }

}
