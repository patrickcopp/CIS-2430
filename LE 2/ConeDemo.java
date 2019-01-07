
/**
 * Write a description of class ConeDemo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;

public class ConeDemo
{
    
    
    public static void main(String[] args)
    {
        IceCreamCone newCone= new IceCreamCone();
        newCone.setFlavour("Vanilla");
        
        Scanner sc=new Scanner (System.in);
        String userInput="";
        while(!userInput.equalsIgnoreCase("regular") && !userInput.equalsIgnoreCase("sugar")) //while user input is not one of the options
        {
            System.out.println("What type of cone (regular, sugar) are you buying today: ");
            userInput=sc.next();
        }
        
        newCone.setCone(userInput.toUpperCase());
        
        while(!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3")) //while user input is not a choice
        {
            System.out.println("How many scoops would you like (1,2,3): ");
            userInput=sc.next();
        }
        
        if(userInput.equals("1"))
        {
            newCone.setScoops(1);
        }
        else if(userInput.equals("2"))
        {
            newCone.setScoops(2);
        }
        else
        {
            newCone.setScoops(3);
        }
        
        double cost=newCone.totalCost();
        
            
        
        
    }
}
