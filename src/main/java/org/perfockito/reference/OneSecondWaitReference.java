package org.perfockito.reference;

import java.util.concurrent.TimeUnit;

public final class OneSecondWaitReference {

    public synchronized void reference(){
        try {
            wait(TimeUnit.SECONDS.toMillis(1));
        }catch (Exception e){
            System.err.println(e);
        }
    }
}
