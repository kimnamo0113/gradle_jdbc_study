package exam.ui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exam.daoImpl.TitleDaoImpl;
import exam.dto.Department;
import exam.dto.Employee;
import exam.dto.Title;
import exam.ui.content.PanelTitleList;

@SuppressWarnings("serial")
public abstract class AbstractUI<T> extends JFrame {
	private JPanel contentPane;
	private T type;
	private List<T> itemList;
	
	private JPanel panelBtn;
	private JButton btnAdd;
	private JButton btnCancel;
	
	public AbstractUI() {
		if(type instanceof Title) {
			
		}else if(type instanceof Department) {
			
		}else {
			
		}
		initComponents();
	};
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
	}
	
}
