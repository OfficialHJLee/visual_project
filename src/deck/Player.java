package deck;

import java.util.ArrayList;

public class Player {
	public ArrayList<Integer> player = new ArrayList<Integer>();
	
	public Player() {}
	
	public int getSize() { // ���� ũ�⸦ ��ȯ���ִ� �޼ҵ�
		return player.size();
	}
	public void addCard(int card) { // ���� ī�带 ���ϴ� �޼ҵ�
		player.add(card);
	}
	public int getCard(int card) { // ī���� ������ ��� �޼ҵ�
		return player.get(card);
	}
	public void throwCard(int card) { // ī�带 ������ �޼ҵ�
		player.remove(card);
	}
	public void setCard(int now, int newCard) { // ī�带 �ٲٴ� �޼ҵ�
		player.set(now, newCard);
	}
	
	public void printDeck() { // ���� ������
		for(int i = 0; i < player.size() ; i++) { 
			System.out.print(player.get(i) + " ");
		}
		System.out.println("");
	}
	public void secretPrint() { // ���� ������� �ε����� ������
		for(int i = 0; i < player.size(); i++) { 
			System.out.print((i+1) + " ");
		}
		System.out.println("");
	}
}
