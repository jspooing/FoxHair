package FoxHairUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import FoxHairDB.DBGetRows;
import FoxHairDB.DBUpdate;

public class ProductOrderDialog extends JFrame{
	JLabel txtName, txtNum;
	JTextField tfNum;
	JButton bConfirm;
	public ProductOrderDialog(JFrame frame,String title, int p_num) {
		setSize(280,170);
		setLocation(600,400);
		setLayout(null);
		setVisible(true);
		
		String p_name = DBGetRows.getRowValue(Login.c, "product", "p_name","p_num",p_num);
		
		txtName = new JLabel("선택한 상품 : "+p_name);
		txtNum = new JLabel("수량 :");
		tfNum = new JTextField();
		bConfirm = new JButton("주문하기");
		
		txtName.setSize(200,30);
		txtNum.setSize(40,20);
		tfNum.setSize(60,20);
		bConfirm.setSize(100,20);
		
		txtName.setLocation(20,20);
		txtNum.setLocation(20,60);
		tfNum.setLocation(60,60);
		bConfirm.setLocation(130, 60);
		
		add(txtName);
		add(txtNum);
		add(tfNum);
		add(bConfirm);
		
		bConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String stock = DBGetRows.getRowValue(Login.c, "product", "stock","p_num",p_num);
				int newStock = Integer.parseInt(stock) + Integer.parseInt(tfNum.getText());
				DBUpdate.DBupdate(Login.c, "product", "p_num", String.valueOf(p_num), "stock", String.valueOf(newStock));
				JOptionPane.showMessageDialog(null, "주문 완료");
				
			}
		});
	}
}
