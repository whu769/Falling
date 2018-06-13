import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	private Person player;
	private boolean running;
	private int currentHealth;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<healthPowerUp> health;
	private ArrayList<shieldPowerUp> shield;
	private Timer clock;
	private boolean dead;
	private boolean hasShield;
	private int score;
	private int hasShieldTime;
	public static boolean pause;
	private boolean musicRunning = false;
	private Clip song;
	private int songPosition;
	private static main w;
	
//	public static void main(String[] args) {
//		new GamePanel(w);
//	}


	public GamePanel(main w) {
		super();
		this.w = w;
		initialize();
		//clock.start();
//		JFrame window = new JFrame("Falling");
//		window.setBounds(0, 0, 500, 800);
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.getContentPane().add(this);
//		this.setBackground(new Color(17, 207, 249));
//		window.addKeyListener(this);
//		window.setVisible(true);
//		window.setResizable(false);
		
		

	}
	
	public void initialize(){
		player = new Person("player_walk.gif", 0, 700, 50, 50);
		obstacles = new ArrayList<Obstacle>();
		health = new ArrayList<healthPowerUp>();
		shield = new ArrayList<shieldPowerUp>();
		dead = false;
		running = true;
		currentHealth = 100;
		hasShield = false;
		hasShieldTime = 0;
		score = 0;
		pause = false;
		songPosition = 0;
		clock = new Timer(16, this);
		if(musicRunning == false){
			music();
			musicRunning =true;
		}
		else{
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(0, 750, 500, 200);
		g.drawRect(0, 0, 100, 50);
		g.setColor(Color.RED);
		g.fillRect(0, 0, currentHealth, 50);
		g.setColor(Color.black);
		g.drawString("" + currentHealth + "/" + 100, 0, 25);

		player.draw(g, null);

		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).draw(g, null);
		}

		for (int i = 0; i < health.size(); i++) {
			health.get(i).draw(g, null);
		}

		for (int i = 0; i < shield.size(); i++) {
			shield.get(i).draw(g, null);
		}

		g.drawString("Score:" + score, this.getWidth() / 2, 50);

		if (hasShield) {
			g.drawOval((int) (player.getX()), (int) (player.getY()), (int) (player.width), (int) (player.height));
		}

//		if (dead) {
//			g.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
//			g.drawString("GAME OVER", 50, 375);
//		}

	}

	public void addHealth() {
		int randXPos = (int) (Math.random() * 500 + 1);

		health.add(new healthPowerUp("health.png", randXPos, 0, 20, 20));
	}
	
	public void start(){
		clock.start();
	}

	public void generateHealth() {
		while (running && health.size() <= 1) {
			addHealth();
		}
		for (int i = 0; i < health.size(); i++) {
			health.get(i).moveByAmount(0, 5);
		}
	}

	public void addShield() {
		int randXPos = (int) (Math.random() * 500 + 1);

		shield.add(new shieldPowerUp("shield.gif", randXPos, 0, 20, 20));
	}

	public void generateShield() {
		while (running && shield.size() <= 1 && hasShield == false) {
			addShield();
		}
		for (int i = 0; i < shield.size(); i++) {
			shield.get(i).moveByAmount(0, 5);
		}
	}

	public void addObstacle() {
		int randXPos = (int) (Math.random() * 500 + 1);
		int randWidth = (int) (Math.random() * 20 + 50);
		int randHeight = (int) (Math.random() * 35 + 50);

		obstacles.add(new Obstacle("rock.png", randXPos, 0, randWidth, randHeight));
	}

	public void generateObstacle() {
		while (running && obstacles.size() < 15) {
			addObstacle();
		}
		for (int i = 0; i < obstacles.size(); i++) {
			int speed = (int) (Math.random() * 5 + 1);
			obstacles.get(i).moveByAmount(0, speed);
		}
	}

	public void updateObjects() {
		for (int i = 0; i < obstacles.size(); i++) {
			if (obstacles.get(i).getY() > 800) {
				obstacles.remove(i);
			}
		}

		for (int i = 0; i < health.size(); i++) {
			if (health.get(i).getY() > 800) {
				health.remove(i);
			}
		}

		for (int i = 0; i < shield.size(); i++) {
			if (shield.get(i).getY() > 800) {
				shield.remove(i);
			}
		}

	}

	public void hitRegister() {
		for (int i = 0; i < obstacles.size(); i++) {
			boolean hits = obstacles.get(i).intersects(player);
			// int rockLeftX = (int) obstacles.get(i).getMinX();
			// int rockRightX = (int) (obstacles.get(i).getMaxX());
			// int rockLowerY = (int) (obstacles.get(i).getMaxY());
			// int playerLeftX = (int) player.getMinX();
			// int playerRightX = (int) (player.getMaxX());
			// int playerUpperY = (int) (player.getMinY());
			// if (rockLeftX > playerLeftX && rockLeftX < playerRightX &&
			// rockLowerY > playerUpperY
			// || rockLeftX < playerLeftX && playerRightX < rockRightX &&
			// rockLowerY > playerUpperY) {
			if (hits) {
				if (hasShield) {
					currentHealth -= 0;
					obstacles.remove(i);
				} else {
					currentHealth -= 10;
					obstacles.remove(i);
					// System.out.println("Hit!");
				}
				if (currentHealth == 0) {
					clock.stop();
					dead = true;
				}
			} else {
				// System.out.println("Miss!");
			}
		}

		for (int i = 0; i < health.size(); i++) {
			boolean hits = health.get(i).intersects(player);
			if (hits) {
				System.out.println("Health acquired!");
				if (currentHealth < 100) {
					currentHealth += 10;
				} else if (currentHealth == 100) {
					currentHealth = 100;
				} else {
				}
				health.remove(i);
			}
		}

		for (int i = 0; i < shield.size(); i++) {
			boolean hits = shield.get(i).intersects(player);
			if (hits) {
				System.out.println("Shield acquired!");
				shield.remove(i);
				hasShield = true;
			}
		}

	}

	public void music() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("Chill-house-music-loop-116-bpm.wav"));
			song = AudioSystem.getClip();
			song.open(audioInputStream);
			song.setMicrosecondPosition(songPosition);
			song.loop(Clip.LOOP_CONTINUOUSLY);
			song.start();
			
		} catch(Exception e){
			
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		generateObstacle();
		generateHealth();
		generateShield();
		hitRegister();
		updateObjects();
		repaint();
		score += 1;
		if (hasShield) {
			hasShieldTime += 1;
		}

		if (hasShieldTime >= 500) {
			hasShield = false;
			hasShieldTime = 0;
		}
		
		if(dead){
			clock.stop();
			song.stop();
			musicRunning =false;
			w.changePanel("5");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (dead || pause) {
			return;
		}
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			player.moveByAmount(-5, 0);
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			player.moveByAmount(5, 0);
		}
		repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ENTER && pause == false && dead == false) {
			clock.stop();
			pause = true;
			w.changePanel("4");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
