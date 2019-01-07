
/**
 * Write a description of class IceCreamCone here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IceCreamCone
{
   
    private int scoops;
    private String coneType;
    private static String flavour; // static type, assuming we have 1 flavour per day which will be changed

    /**
     * Constructor for objects of class IceCreamCone
     */
    public IceCreamCone() //default constructor
    {
        this.scoops=0;
        this.coneType=null;
        this.flavour=null;
        
    }
    public IceCreamCone(String flavour2) //for anonymous object, so it knows the flavour of the day
    {
        scoops=0;
        coneType=null;
        flavour=flavour2;
    }
    
    public enum CONE
    {
        REGULAR,SUGAR  //enum containg cone types
    }
    
    
    public void setFlavour(String flav)
    {
        flavour=flav;
    }
    
    public void setScoops(int numberOfScoops)
    {
        scoops=numberOfScoops;
    }
    
    public int getScoops()
    {
        return scoops;
    }
    
    public void setCone(String coneUser)
    {
        CONE tryCone=null;
        try
        {
            tryCone=CONE.valueOf(coneUser);
            
            coneType=coneUser;

        }
        catch(Exception e)
        {
            System.out.println("Set Cone not working");
        
        }
        
    }
    
    public String getCone()
    {
        return coneType;
    }
        
    public double totalCost()
    {
        if(coneType.equals("REGULAR"))
        {
            System.out.println(new IceCreamCone(flavour).printTotal(2*scoops));
            return 2*scoops; //$2 per scoop
        }
        else
        {
            System.out.println(new IceCreamCone().printTotal(2*scoops+1));
            return 2*scoops+1; //SUGAR CONES COST EXTRA
        }
    }
    
    public String printTotal(double amount) //anonymous method
    {
        
        return ("Total cost of "+flavour+" cone = "+amount);
    
    }
    
}
