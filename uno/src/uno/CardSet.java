/* The CardSet is an abstract class that defines any set of cards from the players hand to the draw pile. */
package uno;

import java.util.ArrayList;

public abstract class CardSet {
	
	
	/* List to hold all the cards in a set. */
	private ArrayList<Card> cardList = new ArrayList<Card>();
	
	/**
	 * Getter for the cards.
	 * @return cardList: List of cards.
	 */
	
	public ArrayList<Card> getCardList() {
		return cardList;
	}
	
	public void setCardList(ArrayList<Card> otherCardList) {
		cardList = otherCardList;
	}
	
	/**
	 * Adds a card to the front of the set.
	 * @param card
	 */
	public void addFront(Card card){
		cardList.add(0, card);
	}
	
	/**
	 * Returns and deletes the first card in the set.
	 * @return card
	 */
	public Card popCard() {
		Card card = cardList.get(0);
		cardList.remove(0);
		return card;
	}
	
	/**
	 * Gets the number of cards in the cardList.
	 * @return size of the cardList.
	 */
	public int getCardCount() {
		return cardList.size();
	}
	
	/**
	 * Returns the front card of the set.
	 * @return card
	 */
	public Card getTopCard() {
		return getCardList().get(0);
	}
}
