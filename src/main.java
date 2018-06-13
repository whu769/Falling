import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class main extends JFrame {
	JPanel cardPanel;
	private GamePanel g;
	private MenuPanel m;
	private InstructionsPanel i;
	private PausePanel p;
	private EndPanel e;

	public main() {
		g = new GamePanel(this);
		g.setBackground(new Color(17, 207, 249));

		m = new MenuPanel(this, g);
		i = new InstructionsPanel(this);
		p = new PausePanel(this,g);
		e = new EndPanel(this,g);
		
		setBounds(0, 0, 500, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cardPanel = new JPanel();
		CardLayout cl = new CardLayout();
		cardPanel.setLayout(cl);

		addKeyListener(g);
		m.addMouseListener(m);

		cardPanel.add(m, "1");
		cardPanel.add(g, "2");
		cardPanel.add(i, "3");
		cardPanel.add(p, "4");
		cardPanel.add(e, "5");

		add(cardPanel);
		changePanel("1");

		setVisible(true);
		setResizable(false);
	}

	public void start() {
		g.start();
	}

	public static void main(String[] args) {
		new main();
	}

	public void changePanel(String name) {
		((CardLayout) cardPanel.getLayout()).show(cardPanel, name);
		requestFocus();
	}
}
