import java.util.ArrayList;

public class Retake {
    private ArrayList<Subject> retakeSubjects;

    public Retake() {
        retakeSubjects = new ArrayList<>();
    }

    public void checkAndAddRetake(Subject subject) {
        if (RetakingSubjects(subject.getGrade())) {
            retakeSubjects.add(subject);
        }
    }

    private boolean RetakingSubjects(String grade) {
        // 학점이 B-, C+, C0, C-, D+, D0, D-, F 중 하나면 true 리턴
        if (grade.equalsIgnoreCase("B-")||grade.equalsIgnoreCase("C+") || grade.equalsIgnoreCase("C0") ||grade.equalsIgnoreCase("C-") || grade.equalsIgnoreCase("D+") || grade.equalsIgnoreCase("D0") ||grade.equalsIgnoreCase("D-") || grade.equalsIgnoreCase("F")) {
            return true;
        }
        return false;
    }

    public void printRetakeSubjects() {
        System.out.println("\n[ 재수강 대상 과목 목록 ]");
        for (Subject s : retakeSubjects) {
            s.printInfo();
        }
    }
}
