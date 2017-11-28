package FoxHairUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainImage extends JPanel{
	ImageIcon p;
	JLabel imageLabel;
	
	public MainImage() {
		setSize(800,400);
		setLocation(0,0);
		setLayout(null);
		p=new ImageIcon("E:\\hairmain.jpg");
		imageLabel = new JLabel(p);
		imageLabel.setSize(800,400);
		imageLabel.setLocation(0,0);
		add(imageLabel);
	}

}
