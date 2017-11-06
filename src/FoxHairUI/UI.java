package FoxHairUI;

import javax.swing.*;

import FoxHairDB.DBLogin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UI extends JFrame{
	Container cp;

	public UI() {
		setTitle("FoxHair System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    cp= getContentPane();
		cp.setBackground(Color.DARK_GRAY);
		cp.setLayout(null);
		
		cp.add(new MyPanel());
		
		setSize(700,340);
		setLocation(550,300);
		revalidate();
		setVisible(true);
		//cp.addKeyListener(l);
	
	}
	
	
	
	
	
	public class MyPanel extends JPanel {
		JLabel t,textID,textPw,imageLabel;
		JButton bLogin,bCancel;
		JTextField tfID,tfPw;
		ImageIcon p;
		
		public MyPanel(){
	    t = new JLabel("여우 헤어 갤러리 미용실 관리 프로그램");  
	    t.setForeground(Color.lightGray);
		t.setSize(250,40);
		t.setLocation(410,11);
		
		p = new ImageIcon("F:\\test.png");  //사진 
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
		
		bLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(DBLogin.getConnection(tfID.getText(), tfPw.getText()) != null)
					{
						cp.removeAll();
						cp.repaint();
						cp.add(new LoginPanel());
						
					}
			}
		
		});
		

		
		}
	}
		
	
	
public class LoginPanel extends JPanel{
	JLabel image;
	ImageIcon p;
	LoginPanel(){
		
		p=new ImageIcon("F:\\loginsuccess.png");
		image = new JLabel(p);
		image.setSize(387,324);
		image.setLocation(0,0);
		cp.add(image);
	}
	
}
	
	
	public static void main(String[] args) {

		UI jg = new UI();
	}

}
