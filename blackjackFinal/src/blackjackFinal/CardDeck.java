package blackjackFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardDeck {
	
	public List<String> deck;
	public Player player = new Player();
	public Dealer dealer = new Dealer();
	//private int playerScore;
	//private int dealerScore;
	//private List<String> playerHand;
	//private List<String> dealerHand;
	
	public CardDeck() {
		// TODO Auto-generated constructor stub
		deck = new ArrayList<>();
		//player.Hand = new ArrayList<>();
		//dealer.Hand = new ArrayList<>();
	}
	
	
	public List<String> initializeDeck() {
		String[] suits = { "♠", "♥", "♦", "♣" };
		String[] ranks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(suit + rank);
			}
		}
		
		return deck;
	}

	public List<String> shuffleDeck() {
		Random rand = new Random();
		for (int i = 0; i < deck.size(); i++) {
			int j = rand.nextInt(deck.size());
			String temp = deck.get(i);
			deck.set(i, deck.get(j));
			deck.set(j, temp);
		}
		System.out.println("샤플 carddeck deck " + deck);
		return deck;
	}
	
	public void hit(List<String> hand) {
		String card = deck.remove(deck.size() - 1);
		hand.add(card);
	}

	public void calculateScore() {
		player.Score = calculateHandScore(player.Hand);
		dealer.Score = calculateHandScore(dealer.Hand);
	}

	
	public void initialDeal() {
		hit(player.Hand);
		hit(dealer.Hand);
		hit(player.Hand);
		hit(dealer.Hand);
		calculateScore();
	}
	
	
	
	private int calculateHandScore(List<String> hand) {
		int score = 0;
		int aceCount = 0;

		for (String card : hand) {
			String rank = card.substring(1);
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

}
