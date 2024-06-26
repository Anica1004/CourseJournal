package ui.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

// This class represents the frame of the Front
// which essentially is a home page where the user can navigate to other
// frames
public class FrontFrame extends JFrame {


    // EFFECTS: initializes a label that informs the user to choose different options to
    // navigate, and initializes background
    FrontFrame() {

        Border border = BorderFactory.createDashedBorder(Color.pink);
        JLabel label = new JLabel();
        label.setText("\"Hello user, select from below to fulfill your needs!\"");
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
        this.setTitle("\"Your University Journey\"");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1000, 1000);
        this.setLayout(null);
        this.add(label);
        this.setVisible(true);

    }





}
