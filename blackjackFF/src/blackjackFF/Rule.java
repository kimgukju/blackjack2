package blackjackFF;

public class Rule {
	public void rule() {
		System.out.println("# 규칙 설명 #");
		System.out.println("1. 유저와 딜러에게 각각 5코인씩 지급");
		System.out.println("   게임 승리시 +1 코인 패배시 -1 코인");
		System.out.println("   보유 코인이 0이 되면 Game Over가 됩니다.\n");
		System.out.println("2. 시작 시 두장의 카드를 뽑습니다.   ");
		System.out.println("   보유 카드가 21이 될때까지 카드를 계속 뽑을 수 있습니다.");
		System.out.println("   보유 카드의 합이 21이 되면 승리하고, 초과하면 패배합니다.");
		System.out.println("   결과 확인시 보유 카드의 합이 21에 가까운 사람이 승리합니다.\n");
		System.out.println("3. ACE카드는 11점으로 계산합니다.   ");
		System.out.println("   보유 카드의 합이 21점을 넘으면 ACE카드는 1점으로 계산합니다.");
		System.out.println("4. J, Q, K 카드의 점수는 10점으로 계산합니다.");
		System.out.println();
	}

	public void clear() {
		System.out.print("\n".repeat(10));
	}

	

}
