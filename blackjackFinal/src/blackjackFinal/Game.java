package blackjackFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	
	CardDeck deck = new CardDeck(); 
	Player player = new Player();
	Dealer dealer = new Dealer();
	//private int playerCoin;
	//private int dealerCoin;
	private Scanner scanner;
	Rule rule = new Rule();
	
	public Game() {
		// TODO Auto-generated constructor stub
		//player.Hand = new ArrayList<>();
		//dealer.Hand = new ArrayList<>();
		//player.Score = 0;
		//dealer.Score = 0;
		//player.Coin = 5;
		//dealer.Coin = 5;
		List<String> deck = new ArrayList<>();
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
				clear();
				break;
			} else if (input.equalsIgnoreCase("T")) {
				clear();
				rule.rule(); 
				System.out.println("게임 시작(S) 게임 규칙(T) 게임 종료(Q)");
			} else if (input.equalsIgnoreCase("Q")) {
				tokenOver = true;
				break;
			}
		}

		while (!tokenOver) {
			deck.initializeDeck();
			deck.shuffleDeck();
			System.out.println("샤플 GAME deck " + deck.shuffleDeck());
			deck.initialDeal();
			String input = "";
			boolean gameOver = false;

			System.out.println("-".repeat(30));
			while (!gameOver) {
				System.out.println("딜러 손패1st : " + dealer.Hand + ", ?");
				System.out.println("유저 손패1st : " + player.Hand + ", 점수: " + player.Score);
				if (player.Score == 21 || dealer.Score == 21) {
					gameOver = true;
					showResult();
					break;
				}
				System.out.println();
				System.out.print("카드 받기(Y) 결과 확인(N) 게임 종료(Q)");
				input = scanner.nextLine();
				System.out.println();

				if (input.equalsIgnoreCase("Y")) {
					deck.hit(player.Hand);
					deck.calculateScore();
					if (player.Score > 21) {
						gameOver = true;
						showResult();
					}
				} else if (input.equalsIgnoreCase("N")) {
					gameOver = true;
					while (dealer.Score < 17) {
						deck.hit(dealer.Hand);
						deck.calculateScore();
					}
					showResult();
				} else if (input.equalsIgnoreCase("Q")) {
					System.err.print("종료시 모든 코인을 잃습니다. 종료(Y) 재시작(N)");
					input = scanner.nextLine();
					System.out.println();
					if (input.equalsIgnoreCase("Y")) {
						player.Coin = 0;
						break;
					} else if (input.equalsIgnoreCase("N")) {

						System.out.print("게임을 재시작 합니다");
					}
				}
			}

			if (player.Coin == 0) {
				System.out.println();
				System.err.print("Game Over");
				tokenOver = true;
			} else if (dealer.Coin == 0) {
				System.out.println();
				System.out.print("승리를 축하합니다.");
				tokenOver = true;
			} else {
				System.out.print("이어하기[Y] 종료하기[Q]");
				input = scanner.nextLine();
				System.out.println();
				if (input.equalsIgnoreCase("Y")) {
					((List<String>) deck).clear();
					player.Hand.clear();
					dealer.Hand.clear();
					clear();
				} else if (input.equalsIgnoreCase("Q")) {
					System.err.print("종료시 모든 코인을 잃습니다. 종료(Y) 재시작(N)");
					input = scanner.nextLine();
					System.out.println();
					if (input.equalsIgnoreCase("Y")) {
						player.Coin = 0;
						break;
					} else if (input.equalsIgnoreCase("N")) {
						System.out.println("게임을 재시작 합니다");
					}
				}
			}
		}
	}
	
	public void showResult() {
		System.out.println("딜러 손패: " + dealer.Hand + ", 점수: " + dealer.Score);
		System.out.println("유저 손패: " + player.Hand + ", 점수: " + player.Score);
		System.out.println();

		if (player.Score > 21) {
			player.Coin += -1;
			dealer.Coin += 1;
			System.err.println("유저가 21을 넘겼습니다. 딜러 승!");
			System.out.println();
			System.out.println("남은 코인");
			System.out.println("유저 : " + player.Coin + "\n" + "딜러 : " + dealer.Coin);
		} else if (dealer.Score > 21) {
			player.Coin += 1;
			dealer.Coin += -1;
			System.err.println("딜러가 21을 넘겼습니다. 유저 승!");
			System.out.println();
			System.out.println("남은 코인");
			System.out.println("유저 : " + player.Coin + "\n" + "딜러 : " + dealer.Coin);
		} else if (player.Score > dealer.Score) {
			player.Coin += 1;
			dealer.Coin += -1;
			System.err.println("유저 승!");
			System.out.println();
			System.out.println("남은 코인");
			System.out.println("유저 : " + player.Coin + "\n" + "딜러 : " + dealer.Coin);
		} else if (player.Score < dealer.Score) {
			player.Coin += -1;
			dealer.Coin += 1;
			System.err.println("딜러 승!");
			System.out.println();
			System.out.println("남은 코인");
			System.out.println("유저 : " + player.Coin + "\n" + "딜러 : " + dealer.Coin);
		} else if (player.Score == dealer.Score) {
			System.err.println("무승부");
			System.out.println();
			System.out.println("남은 코인");
			System.out.println("유저 : " + player.Coin + "\n" + "딜러 : " + dealer.Coin);
		}
	}


	public void clear() {
		System.out.print("\n".repeat(10));
	}

}
