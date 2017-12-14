package Card;

import deck.*;
import system.*;

public class Start {
	All all = new All(); // 카드 배분을 위한 All객체 생성
	Throw th = new Throw(); // 카드를 버리기위한 Throw객체 생성
	Select select = new Select(); // 카드 선택을 위한 Select객체 생성

	public void GameSet(Player com, Player player) {

		all.devide(com, player); // 카드 분배

		th.check(com); // 컴퓨터의 버릴카드 검사
		th.check(player); // 플레이어의 버릴카드 검사
	}
}