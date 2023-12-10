import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class DinoFrame extends JFrame implements KeyListener {

	ImageIcon logo = new ImageIcon("textures\\icon.jpg");

	// dino
	Icon dinoImage = new ImageIcon("textures\\dinoStand.png");
	JLabel dino = new JLabel();

	// cactus
	Icon smallCactusImage = new ImageIcon("textures\\smallCactus.png");
	JLabel smallCactus = new JLabel();
	Icon mediumCactusImage = new ImageIcon("textures\\mediumCactus.png");
	JLabel mediumCactus = new JLabel();
	Icon bigCactusImage = new ImageIcon("textures\\bigCactus.png");
	JLabel bigCactus = new JLabel();

	// clouds
	Icon cloudsImage = new ImageIcon("textures\\clouds.png");
	JLabel clouds1 = new JLabel();
	JLabel clouds2 = new JLabel();
	JLabel clouds3 = new JLabel();
	int cloudsThroughLimit;

	// bird
	Icon birdImage = new ImageIcon("textures\\bird.png");
	Icon birdFlapImage = new ImageIcon("textures\\birdFlap.png");
	JLabel aboveBird = new JLabel();
	JLabel belowBird = new JLabel();

	// timers
	Timer jumpTimer;
	Timer moveTimer;
	Timer scoreTimer;
	Timer animTimer;
	Timer cloudsTimer;
	Timer obstacleGeneratorTimer;

	// boolean variables
	boolean playing = false;
	boolean crouch = false;
	boolean jump = false;
	boolean onAir = false;
	boolean walk = false;
	boolean collided = false;
	boolean flap = true;
	boolean crouchRendered = false;
	boolean soundJump = false;

	// score
	JLabel scoreDisplay = new JLabel();
	int score;

	// entity borders
	JLabel dinoBorder = new JLabel();
	JLabel cactusBorder = new JLabel();
	JLabel birdBorder = new JLabel();
	JLabel obstacle1Border = new JLabel();
	JLabel obstacle2Border = new JLabel();
	JLabel obstacle3Border = new JLabel();
	JLabel obstacle4Border = new JLabel();
	JLabel obstacle5Border = new JLabel();

	// obstacle holder
	JLabel obstacle1 = new JLabel();
	JLabel obstacle2 = new JLabel();
	JLabel obstacle3 = new JLabel();
	JLabel obstacle4 = new JLabel();
	JLabel obstacle5 = new JLabel();

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
	int smallCactusBorderX;
	int smallCactusBorderY;
	int smallCactusBorderWidth;
	int smallCactusBorderHeight;
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

	//jump audio
	private Clip jumpSound;
	private Clip gameOverSound;
	private Clip bgmClip;
	private Clip scoreSound;
		
	//to adjust the bgm volume
	float volume = -20.0f;

	// for levels
	int velocity;
	int summonDistance;
	int additionalDistance;
	int additionalDistanceMin;
	int additionalDistanceMax;
	int backFrame;
	int obstacle;
	int obstacleLimit;
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

		// render images
		dino.setIcon(dinoImage);
		smallCactus.setIcon(smallCactusImage);
		mediumCactus.setIcon(mediumCactusImage);
		bigCactus.setIcon(bigCactusImage);
		clouds1.setIcon(cloudsImage);
		clouds2.setIcon(cloudsImage);
		clouds3.setIcon(cloudsImage);

		// clouds animation (for not to reset every play)
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
		// name
		scoreDisplay.setText("Score: 0");
		this.add(scoreDisplay);
		this.add(obstacle1);
		this.add(obstacle1Border);
		this.add(obstacle2);
		this.add(obstacle2Border);
		this.add(obstacle3);
		this.add(obstacle3Border);
		this.add(obstacle4);
		this.add(obstacle4Border);
		this.add(obstacle5);
		this.add(obstacle5Border);
		this.add(clouds1);
		this.add(clouds2);
		this.add(clouds3);
		this.setVisible(true);

		// timer
		jumpTimer = new Timer(10, new jumpTimerListener());
		moveTimer = new Timer(10, new moveTimerListener());
		scoreTimer = new Timer(200, new scoreTimerListener());
		animTimer = new Timer(150, new animTimerListener());
		cloudsTimer = new Timer(100, new cloudsTimerListener());
		obstacleGeneratorTimer = new Timer(1500, new obstacleGeneratorTimerListener());
	}

	public void initialPosition() {
		summonDistance = 1000;
		additionalDistance = 0;
		additionalDistanceMin = 0;
		additionalDistanceMax = 0;

		score = 0;
		dinoX = 35;
		dinoY = 225;
		dinoStandBorderWidth = 56;
		dinoStandBorderHeight = 60;
		dinoCrouchY = 25;
		dinoCrouchBorderWidth = 80;
		dinoCrouchBorderHeight = 34;
		dinoJumpLimit = 30;

		cactusX = 900; // black sheep (i changed cactusx to summon distance)
		cactusY = 217;
		smallCactusBorderX = summonDistance + 27;
		smallCactusBorderY = cactusY + 40;
		smallCactusBorderWidth = 35;
		smallCactusBorderHeight = 30;

		mediumCactusBorderX = summonDistance + 50;
		mediumCactusBorderY = cactusY + 31;
		mediumCactusBorderWidth = 32;
		mediumCactusBorderHeight = 50;

		bigCactusBorderX = summonDistance + 58;
		bigCactusBorderY = cactusY + 25;
		bigCactusBorderWidth = 25;
		bigCactusBorderHeight = 50;

		velocity = 4;
		cloudsThroughLimit = -70;
		backFrame = -100;
		birdX = -100;
		birdY = 100;
		birdBorderX = summonDistance + 43;
		birdBorderY = 110;
		birdBorderWidth = 50;
		birdBorderHeight = 25;
		obstacle = 1;
		obstacleLimit = 2;

		// dino
		dino.setBounds(dinoX, dinoY, 94, 64);
		dinoBorder.setBounds(dinoX, dinoY, dinoStandBorderWidth, dinoStandBorderHeight);

		// cactus
		smallCactus.setBounds(birdX, cactusY - 8, 94, 94);
		mediumCactus.setBounds(birdX, cactusY - 8, 94, 94);
		bigCactus.setBounds(cactusX, cactusY, 94, 94);
		cactusBorder.setBounds(backFrame, bigCactusBorderY, bigCactusBorderWidth, bigCactusBorderHeight);

		// bird

		aboveBird.setIcon(birdImage);
		aboveBird.setBounds(birdX, birdY, 94, 64);
		belowBird.setIcon(birdImage);
		belowBird.setBounds(birdX, birdY + 105, 94, 64);
		birdBorder.setBounds(birdX, birdY, birdBorderWidth, birdBorderHeight);

		// random obstacles
		obstacle1Border.setBounds(backFrame, backFrame, 0, 0);
		obstacle1.setBounds(obstacle1Border.getBounds());
		obstacle2Border.setBounds(backFrame, backFrame, 0, 0);
		obstacle2.setBounds(obstacle2Border.getBounds());
		obstacle3Border.setBounds(backFrame, backFrame, 0, 0);
		obstacle3.setBounds(obstacle3Border.getBounds());
		obstacle4Border.setBounds(backFrame, backFrame, 0, 0);
		obstacle4.setBounds(obstacle4Border.getBounds());
		obstacle5Border.setBounds(backFrame, backFrame, 0, 0);
		obstacle5.setBounds(obstacle5Border.getBounds());
	    
		//bgm music
				try {
					File bgmsfx = new File("sfx\\xmasBgm.wav");
					AudioInputStream bgmStream = AudioSystem.getAudioInputStream(bgmsfx);
				    bgmClip = AudioSystem.getClip();
				    bgmClip.open(bgmStream);
				} catch (Exception ex) {
				    ex.printStackTrace();
				}
			}
			
			private void setVolume(float volume) {
			    if (bgmClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
			        FloatControl gainControl = (FloatControl) bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
			        gainControl.setValue(volume);
			    }
			}
			
			private void playBackgroundMusic() {
			    try {
			        bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
			}

			private void stopBackgroundMusic() {
			    bgmClip.stop();
			
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
				setVolume(volume);
				playBackgroundMusic();
				cloudsTimer.start();
				moveTimer.start();
				scoreTimer.start();
				animTimer.start();
				obstacleGeneratorTimer.start();
			} else {
				dino.setIcon(new javax.swing.ImageIcon("textures\\dinoStand.png"));
				jumpTimer.start();
				if (!soundJump) {
					try {
						File jumpsfx = new File("sfx\\jump.wav");
						AudioInputStream musicStream = AudioSystem.getAudioInputStream(jumpsfx);
						jumpSound = AudioSystem.getClip();
						jumpSound.open(musicStream);
						jumpSound.start();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				soundJump = true;
				dinoBorder.setBounds(dinoX, dinoY, dinoStandBorderWidth, dinoStandBorderHeight);
			}
			break;
		case 'd':
			dinoBorder.setBorder(new LineBorder(Color.RED));
			cactusBorder.setBorder(new LineBorder(Color.RED));
			birdBorder.setBorder(new LineBorder(Color.RED));
			obstacle1Border.setBorder(new LineBorder(Color.YELLOW));
			obstacle2Border.setBorder(new LineBorder(Color.GREEN));
			obstacle3Border.setBorder(new LineBorder(Color.PINK));
			obstacle4Border.setBorder(new LineBorder(Color.CYAN));
			obstacle5Border.setBorder(new LineBorder(Color.MAGENTA));
			break;
		case 'g':
			score = 99;
			break;
		case 'q':
			System.exit(0);
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (playing) {
			switch (e.getKeyCode()) {
			case 38:
				// up button
				if (!playing) {
					jumpTimer.stop();
					dino.setLocation(dino.getX(), dino.getY());
					dinoBorder.setLocation(dinoBorder.getX(), dinoBorder.getY());
					reset();
					playing = true;
					initialPosition();
					cloudsTimer.start();
					moveTimer.start();
					scoreTimer.start();
					animTimer.start();
					obstacleGeneratorTimer.start();
				} else {
					dino.setIcon(new javax.swing.ImageIcon("textures\\dinoStand.png"));
					jumpTimer.start();
					if (!soundJump) {
						try {
							File jumpsfx = new File("sfx\\jump.wav");
							AudioInputStream musicStream = AudioSystem.getAudioInputStream(jumpsfx);
							jumpSound = AudioSystem.getClip();
							jumpSound.open(musicStream);
							jumpSound.start();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					soundJump = true;
					dinoBorder.setBounds(dinoX, dinoY, dinoStandBorderWidth, dinoStandBorderHeight);
				}
				break;
			case 40:
				// down button
				if (!onAir || jump) {
					crouch = true;
					if (!onAir) {
						if (!crouchRendered) {
							dino.setIcon(new javax.swing.ImageIcon("textures\\dinoDuck.png"));
							crouchRendered = true;
						}
						dinoBorder.setBounds(dinoX, dino.getY() + dinoCrouchY, dinoCrouchBorderWidth,
								dinoCrouchBorderHeight);
					}
				} else if (onAir && dino.getY() < 130) {
					jump = true;
				}
				if (crouch && !onAir) {
					if (!crouchRendered) {
						dino.setIcon(new javax.swing.ImageIcon("textures\\dinoDuck.png"));
						crouchRendered = true;
					}
				}
				break;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (crouch && playing) {
			crouch = false;
			crouchRendered = false;
			dino.setIcon(new javax.swing.ImageIcon("textures\\dinoStand.png"));
			dinoBorder.setBounds(dinoX, dinoY, dinoStandBorderWidth, dinoStandBorderHeight);
		}
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
					soundJump = false;
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

	private class moveTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			obstacle1.setLocation(obstacle1.getX() - velocity, obstacle1.getY());
			obstacle1Border.setLocation(obstacle1Border.getX() - velocity, obstacle1Border.getY());
			obstacle2.setLocation(obstacle2.getX() - velocity, obstacle2.getY());
			obstacle2Border.setLocation(obstacle2Border.getX() - velocity, obstacle2Border.getY());
			obstacle3.setLocation(obstacle3.getX() - velocity, obstacle3.getY());
			obstacle3Border.setLocation(obstacle3Border.getX() - velocity, obstacle3Border.getY());
			obstacle4.setLocation(obstacle4.getX() - velocity, obstacle4.getY());
			obstacle4Border.setLocation(obstacle4Border.getX() - velocity, obstacle4Border.getY());
			obstacle5.setLocation(obstacle5.getX() - velocity, obstacle5.getY());
			obstacle5Border.setLocation(obstacle5Border.getX() - velocity, obstacle5Border.getY());
			if (!crouch) {
				dinoBorder.setLocation(dino.getX(), dino.getY());
			}

			// collision for random obstacle
			if (checkCollision(dinoBorder, obstacle1Border) || checkCollision(dinoBorder, obstacle2Border)
					|| checkCollision(dinoBorder, obstacle3Border) || checkCollision(dinoBorder, obstacle4Border)
					|| checkCollision(dinoBorder, obstacle5Border)) {
				if (crouch) {
					dinoBorder.setBounds(dinoX, dino.getY() + dinoCrouchY, dinoCrouchBorderWidth,
							dinoCrouchBorderHeight);
				} else {
					dinoBorder.setLocation(dino.getX(), dino.getY());
				}
				dino.setLocation(dino.getX(), dino.getY());
				playing = false;
				collided = true;
				moveTimer.stop();
				scoreTimer.stop();
				animTimer.stop();
				cloudsTimer.stop();
				obstacleGeneratorTimer.stop();
				obstacleGenerator.clear();
				stopBackgroundMusic();
				playGameOverSound();
			}
		}
			private void playGameOverSound() {
		        try {
		        	File gameOversfx = new File("sfx\\gameOver.wav");
		        	AudioInputStream gameOverStream = AudioSystem.getAudioInputStream(gameOversfx);
		            gameOverSound = AudioSystem.getClip();
		            gameOverSound.open(gameOverStream);
		            gameOverSound.start();
		        } catch (Exception ex) {
		            ex.printStackTrace();
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

			// LEVELS
			// min max
			if (score % 100 == 0) {
		        playScoreUpSound();
		    }
			
			if (score >= 100 && score < 200) {
				obstacleLimit = 3;
				//additionalDistanceMin = -600;
				//additionalDistanceMax = -700;
			} else if (score >= 200 && score < 300) {
				obstacleLimit = 4;
				velocity = 5;
				//additionalDistanceMin = -600;
				//additionalDistanceMax = -700;
			} else if (score >= 300 && score < 500) {
				velocity = 6;
				obstacleLimit = 5;
				summonDistance = 900;
				smallCactusBorderX = summonDistance + 27;
				mediumCactusBorderX = summonDistance + 50;
				bigCactusBorderX = summonDistance + 58;
				birdBorderX = summonDistance + 43;
			} else if (score >= 500) {
				changeBackground();
				velocity = 7;
				if (obstacleGenerator.size() == 3) {
					obstacleGenerator.add(3);
					obstacleGenerator.add(4);
					System.out.println("Bird Activated");
				}
			}
		}

		private void playScoreUpSound() {
			try {
	        	File scoreUpsfx = new File("sfx\\scoreUp.wav");
	        	AudioInputStream scoreStream = AudioSystem.getAudioInputStream(scoreUpsfx);
	            scoreSound = AudioSystem.getClip();
	            scoreSound.open(scoreStream);
	            scoreSound.start();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	      }
			
		}
	}

	private void changeBackground() {
		this.setContentPane(new JLabel(new ImageIcon("textures\\backgroundNight.png")));
		this.add(dino);
		this.add(dinoBorder);
		// name
		this.add(scoreDisplay);
		this.add(obstacle1);
		this.add(obstacle1Border);
		this.add(obstacle2);
		this.add(obstacle2Border);
		this.add(obstacle3);
		this.add(obstacle3Border);
		this.add(obstacle4);
		this.add(obstacle4Border);
		this.add(obstacle5);
		this.add(obstacle5Border);
		this.add(clouds1);
		this.add(clouds2);
		this.add(clouds3);
	}

	private class obstacleGeneratorTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			additionalDistance = (int) (Math.random() * (additionalDistanceMax - additionalDistanceMin + 1))
					+ additionalDistanceMin;
			int random = (int) (Math.random() * ((obstacleGenerator.size() - 1) - 0 + 1));
			if (random == 0) {
				smallCactus.setLocation(summonDistance, smallCactus.getY());
				cactusBorder.setBounds(smallCactusBorderX, smallCactusBorderY, smallCactusBorderWidth,
						smallCactusBorderHeight);
				if (obstacle == 1 && obstacle1.getX() < backFrame) {
					obstacle1.setBounds(smallCactus.getBounds());
					obstacle1.setIcon(smallCactus.getIcon());
					obstacle1.setLocation(smallCactus.getLocation());
					obstacle1Border.setBounds(cactusBorder.getBounds());
				}
				if (obstacle == 2 && obstacle2.getX() < backFrame) {
					obstacle2.setBounds(smallCactus.getBounds());
					obstacle2.setIcon(smallCactus.getIcon());
					obstacle2.setLocation(smallCactus.getLocation());
					obstacle2Border.setBounds(cactusBorder.getBounds());
					obstacle2.setLocation(obstacle2.getX() + 200, obstacle2.getY());
					obstacle2Border.setLocation(obstacle2Border.getX() + 200, obstacle2Border.getY());
				}
				if (obstacle == 3 && obstacle3.getX() < backFrame) {
					obstacle3.setBounds(smallCactus.getBounds());
					obstacle3.setIcon(smallCactus.getIcon());
					obstacle3.setLocation(smallCactus.getLocation());
					obstacle3Border.setBounds(cactusBorder.getBounds());
					obstacle3.setLocation(obstacle3.getX() + 400, obstacle3.getY());
					obstacle3Border.setLocation(obstacle3Border.getX() + 400, obstacle3Border.getY());
				}
				if (obstacle == 4 && obstacle4.getX() < backFrame) {
					obstacle4.setBounds(smallCactus.getBounds());
					obstacle4.setIcon(smallCactus.getIcon());
					obstacle4.setLocation(smallCactus.getLocation());
					obstacle4Border.setBounds(cactusBorder.getBounds());
					obstacle4.setLocation(obstacle4.getX() + 600, obstacle4.getY());
					obstacle4Border.setLocation(obstacle4Border.getX() + 600, obstacle4Border.getY());
				}
				if (obstacle == 5 && obstacle5.getX() < backFrame) {
					obstacle5.setBounds(smallCactus.getBounds());
					obstacle5.setIcon(smallCactus.getIcon());
					obstacle5.setLocation(smallCactus.getLocation());
					obstacle5Border.setBounds(cactusBorder.getBounds());
					obstacle5.setLocation(obstacle5.getX() + 800, obstacle5.getY());
					obstacle5Border.setLocation(obstacle5Border.getX() + 800, obstacle5Border.getY());
				}
				smallCactus.setLocation(backFrame, smallCactus.getY());
				cactusBorder.setLocation(backFrame, cactusBorder.getY());
				System.out.println("Small Cactus");
			} else if (random == 1) {
				mediumCactus.setLocation(summonDistance - additionalDistance, mediumCactus.getY());
				cactusBorder.setBounds(mediumCactusBorderX - additionalDistance, mediumCactusBorderY,
						mediumCactusBorderWidth, mediumCactusBorderWidth);
				if (obstacle == 1 && obstacle1.getX() < backFrame) {
					obstacle1.setBounds(mediumCactus.getBounds());
					obstacle1.setIcon(mediumCactus.getIcon());
					obstacle1.setLocation(mediumCactus.getLocation());
					obstacle1Border.setBounds(cactusBorder.getBounds());
				}
				if (obstacle == 2 && obstacle2.getX() < backFrame) {
					obstacle2.setBounds(mediumCactus.getBounds());
					obstacle2.setIcon(mediumCactus.getIcon());
					obstacle2.setLocation(mediumCactus.getLocation());
					obstacle2Border.setBounds(cactusBorder.getBounds());
					obstacle2.setLocation(obstacle2.getX() + 200, obstacle2.getY());
					obstacle2Border.setLocation(obstacle2Border.getX() + 200, obstacle2Border.getY());
				}
				if (obstacle == 3 && obstacle3.getX() < backFrame) {
					obstacle3.setBounds(mediumCactus.getBounds());
					obstacle3.setIcon(mediumCactus.getIcon());
					obstacle3.setLocation(mediumCactus.getLocation());
					obstacle3Border.setBounds(cactusBorder.getBounds());
					obstacle3.setLocation(obstacle3.getX() + 400, obstacle3.getY());
					obstacle3Border.setLocation(obstacle3Border.getX() + 400, obstacle3Border.getY());
				}
				if (obstacle == 4 && obstacle4.getX() < backFrame) {
					obstacle4.setBounds(mediumCactus.getBounds());
					obstacle4.setIcon(mediumCactus.getIcon());
					obstacle4.setLocation(mediumCactus.getLocation());
					obstacle4Border.setBounds(cactusBorder.getBounds());
					obstacle4.setLocation(obstacle4.getX() + 600, obstacle4.getY());
					obstacle4Border.setLocation(obstacle4Border.getX() + 600, obstacle4Border.getY());
				}
				if (obstacle == 5 && obstacle5.getX() < backFrame) {
					obstacle5.setBounds(mediumCactus.getBounds());
					obstacle5.setIcon(mediumCactus.getIcon());
					obstacle5.setLocation(mediumCactus.getLocation());
					obstacle5Border.setBounds(cactusBorder.getBounds());
					obstacle5.setLocation(obstacle5.getX() + 800, obstacle5.getY());
					obstacle5Border.setLocation(obstacle5Border.getX() + 800, obstacle5Border.getY());
				}
				mediumCactus.setLocation(backFrame, mediumCactus.getY());
				cactusBorder.setLocation(backFrame, cactusBorder.getY());
				System.out.println("Medium Cactus");
			} else if (random == 2) {
				bigCactus.setLocation(summonDistance - additionalDistance, bigCactus.getY());
				cactusBorder.setBounds(bigCactusBorderX - additionalDistance, bigCactusBorderY, bigCactusBorderWidth,
						bigCactusBorderHeight);
				if (obstacle == 1 && obstacle1.getX() < backFrame) {
					obstacle1.setBounds(bigCactus.getBounds());
					obstacle1.setIcon(bigCactus.getIcon());
					obstacle1.setLocation(bigCactus.getLocation());
					obstacle1Border.setBounds(cactusBorder.getBounds());
				}
				if (obstacle == 2 && obstacle2.getX() < backFrame) {
					obstacle2.setBounds(bigCactus.getBounds());
					obstacle2.setIcon(bigCactus.getIcon());
					obstacle2.setLocation(bigCactus.getLocation());
					obstacle2Border.setBounds(cactusBorder.getBounds());
					obstacle2.setLocation(obstacle2.getX() + 200, obstacle2.getY());
					obstacle2Border.setLocation(obstacle2Border.getX() + 200, obstacle2Border.getY());
				}
				if (obstacle == 3 && obstacle3.getX() < backFrame) {
					obstacle3.setBounds(bigCactus.getBounds());
					obstacle3.setIcon(bigCactus.getIcon());
					obstacle3.setLocation(bigCactus.getLocation());
					obstacle3Border.setBounds(cactusBorder.getBounds());
					obstacle3.setLocation(obstacle3.getX() + 400, obstacle3.getY());
					obstacle3Border.setLocation(obstacle3Border.getX() + 400, obstacle3Border.getY());
				}
				if (obstacle == 4 && obstacle4.getX() < backFrame) {
					obstacle4.setBounds(bigCactus.getBounds());
					obstacle4.setIcon(bigCactus.getIcon());
					obstacle4.setLocation(bigCactus.getLocation());
					obstacle4Border.setBounds(cactusBorder.getBounds());
					obstacle4.setLocation(obstacle4.getX() + 600, obstacle4.getY());
					obstacle4Border.setLocation(obstacle4Border.getX() + 600, obstacle4Border.getY());
				}
				if (obstacle == 5 && obstacle5.getX() < backFrame) {
					obstacle5.setBounds(bigCactus.getBounds());
					obstacle5.setIcon(bigCactus.getIcon());
					obstacle5.setLocation(bigCactus.getLocation());
					obstacle5Border.setBounds(cactusBorder.getBounds());
					obstacle5.setLocation(obstacle5.getX() + 800, obstacle5.getY());
					obstacle5Border.setLocation(obstacle5Border.getX() + 800, obstacle5Border.getY());
				}
				bigCactus.setLocation(backFrame, bigCactus.getY());
				cactusBorder.setLocation(backFrame, cactusBorder.getY());
				System.out.println("Big Cactus");
			} else if (random == 3) {
				aboveBird.setLocation(summonDistance - additionalDistance, aboveBird.getY());
				birdBorder.setBounds(birdBorderX - additionalDistance, birdBorderY, birdBorderWidth, birdBorderHeight);
				if (obstacle == 1 && obstacle1.getX() < backFrame) {
					obstacle1.setBounds(aboveBird.getBounds());
					obstacle1.setIcon(birdImage);
					obstacle1.setLocation(aboveBird.getLocation());
					obstacle1Border.setBounds(birdBorder.getBounds());
				}
				if (obstacle == 2 && obstacle2.getX() < backFrame) {
					obstacle2.setBounds(aboveBird.getBounds());
					obstacle2.setIcon(birdImage);
					obstacle2.setLocation(aboveBird.getLocation());
					obstacle2Border.setBounds(birdBorder.getBounds());
					obstacle2.setLocation(obstacle2.getX() + 200, obstacle2.getY());
					obstacle2Border.setLocation(obstacle2Border.getX() + 200, obstacle2Border.getY());
				}
				if (obstacle == 3 && obstacle3.getX() < backFrame) {
					obstacle3.setBounds(aboveBird.getBounds());
					obstacle3.setIcon(birdImage);
					obstacle3.setLocation(aboveBird.getLocation());
					obstacle3Border.setBounds(birdBorder.getBounds());
					obstacle3.setLocation(obstacle3.getX() + 400, obstacle3.getY());
					obstacle3Border.setLocation(obstacle3Border.getX() + 400, obstacle3Border.getY());
				}
				if (obstacle == 4 && obstacle4.getX() < backFrame) {
					obstacle4.setBounds(aboveBird.getBounds());
					obstacle4.setIcon(birdImage);
					obstacle4.setLocation(aboveBird.getLocation());
					obstacle4Border.setBounds(birdBorder.getBounds());
					obstacle4.setLocation(obstacle4.getX() + 600, obstacle4.getY());
					obstacle4Border.setLocation(obstacle4Border.getX() + 600, obstacle4Border.getY());
				}
				if (obstacle == 5 && obstacle5.getX() < backFrame) {
					obstacle5.setBounds(aboveBird.getBounds());
					obstacle5.setIcon(birdImage);
					obstacle5.setLocation(aboveBird.getLocation());
					obstacle5Border.setBounds(birdBorder.getBounds());
					obstacle5.setLocation(obstacle5.getX() + 800, obstacle5.getY());
					obstacle5Border.setLocation(obstacle5Border.getX() + 800, obstacle5Border.getY());
				}
				aboveBird.setLocation(backFrame, aboveBird.getY());
				birdBorder.setLocation(backFrame, birdBorder.getY());
				System.out.println("Above Bird");
			} else if (random == 4) {
				belowBird.setLocation(summonDistance - additionalDistance, belowBird.getY());
				birdBorder.setBounds(birdBorderX - additionalDistance, birdBorderY + 105, birdBorderWidth,
						birdBorderHeight);
				if (obstacle == 1 && obstacle1.getX() < backFrame) {
					obstacle1.setBounds(belowBird.getBounds());
					obstacle1.setIcon(birdImage);
					obstacle1.setLocation(belowBird.getLocation());
					obstacle1Border.setBounds(birdBorder.getBounds());
				}
				if (obstacle == 2 && obstacle2.getX() < backFrame) {
					obstacle2.setBounds(belowBird.getBounds());
					obstacle2.setIcon(birdImage);
					obstacle2.setLocation(belowBird.getLocation());
					obstacle2Border.setBounds(birdBorder.getBounds());
					obstacle2.setLocation(obstacle2.getX() + 200, obstacle2.getY());
					obstacle2Border.setLocation(obstacle2Border.getX() + 200, obstacle2Border.getY());
				}
				if (obstacle == 3 && obstacle3.getX() < backFrame) {
					obstacle3.setBounds(belowBird.getBounds());
					obstacle3.setIcon(birdImage);
					obstacle3.setLocation(belowBird.getLocation());
					obstacle3Border.setBounds(birdBorder.getBounds());
					obstacle3.setLocation(obstacle3.getX() + 400, obstacle3.getY());
					obstacle3Border.setLocation(obstacle3Border.getX() + 400, obstacle3Border.getY());
				}
				if (obstacle == 4 && obstacle4.getX() < backFrame) {
					obstacle4.setBounds(belowBird.getBounds());
					obstacle4.setIcon(birdImage);
					obstacle4.setLocation(belowBird.getLocation());
					obstacle4Border.setBounds(birdBorder.getBounds());
					obstacle4.setLocation(obstacle4.getX() + 600, obstacle4.getY());
					obstacle4Border.setLocation(obstacle4Border.getX() + 600, obstacle4Border.getY());
				}
				if (obstacle == 5 && obstacle5.getX() < backFrame) {
					obstacle5.setBounds(belowBird.getBounds());
					obstacle5.setIcon(birdImage);
					obstacle5.setLocation(belowBird.getLocation());
					obstacle5Border.setBounds(birdBorder.getBounds());
					obstacle5.setLocation(obstacle5.getX() + 800, obstacle5.getY());
					obstacle5Border.setLocation(obstacle5Border.getX() + 800, obstacle5Border.getY());
				}
				belowBird.setLocation(backFrame, belowBird.getY());
				birdBorder.setLocation(backFrame, birdBorder.getY());
				System.out.println("Below Bird");
			}
			obstacle++;
			if (obstacle == obstacleLimit) {
				obstacle = 1;
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

	private class animTimerListener implements ActionListener {
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
				// continues to obstacle5
				if (obstacle1.getIcon() == birdImage) {
					obstacle1.setIcon(birdFlapImage);
				}
				if (obstacle2.getIcon() == birdImage) {
					obstacle2.setIcon(birdFlapImage);
				}
				if (obstacle3.getIcon() == birdImage) {
					obstacle3.setIcon(birdFlapImage);
				}
				if (obstacle4.getIcon() == birdImage) {
					obstacle4.setIcon(birdFlapImage);
				}
				if (obstacle5.getIcon() == birdImage) {
					obstacle5.setIcon(birdFlapImage);
				}
				flap = false;
			} else {
				aboveBird.setIcon(new javax.swing.ImageIcon("textures\\birdFlap.png"));
				belowBird.setIcon(new javax.swing.ImageIcon("textures\\birdFlap.png"));
				if (obstacle1.getIcon() == birdFlapImage) {
					obstacle1.setIcon(birdImage);
				}
				if (obstacle2.getIcon() == birdFlapImage) {
					obstacle2.setIcon(birdImage);
				}
				if (obstacle3.getIcon() == birdFlapImage) {
					obstacle3.setIcon(birdImage);
				}
				if (obstacle4.getIcon() == birdFlapImage) {
					obstacle4.setIcon(birdImage);
				}
				if (obstacle5.getIcon() == birdFlapImage) {
					obstacle5.setIcon(birdImage);
				}
				flap = true;
			}
		}
	}
}