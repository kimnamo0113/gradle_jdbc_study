package exam.ui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exam.dao.DepartmentDao;
import exam.daoImpl.DepartmentDaoImpl;
import exam.dto.Department;
import exam.dto.Title;
import exam.ui.content.PanelDepartmentList;
import exam.ui.content.PanelDepartmentText;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DepartmentUI extends JFrame implements ActionListener {
	private JPanel contentPane;
	
	private DepartmentDao deptDao;
	private PanelDepartmentList panelList;
	private PanelDepartmentText panelText;

	private List<Department> deptList=null;
	private JPanel panel;
	private JButton btnAdd;
	private JButton btnCancel;

	
	
	public DepartmentUI() {
		deptDao=new DepartmentDaoImpl();
		initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelText = new PanelDepartmentText();
		panelText.setBounds(5, 5, 424, 79);
		contentPane.add(panelText);
		
		panelList = new PanelDepartmentList();
		panelList.setBounds(5, 115, 424, 140);
		
		deptList=deptDao.selectDepartmentByAll();
		panelList.setDeptList(deptList);
		panelList.reloadData();
		panelList.setDeptUI(this);
		panelText.setDeptList(deptList);
		panelText.setting();
		panelText.setDeptNoAble(false);
		
		
		contentPane.add(panelList);
		
		
		
		panel = new JPanel();
		panel.setBounds(5, 83, 434, 33);
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
		String strTno=panelText.getTextDno().getText();
		strTno=strTno.substring(1, 4);
		int dno=Integer.parseInt(strTno);
		String dname=panelText.getTextDeptName().getText();
		int floor=Integer.parseInt(panelText.getTextFloor().getText());
		Department dept=new Department(dno,dname,floor);
		try {
			int res=deptDao.updateDepartment(dept);
			if(res>-1) {
				JOptionPane.showMessageDialog(null, "수정완료");
				deptList=deptDao.selectDepartmentByAll();
				panelList.setDeptList(deptList);
				panelList.reloadData();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		panelText.setDeptList(deptList);
		panelText.defaultNoText();
		
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		panelText.defaultNoText();
		panelText.setTextDeptName("");
		btnAdd.setText("추가");
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		String dnostr=panelText.getTextDno().getText();
		dnostr=dnostr.substring(1, 4);
		int dno=Integer.parseInt(dnostr);
		String tname=panelText.getTextDeptName().getText();
		int floor = Integer.parseInt(panelText.getTextFloor().getText());
		
		
		Department dept=new Department(dno,tname,floor);
		try {
			deptDao.insertDepartment(dept);
			JOptionPane.showMessageDialog(null, "직책 추가 성공");
			deptList=deptDao.selectDepartmentByAll();
			panelList.setDeptList(deptList);
			panelText.setDeptList(deptList);
			panelText.defaultNoText();
			panelList.reloadData();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void popupUpdate(Department dept) {
		String strTno=String.format("T%03d", dept.getDeptNo());
		panelText.setTextDeptName(dept.getDeptName());
		panelText.setTextDno(strTno);
		panelText.setTextFloor(dept.getFloor()+"");
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
