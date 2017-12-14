package Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import Card.*;
import deck.*;
import system.*;

class MainBackImg extends JComponent {
	@Override
	public void paintComponent(Graphics g) {
		super.repaint();
		super.paintComponent(g);
		g.drawImage(new ImageIcon("images/game_bg.png").getImage(), 0, 0, this);
	}
}

public class CatchThief extends JFrame {
	int value1 = -1; // �� ������ ������ ī���� ���� ������ ����1
	int value2 = -1; // �� ������ ������ ī���� ���� ������ ����2
	int value3 = -1; // ��ǻ���� ������ ������ ī���� ���� ������ ����
	int check; // ���ʸ� �����ϱ����� ����

	Player com = new Player(); // ��ǻ���� �� Player��ü ����
	Player player = new Player(); // �÷��̾��� �� Player��ü ����

	Card[] com_array;
	Card[] player_array;
	Card[] trash;

	Select comturn = new Select(); // ��ǻ���� ���ʸ� ��������� ��ü

	int throw_card[] = new int[2]; // ���� ī�带 ����ϱ����� �迭
	int index = 0;

	public CatchThief() {
		setTitle("������� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new MainBackImg());

		setLayout(null);

		Start game = new Start();
		game.GameSet(com, player);

		if (com.getSize() > player.getSize()) {
			check = 0;
		} else {
			comturn.select_com(com, player);
			check = 0;
		}

		ArrayDesktop();

		setSize(1200, 750);
		setVisible(true);
	}

	public void ArrayDesktop() { // ī�带 �����ӿ� �����Ͽ� �����ִ� �޼ҵ�

		if (com.getSize() == 0) { // ��ǻ���� ī�尡 0���� �Ǿ��� �� �˾�â�� ���� ����
			JOptionPane.showMessageDialog(null, "Lose", "Result", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		} else if (player.getSize() == 0) { // �÷��̾��� ī�尡 0���� �Ǿ��� �� �˾�â�� ���� ����
			JOptionPane.showMessageDialog(null, "Win", "Result", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}

		JButton bring = new JButton(new ImageIcon("images/BringBtn.gif")); // �������� ��ư
		bring.setBorderPainted(false);
		bring.setLocation(1075, 215);
		bring.setSize(60, 30);
		add(bring);
		bring.addMouseListener(new MouseBring());

		int num = com.getSize() - 1;
		int buttonset = (1200 - (100 + (50 * num))) / 2;// �߾ӿ� ī�带 ��ġ�ϱ����� ����

		// �ʱ�ȭ (ī�� �� �̻� ������ �ʱ�)
		if (com_array != null) {
			for (int i = 0; i < com_array.length; i++) {
				com_array[i].setVisible(false);
			}
		}

		com_array = new Card[com.getSize()];

		for (int i = 0; i < com.getSize(); i++) { // ��ǻ���� ���� ���
			com_array[i] = new Card(new ImageIcon("images/back.jpg"));
			com_array[i].setValue(com.getCard(i));
			com_array[i].setLocation(buttonset + (i * 50), 90);
			com_array[i].setSize(100, 140);
			add(com_array[i]);
			com_array[i].addMouseListener(new ComSelectCard());
		}

		// �ʱ�ȭ (ī�� �� �̻� ������ �ʱ�)
		if (trash != null) {
			for (int i = 0; i < trash.length; i++) {
				trash[i].setVisible(false);
			}
		}

		// ������ ī���� �� �� ������ �迭�� ����
		for (int i = 0; i < 2; i++) {
			throw_card[i] = Throw.trash.get(Throw.trash.size() - (2 - i));
		}

		trash = new Card[2];

		for (int i = 0; i < 2; i++) { // ������ ī�带 ���
			trash[i] = new Card(new ImageIcon("images/" + throw_card[i] + ".jpg"));
			trash[i].setLocation(500 + (i * 110), 285);
			trash[i].setSize(100, 140);
			add(trash[i]);
		}

		JButton next = new JButton(new ImageIcon("images/NextBtn.gif")); // ������ ��ư
		next.setBorderPainted(false);
		next.setLocation(1075, 346);
		next.setSize(60, 30);
		add(next);
		next.addMouseListener(new MouseNext());

		num = player.getSize() - 1;
		buttonset = (1200 - (100 + (50 * num))) / 2; // �߾ӿ� ī�带 ��ġ�ϱ����� ����

		// �ʱ�ȭ (ī�� �� �̻� ������ �ʱ�)
		if (player_array != null) {
			for (int i = 0; i < player_array.length; i++) {
				player_array[i].setVisible(false);
			}
		}

		player_array = new Card[player.getSize()];

		for (int i = 0; i < player.getSize(); i++) { // �÷��̾��� ���� ����ϴ� ����
			player_array[i] = new Card(new ImageIcon("images/" + player.getCard(i) + ".jpg"));
			player_array[i].setValue(player.getCard(i));
			player_array[i].setLocation(buttonset + (i * 50), 480);
			player_array[i].setSize(100, 140);
			add(player_array[i]);
			player_array[i].addMouseListener(new MouseSelectCard());
		}

		JButton delete = new JButton(new ImageIcon("images/ThrowBtn.gif")); // ������ ��ư
		delete.setBorderPainted(false);
		delete.setLocation(1075, 480);
		delete.setSize(60, 30);
		add(delete);
		delete.addMouseListener(new MouseDelete());

		this.repaint();
	}

	class MouseBring extends MouseAdapter { // ���������ư�� �������� ���콺������

		public void mousePressed(MouseEvent e) {
			if (check == 0) { // �� ������ �˻�
				if (value3 != -1) { // ī�带 �����ߴ��� �˻�
					for (int i = 0; i < com.getSize(); i++) {
						// ������ ī��� ���� ���� ī�带 ��ǻ���� ������ �������� �̵�
						if (com.getCard(i) == value3) {
							player.addCard(com.getCard(i));
							com.throwCard(i);
							value3 = -1;
							i--;
							check = 1;
							break;
						}
					}
				} else { // �� ���� �ƴ� �� ������ ī�� ���� �ʱ�ȭ����
					value3 = -1;
				}
			}
			ArrayDesktop();
		}
	}

	// ��ǻ���� ī�带 �����ϴ� ���콺������
	class ComSelectCard extends MouseAdapter {

		public void mousePressed(MouseEvent e) {

			Card selectedBtn = (Card) e.getSource();

			if (value3 != -1) { // ���õ�����

				// ��ư ������ ����
				if (selectedBtn.isSelected()) {
					selectedBtn.toggleSelect();
					selectedBtn.setLocation(selectedBtn.getX(), selectedBtn.getY() - 20);

					value3 = -1;
				}

			} else { // ������ �ȵ�����

				// ��ư ���ø� ����
				if (!selectedBtn.isSelected()) {
					selectedBtn.toggleSelect();
					selectedBtn.setLocation(selectedBtn.getX(), selectedBtn.getY() + 20);

					value3 = selectedBtn.getValue();
				}
			}
		}
	}

	// ���� ���� ī�带 �����ϴ� ���콺������
	class MouseSelectCard extends MouseAdapter {

		public void mousePressed(MouseEvent e) {

			Card selectedBtn = (Card) e.getSource();

			if (value1 != -1 && value2 != -1) { // �� �� ���õ�����
				// ��ư ������ ����
				if (selectedBtn.isSelected()) {
					selectedBtn.toggleSelect();
					selectedBtn.setLocation(selectedBtn.getX(), selectedBtn.getY() + 20);

					if (selectedBtn.getValue() == value1) {
						// ���õ� ī�尡 value1���� ���� ���,
						value1 = value2;
						value2 = -1;
					} else {
						// ���õ� ī�尡 value2 ���� ���� ���,
						value2 = -1;
					}
				}
				// ���õ��� ���� ī�忡�� �ƹ��� ��ɵ� ���� �ʴ´�.

			} else { // �� ��

				// ��ư ����, ��ư ����
				if (selectedBtn.isSelected()) {
					// ���� -> ����
					selectedBtn.toggleSelect();
					selectedBtn.setLocation(selectedBtn.getX(), selectedBtn.getY() + 20);

					if (selectedBtn.getValue() == value1) {
						// ���õ� ī�尡 value1���� ���� ���,
						value1 = value2;
						value2 = -1;
					} else {
						// ���õ� ī�尡 value2 ���� ���� ���,
						value2 = -1;
					}
				} else {
					// ���� -> ����
					selectedBtn.toggleSelect();
					selectedBtn.setLocation(selectedBtn.getX(), selectedBtn.getY() - 20);

					if (value1 == -1) {
						// ù ��° ���õ��� ��
						value1 = selectedBtn.getValue();
					} else {
						// �� ��° ī�� ������ ���,
						value2 = selectedBtn.getValue();
					}
				}
			}
		}
	}

	// �������ư�� �������� ���콺������
	class MouseDelete extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (value1 != -1 && value2 != -1) { // �ΰ��� ī�带 �����ߴ��� �˻�

				// ī���� ��ȣ�� �������
				if ((value1 % 13) == (value2 % 13)) {
					int playersize = player.getSize();
					for (int i = 0; i < playersize; i++) {

						// ������ ī��� ���� ���� ī�带 �迭���� ����
						if (player.getCard(i) == value1) {
							Throw.trash.add(player.getCard(i));
							player.throwCard(i);
							value1 = -1;

							break;
						}
					}
					for (int i = 0; i < playersize - 1; i++) {
						// ������ ī��� ���� ���� ī�带 �迭���� ����
						if (player.getCard(i) == value2) {
							Throw.trash.add(player.getCard(i));
							player.throwCard(i);
							value2 = -1;

							break;
						}
					}
				}

				// ī���� ��ȣ�� �ٸ����
				else {
					value1 = -1;
					value2 = -1;
				}
			}
			ArrayDesktop();
		}
	}

	// ������ ��ư�� ������ �� ������
	class MouseNext extends MouseAdapter { 
		public void mousePressed(MouseEvent e) {
			if (check == 1) {
				comturn.select_com(com, player);
				check = 0;
			}

			ArrayDesktop();
		}
	}

	public static void main(String[] args) {
		new CatchThief();
	}
}
