import java.util.ArrayList;
import java.util.Scanner;

public class RegisterScore {

    // 등급별 점수 변환
    public static double gradeToScore(String grade) {
        switch (grade) {
            case "A+": return 4.3;
            case "A0": return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B0": return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C0": return 2.0;
            case "C-": return 1.7;
            case "D+": return 1.3;
            case "D0": return 1.0;
            case "F": return 0.0;
            default: return 0.0; // 잘못된 입력 대비
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Subject> listsub = new ArrayList<>();
        Retake retakeChecker = new Retake();

        System.out.print("이름을 입력하세요: ");
        String name = sc.nextLine();
        System.out.println("안녕하세요, " + name + "님!");

        while (true) {
            System.out.print("과목명 입력 (그만하려면 'q' 입력): ");
            String subject = sc.nextLine();
            if (subject.equalsIgnoreCase("q")) break;

            System.out.print("수강 학기 (년도, 1/2/여름/겨울): ");
            String semester = sc.nextLine();

            System.out.print("등급 입력 (A+, A0, A-, ...): ");
            String grade = sc.nextLine();
            
            System.out.print("교수님 입력 : ");
            String professor = sc.nextLine();
            
            System.out.print("과목 코드 입력 : ");
            String code = sc.nextLine();
            
            System.out.print("종류 (전공/교양) : ");
            String type = sc.nextLine();

            System.out.print("과목 학점 : ");
            double credit = sc.nextDouble();
            Subject sub=new Subject(subject, semester, grade, true, credit);
            listsub.add(sub);
            retakeChecker.checkAndAddRetake(sub);
            
        }

        // 과목 리스트 출력
        System.out.println("\n[ 재수강 대상 과목 목록 ]");
        for (Subject sub : listsub) {
        	sub.printInfo();
        }

        // 평균 학점 계산
        double totalScore = 0.0;
        int totalSubjects = listsub.size();
        int retakeCount = 0;

        for (Subject sub : listsub) {
            double score = gradeToScore(sub.grade);
            totalScore += score;

            // B- 이하일 경우 재수강 대상
            if (score <= 2.7) {
                retakeCount++;
            }
        }

        double avgScore = (totalSubjects > 0) ? (totalScore / totalSubjects) : 0.0;
        System.out.printf("\n현재까지 평균 학점 (4.3 만점): %.2f\n", avgScore);
        System.out.println("재수강 대상 과목 수: " + retakeCount);
        retakeChecker.printRetakeSubjects();
    }
}