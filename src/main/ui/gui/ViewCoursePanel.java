package ui.gui;

import javax.swing.*;
import java.awt.*;

public class ViewCoursePanel extends Panel {
    private JButton button;

    public ViewCoursePanel() {
        this.setBounds(0, 350, 1000, 100);
        this.setBackground(Color.white);
        addButton();
    }

    private void addButton() {
        button = new JButton();
        button.setPreferredSize(new Dimension(300, 90));
        button.setBounds(0, 140, 1000, 90);
        button.setText("View Courses");
        this.add(button);
        button.setVerticalAlignment(JButton.CENTER);
        button.setHorizontalAlignment(JButton.CENTER);
    }

    public JButton getButton() {
        return this.button;
    }



}
