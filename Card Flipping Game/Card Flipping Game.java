import java.util.*;

class cardgame 
{
    private String rules;
    private String cards;
    private int length;

    /* Constructor for the cards*/
    public cardgame()
    {
        this.rules = "Object of the game is to remove all the cards, but you can only remove face up cards." + "\n" 
                        + "When you remove a card (face up) the adjacent cards are flipped." + "\n" 
                        + "1 = face up card, 0 = face down card" + "\n";
        this.cards = null;
        this.length = 0;
    }

    /* returns the set rules */
    public String getRules()
    {
        return rules;
    }

    /* Prints the rules before the game begins */
    public void PrintRules()
    {
        System.out.println(getRules());
    }

    /* 
    Gets the order / number of the cards from the user 
    Param   inCards     The set of cards that will be used in the game
    */
    public void setCards(String inCards)
    {
        this.cards = inCards;
        length = cards.length();
    }

    /*Returns the cards as a String */
    public String getCards()
    {
        return cards;
    }

    /*Returns the length */
    public int getLength()
    {
        return length;
    }

    public Boolean checkDeck()
    {
        boolean correct = false;
        boolean playable = false; 
        Scanner inCheck = new Scanner(System.in);

        //check if only 0s and 1s, will only leave loop if so
        while (playable != true && correct != true)
        {
            System.out.println("Please enter card order using ONLY '0's and '1's. Must have at least one '1'. (EX: 00101011)");
            
            String newCards = inCheck.next();
            System.out.println("");       //just for readability

            this.setCards(newCards);

            //begin checks
            for (int i = 0; i < newCards.length(); i++)
            {
                char c = newCards.charAt(i);
                if (c == '0' || c == '1')
                {
                    correct = true;
                }
                else            //if the char at i is not 0 or 1, the loop breaks and asks for the card order again
                {
                    correct = false;
                    playable = false;
                    break;
                }

                if (c == '1')  //needs to have at least one "1" to be playable
                {
                    playable = true;
                }
            }
        }
        return true;
    }
}



/* The actual game*/
class gameDemo
{
    public static void main(String[] args) 
    {
        System.out.println("\n");           //just for readability
        

        cardgame newGame = new cardgame();  //creates card game   
        boolean correct = false;
        

        newGame.PrintRules();

        newGame.checkDeck();
        boolean playable = true;            //can only be true if it passes the deckCheck
        



        //create arrayList to work with
        String[] stringCardList = newGame.getCards().split("");
        List<String> cardList = new ArrayList<String>(newGame.getLength());
        for (String i : stringCardList)
        {
            cardList.add(i);
        }




        //begin game
        while (cardList.size() > 1 && playable == true)         //while there is still a "1" in the deck and more than one card
        {
            System.out.println("Your cards are: " + cardList);
            System.out.println("Choose which face up card to remove");

            
            correct = false;
            int cardNum = 100;
            int c = 100;


            //choose the card to remove
            while (correct != true)
            {
                Scanner in = new Scanner(System.in);
                String cardPlace = in.next();
                System.out.println("");       //just for readability

                
                if (cardPlace.matches("[0-9]+") == true)        //make sure only numbers are entered
                {
                    correct = true;
                    c = Integer.valueOf(cardPlace);
                }

                if (c >= cardList.size() || c < 0)              //make sure its a number that can be actually picked
                {
                    System.out.println("You must choose an actual card number (start at 0)");
                    System.out.println("Your cards are: " + cardList);
                    correct = false;
                }
                else if (correct == true && cardList.get(c).equals("1"))                     //makes sure that number is a 1 in the deck
                {

                    cardNum = c;
                }
                else
                {
                    System.out.println("You must choose a face up card (1)");
                    System.out.println("Your cards are: " + cardList);
                    correct = false;
                }
            }


            //check if chosen card is an end card (first or last), if it is then only the appropriate card is flipped
            boolean endCard = false;
            if (cardNum == (cardList.size() - 1))       //then the last card was chosen
            {
                endCard = true;
                String leftCard = cardList.get(cardNum - 1); 
                int leftElement = (cardNum - 1); 
                if (leftCard .equals("1"))
                {
                    cardList.set(leftElement, "0");
                }
                else
                {
                    cardList.set(leftElement, "1");
                }
            }
            else if (cardNum == 0)                      //then the first card was chosen
            {
                endCard = true; 
                String rightCard = cardList.get(cardNum + 1);
                int rightElement = (cardNum + 1);
                if (rightCard.equals("1"))
                {
                    cardList.set(rightElement, "0");
                }
                else
                {
                    cardList.set(rightElement, "1");
                }
            }
            
            //if its a card in the middle, flip both adjacent cards 
            if (endCard == false)
            {
                String leftCard = cardList.get(cardNum - 1); 
                int leftElement = (cardNum - 1); 
                String rightCard = cardList.get(cardNum + 1);                   //correct order for changing adjacent
                int rightElement = (cardNum + 1);

                //left card
                if (leftCard.equals("1"))
                {
                    cardList.set(leftElement, "0");
                }
                else 
                {
                    cardList.set(leftElement, "1");
                }

                //right card
                if (rightCard.equals("1"))
                {
                    cardList.set(rightElement, "0");
                }
                else
                {
                    cardList.set(rightElement, "1");
                }
            }
            //Adjacent cards now flipped - now removes chosen card and shortens list


            cardList.remove(cardNum);           //removes the chosen card

            //if there is a single "1" left in the cards, there are still moves to play 
            playable = false; 
            for (int i = 0; i < cardList.size(); i++)
            {
                if (cardList.get(i).equals("1"))
                {
                    playable = true;
                }
            }
        }

        //check if the only card left, then determine if win or loss
        if (cardList.size() == 1)
        {
            if (cardList.get(0).equals("1"))
            {
                System.out.println("Your cards are: " + cardList);
                System.out.println("\n" + "Congratulations, you are out of cards - Win");
            }
            else
            {
                System.out.println("Your cards are: " + cardList);
                System.out.println("\n" + "You are left with a single face-down card - loss");
            }
        }
        else
        {
            System.out.println("Your cards are: " + cardList);
            System.out.println("\n" + "There are no more cards to play - loss");
        }


        System.out.println(""); //just for readability
    }
}


//tested with different inputs, dont know how to create unit tests in this IDE yet. SHOULD be unbreakable. 
//  also dunna how to close scanner but allow it to reopen without crashing

//optional:
//  create helper to show examples of correct decks / moves 
//  create auto solver to determine if the deck is beatable before inputting moves 
//  create optional tutorial