package gradle_jdbc_study;

import java.awt.EventQueue;

import gradle_jdbc_study.ui.ErpManagementUI;

public class ErpMain {

	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					System.out.println(System.getProperty("user.dir") + System.getProperty("file.separator"));
//					System.out.println("첫번째 수정");
					ErpManagementUI erpmain=new ErpManagementUI();
					erpmain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
