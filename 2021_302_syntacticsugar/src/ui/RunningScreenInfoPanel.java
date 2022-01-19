package ui;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domain.*;
import domain.abilities.*;


public class RunningScreenInfoPanel extends JPanel{
	private static final DecimalFormat df = new DecimalFormat("0.0");

	private GridBagConstraints gbc;
	private Game game;
	private double width;
	private double height;
	private JLabel chanceGivingLabel, magicalHexLabel, paddleExpansionLabel, unstoppableBallLabel;
	private JButton chanceGivingButton, magicalHexButton, paddleExpansionButton, unstoppableBallButton;
	private JLabel scoreLabel, livesLabel, activeAbilityLabel, timeLabel, ymirLabel;
	private Color DEFAULT = new Color(238,232,170);
	private Color HIGHLIGHT = new Color(128,128,0);
	private ArrayList<String> activeAbility = new ArrayList<String>();
	public RunningScreenInfoPanel() {
		
		game = Game.getInstance();
		width = game.getGameBoard().getSize().getX() ;
		height = game.getGameBoard().getSize().getY();
		
		setSize((int) width, (int)height);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		gbc = new GridBagConstraints();
		JPanel infoPanel = infoPanel();
		JPanel abilitiesPanel =  abilitiesPanel();
		JPanel pauseSavePanel = pauseSavePanel();
		JPanel ymirPanel = ymirPanel();
		setBackground(new Color(240,230,140));
		add(infoPanel);
		add(abilitiesPanel);
		add(ymirPanel);
		add(pauseSavePanel);
		

	}
	private JPanel infoPanel() {
		JPanel infoPanel = new JPanel(new GridBagLayout());
		infoPanel.setSize((int) width/3, (int)height);
		
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel scoreText = new JLabel("Score:");
		scoreLabel = new JLabel("0");
		JLabel livesText = new JLabel("Lives:");
		livesLabel = new JLabel("3");
		
		
		JLabel userText = new JLabel("User: ");
		JLabel userLabel = new JLabel(game.getUserName());
		
		JLabel timeText = new JLabel("Time: ");
	    timeLabel = new JLabel(String.valueOf(game.getTime()));
		timeLabel.setText(Integer.valueOf(game.getTime()).toString());
		
		gbc.gridx=0;
		gbc.gridy=0;
		infoPanel.add(userText,gbc);
		gbc.gridx=1;
		gbc.gridy=0;
		infoPanel.add(userLabel,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		infoPanel.add(livesText,gbc);
		gbc.gridx=1;
		gbc.gridy=1;
		infoPanel.add(livesLabel,gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		infoPanel.add(timeLabel,gbc);
		gbc.gridx=0;
		gbc.gridy=2;
		infoPanel.add(timeText,gbc);
		gbc.gridx=1;
		gbc.gridy=3;
		infoPanel.add(scoreLabel,gbc);
		gbc.gridx=0;
		gbc.gridy=3;
		infoPanel.add(scoreText,gbc);
		
		
		
		
		

		Border borderLine = BorderFactory.createLineBorder(Color.black);
		infoPanel.setBorder(borderLine);
		infoPanel.setOpaque(false);
		
		return infoPanel;
	}
	
	private JPanel abilitiesPanel() {
		JPanel abilitiesPanel = new JPanel(new GridBagLayout());
		abilitiesPanel.setSize((int) width/3, (int)height);
		
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		/*chanceGivingLabel = new JLabel("0");
		chanceGivingButton = new JButton("Chance Giving");
		chanceGivingButton.setBackground(DEFAULT);
		chanceGivingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Activate Magical Ability
				game.useAbility(AbilityType.ChanceGivingAbility);
				
			}
		})*/
		//TODO Get available ability number
		magicalHexLabel = new JLabel("0");
		magicalHexButton = new JButton("Magical Hex");
		magicalHexButton.setBackground(DEFAULT);
		magicalHexButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Activate Magical Ability
				game.useAbility(AbilityType.MagicalHexAbility);
				
			}
		});
		//TODO Get avaliable ability number
		paddleExpansionLabel = new JLabel("0");
		paddleExpansionButton = new JButton("Paddle Expansion");
		paddleExpansionButton.setBackground(DEFAULT);
		paddleExpansionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Activate Magical Ability
				game.useAbility(AbilityType.PaddleExpansionAbility);
				
			}
		});
		/*
		//TODO Get avaliable ability number
		unstoppableBallLabel = new JLabel("0");
		unstoppableBallButton = new JButton("Unstoppable Ball");
		unstoppableBallButton.setBackground(DEFAULT);
		unstoppableBallButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Activate Magical Ability
				game.useAbility(AbilityType.UnstoppableBallAbility);
				
			}
		});*/
		JLabel activeText = new JLabel("Active Abilities:");
		activeAbilityLabel = new JLabel("");
		
		
		gbc.gridx=0;
		gbc.gridy=0;
		abilitiesPanel.add(magicalHexButton,gbc);
		gbc.gridx=1;
		gbc.gridy=0;
		abilitiesPanel.add(magicalHexLabel,gbc);
		gbc.gridx=0;
		gbc.gridy=1;
		abilitiesPanel.add(paddleExpansionButton,gbc);
		gbc.gridx=1;
		gbc.gridy=1;
		abilitiesPanel.add(paddleExpansionLabel, gbc);

		gbc.gridx=1;
		gbc.gridy=2;
		abilitiesPanel.add(activeAbilityLabel,gbc);
		gbc.gridx=0;
		gbc.gridy=2;
		abilitiesPanel.add(activeText,gbc);
		
		Border borderLine = BorderFactory.createLineBorder(Color.black);
		abilitiesPanel.setBorder(borderLine);
		abilitiesPanel.setOpaque(false);
		
		return abilitiesPanel;
	}
	
	private JPanel pauseSavePanel() {
		JPanel pauseSavePanel = new JPanel(new GridBagLayout());
		pauseSavePanel.setSize((int) width/3, (int)height);
		
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add(new PauseScreen(getWidth()/2, getHeight()/2));
                game.switchPaused();
            }
        });
        

        gbc.gridx=0;
		gbc.gridy=0;
		pauseSavePanel.add(pauseButton,gbc);
        
		Border borderLine = BorderFactory.createLineBorder(Color.black);
		pauseSavePanel.setBorder(borderLine);
		pauseSavePanel.setOpaque(false);
		
		return pauseSavePanel;
	}
	private JPanel ymirPanel() {
		JPanel ymirPanel = new JPanel(new GridBagLayout());
		ymirPanel.setSize((int) width/3, (int)height);
		
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		try {
		JLabel ymir = new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource("/ui/assets/Ymir.png"))));
		ymirLabel = new JLabel("");
		gbc.gridx=0;
		gbc.gridy=0;
		ymirPanel.add(ymir,gbc);
		gbc.gridx=1;
		gbc.gridy=0;
		ymirPanel.add(ymirLabel,gbc);
		}
		catch (Exception e){
            throw new RuntimeException();
        }




		Border borderLine = BorderFactory.createLineBorder(Color.black);
		ymirPanel.setBorder(borderLine);
		ymirPanel.setOpaque(false);
		
		return ymirPanel;
	}
	public void setLives() {
		//TODO Get lives info from gameboard?
		livesLabel.setText(Integer.valueOf(game.getLives()).toString());
	}
	
	public void setScore() {
		//TODO Get score information from game?
		scoreLabel.setText(df.format(Double.valueOf(game.getScore())).toString());
	}

	public void setTime() {
		//TODO Get score information from game?
		timeLabel.setText(Double.valueOf(game.getTime()).toString());
	}
	
	public void setAbilityLabels() {
		//TODO Get avaliable ability numbers and update
		int chanceCounter = 0; int hexCounter =0; int expansionCounter =0;int unstopCounter=0;
		List<AbilityType> availableAbilties = game.getAvailableAbilities();
		for (AbilityType a :  availableAbilties) {
			
			if (a == AbilityType.MagicalHexAbility) {
				hexCounter +=1;
			}
			if (a == AbilityType.PaddleExpansionAbility) {
				expansionCounter +=1;
			}
			
			magicalHexLabel.setText(Integer.valueOf(hexCounter).toString());
			paddleExpansionLabel.setText(Integer.valueOf(expansionCounter).toString());

			
		}

	}
	public void setActiveAbility(Ability a) {
		
		if (a instanceof MagicalHexAbility) {
			activeAbility.add("Magical Hex");
		}
		if (a instanceof UnstoppableBallAbility) {
			activeAbility.add("Unstoppable Ball");
			
		}if (a instanceof PaddleExpansionAbility) {
			activeAbility.add("Paddle Expansion");
		}
		 
		activeAbilityLabel.setText(activeAbility.toString());
		if (a instanceof HollowPurpleAbility) {
			ymirLabel.setText("Hollow Purple Ability");
		}
		if (a instanceof DoubleAccelAbility) {
			ymirLabel.setText("Double Accel Ability");
			
		}if (a instanceof InfiniteVoidAbility) {
			ymirLabel.setText("Infinite Void Ability");
		}
		
		
	}
	public void removeAbility(Ability a) {
		
		if (a instanceof MagicalHexAbility) {
			activeAbility.remove("Magical Hex");
		}
		if (a instanceof UnstoppableBallAbility) {
			activeAbility.remove("Unstoppable Ball");
			
		}if (a instanceof PaddleExpansionAbility) {
			activeAbility.remove("Paddle Expansion");
		}
		activeAbilityLabel.setText(activeAbility.toString());
		if (a instanceof HollowPurpleAbility) {
			ymirLabel.setText("");
		}
		if (a instanceof DoubleAccelAbility) {
			ymirLabel.setText("");
			
		}if (a instanceof InfiniteVoidAbility) {
			ymirLabel.setText("");
		}
		
	}
	

	



}
