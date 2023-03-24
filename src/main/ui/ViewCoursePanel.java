package ui;

import javax.swing.*;
import java.awt.*;

public class ViewCoursePanel extends Panel {
    public ViewCoursePanel() {
        this.setBounds(0, 350, 1000, 100);
        this.setBackground(Color.white);
        addButton();
    }

    private void addButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(300, 90));
        button.setBounds(0, 140, 1000, 90);
        this.add(button);
        button.setVerticalAlignment(JButton.CENTER);
        button.setHorizontalAlignment(JButton.CENTER);
    }



}
