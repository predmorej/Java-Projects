import java.util.*;


class translator
{
    public translator()
    {
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
       
        String[] morseCode = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", 
                              "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
    }

    public void abcToDot(String inString)
    {
        int length = inString.length();

        //create arrayList from given string
        String[] toTranslate = inString.split("");
        List<String> listToTranslate = new ArrayList<String>(length);
        for (String i : toTranslate)
        {
            listToTranslate.add(i);
        }


        System.out.println(translator().morseCode[0]);              //figure out how to get the array from the constructor


        for (int i = 0; i < length; i++)
        {
            //String toSwap = listToTranslate.get(i);
        }
    }

    public void dotToAbc(String inString)
    {
        //
    }
}



class demoTranslator
{
    public static void main(String[] args)
    {
        translator codeLists = new translator();

        System.out.println("Enter sentence to be translated: ");

        Scanner in = new Scanner(System.in);
        String toBeTranslated = in.nextLine();
        in.close();
        if (toBeTranslated.charAt(0) == '-' || toBeTranslated.charAt(0) == '.') 
        {
            //then its morsecode to abc
        }
        else
        {
            codeLists.abcToDot(toBeTranslated);
        }
    }
}