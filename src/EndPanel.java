import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicBorders;

public class EndPanel extends JPanel implements ActionListener {
	private main w;
	private GamePanel g;
	
	public EndPanel(main w, GamePanel g){
		setBackground(Color.GRAY);
		this.w = w;
		this.g = g;
		JButton button = new JButton("Play Again");
		button.setBackground(Color.WHITE);
		button.setBorder(new BasicBorders.ButtonBorder(Color.RED, Color.RED, Color.RED, Color.RED));
		button.addActionListener(this);
		add(button);
		JButton button2 = new JButton("Back To Menu");
		button2.setBackground(Color.WHITE);
		button2.setBorder(new BasicBorders.ButtonBorder(Color.RED, Color.RED, Color.RED, Color.RED));
		button2.addActionListener(this);
		add(button2);
	}

	public void actionPerformed(ActionEvent e) {

		String comStr = e.getActionCommand();
		if (comStr.equals("Play Again")){
			w.changePanel("2");
			g.initialize();
			g.start();
			//g.dead = false;
		}else if(comStr.equals("Back To Menu")){
			w.changePanel("1");
		}
	}
}