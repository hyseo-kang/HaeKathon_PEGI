
public class SubjectInfo {
	private String subject;
	private String code;
	private String grade;
	private int credit;
	private String semester;
	private String type;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public boolean isLaterSem(SubjectInfo target) {
		String[] cur = this.semester.split("-");
		String[] tar = target.semester.split("-");
		
		int curYear = Integer.parseInt(cur[0]);
		int curSem = Integer.parseInt(cur[1]);
		int tarYear = Integer.parseInt(tar[0]);
		int tarSem = Integer.parseInt(tar[1]);
		
		return ( (curYear > tarYear) ||
				((curYear == tarYear) && (curSem > tarSem)) );
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
