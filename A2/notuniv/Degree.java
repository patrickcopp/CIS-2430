package notuniv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import univ.Course;
import univ.CourseCatalog;

public class Degree
{
	private String DegreeTitle;
	private ArrayList<Course> RequiredCourses;
	private double requiredCredits;
	private CourseCatalog TotalCatalog; 

	public Degree(CourseCatalog newCatalog)
	{
		DegreeTitle="";
		RequiredCourses=new ArrayList<Course>();
		TotalCatalog=newCatalog;
	}
	
	
	public String getDegreeTitle()
	{
		return DegreeTitle;
	}
	
	public void setDegreeTitle(String title)
	{
		DegreeTitle=title;
	}
	
	public ArrayList<Course> getRequiredCourses()
	{
		return RequiredCourses;
	}
	
	public void setRequiredCourses(ArrayList<Course> listOfRequiredCourseCodes)
	{
		if(DegreeTitle.equals("BCG"))
		{
			requiredCredits=15;
			RequiredCourses.add(TotalCatalog.findCourse("CIS*1500"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*1910"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2430"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2500"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2520"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2750"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2910"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*3530"));
		}
		else if(DegreeTitle.equals("CS"))
		{
			requiredCredits=20;
			RequiredCourses.add(TotalCatalog.findCourse("CIS*1500"));
			RequiredCourses.add(TotalCatalog.findCourse("MATH*1200"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*1910"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2500"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2030"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2430"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2520"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2910"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2750"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*3110"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*3490"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*3150"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*3750"));
			RequiredCourses.add(TotalCatalog.findCourse("STAT*2040"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*3760"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*4650"));
		}
		else if(DegreeTitle.equals("SEng"))
		{
			requiredCredits=20;
			RequiredCourses.add(TotalCatalog.findCourse("CIS*1250"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*1500"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*1910"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2250"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2500"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2030"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2430"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2520"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*3250"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*2750"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*3110"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*3490"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*3750"));
			RequiredCourses.add(TotalCatalog.findCourse("STAT*2040"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*3760"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*3260"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*4150"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*4300"));
			RequiredCourses.add(TotalCatalog.findCourse("CIS*4250"));
		}
		
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

	public double getRequiredCredits()
	{
		return requiredCredits;
	}

	public Boolean meetsRequirements(ArrayList<Course>allTheCoursesPlannedAndTaken)
	{
		double sum=0;
		for(Course all: allTheCoursesPlannedAndTaken)
		{
			sum+=all.getCourseCredit();
		}
		if(sum<getRequiredCredits())
		{
			return false;
		}
		
		boolean found=false;
		ArrayList<Course> neither = new ArrayList<Course>();
		for(Course checkCourse: getRequiredCourses())
		{
			for(Course all: allTheCoursesPlannedAndTaken)
			{
				if(all.equals(checkCourse))
				{
					found=true;
				}
			}
			if(!found)
			{
				neither.add(checkCourse);
			}
			found=false;
		}
		
		if(neither.size()!=0)
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}

	public double numberOfCreditsRemaining(ArrayList<Course> allTheCoursesPlannedAndTaken)
	{
		int sum=0;
		for(Course count: allTheCoursesPlannedAndTaken)
		{
			sum+=count.getCourseCredit();
		}
		return getRequiredCredits()-sum;
	}

	public ArrayList<Course> remainingRequiredCourses(ArrayList<Course> allTheCoursesPlannedAndTaken)
	{
		boolean found=false;
		ArrayList<Course> neither = new ArrayList<Course>();
		for(Course checkCourse: getRequiredCourses())
		{
			for(Course all: allTheCoursesPlannedAndTaken)
			{
				if(all.equals(checkCourse))
				{
					found=true;
				}
			}
			if(!found)
			{
				neither.add(checkCourse);
			}
			found=false;
		}
		
		return neither;
		
	}

	public boolean equals(Degree otherDegree)
	{
		boolean check = true;
		if(!otherDegree.getDegreeTitle().equals(getDegreeTitle()))
		{
			check = false;
		}
		else if(!otherDegree.getRequiredCourses().equals(getRequiredCourses()))
		{
			check = false;
		}
		else if(otherDegree.getRequiredCredits()!=getRequiredCredits())
		{
			check = false;
		}
		
		return check;
	}
}
