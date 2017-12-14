package system;

import deck.*;

public class Select extends Throw {

	public void select_com(Player com, Player player) {
		int sel_com = (int) (Math.random() * player.getSize()); // ���� ī�带 ���ϱ����� ���� ����

		com.addCard(player.getCard(sel_com)); // ���õ� �迭�� ī�带 ��ǻ�� �迭�� ����
		player.throwCard(sel_com); // ���õ� �迭�� ī�带 �÷��̾� �迭���� ����

		check(com); // ������ ī�尡 �ִ��� �˻�
		shuffle(com); // ī�弯��
	}

	public void shuffle(Player p) { // ī�带 ���� �޼ҵ�
		int rand;
		int temp;
		for (int i = 0; i < p.getSize(); i++) {
			rand = (int) (Math.random() * (p.getSize() - 1));
			temp = p.getCard(i);
			p.setCard(i, p.getCard(rand));
			p.setCard(rand, temp);
		}
	}
	
	public void Deckprint(Player com, Player player) {
		System.out.print("com : ");
		com.secretPrint();
		
		System.out.print("player : ");
		player.printDeck();
		
		System.out.print("throw : ");
		
		System.out.println("");
	}

}
