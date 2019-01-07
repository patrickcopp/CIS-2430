package bootstrap;
import java.util.ArrayList;

public class Course
{
	private String CourseCode;
	private String CourseTitle;
	private double CourseCredit;
	private ArrayList<Course>Prerequisites;
	private String CourseStatus;
	private String CourseGrade;
	private String Semester;
	private String electiveOrMajor;
	/**
	 * This is the normal constructor, it defaults all string to Unknown, and the course credit size to 0.5, the standard
	 */
	
	public Course()
    {
		CourseCode="Unknown";
		CourseTitle="Unknown";
		CourseCredit=0.5;
        Prerequisites= new ArrayList<>();
        CourseStatus="Unknown";
        CourseGrade="Unknown";
        electiveOrMajor="Unknown";
    }
	/**
	 * This is the deep copy constructor, it is self explanatory
	 */
	public Course(Course newCourse)
	{
		CourseCode=newCourse.getCourseCode();
		CourseTitle=newCourse.getCourseTitle();
		CourseCredit=newCourse.getCourseCredit();
		Prerequisites=newCourse.getPrerequisites();
		CourseStatus=newCourse.getCourseStatus();
		CourseGrade=newCourse.getCourseGrade();
		Semester=newCourse.getSemesterTaken();
		electiveOrMajor=newCourse.getElectiveOrMajor();
	}
	/**
	 * This is the getter for the course code
	 */
	public String getCourseCode()
	{
		return CourseCode;
	}	
	/**
	 * This is the setter for the course code
	 */
	public void setCourseCode(String courseCode)
	{
		if(courseCode==null)
		{
			courseCode="";
			return;
		}
		CourseCode=courseCode;
	}
	/**
	 * This is the getter for the course title
	 */
	public String getCourseTitle()
	{
		return CourseTitle;
	}	
	/**
	 * This is the setter for the course title
	 */
	public void setCourseTitle(String courseTitle)
	{
		if(courseTitle==null)
		{
			courseTitle="";
			return;
		}
		CourseTitle=courseTitle;
	}	
	/**
	 * This is the getter for the course credit
	 */
	public double getCourseCredit()
	{
		return CourseCredit;
	}
	/**
	 * This is the setter for the course credit
	 */
	public void setCourseCredit(double credit)
	{
		CourseCredit=credit;
	}
	/**
	 * This is the getter for the prerequisites
	 */
	public ArrayList<Course>getPrerequisites()
	{
		return Prerequisites;
	}
	/**
	 * This is the setter for the prerequisites
	 */
	public void setPrerequisites(ArrayList<Course> preReqList)
	{
		Prerequisites=preReqList;
	}
	/**
	 * This is the setter for the course status
	 */
	public void setCourseStatus(String courseStatus)
	{
		if(courseStatus==null)
		{
			CourseStatus="";
			return;
		}
		CourseStatus=courseStatus;
	}
	/**
	 * This is the getter for the course status
	 */
	public String getCourseStatus()
	{
		return CourseStatus;
	}
	/**
	 * This is the setter for the course grade
	 */
	public void setCourseGrade(String grade)
	{
		if(grade==null)
		{
			CourseGrade="";
			return;
		}
		CourseGrade=grade;
	}
	/**
	 * This is the getter for the course grade
	 */
	public String getCourseGrade()
	{
		return CourseGrade;
	}
	/**
	 * This is the toString for courses, the way I have implemented it means that we only save the code, credit, title, and prerequisites here
	 */
	public String toString()
	{
		String toReturn=(CourseCode+","+CourseCredit+","+CourseTitle+",");
		for(Course course: Prerequisites)
		{
			toReturn+=course.getCourseCode()+":";
		}
		toReturn=toReturn.substring(0,toReturn.length()-1);
		toReturn+="\n";
		return toReturn;
	}
	/**
	 * This is the getter for the semester taken
	 */
	public String getSemesterTaken()
	{
		return Semester;
	}
	/**
	 * This is the setter for the semester taken
	 */
	public void setSemesterTaken(String semester)
	{
		Semester=semester;
	}
	/**
	 * This is the setter for whether this course is part of an elective or major
	 */
	public void setElectiveOrMajor(String electOrMajor)
	{
		electiveOrMajor=electOrMajor;
	}
	/**
	 * This is the getter for whether this course is part of an elective or major
	 */
	public String getElectiveOrMajor()
	{
		return electiveOrMajor;
	}

}
