package com.sevenfive.assignment4.helper;
import com.sun.istack.internal.Nullable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SessionInvalidator {

    private String token;
    private long time_seconds_expiry = 1000L;
    private long lastTimeStamp;
    private final Action actionOnExpiry;

    public SessionInvalidator(String token, Action actionOnExpiry, @Nullable Long time_seconds_expiry) {
        this.token = token;
        this.time_seconds_expiry = time_seconds_expiry!=null?time_seconds_expiry:this.time_seconds_expiry;
        this.actionOnExpiry = actionOnExpiry;
        lastTimeStamp = System.currentTimeMillis();
        //kick-off
        start();

    }

    //update timestamp
    public void ping(){
        lastTimeStamp = System.currentTimeMillis();
    }

    private synchronized void start(){

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(()->{
            if(System.currentTimeMillis() - lastTimeStamp > time_seconds_expiry){
                //Expired
                actionOnExpiry.executeAction(null);
                ses.shutdownNow();
            }
        },time_seconds_expiry,time_seconds_expiry, TimeUnit.MILLISECONDS);

    }


}
