package uno;

import java.util.ArrayList;


public class GameServer {
	
	
	/*List of players in the game*/
	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	/*CardSet of cards that are not drawn*/
	private CardPile drawPile = new CardPile();
	
	/*CardSet of cards that were played*/
	private CardPile discardPile = new CardPile();
	
	/*Determines the turn of the player*/
	private int turn;
	
	/*Number of players in the game*/
	private int playerCount;
	
	/*Order of the players is determined by the direction*/
	private int direction = 1;
	
	/*Stores the color that a player wants when a wild is played*/
	private char wildColor = '0';
	
	/*Stores the winning player*/
	private int winner = -1;

	GameServer(int players) {
		playerCount = players;
		drawPile.makeDeck();
		discardPile.addFront(drawPile.getFirstNumberCard());
		for(int playerPlace = 0; playerPlace < playerCount; playerPlace++) { /*For Loop to add 7 cards to each player*/
			playerList.add(new Player(playerPlace));
			drawCards(7, playerList.get(playerPlace));
		}
		turn = 8 * playerCount; /*Multiply by 8 to ensure value is never negative*/
	}
	
	/**
	 * Draws cards from the drawPile and adds them to the playerHand.
	 * If there are not enough cards in the pile the discardPile is merged with the drawPile
	 * @param cardCount: Number of cards to be drawn.
	 * @param player
	 */
	private void drawCards(int cardCount, Player player) {
		for(int cardIdx = cardCount; cardIdx > 0; cardIdx--) {
			if(drawPile.getCardCount() < 1) {
				drawPile.merge(discardPile);
			}
			player.getPlayerHand().addFront(drawPile.popCard());
		}
	}
	
	/**
	 * Checks if a player can play a certain card.
	 * @param playerCard: Card that the player wants to play.
	 * @param player
	 * @return true if card can be played.
	 */
	public boolean checkMove(Card playerCard, Player player) {
		Card topCard = discardPile.getTopCard();
		if(playerCard.getCardNumber() == Card.NORMAL_WILD_NUMBER) { /*Wild can always be played*/
			return true;
		}
		if(playerCard instanceof SpecialCard && playerCard.getCardColor() == Card.WILD) { /*Wild Draw 4 can only be played when player has no other option */
			return !player.getPlayerHand().hasPlayableCard(topCard);
		}
		if(topCard.getCardColor() == Card.WILD || /*Checks if wild was played last turn and the color the previous player chose*/
				(topCard instanceof SpecialCard && ((SpecialCard) topCard).getSpecialType() == SpecialCard.WILD_DRAW_4)) {
			return playerCard.getCardColor() == wildColor;
		}
		if(topCard.getCardColor() == playerCard.getCardColor()) { /*Checks if card matches the previous cards color*/
			return true;
		}
		if(topCard instanceof SpecialCard) { /*Checks if card is the same special type*/
			if(playerCard instanceof SpecialCard) {
				return ((SpecialCard) topCard).getSpecialType() == ((SpecialCard) playerCard).getSpecialType();
			}
			return false;
		}
		if(topCard.getCardNumber() == playerCard.getCardNumber()) { /*Checks if the number of the card is the same*/
			return true;
		}
		return false;
	}
	
	/**
	 * Reverses the order of the players.
	 */
	private void reverse() {
		direction *= -1;
	}
	
	/**
	 * Skips the next players turn.
	 */
	private void skip() {
		turn += direction;
	}
	
	/**
	 * Draws cards and skips the next players turn.
	 * @param cardCount: Number of cards to be drawn.
	 */
	private void draw(int cardCount) {
		turn += direction;
		drawCards(cardCount ,getNextPlayer());
	}
	
	/**
	 * Determines the player that needs to go next.
	 * @return player
	 */
	public Player getNextPlayer() {
		int playerIdx = turn % playerCount;
		return playerList.get(playerIdx);
	}
	
	/**
	 * Sees if any card in a playerHand can be played.
	 * @param player
	 * @return true if a card can be played.
	 */
	public boolean checkAllMoves(Player player) {
		ArrayList<Card> cards = player.getPlayerHand().getCardList();
		for(int cardsIdx = 0; cardsIdx < cards.size(); cardsIdx++) {
			if(checkMove(cards.get(cardsIdx), player)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Draws a card if the player chooses to draw during a turn.
	 * Plays the drawn card if the card is a valid move.
	 * @param player
	 * @param color: Color that the player wants if a wild is drawn.
	 */
	public void drawOnTurn(Player player, char color) {
		drawCards(1, player);
		if(checkMove(player.getPlayerHand().getTopCard(), player)) {
			playCard(player.getPlayerHand().getTopCard(), player, color);
			return;
		}
		endTurn(player);
	}
	
	/**
	 * Plays a Card that a player chooses.
	 * @param card
	 * @param player
	 * @param color: Color that the player wants if a wild is drawn.
	 * @return true if the card is successfully played.
	 */
	public boolean playCard(Card card, Player player, char color) {
		if(checkMove(card, player)) {
			player.getPlayerHand().removeCard(card);
			discardPile.addFront(card);
			if(card.getCardColor() == Card.WILD) {
				wildColor = color;
				 reverse(); /*(Custom Rule) Reverse when a black card is played*/
			}
			if(card instanceof SpecialCard) {
				SpecialCard specialCard = (SpecialCard) card;
				if(specialCard.getSpecialType() == SpecialCard.REVERSE) {
					reverse();
				}
				if(specialCard.getSpecialType() == SpecialCard.SKIP) {
					skip();
				}
				if(specialCard.getSpecialType() == SpecialCard.DRAW_2) {
					draw(2);
				}
				if(specialCard.getSpecialType() == SpecialCard.WILD_DRAW_4) {
					draw(4);
				}
			}
			endTurn(player);
			return true;
		}
		 drawCards(1, player); /*(Custom Rule) Player draws a card when they try to play an invalid card*/
		return false;
	}
	
	/**
	 * Ends the turn of the player and moves on to the next player.
	 * Checks if the player has won the game.
	 * @param player
	 */
	private void endTurn(Player player) {
		if(player.getPlayerHand().getCardCount() == 0) {
			winner = player.getPlayerPlace();
		}
		turn += direction;
	}
	
	/**
	 * Gets the List of Players.
	 * @return playerList
	 */
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	
	/**
	 * Getter for the color a player has chosen if a wild is played.
	 * @return color
	 */
	public char getWildColor() {
		return wildColor;
	}
	
	/**
	 * Getter for the winner of the game.
	 * @return winner
	 */
	public int getWinner() {
		return winner;
	}
	public Card getTopOfDiscardPile() {
		return discardPile.getTopCard();
	}
	public Card getTopOfDrawPile() {
		return drawPile.getTopCard();
	}
	/*These methods are used for the testing*/
	
	/**
	 * Getter for the DrawPile.
	 * @return drawPile
	 */
	public CardPile getDrawPile() {
		return drawPile;
	}
	
	/**
	 * Getter for the DiscardPile.
	 * @return drawPile
	 */
	public CardPile getDiscardPile() {
		return discardPile;
	}
	
	/**
	 * Setter for the drawPile
	 * @param discardPile
	 */
	public void setDiscardPile(CardPile discardPile) {
		this.discardPile = discardPile;
	}
	
	/**
	 * Getter for the order of the players.
	 * @return direction
	 */
	public int getDirection() {
		return direction;
	}
	
}
