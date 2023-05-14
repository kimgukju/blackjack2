package blackJack;

import java.util.ArrayList;
import java.util.List;

public class Gamer implements Player {

	private List<Card> deck;
	private static final String NAME = "사용자";
	
	public Gamer() {
		deck = new ArrayList<>(); 
	}
	
	public String getName() {
		return NAME;
	}
	
	// 카드 받기
	@Override
	public void getCard(Card card) {
		this.deck.add(card); 
	}
	
	// 카드 오픈하기
	@Override
	public void printCards() {
		StringBuilder sb = new StringBuilder();
		sb.append("사용자의 Card : ");
		
		for(Card card : deck) {
			sb.append(card.toString());
			sb.append(" ");
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

}
