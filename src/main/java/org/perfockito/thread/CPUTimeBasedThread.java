package org.perfockito.thread;

import static java.lang.management.ManagementFactory.getThreadMXBean;

final class CPUTimeBasedThread extends AbstractThread {

    @Override
    public void run() {
        try {
            methodForTest.invoke(objectOn, args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            time = getThreadMXBean().getCurrentThreadUserTime();
        }
    }
}
