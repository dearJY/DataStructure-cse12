/*
 * name: Jingyi Ouyang
 * ID: A53108909
 * Login: cs12feg
 */

package hw1;

import org.junit.*;
import static org.junit.Assert.*;


public class StatsTest {

	
		private Stats stat;

		/* this sets up the test fixture. JUnit invokes this method before
	 	   every testXXX method.  
	 	   The @Before tag tells JUnit to run this method 
		   before each test */
		@Before
		public void setUp() throws Exception {
			stat = new Stats(1,2,3);
		}
		
		/* The @Test tag tells JUnit this is a test to run */
		@Test
		public void testgetTotalGames() {
			System.out.println("Checking getTotalGames");
			assertEquals(6, stat.getTotalGames());
		}

		@Test
		public void testIncrements() {
			System.out.println("Checking Proper Increment");
			stat.incrementComputerWins();
			assertEquals(7, stat.getTotalGames());
			stat.incrementUserWins();
			assertEquals(8, stat.getTotalGames());
			stat.incrementTies();
			assertEquals(9, stat.getTotalGames()); 
		}
	

		@Test
		public void testReset() {
			System.out.println("Checking Reset");
			stat.reset();
			assertEquals(0, stat.getTotalGames());
		}

		@Test
		public void testAverage() {
			System.out.println("Checking Average");
			if( stat.getTotalGames() == 0 ) {
				assertEquals(0, stat.averageGames(0));
				assertEquals(0, stat.averageGames(1));
				assertEquals(0, stat.averageGames(2));
			}else {
				assertEquals(16, stat.averageGames(0));
				assertEquals(33, stat.averageGames(1));
				assertEquals(50, stat.averageGames(2));
			}
			
		}

		@Test
		public void testRestWrong() {
			System.out.println("Checking Rest stat Wrongly");
			stat.resetWrong();
			assertEquals(0, stat.getTotalGames());
		}
}

