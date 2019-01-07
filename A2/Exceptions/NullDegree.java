package Exceptions;



 @SuppressWarnings("serial")
public class NullDegree extends Exception 
 {
   public NullDegree()
   {
      System.out.println("User did not select a degree. Reopen program.");
   } 
   
 }