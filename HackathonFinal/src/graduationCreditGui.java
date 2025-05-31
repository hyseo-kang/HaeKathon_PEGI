import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class graduationCreditGui extends JFrame implements ActionListener, KeyListener{
	//여기부터 시작
	private JButton back = new JButton("뒤로가기");
	
	public graduationCreditGui(String userName, double avg, int retakeCount, ArrayList<Subject> subjectList, GradualStatus status) { //이거 불러올 때 인자 넣어주기
		setTitle("당신의 학점은?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫았을 때 메모리에서 제거되도록 하는 코드
		setSize(450, 800);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JLabel userInfo = new JLabel(userName + "님의 현재 학점은 " + String.format("%.2f", avg) + "점 입니다.");
		userInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.add(userInfo);
		mainPanel.add(namePanel);
		
		JPanel line1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel line1Label = new JLabel("--------------------------------------------------------");
		line1Label.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		line1Panel.add(line1Label);
		mainPanel.add(line1Panel);
		
		// semester 오름차순 정렬 와 넘 어려워
		Collections.sort(subjectList, Comparator.comparing(Subject::getSemester));
		
		// 정렬된 리스트를 패널에 표시
		String currentSemester = ""; // 처음에는 빈 문자열로 초기화

		for (Subject s : subjectList) {
		    String semesterPrint = s.getSemester();

		    // 새로운 학기면 제목 출력
		    if (!semesterPrint.equals(currentSemester)) {
		        currentSemester = semesterPrint;
		        double endCredit = status.getSemPerCredit().get(currentSemester);
		        // 학기 JLabel을 따로 한 줄로 출력
		        JLabel semesterLabel = new JLabel("[" + currentSemester + "] " + "(" + "이수 학점 : " + (int)endCredit + ")");
		        semesterLabel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		        JPanel semesterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		        semesterPanel.add(semesterLabel);
		        mainPanel.add(semesterPanel);
		    }

		    // 과목 정보 출력
		    String subjectPrint = s.getSubject();
		    //String paddedSubject = String.format("%-19s", subjectPrint);
		    
		    int displayWidth = getDisplayWidth(subjectPrint); // 한글 너비 고려한 길이
		    int paddingWidth = 19 - displayWidth;
		    StringBuilder paddedSubject = new StringBuilder(subjectPrint);

		    // 공백 추가
		    for (int i = 0; i < paddingWidth; i++) {
		        paddedSubject.append(" ");
		    }
		    
		    String majorPrint = (s.isType() ? "전공" : "교양");
		    int creditPrint = (int) s.getCredit();
		    String info = paddedSubject + " |   " + majorPrint + "   |   " + creditPrint + "학점";

		    JLabel subjectLabel = new JLabel(info);
		    subjectLabel.setFont(new Font("돋움체", Font.PLAIN, 16));
		    JPanel subjectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		    subjectPanel.add(subjectLabel);
		    mainPanel.add(subjectPanel);
		}
		
		JPanel line2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel line2Label = new JLabel("--------------------------------------------------------");
		line2Label.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		line2Panel.add(line2Label);
		mainPanel.add(line2Panel);
		
		JLabel userAllCreditInfo = new JLabel(userName + "님의 졸업 기준 학점은 " + (int)status.getGCredit() + "입니다.");
		userAllCreditInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		JLabel userRemainCreditInfo = new JLabel(userName + "님의 졸업까지 남은 학점은 " + (int)status.getRemainCredit() + "입니다." + "(" + (double)status.getRemainPercent() + "%)");
		userRemainCreditInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		JLabel userRetakeCountInfo = new JLabel(userName + "님의 재수강 과목 수는 " + (int)retakeCount + "입니다.");
		userRetakeCountInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		
		
		//JPanel remainCreditPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//remainCreditPanel.add(userRemainCreditInfo);
		//mainPanel.add(remainCreditPanel);
		//remainCreditPanel.add(userRetakeCountInfo); //수정중이다.
		//mainPanel.add(remainCreditPanel);
		
		// 맨 앞줄: 졸업하기 위한 학점
		JPanel allCreditPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		allCreditPanel.add(userAllCreditInfo);
		mainPanel.add(allCreditPanel);
		
		// 첫 번째 줄: 남은 학점
		JPanel remainCreditPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		remainCreditPanel.add(userRemainCreditInfo);
		mainPanel.add(remainCreditPanel);

		// 두 번째 줄: 재수강 과목 수
		JPanel retakeCountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		retakeCountPanel.add(userRetakeCountInfo);
		mainPanel.add(retakeCountPanel);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(back);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);
		
		back.addActionListener(this);
		
        add(new JScrollPane(mainPanel)); // 스크롤 가능하게
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	
	public void actionPerformed(ActionEvent e) { 
		try {
			if (e.getSource() == back) {
				setVisible(false);
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
	
	// 한글은 2칸, 나머지는 1칸으로 계산
	public static int getDisplayWidth(String str) {
	    int width = 0;
	    for (int i = 0; i < str.length(); i++) {
	        char ch = str.charAt(i);
	        if (isKorean(ch)) {
	            width += 2;
	        } else {
	            width += 1;
	        }
	    }
	    return width;
	}

	public static boolean isKorean(char ch) {
	    return (ch >= 0xAC00 && ch <= 0xD7A3); // 한글 유니코드 범위
	}

}
