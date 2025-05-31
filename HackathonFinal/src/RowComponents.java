import javax.swing.*;
import java.awt.*;

public class RowComponents {
    private JCheckBox major;
    private JTextField subjectText;
    private JComboBox<String> gradeCombo;
    private JComboBox<String> semesterCombo;
    private JTextField creditText;
    //private JPanel labelWrapper;
    private JPanel panel;

    public RowComponents(String[] grades, String[] semesters) {
        major = new JCheckBox();
        subjectText = new JTextField(10);
        semesterCombo = new JComboBox<>(semesters);
        semesterCombo.setSelectedItem("2025-1학기");
        gradeCombo = new JComboBox<>(grades);
        gradeCombo.setPreferredSize(new Dimension(60, 20));
        creditText = new JTextField(5);

        //labelWrapper = new JPanel();
		//labelWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel = new JPanel(new GridLayout(1, 5, 10, 5));
        panel.add(major);
        panel.add(subjectText);
        panel.add(semesterCombo);
        panel.add(gradeCombo);
        panel.add(creditText);
        //labelWrapper.add(panel);
    }

    public JPanel getPanel() {
        return panel;
    }
    
    public boolean isMajor() {
    	return major.isSelected();
    }
    public String getGrade() {
    	return (String) gradeCombo.getSelectedItem();
    }
    // rowcomponent  = newlkjlk rowcompoment.getSemeter()
    // subject = new () 
    public String getSemester() {
    	return  (String) semesterCombo.getSelectedItem();
    }
    public String getSubject() {
    	 try {
             return (subjectText.getText());
         } catch (Exception e) {
             return "";
         }
    }
    public double getCredit() {
        try {
            return Double.parseDouble(creditText.getText());
        } catch (Exception e) {
            return 0.0;
        }
    }

    // getter 추가 필요 시 여기에 정의 가능
}