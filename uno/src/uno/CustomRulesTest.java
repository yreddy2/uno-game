package uno;

import org.junit.Test;


public class CustomRulesTest {
	

	Card green6Card = new Card(Card.GREEN, 6);
	
	Card red7Card = new Card(Card.RED, 7);
	
	Card green7Card = new Card(Card.GREEN, 7);
	
	Card blue6Card = new Card(Card.BLUE, 6);
	
	Card greenSkipCard = new SpecialCard(Card.GREEN, SpecialCard.SKIP);
	
	Card greenReverseCard = new SpecialCard(Card.GREEN, SpecialCard.REVERSE);
	
	Card greenDraw2Card = new SpecialCard(Card.GREEN, SpecialCard.DRAW_2);
	
	Card blueSkipCard = new SpecialCard(Card.BLUE, SpecialCard.SKIP);
	
	Card WildCard = new Card(Card.WILD, Card.NORMAL_WILD_NUMBER);
	
	Card WildDraw4Card = new SpecialCard(Card.WILD, SpecialCard.WILD_DRAW_4);
	
	private void addCardsToCardSet(CardSet cardSet) {
		cardSet.addFront(green6Card);
		cardSet.addFront(red7Card);
		cardSet.addFront(green7Card);
		cardSet.addFront(blue6Card);
		cardSet.addFront(greenSkipCard);
		cardSet.addFront(greenReverseCard);
		cardSet.addFront(blueSkipCard);
	}
	
	/*Checks if players order is reversed when a black card is played*/
	@Test
	public void reverseOnBlackCard() {
		GameServer gameServer = new GameServer(2);
		CardSet cardSet = new CardHand();
		addCardsToCardSet(cardSet);
		cardSet.addFront(greenDraw2Card);
		cardSet.addFront(WildCard);
		gameServer.getPlayerList().get(0).setPlayerHand((CardHand) cardSet);
		gameServer.getDiscardPile().addFront(green6Card);
		assert(gameServer.playCard(WildCard, gameServer.getPlayerList().get(0), Card.BLUE));
		assert(gameServer.getDiscardPile().getTopCard().equals(WildCard));
		assert(gameServer.getWildColor() == 'b');
		assert(gameServer.getNextPlayer().getPlayerPlace() == 1);
		assert(gameServer.getDirection() == -1);
	}
	
	/*Checks if a player plays a card that is not a valid and a card is drawn for the mistake*/
	@Test
	public void PlayNumberCard() {
		GameServer gameServer = new GameServer(2);
		CardSet cardSet = new CardHand();
		addCardsToCardSet(cardSet);
		gameServer.getPlayerList().get(0).setPlayerHand((CardHand) cardSet);
		gameServer.getDiscardPile().addFront(green6Card);
		assert(gameServer.getPlayerList().get(0).getPlayerHand().getCardCount() == 7);
		assert(!gameServer.playCard(red7Card, gameServer.getPlayerList().get(0), Card.BLUE));
		assert(gameServer.getPlayerList().get(0).getPlayerHand().getCardCount() == 8);
		assert(gameServer.getNextPlayer().getPlayerPlace() == 0);
	}

}

