package com.nilsonmassarenti.app.blackjack.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.nilsonmassarenti.app.blackjack.model.Card;
import com.nilsonmassarenti.app.blackjack.model.Deck;
import com.nilsonmassarenti.app.blackjack.model.Suit;
import com.nilsonmassarenti.app.blackjack.model.Value;

public class PlayController {

	private Deck deck;

	public void playTheGame() {
		System.out.println(welcome());
		if (createDeck()) {
			System.out.println("Deck has been created\n");

		} else {
			System.out.println("Problem to create a Deck\n");
			return;
		}

		System.out.println("Showing the cards of deck");
		System.out.println(deck.toString());

		System.out.println("Shuffling the cards of deck\n");
		shuffle();

		System.out.println("Start the game\n");
		playing();
		System.out.println("End of game\n");

	}

	public String welcome() {
		String message = "Welcome to Blackjack\n";
		return message;
	}

	private Boolean createDeck() {
		List<Card> cards = new ArrayList<Card>();
		for (Suit suit : Suit.values()) {
			for (Value value : Value.values()) {
				cards.add(new Card(suit, value));
			}
		}
		this.deck = new Deck();
		this.deck.setCards(cards);
		if (this.deck.getCards().size() == 52) {
			return true;
		} else {
			return false;
		}
	}

	private void shuffle() {
		List<Card> cards = this.deck.getCards();
		Collections.shuffle(cards);
		this.deck.setCards(cards);
	}

	private void playing() {
		Deck playerDeck = new Deck();
		Deck dealerDeck = new Deck();

		Double playerMoney = 100.00;
		Scanner userInput = new Scanner(System.in);
		String stopped = "n";
		Integer response = 0;
		Double playerBet = 0.0;
		Boolean endRound = false;

		while (playerMoney > 0 || !stopped.toLowerCase().equals("y")) {
			endRound = false;
			System.out.println("You have $ " + playerMoney + ", how much would like to bet?");
			playerBet = userInput.nextDouble();
			if (playerBet > playerMoney) {
				System.out.println("You cannot bet more than you have. Please try again.");
				break;
			}
			playerDeck.draw(this.deck);
			playerDeck.draw(this.deck);

			dealerDeck.draw(deck);
			dealerDeck.draw(deck);

			while (true) {
				System.out.println("Your hand: ");
				System.out.println(playerDeck.toString());
				System.out.println("Your hand is value at: " + playerDeck.cardsValue());

				System.out.println("Dealer hand: " + dealerDeck.getCard(0).toString() + " and [Hidden]");

				System.out.println("Would you like to (1) Hit or (2) Stand");
				response = userInput.nextInt();

				if (response == 1) {
					playerDeck.draw(deck);
					System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize() - 1));
					if (playerDeck.cardsValue() > 21) {
						System.out.println("Bust. Currently value at: " + playerDeck.cardsValue());
						playerMoney -= playerBet;
						endRound = true;
						break;

					}
				}
				if (response == 2) {
					break;
				}

			}
			System.out.println("Dealer Cards:" + dealerDeck.toString());
			if ((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false) {
				System.out.println("Dealer beats you " + dealerDeck.cardsValue() + " to " + playerDeck.cardsValue());
				playerMoney -= playerBet;
				endRound = true;
			}

			while ((dealerDeck.cardsValue() < 17) && endRound == false) {
				dealerDeck.draw(deck);
				System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString());
			}
			System.out.println("Dealers hand value: " + dealerDeck.cardsValue());
			if ((dealerDeck.cardsValue() > 21) && endRound == false) {
				System.out.println("Dealer Busts. You win!");
				playerMoney += playerBet;
				endRound = true;
			}
			if ((dealerDeck.cardsValue() == playerDeck.cardsValue()) && endRound == false) {
				System.out.println("Push.");
				endRound = true;
			}
			if ((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false) {
				System.out.println("You win the hand.");
				playerMoney += playerBet;
				endRound = true;
			} else if (endRound == false) {
				System.out.println("Dealer wins.");
				playerMoney -= playerBet;
			}

			playerDeck.moveAllToDeck(deck);
			dealerDeck.moveAllToDeck(deck);
			System.out.println("End of Hand.");
			
			System.out.println("Would you like to (Y) continue or (N) stop");
			stopped = userInput.next();
		}
		userInput.close();
	}

}
