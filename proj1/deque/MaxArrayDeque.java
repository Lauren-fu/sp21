package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    /* creates a MaxArrayDeque with the given Comparator
     */

    private Comparator<T> comp;

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
}
