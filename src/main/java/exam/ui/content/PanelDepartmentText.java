package exam.ui.content;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import exam.dto.Department;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class PanelDepartmentText extends JPanel {
	private JTextField textDno;
	private JTextField textDeptName;
	private JTextField textFloor;

	private List<Department> deptList;
	
	
	
	public List<Department> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

	public JTextField getTextDeptName() {
		return textDeptName;
	}
	public void setTextDeptName(String textDeptName) {
		this.textDeptName.setText(textDeptName); 
	}
	public JTextField getTextFloor() {
		return textFloor;
	}
	public void setTextFloor(String textFloor) {
		this.textFloor.setText(textFloor);
	}
	public void setTextDno(String textDno) {
		this.textDno.setText(textDno);
	}
	public PanelDepartmentText() {
		initComponents();
	}
	
	public void defaultNoText() {
		int number=deptList.get(deptList.size()-1).getDeptNo()+1;
		String no=String.format("T%03d", number);
		textDno.setText(no);
		textDeptName.setText("");
		textFloor.setText("");
		
	}
	
	
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDno = new JLabel("번호");
		panel_2.add(lblDno);
		
		textDno = new JTextField();
		panel_2.add(textDno);
		textDno.setColumns(10);
		
		JLabel lblDeptName = new JLabel("부서명");
		panel_2.add(lblDeptName);
		
		textDeptName = new JTextField();
		panel_2.add(textDeptName);
		textDeptName.setColumns(10);
		
		JLabel lblFloor = new JLabel("위치");
		panel_2.add(lblFloor);
		
		textFloor = new JTextField();
		textFloor.setColumns(10);
		panel_2.add(textFloor);
	}
	
	public JTextField getTextDno() {
		return textDno;
	}
	
	public void setDeptNoAble(boolean b) {
		textDno.setEditable(b);
	}
	public void setting() {
		int defaultNo=deptList.get(deptList.size()-1).getDeptNo()+1;
		String result_no = String.format("D%03d", defaultNo);
		textDno.setText(result_no);
	}
	

}
