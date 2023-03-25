package ui.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AddCourseFrame extends JFrame {

    AddCourseFrame() {

        Border border = BorderFactory.createDashedBorder(Color.pink);
        JLabel label = new JLabel();
        label.setText("\"Add a new course!\"");
        label.setFont(new Font("Serif", Font.ITALIC, 20));
        label.setIconTextGap(20);
        label.setBackground(Color.pink.brighter());

        label.setOpaque(true);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(0, 0, 1000, 80);
        ImageIcon image = new ImageIcon("src/main/ui/gui/BackgroundImage.jpg");
        this.setContentPane(new JLabel(image));
        this.setTitle("\"Add a new Course\"");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setResizable(false);
        this.setSize(1000, 2000);
        this.setLayout(null);
        this.add(label);
        this.setVisible(true);

    }






}
