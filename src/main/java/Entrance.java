import java.util.concurrent.TimeUnit;

public class Entrance {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));

        long startTime = System.nanoTime();

        final MathAction service = DynamicProxy.getService(MathAction.class);
        final float addResult = service.add(10, 10);
        System.out.println("add result " + addResult);

        System.out.println(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime) + "ms");
    }

}
