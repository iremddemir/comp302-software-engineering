package ui;
import domain.Game;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class LoadScreen extends JFrame {
    private Color BACKGROUND_COLOR = new Color(240, 230, 140);
    private Color TEXT_COLOR = new Color(128,128,0);
    private GridBagConstraints gbc;
    private int width;
    private int height;
    private ArrayList<String> list;
    private Game game;

    public LoadScreen(String username) {
        super("Load Screen");
        game = Game.getInstance();
        list = game.getSavedGames(username);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) dim.getWidth();
        height = (int) dim.getHeight();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,width,height);
        gbc = new GridBagConstraints();


        if (list.size() == 1) {
            game.createGameBoard(width, height);
            game.loadGame(list.get(0));
            dispose();
            new RunningScreen();



        } else {

            JPanel mainPanel = new JPanel();
            mainPanel(mainPanel);

            JPanel introPanel = new JPanel();
            introLabel(mainPanel, introPanel, list);

            if (list.size() != 0){
                JPanel listPanel = new JPanel();
                listLabelsPanel(mainPanel, listPanel, list);

            }

            setVisible(true);

        }





    }

    private void mainPanel(JPanel mainPanel) {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);
    }

    public void introLabel(JPanel mainPanel, JPanel loadGamePanel, ArrayList<String> list) {
        loadGamePanel.setLayout(new GridBagLayout());
        gbc.insets = new Insets(100, 50, 100, 50);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        String message;
        loadGamePanel.setBackground(BACKGROUND_COLOR);
        if (list.size() == 0) {
             message = "Looks like you have %d saved games.".formatted(list.size());
            JButton backButton = new JButton("return");
            backButton.setSize(100, 30);
            backButton.addActionListener(e -> {
                setVisible(false);
                new TitleScreen(width, height);
                dispose();

            });
            JLabel numSavedLabel = new JLabel(message);
            numSavedLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
            numSavedLabel.setForeground(Color.decode("#8b0000"));
            gbc.gridx = 0;
            gbc.gridy = 1;
            loadGamePanel.add(numSavedLabel);
            loadGamePanel.add(backButton);

        }
        else{ message = "Looks like you have %d saved games. Choose one below."
                .formatted(list.size());
            JLabel numSavedLabel = new JLabel(message);
            numSavedLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
            numSavedLabel.setForeground(Color.decode("#8b0000"));
            gbc.gridx = 0;
            gbc.gridy = 1;
            loadGamePanel.add(numSavedLabel);
        }


        mainPanel.add(loadGamePanel);

    }

    public void listLabelsPanel(JPanel mainPanel, JPanel listPanel, ArrayList<String> list) {
        //list loaded files
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            JButton label = new JButton(s);
            label.setFont(new Font("Helvetica", Font.BOLD, 14));
            label.setForeground(TEXT_COLOR);
            listPanel.setBackground(BACKGROUND_COLOR);
            gbc.gridx=i;
            gbc.gridy=0;
            label.addActionListener(e -> {

                String gameName = label.getText(); //get saved game name
                game.createGameBoard(width, height);
                game.loadGame(gameName);
                new RunningScreen();
                dispose();

            });

            listPanel.add(label);
        }

        mainPanel.add(listPanel);
    }


}
