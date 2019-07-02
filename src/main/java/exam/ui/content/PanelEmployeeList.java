package exam.ui.content;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import exam.dto.Employee;

@SuppressWarnings("serial")
public class PanelEmployeeList extends JPanel implements ActionListener {
	private JTable table;
	private List<Employee> empList;

	private JPopupMenu popupMenu;
	private JMenuItem mntmPopUpdate;
	private JMenuItem mntmPopDelete;
	
	public PanelEmployeeList() {
popupMenu = new JPopupMenu();
		mntmPopUpdate = new JMenuItem("수정");
		mntmPopUpdate.addActionListener(this);
		popupMenu.add(mntmPopUpdate);
		
		mntmPopDelete = new JMenuItem("삭제");
		mntmPopDelete.addActionListener(this);
		popupMenu.add(mntmPopDelete);
		
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public Object[][] getRows() {
		Object[][] rows = new Object[empList.size()][];
		for (int i = 0; i < empList.size(); i++) {
			rows[i] = empList.get(i).toArray();
		}
		return rows;
	}

	public String[] getColumnNames() {
		return new String[] { "사원번호", "사원명", "직책", "급여", "성별", "부서","입사일"};
	}

	public void reloadData() {
		table.setModel(new DefaultTableModel(getRows(), getColumnNames()));

		// 사원번호, 사원명, 직책, 직속상사, 부서정보 은 가운데 정렬
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2, 3, 5);
		// 급여은 우측 정렬
		tableCellAlignment(SwingConstants.RIGHT, 4);
		// 부서번호, 부서명, 위치 의 폭을 (100, 200, 70)으로 가능하면 설정
		tableSetWidth(70, 100, 70, 100, 120, 100);
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
		// TODO Auto-generated method stub
		
	}

}
