package FoxHairUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import FoxHairDB.DBDelete;
import FoxHairDB.DBGetRows;
import FoxHairDB.DBSelect;



/*
 * ���� ��� 
 * */
public class ReserveUI extends JPanel{


	String[] SearchType = {"���� ��ȣ","��¥","�̸�","���� �ü�","�� ����ó"};
	String[] realType = {"r_num","r_date","name","styling","p_num"};
	JTextField tfSearch = new JTextField();
	JButton bSearch = new JButton("�˻�");
	JButton bDelete = new JButton("���� ���");
	JLabel textSearch = new JLabel("�˻� :");
	JComboBox cbSearchType = new JComboBox(SearchType);
	DefaultTableModel model = new DefaultTableModel(SearchType,0) {public boolean isCellEditable(int i, int c) {
		return false;}};
		JTable tbl = new JTable(model);
		JScrollPane scroll = new JScrollPane(tbl);
		String strSearchType = realType[0];
		JFrame mainFrame = null;
		int selectedRow,selectedCol;


		public ReserveUI(JFrame frame){
			mainFrame = frame;
			setSize(800,400);
			setLayout(null);
			textSearch.setForeground(Color.lightGray);

			//������, ��ġ ���� 
			tfSearch.setSize(100,20);
			bSearch.setSize(60,20);
			textSearch.setSize(50,20);
			cbSearchType.setSize(95,20);
			scroll.setSize(460,250);
			bDelete.setSize(100,20);

			tfSearch.setLocation(130,40);
			bSearch.setLocation(330,40);
			textSearch.setLocation(85,40);
			cbSearchType.setLocation(232,40);
			scroll.setLocation(55,80);
			bDelete.setLocation(550,100);


			tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //���̺� ���� ���� ��� 
			tbl.getTableHeader().setReorderingAllowed(false);//���̺� Į�� ���� �̵� �Ұ�
			//tbl.addMouseListener(this); //���콺 ������ ��� 


			add(bSearch);
			add(tfSearch);
			add(bSearch);
			add(textSearch);
			add(cbSearchType);
			add(scroll);
			add(bDelete);


			cbSearchType.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JComboBox cb = (JComboBox)e.getSource();
					int index= cb.getSelectedIndex();
					strSearchType = realType[index];
				}
			});

			bSearch.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					String searchText;
					searchText = tfSearch.getText();
					model.setNumRows(0);
					model = DBSelect.selectJoin(Login.c, "reserve", "customer", "c_num", realType, searchText, 
							                   strSearchType, model);
					JTable tbl = new JTable(model);
					scroll = new JScrollPane(tbl);

				}
			});
			
			bDelete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					DBDelete.DBDelete(Login.c, "reserve", "r_num", String.valueOf(tbl.getValueAt(selectedRow,0)));
					JOptionPane.showMessageDialog(null, "���� ��� �Ϸ�");
				}
			});


		}

		//�ΰ��� int ���� �Է� �޾� ���� ���õ� ��� ������ �����ϴ� ������ ���� ���ִ� �Լ�.
	
}


