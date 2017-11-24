package FoxHairUI;

import javax.swing.*;

import FoxHairDB.DBLogin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;


public class Login extends JFrame{
	JLabel t,textID,textPw,imageLabel;
	JButton bLogin,bCancel;
	JTextField tfID,tfPw;
	ImageIcon p;
	Container cp;
	JButton bSearch;
	SearchCustomer dSearch = new SearchCustomer(this,"Search");
	static Connection c = null;
	
	public Login() {
		//프레임 기본 설정 
		setTitle("FoxHair System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cp= getContentPane();
		cp.setBackground(Color.DARK_GRAY);
		cp.setLayout(null);
		setSize(700,340);
		setLocation(550,300);
		revalidate();
		setVisible(true);


		t = new JLabel("여우 헤어 갤러리 미용실 관리 프로그램");  
		t.setForeground(Color.lightGray);
		t.setSize(250,40);
		t.setLocation(410,11);

		p = new ImageIcon("E:\\foxhair.jpg");  //사진 
		cp.add(t);
		imageLabel = new JLabel(p);
		imageLabel.setSize(393,316);
		imageLabel.setLocation(0,0);
		cp.add(imageLabel);

		textID = new JLabel("아이디 : ");  //로그인 정보 
		textID.setForeground(Color.lightGray);
		textID.setSize(120,30);
		textID.setLocation(440,90);
		cp.add(textID);

		textPw= new JLabel("비밀번호  : ");
		textPw.setForeground(Color.lightGray);
		textPw.setSize(120,30);
		textPw.setLocation(435,130);
		cp.add(textPw);

		tfID = new JTextField("");
		tfPw = new JTextField("");
		tfID.setSize(120,20);
		tfPw.setSize(120,20);
		tfID.setLocation(510,93);
		tfPw.setLocation(510,133);
		cp.add(tfID);
		cp.add(tfPw);

		bLogin = new JButton("로그인");
		bCancel = new JButton("나가기");
		bLogin.setSize(80,23);
		bCancel.setSize(80,23);
		bLogin.setLocation(450,200);
		bCancel.setLocation(550,200);
		cp.add(bLogin);
		cp.add(bCancel);

		bSearch = new JButton("Search");
		bSearch.setSize(80,23);
		bSearch.setLocation(153,350);

		bLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				c=DBLogin.getConnection(tfID.getText(), tfPw.getText()) ;
				if(c != null)
				{
					cp.removeAll();
					cp.repaint();
					setSize(387,450);
					p=new ImageIcon("F:\\loginsuccess.png");
					imageLabel = new JLabel(p);
					imageLabel.setSize(387,324);
					imageLabel.setLocation(0,0);
					cp.add(imageLabel);
					cp.add(bSearch);
					cp.repaint();


				}
			}
		});

		bSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dSearch.setVisible(true);

			}});

		cp.repaint();

	}





	public static void main(String[] args){
		Login jg = new Login();
		//UI jg = new UI();
		//SearchDialog jj = new SearchDialog(jg,"22");
	}
}