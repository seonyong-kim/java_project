package mypage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalTime;

public class Start extends JFrame{
	private Container container = getContentPane();
	private JButton button = new JButton("press to start");
	
	Start(LocalTime now){
		super("First Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
	    
		container.setLayout(null);
		container.add(button);
		button.setLocation(160,370);
		button.setSize(150,30);
		
		JLabel imageLabel = new JLabel();
		imageLabel.setLocation(0,0);
		imageLabel.setSize(500,500);
		container.add(imageLabel);
		ImageIcon image;
		
		int hour = now.getHour();
		if(hour >= 5 && hour <= 11) {
			image = new ImageIcon("images/sun.jpg");
            imageLabel.setIcon(image);
		}
		else if(hour > 11 && hour <= 17){
			image = new ImageIcon("images/park.jpg");
            imageLabel.setIcon(image);
		}
		else {
			image = new ImageIcon("images/moon.jpeg");
            imageLabel.setIcon(image);
		}
		
        button.addMouseListener(new GoFirstMain());
		setSize(480,500);
		setVisible(true);
	}
	
	private class GoFirstMain implements MouseListener{
		 public void mousePressed(MouseEvent e) {
			 new FirstMain();
	         setVisible(false);
	         }
		 
		 public void mouseClicked(MouseEvent e) {}
		 public void mouseReleased(MouseEvent e) {}
	     public void mouseEntered(MouseEvent e) {}
	     public void mouseExited(MouseEvent e) {}
	}
	
	public static void main(String[] args) {
		LocalTime now = LocalTime.now();        
		new Start(now);
	}
	
}
