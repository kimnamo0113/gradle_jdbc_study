package exam.ui.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import exam.dto.Department;
import exam.dto.Employee;
import exam.dto.Title;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class PanelEmployeeText extends JPanel {
	private JTextField textEno;
	private JTextField textEmpName;
	
	private List<Employee> empList;
	private List<Department> deptList;
	private List<Title> titleList;
	
	private JComboBox<Title> cmbTitle;
	private JComboBox<Department> cmbDept;
	private JComboBox<Employee> cmbMgn;
	
	private DefaultComboBoxModel<Department> deptCmbModel;
	private DefaultComboBoxModel<Employee> mgnCmbModel;
	private DefaultComboBoxModel<Title> titleCmbModel;
	
	
	private SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
	private JSpinner spSalary;
	private JTextField textDate;
	private SpinnerModel numberModel;
	private SimpleDateFormat sf2;
	private JRadioButton rdoMan;
	private JRadioButton rdoGirl;
	private final ButtonGroup selectedGender = new ButtonGroup();
	
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
		
		JLabel lblDept = new JLabel("부서");
		panel_1.add(lblDept);
		
		cmbDept = new JComboBox<Department>();
		panel_1.add(cmbDept);
		
		JLabel lblManager = new JLabel("매니저");
		panel_1.add(lblManager);
		
		cmbMgn = new JComboBox<>();
		panel_1.add(cmbMgn);
		
		JLabel lblSalary = new JLabel("급여");
		panel_1.add(lblSalary);
		
		
		numberModel = new SpinnerNumberModel(1500000, 1000000, 5000000, 100000);
		spSalary=new JSpinner(numberModel);
		panel_1.add(spSalary);
		
		JLabel lblGender = new JLabel("성별");
		panel_1.add(lblGender);
		
		JPanel panelGender = new JPanel();
		panel_1.add(panelGender);
		
		rdoMan = new JRadioButton("남자",true);
		selectedGender.add(rdoMan);
		panelGender.add(rdoMan);
		
		rdoGirl = new JRadioButton("여자");
		selectedGender.add(rdoGirl);
		panelGender.add(rdoGirl);
		
		
		
		JLabel lblDay = new JLabel("입사일");
		panel_1.add(lblDay);
		
		
		textDate = new JTextField();
		textDate.setColumns(10);
		panel_1.add(textDate);
		
		
	}

	public void setting() {
		int num=0;
		for(int i=0; i<empList.size(); i++) {
			if(empList.get(i).getEmpNo()+1!=empList.get(i+1).getEmpNo()) {
				num=empList.get(i).getEmpNo()+1;
				break;
			}
		}
		textEno.setText(num+"");
		textEno.setEditable(false);
		
		DefaultComboBoxModel<Department> newDept = new DefaultComboBoxModel<Department>(new Vector<Department>(deptList));
		cmbDept.setModel(newDept);
		
		DefaultComboBoxModel<Employee> newMgn = new DefaultComboBoxModel<Employee>(new Vector<Employee>(empList));
		cmbMgn.setModel(newMgn);
		
		DefaultComboBoxModel<Title> newTitle = new DefaultComboBoxModel<Title>(new Vector<Title>(titleList));
		cmbTitle.setModel(newTitle);
		
		Date date=new Date();
		
		textDate.setText(sf.format(date));
		
	}
	public void defaultNoText() {
		int num=empList.get(empList.size()-1).getEmpNo()+1;
		for(int i=0; i<empList.size(); i++) {
			if(empList.get(i).getEmpNo()+1!=empList.get(i+1).getEmpNo()) {
				num=empList.get(i).getEmpNo()+1;
				break;
			}
		}
		String no=String.format("E%06d", num);
		textEno.setText(no+"");
		textEmpName.setText("");
		cmbTitle.setSelectedIndex(-1);
		cmbMgn.setSelectedIndex(-1);
		spSalary.setValue(1500000);
		
		rdoMan.setSelected(true);
		cmbDept.setSelectedIndex(-1);
		Date date=new Date();
		sf2 = new SimpleDateFormat("yyyy-MM-dd");
		textDate.setText(sf2.format(date));
	}
	public Employee getEmployee() {
		
		String strEno=textEno.getText().trim();
		strEno=strEno.substring(1, 6);
		
		int eno=Integer.parseInt(strEno);
		String empName=textEmpName.getText().trim();
		Title title=(Title) cmbTitle.getSelectedItem();
		Employee manager=(Employee)cmbMgn.getSelectedItem();
		int salary=(int)spSalary.getValue();
		
		int gender;
		if(rdoMan.isSelected()) {
			gender=1;
		}else {
			gender=0;
		}
		Department dept=(Department)cmbDept.getSelectedItem();
		String date = textDate.getText().trim();
		
		Date day = null;
		try {
			day=sf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Employee(eno,empName,title,manager,salary,gender,dept,day);
	}
	
	
	public void setTextEmployee(Employee emp) {
		String no=String.format("E%06d", emp.getEmpNo()); 
		textEno.setText(no+"");
		textEmpName.setText(emp.getEmpName());
		cmbTitle.setSelectedItem(emp.getTitle());
		cmbMgn.setSelectedItem(emp.getManager());
		spSalary.setValue(emp.getSalary());
		if(emp.getGender()==1) {
			rdoMan.setSelected(true);
		}else {
			rdoGirl.setSelected(true);
		}
		cmbDept.setSelectedItem(emp.getDno());
		new SimpleDateFormat("yyyy-MM-dd");
		textDate.setText(emp.getHire_date()+"");
	}
	
	public JComboBox<Department> getCmbDept() {
		return cmbDept;
	}
	public JComboBox<Employee> getCmbMgn() {
		return cmbMgn;
	}
	public JComboBox<Title> getCmbTitle() {
		return cmbTitle;
	}
	public void setCmbDeptModel(List<Department> deptList) {
		deptCmbModel = new DefaultComboBoxModel<Department>(new Vector<Department>(deptList));
		cmbDept.setModel(deptCmbModel);
	}
	public void setCmbMgnModel(List<Employee> mgnList) {
		mgnCmbModel = new DefaultComboBoxModel<Employee>(new Vector<Employee>(mgnList));
		cmbMgn.setModel(mgnCmbModel);
	}
	public void setTitleCmbModel(List<Employee> empList) {
		titleCmbModel = new DefaultComboBoxModel<Title>(new Vector<Title>(titleList));
		cmbTitle.setModel(titleCmbModel);
	}
}
