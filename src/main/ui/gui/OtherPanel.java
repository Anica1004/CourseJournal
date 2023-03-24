package ui.gui;

import javax.swing.*;
import java.awt.*;

public class OtherPanel extends Panel {

    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;

    public OtherPanel() {
        this.setBounds(0, 570, 1000, 100);
        this.setBackground(Color.white);
        addButton();
    }

    private void addButton() {
        saveButton = new JButton();
        loadButton = new JButton();
        quitButton = new JButton();
        saveButton.setText("Save File");
        loadButton.setText("Load File");
        quitButton.setText("Quit");
        saveButton.setPreferredSize(new Dimension(200, 90));
        loadButton.setPreferredSize(new Dimension(200, 90));
        quitButton.setPreferredSize(new Dimension(200, 90));

        saveButton.setBounds(0, 140, 200, 90);
        loadButton.setBounds(300, 140, 200, 90);
        quitButton.setBounds(600, 140, 200, 90);
        this.add(saveButton);
        this.add(loadButton);
        this.add(quitButton);

    }



}
