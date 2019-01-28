package com.xlorx.bigdatamonitor.model.Data;

/**
 * Created by User on 23/12/2014.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Storing {

    private String departureDelayMinutes;
    private String origin;
    private String destination;
    private String arrivalDelayMinutes;
    private String carrier;

    public Storing(String departureDelayMinutes, String origin, String destination, String arrivalDelayMinutes, String carrier) {
        this.departureDelayMinutes = departureDelayMinutes;
        this.origin = origin;
        this.destination = destination;
        this.arrivalDelayMinutes = arrivalDelayMinutes;
        this.carrier = carrier;
    }

}

public class ReadCSV extends Thread {

    private List<Storing> list = Collections.synchronizedList(new LinkedList<Storing>());
    public ArrayList<String> filePaths = new ArrayList<>();
    public int ThreadPool = 1;

    public void run() {
        File files = new File("F:\\Downloads\\Merge");
        File[] dirs = files.listFiles();

        ExecutorService executorService = Executors.newFixedThreadPool(ThreadPool);

        for (File name : dirs) {
            filePaths.add(name.toString());
        }

        for (String name : filePaths) {
            executorService.submit(new Analyse(name));
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    class Analyse implements Runnable {

        private String directory;

        public Analyse(String directory) {
            this.directory = directory;
        }

        @Override
        public void run() {
            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";

            try {
                br = new BufferedReader(new FileReader(directory));
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(cvsSplitBy);
                    list.add(new Storing(data[32], data[14], data[23], data[43], data[8]));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public int getThreadPool() {
        return ThreadPool;
    }

    public void setThreadPool(int threadPool) {
        ThreadPool = threadPool;
    }
}