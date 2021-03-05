import java.util.*;


class Additive_Persistence 
{

    String rules;
    String number;
    int count;

    //creates constructor
    public Additive_Persistence() 
    {
        this.rules = ("\n" + "Additive Persistence: adding the digits of a number together until it becomes a single digit." + 
            " This will count how many iterations each number takes to reach that point." + "\n" + 
            "EX: 19 == 1 + 9 = 10   1 iteration" + "\n" +
            "    10 == 1 + 0 = 1    2 iterations" + "\n" +
            "    19 takes 2 iterations to become a single digit.");
        this.number = null;
        this.count = 0;
    }
    
    //returns da rules
    public String getRules()
    {
        return rules;
    }

    //returns the number given to check additive persistence
    public String getNumber()
    {
        return number;
    }

    //returns the number of iterations (the additive persistence)
    public int getCount()
    {
        return count;
    }

    //gets input from user to get the number they wish to see its additive persisstence
    public String getNewNumber()
    {
        Scanner in = new Scanner(System.in);
        this.number = in.next();
        in.close();
        return number;
    }

    //steps through the given number's additions and counts the iterations. it does the math for additive persistence.
    public void doMath()
    {
        //get arraylist to work with
        String[] inNums = number.split("");
        List<String> numList = new ArrayList<String>(number.length());
        for (String i : inNums)
        {
            numList.add(i);
        }

        //the math. it ends when the number becomes a single digit 
        while (numList.size() != 1)
        {
            int totalValue = 0;

            for (int i = 0; i < numList.size(); i++)
            {
                totalValue = totalValue + Integer.valueOf(numList.get(i));  //adds each digit to each other to get the new number
            }

            String StringValue = String.valueOf(totalValue);
            String[] splitNum = StringValue.split("");
            numList.clear();            //clears the arraylist to work with the new number
            for (String i : splitNum)
            {
                numList.add(i);
            }
            this.count++;
        }
    }

    //mostly just prints the number of iterations it took
    public void end()
    {
        System.out.println("");
        System.out.println(this.getNumber() + "'s additive persistence is " + this.getCount());
        System.out.println("");
    }
}

//main - whatever the style is called wherein main just calls stuff instead of doing the work inside it. 
class gameDemo
{
    public static void main(String[] args)
    {
        Additive_Persistence newDemo = new Additive_Persistence();

        System.out.println(newDemo.getRules());
        System.out.println("Please enter number to get its additive persistence: ");

        newDemo.getNewNumber();
        newDemo.doMath();
        newDemo.end();  
    }
}   

//probably could have used recursion to be more efficient