package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameCannotStartScreen  extends JFrame{

	public GameCannotStartScreen() {
		 super("Game cannot start");
		 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	       int width = (int) dim.getWidth();
	       int height = (int) dim.getHeight();

	        setVisible(true);
	        setExtendedState(JFrame.MAXIMIZED_BOTH);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        JButton backButton = new JButton("return");
	        JLabel cannotStart = new JLabel();
	         JPanel textPanel = new JPanel();
	        cannotStart.setText("<html>\n" +
	                "\n" +
	                "    <h2>Game cannot start</h2>\n" +
	                "    <h4>Minimum Requirements</h4>\n" +
	                "    <p>To be able to start the game, your build mode desing should satisfy the following</p>\n" +
	                "    <ul>\n" +
	                "      <li><b>Simple Obstacle (Wall Maria)</b> \n" +
	                "        >= 75" +
	                "      </li>\n" +
	                "      <li><b>Firm Obstacle (Steins Gate)</b> \n" +
	                "        >= 10\n" +
	                "      </li>\n" +
	                "      <li><b>Explosive Obstacle (Pandora’s Box)</b>\n" +
	                "        >= 5\n" +
	                "      </li>\n" +
	                "      <li><b>Gift Obstacle (Gift of Uranus)</b> \n" +
	                "        >= 10 \n" +
	                "      </li>\n" +
	                "    </ul>\n" +
	                "\n" +
	                "    <h4>What to do next?</h4>\n" +
	                "    <ul>\n" +
	                "      <li><b>You can use Random Game option and game can be designed for you</b> \n" +
	                "      <li> <b>You can restart your desing to satisfy requirements </b> \n" +       
	                "\n" +
	                "\n" +
	                "  </body>\n" +
	                "</html>");
	        cannotStart.setFont(new Font("Verdana", Font.PLAIN, 13));
	        cannotStart.setBounds(20,20,width - 70,700);
	        backButton.addActionListener(e -> {
	            setVisible(false);
	            new TitleScreen(width, height);
	        });

	        backButton.setSize(100, 30);
			backButton.setBounds(getBounds().width/2-backButton.getWidth()/2 + 40, getBounds().height/2 , backButton.getWidth(), backButton.getHeight());

	        textPanel.setLayout(null);
	        textPanel.add(cannotStart);
	        add(backButton);
	        add(textPanel);

	}

}
