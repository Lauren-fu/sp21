package deque;



public class LinkedListDeque<T> {

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
    private Node sentinal;

    /**
      creating an empty LinkedListDeque
     */
    public LinkedListDeque() {
        size = 0;
        sentinal = new Node(null, null, null);
        sentinal.prev = sentinal;
        sentinal.next = sentinal;
    }

    /** creates a deep copy of other */
    public LinkedListDeque(LinkedListDeque other) {
        size = 0;
        sentinal = new Node(null, null, null);
        sentinal.prev = sentinal;
        sentinal.next = sentinal;
        for(int i = 0; i < other.size(); i += 1) {
            addLast((T)other.get(i));
        }
    }

    /*Invariants:
        1. the first item is sentinal.next
        2. the last item is sentinal.prev
        3. the second to last is sentinal.prev.prev

     */

    /**
      adds an item to the front of the deque
     */
    public void addFirst(T x) {
        size += 1;
        Node node = new Node(x, sentinal.next, sentinal);
        sentinal.next.prev = node;
        sentinal.next = node;
        node = null;
    }

    /**
     * adds an item to the last position of the deque
     */
    public void addLast(T x) {
        size += 1;
        Node node = new Node(x, sentinal, sentinal.prev);
        sentinal.prev.next = node;
        sentinal.prev = node;
        node = null;
    }

    /**
      if deque is empty
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
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
        for (Node p = sentinal.next; p.item != null; p = p.next) {
            System.out.print(p.item + " ");
        }
        System.out.println("");
    }


    /** Removes and return the item at the front of the deque
        if no such item exists, return null
     */

    public T removeFirst () {
        if(isEmpty() == false) {
            Node firstnode = sentinal.next;
            T first = sentinal.next.item;
            size -= 1;
            sentinal.next.next.prev = sentinal;
            sentinal.next = sentinal.next.next;
            firstnode = null;
            return first;
        }
        return null;
    }

    /** Removes and return the item at the back of the deque
     if no such item exists, return null
     */

    public T removeLast() {
        if(isEmpty() == false) {
            Node lastnode = sentinal.prev;
            T last = sentinal.prev.item;
            size -= 1;
            sentinal.prev.prev.next = sentinal;
            sentinal.prev = sentinal.prev.prev;
            lastnode = null;
            return last;
        }
        return null;
    }

    /** gets the item at the given index, where 0 is the front, 1 is the next item
        if no such item exists, return null
     */
    public T get(int index) {
        Node p = sentinal;
        if (index >= size || size == 0) {
            return null;
        }
        while (index >= 0) {
            index -= 1;
            p = p.next;
        }
        return p.item;
    }

    public T getRecursivehelper (Node sentinal, int index) {
        if (index >= size || size == 0) {
            return null;
        }
        if (index == 0) {
            return sentinal.next.item;
        }
        return getRecursivehelper(sentinal.next, index - 1);
    }

    public T getRecursive (int index) {
        return getRecursivehelper(sentinal, index);
    }




}






