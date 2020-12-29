package uno;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CardSetTest.class, CardTest.class, CustomRulesTest.class, GameServerTest.class, PlayerTest.class,
		SpecialCardTest.class })
public class AllTests {

}
