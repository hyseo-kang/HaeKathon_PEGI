
import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class Hack extends JWindow {
	//깃허브 연동 테스트
	public Hack() {
		//JWindow의 크기
		this.setSize(655, 370);
		
		//JWindow를 화면 중앙으로 띄우기
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();	
		int leftTopX = centerPoint.x - this.getWidth()/2;
		int leftTopY = centerPoint.y - this.getHeight()/2;
		this.setLocation(leftTopX, leftTopY); 
		
		//JWindow에 이미지가 포함된 JLabel 추가
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(getClass().getResource("/calculator.png")));
		getContentPane().add(label, BorderLayout.CENTER);
		
		//마우스 클릭 이벤트 처리
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Main_Gui();
			}
		});
	}
	
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	Hack hack = new Hack();
	        	hack.setVisible(true);
	        }
	    });
	}	
}

