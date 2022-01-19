package ui;

import javax.swing.*;
import java.awt.*;


public class HelpScreen extends JFrame {

    public HelpScreen() {
        super("Help Screen");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
       int width = (int) dim.getWidth();
       int height = (int) dim.getHeight();

        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton backButton = new JButton("return");
        JLabel howToText = new JLabel();
         JPanel textPanel = new JPanel();
        howToText.setText("<html>\n" +
                "\n" +
                "    <h2>How to Play</h2>\n" +
                "    <h4>The noble phantasm</h4>\n" +
                "    <p>To control the movement of the noble phantasm, the player needs to use arrow-left and arrow-right buttons. The noble phantasm ( can also be rotated temporarily by up to 45- or 135-degrees using A and D keys respectively.)</p>\n" +
                "    <h4>Obstacles</h4>\n" +
                "    <p>There are 4 different obstacles </p>\n" +
                "    <ul>\n" +
                "      <li><b>Simple Obstacle (Wall Maria)</b> \n" +
                "        Can be broken in one hit\n" +
                "      </li>\n" +
                "      <li><b>Firm Obstacle (Steins Gate)</b> \n" +
                "        These obstacles are more difficult to destroy. Each one contains a number written on it, which corresponds to the number of hits it requires to be destroyed. After every hit it receives, the number decreases by 1, and the obstacle disappears once the number reaches zero.\n" +
                "      </li>\n" +
                "      <li><b>Explosive Obstacle (Pandora’s Box)</b>\n" +
                "        This obstacle has a circular shape and it explodes once it is hit. Once exploded, its remains fall downwards towards the noble phantasm. If the remains touch the noble phantasm, you will loses a chance.\n" +
                "      </li>\n" +
                "      <li><b>Gift Obstacle (Gift of Uranus)</b> \n" +
                "        This obstacle can be destroyed in one hit like the simple one. Once destroyed, it drops a box downwards towards the noble phantasm. If the noble phantasm touches the box, then the box opens and rewards the warrior with a magical ability.\n" +
                "      </li>\n" +
                "    </ul>\n" +
                "\n" +
                "    <h4>Magical Abilities</h4>\n" +
                "    <ul>\n" +
                "      <li><b>Chance Giving Ability</b> \n" +
                "        This ability increases the your chances by 1.\n" +
                "      </li>\n" +
                "      <li> <b>Noble Phantasm Expansion</b> \n" +
                "        This ability doubles the length of the noble phantasm. Once activated, it lasts for only 30 seconds, after which the noble phantasm returns to its original state.\n" +
                "      </li>\n" +
                "      <li> <b>Magical Hex</b>\n" +
                "        This ability equips the noble phantasm with two magical canons on both of its ends. The canons should point upwards and they rotate as the noble phantasm rotates. They can fire magical hexes that can hit the obstacles. A hex hit has the same effect as the hit of an enchanted sphere. It does not activate immediately, but can be activated by pressing H or pressing its icon on the screen. Once activated it remains active for only 30 seconds and then disappears afterwards.\n" +
                "      </li>\n" +
                "\n" +
                "      <li>\n" +
                "          <b>Unstoppable Enchanted Sphere</b> \n" +
                "          This ability upgrades the enchanted sphere and makes it much more powerful, such that if it hits any obstacles, it destroys it and passes through it regardless of its type (even for the firm obstacles). This upgrade only lasts 30 seconds after it is activated.\n" +
                "      </li>\n" +
                "    </ul>\n" +
                "\n" +
                "<h4>Note: You can save the game by first pausing it and then pressing the save button</h4>\n" +
                "<p>Enjoy your adventure</p>\n" +
                "\n" +
                "\n" +
                "  </body>\n" +
                "</html>");
        howToText.setFont(new Font("Verdana", Font.PLAIN, 13));
        howToText.setBounds(20,20,width - 70,700);
        backButton.addActionListener(e -> {
            setVisible(false);
            new TitleScreen(width, height);
        });

        backButton.setSize(100, 30);
		backButton.setBounds(getBounds().width/2-backButton.getWidth()/2 + 40, getBounds().height/2 , backButton.getWidth(), backButton.getHeight());

        textPanel.setLayout(null);
        textPanel.add(howToText);
        add(backButton);
        add(textPanel);


    }
}
