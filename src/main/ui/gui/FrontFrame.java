package ui.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FrontFrame extends JFrame {

    FrontFrame() {
        Border border = BorderFactory.createDashedBorder(Color.pink);
        JLabel label = new JLabel();
        label.setText("\"Hello user, select from below to fulfill your needs!\"");
        label.setFont(new Font("Serif", Font.ITALIC, 20));


        label.setIconTextGap(20);
        label.setBackground(Color.pink);

        label.setOpaque(true);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(0, 0, 1000, 80);


        this.setTitle("\"Your University Journey\"");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1000, 1000);
        this.setLayout(null);
        this.add(label);
        addPanels();
        this.setVisible(true);

    }


    private void addPanels() {
        AddCoursePanel addCoursePanel = new AddCoursePanel();
        RemoveCoursePanel removeCoursePanel = new RemoveCoursePanel();
        ViewCoursePanel viewCoursePanel = new ViewCoursePanel();
        GradeSummaryPanel gradeSummaryPanel = new GradeSummaryPanel();
        OtherPanel otherPanel = new OtherPanel();
        this.add(addCoursePanel);
        this.add(removeCoursePanel);
        this.add(viewCoursePanel);
        this.add(gradeSummaryPanel);
        this.add(otherPanel);

    }


}
