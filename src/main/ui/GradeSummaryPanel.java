package ui;

import javax.swing.*;
import java.awt.*;

public class GradeSummaryPanel extends Panel {
    public GradeSummaryPanel(){
        this.setBounds(0, 460, 1000, 100);
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
