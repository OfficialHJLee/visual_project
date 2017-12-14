package Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class IntroBackImg extends JComponent {
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("images/intro.png").getImage(), 0, 0, this);
	}
}

public class Intro extends JFrame {
	public JLabel Arrow = new JLabel(new ImageIcon("images/arrow.gif"));

	public Intro() {

		setTitle("도둑잡기 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new IntroBackImg());

		Container c = getContentPane();
		c.setLayout(null);

		JButton Sbtn = new JButton(new ImageIcon("images/Start.jpg"));
		Sbtn.setBorderPainted(false);
		Sbtn.setSize(150, 35);
		Sbtn.setLocation(500, 480);
		Sbtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				new CatchThief();
			}
		});
		c.add(Sbtn);

		JButton Ebtn = new JButton(new ImageIcon("images/Exit.jpg"));
		Ebtn.setBorderPainted(false);
		Ebtn.setSize(150, 35);
		Ebtn.setLocation(500, 580);
		Ebtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				System.exit(0);
			}
		});
		c.add(Ebtn);

		Arrow.setSize(70, 50);
		Arrow.setLocation(350, 475);
		Arrow.addKeyListener(new MyKeyListener());
		c.add(Arrow);

		Arrow.setFocusable(true);
		setSize(1200, 750);
		setVisible(true);
	}

	class MyKeyListener extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode(); // 입력된 키의 키코드를 알아낸다
			
			switch (keyCode) {
				case KeyEvent.VK_UP:
				Arrow.setLocation(350, 475);
				break;

				case KeyEvent.VK_DOWN:
				Arrow.setLocation(350, 575);
				break;
			}

			if (keyCode == KeyEvent.VK_ENTER) {
				if (Arrow.getX() == 350 && Arrow.getY() == 475) {
					new CatchThief();
				}
				else {
					System.exit(0);
				}
			}
		}
	}

	public static void main(String[] args) {
		new Intro();
	}
}
