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
	int value1 = -1; // 내 덱에서 선택한 카드의 값을 저장할 변수1
	int value2 = -1; // 내 덱에서 선택한 카드의 값을 저장할 변수2
	int value3 = -1; // 컴퓨터의 덱에서 선택한 카드의 값을 저장할 변수
	int check; // 차례를 저장하기위한 변수

	Player com = new Player(); // 컴퓨터의 덱 Player객체 생성
	Player player = new Player(); // 플레이어의 덱 Player객체 생성

	Card[] com_array;
	Card[] player_array;
	Card[] trash;

	Select comturn = new Select(); // 컴퓨터의 차례를 만들기위한 객체

	int throw_card[] = new int[2]; // 버린 카드를 출력하기위한 배열
	int index = 0;

	public CatchThief() {
		setTitle("도둑잡기 게임");
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

	public void ArrayDesktop() { // 카드를 프레임에 정렬하여 보여주는 메소드

		if (com.getSize() == 0) { // 컴퓨터의 카드가 0개가 되었을 때 팝업창을 띄우며 종료
			JOptionPane.showMessageDialog(null, "Lose", "Result", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		} else if (player.getSize() == 0) { // 플레이어의 카드가 0개가 되었을 때 팝업창을 띄우며 종료
			JOptionPane.showMessageDialog(null, "Win", "Result", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}

		JButton bring = new JButton(new ImageIcon("images/BringBtn.gif")); // 가져오기 버튼
		bring.setBorderPainted(false);
		bring.setLocation(1075, 215);
		bring.setSize(60, 30);
		add(bring);
		bring.addMouseListener(new MouseBring());

		int num = com.getSize() - 1;
		int buttonset = (1200 - (100 + (50 * num))) / 2;// 중앙에 카드를 배치하기위한 변수

		// 초기화 (카드 더 이상 보이지 않기)
		if (com_array != null) {
			for (int i = 0; i < com_array.length; i++) {
				com_array[i].setVisible(false);
			}
		}

		com_array = new Card[com.getSize()];

		for (int i = 0; i < com.getSize(); i++) { // 컴퓨터의 덱을 출력
			com_array[i] = new Card(new ImageIcon("images/back.jpg"));
			com_array[i].setValue(com.getCard(i));
			com_array[i].setLocation(buttonset + (i * 50), 90);
			com_array[i].setSize(100, 140);
			add(com_array[i]);
			com_array[i].addMouseListener(new ComSelectCard());
		}

		// 초기화 (카드 더 이상 보이지 않기)
		if (trash != null) {
			for (int i = 0; i < trash.length; i++) {
				trash[i].setVisible(false);
			}
		}

		// 버려진 카드의 맨 뒤 두장을 배열에 넣음
		for (int i = 0; i < 2; i++) {
			throw_card[i] = Throw.trash.get(Throw.trash.size() - (2 - i));
		}

		trash = new Card[2];

		for (int i = 0; i < 2; i++) { // 버려진 카드를 출력
			trash[i] = new Card(new ImageIcon("images/" + throw_card[i] + ".jpg"));
			trash[i].setLocation(500 + (i * 110), 285);
			trash[i].setSize(100, 140);
			add(trash[i]);
		}

		JButton next = new JButton(new ImageIcon("images/NextBtn.gif")); // 다음턴 버튼
		next.setBorderPainted(false);
		next.setLocation(1075, 346);
		next.setSize(60, 30);
		add(next);
		next.addMouseListener(new MouseNext());

		num = player.getSize() - 1;
		buttonset = (1200 - (100 + (50 * num))) / 2; // 중앙에 카드를 배치하기위한 변수

		// 초기화 (카드 더 이상 보이지 않기)
		if (player_array != null) {
			for (int i = 0; i < player_array.length; i++) {
				player_array[i].setVisible(false);
			}
		}

		player_array = new Card[player.getSize()];

		for (int i = 0; i < player.getSize(); i++) { // 플레이어의 덱을 출력하는 변수
			player_array[i] = new Card(new ImageIcon("images/" + player.getCard(i) + ".jpg"));
			player_array[i].setValue(player.getCard(i));
			player_array[i].setLocation(buttonset + (i * 50), 480);
			player_array[i].setSize(100, 140);
			add(player_array[i]);
			player_array[i].addMouseListener(new MouseSelectCard());
		}

		JButton delete = new JButton(new ImageIcon("images/ThrowBtn.gif")); // 버리기 버튼
		delete.setBorderPainted(false);
		delete.setLocation(1075, 480);
		delete.setSize(60, 30);
		add(delete);
		delete.addMouseListener(new MouseDelete());

		this.repaint();
	}

	class MouseBring extends MouseAdapter { // 가져오기버튼을 누를때의 마우스리스너

		public void mousePressed(MouseEvent e) {
			if (check == 0) { // 내 턴인지 검사
				if (value3 != -1) { // 카드를 선택했는지 검사
					for (int i = 0; i < com.getSize(); i++) {
						// 선택한 카드와 값이 같은 카드를 컴퓨터의 덱에서 내덱으로 이동
						if (com.getCard(i) == value3) {
							player.addCard(com.getCard(i));
							com.throwCard(i);
							value3 = -1;
							i--;
							check = 1;
							break;
						}
					}
				} else { // 내 턴이 아닐 때 선택한 카드 값을 초기화해줌
					value3 = -1;
				}
			}
			ArrayDesktop();
		}
	}

	// 컴퓨터의 카드를 선택하는 마우스리스너
	class ComSelectCard extends MouseAdapter {

		public void mousePressed(MouseEvent e) {

			Card selectedBtn = (Card) e.getSource();

			if (value3 != -1) { // 선택됐을때

				// 버튼 해제만 가능
				if (selectedBtn.isSelected()) {
					selectedBtn.toggleSelect();
					selectedBtn.setLocation(selectedBtn.getX(), selectedBtn.getY() - 20);

					value3 = -1;
				}

			} else { // 선택이 안됐을때

				// 버튼 선택만 가능
				if (!selectedBtn.isSelected()) {
					selectedBtn.toggleSelect();
					selectedBtn.setLocation(selectedBtn.getX(), selectedBtn.getY() + 20);

					value3 = selectedBtn.getValue();
				}
			}
		}
	}

	// 내가 버릴 카드를 선택하는 마우스리스너
	class MouseSelectCard extends MouseAdapter {

		public void mousePressed(MouseEvent e) {

			Card selectedBtn = (Card) e.getSource();

			if (value1 != -1 && value2 != -1) { // 둘 다 선택됐을때
				// 버튼 해제만 가능
				if (selectedBtn.isSelected()) {
					selectedBtn.toggleSelect();
					selectedBtn.setLocation(selectedBtn.getX(), selectedBtn.getY() + 20);

					if (selectedBtn.getValue() == value1) {
						// 선택된 카드가 value1값과 같은 경우,
						value1 = value2;
						value2 = -1;
					} else {
						// 선택된 카드가 value2 값과 같은 경우,
						value2 = -1;
					}
				}
				// 선택되지 않은 카드에는 아무런 기능도 하지 않는다.

			} else { // 그 외

				// 버튼 선택, 버튼 해제
				if (selectedBtn.isSelected()) {
					// 선텍 -> 해제
					selectedBtn.toggleSelect();
					selectedBtn.setLocation(selectedBtn.getX(), selectedBtn.getY() + 20);

					if (selectedBtn.getValue() == value1) {
						// 선택된 카드가 value1값과 같은 경우,
						value1 = value2;
						value2 = -1;
					} else {
						// 선택된 카드가 value2 값과 같은 경우,
						value2 = -1;
					}
				} else {
					// 해제 -> 선택
					selectedBtn.toggleSelect();
					selectedBtn.setLocation(selectedBtn.getX(), selectedBtn.getY() - 20);

					if (value1 == -1) {
						// 첫 번째 선택됐을 때
						value1 = selectedBtn.getValue();
					} else {
						// 두 번째 카드 선택한 경우,
						value2 = selectedBtn.getValue();
					}
				}
			}
		}
	}

	// 버리기버튼을 누를때의 마우스리스너
	class MouseDelete extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (value1 != -1 && value2 != -1) { // 두개의 카드를 선택했는지 검사

				// 카드의 번호가 같은경우
				if ((value1 % 13) == (value2 % 13)) {
					int playersize = player.getSize();
					for (int i = 0; i < playersize; i++) {

						// 선택한 카드와 값이 같은 카드를 배열에서 버림
						if (player.getCard(i) == value1) {
							Throw.trash.add(player.getCard(i));
							player.throwCard(i);
							value1 = -1;

							break;
						}
					}
					for (int i = 0; i < playersize - 1; i++) {
						// 선택한 카드와 값이 같은 카드를 배열에서 버림
						if (player.getCard(i) == value2) {
							Throw.trash.add(player.getCard(i));
							player.throwCard(i);
							value2 = -1;

							break;
						}
					}
				}

				// 카드의 번호가 다른경우
				else {
					value1 = -1;
					value2 = -1;
				}
			}
			ArrayDesktop();
		}
	}

	// 다음턴 버튼을 눌렀을 때 리스너
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
