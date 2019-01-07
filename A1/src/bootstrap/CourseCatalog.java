package bootstrap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CourseCatalog
{
	private ArrayList<Course>Catalog;
	
	public CourseCatalog()
	{
		Catalog=new ArrayList<>();
	}
	
	
	public void initializeCatalog(String filename)
	{
        BufferedReader br = null;
        BufferedReader br2 = null;
        String csvSplitBy = ",";
        String colonSplit = ":";
        String line = "";
        
        
        try {
        	br = new BufferedReader(new FileReader(filename));
            
            while ((line = br.readLine()) != null && !line.equals("***"))
            {
            	Course newCourse=new Course();
                String[] courseStuff = line.split(csvSplitBy);
                newCourse.setCourseCode(courseStuff[0]);
                newCourse.setCourseCredit(Double.parseDouble(courseStuff[1]));
                newCourse.setCourseTitle(courseStuff[2]);
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
            
            while ((line = br2.readLine()) != null && !line.equals("***"))
            {
            	
            	ArrayList<Course> preReq=new ArrayList<Course>();
            	String[] courseStuff = line.split(csvSplitBy);
            	if(courseStuff.length>3)
            	{
            	
            		Course newCourse=findCourse(courseStuff[0]);
                
            		String[] preReqs = courseStuff[3].split(colonSplit);
            	
            		for(String allPreReqs: preReqs)
            		{
            			preReq.add(findCourse(allPreReqs));
            		}
            	newCourse.setPrerequisites(preReq);
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
	
	public void addCourse(Course toAdd)
	{
		if(!toAdd.equals(null))
		{
			Catalog.add(toAdd);
		}
	}

	public void saveCatalog()
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter("catalog.csv"));
			
			for(Course course: getCatalog())
	        {
	        	writer.write(course.toString());
	        	
	        }
			writer.write("***\n");
			writer.close();
			
		}
		
		
		
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Course findCourse(String courseCode)
	{
		for(Course course: getCatalog())
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
		if(!toRemove.equals(null))
		{
			Catalog.remove(toRemove);
		}
	}

	public void loadCatalog(String filename)
	{
        BufferedReader br = null;
        BufferedReader br2 = null;
        String csvSplitBy = ",";
        String colonSplit = ":";
        String line = "";
        
        
        try {
        	br = new BufferedReader(new FileReader(filename));
            
            while ((line = br.readLine()) != null)
            {
            	if(line.equals("***"))
            	{
            		break;
            	}
            	Course newCourse=new Course();
                String[] courseStuff = line.split(csvSplitBy);
                newCourse.setCourseCode(courseStuff[0]);
                newCourse.setCourseCredit(Double.parseDouble(courseStuff[1]));
                newCourse.setCourseTitle(courseStuff[2]);
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
            
            while ((line = br2.readLine()) != null)
            {
            	if(line.equals("***"))
            	{
            		break;
            	}
            	ArrayList<Course> preReq=new ArrayList<Course>();
            	String[] courseStuff2 = line.split(csvSplitBy);
            	if(courseStuff2.length>4)
            	{
            	
            		Course newCourse=findCourse(courseStuff2[0]);
                
            		String[] preReqs = courseStuff2[3].split(colonSplit);
            	
            		for(String allPreReqs: preReqs)
            		{
            			preReq.add(findCourse(allPreReqs));
            		}
            		
            	newCourse.setPrerequisites(preReq);
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
        	
        }
		return toReturn;
	}
	
	
	
}
