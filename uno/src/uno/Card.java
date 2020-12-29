/* This is the bas class for a card. It is able to construct number cards and non draw four wilds. */
package uno;


public class Card {
	
	
	/*Colors are defined as char constants*/
	public static final char GREEN = 'g';
	
	public static final char BLUE = 'b';
	
	public static final char RED = 'r';
	
	public static final char YELLOW = 'y';
	
	public static final char WILD = 'w';
	
	/*The card number for a normal wild is -2*/
	public static final int NORMAL_WILD_NUMBER = -2;
	
	/* Holds the color of the card. */
	private char cardColor;
	
	/* Holds the number associated with the card. For wild or special cards the value is -1. */
	private int cardNumber;
	
	/**
	 * Constructs a simple card of a specific number and color.
	 * @param cardColor: Color of the card.
	 * @param cardNumber: Number of the card.
	 */
	public Card(char cardColor, int cardNumber) {
		this.cardColor = cardColor;
		this.cardNumber = cardNumber;
	}
	
	/**
	 * Getter for the color.
	 * @return cardColor: Color of the card.
	 */
	public char getCardColor() {
		return cardColor;
	}
	
	/**
	 * Getter for the number.
	 * @return cardNumber: Number of the card.
	 */
	public int getCardNumber() {
		return cardNumber;
	}
	
	/**
	 * Converts a card to its name in the game.
	 */
	public String toString() {
		if(getCardColor() == WILD) {
			return colorToString(getCardColor());
		}
		return colorToString(getCardColor()) + " " + Integer.toString(getCardNumber());
	}
	
	/* Overrides the equality function for objects to see if two cards are the same. */
	@Override
	public boolean equals(Object obj) {
		Card card = (Card) obj;
		if(this.cardColor == card.cardColor && this.cardNumber == card.cardNumber) {
			return true;
		}
		return false;
	}
	
	/**
	 * Converts color symbol to String.
	 * @param color: color symbol.
	 * @return String of the color.
	 */
	public String colorToString(char color) {
		if(color == Card.BLUE) {
			return "Blue";
		}
		if(color == Card.GREEN) {
			return "Green";
		}
		if(color == Card.RED) {
			return "Red";
		}
		if(color == Card.YELLOW) {
			return "Yellow";
		}
		return "Wild";
	}
}
