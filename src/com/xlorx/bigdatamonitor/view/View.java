package com.xlorx.bigdatamonitor.view;

import com.xlorx.bigdatamonitor.controller.Controller;
import com.xlorx.bigdatamonitor.view.ViewPanels.Algorithm;
import com.xlorx.bigdatamonitor.view.ViewPanels.ControlPanel;
import com.xlorx.bigdatamonitor.view.ViewPanels.Monitoring;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 01/10/2014.
 */
public class View extends JFrame {

    private ControlPanel controlPanel;
    private Monitoring monitoring;
    private Algorithm algorithm;
    private Controller controller;

    public View() {

        super("Monitoring");
        setSize(900, 925);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(1, 3));

        controlPanel = new ControlPanel();
        monitoring = new Monitoring();
        algorithm = new Algorithm();

        add(controlPanel);

        add(monitoring);

        add(algorithm);

    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public Monitoring getMonitoring() {
        return monitoring;
    }

    public Algorithm getAlgorithm() { return algorithm; }

}

