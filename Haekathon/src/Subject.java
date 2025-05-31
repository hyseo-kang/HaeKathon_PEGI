import java.util.ArrayList;

public class Subject {

	    String subject;
	    String semester;
	    String grade;
	    String professor;
	    String code;
	    String type;
	    String credit;
	    public Subject(String subject, String semester, String grade, String professor, String code,String type,String credit) {
	        this.subject = subject;
	        this.semester = semester;
	        this.grade = grade;
	        this.professor=professor;
	        this.code=code;
	        this.type=type;
	        this.credit=credit;
	    }
	    private ArrayList<Subject> retakeSubjects;
	    public String getSubject() {
	        return subject;
	    }

	    public String getGrade() {
	        return grade;
	    }

	    public String getSemeter() {
	        return semester;
	    }

	    public String getProfessor() {
	        return professor;
	    }
	    public void printInfo() {
	        System.out.println(subject + "," + semester + ","  + grade +"," + professor+"," +code+"," +type+ ","+credit);
	    }

}
