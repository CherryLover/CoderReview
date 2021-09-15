import annotation.Add;
import annotation.Division;
import annotation.Minus;
import annotation.MultiTime;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {

    @SuppressWarnings("unchecked")
    public static <T> T getService(Class cls) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String returnType = method.getGenericReturnType().getTypeName();
                final Annotation[] annotations = method.getAnnotations();
                int argsCount = (args == null ? 0 : args.length);
                System.out.println("invoke " + method.getName() +
                        " return type " + method.getGenericReturnType() +
                        " args " + argsCount +
                        " annotations " + (annotations == null ? 0 : annotations.length));
                if ("void".equals(returnType)) {
                    return null;
                } else if ("float".equals(returnType)) {
                    if (argsCount == 0) {
                        return 0;
                    }
                    return parseReturnFloat(method, args);
                }
                return null;
            }
        });
    }

    private static float parseReturnFloat(Method method, Object[] parameter) {
        final Annotation[] annotations = method.getAnnotations();
        if (annotations == null) {
            return 0;
        }
        float[] args = parseArgs(method, parameter);
        final Annotation option = annotations[0];
        float result = 0;
        if (option instanceof Add) {
            for (float arg : args) {
                result += arg;
            }
        } else if (option instanceof Minus) {
            result = args[0] - args[1];
        } else if (option instanceof MultiTime) {
            result = args[0] * args[1];
        } else if (option instanceof Division) {
            result = args[0] / args[1];
        }
        return result;
    }

    private static float[] parseArgs(Method method, Object[] parameter) {
        float[] args = new float[parameter.length];
        for (int i = 0; i < parameter.length; i++) {
            String logNumber = String.valueOf(parameter[i]);
            args[i] = Float.parseFloat(logNumber);
        }
        return args;
    }
}
