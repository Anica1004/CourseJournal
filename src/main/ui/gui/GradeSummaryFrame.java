package ui.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// This class represents the frame of GradeSummary
// which essentially is a page where the user can see a grade summary
public class GradeSummaryFrame extends JFrame {

    // EFFECTS: initializes a label that informs the user to see the grade summary, and
    // background
    public GradeSummaryFrame() {

        Border border = BorderFactory.createDashedBorder(Color.pink);
        JLabel label = new JLabel();
        label.setText("\"Grade Summary!\"");
        label.setFont(new Font("Serif", Font.ITALIC, 18));
        label.setIconTextGap(20);
        label.setBackground(Color.pink.brighter());

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
        this.add(label);
        this.setLayout(new FlowLayout());
        label.setPreferredSize(new Dimension(1000, 80));
        this.setVisible(true);
    }



}
