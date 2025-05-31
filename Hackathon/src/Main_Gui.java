import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main_Gui extends JFrame implements ActionListener, KeyListener{
	Calculator_Gui calculator = null;
	JLabel label_Name = new JLabel("학점 계산기");
	JButton enter = new JButton("start!");
	
	public Main_Gui() {
		setTitle("학점 계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫았을 때 메모리에서 제거되도록 하는 코드
		setSize(500, 800);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout(10, 10));
		
		JPanel centerPanel = new JPanel(new FlowLayout());
		centerPanel.add(label_Name);
		centerPanel.add(enter);
		enter.setHorizontalAlignment(SwingConstants.CENTER);
		enter.setVerticalAlignment(SwingConstants.CENTER);
		
		c.add(centerPanel, BorderLayout.CENTER);
		
		enter.addActionListener(this);
		
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e) { 
		try {
			if (e.getSource() == enter) {
				setVisible(false);
				calculator = new Calculator_Gui();
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
}
