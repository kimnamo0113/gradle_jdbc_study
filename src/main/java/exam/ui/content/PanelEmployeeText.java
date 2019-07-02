package exam.ui.content;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	private JTextField textEno;
	private JTextField textEmpName;
	private JTextField textSalary;
	private JTextField textGender;
	private JTextField textDate;
	
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
	
	
	
	
	public JTextField getTextEno() {
		return textEno;
	}
	
	
	public JTextField getTextEmpName() {
		return textEmpName;
	}
	
	
	public JComboBox<Title> getCmbTitle() {
		return cmbTitle;
	}
	public void setCmbTitle(JComboBox<Title> cmbTitle) {
		this.cmbTitle = cmbTitle;
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
		
		textEno = new JTextField();
		textEno.setColumns(10);
		panel_1.add(textEno);
		
		JLabel lblName = new JLabel("사원명");
		panel_1.add(lblName);
		
		textEmpName = new JTextField();
		textEmpName.setHorizontalAlignment(SwingConstants.CENTER);
		textEmpName.setColumns(10);
		panel_1.add(textEmpName);
		
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
		
		textDate = new JTextField();
		textDate.setColumns(10);
		panel_1.add(textDate);
		
	}

	public void setting() {
		int no=empList.get(empList.size()-1).getEmpNo()+1;
		textEno.setText(no+"");
		textEno.setEditable(false);
		
		DefaultComboBoxModel<Department> newDept = new DefaultComboBoxModel<Department>(new Vector<Department>(deptList));
		cmbDept.setModel(newDept);
		
		DefaultComboBoxModel<Title> newTitle = new DefaultComboBoxModel<Title>(new Vector<Title>(titleList));
		cmbTitle.setModel(newTitle);
	}
	public Employee getEmployee() {
		int eno=Integer.parseInt(textEno.getText().trim());
		String empName=textEmpName.getText().trim();
		Title title=(Title) cmbTitle.getSelectedItem();
		int salary=Integer.parseInt(textSalary.getText().trim());
		int gender=Integer.parseInt(textGender.getText().trim());
		Department dept=(Department)cmbDept.getSelectedItem();
		String date = textDate.getText().trim();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Date day = null;
		try {
			day=sf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Employee(eno,empName,title,salary,gender,dept,day);
	}
	public void defaultNoText() {
		textEno.setText(empList.get(empList.size()-1).getEmpNo()+1+"");
		textEmpName.setText("");
		cmbTitle.setSelectedItem(-1);
		textSalary.setText("");
		textGender.setText("");
		cmbDept.setSelectedItem(-1);
		Date date=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		textDate.setText(sf.format(date));
	}
	
	public void setTextEmployee(Employee emp) {
		textEno.setText(emp.getEmpNo()+"");
		textEmpName.setText(emp.getEmpName());
		cmbTitle.setSelectedItem(-1);
		textSalary.setText("");
		textGender.setText("");
		cmbDept.setSelectedItem(-1);
		Date date=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		textDate.setText(sf.format(date));
	}
	

}
