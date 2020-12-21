package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer(6);
        arb.enqueue(25.1);
        arb.enqueue(23.2);
        arb.enqueue(63.2);
        arb.enqueue(23.2);
        arb.enqueue(72.0);
        arb.enqueue(10.6);
        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer(6);
        arb2.enqueue(3);
        arb2.enqueue(4);
        arb2.enqueue(5);
        arb2.enqueue(6);
        arb2.enqueue(7);
        arb2.enqueue(9);
        assertFalse(arb.equals(arb2));

    }
}
