package univ;

import java.util.ArrayList;

import Exceptions.NullSet;
/**
 * Course Class
 * @author Patrick Copp
 *
 */

public class Course
{
	protected String CourseCode;
	protected String CourseTitle;
	protected double CourseCredit;
	protected ArrayList<Course>Prerequisites;
	protected String SemesterOffered;
	
	public Course()
    {
		CourseCode="";
		CourseTitle="";
		CourseCredit=0.5;
        Prerequisites= new ArrayList<Course>();
        SemesterOffered="";
        
    }
	/**
	 * deep copy
	 * @param Course newCourse
	 */
	public Course(Course newCourse)
	{
		CourseCode=newCourse.getCourseCode();
		CourseTitle=newCourse.getCourseTitle();
		CourseCredit=newCourse.getCourseCredit();
		Prerequisites=newCourse.getPrerequisites();

	}
	/**
	 * coursecode getter
	 * @return Course coursecode
	 */
	public String getCourseCode()
	{
		return CourseCode;
	}	
	/**
	 * This is the setter for the course code
	 * @param string courseCode
	 */
	protected void setCourseCode(String courseCode)
	{
		if(courseCode==null)
		{
			
			try {
				throw new NullSet();
			} catch (NullSet e) {
				e.printStackTrace();
				return;
			}
			
		}
		
		else
		{
			CourseCode=courseCode;
		}
	}
	/**
	 * This is the getter for the course title
	 * @return string courseTitle
	 */
	public String getCourseTitle()
	{
		return CourseTitle;
	}	
	/**
	 *  This is the setter for the course title
	 * @param string courseTitle
	 */
	protected void setCourseTitle(String courseTitle)
	{
		if(courseTitle==null)
		{
			try {
				throw new NullSet();
			} catch (NullSet e) {
				e.printStackTrace();
				return;
			}
		}
		CourseTitle=courseTitle;
	}	
	/**
	 * This is the getter for the course credit
	 * @return double CourseCredit
	 */
	public double getCourseCredit()
	{
		return CourseCredit;
	}
	/**
	 * This is the setter for the course credit
	 * @param double credit
	 */
	protected void setCourseCredit(double credit)
	{
		CourseCredit=credit;
	}
	/**
	 * This is the getter for the prerequisites
	 * @return ArrayList<Course> prerequisites
	 */
	public ArrayList<Course>getPrerequisites()
	{
		return Prerequisites;
	}
	/**
	 * This is the setter for the prerequisites
	 * @param ArrayList<Course> preReqList
	 */
	protected void setPrerequisites(ArrayList<Course> preReqList)
	{
		if(preReqList==null)
		{
			try {
				throw new NullSet();
			} catch (NullSet e) {
				e.printStackTrace();
				return;
			}
		}
		Prerequisites=preReqList;
	}
	/**
	 * This is the setter for the semester offered
	 * @param offered
	 */
	protected void setSemesterOffered(String offered)
	{
		if(offered!=null && (offered.equals("F") || offered.equals("W") || offered.equals("B")))
		{
			SemesterOffered=offered;
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
	
	/**
	 * This is the getter for the semester offered
	 * @return String semester
	 */
	public String getSemesterOffered()
	{
		if(SemesterOffered.equals("F") || SemesterOffered.equals("W") ||SemesterOffered.equals("B"))
		{
			return SemesterOffered;
		}
		return null;
	}
	/**
	 * This is the equals override
	 * @param Course compare
	 * @return boolean
	 */
	public boolean equals(Course compare)
	{
		if(compare==null)
		{
			try {
				throw new NullSet();
			} catch (NullSet e) {
				e.printStackTrace();
				return false;
			}
		}
		boolean check=true;
		if(!(CourseCode.equals(compare.getCourseCode())))
		{
			check=false;
		}
		if(!(CourseTitle.equals(compare.getCourseTitle())))
		{
			check=false;
		}
		if(!(CourseCredit==compare.getCourseCredit()))
		{
			check=false;
		}
		if(!(Prerequisites.equals(compare.getPrerequisites())))
		{
			check=false;
		}
		if(!(SemesterOffered.equals(compare.getSemesterOffered())))
		{
			check=false;
		}
		return check;
	}
	
	/**
	 * This is the toString override
	 * @return String
	 */
	public String toString()
	{
		String toReturn=(CourseCode+","+CourseCredit+","+CourseTitle+",");
		for(Course course: Prerequisites)
		{
			toReturn+=course.getCourseCode()+":";
		}
		
		toReturn=toReturn.substring(0,toReturn.length()-1);
		toReturn+=","+SemesterOffered;
		toReturn+="\n";
		return toReturn;
	}
	

}
