package FoxHairUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import FoxHairDB.DBGetRows;
import FoxHairDB.DBInsert;



/*
 * �� ���� ��� ���� �������̽� 
 * */
public class CustomerReserveDialog extends JDialog{
	/*
	 * ���� ���� 
	 * */
	String[] reserveCols = {"r_num", "r_date", "styling", "c_num"};
	String[] strYear= {"2017","2018","2019","2020","2021"};
	String[] strMonth = new String[12];
	String[] strDay = new String[31];
	String[] strTime = new String[23];
	Vector<String> vMenu = null;
	JComboBox cbYear,cbMonth,cbDay,cbTime, cbMenu;
	JLabel txtYear,txtMonth,txtDay;
	JButton bConfirm;
	
	int c_num;
	
	public CustomerReserveDialog(JFrame frame,String title, int c_num) {
		super(frame,title);
		// TODO Auto-generated constructor stub
		setSize(630,100);
		setLocation(800,400);
		setLayout(null);
		
		/*
		 * �ʱ�ȭ �۾� 
		 * */
		vMenu = DBGetRows.getRows(Login.c, "menu", "styling");
		
		this.c_num = c_num;
	
		/*
		 * ������Ʈ ��ü ���� 
		 * */
		for(int i=0; i < 12; i ++) strMonth[i] = String.valueOf(i+1);
		for(int i=0; i < 31; i++) strDay[i]=String.valueOf(i+1);
		for(int i=0;  i<23; i++) {
			int time;
			String strMin;
			time = 8+i/2;
			if(i%2==0)strMin = "00";
			else strMin = "30";
			strTime[i] = String.valueOf(time) +":"+strMin;
		}
		
		cbYear = new JComboBox(strYear);
		cbMonth = new JComboBox(strMonth);
		cbDay = new JComboBox(strDay);
		cbTime = new JComboBox(strTime);
		bConfirm = new JButton("���");
		cbMenu = new JComboBox(vMenu);
		txtYear = new JLabel("��");
		txtMonth = new JLabel("��");
		txtDay = new JLabel("��");
		
		/*
		 * �� ������Ʈ�� ��ġ�� ũ�� ���� 
		 * */
		cbYear.setSize(80,20);
		txtYear.setSize(20, 20);
		cbMonth.setSize(50,20);
		txtMonth.setSize(20,20);
		cbDay.setSize(50,20);
		txtDay.setSize(20,20);
		cbTime.setSize(60,20);
		bConfirm.setSize(60,20);
		cbMenu.setSize(80,20);
		
		cbYear.setLocation(20,20);
		txtYear.setLocation(110,20);
		cbMonth.setLocation(150,20);
		txtMonth.setLocation(210,20);
		cbDay.setLocation(250,20);
		txtDay.setLocation(310,20);
		cbTime.setLocation(360,20);
		cbMenu.setLocation(440, 20);
		bConfirm.setLocation(540,20);
		
		add(cbYear);
		add(cbMonth);
		add(cbDay);
		add(cbTime);
		add(cbMenu);
		add(bConfirm);
		add(txtYear);
		add(txtMonth);
		add(txtDay);
		
		setVisible(true);

		
		/*
		 * ���� Ȯ�� ��ư ��� ���� 
		 * */
		bConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String v[] = new String[4];
				String date;
				int sYear=cbYear.getSelectedIndex();
				int sMonth = cbMonth.getSelectedIndex();
				int sDay = cbDay.getSelectedIndex();
				int sTime = cbTime.getSelectedIndex();
				int sMenu = cbMenu.getSelectedIndex();
				date = strYear[sYear]+"-";
				date +=strMonth[sMonth]+"-";
				date +=strDay[sDay]+" ";
				date +=strTime[sTime]+":00";
				
				int maxR = DBInsert.getMaxNum(Login.c, "reserve", "r_num");
				v[0] = String.valueOf(maxR+1);
				v[1] = date;
				v[2] = vMenu.get(sMenu);
				v[3] = String.valueOf(c_num);
				
				
				DBInsert.DBInsert(Login.c, "reserve", reserveCols, v);
				JOptionPane.showMessageDialog(null, "���� ��� �Ϸ�");
			}
			
		});
			
		
}}
