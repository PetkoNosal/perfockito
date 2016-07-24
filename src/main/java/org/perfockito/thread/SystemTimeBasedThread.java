package org.perfockito.thread;

class SystemTimeBasedThread extends AbstractThread {

    @Override
    public void run() {
        time = System.nanoTime();
        try {
            methodForTest.invoke(objectOn, args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            time = System.nanoTime() - time;
        }
    }
}
