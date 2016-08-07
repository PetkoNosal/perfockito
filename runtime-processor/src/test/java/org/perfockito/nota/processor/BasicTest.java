package org.perfockito.nota.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import org.junit.Test;
import org.perfockito.nota.API.PerfockitoEngine;
import org.perfockito.nota.API.PerfockitoEngine.ProcessorType;
import org.perfockito.nota.API.PerfockitoProcessor;
import org.perfockito.nota.annotation.PerfockitoMarker;

public class BasicTest {

    @Test
    public void runTest() throws Exception {
        PerfockitoProcessor processor =
                PerfockitoEngine.getGlobalProcessor(ProcessorType.RUNTIME_PROCESSOR)
                        .registerInstance(this.getClass(), this);
        processor.run().print();
    }

    @Test
    public void engineTest() throws Exception {
        assertNotNull(PerfockitoEngine.getGlobalProcessor(ProcessorType.RUNTIME_PROCESSOR));
        assertNotNull(PerfockitoEngine.getLocalProcessor(ProcessorType.RUNTIME_PROCESSOR));
        assertEquals(PerfockitoEngine.getGlobalProcessor(ProcessorType.RUNTIME_PROCESSOR),
                PerfockitoEngine.getGlobalProcessor(ProcessorType.RUNTIME_PROCESSOR));
        assertNotEquals(PerfockitoEngine.getGlobalProcessor(ProcessorType.RUNTIME_PROCESSOR),
                PerfockitoEngine.getLocalProcessor(ProcessorType.RUNTIME_PROCESSOR));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void processorsHolderConstructTest() throws Throwable {
        Constructor constructor = ProcessorsHolder.class.getDeclaredConstructor();
        assertFalse(constructor.isAccessible());
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
            fail("ProcessorHolder Class should have throw an exception at constructor invocation");
        } catch (Exception e) {
            throw e.getCause();
        }
    }

    @PerfockitoMarker(name = "asd", repetitions = 1000)
    private void testMethod() {
        //SOME CALCULATION HERE
        //System.out.println("tstInvoke");
    }
}