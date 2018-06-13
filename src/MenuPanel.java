import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicBorders;

public class MenuPanel extends JPanel implements MouseListener, ActionListener {

	private main m;
	private GamePanel g;
	private BufferedImage logo;

	public MenuPanel(main m, GamePanel g) {
		this.m = m;
		this.g = g;

		BasicBorders.ButtonBorder border = new BasicBorders.ButtonBorder(Color.RED, Color.RED, Color.RED, Color.RED);
		setBackground(Color.GRAY);
		JButton button1 = new JButton("Play");
		JButton button2 = new JButton("Instructions");
		JButton button3 = new JButton("Exit Game");
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button1.setBackground(Color.WHITE);
		button2.setBackground(Color.WHITE);
		button3.setBackground(Color.WHITE);
		button1.setBorder(border);
		button2.setBorder(border);
		button3.setBorder(border);

		add(button1);
		add(button2);
		add(button3);

		try {
			logo = ImageIO.read(new File("falling.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(logo, 175, 300, 150, 250, null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String comStr = e.getActionCommand();
		if (comStr.equals("Play")) {
			g.initialize();
			m.changePanel("2");
			m.start();
		}
		if (comStr.equals("Instructions")) {
			m.changePanel("3");
		}
		if (comStr.equals("Exit Game")) {
			System.exit(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (my > 275 && my < 325) {
			if (mx > 90 && mx < 140) {
				m.changePanel("2");
				m.start();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
