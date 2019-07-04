package exam.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
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

@SuppressWarnings("serial")
public class EmployeeUI extends JFrame implements ActionListener,ItemListener {
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
		setBounds(100, 100, 494, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelText = new PanelEmployeeText();
		panelText.setBounds(5, 5, 473, 280);
		
		panelText.getCmbMgn().addItemListener(this);
		panelText.getCmbDept().addItemListener(this);
		panelText.getCmbTitle().addItemListener(this);
		
		contentPane.add(panelText);
		
		panelList = new PanelEmployeeList();
		panelList.setParent(this);
		panelList.setBounds(5, 318, 473, 262);
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
		panelText.defaultNoText();
		contentPane.add(panelList);
		
		panel = new JPanel();
		panel.setBounds(5, 284, 473, 33);
		contentPane.add(panel);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		panel.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
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
		btnAdd.setText("추가");
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		try {
			empList=empDao.selectEmployeeByAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panelText.setEmpList(empList);
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
			panelList.reloadData();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panelText.defaultNoText();
	}
	public void popupUpdate(Employee emp) {
		panelText.setTextEmployee(emp);
		btnAdd.setText("수정");
	}
	public void popupDelete(Employee emp) {
		try {
			empDao.deleteEmployee(emp);
			empList=empDao.selectEmployeeByAll();
			panelList.setEmpList(empList);
			panelList.reloadData();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panelText.setEmpList(empList);
		panelText.defaultNoText();
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getItem() instanceof Department) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				Department dept = (Department) e.getItem();
				List<Employee> empList = new Vector<Employee>();
				empList=deptDao.selectDeptNo(dept);
				panelText.setCmbMgnModel(empList);
			}
		}
		if(e.getItem() instanceof Title) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				Title emp = (Title) e.getItem();
			}
		}
		if(e.getItem() instanceof Employee) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				Employee emp = (Employee) e.getItem();
			}
		}
	}
	public void defaultNoText() {
		panelText.defaultNoText();
	}
	

}
