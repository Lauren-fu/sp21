package deque;
import java.util.Iterator;



public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node (T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private int size;
    private Node sentinel;

    /**
      creating an empty LinkedListDeque
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /*Invariants:
        1. the first item is sentinel.next
        2. the last item is sentinel.prev
        3. the second to last is sentinel.prev.prev

     */

    /**
      adds an item to the front of the deque
     */
    public void addFirst(T x) {
        size += 1;
        Node node = new Node(x, sentinel.next, sentinel);
        sentinel.next.prev = node;
        sentinel.next = node;
        node = null;
    }

    /**
     * adds an item to the last position of the deque
     */
    public void addLast(T x) {
        size += 1;
        Node node = new Node(x, sentinel, sentinel.prev);
        sentinel.prev.next = node;
        sentinel.prev = node;
        node = null;
    }

    /**
      return the number of items in the deque
     */
    public int size() {
        return size;
    }

    /**
      prints the items in the deque from first to last separated by a space.
      once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        for (Node p = sentinel.next; p.item != null; p = p.next) {
            System.out.print(p.item + " ");
        }
        System.out.println("");
    }


    /** Removes and return the item at the front of the deque
        if no such item exists, return null
     */

    public T removeFirst () {
        if(isEmpty() == false) {
            Node firstNode = sentinel.next;
            T first = firstNode.item;
            size -= 1;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            firstNode = null;
            return first;
        }
        return null;
    }

    /** Removes and return the item at the back of the deque
     if no such item exists, return null
     */

    public T removeLast() {
        if(isEmpty() == false) {
            Node lastnode = sentinel.prev;
            T last = sentinel.prev.item;
            size -= 1;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            lastnode = null;
            return last;
        }
        return null;
    }

    /** gets the item at the given index, where 0 is the front, 1 is the next item
        if no such item exists, return null
     */
    public T get(int index) {
        Node p = sentinel;
        if (index >= size || size == 0) {
            return null;
        }
        while (index >= 0) {
            index -= 1;
            p = p.next;
        }
        return p.item;
    }

    private T getRecursiveHelper (Node sentinel, int index) {
        if (index >= size || size == 0) {
            return null;
        }
        if (index == 0) {
            return sentinel.next.item;
        }
        return getRecursiveHelper(sentinel.next, index - 1);
    }

    public T getRecursive (int index) {
        return getRecursiveHelper(sentinel, index);
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

    /* Returns whether the parameter o is equals to the Deque
     */
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(o == null) {
            return false;
        }
        if(! (o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (other.size() != size) {
            return false;
        }
        for(int i = 0; i < size; i += 1) {
            if(!other.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }

}






