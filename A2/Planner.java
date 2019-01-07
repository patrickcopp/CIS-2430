import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Exceptions.BadGrade;
import Exceptions.NullDegree;

import javax.swing.JList;
import univ.Attempt;
import univ.Course;
import univ.FakePlanner;


import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class Planner extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static FakePlanner newPlan;
	private JTextField textField;
	private static DefaultListModel<Course> model;
	private static DefaultListModel<Attempt> model2;
	private static DefaultListModel<Attempt> model3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Planner frame = new Planner();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws NullDegree 
	 */
	public Planner() throws NullDegree {
		model = new DefaultListModel<Course>();
		model2 = new DefaultListModel<Attempt>();
		model3 = new DefaultListModel<Attempt>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1093, 782);
		String input = null;
		String load[]= {"Yes","No"};
		String inputLoad = (String) JOptionPane.showInputDialog(null, "Load", "Do you wish to load a student?", JOptionPane.QUESTION_MESSAGE, null, load,load[0]);
		String loadname;
		newPlan = new FakePlanner("CS");
		if(inputLoad.equals("Yes"))
		{
			loadname = JOptionPane.showInputDialog(null, "File to Load (do not include.txt): ");
			input=newPlan.getStudent().load(loadname,newPlan.getCatalog());
		}
		else
		{
			String choices[]= {"Honours Degree","General Degree"};
		    input = (String) JOptionPane.showInputDialog(null, "Choose Degree Type", "Degree", JOptionPane.QUESTION_MESSAGE, null, choices,choices[0]);
			    try {
					if(input.equals("Honours Degree"))
					{
						choices[0]="CS";
						choices[1]="SEng";
					    input = (String) JOptionPane.showInputDialog(null, "Choose Degree Type", "Degree", JOptionPane.QUESTION_MESSAGE, null, choices,choices[0]);

					}
					else
					{
						input="BCG";
					}
				} catch (NullPointerException e1) {
					throw new NullDegree();
				}
		}
		
		newPlan = new FakePlanner(input,newPlan.getStudent());
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename = JOptionPane.showInputDialog(null, "File Name: ");
				String studentFirst = JOptionPane.showInputDialog(null, "First Name: ");
				String studentLast = JOptionPane.showInputDialog(null, "Last Name: ");
				String studentNumber = JOptionPane.showInputDialog(null, "Student Number: ");
				
				newPlan.getStudent().setFirstName(studentFirst);
				newPlan.getStudent().setLastName(studentLast);
				newPlan.getStudent().setStudentNumber(Integer.parseInt(studentNumber));
				
				try {
					newPlan.getStudent().saveStudent(filename,newPlan.getDegree().getDegreeTitle());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String loadname = JOptionPane.showInputDialog(null, "File to Load (do not include.txt): ");
				String input=newPlan.getStudent().load(loadname,newPlan.getCatalog());
				newPlan = new FakePlanner(input,newPlan.getStudent());
				updateLists();
			}
		});
		mnFile.add(mntmLoad);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem menuItem = new JMenuItem("Required Courses not in Plan/Transcript");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String message="\n";
				if(newPlan.notRepresented()!=null)
				{
					for(Course courses: newPlan.notRepresented())
					{
						message+=courses.toString();
						message+='\n';
					}
				}
				
				JOptionPane.showMessageDialog(null,message);
				
			}
		});
		mnView.add(menuItem);
		
		JMenuItem mntmRequiredCoursesNot = new JMenuItem("Required Courses not in Transcript");
		mntmRequiredCoursesNot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String message="\n";
				if(newPlan.notRepresented()!=null)
				{
					for(Course courses: newPlan.notRepresentedTranscript())
					{
						message+=courses.toString();
						message+='\n';
					}
				}
				
				JOptionPane.showMessageDialog(null,message);
				
			}
		});
		mnView.add(mntmRequiredCoursesNot);
		
		JMenuItem menuItem_1 = new JMenuItem("Number of credits to add to plan to graduate");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null,newPlan.addCredits());
			}
		});
		mnView.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("List of prerequisite courses for any course");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				@SuppressWarnings({ "unchecked", "rawtypes" })
				JList list=new JList(newPlan.getCatalog().getCatalog().toArray());
				JOptionPane.showMessageDialog(null, new JScrollPane(list));
				Course findPre=(Course) list.getSelectedValue();
				@SuppressWarnings({ "rawtypes", "unchecked" })
				JList list2=new JList(findPre.getPrerequisites().toArray());
				JOptionPane.showMessageDialog(null, new JScrollPane(list2));
				
			}
		});
		mnView.add(menuItem_2);
		
		JMenuItem mntmNumberOfCredits = new JMenuItem("Number of credits completed in program");
		mntmNumberOfCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null,newPlan.cisCompleted());
			}
		});
		mnView.add(mntmNumberOfCredits);
		
		JMenuItem mntmNumberOfCredits_1 = new JMenuItem("Number of credits remaining to complete program");
		mntmNumberOfCredits_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,newPlan.cisRemaining());
			}
		});
		mnView.add(mntmNumberOfCredits_1);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		if(newPlan.getCatalog().getCatalog().size()!=0)
		{
			for(Course s:newPlan.getCatalog().getCatalog()){
			    model.addElement(s);
			}
		}
		JList<Course> allCourses = new JList<Course>(model);
		allCourses.setBounds(154, 31, 511, 617);
		contentPane.add(allCourses);
		
		
		if(newPlan.getStudent().getAttemptList().size()!=0)
		{
			for(Attempt s:newPlan.getStudent().getAttemptList()){
				model2.addElement(s);
			}
		}
		JList<Attempt> transcript = new JList<Attempt>(model2);
		transcript.setBounds(885, 31, 182, 617);
		contentPane.add(transcript);
		
		
		if(newPlan.getStudent().getPlanList().size()!=0)
		{
			for(Attempt s:newPlan.getStudent().getPlanList()){
			    model3.addElement(s);
			}
		}
		
		
		JList<Attempt> planned = new JList<Attempt>(model3);
		planned.setBounds(680, 31, 182, 617);
		contentPane.add(planned);
		
		JLabel label = new JLabel("Transcript");
		label.setBounds(955, 11, 62, 14);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("Planned");
		lblNewLabel.setBounds(747, 11, 54, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAll = new JLabel("All Courses");
		lblAll.setBounds(365, 11, 69, 14);
		contentPane.add(lblAll);
		
		JLabel lblPlanned = new JLabel("PLANNED");
		lblPlanned.setBounds(45, 32, 83, 14);
		contentPane.add(lblPlanned);
		
		
		
		JButton btnPlanAdd = new JButton("Add");
		btnPlanAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if(allCourses.getSelectedValue()!=null && newPlan.getCatalog().findCourse2(allCourses.getSelectedValue().toString())!=null)
				{
					Attempt newAttempt= new Attempt();
					newPlan.addToPlan(allCourses.getSelectedValue().toString(),"PLN");
					String[] choices = {"Fall","Winter"};
					if(allCourses.getSelectedValue().getSemesterOffered().equals("W"))
					{
						choices[0]="Winter";
						choices[1]=null;
					}
					else if(allCourses.getSelectedValue().getSemesterOffered().equals("F"))
					{
						choices[1]=null;
					}
					
				    String input = (String) JOptionPane.showInputDialog(null, "Choose Semester", "Semester", JOptionPane.QUESTION_MESSAGE, null, choices,choices[0]);
				    if(input!=null)
				    {
				    	String[] choices2 = new String[50];
					    for(int i=0;i<50;i++)
					    {
					    	choices2[i]=Integer.toString(i+2018);
					    }
					    
					    String input2 = (String) JOptionPane.showInputDialog(null, "Choose Year", "Year", JOptionPane.QUESTION_MESSAGE, null, choices2,choices2[0]);
					    if(input2!=null)
					    {
						newAttempt.setSemesterTaken(input.charAt(0)+input2.substring(2,4));
						newAttempt.setCourseAttempted(newPlan.getCatalog().findCourse2(allCourses.getSelectedValue().toString()));
						newAttempt.setAttemptGrade("PLN");
						model3.addElement(newAttempt);
					    }
				    }
				    printLists();
				    
				}
			}
		});
		
		btnPlanAdd.setBounds(10, 57, 118, 23);
		contentPane.add(btnPlanAdd);
		
		
		
		JButton btnPlanRemove = new JButton("Remove");
		btnPlanRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(planned.getSelectedValue()!=null)
				{
					newPlan.removeFromPlan((planned.getSelectedValue().toStringNoSemester()));
					model3.removeElement(planned.getSelectedValue());
					printLists();
				}
				
			}
		});
		btnPlanRemove.setBounds(10, 91, 118, 23);
		contentPane.add(btnPlanRemove);
		
		JLabel lblTranscript = new JLabel("TRANSCRIPT");
		lblTranscript.setBounds(34, 159, 83, 14);
		contentPane.add(lblTranscript);
		
		
		JButton btnTransAdd = new JButton("Add From Plan");
		btnTransAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				
				if(planned.getSelectedValue()!=null)
				{
					newPlan.removeFromPlan((planned.getSelectedValue().toStringNoSemester()));
					newPlan.addToAttempt(planned.getSelectedValue());
					model2.addElement(planned.getSelectedValue());
					model3.removeElement(planned.getSelectedValue());
					printLists();
				}
			}
		});
		btnTransAdd.setBounds(10, 184, 118, 23);
		contentPane.add(btnTransAdd);
		
		
		
		
		
		JButton btnModifyGrade = new JButton("Modify Grade");
		btnModifyGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(transcript.getSelectedValue()!=null)
				{
					String newGrade="vf";
					int count=0;
					while(!newGrade.equals("P") && !newGrade.equals("F") && !newGrade.equals("INC") && !newGrade.equals("MNR") && !(Character.isDigit(newGrade.charAt(0)) && Character.isDigit(newGrade.charAt(1))))
					{
						newGrade = JOptionPane.showInputDialog(null, "New Grade: ");
						if(count!=0 && !newGrade.equals("P") && !newGrade.equals("F") && !newGrade.equals("INC") && !newGrade.equals("MNR") && !(Character.isDigit(newGrade.charAt(0)) && Character.isDigit(newGrade.charAt(1))))
						{
							try {
								throw new BadGrade();
							} catch (BadGrade e1) {
								
							}
						} 
						count++;
					}
					newPlan.getStudent().findAttemptList(transcript.getSelectedValue().toStringNoSemester()).setAttemptGrade(newGrade);
					model2.removeAllElements();
					if(newPlan.getStudent().getAttemptList().size()!=0)
					{
						for(Attempt s:newPlan.getStudent().getAttemptList()){
							model2.addElement(s);
						}
					}
					printLists();
				}
			}
			
		});
		btnModifyGrade.setBounds(10, 225, 118, 23);
		contentPane.add(btnModifyGrade);
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(transcript.getSelectedValue()!=null)
				{
					newPlan.removeFromAttempt((transcript.getSelectedValue().toStringNoSemester()));
					model2.removeElement(transcript.getSelectedValue());
					printLists();
				}
			}
		});
		btnNewButton.setBounds(10, 265, 118, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextPane textPane = new JTextPane();
				textPane.setBounds(90, 485, 50, 23);
				textPane.setEditable(false);
				textPane.setText(Double.toString(newPlan.getGPA()));
				contentPane.add(textPane);
				
				JTextPane textPane2 = new JTextPane();
				textPane2.setBounds(90, 547, 50, 23);
				textPane2.setEditable(false);
				textPane2.setText(Double.toString(newPlan.getCISGPA()));
				contentPane.add(textPane2);
				
				JTextPane textPane3 = new JTextPane();
				textPane3.setBounds(90, 604, 50, 23);
				textPane3.setEditable(false);
				textPane3.setText(Double.toString(newPlan.getGPA()));
				contentPane.add(textPane3);
				
				textField = new JTextField();
				textField.setBounds(10, 753, 130, 20);
				textField.setEditable(false);
				contentPane.add(textField);
				textField.setColumns(10);
				if(newPlan.graduate())
				{
					textField.setText("You can graduate!");
				}
				else
				{
					textField.setText("You can not graduate.");
				}
				
				
			}
		});
		btnNewButton_1.setBounds(10, 431, 118, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		
		JLabel lblGpa = new JLabel("GPA:");
		lblGpa.setBounds(10, 489, 54, 14);
		contentPane.add(lblGpa);
		
		JLabel lblCisGpa = new JLabel("CIS GPA:");
		lblCisGpa.setBounds(10, 550, 69, 14);
		contentPane.add(lblCisGpa);
		
		JLabel lblLastGpa = new JLabel("Last 10 GPA:");
		lblLastGpa.setBounds(10, 607, 107, 14);
		contentPane.add(lblLastGpa);
		
		JButton btnNewButton_2 = new JButton("Administrator");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String newFile = JOptionPane.showInputDialog(null, "New Course Catalog File: (if you don't have one insert 'q' to quit) (do not include .txt)");
				if(!newFile.equals("q"))
				{
					newPlan.getCatalog().initializeCatalog(newFile+".txt");
					updateLists();
				}
				
			}
		});
		btnNewButton_2.setBounds(10, 688, 118, 23);
		contentPane.add(btnNewButton_2);
		
		
	}
	
	public static void printLists()
	{
		/*
		System.out.println("Attempts:");
		for(Attempt attempts: newPlan.getStudent().getAttemptList())
		{
			System.out.println(attempts.getCourseAttempted().getCourseCode());
		}
		System.out.println("Plan:");
		for(Attempt attempts: newPlan.getStudent().getPlanList())
		{
			System.out.println(attempts.getCourseAttempted().getCourseCode());
		}
		*/
	}
	
	public static void updateLists()
	{
		model.removeAllElements();
		model2.removeAllElements();
		model3.removeAllElements();
		for(Attempt s:newPlan.getStudent().getPlanList()){
		    model3.addElement(s);
		}
		for(Attempt s:newPlan.getStudent().getAttemptList()){
			model2.addElement(s);
		}
		for(Course s:newPlan.getCatalog().getCatalog()){
		    model.addElement(s);
		}
	}
}

















