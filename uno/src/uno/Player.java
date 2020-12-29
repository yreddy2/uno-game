/* This class represents the player. */
package uno;

public class Player {
	
	
	/* Defines their place which will determine when their turn is as well as their hand*/
	private int playerPlace;
	
	/* Defines what cards the player has */
	
	private CardHand playerHand = new CardHand();
	
	/**
	 * Creates a player with a certain place.
	 * @param playerPlace
	 */
	Player(int playerPlace){
		this.playerPlace = playerPlace;
	}
	
	/**
	 * Getter for the place of a player.
	 * @return playerPlace
	 */
	public int getPlayerPlace() {
		return playerPlace;
	}
	
	/**
	 * Returns the card set of the players hand.
	 * @return playerHand
	 */
	public CardHand getPlayerHand() {
		return playerHand;
	}
	
	/**
	 * (Used in Testing) Sets the cards that the player has.
	 * @param playerHand
	 */
	public void setPlayerHand(CardHand playerHand) {
		this.playerHand = playerHand;
	}	
}
