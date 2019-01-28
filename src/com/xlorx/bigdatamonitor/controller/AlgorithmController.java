package com.xlorx.bigdatamonitor.controller;

import com.xlorx.bigdatamonitor.model.Algorithm.RuntimeMonitor;
import com.xlorx.bigdatamonitor.model.CPU.CPUHigh;
import com.xlorx.bigdatamonitor.model.CPU.CPULow;
import com.xlorx.bigdatamonitor.model.CPU.CPUMedium;
import com.xlorx.bigdatamonitor.model.Data.HeavyThread;
import com.xlorx.bigdatamonitor.model.Data.ReadCSV;
import com.xlorx.bigdatamonitor.model.RAM.RAMHigh;
import com.xlorx.bigdatamonitor.model.RAM.RAMLow;
import com.xlorx.bigdatamonitor.model.RAM.RAMMedium;
import com.xlorx.bigdatamonitor.view.View;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.util.ArrayList;

/**Ø
 * Created by User on 12/11/2014.
 */
public class AlgorithmController {

    private static final int CPUmhz = 3500;
    private static final int CPUlogicalcores = 12;
    private static final long MaxJVMmemory = 7266;
    private long CPUInfoTotal;
    private RuntimeMonitor runtimeMonitor;
    private View view;
    private ReadCSV readCSV;
    private HeavyThread heavyThread;

    public AlgorithmController(View view) {
        this.view = view;
    }

    public void beginAlgorithmController(ReadCSV readCSV) {
        this.readCSV = readCSV;

        Sigar sigar = new Sigar();

        try {
            Runtime runtime = Runtime.getRuntime();
            CpuInfo[] cpuInfo = sigar.getCpuInfoList();
            CpuInfo info = cpuInfo[0];
            int mhz = info.getMhz() / CPUmhz;
            int CPUcorespersocket = info.getTotalCores() / CPUlogicalcores;
            long maxJVMRAM = runtime.maxMemory() / 1024 / 1024 / MaxJVMmemory;

            CPUInfoTotal = mhz + CPUcorespersocket;

            // Create Runtime Monitor

            runtimeMonitor = new RuntimeMonitor(readCSV);

            // Correct instruction Files for CPU

            if (CPUInfoTotal <= .65) {
                CPULow cpuLow = new CPULow();
                runtimeMonitor.setCpu(cpuLow);
            } else if (CPUInfoTotal <= 1.3) {
                CPUMedium cpuMedium = new CPUMedium();
                runtimeMonitor.setCpu(cpuMedium);
            } else if (CPUInfoTotal <= 2) {
                CPUHigh cpuHigh = new CPUHigh();
                runtimeMonitor.setCpu(cpuHigh);
            } else {
                CPUHigh cpuHigh = new CPUHigh();
                runtimeMonitor.setCpu(cpuHigh);
            }

            // Correct instruction Files for RAM

            if (maxJVMRAM <= 2422) {
                RAMLow ramLow = new RAMLow();
                runtimeMonitor.setRam(ramLow);
            } else if (maxJVMRAM <= 4844) {
                RAMMedium ramMedium = new RAMMedium();
                runtimeMonitor.setRam(ramMedium);
            } else if (maxJVMRAM <= 7266) {
                RAMHigh ramHigh = new RAMHigh();
                runtimeMonitor.setRam(ramHigh);
            } else {
                RAMHigh ramHigh = new RAMHigh();
                runtimeMonitor.setRam(ramHigh);
            }

            runtimeMonitor.beginThreadPoolOptimisation();

        } catch (SigarException e) {
            e.printStackTrace();
        }
    }

    public void beginAlgorithmControllerHeavy(HeavyThread heavyThread) {

        this.heavyThread = heavyThread;

        Sigar sigar = new Sigar();

        try {
            Runtime runtime = Runtime.getRuntime();
            CpuInfo[] cpuInfo = sigar.getCpuInfoList();
            CpuInfo info = cpuInfo[0];
            int mhz = info.getMhz() / CPUmhz;
            int CPUcorespersocket = info.getTotalCores() / CPUlogicalcores;
            long maxJVMRAM = runtime.maxMemory() / 1024 / 1024 / MaxJVMmemory;

            CPUInfoTotal = mhz + CPUcorespersocket;

            // Create Runtime Monitor

            runtimeMonitor = new RuntimeMonitor(heavyThread);

            // Correct instruction Files for CPU

            if (CPUInfoTotal <= .65) {
                CPULow cpuLow = new CPULow();
                runtimeMonitor.setCpu(cpuLow);
            } else if (CPUInfoTotal <= 1.3) {
                CPUMedium cpuMedium = new CPUMedium();
                runtimeMonitor.setCpu(cpuMedium);
            } else if (CPUInfoTotal <= 2) {
                CPUHigh cpuHigh = new CPUHigh();
                runtimeMonitor.setCpu(cpuHigh);
            } else {
                CPUHigh cpuHigh = new CPUHigh();
                runtimeMonitor.setCpu(cpuHigh);
            }

            // Correct instruction Files for RAM

            if (maxJVMRAM <= 2422) {
                RAMLow ramLow = new RAMLow();
                runtimeMonitor.setRam(ramLow);
            } else if (maxJVMRAM <= 4844) {
                RAMMedium ramMedium = new RAMMedium();
                runtimeMonitor.setRam(ramMedium);
            } else if (maxJVMRAM <= 7266) {
                RAMHigh ramHigh = new RAMHigh();
                runtimeMonitor.setRam(ramHigh);
            } else {
                RAMHigh ramHigh = new RAMHigh();
                runtimeMonitor.setRam(ramHigh);
            }

            runtimeMonitor.beginThrottlingCPU();

        } catch (SigarException e) {
            e.printStackTrace();
        }
    }


    public RuntimeMonitor getRuntimeMonitor() {
        return runtimeMonitor;
    }
}

