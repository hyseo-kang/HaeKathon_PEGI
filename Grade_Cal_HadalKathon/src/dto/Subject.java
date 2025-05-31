package dto;

import java.util.ArrayList;

public class Subject {

	    public String subject;
	    public String semester;
	    public String grade;
	    public String professor;
	    public String code;
	    public boolean type;
	    public double credit;
	    
	    public Subject(String subject, String semester, String grade,boolean type,double credit) {
	        this.subject = subject;
	        this.semester = semester;
	        this.grade = grade;
	        this.type=type;
	        this.credit=credit;
	    }
	    
	    private ArrayList<Subject> retakeSubjects;
	 
	    
	    public String getGrades() {
	        return grade;
	    }



	    public void printInfo() {
	        System.out.println(subject + "," + semester + ","  + grade +"," +type+ ","+credit);
	    }

}
