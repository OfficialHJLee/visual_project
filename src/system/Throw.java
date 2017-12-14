package system;

import java.util.*;
import deck.*;

public class Throw {
	public static ArrayList<Integer> trash = new ArrayList<Integer>();

	public void check(Player p) { // ����ī�带 �˻��ϴ� �޼ҵ�
		int csize = p.getSize(); // ���� ī�� ������ ������ ����
		for (int i = 0; i < csize - 1; i++) {
			for (int j = i + 1; j < csize; j++) {
				if (p.getCard(i) == 0 || p.getCard(j) == 0) // ���õ� ī�尡 ��Ŀ(0)�� �� ó������ ���ư�
					continue;
				if ((p.getCard(i) % 13) == (p.getCard(j) % 13)) { // 13���� �������� �������� ������ trash ���� �� ����
					trash.add(p.getCard(i));
					trash.add(p.getCard(j));
					p.throwCard(i);
					p.throwCard(j - 1); // �ռ� i�� �����Ǿ� index�� 1�� �پ������� j-1��° �� ����
					csize = csize - 2; // ī���� ������ �پ�縸ŭ ����
					if (i != 0)
						i = i - 1; // i�� �����Ǿ��� ������ i�� ���� �ٽ��ѹ� ���� �ڸ��� ���� �˻�
					else
						i = 0;
					continue; // �ݺ����� ó������ ���ư��� ���� ī�� �˻����
				}
			}
		}
	}
	
	public void printTrash() { // ������ ī�� ��ü�� ������
		for(int i = 0; i < trash.size() ; i++) { 
			System.out.print(trash.get(i) + " ");
		}
		System.out.println("");
	}
	
	public int last() {
		return trash.size();
	}
}
