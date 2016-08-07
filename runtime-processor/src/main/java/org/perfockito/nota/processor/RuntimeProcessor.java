package org.perfockito.nota.processor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.perfockito.nota.API.PerfockitoProcessor;
import org.perfockito.nota.annotation.PerfockitoMarker;
import org.perfockito.nota.report.API.PerfockitoReport;
import org.perfockito.nota.report.model.BaseReport;
import org.perfockito.nota.report.model.Group;
import org.perfockito.nota.report.model.SimpleReport;

class RuntimeProcessor implements PerfockitoProcessor{

    private static final Logger LOGGER = Logger.getLogger(RuntimeProcessor.class.getName());
    private final static Object EMPTY_OBJECT = new Object();
    private final Map<Class, Object> runOnObjects = new HashMap<>();
    private final List<Long> runTimes = new ArrayList<>();

    @Override
    public PerfockitoProcessor registerInstance(final Class clazz, final Object instance) {
        runOnObjects.put(clazz, instance);
        return this;
    }

    @Override
    public PerfockitoProcessor registerClasses(final Class... clazzes) {
        Arrays.asList(clazzes).forEach(clazz -> registerInstance(clazz, EMPTY_OBJECT));
        return this;
    }

    @Override
    public PerfockitoReport run() {
        BaseReport report = new BaseReport();
        for(final Entry<Class, Object> obj : runOnObjects.entrySet()) {
            for(final Method method : obj.getKey().getDeclaredMethods()) {
                if(method.isAnnotationPresent(PerfockitoMarker.class)){
                    final PerfockitoMarker marker = method.getAnnotation(PerfockitoMarker.class);
                    final SimpleReport simpleReport =
                            invokeMethod(method, obj.getValue() != EMPTY_OBJECT ? obj.getValue() : null,
                                    marker.repetitions());
                    simpleReport.setRepetitions(marker.repetitions());
                    Group group = Group.getDefaultGroup();
                    if(marker.name().equals("")){
                        simpleReport.setName(obj.getKey().getSimpleName() + '.' + method.getName());
                    }else{
                        simpleReport.setName(marker.name());
                    }

                    if(!marker.group().equals("")){
                        group.setName(marker.group());
                    }
                    group.getSimpleReports().add(simpleReport);

                    if(report.containsGroup(group)){
                        report.getGroup(group.getName()).getSimpleReports().add(simpleReport);
                    }else {
                        report.getGroups().add(group);
                    }
                }
            }
        }
        return report;
    }

    private SimpleReport invokeMethod(final Method method, final Object objectOn, long repetitions) {
        long executionTime;
        final List<Long> times = new ArrayList<>();
        SimpleReport report = new SimpleReport();
        method.setAccessible(true);
        try {
            for (int repetition = 0; repetition < repetitions; repetition++) {
                executionTime = System.nanoTime();
                method.invoke(objectOn);
                executionTime = System.nanoTime() - executionTime;
                times.add(executionTime);
            }
        } catch (final Exception e) {
            LOGGER.log(Level.SEVERE, "exception at invocation stage", e);
        }

        long totalTime = 0L;
        long minTime = Long.MAX_VALUE;
        long maxTime = Long.MIN_VALUE;
        for (Long time : times) {
            totalTime += time;
            if(time > maxTime) maxTime = time;
            if(time < minTime) minTime = time;
        }

        report.setMaxTime(maxTime);
        report.setMinTime(minTime);
        report.setAverageTime(totalTime / times.size());

        return report;
    }
}
