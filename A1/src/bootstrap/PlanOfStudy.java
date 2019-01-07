package bootstrap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PlanOfStudy
{
	private Degree major;
	private ArrayList<Course> myCourses;
	private CourseCatalog catalog;
	
	public PlanOfStudy()
	{
		major=null;
		myCourses = new ArrayList<Course>();
		catalog = new CourseCatalog();
	}
	
	
	public void setDegreeProgram(Degree deg)
	{
		major=deg;
	}
	
	public Degree getDegreeProgram()
	{
		return major;
	}
	
	public void importData(String filename)
	{
		BufferedReader br = null;
		String line="";
		String csvSplitBy = ",";
		
		try
		{
			
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null)
            {
				Course transCourse=new Course();
				String[] courseStuff = line.split(csvSplitBy);
				
				transCourse.setCourseCode(courseStuff[0]);
				transCourse.setCourseStatus(courseStuff[1]);
				transCourse.setCourseGrade(courseStuff[2]);
				transCourse.setSemesterTaken(courseStuff[3]);
				
				for(Course courses: catalog.getCatalog())
				{
					if(courses.getCourseCode().equals(transCourse.getCourseCode()))
					{
						myCourses.add(transCourse);
					}
				}
            
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
		try
		{
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void saveState()
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter("newTranscript.csv"));
	        writer.write(toString());
			writer.close();
			System.out.println("State saved.");
		}
		
		catch (IOException e)
		{
			System.out.println("Error: State not saved.");
		}
	}
	
	public void addCourse(String courseCode,String semester)
	{
		Course newCourse=catalog.findCourse(courseCode);
		if(newCourse==null)
		{
			System.out.println("Course not found.");
			return;
		}
		newCourse.setSemesterTaken(semester);
		myCourses.add(newCourse);
	}
	
	public void removeCourse(String courseCode, String semester)
	{
		Course remove =new Course();
		remove=getCourse(courseCode,semester);
		myCourses.remove(remove);
	}
	
	public void setCourseStatus(String courseCode,String semester,String courseStatus)
	{
		for(Course courses: myCourses)
		{
			if(courses.getCourseCode().equals(courseCode))
			{
				courses.setSemesterTaken(semester);
				courses.setCourseStatus(courseStatus);
			}
		}
	}
	
	public void setCourseGrade(String courseCode,String semester,String grade)
	{
		for(Course courses: myCourses)
		{
			if(courses.getCourseCode().equals(courseCode))
			{
				courses.setSemesterTaken(semester);
				courses.setCourseGrade(grade);
			}
		}
	}
	
	public Course getCourse(String courseCode, String semester)
	{
		Course courseHold=null;
		for(Course course: myCourses)
		{
			if(course.getCourseCode().equals(courseCode) && course.getSemesterTaken().equals(semester))
			{
				courseHold=course;
			}
		}
		
		return courseHold;
	}
	
	public String toString()
	{
		try
		{
			String toReturn="";
			toReturn=(major.getDegreeTitle()+"\n");
			
			for(Course courses: myCourses)
			{
				toReturn+=(courses.getCourseCode()+","+courses.getCourseStatus()+","+courses.getCourseGrade()+","+courses.getSemesterTaken()+","+courses.getElectiveOrMajor()+"\n");
			}
			
			return toReturn;
		}
		
		catch (NullPointerException e)
		{
			System.out.println("Error: State not saved.");
			return null;
		}
		
	}
	
	public void setCatalog(CourseCatalog newCatalog)
	{
		catalog=newCatalog;
	}
	
	public ArrayList<Course> getMyCourses()
	{
		return myCourses;
	}
}
