package FoxHairUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import FoxHairDB.DBInsert;



/*
 * 고객 등록 유저 인터페이스 
 * */
public class CustomerSignUpDialog extends JDialog{
	/*
	 * 변수 선언 
	 * */
	JLabel txtname,txtp_num;
	JTextField tfName,tfP_num;
	JButton bConfirm, bCancel;
	String[] realType = {"c_num","name","p_num","point"};

	public CustomerSignUpDialog(JFrame frame,String title) {
		super(frame,title);
		// TODO Auto-generated constructor stub
		setSize(400,150);
		setLocation(800,400);
		setLayout(null);
		
		
		/*
		 * 컴포넌트 객체 생성 
		 * */

		txtname = new JLabel("이름");
		txtp_num = new JLabel("연락처");
		tfName = new JTextField(5);
		tfP_num = new JTextField(11);
		bConfirm = new JButton("등록");
		bCancel = new JButton("취소");
	
		
		/*
		 * 컴포넌트 위치및 크기 조정 
		 * */
		txtname.setSize(55,20);
		txtp_num.setSize(40,20);
		tfName.setSize(80,20);
		tfP_num.setSize(80,20);
		bConfirm.setSize(60,20);
		bCancel.setSize(60,20);
		
		txtname.setLocation(20,10);
		txtp_num.setLocation(180,10);
		tfName.setLocation(80,10);
		tfP_num.setLocation(230,10);
		bConfirm.setLocation(120,60);
		bCancel.setLocation(200,60);
		
		add(txtname);
		add(txtp_num);
		add(tfName);
		add(tfP_num);
		add(bConfirm);
		add(bCancel);
		
		
		/*
		 * 고객 등록 버튼 기능 구현 
		 * */
		bConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String[] v = new String[4];
				v[0] = String.valueOf(DBInsert.getMaxNum(Login.c,"customer","c_num")+1);
				v[1] = tfName.getText();
				v[2] = tfP_num.getText();
				v[3] = "0";
				DBInsert.DBInsert(Login.c, "customer", realType , v );
				JOptionPane.showMessageDialog(null, "고객 등록 완료");
				setVisible(false);
			}
			
		});
		



	}



}
