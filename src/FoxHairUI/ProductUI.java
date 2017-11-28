package FoxHairUI;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import FoxHairDB.DBDelete;
import FoxHairDB.DBSelect;
import FoxHairDB.DBUpdate;

public class ProductUI extends JPanel{
	
		String[] SearchType = {"상품 번호","제품 명","가격","원가","공급 업체","상품 유형","재고"};
		String[] realType = {"p_num","p_name","price","prime_cost","supplier","p_type","stock"};
		JTextField tfSearch = new JTextField();
		JButton bSearch = new JButton("검색");
		JButton bRegist = new JButton("상품 등록");
		JButton bOrder = new JButton("상품 주문");
		JButton bRgSply = new JButton("업체 등록");
		JLabel textSearch = new JLabel("검색 :");
		JComboBox cbSearchType = new JComboBox(SearchType);
		DefaultTableModel model = new DefaultTableModel(SearchType,0) {public boolean isCellEditable(int i, int c) {
			return false;}};
		JTable tbl = new JTable(model);
		JScrollPane scroll = new JScrollPane(tbl);
		String strSearchType = realType[0];
		JFrame mainFrame = null;
		int selectedRow,selectedCol;


		public ProductUI(JFrame frame) {
			mainFrame = frame;
			setSize(800,400);
			//setLocation(400,200);
			setLayout(null);
			//setVisible(true);
			textSearch.setForeground(Color.lightGray);

			//사이즈, 위치 정의 
			tfSearch.setSize(100,20);
			bSearch.setSize(60,20);
			textSearch.setSize(50,20);
			bOrder.setSize(100,20);
			bRegist.setSize(100,20);
			cbSearchType.setSize(95,20);
			scroll.setSize(400,250);
			bRgSply.setSize(100,20);
			

			tfSearch.setLocation(130,40);
			bSearch.setLocation(330,40);
			textSearch.setLocation(85,40);
			bOrder.setLocation(480,100);
			bRegist.setLocation(480,140);
			bRgSply.setLocation(480,180);
			cbSearchType.setLocation(232,40);
			scroll.setLocation(55,80);


			tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //테이블 단일 선택 모드 
			tbl.getTableHeader().setReorderingAllowed(false);//테이블 칼럼 순서 이동 불가
			//tbl.addMouseListener(this); //마우스 리스너 등록 
			
			
			add(bSearch);
			add(tfSearch);
			add(bSearch);
			add(textSearch);
			add(cbSearchType);
			add(scroll);
			add(bOrder);
			add(bRegist);
			add(bRgSply);
			

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
					model = DBSelect.select(Login.c, "product",searchText, strSearchType,model);
					JTable tbl = new JTable(model);
					scroll = new JScrollPane(tbl);
					
				}
			});
			
			bRegist.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					new ProductRegistDialog(frame,"상품 등록");
				}
			});
			
			bOrder.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					int p_num = Integer.parseInt(tbl.getValueAt(tbl.getSelectedRow(), 0).toString());
					new ProductOrderDialog(frame,"상품 주문",p_num);
					
					
				}
			});
			
			bRgSply.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new SupplierRegistDialog(frame,"업체 등록");
				}
				
			});
			
			
			
			
		}
		
		//두개의 int 값을 입력 받아 현재 선택된 행과 열값을 저장하는 변수를 변경 해주는 함수.
		void setSelected(int r,int c) {
			selectedRow = r;
			selectedCol = c;
		}
	}
