

public class Tester
{
    // instance variables - replace the example below with your own
    public static void main(String[] args)
    {
        Office testOffice = new Office();
        
        testOffice.setOccupant("Patrick");
        testOffice.setOfficeNumber("THRN 101");
        
        System.out.println(testOffice.getOccupant()+"'s office is in "+testOffice.getOfficeNumber());
        
        Building testBuild=new Building();
        testBuild.addOffice("THRN 101","Patrick");
        
        System.out.println(testBuild.getOccupantName("THRN 101"));
        System.out.println(testBuild.getOccupantName("Will not work"));
        
        System.out.println("Number of Offices: "+testBuild.numOffices());
        
        testBuild.removeOffice("THRN 101", "Patrick");
        testBuild.removeOffice("Will not work","No offices");
        
        System.out.println("Number of Offices: "+testBuild.numOffices());
        
        System.out.println(testBuild.getOccupantName("Will not work"));
        System.out.println("Number of Offices: "+testBuild.numOffices());
        
    }
}
