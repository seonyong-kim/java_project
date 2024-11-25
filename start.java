package mypage;
import java.awt.*;
import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class start extends JFrame{
	private Container c = getContentPane();
	
	start(LocalTime now){
		super("First page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(null);
		int hour = now.getHour();
		
		JLabel label = new JLabel("click_start");
		c.add(label);
		label.setLocation(200,300);
		label.setSize(100,100);
		
		JLabel imageLabel = new JLabel();
		imageLabel.setLocation(0,0);
		imageLabel.setSize(500,500);
		c.add(imageLabel);
		if(hour >= 5 && hour <= 11) {
			// 화면 및 클릭시 시잓 구현하기
			ImageIcon image = new ImageIcon("images/moon.jpeg");
            imageLabel.setIcon(image);
		}
		else if(hour > 11 && hour <= 17){
			ImageIcon image = new ImageIcon("images/moon.jpeg");
            imageLabel.setIcon(image);
		}
		else {
			ImageIcon image = new ImageIcon("images/moon.jpeg");
            imageLabel.setIcon(image);
		}
		setSize(500,500);
		setVisible(true);
	}
	public static void main(String[] args) {
		LocalTime now = LocalTime.now();       
		System.out.println(now);  
		int hour = now.getHour();
		new start(now);
	}
}
