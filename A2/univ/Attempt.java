package univ;

import Exceptions.NullSet;

public class Attempt
{
	private Course CourseAttempt;
	private String CourseGrade;
	private String Semester;
	
	public Attempt()
	{
		CourseAttempt=null;
        CourseGrade="";
        Semester="";
	}
	
	public void setAttemptGrade(String grade)
	{
		if(grade!=null)
		{
			CourseGrade=grade;
			return;
		}
		else
		{
			try {
				throw new NullSet();
			} catch (NullSet e) {
				e.printStackTrace();
			}
		}
	}
	public String getAttemptGrade()
	{
		return CourseGrade;
	}
	public void setSemesterTaken(String semester)
	{
		if(semester!=null)
		{
			Semester=semester;
		}
		else
		{
			try {
				throw new NullSet();
			} catch (NullSet e) {
				e.printStackTrace();
			}
		}
	}
	public String getSemesterTaken()
	{
		return Semester;
	}
	public void setCourseAttempted(Course theCourse)
	{
		if(theCourse!=null)
		{
			CourseAttempt=theCourse;
	
		}
		else
		{
			try {
				throw new NullSet();
			} catch (NullSet e) {
				e.printStackTrace();
			}
		}
	}
	public Course getCourseAttempted()
	{
		return CourseAttempt;
	}
	public Boolean passFail()
	{
		if(CourseGrade.equals(""))
		{
			return false;
		}
		else if(CourseGrade.charAt(0)=='P')
		{
			return true;
		}
		else if(Character.isDigit(CourseGrade.charAt(0)))
		{
			if(Double.parseDouble(CourseGrade)>49)
			{
				return true;
			}
		}
		
		return false;
	}	
	public String toString()
	{
		if(CourseAttempt==null)
		{
			
			try {
				throw new NullSet();
			} catch (NullSet e) {
				e.printStackTrace();
				return null;
			}
			
		}
		
		return CourseAttempt.getCourseCode()+","+Semester+","+CourseGrade;
	}
	public String toStringNoSemester()
	{
		if(CourseAttempt==null)
		{
			return null;
		}
		return CourseAttempt.getCourseCode()+","+CourseGrade;
	}
	public boolean equals(Attempt otherAttempt)
	{
		boolean check =true;
		if(!otherAttempt.getAttemptGrade().equals(getAttemptGrade()))
		{
			check = false;
		}
		else if(!otherAttempt.getCourseAttempted().equals(getCourseAttempted()))
		{
			check = false;
		}
		else if(!otherAttempt.getSemesterTaken().equals(getSemesterTaken()))
		{
			check = false;
		}
		return check;
	}
	
}
