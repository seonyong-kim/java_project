package mypage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PhotoAlbum extends JFrame{
	private Container container;
	
	PhotoAlbum(){
		super("PhotoAlbum");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        container = getContentPane();
        container.add(CreateButton(),BorderLayout.NORTH);
        container.add(CreatePhotoAlbum(),BorderLayout.CENTER);
        
		setSize(400,400);
	    setResizable(false);
		setVisible(true);
	}
	
	private JPanel CreateButton() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JButton back = new JButton("back");
		panel.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FirstMain();
		        setVisible(false);
			}
		});
		return panel; 
	}
	
	private JPanel CreatePhotoAlbum() {
		JPanel panel = new JPanel(new GridLayout(3,2,2,2));
		ImageIcon[] images = new ImageIcon[6];
		images[0] = new ImageIcon("images/japan1.jpg");
		images[1] = new ImageIcon("images/japan2.jpg");
		images[2] = new ImageIcon("images/ssg.jpg");
		images[3] = new ImageIcon("images/exhibition.jpg");
	
		for(int i=0;i<images.length;i++) {
			JLabel label = new JLabel();
			if (images[i] != null) {
				Image img= images[i].getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	            label.setIcon(new ImageIcon(img));
	            panel.add(label);
			}
		}
		return panel; 
	}
	
	 
	public static void main(String[] args) {
		new PhotoAlbum();
	}

}
