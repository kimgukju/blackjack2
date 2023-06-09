package blackJack;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
	
	private static List<Card> cards; 
	private static String[] PATTERNS = {"♥", "♠", "◆", "♣"};
	private static int CARD_CNT = 13; 
	

	public CardDeck() {
		cards = this.generatedDeck();
	}
	
	private List<Card> generatedDeck() {
		List<Card> deck = new ArrayList<>();
		// 1=A, 11,12,13=J,Q,K 
		for(String pattern : PATTERNS) {
			for(int i = 1; i <= CARD_CNT; i++) {
				String number = ""; 
				int point = 0;
				number = Integer.toString(i);
				
				if(i==1) {
					number = "A";
				} else if(i==11) {
					number = "J";
				} else if(i==12) {
					number = "Q";
				} else if(i==13) {
					number = "K";
				}
				
				
				// J,Q,K는 10값 주기위해 11이상이면 10으로 주기
				if(i == 1) {
					point = 1;
				}else if(i >= 11) {
					point = 10;
				}else {
					point = i; 
				}
				
				Card card = new Card(pattern, number, point); 
				deck.add(card); 			
			}
		}
		
		return deck; 
	}
	
	public Card draw(){
        Card selectedCard = getRandomCard();
        cards.remove(selectedCard);
        return selectedCard;
    }

    private Card getRandomCard() {
        int size = cards.size();
        int select = (int)(Math.random()*size);
        return cards.get(select);
    }
	
	
	@Override 
	public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Card card : cards){
            sb.append(card.toString());
            sb.append("\n");
        }

        return sb.toString();		
	}
	
    public List<Card> getDeck() {
        return cards;
    }

   
}
