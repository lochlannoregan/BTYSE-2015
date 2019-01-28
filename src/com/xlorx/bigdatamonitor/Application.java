package com.xlorx.bigdatamonitor;

import com.xlorx.bigdatamonitor.controller.AlgorithmController;
import com.xlorx.bigdatamonitor.controller.Controller;
import com.xlorx.bigdatamonitor.view.View;

import javax.swing.*;

/**
 * Created by User on 01/10/2014.
 */
public class Application {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

            		// Random Comment for testing

                runApplication();

            }
        });
    }

    public static void runApplication() {

        View view = new View();
        AlgorithmController algorithmController = new AlgorithmController(view);
        Controller controller = new Controller(view, algorithmController);


    }

}

