package FoxHairUI;

import javax.swing.*;

import FoxHairDB.DBLogin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;




/*
 * �α��� �������̽� 
 * */
public class Login extends JFrame{
	JLabel t,textID,textPw,imageLabel;
	JButton bLogin,bCancel;
	JTextField tfID;
	JPasswordField tfPw;
	ImageIcon p;
	Container cp;
	JPanel mi = new MainImage();
	MainUI mainUI = new MainUI(this,mi);
	static Connection c = null;	  // db�� ������ ���� ��ü�̸� �ٸ� ��� ���� �������̽����� ����ϹǷ� static ���� �Ͽ���. 
	
	public Login() {
		/*
		 * â �ʱ�ȭ �۾� 
		 * */
		setTitle("FoxHair System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cp= getContentPane();
		cp.setBackground(Color.DARK_GRAY);
		cp.setLayout(null);
		setSize(700,340);
		setLocation(550,300);
		revalidate();
		setVisible(true);


		t = new JLabel("���� ��� ������ �̿�� ���� ���α׷�");  
		t.setForeground(Color.lightGray);
		t.setSize(250,40);
		t.setLocation(410,11);

		p = new ImageIcon("E:\\foxhair.jpg");  //�α��� â�� ���� �̹��� 
		cp.add(t);
		imageLabel = new JLabel(p);
		imageLabel.setSize(393,316);
		imageLabel.setLocation(0,0);
		cp.add(imageLabel);

		textID = new JLabel("���̵� : ");  //�α��� ���� 
		textID.setForeground(Color.lightGray);
		textID.setSize(120,30);
		textID.setLocation(440,90);
		cp.add(textID);

		textPw= new JLabel("��й�ȣ  : ");
		textPw.setForeground(Color.lightGray);
		textPw.setSize(120,30);
		textPw.setLocation(435,130);
		cp.add(textPw);

		tfID = new JTextField("");   
		tfPw = new JPasswordField("");
		tfID.setSize(120,20);
		tfPw.setSize(120,20);
		tfID.setLocation(510,93);
		tfPw.setLocation(510,133);
		cp.add(tfID);
		cp.add(tfPw);

		bLogin = new JButton("�α���");
		bCancel = new JButton("������");
		bLogin.setSize(80,23);
		bCancel.setSize(80,23);
		bLogin.setLocation(450,200);
		bCancel.setLocation(550,200);
		cp.add(bLogin);
		cp.add(bCancel);

		

		bLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				c=DBLogin.getConnection(tfID.getText(), tfPw.getText()) ;   //DB�� �α��� ������ ������ �α����� �õ��Ѵ�. 
				
				/*
				 * �α��ο� ���� �� ��� â ũ�⸦ ������ �ϰ� ���� ȭ���� �籸���Ѵ�. 
				 * */
				if(c != null)
				{
					cp.removeAll();
					setSize(800,500);
					mainUI.setLocation(0,400);
					mainUI.setSize(800,50);
					mainUI.setBackground(Color.DARK_GRAY);
					add(mainUI);
					add(mi);
					cp.repaint();
				}
				else {
					JOptionPane.showMessageDialog(null, "�α��� ����");
				}
			}
		});
		
		bCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});


		cp.repaint();
		
		

	}



	public static void main(String[] args){
		Login jg = new Login();
		
	}
}