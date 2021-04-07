import static org.junit.Assert.*;
import org.junit.Test;




public class testAdditive_Persistence 
{
    String per0 = "0";
    String per1 = "10";
    String per2 = "19";
    String per3 = "199";
    String per4 = "19999999999999999999999";
    Additive_Persistence test = new Additive_Persistence();

    //testing to see if constructor is created
    @Test
    public void testConstructor()
    {
        assertNotNull(test);
    }


    //testing to see if getters work
    @Test
    public void testGets()
    {
        assertEquals(0, test.getCount());
        assertEquals(null, test.getNumber());
    }

    
    //tests the math involved, makes sure gives correct persistence
    @Test
    public void testDoMath()
    {
        test.setNumber(per0);
        assertEquals(0, test.doMath(per0));
        test.resetCount();

        test.setNumber(per1);
        assertEquals(1, test.doMath(per1));
        test.resetCount();

        test.setNumber(per2);
        assertEquals(2, test.doMath(per2));
        test.resetCount();

        test.setNumber(per3);
        assertEquals(3, test.doMath(per3));
        test.resetCount();

        test.setNumber(per4);
        assertEquals(4, test.doMath(per4));
        test.resetCount();
    }
}