import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicBorders;

public class InstructionsPanel extends JPanel implements ActionListener {
	private main w;
	private BufferedImage player,health,shield,rock;

	public InstructionsPanel(main w) {
		setBackground(Color.GRAY);
		this.w = w;
		JButton button = new JButton("Back");
		button.setBackground(Color.WHITE);
		button.setBorder(new BasicBorders.ButtonBorder(Color.RED, Color.RED, Color.RED, Color.RED));
		button.addActionListener(this);
		add(button);
		try {
			player = ImageIO.read(new File("player_walk.gif"));
			health = ImageIO.read(new File("health.png"));
			shield = ImageIO.read(new File("shield.gif"));
			rock = ImageIO.read(new File("rock.png")); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Image player = new ImageIcon("player_walk.gif").getImage();

		g.setFont(new Font("Monospaced Plain", Font.PLAIN, 17));
		g.setColor(Color.BLACK);
		g.drawString("You are an unlucky man caught in a disaster!", 50, 75);
		g.drawString("Use the left and right arrow keys to avoid certain death!", 50, 95);
		g.drawString("There are shields and health too to help you survive", 50, 115);
		g.drawString("longer. Try to survive for as long as you can! ", 50, 135);

		g.drawImage(player, 60, 300, 50, 50, null);
		g.drawString("You", 250, 350);
		
		g.drawImage(health, 60, 400, 50, 50, null);
		g.drawString("Your Health", 250, 450);
		
		g.drawImage(shield, 60, 500, 50, 50, null);
		g.drawString("Shields", 250, 550);
		
		g.drawImage(rock, 60, 600, 50, 50, null);
		g.drawString("Rocks", 250, 650);

	}

	public void actionPerformed(ActionEvent e) {

		String comStr = e.getActionCommand();
		if (comStr.equals("Back"))
			w.changePanel("1");
	}

}
