package exam.ui.content;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import exam.dto.Title;
import exam.ui.TitleUI;

@SuppressWarnings("serial")
public class PanelTitleList extends JPanel implements ActionListener {
	private JTable table;
	private List<Title> titleList;
	private JScrollPane scrollPane;
	
	private JPopupMenu popupMenu;
	private JMenuItem mntmPopUpdate;
	private JMenuItem mntmPopDelete;
	
	private TitleUI titleUI;
	
	public PanelTitleList() {
		
		initComponents();
	}
	
	public void setTitleUI(TitleUI titleUI) {
		this.titleUI=titleUI;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	

	private void initComponents() {
		popupMenu = new JPopupMenu();
		
		mntmPopUpdate = new JMenuItem("수정");
		mntmPopUpdate.addActionListener(this);
		popupMenu.add(mntmPopUpdate);
		
		mntmPopDelete = new JMenuItem("삭제");
		mntmPopDelete.addActionListener(this);
		popupMenu.add(mntmPopDelete);
		
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setComponentPopupMenu(popupMenu);
		scrollPane.setViewportView(table);
	}
	public void setTitleList(List<Title> titleList) {
		this.titleList=titleList;
	}
	
	public void reloadData() {
		table.setModel(new DefaultTableModel(getRows(),getColumnNames()));
		tableCellAlignment(SwingConstants.CENTER, 0,1);
		// 부서번호, 부서명, 위치 의 폭을 (100, 200, 70)으로 가능하면 설정
		tableSetWidth(200,200);
	}
	private String[] getColumnNames() {
		return new String[] {"번호","직책"};
	}
	private Object[][] getRows() {
		Object[][] rows=new Object[titleList.size()][];
		for(int i=0; i<titleList.size(); i++) {
			rows[i]=titleList.get(i).toArray();
		}
		return rows;
	}
	
	
	// 테이블 셀 내용의 정렬
		protected void tableCellAlignment(int align, int... idx) {
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(align);

			TableColumnModel model = table.getColumnModel();
			for (int i = 0; i < idx.length; i++) {
				model.getColumn(idx[i]).setCellRenderer(dtcr);
			}
		}

		// 테이블 셀의 폭 설정
		protected void tableSetWidth(int... width) {
			TableColumnModel cModel = table.getColumnModel();

			for (int i = 0; i < width.length; i++) {
				cModel.getColumn(i).setPreferredWidth(width[i]);
			}
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			int i = table.getSelectedRow();
			JOptionPane.showMessageDialog(null, "i="+i);
			Title title=titleList.get(i);
			if (e.getSource() == mntmPopUpdate) {
				titleUI.popupUpdate(title);
			}
			if (e.getSource() == mntmPopDelete) {
				
				titleUI.popupDelete(title);
			}
		
		}
}
