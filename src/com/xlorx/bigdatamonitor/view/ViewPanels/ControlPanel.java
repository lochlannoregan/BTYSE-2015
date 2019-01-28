package com.xlorx.bigdatamonitor.view.ViewPanels;

import com.xlorx.bigdatamonitor.controller.BeginAnalysisListener;
import com.xlorx.bigdatamonitor.controller.MonitorListener;
import com.xlorx.bigdatamonitor.controller.StopAllListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 07/10/2014.
 */
public class ControlPanel extends JPanel implements ActionListener {

    private JButton monitor;
    private JButton beginAnalysis;
    private JButton stopAll;
    private JTextArea console;
    private MonitorListener monitorListener;
    private StopAllListener stopAllListener;
    private BeginAnalysisListener beginAnalysisListener;


    public ControlPanel() {

        Dimension dim = getPreferredSize();
        dim.setSize(300, 925);
        setPreferredSize(dim);

        monitor = new JButton("Monitor");
        beginAnalysis = new JButton("Begin Analysis");
        stopAll = new JButton("Stop All");
        console = new JTextArea(40, 18);
        console.setEditable(false);

        // Set widths of buttons

        monitor.setPreferredSize(new Dimension(150, 30));
        beginAnalysis.setPreferredSize(new Dimension(150, 30));
        stopAll.setPreferredSize(new Dimension(150, 30));

        // Remove focus from buttons

        monitor.setFocusPainted(false);
        beginAnalysis.setFocusPainted(false);
        stopAll.setFocusPainted(false);

        Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border inner = BorderFactory.createTitledBorder("Control Panel");

        setBorder(BorderFactory.createCompoundBorder(outer, inner));

        stopAll.addActionListener(this);

        monitor.addActionListener(this);

        beginAnalysis.addActionListener(this);

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        // Monitor Button

        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.01;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);

        add(monitor, gc);

        // Begin Analysis Button

        gc.gridy = 1;

        gc.weightx = 1;
        gc.weighty = 0.01;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);

        add(beginAnalysis, gc);

        // Stop All Button

        gc.gridy = 2;

        gc.weightx = 1;
        gc.weighty = 0.01;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);

        add(stopAll, gc);

        // Console

        gc.gridy = 3;

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.SOUTHWEST;
        gc.insets = new Insets(0, 0, 0, 0);

        add(new JScrollPane(console), gc);
    }

    public void setConsole(String text) {
        console.append(text + "\n");
    }

    public void setMonitorListener(MonitorListener monitorListener) {
        this.monitorListener = monitorListener;
    }

    public void setStopAll(StopAllListener stopAllListener) {
        this.stopAllListener = stopAllListener;
    }

    public void setBeginAnalysis(BeginAnalysisListener beginAnalysisListener) {
        this.beginAnalysisListener = beginAnalysisListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clicked = (JButton) e.getSource();

        if (clicked == monitor) {
            if (monitorListener != null) {
                monitorListener.monitorPerformed();
            }
        }

        if (clicked == stopAll) {
            if (stopAllListener != null) {
                stopAllListener.stopAllPerformed();
            }
        }

        if (clicked == beginAnalysis) {
            if (beginAnalysisListener != null) {
                beginAnalysisListener.beginAnalysisPerformed();
            }
        }

    }


}
