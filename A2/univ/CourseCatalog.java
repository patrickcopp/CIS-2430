package univ;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Exceptions.NullSet;


public class CourseCatalog
{
	protected ArrayList<Course>Catalog;
	
	public CourseCatalog()
	{
		Catalog=new ArrayList<>();
	}
	
	protected void addCourse(Course toAdd)
	{
		if(toAdd==null)
		{
			try {
				throw new NullSet();
			} catch (NullSet e) {
				e.printStackTrace();
				return;
			}
		}
		Catalog.add(toAdd);
		
	}

	public Course findCourse(String courseCode)
	{
		for(Course course: Catalog)
        {
        	if(course.getCourseCode().equals(courseCode))
        	{
        		return course;
        	}
        }
		return null;
	}
	
	public ArrayList<Course> getCatalog()
	{
		return Catalog;
	}
	
	public void removeCourse(Course toRemove)
	{
		if(toRemove!=null)
		{
			Catalog.remove(toRemove);
		}
		else
		{
			try {
				throw new NullSet();
			} catch (NullSet e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
	public boolean isEmpty()
	{
		if(Catalog.size()==0)
		{
			return true;
		}
		return false;
	}
	
	public String toString()
	{
		String toReturn="";
		for(Course course: getCatalog())
        {
        	toReturn+=course.toString();
        	toReturn+="\n";
        }
		return toReturn;
	}

	public void initializeCatalog(String filename)
	{
		Catalog.clear();
        BufferedReader br = null;
        BufferedReader br2 = null;
        String csvSplitBy = ",";
        String colonSplit = ":";
        String line = "";
        
        
        try {
        	br = new BufferedReader(new FileReader(filename));
            
            while ((line = br.readLine()) != null)
            {
            	Course newCourse=new Course();
                String[] courseStuff = line.split(csvSplitBy);
                newCourse.setCourseCode(courseStuff[0]);
                newCourse.setCourseCredit(Double.parseDouble(courseStuff[1]));
                newCourse.setCourseTitle(courseStuff[2]);
                newCourse.setSemesterOffered(courseStuff[3]);
                addCourse(newCourse);
            }
            
            try
            {
                br.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            
            
            
            br2 = new BufferedReader(new FileReader(filename));
            
            while ((line = br2.readLine()) != null )
            {
            	ArrayList<Course> preReq=new ArrayList<Course>();
            	String[] courseStuff = line.split(csvSplitBy,-1);
            	Course newCourse=findCourse(courseStuff[0]);
            	String[] preReqs = courseStuff[4].split(colonSplit);
            	for(String allPreReqs: preReqs)
            	{
            		if(findCourse(allPreReqs)!=null)
            		{
            				preReq.add(findCourse(allPreReqs));
            		}
            	}
            	newCourse.setPrerequisites(preReq);
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
        
        finally
        {
            if (br2 != null)
            {
                try
                {
                    br2.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
	}

	public Course findCourse2(String courseString)
	{
		for(Course course: Catalog)
        {
        	if(course.toString().equals(courseString))
        	{
        		return course;
        	}
        }
		return null;
	}
	
	public boolean equals(CourseCatalog otherCatalog)
	{
		if(otherCatalog.getCatalog().equals(Catalog))
		{
			return true;
		}
		return false;
	}

}
