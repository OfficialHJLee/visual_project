package system;

import java.util.*;
import deck.*;

public class Throw {
	public static ArrayList<Integer> trash = new ArrayList<Integer>();

	public void check(Player p) { // 버릴카드를 검사하는 메소드
		int csize = p.getSize(); // 덱의 카드 개수를 변수에 저장
		for (int i = 0; i < csize - 1; i++) {
			for (int j = i + 1; j < csize; j++) {
				if (p.getCard(i) == 0 || p.getCard(j) == 0) // 선택된 카드가 조커(0)일 때 처음으로 돌아감
					continue;
				if ((p.getCard(i) % 13) == (p.getCard(j) % 13)) { // 13으로 나눈값의 나머지가 같으면 trash 저장 후 삭제
					trash.add(p.getCard(i));
					trash.add(p.getCard(j));
					p.throwCard(i);
					p.throwCard(j - 1); // 앞서 i가 삭제되어 index가 1이 줄었음으로 j-1번째 것 삭제
					csize = csize - 2; // 카드의 개수가 줄어든만큼 줄임
					if (i != 0)
						i = i - 1; // i가 삭제되었기 때문에 i를 빼서 다시한번 같은 자리의 값을 검사
					else
						i = 0;
					continue; // 반복문의 처음으로 돌아가서 다음 카드 검사시작
				}
			}
		}
	}
	
	public void printTrash() { // 버려진 카드 전체를 보여줌
		for(int i = 0; i < trash.size() ; i++) { 
			System.out.print(trash.get(i) + " ");
		}
		System.out.println("");
	}
	
	public int last() {
		return trash.size();
	}
}
