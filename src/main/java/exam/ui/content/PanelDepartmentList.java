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

import exam.dto.Department;
import exam.ui.DepartmentUI;

@SuppressWarnings("serial")
public class PanelDepartmentList extends JPanel implements ActionListener {
	private JTable table;
	
	private List<Department> deptList=null;
	
	private JPopupMenu popupMenu;
	private JMenuItem mntmPopUpdate;
	private JMenuItem mntmPopDelete;
	
	private DepartmentUI deptUI;
	
	public PanelDepartmentList() {
		
		initComponents();
	}
	
	public void setDeptUI(DepartmentUI deptUI) {
		this.deptUI=deptUI;
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
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setComponentPopupMenu(popupMenu);
		scrollPane.setViewportView(table);
	}
	public void setDeptList(List<Department> deptList) {
		this.deptList=deptList;
		
	}
	
	
	public void reloadData() {
		table.setModel(new DefaultTableModel(getRows(),getColumnNames()));
		
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2);
				
		tableSetWidth(100, 200, 70);
	}
	private Object[][] getRows() {
		Object[][] rows = new Object[deptList.size()][];
		for(int i=0; i<deptList.size(); i++) {
			
			rows[i]=deptList.get(i).toArray();
		}
		return rows;
	}
	private String[] getColumnNames() {
		return new String[] {"번호","부서명","위치"};
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
			Department title=deptList.get(i);
			if (e.getSource() == mntmPopUpdate) {
				deptUI.popupUpdate(title);
			}
			if (e.getSource() == mntmPopDelete) {
				
				deptUI.popupDelete(title);
			}
		}
		
}
