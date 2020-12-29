package uno;


import org.junit.Test;

public class PlayerTest {

	@Test
	public void playerConstructorTest() {
		Player player = new Player(1);
		assert(player.getPlayerPlace() == 1);
	}

}
