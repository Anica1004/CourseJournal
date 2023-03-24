package ui;

import javax.swing.*;
import java.awt.*;

public class AddCoursePanel extends Panel {

    public AddCoursePanel() {

        this.setBackground(Color.white);
        addButton();
        this.setBounds(0, 130, 1000, 100);


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
