import java.util.*;


class translator
{
    List<String> alphabet;
    List<String> morseCode;

    public translator()
    {
        this.alphabet = new ArrayList<String>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", 
                                                                "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
        this.morseCode = new ArrayList<String>(Arrays.asList(".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", 
                                                             "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."));
    }

    
    //begin translating process
    public String translate(String inString)
    {
        String translated = "";
        int length = inString.length();
        inString = inString.toLowerCase();

        //create arrayList from given string
        String[] toTranslate = inString.split("");
        List<String> listToTranslate = new ArrayList<String>(length);
        for (String i : toTranslate)
        {
            listToTranslate.add(i);
        }
        
        //Choose method of swapping (abc to morse - simple or formatted, or morse to abc)
        if (listToTranslate.get(0).equals("-") || listToTranslate.get(0).equals("."))     //if morse code to abc
        {
            translated = morseToAbc(listToTranslate);           
        }
        else                                                                              //else is abc to morse code
        {
            //ask what form they want the morse code in, simple or formatted
            System.out.println("");                                                       //for readability
            System.out.println("Formatted? Y/N");
            Scanner inAnswer = new Scanner(System.in);
            String Answer = inAnswer.next();

            Answer = Answer.toLowerCase();
            inAnswer.close();

            if (Answer.equals("y"))     //formatted
            {
                translated = abcToMorseFormatted(listToTranslate);
            }
            else                        //reader friendly
            {
                translated = abcToMorseSimple(listToTranslate);
            }
        }

        System.out.println("");         //for output readability
        System.out.println(translated);
        System.out.println("");         //for output readability
        return translated;
        
    }


    //Translate a string of letters into a simple more readable morse code (seperated from the formatted version to help testing)
    public String abcToMorseSimple(List<String> inList)                         
    {
        int length = inList.size();

        //StringBuilder for output
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++)
        {
            String toSwap = inList.get(i);
            int swapPlacement = alphabet.indexOf(toSwap);
            if (swapPlacement != -1)
            {
                String swapped = morseCode.get(swapPlacement);
                builder.append(swapped);
                builder.append(" ");            //adds spaces between "letters" to make it more readable
            }
            else if (toSwap.equals(" "))
            {
                builder.append("  /  ");        //adds double spaces in addition to '/' to make it more readable and tell the difference between spaces and letters
            }
            else
            {
                builder.append(toSwap + " ");         //if not a letter or a space, just adds whatever as a new "letter"
            }
        }
        return builder.toString();
    }


    //Translate a string of letters into a formatted morse code (seperated from the simple version to help testing)
    public String abcToMorseFormatted(List<String> inList)
    {
        int length = inList.size();

        //StringBuilder for output
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++)
        {
            String toSwap = inList.get(i);
            int swapPlacement = alphabet.indexOf(toSwap);
            if (swapPlacement != -1)
            {
                String swapped = morseCode.get(swapPlacement);
                builder.append(swapped);
                builder.append(" ");            //adds spaces between "letters" to make it more readable
            }
            else if (toSwap.equals(" "))
            {
                builder.append("  ");           //adds double spaces for format
            }
            else
            {
                builder.append(toSwap + " ");         //if not a letter or a space, just adds whatever as a new "letter"
            }
        }
        return builder.toString();
    }


    //Translates morse code back into letters
    public String morseToAbc(List<String> inList)
    {
        int length = inList.size();

        //StringBuilder for output
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++)
        {
            //Creates new StringBuilder to get all dots / dashes included for each letter
            StringBuilder dots = new StringBuilder();
            dots.append(inList.get(i));
            int j = i + 1;
            while (j != length && (!inList.get(j).equals(" ")))    //gets all characters until it hits a space or the end of the input string
            {
                dots.append(inList.get(j));
                j++;
            }
            i = j;      //effectively skips over the morse code in the string that is being translated

            //continue same as abc to morse code
            String toSwap = dots.toString();
            int swapPlacement = morseCode.indexOf(toSwap);
            if (swapPlacement != -1)
            {
                String swapped = alphabet.get(swapPlacement);
                builder.append(swapped);
            }
            else
            {
                builder.append(" ");        //adds the space between words
            }
        }
        return builder.toString();
    }
}





class demoTranslator
{
    public static void main(String[] args)
    {
        translator codeLists = new translator();

        System.out.println("");         //for output readability
        System.out.println("NOTICE: will not translate symbols or numbers from morse code to letters.");
        System.out.println("NOTICE: if entering morse code, must be in the format of: " + 
                           " .- -.   . -..- .- -- .--. .-.. .  (spaces between characters and double spaces for between words, using only '.' and '-')");
        System.out.println("Enter sentence to be translated: ");

        Scanner in = new Scanner(System.in);
        String toBeTranslated = in.nextLine();
        codeLists.translate(toBeTranslated);
        in.close();
    }
}