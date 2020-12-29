package uno;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;


public class CardSetTest {

	
	private Card green6Card = new Card(Card.GREEN, 6);
	
	private Card greenSkipCard = new SpecialCard(Card.GREEN, SpecialCard.SKIP);
	
	private Card greenSkipCardDuplicate = new SpecialCard(Card.GREEN, SpecialCard.SKIP);
	
	private Card greenReverseCard = new SpecialCard(Card.GREEN, SpecialCard.REVERSE);
	
	private Card blueSkipCard = new SpecialCard(Card.GREEN, SpecialCard.SKIP);
	
	private Card red7Card = new Card('r', 7);
	
	/*Checks to see if a cardList can be retrieved*/
	@Test
	public void getCardListTest() {
		Card green6Card = new Card(Card.GREEN, 6);
		ArrayList<Card> cardList = new ArrayList<Card>();
		cardList.add(green6Card);
		CardSet cardSet = new CardHand();
		cardSet.addFront(green6Card);
		assertEquals(cardList, cardSet.getCardList());
	}
	
	/*Checks to see if cards are added properly*/
	@Test
	public void addCardTest() {
		CardSet cardSet = new CardHand();
		addingCardsToCardSet(cardSet);
		assertEquals(5, cardSet.getCardCount()); /*Checks to see if all the cards are added*/
		assert(cardSet.getTopCard().equals(blueSkipCard)); /*Checks to see if the cards are added in the right order*/
	}

	private void addingCardsToCardSet(CardSet cardSet) {
		cardSet.addFront(green6Card);
		cardSet.addFront(greenSkipCard);
		cardSet.addFront(greenSkipCardDuplicate);
		cardSet.addFront(greenReverseCard);
		cardSet.addFront(blueSkipCard);
	}
	
	/*Checks to see if cards are removed properly*/
	@Test
	public void removeTest() {
		CardSet cardSet = new CardHand();
		addingCardsToCardSet(cardSet);
		assertEquals(5, cardSet.getCardCount()); /*Checks to see if all the cards are added*/
		assertEquals(blueSkipCard, cardSet.popCard()); /*Checks to see if the top card is removed*/
		assertEquals(4, cardSet.getCardCount()); /*Checks to see if the list is updated*/
		((CardHand) cardSet).removeCard(green6Card); /*Checks to see if a specific card is removed*/
		assertEquals(3, cardSet.getCardCount()); /*Checks to see if the list is updated*/
	}
	
	/*Checks to see if cards are removed properly*/
	@Test
	public void removeAllTest() {
		CardSet cardSet = new CardPile();
		addingCardsToCardSet(cardSet);
		assertEquals(5, cardSet.getCardCount()); /*Checks to see if all the cards are added*/
		assertEquals(blueSkipCard, cardSet.popCard()); /*Checks to see if the top card is removed*/
		assertEquals(4, cardSet.getCardCount()); /*Checks to see if the list is updated*/
		((CardPile) cardSet).removeAll(); /*Checks to see if all the cards are removed*/
		assertEquals(0, cardSet.getCardCount()); /*Checks to see if the list is updated*/
	}
	
	/*Checks to see if a number card can be selected to start the game*/
	@Test
	public void getNumberCardTest() {
		CardSet cardSet = new CardPile();
		addingCardsToCardSet(cardSet);
		assertEquals(green6Card, ((CardPile) cardSet).getFirstNumberCard()); /*Checks to see if a number card can be retrieved*/
	}
	
	/*Checks to see if a draw and discard pile can be merged*/
	@Test
	public void mergeTest() {
		CardSet cardSet = new CardPile();
		addingCardsToCardSet(cardSet);
		CardSet otherCardSet = new CardPile();
		addingCardsToCardSet(otherCardSet);
		otherCardSet.addFront(red7Card);
		((CardPile) cardSet).merge(((CardPile) otherCardSet));
		assertEquals(10, cardSet.getCardCount()); /*Checks to see if cards are merged*/
	}

	/*Checks to see if a deck can be made*/
	@Test
	public void makeDeckTest() {
		CardSet cardSet = new CardPile();
		((CardPile) cardSet).makeDeck();
		assertEquals(108, cardSet.getCardCount());
	}

}