package com.xlorx.bigdatamonitor.model.Data;

/**
 * Created by User on 23/12/2014.
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HeavyThread extends Thread {

    private Integer HeavyWorkerInteger = 11;

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(HeavyWorkerInteger);
        for (int i = 0; i < 11; i++) {
            executorService.submit(new WorkerHeavy(324412341));
        }
    }


    public Integer getHeavyWorkerInteger() {
        return HeavyWorkerInteger;
    }

    public void setHeavyWorkerInteger(Integer heavyWorkerInteger) {
        HeavyWorkerInteger = heavyWorkerInteger;
    }

}

class WorkerHeavy implements Runnable{

    private long length;

    public WorkerHeavy(long length) {
        this.length = length;
    }

    @Override
    public void run() {
        String data = "";

        for (int i = 0; i < length; i++) {
            data += UUID.randomUUID().toString();
        }

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(data.getBytes());
    }
}