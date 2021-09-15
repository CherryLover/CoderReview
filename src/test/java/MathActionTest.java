import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathActionTest {
    final MathAction service = DynamicProxy.getService(MathAction.class);

    @Test
    public void test_add() {
        final float result = service.add(10, 10);
        assertEquals(result, 20);
    }

    @Test
    public void test_minus() {
        final float result = service.minus(10, 10);
        assertEquals(result, 0);
    }

    @Test
    public void test_multi_times() {
        final float result = service.multiTime(10, 10);
        assertEquals(result, 100);
    }

    @Test
    public void test_division() {
        final float division = service.division(15, 3);
        assertEquals(division, 5);
    }

}
