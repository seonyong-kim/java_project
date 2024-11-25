package mypage;
import java.awt.*;
import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class start extends JFrame{
	private Container c = getContentPane();
	start(int hour){
		super("start screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(null);
		
		JLabel label = new JLabel("click_start");
		c.add(label);
		
		label.setLocation(200,300);
		label.setSize(100,100);
		if(hour >= 5 && hour <= 11) {
			// 화면 및 클릭시 시잓 구현하기
		}
		else if(hour > 11 && hour <= 17)
		{
			
		}
		else {
			
		}
		setSize(500,500);
		setVisible(true);
	}
	public static void main(String[] args) {
		LocalTime now = LocalTime.now();       
		System.out.println(now);  
		int hour = now.getHour();
		new start(hour);
	}
}
