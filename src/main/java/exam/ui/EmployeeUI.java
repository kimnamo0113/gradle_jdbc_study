package exam.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exam.dao.DepartmentDao;
import exam.dao.EmployeeDao;
import exam.dao.TitleDao;
import exam.daoImpl.DepartmentDaoImpl;
import exam.daoImpl.EmployeeDaoImpl;
import exam.daoImpl.TitleDaoImpl;
import exam.dto.Department;
import exam.dto.Employee;
import exam.dto.Title;
import exam.ui.content.PanelEmployeeList;
import exam.ui.content.PanelEmployeeText;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class EmployeeUI extends JFrame {
	private JPanel contentPane;
	
	private EmployeeDao empDao;
	private DepartmentDao deptDao;
	private TitleDao titleDao;
	
	private PanelEmployeeText panelText;
	private PanelEmployeeList panelList;
	
	private List<Employee> empList=null;
	private List<Department> deptList=null;
	private List<Title> titleList=null;
	private JPanel panel;
	private JButton btnAdd;
	private JButton btnCancel;

	public EmployeeUI() {
		empDao=new EmployeeDaoImpl();
		deptDao=new DepartmentDaoImpl();
		titleDao=new TitleDaoImpl();
		initComponents();
	}
	
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 445, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelText = new PanelEmployeeText();
		panelText.setBounds(5, 5, 419, 157);
		
		
		
		contentPane.add(panelText);
		
		panelList = new PanelEmployeeList();
		panelList.setBounds(5, 194, 419, 268);
		try {
			empList=empDao.selectEmployeeByAll();
			deptList=deptDao.selectDepartmentByAll();
			titleList=titleDao.selectTitleByAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panelList.setEmpList(empList);
		panelList.reloadData();
		panelText.setEmpList(empList);
		panelText.setDeptList(deptList);
		panelText.setTitleList(titleList);
		panelText.setting();
		contentPane.add(panelList);
		
		panel = new JPanel();
		panel.setBounds(5, 161, 419, 33);
		contentPane.add(panel);
		
		btnAdd = new JButton("추가");
		panel.add(btnAdd);
		
		btnCancel = new JButton("취소");
		panel.add(btnCancel);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().equals("추가"))
				actionPerformedBtnAdd(e);
			else
				actionPerformedBtnUpdate(e);
		}
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
	}
	private void actionPerformedBtnUpdate(ActionEvent e) {
		
		
		Employee emp=panelText.getEmployee();
		try {
			int res=empDao.updateEmployee(emp);
			if(res>-1) {
				JOptionPane.showMessageDialog(null, "수정완료");
				empList=empDao.selectEmployeeByAll();
				panelList.setEmpList(empList);
				panelList.reloadData();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		panelText.setEmpList(empList);
		panelText.defaultNoText();
		
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		panelText.defaultNoText();
		btnAdd.setText("추가");
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
				
		Employee emp=panelText.getEmployee();
		try {
			empDao.insertEmployee(emp);
			JOptionPane.showMessageDialog(null, "사원 추가 성공");
			empList=empDao.selectEmployeeByAll();
			panelList.setEmpList(empList);
			panelText.setEmpList(empList);
			panelText.defaultNoText();
			panelList.reloadData();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void popupUpdate(Employee emp) {
		panelText.setTextEmployee(emp);
		btnAdd.setText("수정");
	}
	public void popupDelete(Department dept) {
		try {
			deptDao.deleteDepartment(dept);
			deptList=deptDao.selectDepartmentByAll();
			panelList.setDeptList(deptList);
			panelList.reloadData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

}
