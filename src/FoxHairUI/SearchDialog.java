package FoxHairUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import FoxHairDB.DBSelect;


public class SearchDialog extends JDialog{
	String[] SearchType = {"학번","이름","학과","연락처"};
	String[] realType = {"ID","Name","DEPARTMENT","PH_NUM"};
	JTextField tfSearch = new JTextField();
	JButton bSearch = new JButton("검색");
	JLabel textSearch = new JLabel("검색 :");
	JComboBox cbSearchType = new JComboBox(SearchType);
	DefaultTableModel model = new DefaultTableModel(SearchType,0);
	JTable tbl = new JTable(model);
	JScrollPane scroll = new JScrollPane(tbl);
	String strSearchType = realType[0];


	public SearchDialog(JFrame frame, String title) {
		super(frame,title);
		setSize(530,400);
		setLocation(400,200);
		setLayout(null);
		//setVisible(true);

		//사이즈, 위치 정의 
		tfSearch.setSize(100,20);
		bSearch.setSize(60,20);
		textSearch.setSize(50,20);
		cbSearchType.setSize(65,20);
		scroll.setSize(400,250);

		tfSearch.setLocation(130,40);
		bSearch.setLocation(300,40);
		textSearch.setLocation(85,40);
		cbSearchType.setLocation(232,40);
		scroll.setLocation(55,80);

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
				model = DBSelect.select(UI.c, searchText, strSearchType, model);
				JTable tbl = new JTable(model);
				scroll = new JScrollPane(tbl);
				
			}
		});
	}
}
