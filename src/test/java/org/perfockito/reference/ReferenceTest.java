package org.perfockito.reference;

import org.junit.Test;
import org.perfockito.api.Perfockito;
import org.perfockito.engine.PerfockitoEngineFactory;

public class ReferenceTest {

    @Test
    public void instanceReferenceTest() throws Exception {
        long time = System.nanoTime();
        testMethod1();
        System.out.println("Method1 time: " + ((double)(System.nanoTime() - time) / 1000000000.0));

        time = System.nanoTime();
        testMethod2();
        System.out.println("Method2 time: " + ((double)(System.nanoTime() - time) / 1000000000.0));

        Perfockito perfockito = PerfockitoEngineFactory.create("test");
        perfockito.init(10, 5);
        OneSecondWaitReference ref = new OneSecondWaitReference();
        perfockito.addStaticMethodToTest("testMethod1", this.getClass());
        perfockito.addStaticMethodToTest("testMethod2", this.getClass());
        perfockito.runTest();
        perfockito.getReport().print();
    }



    private static void testMethod1() {
        for (int i = 0; i < 100; i++) {
            Object o = new Object().equals(new Object());
        }
    }

    private static void testMethod2() {
        Object o1= new Object();
        Object o2 = new Object();
        for (int i = 0; i < 100; i++) {
            o1.equals(o2);
        }
    }
}