package uno;

import org.junit.Test;


public class GameServerTest {

	
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
	
	/*Checks to see if initial game state is correct*/
	@Test
	public void GameServerConstructorTest() {
		GameServer gameServer = new GameServer(2);
		assert(gameServer.getDiscardPile().getCardCount() == 1);
		assert(gameServer.getDiscardPile().getTopCard().getCardNumber() > -1);
		assert(gameServer.getDrawPile().getCardCount() == 93);
		assert(gameServer.getPlayerList().get(0).getPlayerHand().getCardCount() == 7);
		assert(gameServer.getPlayerList().get(1).getPlayerHand().getCardCount() == 7);
		assert(gameServer.getNextPlayer().getPlayerPlace() == 0);
	}
	
	/*Checks to see if state properly changes when a card is played*/
	@Test
	public void PlayNumberCard() {
		GameServer gameServer = new GameServer(2);
		CardSet cardSet = new CardHand();
		addCardsToCardSet(cardSet);
		gameServer.getPlayerList().get(0).setPlayerHand((CardHand) cardSet);
		gameServer.getDiscardPile().addFront(green6Card);
		assert(gameServer.checkMove(blue6Card, gameServer.getPlayerList().get(0)));
		assert(gameServer.checkMove(green7Card, gameServer.getPlayerList().get(0)));
		assert(!gameServer.checkMove(red7Card, gameServer.getPlayerList().get(0)));
		assert(!gameServer.checkMove(blueSkipCard, gameServer.getPlayerList().get(0)));
		assert(gameServer.checkAllMoves(gameServer.getPlayerList().get(0)));
		assert(!gameServer.playCard(red7Card, gameServer.getPlayerList().get(0), Card.BLUE));
		assert(gameServer.playCard(green7Card, gameServer.getPlayerList().get(0), Card.BLUE));
		assert(gameServer.getNextPlayer().getPlayerPlace() == 1);
	}

	
	private void addCardsToCardSet(CardSet cardSet) {
		cardSet.addFront(green6Card);
		cardSet.addFront(red7Card);
		cardSet.addFront(green7Card);
		cardSet.addFront(blue6Card);
		cardSet.addFront(greenSkipCard);
		cardSet.addFront(greenReverseCard);
		cardSet.addFront(blueSkipCard);
	}
	
	/*Checks to see if state properly changes when card can be drawn but not played*/
	@Test
	public void DrawInvalidCard() {
		GameServer gameServer = new GameServer(2);
		CardSet cardSet = new CardHand();
		addCardsToCardSet(cardSet);
		gameServer.getPlayerList().get(0).setPlayerHand((CardHand) cardSet);
		gameServer.getDiscardPile().addFront(green6Card);
		gameServer.getDrawPile().addFront(red7Card);
		gameServer.drawOnTurn(gameServer.getPlayerList().get(0), Card.GREEN);
		assert(gameServer.getPlayerList().get(0).getPlayerHand().getCardCount() == 8);
		assert(gameServer.getNextPlayer().getPlayerPlace() == 1);
	}

	/*Checks to see if state properly changes when a valid card is drawn that it is played as well. */
	@Test
	public void DrawValidCard() {
		GameServer gameServer = new GameServer(2);
		CardSet cardSet = new CardHand();
		addCardsToCardSet(cardSet);
		gameServer.getPlayerList().get(0).setPlayerHand((CardHand) cardSet);
		gameServer.getDiscardPile().addFront(green6Card);
		gameServer.getDrawPile().addFront(green7Card);
		gameServer.drawOnTurn(gameServer.getPlayerList().get(0), Card.GREEN);
		assert(gameServer.getPlayerList().get(0).getPlayerHand().getCardCount() == 7);
		assert(gameServer.getNextPlayer().getPlayerPlace() == 1);
		assert(gameServer.getDiscardPile().getTopCard().equals(green7Card));
	}
	
	/*Checks to see if state properly changes when skip works*/
	@Test
	public void PlaySkipCard() {
		GameServer gameServer = new GameServer(2);
		CardSet cardSet = new CardHand();
		addCardsToCardSet(cardSet);
		gameServer.getPlayerList().get(0).setPlayerHand((CardHand) cardSet);
		gameServer.getDiscardPile().addFront(green6Card);
		assert(!gameServer.checkMove(blueSkipCard, gameServer.getPlayerList().get(0)));
		assert(gameServer.playCard(greenSkipCard, gameServer.getPlayerList().get(0), 'b'));
		assert(gameServer.getDiscardPile().getTopCard().equals(greenSkipCard));
		assert(gameServer.getNextPlayer().getPlayerPlace() == 0);
	}
	
	/*Checks to see if state properly changes when reverse works*/
	@Test
	public void PlayReverseCard() {
		GameServer gameServer = new GameServer(2);
		CardSet cardSet = new CardHand();
		addCardsToCardSet(cardSet);
		gameServer.getPlayerList().get(0).setPlayerHand((CardHand) cardSet);
		gameServer.getDiscardPile().addFront(green6Card);
		assert(gameServer.playCard(greenReverseCard, gameServer.getPlayerList().get(0), Card.BLUE));
		assert(gameServer.getDiscardPile().getTopCard().equals(greenReverseCard));
		assert(gameServer.getNextPlayer().getPlayerPlace() == 1);
		assert(gameServer.getDirection() == -1);
	}
	
	/*Checks to see if state properly changes when draw2 works*/
	@Test
	public void PlayDraw2Card() {
		GameServer gameServer = new GameServer(2);
		CardSet cardSet = new CardHand();
		addCardsToCardSet(cardSet);
		cardSet.addFront(greenDraw2Card);
		gameServer.getPlayerList().get(0).setPlayerHand((CardHand) cardSet);
		gameServer.getDiscardPile().addFront(green6Card);
		assert(gameServer.playCard(greenDraw2Card, gameServer.getPlayerList().get(0), Card.BLUE));
		assert(gameServer.getDiscardPile().getTopCard().equals(greenDraw2Card));
		assert(gameServer.getNextPlayer().getPlayerPlace() == 0);
		assert(gameServer.getPlayerList().get(1).getPlayerHand().getCardCount() == 9);
	}
	
	/*Checks to see if state properly changes when wild draw 4 cannot be played when another move is possible*/
	@Test
	public void PlayInValidWildDraw4Card() {
		GameServer gameServer = new GameServer(2);
		CardSet cardSet = new CardHand();
		addCardsToCardSet(cardSet);
		cardSet.addFront(greenDraw2Card);
		cardSet.addFront(WildDraw4Card);
		gameServer.getPlayerList().get(0).setPlayerHand((CardHand) cardSet);
		gameServer.getDiscardPile().addFront(green6Card);
		assert(!gameServer.playCard(WildDraw4Card, gameServer.getPlayerList().get(0), Card.BLUE));
		assert(gameServer.getDiscardPile().getTopCard().equals(green6Card));
		assert(gameServer.getNextPlayer().getPlayerPlace() == 0);
		assert(gameServer.getPlayerList().get(0).getPlayerHand().getCardCount() == 10);
	}
	
	/*Checks to see if state properly changes when wild draw 4 cannot be played when another move is not possible*/
	@Test
	public void PlayValidWildDraw4Card() {
		GameServer gameServer = new GameServer(2);
		CardSet cardSet = new CardHand();
		cardSet.addFront(blueSkipCard);
		cardSet.addFront(WildDraw4Card);
		gameServer.getPlayerList().get(0).setPlayerHand((CardHand) cardSet);
		gameServer.getDiscardPile().addFront(green6Card);
		assert(gameServer.playCard(WildDraw4Card, gameServer.getPlayerList().get(0), Card.BLUE));
		assert(gameServer.getDiscardPile().getTopCard().equals(WildDraw4Card));
		assert(gameServer.getNextPlayer().getPlayerPlace() == 0);
		assert(gameServer.getPlayerList().get(1).getPlayerHand().getCardCount() == 11);
		assert(gameServer.getWildColor() == Card.BLUE);
	}
	
	/*Checks to see if state properly changes when wild works*/
	@Test
	public void PlayWild() {
		GameServer gameServer = new GameServer(2);
		CardSet cardSet = new CardHand();
		addCardsToCardSet(cardSet);
		cardSet.addFront(greenDraw2Card);
		cardSet.addFront(WildCard);
		gameServer.getPlayerList().get(0).setPlayerHand((CardHand) cardSet);
		gameServer.getDiscardPile().addFront(green6Card);
		assert(gameServer.playCard(WildCard, gameServer.getPlayerList().get(0), Card.BLUE));
		assert(gameServer.getDiscardPile().getTopCard().equals(WildCard));
		assert(gameServer.getNextPlayer().getPlayerPlace() == 1);
		assert(gameServer.getWildColor() == 'b');
	}
	
	/*Checks to see if state properly changes when discard pile can be reused when draw pile becomes empty*/
	@Test
	public void DrawWithNoCards() {
		GameServer gameServer = new GameServer(2);
		CardSet cardSet = new CardPile();
		addCardsToCardSet(cardSet);
		cardSet.addFront(greenDraw2Card);
		cardSet.addFront(WildCard);
		gameServer.getDiscardPile().addFront(green6Card);
		gameServer.setDiscardPile((CardPile) cardSet);
		gameServer.getDrawPile().removeAll();
		gameServer.drawOnTurn(gameServer.getPlayerList().get(0), Card.BLUE);
		assert(gameServer.getDiscardPile().getCardCount() < 3);
		assert(gameServer.getDrawPile().getCardCount() > 5);
	}
	
	/*Checks to see if state properly changes when a winner is identified*/
	@Test
	public void DetermineWinner() {
		GameServer gameServer = new GameServer(2);
		Card green6Card = new Card(Card.GREEN, 6);
		CardSet cardSet = new CardHand();
		cardSet.addFront(green6Card);
		gameServer.getPlayerList().get(0).setPlayerHand((CardHand) cardSet);
		gameServer.getDiscardPile().addFront(green6Card);
		assert(gameServer.playCard(green6Card, gameServer.getPlayerList().get(0), Card.BLUE));
		assert(gameServer.getWinner() == 0);
	}
}
