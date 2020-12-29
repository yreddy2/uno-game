/**
 * This class extends the Card class and is able to represent all the other types of cards. 
 * The "Special" Types of cards include skips, reverse, draw 2, and wild draw four.
 */
package uno;


public class SpecialCard extends Card {
	
	
	/*Card types are defined as char constants*/
	public static final char SKIP = 's';
	public static final char REVERSE = 'r';
	public static final char DRAW_2 = '2';
	public static final char WILD_DRAW_4 = '4';
	
	/*Card number for special cards is -1*/
	public static final int SPECIAL_NUMBER = -1;
	
	/* Holds the Type of Card.*/
	private char specialType;
	
	/**
	 * Constructs a special card of a specific type and color.
	 * @param cardColor: Color of the card.
	 * @param specialType: Type of the card
	 */
	public SpecialCard(char cardColor, char specialType) {
		super(cardColor, -1);
		this.specialType = specialType;
	}

	/**
	 * Getter for the type of card.
	 * @return specialType
	 */
	public char getSpecialType() {
		return specialType;
	}

	/**
	 * Overrides the equality function for objects to see if two special type cards are the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if(this.getClass() == obj.getClass() && super.equals(obj)) {
			SpecialCard specialCard = (SpecialCard) obj;
			if(this.specialType == specialCard.specialType) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Overrides the toString() written in Card to handle special cards.
	 */
	@Override
	public String toString() {
		return colorToString(getCardColor()) + " " + typeToString();
	}
	
	/**
	 * Converts the special type to a string.
	 * @return String: he special type of the card.
	 */
	public String typeToString() {
		if(getSpecialType() == SKIP) {
			return "Skip";
		}
		if(getSpecialType() == REVERSE) {
			return "Reverse";
		}
		if(getSpecialType() == DRAW_2) {
			return "Draw 2";
		}
		return "Draw 4";
	}

}
