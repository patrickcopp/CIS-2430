package bootstrap;


public class Student
{
	private String fullName;
	private String lastName;
	private String firstName;
	private int studentNumber;
	
	public Student()
    {
		fullName="";
		lastName="";
		firstName="";
		studentNumber=0;
    }
	
	public String getFullName()
	{
		fullName=firstName+" "+lastName;
		return fullName;
	}
	public void setFirstName(String first)
	{
		firstName=first;
	}
	public void setLastName(String last)
	{
		lastName=last;
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
		studentNumber=studentNum;
	}
	public Integer getStudentNumber()
	{
		return studentNumber;
	}
	
}