package bootstrap;



import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Degree
{
	private String DegreeTitle;
	private ArrayList<Course> RequiredCourses;
	private boolean meetsRequirements;
	private double numberOfCreditsRemaining;
	private ArrayList<Course> remainingRequiredCourses;
	
	public Degree()
	{
		DegreeTitle="";
		RequiredCourses=new ArrayList<>();
		meetsRequirements=false;
		numberOfCreditsRemaining=15;
		remainingRequiredCourses=new ArrayList<>();
	}
	
	
	public String getDegreeTitle()
	{
		return DegreeTitle;
	}
	
	public void setDegreeTitle(String title)
	{
		DegreeTitle=title;
	}
	
	public void setRequiredCourses(ArrayList<String> listOfRequiredCourseCodes)
	{
		CourseCatalog catalog=new CourseCatalog();
		
		catalog.initializeCatalog("course.csv");
		
		for(String code: listOfRequiredCourseCodes)
		{
			for(Course course: catalog.getCatalog())
			{
				if(course.getCourseCode().equals(code))
				{
					RequiredCourses.add(course);
				}
			}
		}
	}

	public ArrayList<Course> getRequiredCourses()
	{
		return RequiredCourses;
	}
	
	public boolean meetsRequirements(PlanOfStudy thePlan)
	{
		return meetsRequirements;
	}
	
	public double numberOfCreditsRemaining(PlanOfStudy thePlan)
	{
		return numberOfCreditsRemaining;
	}

	public ArrayList <Course> remainingRequiredCourses(PlanOfStudy thePlan)
	{
		return remainingRequiredCourses;
	}

	public void initializeDegree(String[] courseInfo)
	{
		ArrayList <String> requiredString= new ArrayList<String>();
		
		for(int i=1;i<courseInfo.length;i++)
        {
        	requiredString.add(courseInfo[i]);
        }
        
		setRequiredCourses(requiredString);
		
	}

	public void saveDegree()
	{
		try
		{
			String filename= "catalog.csv";
		    FileWriter fw = new FileWriter(filename,true);
		    fw.write(toString());
		    fw.close();
			
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String toString()
	{
		String toReturn=(DegreeTitle);
		toReturn+=",";
		for(Course course: RequiredCourses)
		{
			toReturn+=course.getCourseCode()+",";
		}
		toReturn=toReturn.substring(0,toReturn.length()-1);
		toReturn+="\n";
		return toReturn;
	}
	
	public void setCreditsRemaining(double number)
	{
		numberOfCreditsRemaining=number;
	}

	

}
