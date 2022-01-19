package ui;

import domain.Direction;
import domain.Game;
import domain.Ymir;
import domain.abilities.AbilityType;
import domain.listeners.AbilityEvent;
import domain.physicalobjects.Paddle;
import domain.physicalobjects.PhysicalObject;
import domain.physicalobjects.engines.AbilityEngine;
import domain.services.Service;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RunningScreenPanel extends JPanel {

    private static List<PhysicalObjectLabel> labels = new ArrayList<>();

    public RunningScreenPanel(int width, int height){
        setBounds(0, 0, width, height);

        Service.addServiceListener(
                (serviceType, input, result) -> {
                    switch (serviceType){
                        case DESTROY:
                            labels = labels.stream()
                                    .filter(label -> {
                                        boolean predicate =  !(label.getPhysicalObject().equals(input));
                                        return predicate;
                                    })
                                    .collect(Collectors.toList());
                            break;
                        case SUMMON:
                            PhysicalObject physicalObject = (PhysicalObject) input;
                            PhysicalObjectLabel label = new PhysicalObjectLabel(physicalObject);
                            labels.add(label);
                            break;
                        case GAME_LOST:
                            Game.getInstance().switchPaused();
                            new exitPopup("Lost Screen", "You lost (✖╭╮✖)");
                            SwingUtilities.getWindowAncestor(this).dispose();

                            break;
                        case GAME_WON:
                            Game.getInstance().switchPaused();
                            new exitPopup("Won Screen", "You won 〤◕‿◕〤");
                            SwingUtilities.getWindowAncestor(this).dispose();
                            break;
                    }

                    revalidate();
                    repaint();
                }
        );
        RunningScreenInfoPanel infoPanel = new RunningScreenInfoPanel();
        add(infoPanel);

        AbilityEngine.getInstance().addEventListener(o -> {
            AbilityEvent event = (AbilityEvent) o;

            if(event.isActive()){
                //JLabel label = new JLabel(event.getAbility().toString());
                infoPanel.setActiveAbility(event.getAbility());
            }
            else{
                infoPanel.removeAbility(event.getAbility());
            }
        });


        Game game = Game.getInstance();
        Ymir ymir = new Ymir();

        if (game.getGameBoard() == null){
            game.createGameBoard(getWidth(), getHeight());
        }

        for(PhysicalObject object: game.getGameBoard().getPhysicalObjects()){
            if(object instanceof Paddle)
                labels.add(new PaddleLabel((Paddle) object));
            else
                labels.add(new PhysicalObjectLabel(object));
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game.getInstance().shootBall();
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_LEFT :
                        game.movePaddle(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.movePaddle(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        game.shootBall();
                        break;
                    case KeyEvent.VK_A:
                        game.rotatePaddle(Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        game.rotatePaddle(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_W:
                        game.shootBall();
                        break;
                    case KeyEvent.VK_H:
                        game.useAbility(AbilityType.MagicalHexAbility);
                        break;
                    case KeyEvent.VK_T:
                        game.useAbility(AbilityType.PaddleExpansionAbility);
                        break;
                    case KeyEvent.VK_SPACE:
                        game.shootMagicalHex();
                        break;

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });


        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                infoPanel.setAbilityLabels();
                infoPanel.setScore();
                infoPanel.setLives();
                infoPanel.setTime();
                requestFocusInWindow();
                revalidate();
                repaint();
            }
        });


        timer.start();
        game.start();
        ymir.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon(this.getClass().getResource(PATHS.BACKGROUND_IMG_PATH)).getImage(), 0,0, getWidth(), getHeight(), (img, infoflags, x, y, width, height) -> false);

        for(PhysicalObjectLabel label: new ArrayList<>(labels))
            label.paint(g);
    }
}


class exitPopup extends JFrame{

    public exitPopup(String title, String message){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lastMessage = new JLabel(message);
        lastMessage.setBounds(100,50,300,100);
        lastMessage.setFont(new Font(null,Font.PLAIN,25));
        lastMessage.setVisible(true);
        add(lastMessage);
        JButton exitButton = new JButton("Exit");
        exitButton.setVisible(true);
        exitButton.setBounds(100,160,200,40);
        exitButton.setFocusable(false);
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        add(exitButton);
        setSize(400, 300);
        setLocationRelativeTo(null);

        setLayout(null);
        setVisible(true);
    }

}
