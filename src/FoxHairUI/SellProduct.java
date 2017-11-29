package FoxHairUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

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


/*
 * �y�� ���̾�α׿��� ����ϴ� ��ǰ �Ǹ� �г��̴�. 
 * */
public class SellProduct extends JPanel{

	String[] SearchType = {"��ǰ ��ȣ","��ǰ ��","����","����","���� ��ü","��ǰ ����","���"};
	String[] realType = {"p_num","p_name","price","prime_cost","supplier","p_type","stock"};
	String[] colsTransAction= {"ta_num","price","ta_date","type"};
	String[] colsSale = {"ta_num", "c_num", "p_num", "amount" };
	JTextField tfSearch = new JTextField();
	JButton bSearch = new JButton("�˻�");
	JLabel txtQuantity = new JLabel("����");
	JTextField tfQuantity = new JTextField();
	JButton bSell = new JButton("�Ǹ�");
	JLabel textSearch = new JLabel("�˻� :");
	JComboBox cbSearchType = new JComboBox(SearchType);
	DefaultTableModel model = new DefaultTableModel(SearchType,0) {public boolean isCellEditable(int i, int c) {
		return false;}};
	JTable tbl = new JTable(model);
	JScrollPane scroll = new JScrollPane(tbl);
	String strSearchType = realType[0];
	JFrame mainFrame = null;
	int selectedRow,selectedCol;


	public SellProduct(JFrame frame,int c_num) {
		
		setSize(800,400);
		//setLocation(400,200);
		setLayout(null);
		//setVisible(true);
		textSearch.setForeground(Color.lightGray);
		txtQuantity.setForeground(Color.lightGray);

		//������, ��ġ ���� 
		tfSearch.setSize(100,20);
		bSearch.setSize(60,20);
		textSearch.setSize(50,20);
		cbSearchType.setSize(95,20);
		scroll.setSize(400,250);
		txtQuantity.setSize(40,20);
		tfQuantity.setSize(60,20);
		bSell.setSize(60,30);
		
		

		tfSearch.setLocation(130,40);
		bSearch.setLocation(330,40);
		textSearch.setLocation(85,40);
		cbSearchType.setLocation(232,40);
		scroll.setLocation(55,80);
		txtQuantity.setLocation(480,40);
		tfQuantity.setLocation(530,40);
		bSell.setLocation(500,70);


		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //���̺� ���� ���� ��� 
		tbl.getTableHeader().setReorderingAllowed(false);//���̺� Į�� ���� �̵� �Ұ�
		//tbl.addMouseListener(this); //���콺 ������ ��� 
		
		
		add(bSearch);
		add(tfSearch);
		add(bSearch);
		add(textSearch);
		add(cbSearchType);
		add(scroll);
		add(txtQuantity);
		add(tfQuantity);
		add(bSell);
		

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
		
		bSell.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String p_num = tbl.getValueAt(tbl.getSelectedRow(),0).toString();
				String stock = DBGetRows.getRowValue(Login.c, "product", "stock", "p_num", Integer.parseInt(p_num));
				int newstock = Integer.parseInt(stock)-Integer.parseInt(tfQuantity.getText());
				String price = DBGetRows.getRowValue(Login.c, "product", "price", "p_num", Integer.parseInt(p_num));
				long time = System.currentTimeMillis(); 

				SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String strDate = dayTime.format(new Date(time));
				
				int ta_num = DBInsert.getMaxNum(Login.c, "trans_action", "ta_num");
				System.out.println("ta_num :"+ta_num);
				System.out.println(strDate);
				String vTA[] = new String[4];
				vTA[0] = String.valueOf(ta_num+1);
				vTA[1] = String.valueOf(Integer.parseInt(price)*Integer.parseInt(tfQuantity.getText()));
				vTA[2] = strDate;
				vTA[3] = "��ǰ�Ǹ�";
				
				String vSale[] = new String[4];
				vSale[0] = vTA[0];
				vSale[1] = String.valueOf(c_num);
				vSale[2] = p_num;
				vSale[3] = tfQuantity.getText();
				
				if(newstock >=0) {
					DBUpdate.DBupdate(Login.c, "product", "p_num", tbl.getValueAt(tbl.getSelectedRow(),0).toString(), "stock", String.valueOf(newstock));
					DBInsert.DBInsert(Login.c, "trans_action", colsTransAction, vTA);
					DBInsert.DBInsert(Login.c, "sale", colsSale, vSale );
					
					
				}
				JOptionPane.showMessageDialog(null, "�Ǹ� ��� �Ϸ�");
			}
			
		});
		
		
	}
	
	//�ΰ��� int ���� �Է� �޾� ���� ���õ� ��� ������ �����ϴ� ������ ���� ���ִ� �Լ�.
}

