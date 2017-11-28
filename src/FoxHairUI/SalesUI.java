package FoxHairUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import FoxHairDB.DBSelect;

public class SalesUI extends JPanel {
		JLabel txtYear,txtMonth,txtDay;
	    String[] colsTransAction= {"ta_num","price","ta_date","type"};
	    String[] strYear= {"2017","2018","2019","2020","2021"};
		JButton bSearch = new JButton("검색");
		JCheckBox cboption;
		DefaultTableModel model = new DefaultTableModel(colsTransAction,0); 
		JTable tbl = new JTable(model);
		JScrollPane scroll = new JScrollPane(tbl);
		JFrame mainFrame = null;
		JComboBox cbYear,cbMonth,cbDay;
		String[] strMonth = new String[12];
		String[] strDay = new String[31];

		public SalesUI(JFrame frame) {
			mainFrame = frame;
			setSize(800,400);
			setLayout(null);
			
			for(int i=0; i < 12; i ++) strMonth[i] = String.valueOf(i+1);
			for(int i=0; i < 31; i++) strDay[i]=String.valueOf(i+1);
			
			cbYear = new JComboBox(strYear);
			cbMonth = new JComboBox(strMonth);
			cbDay = new JComboBox(strDay);
			cboption = new JCheckBox("월별 검색",false);
			txtYear = new JLabel("년");
			txtMonth = new JLabel("월");
			txtDay = new JLabel("일");
			
			txtYear.setForeground(Color.lightGray);
			txtMonth.setForeground(Color.lightGray);
			txtDay.setForeground(Color.lightGray);
			cboption.setForeground(Color.lightGray);
			cboption.setBackground(Color.DARK_GRAY);
			
			
			cbYear.setSize(80,20);
			bSearch.setSize(60,20);
			cbMonth.setSize(50,20);
			cbDay.setSize(50,20);
			scroll.setSize(400,250);
			txtYear.setSize(20,20);
			txtMonth.setSize(20,20);
			txtDay.setSize(20,20);
			cboption.setSize(90,20);
			
			
			
			cbYear.setLocation(60,40);
			cbMonth.setLocation(190,40);
			cbDay.setLocation(290,40);
			bSearch.setLocation(490,40);
			scroll.setLocation(55,80);
			txtYear.setLocation(150,40);
			txtMonth.setLocation(250,40);
			txtDay.setLocation(350,40);
			cboption.setLocation(390,40);
			
			


			tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //테이블 단일 선택 모드 
			tbl.getTableHeader().setReorderingAllowed(false);//테이블 칼럼 순서 이동 불가
			//tbl.addMouseListener(this); //마우스 리스너 등록 
			
			add(cbYear);
			add(cbMonth);
			add(cbDay);
			add(bSearch);
			add(bSearch);
			add(scroll);
			add(txtYear);
			add(txtMonth);
			add(txtDay);
			add(cboption);
			

			
			
			bSearch.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					model.setNumRows(0);
					String date = strYear[cbYear.getSelectedIndex()]+"-"+strMonth[cbMonth.getSelectedIndex()];
					if(!cboption.isSelected())
						date+="-"+strDay[cbDay.getSelectedIndex()];
					else date+="-"+"00";
					
					model = DBSelect.select(Login.c,"trans_action", date, "ta_date",model);
					JTable tbl = new JTable(model);
					scroll = new JScrollPane(tbl);
					
				}
			});
			
			
			
			
			
		}
		
		//두개의 int 값을 입력 받아 현재 선택된 행과 열값을 저장하는 변수를 변경 해주는 함수.
		
	

	}

