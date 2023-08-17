
package deque;

public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int length = 8;

    /** creating an empty array deque */
    public ArrayDeque() {
        array = (T[]) new Object[length];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** create a deep copy of other */
    public ArrayDeque(ArrayDeque other) {
        length = other.size();
        array = (T[]) new Object[length];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        for(int i = 0; i < length; i += 1) {
            addLast((T)other.get(i));
        }
    }

    /** invariants:
     when the array is full:
     nextFirst + 1 = nextLast;
     the index of last item: nextFirst;
     the index of first item: nextLast;
     nextFirst >= 1 and nextLast <= size - 2:
     1. index of the first item is nextFirst + 1;
     2. the item will be added in the front is in the position of nextFirst;
     3. index of the last item is nextLast - 1;
     4. the item will be added in the last is in the position of nextLast;
     */

    /** Resizes the underlying array to the target capacity. */
    public void resize (int size) {
        int factor = 4;
        length = size * factor;
        T[] a = (T[]) new Object[length];
        if(nextFirst == 0 && nextLast == 1) {
            System.arraycopy(array, 1, a, 0, size - 1);
            a[size - 1] = array[0];
        }
        else if (nextLast == (size - 1) && nextFirst == (size - 2)) {
            a[0]= array[nextLast];
            System.arraycopy(array, 0, a, 1, size - 1);
        }
        else if (nextFirst + 1 == nextLast) {
            int number = size - nextLast;
            System.arraycopy(array, nextLast, a, 0, number);
            System.arraycopy(array, 0, a, number, nextLast);
        }
        else {
            int number = size - nextLast;
            System.arraycopy(array, nextFirst + 1, a, 0, number);
            System.arraycopy(array, 0, a, number, nextLast);
        }
        array = a;
    }

    /** adds an item to the front of the deque */
    public void addFirst(T item) {
        size += 1;
        array[nextFirst] = item;
        if(nextFirst > 0) {
            nextFirst -= 1;
        }
        if(nextFirst == 0) {
            nextFirst = length - 1;
        }
    }

    /** adds an item to the back of the deque */
    public void addLast(T item) {
        size += 1;
        array[nextLast] = item;
        if (nextLast == length - 1) {
            nextLast = 0;
        }
        if (nextLast < length - 1) {
            nextLast += 1;
        }
    }

    /** returns true if deque is empty */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** return the number if items in the deque */
    public int size(){
        return size;
    }

    /** prints the items in the deque from first to last. */
    public void printDeque() {
        for (int index = 0; index < size; index += 1){
            System.out.print(array[index] + "");
        }
        System.out.println(" ");
    }

    /** removes and returns the item at the front of the deque
     if no such item exists, return null
     */
    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        size -= 1;
        if(nextFirst == length - 1) {
            T first = array[0];
            array[0] = null;
            nextFirst = 0;
            return first;
        }
        else {
            T first = array[nextFirst + 1];
            array[nextFirst + 1] = null;
            nextFirst += 1;
            return first;
        }
    }

    /** removes and returns the item at the back of the deque
     if no such item exists, return null
     */
    public T removeLast() {
        if(size == 0) {
            return null;
        }
        if(nextLast == 0) {
            T last = array[length - 1];
            array[length - 1] = null;
            nextFirst = length - 1;
            return last;
        }
        else {
            T last= array[nextLast - 1];
            array[nextLast - 1] = null;
            nextLast -= 1;
            return last;
        }
    }

    /** gets the item ar the given index */
    public T get(int index) {
        return array[index];
    }


}
