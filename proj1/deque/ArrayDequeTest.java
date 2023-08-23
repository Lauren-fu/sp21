package deque;
import org.junit.Test;
import static org.junit.Assert.*;


public class ArrayDequeTest {


    @Test
    public void addFirstTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        assertTrue(ad.isEmpty());

        ad.addLast("a");
        ad.addLast("b");
        ad.addLast("c");
        ad.addLast("d");
        ad.addLast("e");
        ad.addLast("f");
        ad.addLast("g");
        ad.addLast("h");
        ad.addLast("i");
        ad.addLast("j");
        ad.addLast("k");
        ad.removeFirst();
        ad.removeLast();
    }
}
