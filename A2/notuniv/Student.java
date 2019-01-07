package notuniv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Exceptions.BadFile;
import univ.Attempt;
import univ.CourseCatalog;

public class Student
{
	private String fullName;
	private String lastName;
	private String firstName;
	private Integer studentNumber;
	private ArrayList<Attempt> AttemptList;
	private ArrayList<Attempt> PlanList;
	
	
	public Student()
    {
		fullName="";
		lastName="";
		firstName="";
		studentNumber=0;
		AttemptList=new ArrayList<Attempt>();
		PlanList=new ArrayList<Attempt>();
    }
	
	public String getFullName()
	{
		fullName=firstName+" "+lastName;
		return fullName;
	}
	public void setFirstName(String first)
	{
		if(first!=null)
		{
			firstName=first;
		}
		
	}
	public void setLastName(String last)
	{
		if(last!=null)
		{
			lastName=last;
		}
	}
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setStudentNumber(Integer studentNum)
	{
		if(studentNum!=null)
		{
			studentNumber=studentNum;
		}
	}
	public Integer getStudentNumber()
	{
		return studentNumber;
	}
	public ArrayList<Attempt> getAttemptList()
	{
		return AttemptList;
	}
	public void setAttemptList(ArrayList<Attempt> newList)
	{
		if(newList!=null)
		{
			AttemptList=newList;
		}
	}
	public ArrayList<Attempt> getPlanList()
	{
		return PlanList;
	}
	public void setPlanList(ArrayList<Attempt> newList)
	{
		if(newList!=null)
		{
			PlanList=newList;
		}
		
	}
	public Attempt findAttemptList(String courseCode)
	{
		for(Attempt courses: AttemptList)
        {
        	if(courses.toStringNoSemester().equals(courseCode))
        	{
        		return courses;
        	}
        }
		return null;
	}
	public Attempt findPlanList(String courseCode)
	{
		for(Attempt course: PlanList)
        {
        	if(course.toStringNoSemester().equals(courseCode))
        	{
        		return course;
        	}
        }
		return null;
	}
	public boolean equals(Student otherStudent)
	{
		boolean check = true;
		if(!otherStudent.getFullName().equals(getFullName()))
		{
			check=false;
		}
		else if(!otherStudent.getStudentNumber().equals(getStudentNumber()))
		{
			check=false;
		}
		else if(!otherStudent.getAttemptList().equals(getAttemptList()))
		{
			check=false;
		}
		else if(!otherStudent.getPlanList().equals(getPlanList()))
		{
			check=false;
		}
		return check;
	}
 	
	public void saveStudent(String filename,String DegreeTitle) throws IOException
	{
		BufferedWriter save = new BufferedWriter(new FileWriter(filename+".txt"));
		save.write(getFullName());
		save.write("\n");
		save.write(Integer.toString(getStudentNumber()));
		save.write("\n");
		save.write(DegreeTitle);
		save.write("\n");
		for(Attempt trans: getAttemptList())
		{
			save.write(trans.toString());
			save.write("\n");
		}
		save.write("***");
		save.write("\n");
		for(Attempt trans2: getPlanList())
		{
			save.write(trans2.toString());
			save.write("\n");
		}
		
	    save.close();
	}
	public String load(String filename,CourseCatalog catalog)
	{
		String degree = null;
		BufferedReader br = null;
		FileReader fr = null;
		PlanList.clear();
		AttemptList.clear();
		setAttemptList(null);
		
		try
		{
			fr = new FileReader(filename+".txt");
			br = new BufferedReader(fr);
			String sCurrentLine;
			int count=0;
			while ((sCurrentLine = br.readLine()) != null)
			{
				if(count==0)
				{
					String names[]=sCurrentLine.split(" ");
					setFirstName(names[0]);
					setLastName(names[1]);
					count++;
					continue;
				}
				else if(count==1)
				{
					setStudentNumber(Integer.parseInt(sCurrentLine));
					count++;
					continue;
				}
				else if(count==2)
				{
					degree=sCurrentLine;
					count++;
					continue;
				}
				else
				{
					
					 String attempt[]=sCurrentLine.split(",");
					 Attempt newAttempt = new Attempt();
					 newAttempt.setCourseAttempted(catalog.findCourse(attempt[0]));
					 newAttempt.setSemesterTaken(attempt[1]);
					 newAttempt.setAttemptGrade(attempt[2]);
					 AttemptList.add(newAttempt);
					
					 while((sCurrentLine = br.readLine()) != null)
					 {
						 if(sCurrentLine.equals("***"))
						 {
							 break;
						 }
						 String attempt2[]=sCurrentLine.split(",");
						 Attempt newAttempt2 = new Attempt();
						 newAttempt.setCourseAttempted(catalog.findCourse(attempt2[0]));
						 newAttempt.setSemesterTaken(attempt2[1]);
						 newAttempt.setAttemptGrade(attempt2[2]);
						 AttemptList.add(newAttempt2);
					 }
					 
					String yolo;
					while ((yolo = br.readLine()) != null)
					{
						String attempt2[]=yolo.split(",");
						Attempt newAttempt3 = new Attempt();
						newAttempt3.setCourseAttempted(catalog.findCourse(attempt2[0]));
						newAttempt3.setSemesterTaken("N/A");
						newAttempt3.setAttemptGrade(attempt2[2]);
						PlanList.add(newAttempt3);
						
					}
					 
					break;
					 
					 
				}
				
				
			}

		}
		
		catch (IOException e)
		{
			try {
				throw new BadFile();
			} catch (BadFile e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();

		}
		finally
		{

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		
		return degree;
	}
	
}





























