import java.util.ArrayList;

public class Subject {

	    String subject;
	    String semester;
	    String grade;
	    String professor;
	    String code;
	    boolean type;
	    double credit;
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
	        System.out.println(subject + "," + semester + ","  + grade +"," + professor+"," +code+"," +type+ ","+credit);
	    }

}
