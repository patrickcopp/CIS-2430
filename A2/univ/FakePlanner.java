package univ;


import java.util.ArrayList;

import notuniv.Degree;
import notuniv.Student;

public class FakePlanner

{
	ArrayList<Degree> degreeList;
	CourseCatalog catalog;
	Student currentStudent;
	Degree myDegree;
	
	public FakePlanner(String degreeThing)
	{
		degreeList=new ArrayList<Degree>();
		catalog=new CourseCatalog();
		catalog.initializeCatalog("courselistA2.txt");
		currentStudent=new Student();
		myDegree= new Degree(catalog);
		myDegree.setDegreeTitle(degreeThing); //NEED TO SET DEGREE AT VERY BEGINNING
		myDegree.setRequiredCourses(null);
	}
	public FakePlanner(String degreeThing,Student myStudent)
	{
		degreeList=new ArrayList<Degree>();
		catalog=new CourseCatalog();
		catalog.initializeCatalog("courselistA2.txt");
		currentStudent=myStudent;
		myDegree= new Degree(catalog);
		myDegree.setDegreeTitle(degreeThing); //NEED TO SET DEGREE AT VERY BEGINNING
		myDegree.setRequiredCourses(null);
	}
	
	
	
	public void addToPlan(String toAdd,String newGrade)
	{
		if(catalog.findCourse2(toAdd)!=null)
		{
			Attempt newAttempt= new Attempt();
			newAttempt.setCourseAttempted(catalog.findCourse2(toAdd));
			newAttempt.setAttemptGrade(newGrade);
			currentStudent.getPlanList().add(newAttempt);
		}
	}
	public void removeFromPlan(String toRemove)
	{
		Attempt attemptHold=new Attempt();
		for(Attempt attempt: currentStudent.getPlanList())
		{
			if(attempt.toStringNoSemester().equals(toRemove))
			{
				attemptHold=attempt;
			}
		}
		currentStudent.getPlanList().remove(attemptHold);
		
	}
	public void addToAttempt(Attempt newAttempt)
	{
		if(newAttempt!=null)
		{
			newAttempt.setAttemptGrade("INC");
			currentStudent.getAttemptList().add(newAttempt);
		}
	}
	public void removeFromAttempt(String toRemove)
	{
		if(currentStudent.findAttemptList(toRemove)!=null)
		{
			currentStudent.getAttemptList().remove(currentStudent.findAttemptList(toRemove));
		}
	}
	public void changeAttemptGrade(String toChange,String newGrade)
	{
		if(currentStudent.findAttemptList(toChange)!=null)
		{
			currentStudent.findAttemptList(toChange).setAttemptGrade(newGrade);
		}
		
	}
	public ArrayList<Course> notRepresented()
	{
		boolean found=false;
		ArrayList<Course> neither = new ArrayList<Course>();
		for(Course checkCourse: myDegree.getRequiredCourses())
		{
			for(Attempt allAttempted: currentStudent.getAttemptList())
			{
				if(checkCourse.equals(allAttempted.getCourseAttempted()))
				{
					found=true;
				}
			}
			
			if(!found)
			{
				for(Attempt allAttempted: currentStudent.getPlanList())
				{
					if(checkCourse.equals(allAttempted.getCourseAttempted()))
					{
						found=true;
					}
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
	public ArrayList<Course> notRepresentedTranscript()
	{
		boolean found=false;
		ArrayList<Course> neither = new ArrayList<Course>();
		for(Course checkCourse: myDegree.getRequiredCourses())
		{
			for(Attempt allAttempted: currentStudent.getAttemptList())
			{
				if(checkCourse.equals(allAttempted.getCourseAttempted()))
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
	public Double addCredits()
	{
		double sum2=0;
		
		for(Attempt taken: currentStudent.getAttemptList())
		{
			sum2+=taken.getCourseAttempted().getCourseCredit();
		}
		for(Attempt taken: currentStudent.getPlanList())
		{
			sum2+=taken.getCourseAttempted().getCourseCredit();
		}
		
		sum2=myDegree.getRequiredCredits()-sum2;
		
		return sum2;
	}
	public Double cisCompleted()
	{
		double toReturn=0;
		
		for(Course required: myDegree.getRequiredCourses())
		{
			for(Attempt attempts: currentStudent.getAttemptList())
			{
				if(attempts.getCourseAttempted().equals(required))
				{
					toReturn+=required.getCourseCredit();
					break;
				}
			}
		}
		
		
		return toReturn;
	}
	public Double cisRemaining()
	{
		return myDegree.getRequiredCredits()-cisCompleted();
	}
	public Boolean graduate()
	{
		double sum=0;
		for(Attempt attempts: currentStudent.getAttemptList())
		{
			sum+=attempts.getCourseAttempted().getCourseCredit();
		}
		if(sum<myDegree.getRequiredCredits())
		{
			return false;
		}
		
		ArrayList<Course> notInTrans= new ArrayList<Course>();
		notInTrans=notRepresentedTranscript();
		if(notInTrans.size()==0)
		{
			return true;
		}
		
		
		return false;
	}
	public Double getGPA()
	{
		double sum=0;
		double count=0;
		for(Attempt attempts: currentStudent.getAttemptList())
		{
			if(!attempts.getAttemptGrade().equals("") && !attempts.getAttemptGrade().equalsIgnoreCase("P")&& !attempts.getAttemptGrade().equalsIgnoreCase("F")&& !attempts.getAttemptGrade().equalsIgnoreCase("INC")&& !attempts.getAttemptGrade().equalsIgnoreCase("MNR"))
			{
				sum+=2*Double.parseDouble(attempts.getAttemptGrade())*attempts.getCourseAttempted().getCourseCredit();
				count+=2*attempts.getCourseAttempted().getCourseCredit();
				
			}
		}
		
		if(count==0)
		{
			return 0.0;
		}
		else
		{
			return sum/count;
		}
		
	}
	public Double getCISGPA()
	{
		double sum=0;
		double count=0;
		for(Attempt attempts: currentStudent.getAttemptList())
		{
			if(!attempts.getAttemptGrade().equals("") && !attempts.getAttemptGrade().equals("P")&& !attempts.getAttemptGrade().equals("F")&& !attempts.getAttemptGrade().equals("INC")&& !attempts.getAttemptGrade().equals("MNR") && attempts.getCourseAttempted().getCourseCode().substring(0,3).equals("CIS"))
			{
				sum+=2*Double.parseDouble(attempts.getAttemptGrade())*attempts.getCourseAttempted().getCourseCredit();
				count+=2*attempts.getCourseAttempted().getCourseCredit();
			}
		}
		
		if(count==0)
		{
			return 0.0;
		}
		else
		{
			return sum/count;
		}
		
	}
	public CourseCatalog getCatalog()
	{
		return catalog;
	}
	public Student getStudent()
	{
		return currentStudent;
	}
	public Degree getDegree()
	{
		return myDegree;
	}

	
}















