package blackjackFF;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = 0;
        int aceCount = 0;

        for (Card card : cards) {
            String rank = card.getRank();
            if (rank.equals("A")) {
                score += 11;
                aceCount++;
            } else if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) {
                score += 10;
            } else {
                score += Integer.parseInt(rank);
            }
        }

        while (aceCount > 0 && score > 21) {
            score -= 10;
            aceCount--;
        }

        return score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card).append(", ");
        }
        return sb.toString();
    }
}