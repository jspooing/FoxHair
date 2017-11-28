package FoxHairUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

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

import FoxHairDB.DBGetRows;
import FoxHairDB.DBInsert;
import FoxHairDB.DBSelect;
import FoxHairDB.DBUpdate;

public class SellStyling extends JPanel{
	String[] SearchType = {"주민번호","이름","연락처","월급 계좌","생일","월급"};
	String[] realType = {"SSN","name","p_num","account","b_day","salary"};
	String[] colsTransAction= {"ta_num","price","ta_date","type"};
	String[] colsStyling = {"ta_num","styling","ssn","c_num"};
	JTextField tfSearch = new JTextField();
	JButton bSearch = new JButton("검색");
	JLabel textSearch = new JLabel("검색 :");
	JComboBox cbSearchType = new JComboBox(SearchType);
	DefaultTableModel model = new DefaultTableModel(SearchType,0) {public boolean isCellEditable(int i, int c) {
		return false;}};
		JTable tbl = new JTable(model);
		JScrollPane scroll = new JScrollPane(tbl);
		String strSearchType = realType[0];
		JFrame mainFrame = null;

		Vector<String> vMenu = null;
		JComboBox cbMenu;
		JButton bRegist = new JButton("확인");

		public SellStyling(JFrame frame,int c_num) {
			setSize(800,400);
			setLayout(null);
			vMenu = DBGetRows.getRows(Login.c, "menu", "styling");

			textSearch.setForeground(Color.lightGray);
			
			cbMenu = new JComboBox(vMenu);
			
			//사이즈, 위치 정의 
			tfSearch.setSize(100,20);
			bSearch.setSize(60,20);
			textSearch.setSize(50,20);
			cbSearchType.setSize(95,20);
			scroll.setSize(400,250);
			cbMenu.setSize(80,20);
			bRegist.setSize(60,20);

			tfSearch.setLocation(130,40);
			bSearch.setLocation(330,40);
			textSearch.setLocation(85,40);
			cbSearchType.setLocation(232,40);
			scroll.setLocation(55,80);
			cbMenu.setLocation(480,40);
			bRegist.setLocation(490,70);
			


			tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //테이블 단일 선택 모드 
			tbl.getTableHeader().setReorderingAllowed(false);//테이블 칼럼 순서 이동 불가


			add(bSearch);
			add(tfSearch);
			add(bSearch);
			add(textSearch);
			add(cbSearchType);
			add(scroll);
			add(cbMenu);
			add(bRegist);


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
					model = DBSelect.select(Login.c, "staff",searchText, strSearchType,model);
					JTable tbl = new JTable(model);
					scroll = new JScrollPane(tbl);

				}
			});
			
			bRegist.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					String price = DBGetRows.getRowValue(Login.c, "menu", "price", "styling", vMenu.get(cbMenu.getSelectedIndex()));
					
					long time = System.currentTimeMillis(); 
					SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String strDate = dayTime.format(new Date(time));
					
					int ta_num = DBInsert.getMaxNum(Login.c, "trans_action", "ta_num");
					
					String vTA[] = new String[4];
					vTA[0] = String.valueOf(ta_num+1);
					vTA[1] = price;
					vTA[2] = strDate;
					vTA[3] = "시술";
					
					String vStyling[] = new String[4];
					vStyling[0] = vTA[0];
					vStyling[1] = vMenu.get(cbMenu.getSelectedIndex());
					vStyling[2] = tbl.getValueAt(tbl.getSelectedRow(),0).toString();
					vStyling[3] = String.valueOf(c_num);
					
					
					DBInsert.DBInsert(Login.c, "trans_action", colsTransAction, vTA);
					DBInsert.DBInsert(Login.c, "styling", colsStyling, vStyling );
						
					JOptionPane.showMessageDialog(null, "판매 등록 완료");
					
				}
				
			});





		}


}

