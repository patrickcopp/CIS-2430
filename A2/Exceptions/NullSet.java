package Exceptions;



 @SuppressWarnings("serial")
public class NullSet extends Exception 
 {
   public NullSet()
   {
      System.out.println("Attempted to set to a value of null. Returning without changes.");
   } 
   
 }