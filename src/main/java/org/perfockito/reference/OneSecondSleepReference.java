package org.perfockito.reference;

import java.util.concurrent.TimeUnit;

public final class OneSecondSleepReference {

    public static void reference() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }catch (Exception e){
            System.err.println(e);
        }
    }
}
