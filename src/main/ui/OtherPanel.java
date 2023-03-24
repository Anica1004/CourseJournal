package ui;

import javax.swing.*;
import java.awt.*;

public class OtherPanel extends Panel {
    public OtherPanel(){
        this.setBounds(0, 570, 1000, 100);
        this.setBackground(Color.white);
        addButton();
    }

    private void addButton() {
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        button1.setPreferredSize(new Dimension(200, 90));
        button2.setPreferredSize(new Dimension(200, 90));
        button3.setPreferredSize(new Dimension(200, 90));

        button1.setBounds(0, 140, 200, 90);
        button2.setBounds(300, 140, 200, 90);
        button3.setBounds(600, 140, 200, 90);
        this.add(button1);
        this.add(button2);
        this.add(button3);

    }



}
