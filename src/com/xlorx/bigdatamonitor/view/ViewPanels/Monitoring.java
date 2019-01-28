package com.xlorx.bigdatamonitor.view.ViewPanels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by User on 07/10/2014.
 */
public class Monitoring extends JPanel {

    private JLabel RAMusageLabel;
    private JTextField RAMusage;
    private JLabel CPUUsageLabel;
    private JTextField CPUUsage;
    private JLabel RAMProcessusageLabel;
    private JTextField RAMProcessusage;
    private JLabel CPUProcessUsageLabel;
    private JTextField CPUProcessUsage;

    public Monitoring() {

        Dimension dim = getPreferredSize();
        dim.setSize(300, 925);
        setPreferredSize(dim);

        RAMusage = new JTextField(22);
        RAMusage.setEditable(false);
        RAMusageLabel = new JLabel("RAM System Usage");
        CPUUsage = new JTextField(22);
        CPUUsage.setEditable(false);
        CPUUsageLabel = new JLabel("CPU System Usage");

        RAMProcessusage = new JTextField(22);
        RAMProcessusage.setEditable(false);
        RAMProcessusageLabel = new JLabel("RAM GUI Process Usage");
        CPUProcessUsage = new JTextField(22);
        CPUProcessUsage.setEditable(false);
        CPUProcessUsageLabel = new JLabel("CPU GUI Process Usage");

        Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border inner = BorderFactory.createTitledBorder("Monitoring");

        setBorder(BorderFactory.createCompoundBorder(outer, inner));

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        // RAM Usage Label

        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.01;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5, 5, 0, 0);
        add(RAMusageLabel, gc);

        // RAM Usage Field

        gc.gridy = 1;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5, 5, 0, 0);
        add(RAMusage, gc);

        // CPU Usage Label

        gc.gridy = 2;

        gc.weightx = 1;
        gc.weighty = 0.01;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5, 5, 0, 0);

        add(CPUUsageLabel, gc);

        // CPU Usage Field

        gc.gridy = 3;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5, 5, 0, 0);
        add(CPUUsage, gc);

        // Separator

        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(5, 1));

        gc.gridy = 4;

        gc.weightx = 1;
        gc.weighty = .25;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(5, 5, 0, 0);

        add(separator, gc);

        // RAM Process Usage Label

        gc.gridy = 5;

        gc.weightx = 1;
        gc.weighty = 0.01;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(90, 5, 0, 0);
        add(RAMProcessusageLabel, gc);

        // Ram Process Usage

        gc.gridy = 6;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5, 5, 0, 0);
        add(RAMProcessusage, gc);

        // CPU Process Usage Label

        gc.gridy = 7;

        gc.weightx = 1;
        gc.weighty = 0.01;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5, 5, 0, 0);
        add(CPUProcessUsageLabel, gc);

        // CPU Process Usage

        gc.gridy = 8;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5, 5, 0, 0);
        add(CPUProcessUsage, gc);


    }

    public void setMemoryUsage(String RAM) {
        RAMusage.setText(RAM);
    }

    public void setCPUUsage(String CPU) {
        CPUUsage.setText(CPU);
    }

    public void setCPUProcessUsage(String CPU) {
        CPUProcessUsage.setText(CPU);
    }

    public void setRAMProcessusage(String RAM) {
        RAMProcessusage.setText(RAM);
    }

    public void clear() {
        RAMusage.setText(null);
        CPUUsage.setText(null);
        RAMProcessusage.setText(null);
        CPUProcessUsage.setText(null);
    }
}


