package ui.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

public class FrontFrame extends JFrame {

    FrontFrame() {

        Border border = BorderFactory.createDashedBorder(Color.pink);
        JLabel label = new JLabel();
        label.setText("\"Hello user, select from below to fulfill your needs!\"");
        label.setFont(new Font("Serif", Font.ITALIC, 20));
        label.setIconTextGap(20);
        label.setBackground(Color.pink.brighter());
        label.setOpaque(true);
        label.setBorder(border);
        label.setSize(1000, 80);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);


        ImageIcon image = new ImageIcon("src/main/ui/gui/BackgroundImage.jpg");
        this.setContentPane(new JLabel(image));
        this.setTitle("\"Your University Journey\"");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1000, 1000);
        this.setLayout(new B);
        this.setVisible(true);

    }





}
