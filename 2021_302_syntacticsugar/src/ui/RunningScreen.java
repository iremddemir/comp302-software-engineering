package ui;

import javax.swing.*;
import java.awt.*;

public class RunningScreen extends JFrame{

    public RunningScreen(){
        super("Need for Spear");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dim.getWidth();
        int height = (int) dim.getHeight();

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,width,height);

        JPanel panel = new RunningScreenPanel(width, height);
        setContentPane(panel);


        setResizable(true);
        setVisible(true);
        revalidate();
        repaint();
        }
}
