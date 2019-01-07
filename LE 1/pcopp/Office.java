import java.util.*;
/**
 * Write a description of class Office here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Office
{
    // instance variables - replace the example below with your own
    private String officeNumbers;
    private String occupant;
    
    public Office()
    {
        
        this("none","none");
    }
    public Office(String officeNumber,String nameOfOccupant)
    {
        officeNumbers=officeNumber;
        occupant=nameOfOccupant;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setOfficeNumber(String officeNumber)
    {
        officeNumbers=officeNumber;
    }
    public String getOfficeNumber()
    {
        return officeNumbers;
    }
    public void setOccupant(String nameOfOccupant)
    {
        occupant=nameOfOccupant;
    }
    public String getOccupant()
    {
        return occupant;
    }
    
}
