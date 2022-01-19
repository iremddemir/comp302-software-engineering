package ui;
import domain.Game;
import domain.GameBoard;
import domain.physicalobjects.PhysicalObject;
import domain.physicalobjects.Vector;
import domain.physicalobjects.obstacles.*;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import domain.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;


public class BuildScreen extends JFrame{
	private Game game;
	private ObstacleType currentObstacle = ObstacleType.SimpleObstacle;
	private int width;
	private int height;
	private static HashMap<PhysicalObject, PhysicalObjectLabel> objectToLabelMap = new HashMap<>();
	private static HashMap<PhysicalObject, JLabel> objectToJLabelMap = new HashMap<>();
	private static HashMap<ArrayList<Integer>, PhysicalObject> gameGrid = new HashMap<>();
	private boolean isDeletingMode = false;
	private Point drawPoint;
	GridBagConstraints gbc;
	JButton simpleObstacleButton,firmObstacleButton,explosiveObstacleButton,giftObstacleButton,deleteButton,startGameButton,randomGameButton,helpButton,saveButton;
	JLabel simpleObstacleLabelNumber,firmObstacleLabelNumber,giftObstacleLabelNumber,explosiveObstacleLabelNumber;

	public BuildScreen(int width, int height) {
		super("BuildGameScreen");
		this.width = width;
		this.height = height;
		Constants.PADDLE_LENGTH = width/10;
		setBounds(0,0,width,height);
		setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		game = Game.getInstance();
		game.createGameBoard(width, height);

		gbc = new GridBagConstraints();

		JPanel buttonsPanel = new JPanel(new GridBagLayout());
		add(buttonsPanel(buttonsPanel,  width, height), BorderLayout.NORTH);

		JPanel bottomPanel = new JPanel(new GridBagLayout());
		add(bottomPanel(bottomPanel), BorderLayout.SOUTH);

		JPanel totalShowPanel = new JPanel(new GridBagLayout());
		add(totalShowPanel(totalShowPanel), BorderLayout.EAST);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				drawPoint = MouseInfo.getPointerInfo().getLocation();
				drawPoint.x += 25;
				drawPoint.y += 25;
				if(isDeletingMode) {
					removeObstacle(drawPoint);

				}else {
				addObstacle(drawPoint, currentObstacle);

				}
				repaint();
			}
		});

		Timer timer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				//Update all physical objects
				for(PhysicalObject object: objectToLabelMap.keySet()){
					updatePhysicalObjectLabel(object);
				}
				requestFocusInWindow();
			}
		});

		timer.start();

		setVisible(true);
		revalidate();
		repaint();
	}

//PANELS:
	private JPanel buttonsPanel(JPanel buttonsPanel, int width, int height) {
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		buttonsPanel.setSize((int) width/3, (int) height/6);

		//TODO : add figures of obstacles instead of texts
		simpleObstacleButton = new JButton("Simple Obstacle");
		simpleObstacleButton.setActionCommand("Simple Obstacle)");
		simpleObstacleButton.addActionListener(new ActionListener()
	    {
	        //TODO: look for action on GameAreaPanel, if exists, get location, save, update totalShowPanel

			public void actionPerformed(ActionEvent ae)
	        {
				currentObstacle = ObstacleType.SimpleObstacle;
	        }
	    });
		firmObstacleButton= new JButton("Firm Obstacle");
		firmObstacleButton.setActionCommand("FirmObstacleButton");
		firmObstacleButton.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent ae)
	        {
				currentObstacle = ObstacleType.FirmObstacle;

	        }
	    });
		explosiveObstacleButton = new JButton("Explosive Obstacle");
		explosiveObstacleButton.setActionCommand("explosiveObstacleButton");
		explosiveObstacleButton.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent ae)
	        {
				currentObstacle = ObstacleType.ExplosiveObstacle;
	        }
	    });
		giftObstacleButton = new JButton("Gift Obstacle");
		giftObstacleButton.setActionCommand("giftObstacleButton");
		giftObstacleButton.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent ae)
	        {
				currentObstacle = ObstacleType.GiftObstacle;
	        }
	    });
		deleteButton = new JButton("Delete Obstacle");
		deleteButton.setActionCommand("Delete Obstacle");
		deleteButton.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			if(!isDeletingMode) {
				isDeletingMode = true;
				deleteButton.setText("Delete Mode is on");
			}
			else {
				isDeletingMode = false;
				deleteButton.setText("Delete Obstacle");
			}
		}
	});

		gbc.gridx=0;
		gbc.gridy=0;
		buttonsPanel.add(simpleObstacleButton);

		gbc.gridx=1;
		gbc.gridy=0;
		buttonsPanel.add(firmObstacleButton);

		gbc.gridx=2;
		gbc.gridy=0;
		buttonsPanel.add(explosiveObstacleButton);

		gbc.gridx=3;
		gbc.gridy=0;
		buttonsPanel.add(giftObstacleButton);

		gbc.gridx=4;
		gbc.gridy=0;
		buttonsPanel.add(deleteButton);
		return buttonsPanel;


	}
	private JPanel bottomPanel(JPanel bottomPanel) {
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		startGameButton = new JButton("Start Game");
		startGameButton.setActionCommand("Start Game");
		bottomPanel.setSize((int) width, (int) height/6);
		startGameButton.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			if(game.isValidInventory()) {
				setVisible(false);
				new RunningScreen();
			}
			else {
				setVisible(false);
				new GameCannotStartScreen();
			}

		}
	});
		randomGameButton = new JButton("Random Game");
		randomGameButton.setActionCommand("Random Game");
		randomGameButton.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			game.randomGame();
			setVisible(false);
			new RunningScreen();
		}
	});
		helpButton = new JButton("Help Screen");
		helpButton.setActionCommand("Help Screen");
		helpButton.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			setVisible(false);
			new HelpScreen();
		}
	});
		saveButton = new JButton("Save");
		saveButton.setActionCommand("Save");
		saveButton.addActionListener(ae -> {

			game.saveGame(0);
		});


		gbc.gridx=0;
		gbc.gridy=0;
		bottomPanel.add(startGameButton);

		gbc.gridx=1;
		gbc.gridy=0;
		bottomPanel.add(randomGameButton);

		gbc.gridx=2;
		gbc.gridy=0;
		bottomPanel.add(helpButton);

		gbc.gridx=3;
		gbc.gridy=0;
		bottomPanel.add(saveButton);
		return bottomPanel;

	}


	private JPanel totalShowPanel(JPanel totalShowPanel) {
		totalShowPanel.setLayout(new BoxLayout(totalShowPanel, BoxLayout.Y_AXIS));
		totalShowPanel.setPreferredSize(new Dimension(width/6, height/6));
		try {
		JLabel simpleObstacleLabel =new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource(PATHS.SIMPLE_OBSTACLE_IMG_PATH))));
		totalShowPanel.add(simpleObstacleLabel);
		simpleObstacleLabelNumber = new JLabel("0");
		totalShowPanel.add(simpleObstacleLabelNumber);

		JLabel firmObstacleLabel =new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource(PATHS.FIRM_OBSTACLE_3_IMG_PATH))));
		firmObstacleLabelNumber = new JLabel("0");
		totalShowPanel.add(firmObstacleLabel);
		totalShowPanel.add(firmObstacleLabelNumber);

		JLabel explosiveObstacleLabel =new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource(PATHS.EXPLOSIVE_OBSTACLE_IMG_PATH))));
		explosiveObstacleLabelNumber = new JLabel("0");
		totalShowPanel.add(explosiveObstacleLabel);
		totalShowPanel.add(explosiveObstacleLabelNumber);

		JLabel giftObstacleLabel =new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource(PATHS.GIFT_OBSTACLE_IMG_PATH))));
		giftObstacleLabelNumber = new JLabel("0");
		totalShowPanel.add(giftObstacleLabel);
		totalShowPanel.add(giftObstacleLabelNumber);
		}
		catch (Exception e){
            throw new RuntimeException();
        }

		return totalShowPanel;
	}


//ADD REMOVE RELATED
	private void removeObstacle(Point removePoint) {
		int x = (int) (50*(int)(drawPoint.x/50)- (int)(40/2));
		int y = (int) (50*(int)(drawPoint.y/50)-(int)(40/2));

		ArrayList<Integer> coord = new ArrayList<Integer>();
		coord.add(x); coord.add(y);

		if(gameGrid.get(coord) != null) {
			Obstacle obstacle = (Obstacle) gameGrid.get(coord);
			obstacle.getService(0).perform(obstacle);
			removePhysicalObjectLabel(obstacle);
			updateLabelNumber((Obstacle) obstacle, -1);
		}


		/*for (PhysicalObject obstacle : objectToLabelMap.keySet()) {
			double obsX = obstacle.getLocation().getX();
			double upperX = obsX + 40;
			System.out.println(obstacle.getWidth());
			double lowerX = obsX;
			double obsY = obstacle.getLocation().getY();
			double lowerY = obsY;
			double upperY = obsY + 40;
			System.out.printf("remove point = %f,%f obsX = %f obsY = %f",x,y,obsX,obsY);
			//if there exists a object in within the clicked point
			if ((x >= lowerX && x<=upperX) && (y >= lowerY && y<=upperY)) {
				//Destroy obstacle
				obstacle.getService(0).perform(obstacle);
				removePhysicalObjectLabel(obstacle);
				updateLabelNumber((Obstacle) obstacle, -1);
				break;
			}
		}*/

	}
	private void removePhysicalObjectLabel(PhysicalObject object) {
		JLabel objectJLabel = objectToJLabelMap.get(object);
		remove(objectJLabel);
		objectToLabelMap.remove(object);
		objectToJLabelMap.remove(object);
	}
	private void addObstacle(Point drawPoint, ObstacleType type){
		int x = (int) (50*(int)(drawPoint.x/50)- (int)(40/2));
		int y = (int) (50*(int)(drawPoint.y/50)-(int)(40/2));

		ArrayList<Integer> coord = new ArrayList<Integer>();
		coord.add(x); coord.add(y);
		if(gameGrid.get(coord) == null) {
			Obstacle obstacle = game.getGameBoard().addObstacle(type, new Vector(x, y));
			addPhysicalObjectLabel(obstacle);
			updateLabelNumber(obstacle, 1);
			gameGrid.put(coord, obstacle);
		}
		/*else {
			System.out.println("another obstacle is exists");
		}*/
	}

	private void addPhysicalObjectLabel(PhysicalObject object){
		PhysicalObjectLabel objectLabel = new PhysicalObjectLabel(object);
		JLabel objectJLabel = new JLabel(new ImageIcon(objectLabel.getImage()));
		add(objectJLabel);
		objectToLabelMap.put(object, objectLabel);
		objectToJLabelMap.put(object, objectJLabel);
	}

	private static void updatePhysicalObjectLabel(PhysicalObject object){

		int x = (int) object.getLocation().getX();
		int y = (int) object.getLocation().getY();
		int height = (int) object.getHeight();
		int width = (int) object.getWidth();

		objectToJLabelMap.get(object)
				.setBounds(x, y, width, height);
	}

	private void updateLabelNumber(Obstacle obstacle, int i) {
		JLabel labelNumber ;
		if (obstacle instanceof SimpleObstacle) {
			labelNumber = simpleObstacleLabelNumber;

		}else if (obstacle instanceof FirmObstacle) {
			labelNumber = firmObstacleLabelNumber;

		}else if (obstacle instanceof ExplosiveObstacle) {
			labelNumber = explosiveObstacleLabelNumber;

		}
		else {
			labelNumber = giftObstacleLabelNumber;
		}


		int previousNumber = Integer.parseInt(labelNumber.getText());
		labelNumber.setText(Integer.valueOf(previousNumber + i).toString());
	}



}

