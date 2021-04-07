import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

public class testCardGame 
{
    cardgame test = new cardgame();
    String sampleDeck = "0101110101";
    String winningDeck = "011";             //moves = 1,0

    @Test
    public void testConstructor()
    {
        assertNotNull(test);
    }

    @Test
    public void testSetterAndGetters()
    {
        test.setCards(sampleDeck);
        assertEquals(sampleDeck, test.getCards());
        assertEquals(10, test.getLength());
    }

    @Test
    public void testCheckDeck()
    {
        assertEquals(true, test.checkDeck());   //stil needs input
    }
}

//game itself involes input throughout, must check manually 
