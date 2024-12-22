package mypage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PhotoAlbum extends JFrame{
	private Container container;
	
	PhotoAlbum(){
		super("Calendar");
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
		JPanel panel = new JPanel(new GridLayout(2,2,1,1));
		ImageIcon[] images = new ImageIcon[9];
		images[0] = new ImageIcon("images/japan1.jpg");
		images[1] = new ImageIcon("images/japan2.jpg");
		images[2] = new ImageIcon("images/ssg.jpg");
		images[3] = new ImageIcon("images/exhibition.jpg");
		for(int i=0;i<images.length;i++) {
			 if (images[i] != null) {
				 Image img= images[i].getImage(); 
			 }
		}
		return panel; 
	}

	 
	public static void main(String[] args) {
		new PhotoAlbum();
	}

}
