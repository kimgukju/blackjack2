package blackJack;

import java.util.Scanner;

public class BlackJack {

	public void play() {
		
			System.out.println("☆★☆★☆★☆★ 블랙잭 게임을 시작하겠습니다 ★☆★☆★☆★☆");
			
			Dealer dealer = new Dealer();
			Gamer gamer = new Gamer();
			Rule rule = new Rule();
			CardDeck cardDeck = new CardDeck(); 
			
			initGame(cardDeck, gamer, dealer); 
			playingGame(cardDeck, gamer, dealer, rule);
			System.out.println();
			System.out.println("게임 끝");
			System.out.println();
		}
	
	private void playingGame(CardDeck cardDeck, Gamer gamer, Dealer dealer, Rule rule) {
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("----------------------------");
			System.out.println("1.뽑는다 2.멈춘다 0.게임나가기");
			String sc = scan.nextLine();
			
			if(sc.equals("1")) {
				// 게이머에게 한장 줌
				Card card = cardDeck.draw();
				gamer.getCard(card);
				dealer.printCards();
				gamer.printCards();
				
				if(rule.isBust(gamer, gamer.getSum())) {
					break; 
				}
				
			}else if(sc.equals("2")){
				// 딜러 합 확인 뒤 승패 결정
				dealer.checkDealerCards(cardDeck);
				dealer.printCards("last");
				gamer.printCards();
				
				if(rule.isBust(dealer, dealer.getSum())) {
					break;
				}else if(rule.isBust(gamer, gamer.getSum())) {
					break;
				}else {
					rule.getWinner(dealer.getSum(), gamer.getSum());
					break; 
				}				
				
			}else {
				// 게임 종료
				break;
			}
			System.out.println("----------------------------");
				
		}
	}
	
	private static final int INIT_RECEIVE_CARD_CNT = 2;
	private void initGame(CardDeck cardDeck, Gamer gamer, Dealer dealer) {
		for(int i = 0; i < INIT_RECEIVE_CARD_CNT; i++) {
			Card card = cardDeck.draw();
			gamer.getCard(card);
			
			Card card2 = cardDeck.draw();
			dealer.getCard(card2);
		}
		
		dealer.printCards();
		gamer.printCards();
	}

}
