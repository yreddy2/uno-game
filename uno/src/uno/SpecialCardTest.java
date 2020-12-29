package uno;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;


public class SpecialCardTest {

	
	/*Checks to see if the SpecialCard constructor works properly*/
	@Test
	public void specialCardConstructorTest() {
		SpecialCard greenSkipCard = new SpecialCard(Card.GREEN, 's');
		assertEquals(Card.GREEN, greenSkipCard.getCardColor()); /*Checks if correct color is defined*/
		assertEquals(SpecialCard.SPECIAL_NUMBER, greenSkipCard.getCardNumber()); /*Checks if correct number is defined*/
		assertEquals(SpecialCard.SKIP, greenSkipCard.getSpecialType()); /*Checks if correct special type is defined*/
	}
	
	/*Checks to see if the overridden equality function works*/
	@Test
	public void specialCardEqualityTest() {
		Card green6Card = new Card(Card.GREEN, 6);
		Card greenSkipCard = new SpecialCard(Card.GREEN, SpecialCard.SKIP);
		Card greenSkipCardDuplicate = new SpecialCard(Card.GREEN, SpecialCard.SKIP);
		Card greenReverseCard = new SpecialCard(Card.GREEN, SpecialCard.REVERSE);
		Card blueSkipCard = new SpecialCard(Card.BLUE, SpecialCard.SKIP);
		assertNotEquals(green6Card, greenSkipCard); /*Normal cards and special cards should not be equal*/
		assertEquals(greenSkipCardDuplicate, greenSkipCard); /*Special cards with the same color and type should be equal*/
		assertNotEquals(greenReverseCard, greenSkipCard); /*Special cards with a different type should not be equal*/
		assertNotEquals(blueSkipCard, greenSkipCard); /*Special cards with a different color should not be equal*/
	}
	
	/*Checks to see if card is converted to string properly*/
	@Test
	public void cardString() {
		Card greenSkipCard = new SpecialCard(Card.GREEN, SpecialCard.SKIP);
		Card greenReverseCard = new SpecialCard(Card.GREEN, SpecialCard.REVERSE);
		Card greenDraw2Card = new SpecialCard(Card.GREEN, SpecialCard.DRAW_2);
		Card WildDraw4Card = new SpecialCard(Card.WILD, SpecialCard.WILD_DRAW_4);
		assertEquals(greenSkipCard.toString(), "Green Skip");
		assertEquals(greenReverseCard.toString(), "Green Reverse");
		assertEquals(greenDraw2Card.toString(), "Green Draw 2");
		assertEquals(WildDraw4Card.toString(), "Wild Draw 4");
	}

}
