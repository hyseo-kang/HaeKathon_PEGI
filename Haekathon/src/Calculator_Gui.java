import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator_Gui extends JFrame implements ActionListener, KeyListener{
	private String[] grades = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D+", "D0", "D-", "F"};
	private JPanel subjectPanel;
	JLabel userName = new JLabel("이름");
	JTextField nameText = new JTextField(20);
	private JButton enter = new JButton("계산하기");
	private JButton addSubject = new JButton("과목 추가");
	private ArrayList<RowComponents> rowList = new ArrayList<>(); // 입력 행 리스트
	// 클래스 멤버 변수로 졸업 학점 필드 추가
	private ArrayList<Subject> subjectlist = new ArrayList<>();//과목 정보 배열 저장-oje
	Retake retakeChecker = new Retake();
	private JTextField graduationCreditField = new JTextField(20);
	
	public Calculator_Gui() {
		setTitle("학점 계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫았을 때 메모리에서 제거되도록 하는 코드
		setSize(1000, 800);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.add(userName);
		namePanel.add(nameText);
		mainPanel.add(namePanel);
		
		JPanel graduationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		graduationPanel.add(new JLabel("졸업 학점"));
		graduationPanel.add(graduationCreditField);
		mainPanel.add(graduationPanel);
		
		//mainPanel.add(Box.createVerticalStrut(10));
		
		// 라벨 행을 가운데 정렬로 감싸기
		//JPanel labelWrapper = new JPanel();
		//labelWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel labelRow = new JPanel(new GridLayout(1, 5, 10, 10));
		labelRow.add(new JLabel("전공"));
        labelRow.add(new JLabel("과목"));
        labelRow.add(new JLabel("수강 학기"));
        labelRow.add(new JLabel("등급"));
        labelRow.add(new JLabel("학점"));
        //labelWrapper.add(labelRow);
		//mainPanel.add(labelWrapper);
        mainPanel.add(labelRow);
        
        // 과목 입력 패널 (여기에 동적으로 행 추가)
        subjectPanel = new JPanel();
        subjectPanel.setLayout(new BoxLayout(subjectPanel, BoxLayout.Y_AXIS));
        subjectPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(subjectPanel);
        
        // 기본 6줄 생성
        addSubjectRows(6);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addSubject);
        buttonPanel.add(enter);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);
		
		enter.addActionListener(this);
		addSubject.addActionListener(this);
		
        add(new JScrollPane(mainPanel)); // 스크롤 가능하게
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	private String[] generateSemesterOptions(int startYear, int endYear) {
		ArrayList<String> list = new ArrayList<>();
		for (int year = startYear; year <= endYear; year++) {
			list.add(year + "-1학기");
			list.add(year + "-여름계절학기");
			list.add(year + "-2학기");
			list.add(year + "-겨울계절학기");
		}
		return list.toArray(new String[0]);
	}
	
	private void addSubjectRows(int count) {
		String[] semesters = generateSemesterOptions(2021, 2025);
	    for (int i = 0; i < count; i++) {
	        RowComponents row = new RowComponents(grades, semesters); // grades를 생성자 인자로 전달
	        rowList.add(row);
	        subjectPanel.add(row.getPanel());
	    }
	    subjectPanel.revalidate();
	    subjectPanel.repaint();
	}
	
	public void actionPerformed(ActionEvent e) { 
		try {
			if (e.getSource() == enter) {
				double totalCredit = 0.0;
	            double totalGradePoint = 0.0;
	            int retakeCount = 0;
	            
	            double graduationCredit;
	            try {
	            	graduationCredit = Double.parseDouble(graduationCreditField.getText());
	            } catch (NumberFormatException ex) {
	            	JOptionPane.showMessageDialog(this, "졸업 학점을 올바르게 입력해주세요.");
	                return;
	            }
	            
	            Map<String, Double> gradeToPoint = Map.ofEntries(
	            		Map.entry("A+", 4.5), Map.entry("A0", 4.3), Map.entry("A-", 4.0),
	            	    Map.entry("B+", 3.7), Map.entry("B0", 3.5), Map.entry("B-", 3.0),
	            	    Map.entry("C+", 2.7), Map.entry("C0", 2.5), Map.entry("C-", 2.0),
	            	    Map.entry("D+", 1.7), Map.entry("D0", 1.5), Map.entry("D-", 1.0),
	            	    Map.entry("F", 0.0)
	            );

	            for (RowComponents row : rowList) {
	                String grade = row.getGrade();
	                double credit = row.getCredit();
	                String subject = row.getSubject();
	                String semester= row.getSemester();
	                boolean major=row.isMajor();
	                Subject sub=new Subject(subject, semester, grade, major, credit);
	                subjectlist.add(sub);
	                retakeChecker.checkAndAddRetake(sub);
	                if (gradeToPoint.containsKey(grade)) {
	                    totalGradePoint += gradeToPoint.get(grade) * credit;
	                    totalCredit += credit;

	                    // 재수강 대상
	                    if (Arrays.asList("B-", "C+", "C0", "C-", "D+", "D0", "D-", "F").contains(grade)) {
	                        retakeCount++;
	                    }
	                }
	            }

	            double average = (totalCredit > 0) ? totalGradePoint / totalCredit : 0.0;
	            String name = nameText.getText();

	            // 새 창 띄우기
	            showResultWindow(name, average, retakeCount, totalCredit, graduationCredit);
	            
				//JOptionPane.showMessageDialog(this, "입력이 완료되었습니다.");
				//setVisible(false);
			} else if (e.getSource() == addSubject) {
				addSubjectRows(3);
			}
		} catch (Exception a) {
			JOptionPane.showMessageDialog(null, "올바르지 않은 입력입니다.");
		}
	}
	
	public void keyPressed(KeyEvent e) {
		//사용 안 할 예정, 그렇지만 필요함.
	}
	
	public void keyTyped(KeyEvent e) {
		//사용 안 할 예정, 그렇지만 필요함.
	}
	
	public void keyReleased(KeyEvent e) {
		//사용 안 할 예정, 그렇지만 필요함.
	}
	
	private void showResultWindow(String name, double avg, int retakeCount, double totalCredit, double graduationCredit) {
	    JFrame resultFrame = new JFrame("결과");
	    resultFrame.setSize(400, 300);
	    resultFrame.setLocationRelativeTo(null);

	    JPanel resultPanel = new JPanel();
	    resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
	    resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	    resultPanel.add(new JLabel(name + "님의"));
	    resultPanel.add(Box.createVerticalStrut(10));
	    resultPanel.add(new JLabel("학점 평균 : " + String.format("%.2f", avg) + "점"));
	    resultPanel.add(Box.createVerticalStrut(10));
	    resultPanel.add(new JLabel("재수강 과목 : " + retakeCount + "개"));
	    resultPanel.add(Box.createVerticalStrut(10));
	    
	    double remainingCredit = Math.max(graduationCredit - totalCredit, 0);
	    //사용자 기준 예상 학점 수강량(한 학기당 듣는 학점 수)
	    double creditsPerSemester = 20.0;
	    double semestersLeft = (remainingCredit > 0) ? Math.ceil(remainingCredit / creditsPerSemester) : 0;
	    //Math.ceil()은 올림처리 된다(예 : 5.55학기는 6학기로 표시됨. 소수점 첫째자리까지 보이고 싶다면 String.format() 사용.

	    resultPanel.add(new JLabel("졸업까지 " + (int)remainingCredit + "학점 남음. (약 " + (int)semestersLeft + "학기)"));
	    resultPanel.add(Box.createVerticalStrut(20));

	    JButton retakeBtn = new JButton("재수강 확인하기");
	    JButton gradBtn = new JButton("졸업학점 확인하기");
	    resultPanel.add(retakeBtn);
	    resultPanel.add(Box.createVerticalStrut(10));
	    resultPanel.add(gradBtn);
	    System.out.println("재수강 대상 과목 수: " + retakeCount);
        retakeChecker.printRetakeSubjects();
	    resultFrame.add(resultPanel);
	    resultFrame.setVisible(true);
	}

	
}
