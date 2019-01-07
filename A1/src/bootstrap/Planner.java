package bootstrap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Planner

{
	
	static PlanOfStudy myPlan=new PlanOfStudy();
	static ArrayList<Degree> degreeList=new ArrayList<Degree>();
	static CourseCatalog catalog=new CourseCatalog();
	
	public static void main(String[] args)
	{
		
		Scanner scanner = new Scanner(System. in);
		
		catalog.loadCatalog("catalog.csv");
		
		degreeList=degreeFromCatalog();
		
		myPlan.setCatalog(catalog);
	
		
		
		
		String userChoice="";
		
		while(!userChoice.equals("14"))
		{
			menuDisplay();
			
			userChoice=scanner.next();
			
			if(userChoice.equals("1"))
			{
				addCourse(scanner);
			}
			else if(userChoice.equals("2"))
			{
				markCourses(scanner);
			}
			else if(userChoice.equals("3"))
			{
				removeCourse(scanner);
			}
			else if(userChoice.equals("4"))
			{
				changeGrade(scanner);
			}
			else if(userChoice.equals("5"))
			{
				saveState();
			}
			else if(userChoice.equals("6"))
			{
				loadState(scanner);
			}
			else if(userChoice.equals("7"))
			{
				notInPlan();
			}
			else if(userChoice.equals("8"))
			{
				degreePrereqs(scanner);
			}
			else if(userChoice.equals("9"))
			{
				creditsDone(scanner);
			}
			else if(userChoice.equals("10"))
			{
				creditsLeft(scanner);
			}
			else if(userChoice.equals("11"))
			{
				degreeDone(scanner);
			}
			
			else if(userChoice.equals("12"))
			{
				addTranscript(scanner);
			}
			else if(userChoice.equals("13"))
			{
				chooseDegree(scanner);
			}
			
			
		}
		
		
		scanner.close();
		
	}
	
	
	
	public static void addCourse(Scanner scanner)
	{
		System.out.print("Enter a course code: ");
		String code=scanner.next();
		System.out.print("Enter a course status (progress,complete,planned): ");
		String status=scanner.next();
		myPlan.addCourse(code,status);
		
	}

	public static ArrayList<Degree> degreeFromCatalog()
	{
		ArrayList<Degree> degreeList=new ArrayList<Degree>();
		String line="";
		BufferedReader br = null;
		
		try
		{
			br = new BufferedReader(new FileReader("catalog.csv"));
			
			while ((line = br.readLine()) != null && !line.equals("***"))
	        {
	        	
	        }
			if(line.equals(null))
			{
				br.close();
				return null;
			}
			
			while ((line = br.readLine()) != null)
	        {
				Degree newDegree=null;
				String [] degreeStuff=line.split(",");
				
				if(degreeStuff[0].equalsIgnoreCase("BCG"))
                {
                    BCG degreeBCG=new BCG();
                    
                    newDegree=degreeBCG;
                    newDegree.setDegreeTitle("BCG");
                }
                    
                else if(degreeStuff[0].equalsIgnoreCase("SEng"))
                {
                    SEng degreeSEng=new SEng();
                    newDegree=degreeSEng;
                    newDegree.setDegreeTitle("SEng");
                }
                    
                else if(degreeStuff[0].equalsIgnoreCase("CS"))
                {
                    CS degreeCS=new CS();
                    newDegree=degreeCS;
                    newDegree.setDegreeTitle("CS");
                }
				
				newDegree.initializeDegree(degreeStuff);
				degreeList.add(newDegree);
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
		
		return degreeList;
		
	}
 
	public static void menuDisplay()
	{
		 System.out.println("   ___  __    _   _  __ _  __ ___  ___ ");
		 System.out.println("  / o |/ /  .' \\ / |/ // |/ // _/ / o |");
		 System.out.println(" / _,'/ /_ / o // || // || // _/ /  ,'");
		 System.out.println("/_/  /___//_n_//_/|_//_/|_//___//_/`_\\ ");
		 System.out.println("                                      \n\n");
		 
		 System.out.println("1.  Add courses to plan of study");
		 System.out.println("2.  Mark courses as required, elective or minor");
		 System.out.println("3.  Remove a course in my plan of study");
		 System.out.println("4.  Change a grade in a course in my plan of study");
		 System.out.println("5.  Save my plan of study to a file");
		 System.out.println("6.  Load my plan of study from another file");
		 System.out.println("7.  View a list of required course for my degree that are not in my plan of study");
		 System.out.println("8.  View a list of prerequisites for any course in my degree");
		 System.out.println("9.  View the number of credits I have completed in my plan of study");
		 System.out.println("10. View the number of credits I have remaining to complete my plan of study");
		 System.out.println("11. Determine if I have met the completion requirements of my chosen degree");
		 System.out.println("12. Add a transcript to your plan");
		 System.out.println("13. Select my degree");
		 System.out.println("14. Quit\n");
		 
		 System.out.print("Enter an option: ");
		 
		 
		 
	}

	public static void addTranscript(Scanner scanner)
	{
		System.out.print("If you have a .csv containing a transcript, enter it now or 'q' to exit: ");
		String filename=scanner.next();
		if(!filename.equals("q"))
		{
			myPlan.importData(filename);
		}
	}
	
	public static void chooseDegree(Scanner scanner)
	{
		Degree newDegree=null;
		
		String userDegree="";
		
		while(!userDegree.equals("General") && !userDegree.equals("Honours"))
		{
			
		
			System.out.print("Select your degree (Honours,General): ");
			userDegree=scanner.next();
			
			if(userDegree.equalsIgnoreCase("General"))
			{
				BCG degreeBCG=new BCG();
				newDegree=degreeBCG;
				newDegree.setDegreeTitle("BCG");
			}
			else if(userDegree.equalsIgnoreCase("Honours"))
			{
				HonoursDegree degreeHD=new HonoursDegree();
				newDegree=degreeHD;
				newDegree.setDegreeTitle("Honours");
			}
			else
			{
				System.out.println("This is not a viable degree.");
			}
		}
		
		
		if(newDegree.getDegreeTitle().equals("Honours"))
		{
			boolean degreeExists=false;
			
			
			while(!userDegree.equals("CS") && !userDegree.equals("SEng")) //Make sure input is cs/seng
			{
				System.out.print("Select your major (CS, SEng): ");
				userDegree=scanner.next();
				
				if(userDegree.equals("CS"))
				{
					CS degreeCS=new CS();
					newDegree=degreeCS;
					newDegree.setDegreeTitle("CS");
				}
				else if(userDegree.equals("SEng"))
				{
					SEng degreeSEng=new SEng();
					newDegree=degreeSEng;
					newDegree.setDegreeTitle("SEng");
				}
				
			}
			
			for(Degree degrees: degreeList)  //make sure degree was in catalog
			{
				if(degrees.getDegreeTitle().equals(userDegree))
				{
					degreeExists=true;
				}
			}
			
			
			if(!degreeExists)
			{
				 System.out.println("This degree is not stored within catalog.csv. Exiting.");
				 return;
			}
			
			else
			{
				myPlan.setDegreeProgram(newDegree);
			}
			
		}
		
		else //General Degree
		{
			boolean degreeExists=false;
			
			for(Degree degrees: degreeList)
			{
				if(degrees.getDegreeTitle().equals("BCG"))
				{
					degreeExists=true;
				}
			}
			
			if(!degreeExists)
			{
				 System.out.println("This degree is not stored within catalog.csv. Exiting.");
				 return;
			}
			else
			{
				myPlan.setDegreeProgram(newDegree);
			}
			
		}
		
	}
	
	public static void markCourses(Scanner scanner)
	{
		System.out.print("Enter a course code to mark: ");
		String courseCheck=scanner.next();
		for(Course newCourse:myPlan.getMyCourses())
		{
			if(courseCheck.equals(newCourse.getCourseCode()))
			{
				System.out.print("Enter whether this course is an elective, minor, or required: ");
				newCourse.setElectiveOrMajor(scanner.next());
				return;
			}
		}
		
		System.out.print("Course was not found within your plan of study.");
		
	}
	
	public static void removeCourse(Scanner scanner)
	{
		System.out.println("Enter the course you want to remove: ");
		String remove=scanner.next();
		Course toRemove=new Course();
		boolean found=false;
		for(Course courses: myPlan.getMyCourses())
		{
			if(courses.getCourseCode().equals(remove))
			{
				toRemove=courses;
				found=true;
			}
		}
		
		if(found)
		{
			myPlan.getMyCourses().remove(toRemove);
			return;
		}
		
		else
		{
			System.out.println("Course not found in your plan of study.");
		}
		
	}
	
	public static void changeGrade(Scanner scanner)
	{
		System.out.println("Enter the course whose grade you want to alter: ");
		String alter=scanner.next();
		Course toAlter=new Course();
		boolean found=false;
		for(Course courses: myPlan.getMyCourses())
		{
			if(courses.getCourseCode().equals(alter))
			{
				toAlter=courses;
				found=true;
			}
		}
		
		if(found)
		{
			System.out.println("Enter the new grade: ");
			String newGrade=scanner.next();
			toAlter.setCourseGrade(newGrade);
			return;
		}
		
		else
		{
			System.out.println("Course not found in your plan of study.");
		}
	}

	public static void saveState()
	{
		myPlan.saveState();
	}
	
	public static void loadState(Scanner scanner)
	{
		String line="";
		BufferedReader br = null;
		String csvSplitBy = ",";
		
		try
		{
			System.out.print("Enter the .csv file you would like to import: ");
			String filename=scanner.next();
			
			br = new BufferedReader(new FileReader(filename));
			line = br.readLine();
			String[] degreeTitles = line.split(csvSplitBy);
	        
			Degree newDegree=null;
			
			
			if(degreeTitles[0].equalsIgnoreCase("BCG"))
            {
				for(Degree degreeCheck: degreeList)
				{
					if(degreeCheck.getDegreeTitle().equals("BCG"))
					{
						BCG degreeBCG=new BCG();
		                newDegree=degreeBCG;
		                newDegree.setDegreeTitle("BCG");
		                
		                ArrayList<String> requiredCourses=new ArrayList<String>();
		                
		                for(Course required: degreeCheck.getRequiredCourses())
		                {
		                	requiredCourses.add(required.getCourseCode());
		                }
		                newDegree.setRequiredCourses(requiredCourses);
					}
				}
                    
            }
                    
            else if(degreeTitles[0].equalsIgnoreCase("SEng"))
            {
            	for(Degree degreeCheck: degreeList)
				{
					if(degreeCheck.getDegreeTitle().equals("SEng"))
					{
						SEng degreeSEng=new SEng();
		                newDegree=degreeSEng;
		                newDegree.setDegreeTitle("SEng");
		                ArrayList<String> requiredCourses=new ArrayList<String>();
		                
		                for(Course required: degreeCheck.getRequiredCourses())
		                {
		                	requiredCourses.add(required.getCourseCode());
		                }
		                newDegree.setRequiredCourses(requiredCourses);    
					}
				}
                
            }
                    
            else if(degreeTitles[0].equalsIgnoreCase("CS"))
            {
            	for(Degree degreeCheck: degreeList)
				{
					if(degreeCheck.getDegreeTitle().equals("CS"))
					{
						CS degreeCS=new CS();
		                newDegree=degreeCS;
		                newDegree.setDegreeTitle("CS");
		                ArrayList<String> requiredCourses=new ArrayList<String>();
		                
		                for(Course required: degreeCheck.getRequiredCourses())
		                {
		                	requiredCourses.add(required.getCourseCode());
		                }
		                newDegree.setRequiredCourses(requiredCourses);
		                    
					}
				}
                
            }
			
            else
            {
                
                br.close();
                return;
            }
			
			
			
			myPlan.setDegreeProgram(newDegree);
			
			line="";
			
			Course holdCourse=new Course();
			
			
			while ((line = br.readLine()) != null)
            {
				
                String[] courseStuff = line.split(csvSplitBy);
                Course newCourse=holdCourse;
                newCourse=catalog.findCourse(courseStuff[0]);
                newCourse.setCourseStatus(courseStuff[1]);
                newCourse.setCourseGrade(courseStuff[2]);
                newCourse.setSemesterTaken(courseStuff[3]);
                newCourse.setElectiveOrMajor(courseStuff[4]);
                myPlan.getMyCourses().add(newCourse);
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
		
	}

	public static void notInPlan()
	{
		boolean found=false;
		for(Course requiredCourse: myPlan.getDegreeProgram().getRequiredCourses())
		{
			System.out.println("fdsfsd");
			found=false;
			for(Course myCourses: myPlan.getMyCourses())
			{
				if(requiredCourse.getCourseCode().equals(myCourses.getCourseCode()))
				{
					found=true;
				}
			}
			
			if(!found)
			{
				System.out.println(requiredCourse.toString());
			}
			
		}
	}

	public static void degreePrereqs(Scanner scanner)
	{
		System.out.println("Enter the course you want the prerequisites for: ");
		String findCourse=scanner.next();
		for(Course courses: myPlan.getDegreeProgram().getRequiredCourses())
		{
			if(courses.getCourseCode().equals(findCourse))
			{
				for(Course preReqs: courses.getPrerequisites())
				{
					System.out.print(preReqs.getCourseCode()+" ");
				}
				System.out.println();
			}
		}
		System.out.println("Course not found.");
		
	}

	public static void creditsDone(Scanner scanner)
	{
		double sum=0;
		for(Course courses: myPlan.getMyCourses())
		{
			if(courses.getCourseStatus().equalsIgnoreCase("complete"))
			{
				sum+=courses.getCourseCredit();
			}
		}
		
		System.out.println("Completed credits: "+sum);
	}

	public static void creditsLeft(Scanner scanner)
	{
		double sum=0;
		for(Course courses: myPlan.getDegreeProgram().getRequiredCourses())
		{
			sum+=courses.getCourseCredit();
		}
		
		
		double sum2=0;
		for(Course courses: myPlan.getMyCourses())
		{
			if(courses.getCourseStatus().equalsIgnoreCase("complete"))
			{
				sum2+=courses.getCourseCredit();
			}
		}
		
		sum-=sum2;
		System.out.println("Credits remaining: "+sum);
		
	}

	public static void degreeDone(Scanner scanner)
	{
		System.out.println("Degree not completed.");
	}
}


