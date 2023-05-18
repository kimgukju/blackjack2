package blackjackFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class blackJackMain {
	private List<String> deck;
	private List<String> playerHand;
	private List<String> dealerHand;
	private int playerScore;
	private int dealerScore;
	private int playerCoin;
	private int dealerCoin;
	private Scanner scanner;

	public blackJackMain() {
		deck = new ArrayList<>();
		playerHand = new ArrayList<>();
		dealerHand = new ArrayList<>();
		playerScore = 0;
		dealerScore = 0;
		playerCoin = 5;
		dealerCoin = 5;
		scanner = new Scanner(System.in);
	}

	public void startGame() {
		boolean tokenOver;
		System.out.println("* BlackJack *");
		System.out.println("게임 시작(S) 게임 규칙(T) 게임 종료(Q)");

		while (true) {
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("S")) {
				tokenOver = false;
				break;
			} else if (input.equalsIgnoreCase("T")) {
				rule();
				System.out.println("게임 시작(S) 게임 규칙(T) 게임 종료(Q)");
			} else if (input.equalsIgnoreCase("Q")) {
				System.out.println("Game Over");
				tokenOver = true;
				break;
			}
		}

		while (!tokenOver) {
			initializeDeck();
			shuffleDeck();
			initialDeal();

			boolean gameOver = false;

			System.out.println("-".repeat(30));
			while (!gameOver) {
				System.out.println("딜러 손패1st : " + dealerHand.get(0) + ", ?");
				System.out.println("유저 손패1st : " + playerHand + ", 점수: " + playerScore);

				if (playerScore == 21 || dealerScore == 21) {
					gameOver = true;
					showResult();
					break;
				}
				System.out.println();
				System.out.println("카드 받기(Y) 결과 확인(N) 게임 종료(Q)");
				System.out.println();
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
				} else if (input.equalsIgnoreCase("Q")) {
					System.err.print("종료시 모든 코인을 잃습니다. 종료(Y) 재시작(N)");
					input = scanner.nextLine();
					if (input.equalsIgnoreCase("Y")) {
						playerCoin = 0;
						gameOver = true;
						tokenOver = true;

					} else if (input.equalsIgnoreCase("N")) {

						gameOver = true;
						System.out.print("게임을 재시작 합니다");
					}
				}
			}
			if (playerCoin == 0) {
				System.out.println();
				System.err.print("Game Over");
				tokenOver = true;

			} else if (dealerCoin == 0) {
				System.out.println();
				System.out.print("승리를 축하합니다.");
				tokenOver = true;
			}
			deck.clear();
			playerScore = 0;
			dealerScore = 0;
			playerHand.clear();
			dealerHand.clear();
		}
	}

	private void initializeDeck() {
		String[] suits = { "♠", "♥", "♦", "♣" };
		String[] ranks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

		for (String suit : suits) {
			for (String rank : ranks) {
				//deck.add(rank+suit)
				deck.add(suit+rank);
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
		//hit(playerHand);
		//hit(dealerHand);
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

	private void showResult() {
		System.out.println("딜러 손패: " + dealerHand + ", 점수: " + dealerScore);
		System.out.println("유저 손패: " + playerHand + ", 점수: " + playerScore);
		System.out.println();

		if (playerScore > 21) {
			playerCoin += -1;
			dealerCoin += 1;
			System.err.println("유저가 21을 넘겼습니다. 딜러 승!");
			System.out.println();
			System.out.println("남은 코인");
			System.out.println("유저 : " + playerCoin + "\n딜러 : " + dealerCoin);
		} else if (dealerScore > 21) {
			playerCoin += 1;
			dealerCoin += -1;
			System.err.println("딜러가 21을 넘겼습니다. 유저 승!");
			System.out.println();
			System.out.println("남은 코인");
			System.out.println("유저 : " + playerCoin + "\n딜러 : " + dealerCoin);
		} else if (playerScore > dealerScore) {
			playerCoin += 1;
			dealerCoin += -1;
			System.err.println("유저 승!");
			System.out.println();
			System.out.println("남은 코인");
			System.out.println("유저 : " + playerCoin + "\n딜러 : " + dealerCoin);
		} else if (playerScore < dealerScore) {
			playerCoin += -1;
			dealerCoin += 1;
			System.err.println("딜러 승!");
			System.out.println();
			System.out.println("남은 코인");
			System.out.println("유저 : " + playerCoin + "\n딜러 : " + dealerCoin);
		} else if (playerScore == dealerScore) {
			System.err.println("무승부");
			System.out.println();
			System.out.println("남은 코인");
			System.out.println("유저 : " + playerCoin + "\n딜러 : " + dealerCoin);
		}
	}

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

	public static void main(String[] args) {
		blackJackMain game = new blackJackMain();
		game.startGame();
	}
}