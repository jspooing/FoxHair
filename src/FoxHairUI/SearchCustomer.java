package FoxHairUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import FoxHairDB.DBDelete;
import FoxHairDB.DBInsert;
import FoxHairDB.DBSelect;
import FoxHairDB.DBUpdate;


public class SearchCustomer extends JPanel implements MouseListener{
	String[] SearchType = {"고객 번호","이름","연락처","포인트"}; 
	String[] realType = {"c_num","name","p_num","point"};
	JTextField tfSearch = new JTextField();
	JTextArea taMemo = new JTextArea(20,3);
	JButton bSearch = new JButton("검색");
	JButton bModifyMemo = new JButton("메모 등록");
	JButton bReserve = new JButton("예약");
	JButton bSell = new JButton("판매");
	JButton bSignup = new JButton("고객 등록");
	JButton bDelete = new JButton("삭제");
	JLabel textSearch = new JLabel("검색 :");
	JLabel textMemo = new JLabel("고객 메모");
	JComboBox cbSearchType = new JComboBox(SearchType);
	DefaultTableModel model = new DefaultTableModel(SearchType,0) {public boolean isCellEditable(int i, int c) {
		return false;}};
		JTable tbl = new JTable(model);
		JScrollPane scroll = new JScrollPane(tbl);
		String strSearchType = realType[0];
		JFrame mainFrame = null;
		int selectedRow,selectedCol;


		public SearchCustomer(JFrame frame) {
			mainFrame = frame;
			setSize(800,400);
			//setLocation(400,200);
			setLayout(null);
			//setVisible(true);

			textSearch.setForeground(Color.lightGray);
			textMemo.setForeground(Color.lightGray);

			//사이즈, 위치 정의 
			tfSearch.setSize(100,20);
			bSearch.setSize(60,20);
			textSearch.setSize(50,20);
			textMemo.setSize(60,20);
			cbSearchType.setSize(95,20);
			scroll.setSize(400,250);
			taMemo.setSize(180,150);
			bModifyMemo.setSize(90,20);
			bReserve.setSize(90,20);
			bSell.setSize(90,20);
			bSignup.setSize(90,20);
			bDelete.setSize(90,20);

			tfSearch.setLocation(130,40);
			bSearch.setLocation(330,40);
			textSearch.setLocation(85,40);
			textMemo.setLocation(480,80);
			cbSearchType.setLocation(232,40);
			scroll.setLocation(55,80);
			taMemo.setLocation(480,100);
			bModifyMemo.setLocation(670,100);
			bReserve.setLocation(670,130);
			bSell.setLocation(670,160);
			bSignup.setLocation(670,190);
			bDelete.setLocation(670,220);

			tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //테이블 단일 선택 모드 
			tbl.getTableHeader().setReorderingAllowed(false);//테이블 칼럼 순서 이동 불가
			tbl.addMouseListener(this); //마우스 리스너 등록 


			add(bSearch);
			add(tfSearch);
			add(bSearch);
			add(textSearch);
			add(textMemo);
			add(cbSearchType);
			add(scroll);
			add(taMemo);
			add(bModifyMemo);
			add(bReserve);
			add(bSell);
			add(bSignup);
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
					model = DBSelect.select(Login.c,"customer", searchText, strSearchType,model);
					JTable tbl = new JTable(model);
					scroll = new JScrollPane(tbl);

				}
			});

			bModifyMemo.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String modifiedMemo = taMemo.getText();
					DBUpdate.DBupdate(Login.c, "customer", "c_num", String.valueOf(tbl.getValueAt(selectedRow, 0)), "memo", modifiedMemo);
					JOptionPane.showMessageDialog(null, "메모 등록 완료");
				}

			});

			bSignup.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//				String[] t = {"c_num","name","p_num","point"};
					//				String[] v = {"23","김테스터","01022341022","0"};
					//				DBInsert.DBInsert(Login.c, "customer", t, v);
					CustomerSignUpDialog dSignUp = new CustomerSignUpDialog(mainFrame,"고객등록");
					dSignUp.setVisible(true);

				}
			});

			bDelete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					DBDelete.DBDelete(Login.c, "customer", "c_num", String.valueOf(tbl.getValueAt(selectedRow,0)));
					JOptionPane.showMessageDialog(null, "삭제 완료");

				}
			});

			bReserve.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new CustomerReserveDialog(frame,"예약 등록",Integer.parseInt(tbl.getValueAt(selectedRow, 0).toString()));
				} 

			});

			bSell.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					new SellDialog(frame,"상품 판매",Integer.parseInt(String.valueOf(tbl.getValueAt(selectedRow,0))));

				}

			});

		}

		//두개의 int 값을 입력 받아 현재 선택된 행과 열값을 저장하는 변수를 변경 해주는 함수.
		void setSelected(int r,int c) {
			selectedRow = r;
			selectedCol = c;
		}



		/*
	테이블에서 마우스 클릭시 클릭한 행과 열 값을 저장 하는 마우스 클릭 이벤트 처리 
		 */
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			String memo = "m";
			int r = tbl.getSelectedRow();
			int c = tbl.getSelectedColumn();
			setSelected(r,c);
			System.out.println(r+"행"+ c+"열 선택 함 값 :" + tbl.getValueAt(r,0) );
			String c_num = String.valueOf(tbl.getValueAt(r,0));
			System.out.println("C_NUM = "+c_num);
			memo = DBSelect.selectCol(Login.c,"c_num",c_num,"memo");
			taMemo.setText(memo);

		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

}
