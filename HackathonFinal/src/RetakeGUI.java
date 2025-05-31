import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class RetakeGUI extends JFrame implements ActionListener {

    private JButton back = new JButton("뒤로가기");

    public RetakeGUI(ArrayList<Subject> retakeSubjects) {
        setTitle("눈물의 재수강 리스트");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"과목명", "수강 학기", "등급", "학점", "전공 여부"};
        String[][] data = new String[retakeSubjects.size()][5];

        for (int i = 0; i < retakeSubjects.size(); i++) {
            Subject s = retakeSubjects.get(i);
            data[i][0] = s.subject;
            data[i][1] = s.semester;
            data[i][2] = s.grade;
            data[i][3] = String.valueOf(s.credit);
            data[i][4] = s.type ? "전공" : "비전공";
        }

        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(back);
        back.addActionListener(this);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            setVisible(false);
        }
    }
}