package exam.ui.content;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import exam.dto.Department;
import exam.dto.Employee;
import exam.dto.Title;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class PanelEmployeeText extends JPanel {
	private JTextField textNo;
	private JTextField textName;
	private JTextField textSalary;
	private JTextField textGender;
	private JTextField textDay;
	
	private List<Employee> empList;
	private List<Department> deptList;
	private List<Title> titleList;
	
	private JComboBox<Title> cmbTitle;
	private JComboBox<Department> cmbDept;
	
	
	
	public void setEmpList(List<Employee> empList) {
		this.empList=empList;
	}
	public void setDeptList(List<Department> deptList) {
		this.deptList=deptList;
	}
	public void setTitleList(List<Title> titleList) {
		this.titleList=titleList;
	}
	public PanelEmployeeText() {
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNo = new JLabel("번호");
		panel_1.add(lblNo);
		
		textNo = new JTextField();
		textNo.setColumns(10);
		panel_1.add(textNo);
		
		JLabel lblName = new JLabel("사원명");
		panel_1.add(lblName);
		
		textName = new JTextField();
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		textName.setColumns(10);
		panel_1.add(textName);
		
		JLabel lblTitle = new JLabel("직책");
		panel_1.add(lblTitle);
		
		cmbTitle = new JComboBox<Title>();
		panel_1.add(cmbTitle);
		
		JLabel lblSalary = new JLabel("급여");
		panel_1.add(lblSalary);
		
		textSalary = new JTextField();
		textSalary.setColumns(10);
		panel_1.add(textSalary);
		
		JLabel lblGender = new JLabel("성별");
		panel_1.add(lblGender);
		
		textGender = new JTextField();
		textGender.setColumns(10);
		panel_1.add(textGender);
		
		JLabel lblDept = new JLabel("부서");
		panel_1.add(lblDept);
		
		cmbDept = new JComboBox<Department>();
		panel_1.add(cmbDept);
		
		JLabel lblDay = new JLabel("입사일");
		panel_1.add(lblDay);
		
		textDay = new JTextField();
		textDay.setColumns(10);
		panel_1.add(textDay);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		
		JButton button = new JButton("추가");
		panel_2.add(button);
		
		JButton button_1 = new JButton("취소");
		panel_2.add(button_1);
		
	}

	public void setting() {
		int no=empList.get(empList.size()-1).getEmpNo()+1;
		textNo.setText(no+"");
		textNo.setEditable(false);
		
		DefaultComboBoxModel<Department> newDept = new DefaultComboBoxModel<Department>(new Vector<Department>(deptList));
		cmbDept.setModel(newDept);
		
		DefaultComboBoxModel<Title> newTitle = new DefaultComboBoxModel<Title>(new Vector<Title>(titleList));
		cmbTitle.setModel(newTitle);
	}
	
	
	

}
