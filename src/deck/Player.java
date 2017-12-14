package deck;

import java.util.ArrayList;

public class Player {
	public ArrayList<Integer> player = new ArrayList<Integer>();
	
	public Player() {}
	
	public int getSize() { // 덱의 크기를 반환해주는 메소드
		return player.size();
	}
	public void addCard(int card) { // 덱에 카드를 더하는 메소드
		player.add(card);
	}
	public int getCard(int card) { // 카드의 정보를 얻는 메소드
		return player.get(card);
	}
	public void throwCard(int card) { // 카드를 버리는 메소드
		player.remove(card);
	}
	public void setCard(int now, int newCard) { // 카드를 바꾸는 메소드
		player.set(now, newCard);
	}
	
	public void printDeck() { // 덱을 보여줌
		for(int i = 0; i < player.size() ; i++) { 
			System.out.print(player.get(i) + " ");
		}
		System.out.println("");
	}
	public void secretPrint() { // 덱을 비공개로 인덱스만 보여줌
		for(int i = 0; i < player.size(); i++) { 
			System.out.print((i+1) + " ");
		}
		System.out.println("");
	}
}
