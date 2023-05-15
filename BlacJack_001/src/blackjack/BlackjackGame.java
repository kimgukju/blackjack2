package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BlackjackGame {
	private List<String> deck;
	private List<String> playerHand;
	private List<String> dealerHand;
	private int playerScore;
	private int dealerScore;
	private Scanner scanner;

	public BlackjackGame() {
		deck = new ArrayList<>();
		playerHand = new ArrayList<>();
		dealerHand = new ArrayList<>();
		playerScore = 0;
		dealerScore = 0;
		scanner = new Scanner(System.in);
	}

	public void startGame() {
		System.out.println("블랙잭 시작");

		boolean gameOver = false;
		boolean tokenOver = false;

		initializeDeck();
		shuffleDeck();
		initialDeal();
		while (!gameOver) {
			System.out.println("플레이어 손패: " + playerHand + ", 점수: " + playerScore);
			System.out.println("딜러 손패: " + dealerHand.get(0) + ", ?");

			if (playerScore == 21 || dealerScore == 21) {
				gameOver = true;
				showResult();
				break;
			}

			System.out.println("카드를 더 받으시겠습니까? (Y/N)");
			String input = scanner.nextLine();

			if (input.equalsIgnoreCase("Y")) {
				hit(playerHand);
				calculateScore();
				if (playerScore > 21) {
					gameOver = true;
					showResult();
				}
			} else if (input.equalsIgnoreCase("N")) {
				gameOver = true;
				while (dealerScore < 17) {
					hit(dealerHand);
					calculateScore();
				}
				showResult();
			}
		}
		scanner.close();

	}

	private void initializeDeck() {
		String[] suits = { "♠", "♥", "♦", "♣" };
		String[] ranks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + suit);
			}
		}
	}

	private void shuffleDeck() {
		Random rand = new Random();
		for (int i = 0; i < deck.size(); i++) {
			int j = rand.nextInt(deck.size());
			String temp = deck.get(i);
			deck.set(i, deck.get(j));
			deck.set(j, temp);
		}
	}

	private void initialDeal() {
		hit(playerHand);
		hit(dealerHand);
		hit(playerHand);
		hit(dealerHand);
		calculateScore();
	}

	private void hit(List<String> hand) {
		String card = deck.remove(deck.size() - 1);
		hand.add(card);
	}

	private void calculateScore() {
		playerScore = calculateHandScore(playerHand);
		dealerScore = calculateHandScore(dealerHand);
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

	private void showResult() {
		System.out.println("플레이어 손패: " + playerHand + ", 점수: " + playerScore);
		System.out.println("딜러 손패: " + dealerHand + ", 점수: " + dealerScore);

		if (playerScore > 21) {
			System.out.println("User가 21을 넘겼습니다. Dealer 승!");
		} else if (dealerScore > 21) {
			System.out.println("Dealer가 21을 넘겼습니다. User 승리!");
		} else if (playerScore == dealerScore) {
			System.out.println("무승부입니다.");
		} else if (playerScore > dealerScore) {
			System.out.println("플레이어 승리!");
		} else {
			System.out.println("딜러 승리!");
		}
	}

	public static void main(String[] args) {

		BlackjackGame game = new BlackjackGame();
		game.startGame();
	}
}
