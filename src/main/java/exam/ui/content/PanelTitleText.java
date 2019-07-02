package exam.ui.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exam.dto.Title;
import exam.ui.TitleUI;

@SuppressWarnings("serial")
public class PanelTitleText extends JPanel{
	private JTextField textTno;
	private JTextField textTname;
	private List<Title> titleList;
	
	
	
	public void setTextTno(String value) {
		this.textTno.setText(value);;
	}

	public JTextField getTextTno() {
		return textTno;
	}

	public JTextField getTextTname() {
		return textTname;
	}
	public void setTextTname(String value) {
		textTname.setText(value);
	}
	public void defaultNoText() {
		int number=titleList.get(titleList.size()-1).getTno()+1;
		String no=String.format("T%03d", number);
		textTno.setText(no);
		textTname.setText("");
	}
	public void tnoAble(boolean b) {
		textTno.setEditable(b);
	}
	public PanelTitleText() {
		
		initComponents();
	}
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTno = new JLabel("번호");
		panel_3.add(lblTno);
		
		textTno = new JTextField();
		panel_3.add(textTno);
		textTno.setColumns(10);
		
		JLabel lblTname = new JLabel("직책명");
		panel_3.add(lblTname);
		
		textTname = new JTextField();
		panel_3.add(textTname);
		textTname.setColumns(10);
	}
	
	public void setTitleList(List<Title> titleList) {
		this.titleList=titleList;
	}
	
	
	

}
