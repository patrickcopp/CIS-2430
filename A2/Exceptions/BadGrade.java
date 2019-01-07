package Exceptions;



 @SuppressWarnings("serial")
public class BadGrade extends Exception 
 {
   public BadGrade()
   {
      System.out.println("Choose one of: P, F, INC, MNR, or a number between 0, 100.");
   } 
   
 }