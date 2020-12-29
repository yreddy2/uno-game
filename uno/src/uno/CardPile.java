/*This class extends CardSet and adds functionality that is only used in the deck and discard pile*/
package uno;

import java.util.ArrayList;
import java.util.Collections;

public class CardPile extends CardSet{

	
	/**
	 * Removes all elements in the cardList.
	 */
	public void removeAll() {
		setCardList(new ArrayList<Card>());
	}
	
	/**
	 * Returns the first numbered card in a deck to start the game.
	 * @return card
	 */
	public Card getFirstNumberCard() {
		for(int cardListIdx = 0; cardListIdx < 33 && cardListIdx < getCardList().size(); cardListIdx++) { /* Checks first 33 cards since there has to be a numbered card in that range */
			Card card = getCardList().get(cardListIdx);
			if(card.getCardNumber() >= 0) {
				getCardList().remove(cardListIdx); 
				return card;
			}
		}
		return null;
	}
	
	/**
	 * Merges two CardSets together. It is used when the draw pile becomes empty.
	 * @param other
	 */
	public void merge(CardPile other) {
		Card topCard = other.popCard();
		ArrayList<Card> otherCardList = other.getCardList();
		Collections.shuffle(otherCardList); /*Shuffles cards*/
		getCardList().addAll(otherCardList);
		other.removeAll();
		other.addFront(topCard); /*Keeps the top of the pile*/
	}
	
	/**
	 * Makes a new deck with all 108 specified cards.
	 */
	public void makeDeck() {
		setCardList(new ArrayList<Card>());
		char colors[] = {'b', 'g', 'r', 'y', 'w'}; /* All the different colors possible */
		char specialTypes[] = {'s', 'r', '2', '4'}; /* All the special types possible */
		for(int colorIdx = 0; colorIdx < colors.length - 1; colorIdx++) { /* Double for loop to add all the numbered cards */
			this.addFront(new Card(colors[colorIdx], 0));
			for(int cardNumber = 1; cardNumber < 10; cardNumber++) {
				this.addFront(new Card(colors[colorIdx], cardNumber));
				this.addFront(new Card(colors[colorIdx], cardNumber));
			}
		}
		for(int colorIdx = 0; colorIdx < colors.length - 1; colorIdx++) { /* Double for loop to add special cards except for wilds */
			for(int specialTypesIdx = 0; specialTypesIdx < specialTypes.length - 1; specialTypesIdx++) {
				this.addFront(new SpecialCard(colors[colorIdx], specialTypes[specialTypesIdx]));
				this.addFront(new SpecialCard(colors[colorIdx], specialTypes[specialTypesIdx]));
			}
		}
		for(int wildCount = 0; wildCount < 4; wildCount++) { /* For loop to add all the wilds */
			this.addFront(new Card(colors[4], -2));
			this.addFront(new SpecialCard(colors[4], specialTypes[3]));
		}
		Collections.shuffle(getCardList()); /* Deck is shuffled */
	}
}
