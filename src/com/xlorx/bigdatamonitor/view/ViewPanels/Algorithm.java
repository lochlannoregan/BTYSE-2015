package com.xlorx.bigdatamonitor.view.ViewPanels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by User on 07/10/2014.
 */
public class Algorithm extends JPanel {

    private JRadioButton algorithmdisabled;
    private JRadioButton algorithmenabled;
    private JRadioButton under80;
    private JRadioButton over80;
    private ButtonGroup pressureLevels;
    private ButtonGroup algorithmradiobuttons;
    private JLabel version;

    public Algorithm() {

        Dimension dim = getPreferredSize();
        dim.setSize(300, 925);
        setPreferredSize(dim);

        Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border inner = BorderFactory.createTitledBorder("Analysis");

        setBorder(BorderFactory.createCompoundBorder(outer, inner));

        algorithmdisabled = new JRadioButton("Algorithm Disabled");
        algorithmenabled = new JRadioButton("Algorithm Enabled");
        over80 = new JRadioButton("Throttle on - Throughput off");
        under80 = new JRadioButton("Throttle off - Throughput on");
        pressureLevels = new ButtonGroup();
        algorithmradiobuttons = new ButtonGroup();
        version = new JLabel("Version 1.4");

        // Add radio buttons to radio group

        algorithmradiobuttons.add(algorithmdisabled);
        algorithmradiobuttons.add(algorithmenabled);
        algorithmdisabled.setSelected(true);
        pressureLevels.add(under80);
        pressureLevels.add(over80);
        under80.setSelected(true);

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        // Algorithm Enabled Radio Button

        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.0001;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);

        add(algorithmdisabled, gc);

        // Algorithm Enabled Radio Button

        gc.gridy = 1;

        gc.weightx = 1;
        gc.weighty = 0.0001;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);

        add(algorithmenabled, gc);

        // Over 80% Radio Button

        gc.gridy = 2;

        gc.weightx = 1;
        gc.weighty = 0.0001;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);

        add(under80, gc);

        // Under 80% Radio Button

        gc.gridy = 3;

        gc.weightx = 1;
        gc.weighty = 0.0001;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NORTH;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);

        add(over80, gc);

        // Add version

        gc.gridy = 4;

        gc.weightx = 1;
        gc.weighty = 5;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NORTH;
        gc.anchor = GridBagConstraints.LAST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 0);

        add(version, gc);

    }

    public boolean isSelected(){
        if(algorithmenabled.isSelected()){
            return true;
        }else return false;
    }

    public boolean isSelectedPressure(){
        if(under80.isSelected()){
            return true;
        }else return false;
    }

}