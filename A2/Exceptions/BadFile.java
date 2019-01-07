package Exceptions;



 @SuppressWarnings("serial")
public class BadFile extends Exception 
 {
   public BadFile()
   {
      System.out.println("File not compatible");
   } 
   
 }