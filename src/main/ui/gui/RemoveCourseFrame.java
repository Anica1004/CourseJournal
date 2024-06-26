package ui.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// This class represents the frame of RemoveCourse
// which essentially is a page where the user can remove a course
public class RemoveCourseFrame extends JFrame {

    // EFFECTS: initializes a label that informs the user to remove a course, and
    // set background
    public RemoveCourseFrame() {

        Border border = BorderFactory.createDashedBorder(Color.cyan);
        JLabel label = new JLabel();
        label.setText("\"Remove a Course!\"");
        label.setFont(new Font("Serif", Font.ITALIC, 18));
        label.setIconTextGap(20);
        label.setBackground(Color.cyan.brighter());

        label.setOpaque(true);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon image = new ImageIcon("src/main/ui/gui/BackgroundImage.jpg");
        this.setContentPane(new JLabel(image));
        this.setTitle("\"Add a new Course\"");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1000, 1000);
        this.setLayout(new FlowLayout());
        label.setPreferredSize(new Dimension(1000, 80));
        this.add(label);
        this.setVisible(true);
    }


}

