package FoxHairUI;

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

public class ProductRegistDialog extends JDialog{
	String[] strCols = {"p_num","p_name","price","prime_cost","supplier","p_type"};
	JTextField tfName,tfPrice,tfPrimeCost,tfType;
	JLabel txtName,txtPrice,txtPrimeCost,txtType,txtSupplier;
	JButton bRegist;
	JComboBox cbSupplier;
	Vector vSupplier = new Vector<String>();
	
	public ProductRegistDialog(JFrame frame, String title) {
		super(frame,title);
		setSize(620,160);
		setLocation(800,400);
		setLayout(null);
		setVisible(true);
		
		vSupplier = DBGetRows.getRows(Login.c, "supplier", "sp_name");
		
		tfName = new JTextField();
		tfPrice = new JTextField();
		tfPrimeCost = new JTextField();
		tfType = new JTextField();
		txtName = new JLabel("상품 명");
		txtPrice = new JLabel("상품 가격");
		txtPrimeCost = new JLabel("원가");
		txtType = new JLabel("상품 분류");
		txtSupplier = new JLabel("공급 업체");
		bRegist = new JButton("등록");
		cbSupplier = new JComboBox(vSupplier);
		
		tfName.setSize(100,20); 
		tfPrice.setSize(100,20); 
		tfPrimeCost.setSize(100,20); 
		tfType.setSize(100,20); 
		txtName.setSize(60,20);
		txtPrice.setSize(60,20);
		txtPrimeCost.setSize(60,20);
		txtType.setSize(60,20);
		txtSupplier.setSize(60,20);
		bRegist.setSize(120,20);
		cbSupplier.setSize(100,20);
		
		txtName.setLocation(20,20);
		tfName.setLocation(100,20);
		txtPrice.setLocation(220,20);
		tfPrice.setLocation(300,20);
		txtPrimeCost.setLocation(420,20);
		tfPrimeCost.setLocation(480,20);
		txtType.setLocation(20,60);
		tfType.setLocation(100,60);
		txtSupplier.setLocation(220,60);
		cbSupplier.setLocation(300,60);
		bRegist.setLocation(450,60);
		
		add(txtName);
		add(tfName);
		add(txtPrice);
		add(tfPrice);
		add(txtPrimeCost);
		add(tfPrimeCost);
		add(txtType);
		add(tfType);
		add(txtSupplier);
		add(cbSupplier);
		add(bRegist);
		
		bRegist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] v=new String[6];
				v[0] =String.valueOf(DBInsert.getMaxNum(Login.c,"product","p_num")+1);
				v[1] =tfName.getText();
				v[2] =tfPrice.getText();
				v[3] =tfPrimeCost.getText();
				v[4] = (String)vSupplier.get(cbSupplier.getSelectedIndex());
				v[5] =tfType.getText();
				DBInsert.DBInsert(Login.c, "product", strCols, v);
				JOptionPane.showMessageDialog(null, "상품 등록 완료");
			}	
		});
		
		
		
	}
}
