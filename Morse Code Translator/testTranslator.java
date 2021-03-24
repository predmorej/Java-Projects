import static org.junit.Assert.*;


import org.junit.Test;


import java.util.*;




public class testTranslator
{
    translator codeLists = new translator();
    String testString = "example of a string";
    String testMorseCode = ". -..- .- -- .--. .-.. .   --- ..-.   .-   ... - .-. .. -. --.";
    

    List<String> alphabetList = new ArrayList<String>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", 
                                                                    "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));

    List<String> morseList = new ArrayList<String>(Arrays.asList(".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", 
                                                                 "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."));
    
    
                                                                 


    @Test
    public void testConstructor()
    {
        assertNotNull(codeLists);
        assertEquals(alphabetList, codeLists.alphabet);
        assertEquals(morseList, codeLists.morseCode);
    }

    @Test
    public void testAbcToMorse()
    {
        int length = testString.length();
        String[] toTranslate = testString.split("");
        List<String> listToTranslate = new ArrayList<String>(length);
        for (String i : toTranslate)
        {
            listToTranslate.add(i);
        }

        assertEquals(". -..- .- -- .--. .-.. .   --- ..-.   .-   ... - .-. .. -. --. ", codeLists.abcToMorseFormatted(listToTranslate));
        assertEquals(". -..- .- -- .--. .-.. .   /  --- ..-.   /  .-   /  ... - .-. .. -. --. ", codeLists.abcToMorseSimple(listToTranslate));
    }


    @Test
    public void testMorseToAbc()
    {
        int length = testMorseCode.length();

        //create arrayList from given string
        String[] toTranslate = testMorseCode.split("");
        List<String> listToTranslate = new ArrayList<String>(length);
        for (String i : toTranslate)
        {
            listToTranslate.add(i);
        }
        assertEquals(testString, codeLists.morseToAbc(listToTranslate));
    }
}
