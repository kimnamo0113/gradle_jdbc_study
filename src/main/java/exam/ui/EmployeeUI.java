package exam.ui;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
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
import java.awt.GridLayout;
import java.awt.BorderLayout;
import exam.ui.content.PanelEmployeeList;

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

	public EmployeeUI() {
		empDao=new EmployeeDaoImpl();
		deptDao=new DepartmentDaoImpl();
		titleDao=new TitleDaoImpl();
		initComponents();
	}
	
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 445, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelText = new PanelEmployeeText();
		
		
		
		contentPane.add(panelText);
		
		panelList = new PanelEmployeeList();
		
		
		
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
	}
	
	

	
	
	

}
