package deck;

public class All {
	public int all[] = new int[53]; // Ʈ����ī�� ��ü ��
	
	public All() {}
	
	public void devide(Player p1, Player p2) { 
		for(int i = 0; i < all.length; i++) {
			all[i] = i;
		}
		// ī�� ���
		int check = 0; // �� ī����� üũ�ϱ����� ����
		
		while(check < 53) {
			int select = (int)(Math.random()); // ����� �� ����
			int card = (int)(Math.random()*53); // ī�� ����
			
			if(check == 53) // ��� ī�尡 ��� �Ǿ��� �� �ݺ��� ����
				break;
			if(all[card] == -1) // �̹� ������ ī���� ��� �ݺ����� ó������ ���ư�
				continue;
			
			if(p1.getSize() == 27 || p2.getSize() == 27) { // �� �� �� �÷��̾��� ���� ���� ������ ���
				if(p1.getSize() == 27) {
					p2.addCard(all[card]); 
					all[card] = -1; // Ʈ����ī�尡 �й���� �ǹ�
					check++;
				}
				else {
					p1.addCard(all[card]);
					all[card] = -1;
					check++;
				}
			}
			else { // �� �÷��̾� ��� ���� ���� ������ ���� ���
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
