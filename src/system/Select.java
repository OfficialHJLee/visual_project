package system;

import deck.*;

public class Select extends Throw {

	public void select_com(Player com, Player player) {
		int sel_com = (int) (Math.random() * player.getSize()); // 뽑을 카드를 정하기위한 난수 생성

		com.addCard(player.getCard(sel_com)); // 선택된 배열의 카드를 컴퓨터 배열에 더함
		player.throwCard(sel_com); // 선택된 배열의 카드를 플레이어 배열에서 지움

		check(com); // 동일한 카드가 있는지 검사
		shuffle(com); // 카드섞기
	}

	public void shuffle(Player p) { // 카드를 섞는 메소드
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
