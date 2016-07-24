package org.perfockito.engine;

import com.sun.istack.internal.NotNull;
import org.perfockito.api.Perfockito;
import org.perfockito.api.PerfockitoReport;
import org.perfockito.api.PerfockitoThreadPool;
import org.perfockito.reference.OneSecondSleepReference;
import org.perfockito.report.PerfockitoReportFactory;
import org.perfockito.threadpool.PerfockitoThreadPoolFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class PerfockitoEngine implements Perfockito {

    private static final int COUNTER = new AtomicInteger().incrementAndGet();
    private final PerfockitoReport report = PerfockitoReportFactory.create();
    private final Map<String, List<Double>> reportData = new HashMap<>();
    private final Map<String, PerfockitoThreadPool> mainPool;
    private final String name;

    private int repetitions;
    private int cycle;

    PerfockitoEngine(String name) {
        this.name = (name == null ? "Unnamed Test" : name) + " #" + COUNTER;
        this.mainPool = new HashMap<>();
    }

    @Override
    public void init(int repetitions, int cycle) {
        this.repetitions = repetitions;
        this.cycle = cycle;
        report.setHeader(name, repetitions, cycle);
    }

    @Override
    public void runTest() {
        int counter = 0;
        addReferenceMethodToPool();
        while(counter < repetitions){
            counter++;
            mainPool.forEach((name, pool) -> {
                pool.startThreads();
                while (!pool.areAllThreadsTerminated()){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        System.err.println(e);
                        return;
                    }
                }
                if(!name.equals("OneSecReference")) {
                    report.appendToBody(name, pool.getAvgUserTime());
                }
                reportData.get(name).add(pool.getAvgUserTime());
            });
        }
    }

    @Override
    public void addInstanceMethodToTest(@NotNull String methodName, Object objectOn, Object... methodArgs) {
        final PerfockitoThreadPool pool = PerfockitoThreadPoolFactory.create(cycle);
        final Method method;
        try {
            method = objectOn.getClass().getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            System.err.println(e);
            return;
        }
        pool.initThreadPool(method, objectOn, methodArgs);
        mainPool.put(methodName, pool);
        reportData.put(methodName, new ArrayList<>());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addStaticMethodToTest(@NotNull String methodName, Class clazz, Object... methodArgs) {
        final PerfockitoThreadPool pool = PerfockitoThreadPoolFactory.create(cycle);
        final Method method;
        try {
            method = clazz.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            System.err.println(e);
            return;
        }
        pool.initThreadPool(method, null, methodArgs);
        mainPool.put(methodName, pool);
        reportData.put(methodName, new ArrayList<>());
    }

    private void addReferenceMethodToPool() {
        final PerfockitoThreadPool pool = PerfockitoThreadPoolFactory.create(5);
        final Method method;
        try {
            method = OneSecondSleepReference.class.getDeclaredMethod("reference");
        } catch (NoSuchMethodException e) {
            System.err.println(e);
            return;
        }
        pool.initThreadPool(method, null, null);
        mainPool.put("OneSecReference", pool);
        reportData.put("OneSecReference", new ArrayList<>());
    }

    @Override
    public PerfockitoReport getReport() {
        reportData.forEach((name, data) -> {
            final double[] time = {0};
            data.forEach(t -> {
                time[0] += t;
//                report.appendToBody(name, t);
            });

            if(!name.equals("OneSecReference")) {
                report.addMethodToReport(name, (time[0] / (double) data.size()));
            } else {
                report.addReferenceInfo(reportData.get("OneSecReference").size(),
                        (time[0] / (double)reportData.get("OneSecReference").size()));
            }
        });

        return report;
    }
}
