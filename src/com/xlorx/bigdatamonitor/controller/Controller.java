package com.xlorx.bigdatamonitor.controller;

import com.xlorx.bigdatamonitor.model.Data.HeavyThread;
import com.xlorx.bigdatamonitor.model.Data.ReadCSV;
import com.xlorx.bigdatamonitor.view.View;
import com.xlorx.bigdatamonitor.view.ViewPanels.ControlPanel;
import org.hyperic.sigar.*;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by User on 01/10/2014.
 */

public class Controller {

    private View view;
    private ControlPanel controlPanel;
    private boolean guiLoop;
    private AlgorithmController algorithmController;
    private boolean algorithmEnabled;
    private boolean under80Enabled;

    public Controller(final View view, final AlgorithmController algorithmController) {

        this.algorithmController = algorithmController;
        this.view = view;

        controlPanel = new ControlPanel();

        view.getControlPanel().setMonitorListener(new MonitorListener() {
            @Override
            public void monitorPerformed() {
                view.getControlPanel().setConsole("Monitoring Started.");

                try {
                    // Setting where the libs are - different for Jar
                    String directory = System.getProperty("user.dir");
                    System.setProperty("java.library.path", "C:\\Users\\User\\Documents\\Programming\\Java\\Exports\\hyperic-sigar-1.6.4\\sigar-bin\\lib");
                    Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
                    fieldSysPath.setAccessible(true);
                    fieldSysPath.set(null, null);
                    System.loadLibrary("sigar-amd64-winnt");
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (NoSuchFieldException e1) {
                    e1.printStackTrace();
                }

                SwingWorker<Integer, String> monitor = new SwingWorker<Integer, String>() {
                    @Override
                    protected Integer doInBackground() throws Exception {
                        Sigar sigar = new Sigar();
                        guiLoop = true;
                        while (guiLoop) {
                            try {
                                if (!guiLoop) {
                                    break;
                                }
                                Mem memory = sigar.getMem();
                                final String MemPercentage = (memory.getUsedPercent() + "%");

                                CpuPerc cpuPercentage = sigar.getCpuPerc();
                                final String CPUUsage = (cpuPercentage.getCombined() * 100 + "%");

                                long pid = sigar.getPid();

                                Runtime runtime = Runtime.getRuntime();
                                long runtimeMemory = runtime.totalMemory() - runtime.freeMemory();
                                runtimeMemory = runtimeMemory / 1024 / 1024;
                                final String returnmemory = (runtimeMemory + " mb");

                                ProcCpu cpuProcess = new ProcCpu();
                                cpuProcess.gather(sigar, pid);
                                final String cpuProcessgui = (cpuProcess.getPercent() + "%");

                                process("\n" + MemPercentage + "\n", "\n" + CPUUsage + "\n", returnmemory, cpuProcessgui);

                                Thread.sleep(2000);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (SigarException e) {
                                e.printStackTrace();
                            }
                        }
                        controlPanel.setConsole("Stopped All");
                        view.getMonitoring().clear();
                        return null;
                    }
                    protected void process(String chunks1, String chunks2, String chunks3, String chunks4) {
                        view.getMonitoring().setMemoryUsage(chunks1);
                        view.getMonitoring().setCPUUsage(chunks2);
                        view.getMonitoring().setRAMProcessusage(chunks3);
                        view.getMonitoring().setCPUProcessUsage(chunks4);
                    }
                };
                monitor.execute();
            }
        });

        view.getControlPanel().setStopAll(new StopAllListener() {
            @Override
            public void stopAllPerformed() {
                guiLoop = false;
                under80Enabled = view.getAlgorithm().isSelectedPressure();
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        @Override
                        public void run() {
                            view.getControlPanel().setConsole("Stopped All");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });

        view.getControlPanel().setBeginAnalysis(new BeginAnalysisListener() {
            @Override
            public void beginAnalysisPerformed() {
                algorithmEnabled = view.getAlgorithm().isSelected();
                under80Enabled = view.getAlgorithm().isSelectedPressure();
                if (algorithmEnabled) {
                    if (under80Enabled) {
                        SwingWorker<Integer, String> under80Enable = new SwingWorker<Integer, String>() {
                            @Override
                            protected Integer doInBackground() throws Exception {
                                Long startAdding = System.nanoTime();
                                ReadCSV readCSV = new ReadCSV();
                                algorithmController.beginAlgorithmController(readCSV);
                                final Integer threadPoolSize = readCSV.getThreadPool();
                                process("Starting Algorithm");
                                process("Beginning to add data to memory.");
                                process("Threads: " + threadPoolSize.toString());
                                readCSV.start();
                                try {
                                    readCSV.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Long stopAdding = System.nanoTime() - startAdding;
                                stopAdding = stopAdding / 1000000000;
                                process("Adding Data Completed");
                                process("Time Taken: " + stopAdding.toString());
                                return null;
                            }
                            protected void process(String chunks) {
                                view.getControlPanel().setConsole(chunks);
                            }
                        };
                        under80Enable.execute();
                    } else {
                        SwingWorker<Integer, String> over80Enable = new SwingWorker<Integer, String>() {
                            @Override
                            protected Integer doInBackground() throws Exception {
                                HeavyThread heavyThread = new HeavyThread();
                                algorithmController.beginAlgorithmControllerHeavy(heavyThread);
                                process("Starting Algorithm");
                                process("Starting Heavy Threads.");
                                process("Threads Limited to: " + heavyThread.getHeavyWorkerInteger());
                                heavyThread.start();
                                return null;
                            }
                            protected void process(String chunks) {
                                view.getControlPanel().setConsole(chunks);
                            }
                        };
                        over80Enable.execute();
                    }
                } else {
                    if (under80Enabled) {
                        SwingWorker<Integer, String> under80Enable = new SwingWorker<Integer, String>() {
                            @Override
                            protected Integer doInBackground() throws Exception {
                                Long startAdding = System.nanoTime();
                                ReadCSV readCSV = new ReadCSV();
                                process("Beginning to add data to memory.");
                                readCSV.start();
                                try {
                                    readCSV.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Long stopAdding = System.nanoTime() - startAdding;
                                stopAdding = stopAdding / 1000000000;
                                process("Adding Data Completed");
                                process("Time Taken: " + stopAdding.toString());
                                return null;
                            }


                            protected void process(String chunks) {
                                view.getControlPanel().setConsole(chunks);
                            }
                        };
                        under80Enable.execute();
                    } else {
                        SwingWorker<Integer, String> over80Enable = new SwingWorker<Integer, String>() {
                            @Override
                            protected Integer doInBackground() throws Exception {
                                HeavyThread heavyThread = new HeavyThread();
                                process("Starting Heavy Threads.");
                                heavyThread.start();
                                return null;
                            }

                            protected void process(String chunks) {
                                view.getControlPanel().setConsole(chunks);
                            }
                        };
                        over80Enable.execute();
                    }
                }
            }
        });
    }
}

