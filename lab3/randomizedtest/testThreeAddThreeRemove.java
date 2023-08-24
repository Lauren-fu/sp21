package randomizedtest;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;

public class testThreeAddThreeRemove {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> lst1 = new AListNoResizing<>();
        BuggyAList<Integer> lst2 = new BuggyAList<>();

        lst1.addLast(5);
        lst1.addLast(10);
        lst1.addLast(15);

        lst2.addLast(5);
        lst2.addLast(10);
        lst2.addLast(15);

        assertEquals(lst1.size(), lst2.size());


        assertEquals(lst1.removeLast(), lst2.removeLast());
        assertEquals(lst1.removeLast(), lst2.removeLast());
        assertEquals(lst1.removeLast(), lst2.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L1 = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L1.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size1 = L1.size();
                assertEquals(size, size1);
            } else if (operationNumber == 2 && L.size() != 0) {
                //getLast
                int last = L.getLast();
                int last1 = L1.getLast();
                assertEquals(last, last1);
            } else if (operationNumber == 3 && L.size() != 0) {
                //removeLast
                int last = L.removeLast();
                int last1 = L1.removeLast();
                assertEquals(last, last1);
            }
        }
    }

}
