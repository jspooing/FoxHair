package FoxHairUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.MenuKeyListener;
import javax.swing.table.DefaultTableModel;

import FoxHairDB.DBSelect;


public class SearchCustomer extends JDialog implements MouseListener{
	String[] SearchType = {"고객 번호","이름","연락처","포인트"};
	String[] realType = {"c_num","name","p_num","point"};
	JTextField tfSearch = new JTextField();
	JButton bSearch = new JButton("검색");
	JLabel textSearch = new JLabel("검색 :");
	JComboBox cbSearchType = new JComboBox(SearchType);
	DefaultTableModel model = new DefaultTableModel(SearchType,0);
	JTable tbl = new JTable(model);
	JScrollPane scroll = new JScrollPane(tbl);
	String strSearchType = realType[0];
	int selectedRow,selectedCol;


	public SearchCustomer(JFrame frame, String title) {
		super(frame,title);
		setSize(600,400);
		setLocation(400,200);
		setLayout(null);
		//setVisible(true);

		//사이즈, 위치 정의 
		tfSearch.setSize(100,20);
		bSearch.setSize(60,20);
		textSearch.setSize(50,20);
		cbSearchType.setSize(95,20);
		scroll.setSize(400,250);

		tfSearch.setLocation(130,40);
		bSearch.setLocation(330,40);
		textSearch.setLocation(85,40);
		cbSearchType.setLocation(232,40);
		scroll.setLocation(55,80);

		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //테이블 단일 선택 모드 
		tbl.addMouseListener(this);
		
		add(bSearch);
		add(tfSearch);
		add(bSearch);
		add(textSearch);
		add(cbSearchType);
		add(scroll);

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
				model = DBSelect.select(Login.c, searchText, strSearchType, model);
				JTable tbl = new JTable(model);
				scroll = new JScrollPane(tbl);
				
			}
		});
		
	}

	void setSelected(int r,int c) {
		selectedRow = r;
		selectedCol = c;
	}
	




	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int r = tbl.getSelectedRow();
		int c = tbl.getSelectedColumn();
		setSelected(r,c);
		System.out.println(selectedRow + " "+ selectedCol);
		
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
