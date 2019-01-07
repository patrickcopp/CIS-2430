
package bootstrap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 *
 * @author Patrick Copp
 */

public class BootStrap
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
    	Scanner in = new Scanner(System.in);
    	
        CourseCatalog catalog=new CourseCatalog();
        
        System.out.println("Enter a filename for a course catalog: ");
        String user=in.next();
        
        catalog.initializeCatalog(user);
        catalog.saveCatalog();
        
    	BufferedReader br = null;
    		
        String csvSplitBy = ",";
        String line = "";
            
        try
        {
        	System.out.println("Enter a filename for a degree or 'q' to quit: ");
        	user=in.next();
        	
        	if(user.equals("q"))
        	{
        		in.close();
        		return;
        	}
        	
        	br = new BufferedReader(new FileReader(user));
            
            
            while ((line = br.readLine()) != null)
            {
            	Degree newDegree=null;
            	
                line.replaceAll("\\s+", "");
                
                String[] courseStuff = line.split(csvSplitBy);
                    
                if(courseStuff[0].equalsIgnoreCase("BCG"))
                {
                	System.out.println("BCG");
                    BCG degreeBCG=new BCG();
                    newDegree=degreeBCG;
                    newDegree.setDegreeTitle("BCG");
                }
                    
                else if(courseStuff[0].equalsIgnoreCase("SEng"))
                {
                	System.out.println("SEng");
                    SEng degreeSEng=new SEng();
                    newDegree=degreeSEng;
                    newDegree.setDegreeTitle("SEng");
                }
                    
                else if(courseStuff[0].equalsIgnoreCase("CS"))
                {
                	System.out.println("CS");
                    CS degreeCS=new CS();
                    newDegree=degreeCS;
                    newDegree.setDegreeTitle("CS");
                }
                else
                {
                    System.out.println("File invalid: Degree is not proper. Use 'CS', 'SEng', or 'BCG'");
                    br.close();
                    break;
                }
                
                newDegree.initializeDegree(courseStuff);
                newDegree.saveDegree();
                
                System.out.println("Enter a filename for a degree or 'q' to quit: ");
            	user=in.next();
            	
            	if(user.equals("q"))
            	{
            		break;
            	}
            	
            	br = new BufferedReader(new FileReader(user));
            	
                
            }
            
            try
            {
                br.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
                
              
            }
            catch (FileNotFoundException e)
            {
            	e.printStackTrace();
            }
            catch (IOException e)
            {
            	e.printStackTrace();
            }
        in.close();
    		 
    }
        
}
    
