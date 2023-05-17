package blackjackFF;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    public List<String> deck = new ArrayList<>();

    public Deck() {
        //deck = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public void initializeDeck() {
    	String[] suits = { "♠", "♥", "♦", "♣" };
		String[] ranks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(suit+rank);
			}
		}
    }

    public void shuffleDeck() {
		Random rand = new Random();
		for (int i = 0; i < deck.size(); i++) {
			int j = rand.nextInt(deck.size());
			String temp = deck.get(i);  // card?!
			deck.set(i, deck.get(j));
			deck.set(j, temp);
		}
	}


    

//    public Card drawCard() {
//        if (cards.isEmpty()) {
//            initializeDeck();
//            shuffleDeck();
//        }
//
//        return cards.remove(cards.size() - 1);
//    }
}
