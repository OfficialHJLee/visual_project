package Card;

import deck.*;
import system.*;

public class Start {
	All all = new All(); // ī�� ����� ���� All��ü ����
	Throw th = new Throw(); // ī�带 ���������� Throw��ü ����
	Select select = new Select(); // ī�� ������ ���� Select��ü ����

	public void GameSet(Player com, Player player) {

		all.devide(com, player); // ī�� �й�

		th.check(com); // ��ǻ���� ����ī�� �˻�
		th.check(player); // �÷��̾��� ����ī�� �˻�
	}
}