package FoxHairUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainUI extends JPanel {
	JButton bProduct,bSales,bSearch,bStaff,bReserve;
	SearchCustomer dSearch ;
	ProductUI dProduct;
	StaffUI dStaff;
	SalesUI dSales;
	ReserveUI dReserve;
	
	
	
	JPanel jpNow = null;
	public MainUI(JFrame frame,JPanel image) {
		setLayout(null);
		
		dSearch = new SearchCustomer(frame);
		dProduct = new ProductUI(frame);
		dStaff = new StaffUI(frame);
		dSales = new SalesUI(frame);
		dReserve = new ReserveUI(frame);
		
		bSearch = new JButton("绊按 包府");
		bProduct = new JButton("惑前 包府");
		bStaff = new JButton("流盔 包府");
		bSales = new JButton("概免");
		bReserve = new JButton("抗距 包府");
		
		bSearch.setSize(120,23);
		bProduct.setSize(120,23);
		bStaff.setSize(120,23);
		bSales.setSize(120,23);
		bReserve.setSize(120,23);
		
		bSearch.setLocation(40,20);
		bProduct.setLocation(190,20);
		bStaff.setLocation(340, 20);
		bSales.setLocation(490,20);
		bReserve.setLocation(640,20);
		
		dSearch.setBackground(Color.DARK_GRAY);
		dProduct.setBackground(Color.DARK_GRAY);
		dStaff.setBackground(Color.DARK_GRAY);
		dSales.setBackground(Color.DARK_GRAY);
		dReserve.setBackground(Color.DARK_GRAY);
		
		add(bProduct);
		add(bSearch);
		add(bStaff);
		add(bSales);
		add(bReserve);
		
		jpNow = image;
		
		
		
		bSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setVisible(false);
				if(!jpNow.equals(dSearch)) {
				frame.remove(jpNow);
				frame.add(dSearch);
				jpNow = dSearch;}
				else {
					frame.remove(dSearch);
					frame.add(image);
					jpNow = image;
				}
				frame.repaint();

			}});
		
		bProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!jpNow.equals(dProduct)) {
					frame.remove(jpNow);
					frame.add(dProduct);
					jpNow=dProduct;
				}
				else {
					frame.remove(dProduct);
					frame.add(image);
					jpNow = image;
				}
				frame.repaint();
			}
			
		});
		
		bStaff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!jpNow.equals(dStaff)) {
					frame.remove(jpNow);
					frame.add(dStaff);
					jpNow=dStaff;
				}
				else {
					frame.remove(dStaff);
					frame.add(image);
					jpNow = image;
				}
				frame.repaint();
			}
			
		});
		
		bSales.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!jpNow.equals(dSales)) {
					frame.remove(jpNow);
					frame.add(dSales);
					jpNow=dSales;
				}
				else {
					frame.remove(dSales);
					frame.add(image);
					jpNow = image;
				}
				frame.repaint();
			}
			
		});
		
		bReserve.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!jpNow.equals(dReserve)) {
					frame.remove(jpNow);
					frame.add(dReserve);
					jpNow=dReserve;
				}
				else {
					frame.remove(dReserve);
					frame.add(image);
					jpNow = image;
				}
				frame.repaint();
			}
			
		});
	}
}
