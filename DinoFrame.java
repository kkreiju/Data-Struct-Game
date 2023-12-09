import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class DinoFrame extends JFrame implements KeyListener {

	ImageIcon logo = new ImageIcon("textures\\icon.png");
	
	//dino
	Icon dinoImage = new ImageIcon("textures\\dinoStand.png");
	JLabel dino = new JLabel();
	
	//cactus
	Icon mediumCactusImage = new ImageIcon("textures\\mediumCactus.png");
	JLabel mediumCactus = new JLabel();
	Icon bigCactusImage = new ImageIcon("textures\\bigCactus.png");
	JLabel bigCactus = new JLabel();
	
	//clouds
	Icon cloudsImage = new ImageIcon("textures\\clouds.png");
	JLabel clouds1 = new JLabel();
	JLabel clouds2 = new JLabel();
	JLabel clouds3 = new JLabel();
	int cloudsThroughLimit;
	
	//bird
	Icon birdImage = new ImageIcon("textures\\bird.png");
	JLabel aboveBird = new JLabel();
	JLabel belowBird = new JLabel();

	//timers
	Timer jumpTimer;
	Timer cactusTimer;
	Timer scoreTimer;
	Timer dinoRunAnimTimer;
	Timer cloudsTimer;
	Timer obstacleGeneratorTimer;

	//boolean variables
	boolean playing = false;
	boolean crouch = false;
	boolean jump = false;
	boolean onAir = false;
	boolean walk = false;
	boolean collided = false;
	boolean keybindInput = false;
	boolean flap = true;
	
	//score
	JLabel scoreDisplay = new JLabel();
	int score;

	// entity borders
	JLabel dinoBorder = new JLabel();
	JLabel cactusBorder = new JLabel();
	JLabel birdBorder = new JLabel();

	// variable values
	// dino
	int dinoX;
	int dinoY;
	int dinoStandBorderWidth;
	int dinoStandBorderHeight;
	int dinoCrouchY;
	int dinoCrouchBorderWidth;
	int dinoCrouchBorderHeight;
	int dinoJumpLimit;

	// cactus
	int cactusX;
	int cactusY;
	int mediumCactusBorderX;
	int mediumCactusBorderY;
	int mediumCactusBorderWidth;
	int mediumCactusBorderHeight;
	int bigCactusBorderX;
	int bigCactusBorderY;
	int bigCactusBorderWidth;
	int bigCactusBorderHeight;

	// bird
	int birdX;
	int birdY;
	int birdBorderX;
	int birdBorderY;
	int birdBorderWidth;
	int birdBorderHeight;

	int velocity;
	ArrayList<Integer> obstacleGenerator = new ArrayList<Integer>();

	// reminders
	// PLEASE PRESS JUMP TO PLAY
	DinoFrame() {
		// general options
		this.setTitle("Dino Jump!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(850, 450);
		this.setLayout(null);
		this.setLocationRelativeTo(null);

		// score
		scoreDisplay.setBounds(750, 0, 70, 20);
		scoreDisplay.setForeground(Color.WHITE);

		//render images
		dino.setIcon(dinoImage);
		mediumCactus.setIcon(mediumCactusImage);
		bigCactus.setIcon(bigCactusImage);
		clouds1.setIcon(cloudsImage);
		clouds2.setIcon(cloudsImage);
		clouds3.setIcon(cloudsImage);
		
		//clouds animation (for not to reset every play)
		clouds1.setBounds(700, 35, 94, 94);
		clouds2.setBounds(200, -10, 94, 94);
		clouds3.setBounds(500, 60, 94, 94);

		initialPosition();

		// background
		this.setContentPane(new JLabel(new ImageIcon("textures\\background.png")));
		this.setIconImage(logo.getImage());
		this.addKeyListener(this);

		// add labels to frame
		this.add(dino);
		this.add(dinoBorder);
		//name
		scoreDisplay.setText("Score: 0");
		this.add(scoreDisplay);
		this.add(mediumCactus);
		this.add(bigCactus);
		this.add(cactusBorder);
		this.add(aboveBird);
		this.add(belowBird);
		this.add(birdBorder);
		this.add(clouds1);
		this.add(clouds2);
		this.add(clouds3);
		this.setVisible(true);

		//timer
		jumpTimer = new Timer(10, new jumpTimerListener());
		cactusTimer = new Timer(10, new cactusListener());
		scoreTimer = new Timer(200, new scoreTimerListener());
		dinoRunAnimTimer = new Timer(100, new dinoRunAnimTimerListener());
		cloudsTimer = new Timer(100, new cloudsTimerListener());
		obstacleGeneratorTimer = new Timer(5000, new obstacleGeneratorTimerListener());
	}

	public void initialPosition() {
		score = 0;
		dinoX = 35;
		dinoY = 225;
		dinoStandBorderWidth = 56;
		dinoStandBorderHeight = 60;
		dinoCrouchY = 25;
		dinoCrouchBorderWidth = 80;
		dinoCrouchBorderHeight = 34;
		dinoJumpLimit = 30;
		cactusX = 900;
		cactusY = 217;
		mediumCactusBorderX = cactusX + 50;
		mediumCactusBorderY = cactusY + 31;
		mediumCactusBorderWidth = 37;
		mediumCactusBorderHeight = 50;
		bigCactusBorderX = cactusX + 58;
		bigCactusBorderY = cactusY + 25;
		bigCactusBorderWidth = 25;
		bigCactusBorderHeight = 50;
		velocity = 4;
		cloudsThroughLimit = -70;
		birdX = -100;
		birdY = 100;
		birdBorderX = 943;
		birdBorderY = 110;
		birdBorderWidth = 50;
		birdBorderHeight = 25;

		// dino

		dino.setBounds(dinoX, dinoY, 94, 64);
		dinoBorder.setBounds(dinoX, dinoY, dinoStandBorderWidth, dinoStandBorderHeight);

		// cactus
		mediumCactus.setBounds(birdX, cactusY - 8, 94, 94);
		bigCactus.setBounds(cactusX, cactusY, 94, 94);
		
		//ang mausab sa cactus (apply the same logic for bird)
		cactusBorder.setBounds(bigCactusBorderX, bigCactusBorderY, bigCactusBorderWidth, bigCactusBorderHeight);

		// bird

		aboveBird.setIcon(birdImage);
		aboveBird.setBounds(birdX, birdY, 94, 64);
		belowBird.setIcon(birdImage);
		belowBird.setBounds(birdX, birdY + 105, 94, 64);
		
		//ang mausab sa bird
		birdBorder.setBounds(birdX, birdY, birdBorderWidth, birdBorderHeight);
	}

	@Override
	public void keyTyped(KeyEvent e) {

		switch (e.getKeyChar()) {
		case ' ':
			if (!playing) {
				jumpTimer.stop();
				dino.setLocation(dino.getX(), dino.getY());
				dinoBorder.setLocation(dinoBorder.getX(), dinoBorder.getY());
				reset();
				playing = true;
				initialPosition();
				cloudsTimer.start();
				cactusTimer.start();
				scoreTimer.start();
				dinoRunAnimTimer.start();
				obstacleGeneratorTimer.start();
			} else {
				dino.setIcon(new javax.swing.ImageIcon("textures\\dinoStand.png"));
				jumpTimer.start();
				dinoBorder.setBounds(dinoX, dinoY, dinoStandBorderWidth, dinoStandBorderHeight);
			}
			break;
		case 'd':
			dinoBorder.setBorder(new LineBorder(Color.RED));
			cactusBorder.setBorder(new LineBorder(Color.RED));
			birdBorder.setBorder(new LineBorder(Color.RED));
			break;
		case 'g':
			score = 500;
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (playing) {
			switch (e.getKeyCode()) {
			case 38:
				//up button
				if (!playing) {
					jumpTimer.stop();
					dino.setLocation(dino.getX(), dino.getY());
					dinoBorder.setLocation(dinoBorder.getX(), dinoBorder.getY());
					reset();
					playing = true;
					initialPosition();
					cloudsTimer.start();
					cactusTimer.start();
					scoreTimer.start();
					dinoRunAnimTimer.start();
					obstacleGeneratorTimer.start();
				} else {
					dino.setIcon(new javax.swing.ImageIcon("textures\\dinoStand.png"));
					jumpTimer.start();
					dinoBorder.setBounds(dinoX, dinoY, dinoStandBorderWidth, dinoStandBorderHeight);
				}
				break;
			case 40:
				//down button
				if (!onAir || jump) {
					crouch = true;
					if (!onAir) {
						dino.setIcon(new javax.swing.ImageIcon("textures\\dinoDuckLeft.png"));
						dinoBorder.setBounds(dinoX, dino.getY() + dinoCrouchY, dinoCrouchBorderWidth,
								dinoCrouchBorderHeight);
					}
				} else if (onAir && dino.getY() < 130) {
					jump = true;
				}
				if (crouch && !onAir) {
					dino.setIcon(new javax.swing.ImageIcon("textures\\dinoDuckLeft.png"));
				}
				break;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		crouch = false;
		keybindInput = false;
		dinoBorder.setBounds(dinoX, dinoY, dinoStandBorderWidth, dinoStandBorderHeight);
	}

	private class jumpTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!collided) {
				if (!jump) {
					dinoBorder.setLocation(dino.getX(), dino.getY() - 10);
					dino.setLocation(dino.getX(), dino.getY() - 10);
					onAir = true;
				} else {
					dinoBorder.setLocation(dino.getX(), dino.getY() + 7);
					dino.setLocation(dino.getX(), dino.getY() + 7);
				}

				if (dino.getY() <= dinoJumpLimit) {
					jump = true;
				} else if (dino.getY() >= dinoY) {
					dinoBorder.setLocation(dino.getX(), dinoY);
					dino.setLocation(dino.getX(), dinoY);
					dinoBorder.setBounds(dinoBorder.getX(), dinoBorder.getY(), dinoStandBorderWidth,
							dinoStandBorderHeight);
					jump = false;
					onAir = false;
					jumpTimer.stop();
				}
			}
		}
	}

	private void reset() {
		playing = false;
		crouch = false;
		jump = false;
		onAir = false;
		walk = false;
		collided = false;
		for (int i = 0; i < 3; i++) {
			obstacleGenerator.add(i);
		}
	}

	private class cactusListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mediumCactus.setLocation(mediumCactus.getX() - velocity, mediumCactus.getY());
			bigCactus.setLocation(bigCactus.getX() - velocity, bigCactus.getY());
			cactusBorder.setLocation(cactusBorder.getX() - velocity, cactusBorder.getY());
			aboveBird.setLocation(aboveBird.getX() - velocity, aboveBird.getY());
			belowBird.setLocation(belowBird.getX() - velocity, belowBird.getY());
			birdBorder.setLocation(birdBorder.getX() - velocity, birdBorder.getY());
			if (!crouch) {
				dinoBorder.setLocation(dino.getX(), dino.getY());
			}

			//collision for bird or cactus (might be temp)
			if (checkCollision(dinoBorder, cactusBorder) || checkCollision(dinoBorder, birdBorder)) {
				dino.setLocation(dino.getX(), dino.getY());
				dinoBorder.setLocation(dino.getX(), dino.getY());
				playing = false;
				collided = true;
				cactusTimer.stop();
				scoreTimer.stop();
				dinoRunAnimTimer.stop();
				cloudsTimer.stop();
				obstacleGeneratorTimer.stop();
				obstacleGenerator.clear();
			}
		}
	}

	private boolean checkCollision(JComponent comp1, JComponent comp2) {
		Rectangle bounds1 = comp1.getBounds();
		Rectangle bounds2 = comp2.getBounds();
		return bounds1.intersects(bounds2);
	}

	private class scoreTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			score++;
			scoreDisplay.setText("Score: " + score);

			//implement the obs1-5 thingy here
			if (score >= 300 && score < 500) {
				velocity = 5;
			} else if (score >= 500) {
				velocity = 6;
				if (obstacleGenerator.size() == 3) {
					obstacleGenerator.add(3);
					obstacleGenerator.add(4);
					System.out.println("Bird Activated");
				}
			}
		}
	}

	private class obstacleGeneratorTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int random = (int) (Math.random() * ((obstacleGenerator.size() - 1) - 0 + 1));
			if (random == 0) {
				bigCactus.setLocation(cactusX, bigCactus.getY());
				cactusBorder.setBounds(bigCactusBorderX, bigCactusBorderY, bigCactusBorderWidth, bigCactusBorderHeight);
				System.out.println("Small Cactus");
			} else if (random == 1) {
				mediumCactus.setLocation(cactusX, mediumCactus.getY());
				cactusBorder.setBounds(mediumCactusBorderX, mediumCactusBorderY, mediumCactusBorderWidth, mediumCactusBorderWidth);
				System.out.println("Medium Cactus");
			} else if (random == 2) {
				bigCactus.setLocation(cactusX, bigCactus.getY());
				cactusBorder.setBounds(bigCactusBorderX, bigCactusBorderY, bigCactusBorderWidth, bigCactusBorderHeight);
				System.out.println("Large Cactus");
			} else if (random == 3) {
				aboveBird.setLocation(cactusX, aboveBird.getY());
				birdBorder.setBounds(birdBorderX, birdBorderY, birdBorderWidth, birdBorderHeight);
				System.out.println("Above Bird");
			} else if (random == 4) {
				belowBird.setLocation(cactusX, belowBird.getY());
				System.out.println("Below Bird");
				birdBorder.setBounds(birdBorderX, birdBorderY + 105, birdBorderWidth, birdBorderHeight);
			}
		}
	}

	private class cloudsTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			clouds1.setLocation(clouds1.getX() - 1, clouds1.getY());
			clouds2.setLocation(clouds2.getX() - 1, clouds2.getY());
			clouds3.setLocation(clouds3.getX() - 1, clouds3.getY());
			if (clouds1.getX() < cloudsThroughLimit) {
				clouds1.setLocation(cactusX, clouds1.getY());
			} else if (clouds2.getX() < cloudsThroughLimit) {
				clouds2.setLocation(cactusX, clouds2.getY());
			} else if (clouds3.getX() < cloudsThroughLimit) {
				clouds3.setLocation(cactusX, clouds3.getY());
			}
		}
	}

	private class dinoRunAnimTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!walk && !jump && !crouch) {
				dino.setIcon(new javax.swing.ImageIcon("textures\\dinoStandLeft.png"));
				walk = true;
			} else if (walk && !jump && !crouch) {
				dino.setIcon(new javax.swing.ImageIcon("textures\\dinoStandRight.png"));
				walk = false;
			} else if (jump || onAir) {
				dino.setIcon(new javax.swing.ImageIcon("textures\\dinoStand.png"));
				walk = false;
			} else if (crouch && !walk && !onAir) {
				dino.setIcon(new javax.swing.ImageIcon("textures\\dinoDuckLeft.png"));
				walk = true;
			} else if (crouch && walk && !onAir) {
				dino.setIcon(new javax.swing.ImageIcon("textures\\dinoDuckRight.png"));
				walk = false;
			}
			if (flap) {
				aboveBird.setIcon(new javax.swing.ImageIcon("textures\\bird.png"));
				belowBird.setIcon(new javax.swing.ImageIcon("textures\\bird.png"));
				flap = false;
			} else {
				aboveBird.setIcon(new javax.swing.ImageIcon("textures\\birdFlap.png"));
				belowBird.setIcon(new javax.swing.ImageIcon("textures\\birdFlap.png"));
				flap = true;
			}
		}
	}

}