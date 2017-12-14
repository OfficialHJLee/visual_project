package deck;

public class All {
	public int all[] = new int[53]; // 트럼프카드 전체 덱
	
	public All() {}
	
	public void devide(Player p1, Player p2) { 
		for(int i = 0; i < all.length; i++) {
			all[i] = i;
		}
		// 카드 배분
		int check = 0; // 총 카드수를 체크하기위한 변수
		
		while(check < 53) {
			int select = (int)(Math.random()); // 배분할 덱 설정
			int card = (int)(Math.random()*53); // 카드 설정
			
			if(check == 53) // 모든 카드가 배분 되었을 때 반복문 종료
				break;
			if(all[card] == -1) // 이미 나눠준 카드일 경우 반복문의 처음으로 돌아감
				continue;
			
			if(p1.getSize() == 27 || p2.getSize() == 27) { // 둘 중 한 플레이어의 덱이 전부 차있을 경우
				if(p1.getSize() == 27) {
					p2.addCard(all[card]); 
					all[card] = -1; // 트럼프카드가 분배됨을 의미
					check++;
				}
				else {
					p1.addCard(all[card]);
					all[card] = -1;
					check++;
				}
			}
			else { // 두 플레이어 모두 덱이 전부 차있지 않을 경우
				if(select == 0) { 
					p1.addCard(all[card]);
					all[card] = -1;
					check++;
				}
				else {
					p2.addCard(all[card]);
					all[card] = -1;
					check++;
				}
			}
		}
	}
}
