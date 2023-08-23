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
        ad.addFirst("c");
        ad.addFirst("d");
        ad.addFirst("e");
        String a = ad.get(3);
    }
}
