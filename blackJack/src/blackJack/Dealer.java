package blackJack;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements Player {
	
	private List<Card> deck;
	private static final String NAME = "딜러";
	
	public Dealer() {
		deck = new ArrayList<>(); 
	}
	
	public String getName() {
		return NAME;
	}

	@Override
	public void getCard(Card card) {
		this.deck.add(card);
		
	}

	@Override
	public void printCards() {
		StringBuilder sb = new StringBuilder();
		sb.append("딜러의 Card : ");
				
		for(int i = 0; i < deck.size(); i++) {
			
			Card card = deck.get(i);
			if (i == 0) {
				sb.append("(?) ");
			}else {
				sb.append(card.toString());
				sb.append(" "); 
			}
			
		}
		
		System.out.println(sb.toString());
	}
	
	public void printCards(String last) {
		StringBuilder sb = new StringBuilder();
		sb.append("딜러의 Card : ");
		for(Card card : deck) {
			sb.append(card.toString());
		}
		
		System.out.println(sb.toString() + " 총합 : " + this.getSum());
	}
	
	@Override
	public int getSum() {
		int sum = 0; 
		for(Card card : deck) {
			sum += card.getPoint();
		}
		return sum; 
	}
	
	// 합 16 이하인지 확인
	private boolean isLessThan() {
		return this.getSum() <= 16;
	}
	
	// 합이 16 이하인 경우 이상으로 만들기 
	public void checkDealerCards(CardDeck cardDeck) {
		while(isLessThan()) {
			Card card = cardDeck.draw();
			this.getCard(card);
		}
	}	

}
