package exam.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exam.dao.TitleDao;
import exam.daoImpl.TitleDaoImpl;
import exam.dto.Title;
import exam.ui.content.PanelTitleList;
import exam.ui.content.PanelTitleText;

@SuppressWarnings("serial")
public class TitleUI extends JFrame implements ActionListener {
	private JPanel contentPane;
	private TitleDao titleDao;
	private List<Title> titleList;
	
	private PanelTitleText panelText;
	private PanelTitleList panelList;
	private JPanel panelBtn;
	private JButton btnAdd;
	private JButton btnCancel;
	
	
	
	public TitleUI() {
		titleDao=new TitleDaoImpl();
		initComponents();
	}
	
	
	
	public TitleDao getTitleDao() {
		return titleDao;
	}

	public void setTitleDao(TitleDao titleDao) {
		this.titleDao = titleDao;
	}

	public List<Title> getTitleList() {
		return titleList;
	}

	public void setTitleList(List<Title> titleList) {
		this.titleList = titleList;
	}



	private void initComponents() {
	
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panelText = new PanelTitleText();
		panelText.setBounds(91, 0, 242, 95);
		
		
		try {
			titleList=titleDao.selectTitleByAll();
			panelText.setTitleList(titleList);
			panelText.defaultNoText();
			panelText.tnoAble(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.setLayout(null);
		
		
		contentPane.add(panelText);
		
		panelBtn = new JPanel();
		panelBtn.setBounds(0, 94, 434, 33);
		contentPane.add(panelBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		panelBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		panelBtn.add(btnCancel);
		panelList = new PanelTitleList();
		panelList.setBounds(0, 133, 434, 128);
		panelList.setTitleList(titleList);
		panelList.reloadData();
		panelList.setTitleUI(this);
		contentPane.add(panelList);
		
		
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
		String strTno=panelText.getTextTno().getText();
		strTno=strTno.substring(1, 4);
		System.out.println(strTno);
		int tno=Integer.parseInt(strTno);
		System.out.println(tno);
		String tname=panelText.getTextTname().getText();
		Title title=new Title(tno,tname);
		try {
			int res=titleDao.updateTitle(title);
			if(res>-1) {
				JOptionPane.showMessageDialog(null, "수정완료");
				titleList=titleDao.selectTitleByAll();
				panelList.setTitleList(titleList);
				panelList.reloadData();
			}
				
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		panelText.setTitleList(titleList);
		panelText.defaultNoText();
		btnAdd.setText("추가");
	}



	protected void actionPerformedBtnCancel(ActionEvent e) {
		defaultNoText();
		panelText.setTextTname("");
		btnAdd.setText("추가");
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		String tnostr=panelText.getTextTno().getText();
		tnostr=tnostr.substring(1, 4);
		int tno=Integer.parseInt(tnostr);
		String tname=panelText.getTextTname().getText();
		
		
		
		Title title=new Title(tno,tname);
		try {
			titleDao.insertTitle(title);
			JOptionPane.showMessageDialog(null, "직책 추가 성공");
			titleList=titleDao.selectTitleByAll();
			panelList.setTitleList(titleList);
			panelText.setTitleList(titleList);
			defaultNoText();
			panelList.reloadData();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}



	public void popupDelete(Title title) {
		try {
			titleDao.deleteTitle(title);
			titleList=titleDao.selectTitleByAll();
			panelList.setTitleList(titleList);
			panelText.setTitleList(titleList);
			defaultNoText();
			panelList.reloadData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	public void popupUpdate(Title title) {
		String strTno=String.format("T%03d", title.getTno());
		panelText.setTextTname(title.getTname());
		panelText.setTextTno(strTno);
		btnAdd.setText("수정");
	}



	public void defaultNoText() {
		panelText.defaultNoText();
	}
	
	
}























