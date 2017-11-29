package FoxHairUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import FoxHairDB.DBGetRows;
import FoxHairDB.DBSelect;




/*
 * 판매 다이얼로그 
 * */
public class SellDialog extends JDialog{
	JRadioButton rbProduct,rbStyling;
	SellProduct jpProduct;
	SellStyling jpStyling;
	SellDialog td = this;
	JPanel jpNow = null;
	JLabel selName ;
	int c_num;

	public SellDialog(JFrame frame,String Title,int c_num) {
		super(frame,Title);
		setSize(800,600);
		setLocation(800,400);
		setLayout(null);
		setVisible(true);
		setBackground(Color.DARK_GRAY);
		
		this.c_num = c_num;
		String c_name = DBGetRows.getRowValue(Login.c, "customer", "name", "c_num", c_num);
		
		rbProduct = new JRadioButton("상품",true);
		rbStyling = new JRadioButton("시술");
		jpProduct = new SellProduct(frame,c_num);
		jpStyling = new SellStyling(frame,c_num);
		ButtonGroup bg = new ButtonGroup();
		jpProduct.setBackground(Color.DARK_GRAY);
		jpStyling.setBackground(Color.DARK_GRAY);
		selName =new JLabel("선택된 고객 이름: "+ c_name);

		bg.add(rbProduct);
		bg.add(rbStyling);

		jpProduct.setSize(800,400);
		jpStyling.setSize(800,400);
		rbProduct.setSize(60,20);
		rbStyling.setSize(60,20);
		selName.setSize(200,20);

		jpProduct.setLocation(0,40);
		jpStyling.setLocation(0,40);
		rbProduct.setLocation(20,20);
		rbStyling.setLocation(80,20);
		selName.setLocation(180,20);

		
		add(jpProduct);
		add(rbProduct);
		add(rbStyling);
		add(selName);
		jpNow=jpProduct;
		repaint();
		
		rbProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				remove(jpNow);
				jpNow=jpProduct;
				add(jpNow);
				repaint();
			}



			});

		rbStyling.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				remove(jpNow);
				jpNow=jpStyling;
				add(jpNow);
				repaint();
			}

		});

		
		}

	}

