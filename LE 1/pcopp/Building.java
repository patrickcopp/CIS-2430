import java.util.*;
/**
 * Write a description of class Building here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Building
{
    private ArrayList <Office> officeList;

    /**
     * Constructor for objects of class Building
     */
    public Building()
    {
        officeList= new ArrayList<>();
    }

    
    public void addOffice(String officeNumber, String nameOfOccupant)
    {
        
        if(officeNumber!=null && nameOfOccupant!=null && officeNumber!="" && nameOfOccupant!="")
        {
            Office newOffice = new Office(officeNumber,nameOfOccupant);
            if(officeList.contains(newOffice))
            {
                System.out.println("Already inside building.");
            }
            else
            {
                officeList.add(newOffice);
            }
        }
        else
        {
            System.out.println("Error: Insert correct input.");
        }
    }
    public void removeOffice(String officeNumber, String nameOfOccupant)
    {
        if(officeList!=null)
        {
            for(int i=0; i<officeList.size();i++)
            {
                Office findOccupant= new Office();
                findOccupant=officeList.get(i);
                if(findOccupant.getOfficeNumber()==officeNumber && findOccupant.getOccupant()==nameOfOccupant)
                {
                    officeList.remove(findOccupant);
                    return;
                }
            }
            System.out.print("Office not found");
            
        }
        else
        {
            System.out.println("This building is empty.");
            
        }
    }
    public String getOccupantName(String officeNumber)
    {
        if(officeList!=null)
        {
            for(int i=0; i<officeList.size();i++)
            {
                Office findOccupant= new Office();
                findOccupant=officeList.get(i);
                if(findOccupant.getOfficeNumber()==officeNumber)
                {
                    return findOccupant.getOccupant();
                }
            }
            System.out.println("Office number not found");
            return "";
        }
        else
        {
            System.out.println("This building is empty.");
            return "";
        }
            
    }
    public int numOffices()
    {
        if(officeList!=null)
        {
            return officeList.size();
        }
        else
        {
            return 0;
        }
    }
    
}
