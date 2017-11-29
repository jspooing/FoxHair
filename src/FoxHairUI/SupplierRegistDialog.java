package FoxHairUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import FoxHairDB.DBInsert;


/*
 * ��ü ��� ���̾�α� 
 * */
public class SupplierRegistDialog extends JDialog{
	String[] Cols= {"sp_name","ic_person","icp_number"};
	JLabel txtName,txtIcName,txtIcNum;
	JTextField tfName,tfIcName,tfIcNum;
	JButton bConfirm;
	
	public SupplierRegistDialog(JFrame frame, String title) {
		super(frame,title);
		setSize(640,100);
		setLocation(800,400);
		setVisible(true);
		setLayout(null);
		
		txtName = new JLabel("��ü ��");
		txtIcName = new JLabel("�����");
		txtIcNum = new JLabel("����ó" );
		tfName = new JTextField();
		tfIcName = new JTextField();
		tfIcNum = new JTextField();
		bConfirm = new JButton("���");
		
		txtName.setSize(50,20);
		txtIcName.setSize(50,20);
		txtIcNum.setSize(50,20);
		tfName.setSize(70,20);
		tfIcName.setSize(70,20);
		tfIcNum.setSize(70,20);
		bConfirm.setSize(80,20);
		
		txtName.setLocation(20,20);
		tfName.setLocation(90,20);
		txtIcName.setLocation(190,20);
		tfIcName.setLocation(260,20);
		txtIcNum.setLocation(350,20);
		tfIcNum.setLocation(420,20);
		bConfirm.setLocation(530,20);
		
		
		add(txtName);
		add(txtIcNum);
		add(txtIcName);
		add(tfName);
		add(tfIcNum);
		add(tfIcName);
		add(bConfirm);
		
		bConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String v[] = new String[3];
				v[0] = tfName.getText();
				v[1] = tfIcName.getText();
				v[2] = tfIcNum.getText();
				DBInsert.DBInsert(Login.c, "supplier", Cols, v);
				JOptionPane.showMessageDialog(null, "��ü ��� �Ϸ�");
			}
			
		});
		

	}
	
	
}
