package FoxHairUI;

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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import FoxHairDB.DBSelect;

public class StaffUI extends JPanel{
	String[] SearchType = {"주민번호","이름","연락처","월급 계좌","생일","월급"};
	String[] realType = {"SSN","name","p_num","account","b_day","salary"};
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
	int selectedRow,selectedCol;


	public StaffUI(JFrame frame) {
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
		
		cbSearchType.setSize(95,20);
		scroll.setSize(400,250);
		

		tfSearch.setLocation(130,40);
		bSearch.setLocation(330,40);
		textSearch.setLocation(85,40);
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
		
		
		
		
		
	}
	
	//두개의 int 값을 입력 받아 현재 선택된 행과 열값을 저장하는 변수를 변경 해주는 함수.
	void setSelected(int r,int c) {
		selectedRow = r;
		selectedCol = c;
	}
}


