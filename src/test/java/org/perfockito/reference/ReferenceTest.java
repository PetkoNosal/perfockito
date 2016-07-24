package org.perfockito.reference;

import org.junit.Test;
import org.perfockito.api.Perfockito;
import org.perfockito.engine.PerfockitoEngineFactory;

import java.util.concurrent.TimeUnit;

public class ReferenceTest {

    @Test
    public void instanceReferenceTest() throws Exception {
        Perfockito perfockito = PerfockitoEngineFactory.create("test");
        perfockito.init(5, 10);
        OneSecondWaitReference ref = new OneSecondWaitReference();
        perfockito.addStaticMethodToTest("testMethod1", this.getClass());
        perfockito.addStaticMethodToTest("testMethod2", this.getClass());
        perfockito.addStaticMethodToTest("testMethod3", this.getClass());
        perfockito.runTest();
        perfockito.getReport().print();
    }



    private static void testMethod1() {
        try {
            Thread.sleep(TimeUnit.MILLISECONDS.toMillis(500));
        }catch (Exception e){
            System.err.println(e);
        }
    }

    private static void testMethod2() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        }catch (Exception e){
            System.err.println(e);
        }
    }

    private static void testMethod3() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        }catch (Exception e){
            System.err.println(e);
        }
    }
}