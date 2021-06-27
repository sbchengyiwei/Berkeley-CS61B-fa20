import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
        AListFloorSet a = new AListFloorSet();
        RedBlackFloorSet b = new RedBlackFloorSet();
        for(int i = 0; i < 1000000; i++){
            double t = StdRandom.uniform(-5000.0,5000.0);
            a.add(t);
            b.add(t);

         }
        for(int i = 0; i < 100000; i++){
            double d = StdRandom.uniform(-5000.0,5000.0);
            assertEquals(a.floor(d), b.floor(d), 0.000001);
        }


       }

    }

