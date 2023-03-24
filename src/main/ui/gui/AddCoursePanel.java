package ui.gui;

import javax.swing.*;
import java.awt.*;

public class AddCoursePanel extends Panel {

    private JButton button;

    public AddCoursePanel() {

        this.setBackground(Color.white);
        addButton();
        this.setBounds(0, 130, 1000, 100);


    }

    private void addButton() {
        button = new JButton();
        button.setPreferredSize(new Dimension(300, 90));
        button.setBounds(0, 140, 1000, 90);
        button.setText("Add a Course");
        this.add(button);
        button.setVerticalAlignment(JButton.CENTER);
        button.setHorizontalAlignment(JButton.CENTER);
    }

    public JButton getButton() {
        return this.button;
    }





}
