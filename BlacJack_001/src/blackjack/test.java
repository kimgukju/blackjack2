package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class test {
	private List<String> deck = new ArrayList<>();
	
	private void hit(List<String> hand) {
		String card = deck.remove(deck.size() - 1);
		hand.add(card);
	}
	
	private int calculateHandScore(List<String> hand) {
		int score = 0;
		int aceCount = 0;

		for (String card : hand) {
			String rank = card.substring(0, card.length() - 1);
			if (rank.equals("A")) {
				score += 11; // Ace는 일단 11로 계산합니다.
				aceCount++;
			} else if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) {
				score += 10; // K, Q, J는 10으로 계산합니다.
			} else {
				score += Integer.parseInt(rank); // 숫자 카드는 해당 숫자로 계산합니다.
			}
		}

		// Ace가 있고 합이 21을 초과하면 Ace의 값을 1로 변경합니다.
		while (aceCount > 0 && score > 21) {
			score -= 10;
			aceCount--;
		}

		return score;
	}
	
	
	public static void main(String[] args) {
		
		
		List<String> deck = new ArrayList<>();

		String[] suits = { "♠", "♥", "♦", "♣" };
		String[] ranks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(suit + rank);
			}
		}
		System.out.println(deck.toString());

		Random rand = new Random();
		for (int i = 0; i < deck.size(); i++) {
			int j = rand.nextInt(deck.size());
			String temp = deck.get(i);
			deck.set(i, deck.get(j));
			deck.set(j, temp);
		}
		
		String card = deck.remove(deck.size() - 1);
		hand.add(card);
		
		System.out.println(deck.get(0));
		System.out.println(deck.get(1));
		
	}

}
