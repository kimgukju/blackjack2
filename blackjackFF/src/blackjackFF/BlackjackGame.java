package blackjackFF;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 클래스나눈거임 
public class BlackjackGame {
	Rule rule = new Rule();
    public Deck makedeck;
    private List<String> deck;
    private List<String> playerHand;
    private List<String> dealerHand;
    private int playerScore;
    private int dealerScore;
    private Scanner scanner;
	private int playerCoin;
	private int dealerCoin;

    public BlackjackGame() {
        makedeck = new Deck();
        deck = new ArrayList<>();
        playerHand = new ArrayList<String>();
        dealerHand = new ArrayList<String>();
        playerScore = 0;
        dealerScore = 0;
        scanner = new Scanner(System.in);
        playerCoin = 5;
		dealerCoin = 5;
    }

    public void startGame() {
    	boolean tokenOver;
		System.out.println("* BlackJack *");
		System.out.println("게임 시작(S) 게임 규칙(T) 게임 종료(Q)");

		while (true) {
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("S")) {
				tokenOver = false;
				rule.clear();
				break;
			} else if (input.equalsIgnoreCase("T")) {
				rule.clear();
				rule.rule();
				System.out.println("게임 시작(S) 게임 규칙(T) 게임 종료(Q)");
			} else if (input.equalsIgnoreCase("Q")) {
				tokenOver = true;
				break;
			}
		}

		while (!tokenOver) {
			makedeck.initializeDeck();
			makedeck.shuffleDeck();
			initialDeal();
			String input = "";
			boolean gameOver = false;

			System.out.println("-".repeat(30));
			while (!gameOver) {
				System.out.println("딜러 손패(1st) : " + dealerHand.get(0) + ", ?");
				System.out.println("유저 손패(1st) : " + playerHand + ", 점수: " + playerScore);

				if (playerScore == 21 || dealerScore == 21) {
					gameOver = true;
					showResult();
					break;
				}
				System.out.println();
				System.out.print("카드 받기(Y) 결과 확인(N) 게임 종료(Q)");
				input = scanner.nextLine();
				System.out.println();

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
					System.out.println();
					if (input.equalsIgnoreCase("Y")) {
						playerCoin = 0;
						break;
					} else if (input.equalsIgnoreCase("N")) {

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
			} else {
				System.out.print("이어하기[Y] 종료하기[Q]");
				input = scanner.nextLine();
				System.out.println();
				if (input.equalsIgnoreCase("Y")) {
					deck.clear(); // java clear
					playerHand.clear(); // java clear
					dealerHand.clear(); // java clear
					rule.clear();
				} else if (input.equalsIgnoreCase("Q")) {
					System.err.print("종료시 모든 코인을 잃습니다. 종료(Y) 재시작(N)");
					input = scanner.nextLine();
					System.out.println();
					if (input.equalsIgnoreCase("Y")) {
						playerCoin = 0;
						break;
					} else if (input.equalsIgnoreCase("N")) {
						System.out.println("게임을 재시작 합니다");
					}
				}
			}
		}
    }

    public void initialDeal() {
        hit(playerHand);
        hit(dealerHand);
        hit(playerHand);
        hit(dealerHand);
        calculateScore();
    }

	public void hit(List<String> hand) {
		String card = makedeck.deck.remove(makedeck.deck.size() - 1);
		hand.add(card);
	}

	public void calculateScore() {
		playerScore = calculateHandScore(playerHand);
		dealerScore = calculateHandScore(dealerHand);
	}
	
	public int calculateHandScore(List<String> hand) {
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
			System.out.println("유저 : " + playerCoin + "\n" + "딜러 : " + dealerCoin);
		} else if (dealerScore > 21) {
			playerCoin += 1;
			dealerCoin += -1;
			System.err.println("딜러가 21을 넘겼습니다. 유저 승!");
			System.out.println();
			System.out.println("남은 코인");
			System.out.println("유저 : " + playerCoin + "\n" + "딜러 : " + dealerCoin);
		} else if (playerScore > dealerScore) {
			playerCoin += 1;
			dealerCoin += -1;
			System.err.println("유저 승!");
			System.out.println();
			System.out.println("남은 코인");
			System.out.println("유저 : " + playerCoin + "\n" + "딜러 : " + dealerCoin);
		} else if (playerScore < dealerScore) {
			playerCoin += -1;
			dealerCoin += 1;
			System.err.println("딜러 승!");
			System.out.println();
			System.out.println("남은 코인");
			System.out.println("유저 : " + playerCoin + "\n" + "딜러 : " + dealerCoin);
		} else if (playerScore == dealerScore) {
			System.err.println("무승부");
			System.out.println();
			System.out.println("남은 코인");
			System.out.println("유저 : " + playerCoin + "\n" + "딜러 : " + dealerCoin);
		}
	}

 
}