package uno;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;


public class CardTest {
	
	
	/*Checks to see if the Card constructor works properly*/
	@Test
	public void cardConstructorTest() {
		Card green6Card = new Card(Card.GREEN, 6); 
		assertEquals(Card.GREEN, green6Card.getCardColor()); /*Checks if correct color is defined*/
		assertEquals(6, green6Card.getCardNumber()); /*Checks if correct number is defined*/
	}
	
	/*Checks to see if the overridden equality function works*/
	@Test
	public void cardEqualityTest() {
		Card green6Card = new Card(Card.GREEN, 6);
		Card green6CardDuplicate = new Card(Card.GREEN, 6);
		Card green7Card = new Card(Card.GREEN, 7);
		Card blue6Card = new Card(Card.BLUE, 6);
		assertEquals(green6CardDuplicate, green6Card); /*Cards with the same color and number should be equal*/
		assertNotEquals(green7Card, green6Card); /*Cards with a different number should not be equal*/
		assertNotEquals(blue6Card, green6Card); /*Cards with a different color should not be equal*/
	}
	
	/*Checks to see if card is converted to string properly*/
	@Test
	public void cardString() {
		Card green6Card = new Card(Card.GREEN, 6);
		Card red6Card = new Card(Card.RED, 6);
		Card blue6Card = new Card(Card.BLUE, 6);
		Card yellow6Card = new Card(Card.YELLOW, 6);
		Card wildCard = new Card(Card.WILD, Card.NORMAL_WILD_NUMBER);
		assertEquals(green6Card.toString(), "Green 6");
		assertEquals(red6Card.toString(), "Red 6");
		assertEquals(blue6Card.toString(), "Blue 6");
		assertEquals(yellow6Card.toString(), "Yellow 6");
		assertEquals(wildCard.toString(), "Wild");
	}

}