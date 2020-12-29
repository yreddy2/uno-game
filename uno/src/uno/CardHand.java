/*This class extends CardSet and adds functionality that is only used in the players hand*/
package uno;


public class CardHand extends CardSet{
	
	
	
	/**
	 * Checks if a certain card can be played.
	 * @param card: The card that is compared to.
	 * @return true if color exists.
	 */
	public boolean hasPlayableCard(Card card) {
		for(int cardListIdx = 0; cardListIdx < getCardList().size(); cardListIdx++) {
			if(getCardList().get(cardListIdx).getCardColor() == card.getCardColor()) { /*Sees if card is the same color*/
				return true;
			}
			if(card.getCardNumber() > -1 && getCardList().get(cardListIdx).getCardNumber() == card.getCardNumber()) { /*Sees if card is the same number*/
				return true;
			}
			if(card instanceof SpecialCard && card.getClass() == getCardList().get(cardListIdx).getClass()) {  /*Sees if card is the same special type*/
				if(((SpecialCard) card).getSpecialType() == ((SpecialCard) getCardList().get(cardListIdx)).getSpecialType() 
						&& ((SpecialCard) getCardList().get(cardListIdx)).getSpecialType() != SpecialCard.WILD_DRAW_4) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Removes a specific card from the set.
	 * @param card
	 */
	public void removeCard(Card card) {
		for(int cardListIdx = 0; cardListIdx < getCardList().size(); cardListIdx++) {
			if(card.equals(getCardList().get(cardListIdx))) {
				getCardList().remove(cardListIdx);
				return;
			}
		}
	}
}
