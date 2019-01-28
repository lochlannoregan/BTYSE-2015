package com.xlorx.bigdatamonitor.model.Algorithm;

import com.xlorx.bigdatamonitor.model.CPU.CPU;
import com.xlorx.bigdatamonitor.model.Data.HeavyThread;
import com.xlorx.bigdatamonitor.model.Data.ReadCSV;
import com.xlorx.bigdatamonitor.model.RAM.RAM;

/**
 * Created by User on 18/11/2014.
 */
public class RuntimeMonitor {

    private CPU cpu;
    private RAM ram;
    private Double setThreadPoolDouble;
    private Integer setThreadPoolInteger;
    private ReadCSV readCSV;
    private HeavyThread heavyThread;

    public RuntimeMonitor(ReadCSV readCSV){
        this.readCSV = readCSV;
    }

    public RuntimeMonitor(HeavyThread heavyThread){
        this.heavyThread = heavyThread;
    }

    public void beginThreadPoolOptimisation() {
        Integer getThreadPoolInteger = readCSV.getThreadPool();
        double passInDouble = (double) getThreadPoolInteger;
        NewtonThreadPool newtonThreadPool = new NewtonThreadPool(passInDouble);
        do{
            setThreadPoolDouble = newtonThreadPool.findThreadOptimum();
        } while(newtonThreadPool.hasMoreGuesses());
        setThreadPoolInteger = (int) Math.round(setThreadPoolDouble);
        readCSV.setThreadPool(setThreadPoolInteger);
    }

    public void beginThrottlingCPU(){
        Integer getThreadPoolCPU = heavyThread.getHeavyWorkerInteger();
        heavyThread.setHeavyWorkerInteger(2);
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

}
