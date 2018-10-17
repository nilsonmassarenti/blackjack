package com.nilsonmassarenti.app.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {

	private List<Card> cards = new ArrayList<Card>();

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public String toString() {
		String cardsOutput = "";
		Integer i = 1;
		if (cards != null) {
			for (Card card : this.cards) {
				cardsOutput += "\n" + i + "-" + card.toString();
				i++;
			}
			return cardsOutput;
		} else {
			return "Cards no available";
		}
	}

	public void removeCard(int i) {
		this.cards.remove(i);
	}

	public Card getCard(int i) {
		return this.cards.get(i);
	}

	public void addCard(Card addCard) {
		this.cards.add(addCard);
	}

	public void draw(Deck comingFrom) {
		this.cards.add(comingFrom.getCard(0));
		comingFrom.removeCard(0);
	}

	public int deckSize(){
		return this.cards.size();
	}
	
	public void moveAllToDeck(Deck moveTo){
		int thisDeckSize = this.cards.size();
		for(int i = 0; i < thisDeckSize; i++){
			moveTo.addCard(this.getCard(i));
		}
		for(int i = 0; i < thisDeckSize; i++){
			this.removeCard(0);
		}
	}
	
	public Integer cardsValue() {
		Integer totalValue = 0;
		Integer aces = 0;

		for (Card card : this.cards) {
			switch (card.getValue()) {
			case TWO:
				totalValue += 2;
				break;
			case THREE:
				totalValue += 3;
				break;
			case FOUR:
				totalValue += 4;
				break;
			case FIVE:
				totalValue += 5;
				break;
			case SIX:
				totalValue += 6;
				break;
			case SEVEN:
				totalValue += 7;
				break;
			case EIGHT:
				totalValue += 8;
				break;
			case NINE:
				totalValue += 9;
				break;
			case TEN:
				totalValue += 10;
				break;
			case JACK:
				totalValue += 10;
				break;
			case QUEEN:
				totalValue += 10;
				break;
			case KING:
				totalValue += 10;
				break;
			case ACE:
				aces += 1;
				break;
			}
		}
		for (int i = 0; i < aces; i++) {
			if (totalValue > 10) {
				totalValue += 1;
			} else {
				totalValue += 11;
			}
		}

		return totalValue;
	}
}
