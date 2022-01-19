package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import domain.Game;

public class PauseScreen extends JPanel {

    public PauseScreen(int x, int y){
        int width = 200;
        int height = 100;
        setBounds(x-width/2,y-height/2,200,100);
        setLayout(new BorderLayout());

        setBackground(Color.orange);
        add(new JLabel("Game paused", SwingConstants.CENTER));

        JButton quitButton = new JButton("Quit Game");
        quitButton.setBounds(150, 150, 100, 50);
        quitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
            }
        });

        JButton resumeButton = new JButton("RESUME");
        resumeButton.setBounds(150, 150, 100, 50);

        resumeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                Game.getInstance().switchPaused();
            }
        });
        add(resumeButton, BorderLayout.PAGE_START);

        JButton saveButton = new JButton("SAVE");
        saveButton.setBounds(150, 150, 100, 50);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Game.getInstance().saveGame(2);
            }
        });
        add(saveButton, BorderLayout.CENTER);
        add(quitButton, BorderLayout.PAGE_END);

        setVisible(true);
        revalidate();
        repaint();
    }

}
