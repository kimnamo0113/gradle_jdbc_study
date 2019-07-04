package exam.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exam.dao.EmployeeDao;
import exam.daoImpl.EmployeeDaoImpl;
import exam.dto.Employee;
import exam.ui.content.PanelEmployeeList;

@SuppressWarnings("serial")
public class ErpMainPageUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnEmp;
	private JButton btnDept;
	private JButton btnTitle;
	private PanelEmployeeList panelList;
	private EmployeeUI empUI;
	private EmployeeDao dao;
	private DepartmentUI deptUI;
	private TitleUI titleUI;
	
	public ErpMainPageUI() {
		dao=new EmployeeDaoImpl();
		
		initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 107);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 10, 10));
		
		btnEmp = new JButton("사원관리");
		btnEmp.addActionListener(this);
		contentPane.add(btnEmp);
		
		btnDept = new JButton("부서관리");
		btnDept.addActionListener(this);
		contentPane.add(btnDept);
		
		btnTitle = new JButton("직책관리");
		btnTitle.addActionListener(this);
		contentPane.add(btnTitle);
	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnTitle) {
			do_btnTitle_actionPerformed(e);
		}
		if (e.getSource() == btnDept) {
			do_btnDept_actionPerformed(e);
		}
		if (e.getSource() == btnEmp) {
			do_btnEmp_actionPerformed(e);
		}
	}
	protected void do_btnEmp_actionPerformed(ActionEvent e) {
		if(empUI==null)
			empUI = new EmployeeUI();
		empUI.defaultNoText();
		empUI.setVisible(true);
		
	}
	protected void do_btnDept_actionPerformed(ActionEvent e) {
		if(deptUI==null)
			deptUI = new DepartmentUI();
		deptUI.defaultNoText();
		deptUI.setVisible(true);
	}
	protected void do_btnTitle_actionPerformed(ActionEvent e) {
		if(titleUI==null)
			titleUI = new TitleUI();
		titleUI.defaultNoText();
		titleUI.setVisible(true);
	}
}
