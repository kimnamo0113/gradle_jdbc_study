package exam.ui.content;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import exam.dto.Employee;

public class PanelEmployeeList extends JPanel {
	private JTable table;
	private List<Employee> empList;

	public PanelEmployeeList() {
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
		return new String[] { "사원번호", "사원명", "직책", "직속상사", "급여", "부서" };
	}

	public void reloadData() {
		table.setModel(new DefaultTableModel(getRows(), getColumnNames()));
		revalidate();
		repaint();

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

}
