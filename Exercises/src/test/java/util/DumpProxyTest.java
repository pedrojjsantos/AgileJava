package util;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class DumpProxyTest {
    private static final String dumpMethodName = "toString";
    private static final String objectMethodName = "method";
    private Object object;
    private DumpProxy proxy;
    private boolean dumpMethodCalled;
    private boolean objectMethodCalled;

    @Before
    public void setUp() {
        object = new Object() {
            public String toString() {
                dumpMethodCalled = true;
                return "";
            }
            public void method() {
                objectMethodCalled = true;
            }

            private int num = 101;
            public String name = "Foo";
        };
        proxy = new DumpProxy(object, dumpMethodName, objectMethodName);
    }

    @Test
    public void testObjectMethod() throws Throwable {
        Method objectMethod =
                object.getClass().getDeclaredMethod(
                        objectMethodName);

        proxy.invoke(proxy, objectMethod, new Object[]{});
        assertTrue(objectMethodCalled);
    }

    @Test
    public void testDumpMethod() throws Throwable {
        Method dumpMethod =
            object.getClass().getDeclaredMethod(
                    dumpMethodName);
        String str = (String) proxy.invoke(proxy, dumpMethod, new Object[]{});
        assertTrue(dumpMethodCalled);
        assertEquals(ObjectDumper.print(object), str);
    }
}
