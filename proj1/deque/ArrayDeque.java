
package deque;
import java.util.Iterator;

import static org.junit.Assert.assertTrue;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
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
    public void resize (int l) {
        int lgh = l;
        T[] a = (T[]) new Object[lgh];
        if (nextFirst == length - 1)  {
            System.arraycopy(array, 0, a, 0, size);
        }
        else if (size == length) {
            int number = size - nextFirst - 1;
            System.arraycopy(array, nextFirst + 1, a, 0, number);
            System.arraycopy(array, 0, a, number,nextFirst + 1);
        }
        else if (nextLast <= nextFirst && size != length) {
            int number = size - nextLast;
            System.arraycopy(array, nextFirst + 1, a, 0, number);
            System.arraycopy(array, 0, a, number, nextLast);
        }
        else {
            System.arraycopy(array, nextFirst + 1, a,0, size);
        }
        length = l;
        nextFirst = l - 1;
        nextLast = size;
        array = a;
    }

    /** adds an item to the front of the deque */
    public void addFirst(T item) {
        if(size == length) {
            resize(size * 2);
        }
        size += 1;
        array[nextFirst] = item;
        if(nextFirst > 0) {
            nextFirst -= 1;
        }
        else {
            nextFirst = length - 1;
        }
    }

    /** adds an item to the back of the deque */
    public void addLast(T item) {
        if(size == length) {
            resize(size * 2);
        }
        size += 1;
        array[nextLast] = item;
        if (nextLast == length - 1) {
            nextLast = 0;
        }
        else {
            nextLast += 1;
        }
    }

    /** returns true if deque is empty */

    /** return the number if items in the deque */
    public int size(){
        return size;
    }

    /** prints the items in the deque from first to last. */
    public void printDeque() {
        for (int index = 0; index < size; index += 1){
            System.out.print(get(index) + " ");
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
        T first;
        if (nextFirst == length - 1) {
           first = array[0];
            array[0] = null;
            nextFirst = 0;
        } else {
            first = array[nextFirst + 1];
            array[nextFirst + 1] = null;
            nextFirst += 1;
        }
        if(size > 8) {
            resize(size * 2);
        }
        return first;
    }

    /** removes and returns the item at the back of the deque
     if no such item exists, return null
     */
    public T removeLast() {
        if(size == 0) {
            return null;
        }
        size -= 1;
        T last;
        if (nextLast == 0) {
           last = array[length - 1];
            array[length - 1] = null;
            nextFirst = length - 1;
        }
        else {
            last= array[nextLast - 1];
            array[nextLast - 1] = null;
            nextLast -= 1;
        }
        if (size > 8) {
            resize(size * 2);
        }
        return last;
    }

    /** gets the item ar the given index */
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        else if(nextFirst == length - 1) {
            return array[index];
        }
        else if (nextFirst + 1 + index <= length - 1) {
            return array[nextFirst + 1 + index];
        }
        else {
            return array[index - length + 1 + nextFirst];
        }
    }

    /* return an iterator
     */
    public Iterator<T> iterator() {
        return new itemIterator();
    }

    private class itemIterator implements Iterator<T> {
        public int position;

        public itemIterator() {
            position = 0;
        }

        public boolean hasNext() {
            return position < size;
        }

        public T next() {
            T value = get(position);
            position += 1;
            return value;
        }
    }

    /* returns whether the parameter o is equal to the deque
     */
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(o == null) {
            return false;
        }
        if(o.getClass() != this.getClass()){
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if(other.size() != size) {
            return false;
        }
        for(int i = 0; i < size; i ++) {
            if(!other.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> lld = new ArrayDeque<>();
        System.out.println(lld.isEmpty());
        lld.addFirst(1);
        lld.addFirst(5);
        lld.addFirst(11);
        lld.addFirst(12);
        lld.addLast(13);
        lld.addFirst(104);
        lld.addFirst(105);
        lld.addFirst(106);
        lld.addFirst(100);
        System.out.println(lld.isEmpty());
        lld.printDeque();
        System.out.println(lld.size());
        System.out.println(lld.get(2));
        System.out.println(lld.get(5));
        System.out.println(lld.get(10));
        lld.removeLast();
        lld.removeFirst();
        lld.printDeque();

        for(int i : lld) {
            System.out.println(i);
        }
    }
}
