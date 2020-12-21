import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void randomTest1() {
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> stu= new StudentArrayDeque<>();
        String msg = "\n";

        for (int i = 0; i < 10; i++) {
            Integer randomNum = StdRandom.uniform(100);
            sol.addFirst(randomNum);
            stu.addFirst(randomNum);
            msg = msg + "addFirst(" + randomNum + ")\n";
        }

        for (int i = 0; i < 10; i++) {
            msg = msg + "removeFirst()\n";
            assertEquals(msg, sol.removeFirst(), stu.removeFirst());
            msg = msg + "removeLast()\n";
            assertEquals(msg, sol.removeLast(), stu.removeLast());
        }
    }
}