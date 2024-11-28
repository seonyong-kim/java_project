package mypage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import mypage.*;

public class start extends JFrame{
	private Container c = getContentPane();
	private JButton button = new JButton("press to start");
	start(LocalTime now){
		super("First page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(null);
		int hour = now.getHour();
		
		c.add(button);
		button.setLocation(160,370);
		button.setSize(150,30);
		
		JLabel imageLabel = new JLabel();
		imageLabel.setLocation(0,0);
		imageLabel.setSize(500,500);
		c.add(imageLabel);
		if(hour >= 5 && hour <= 11) {
			// 화면 및 클릭시 시잓 구현하기
			ImageIcon image = new ImageIcon("images/sun.jpg");
            imageLabel.setIcon(image);
		}
		else if(hour > 11 && hour <= 17){
			ImageIcon image = new ImageIcon("images/park.jpg");
            imageLabel.setIcon(image);
		}
		else {
			ImageIcon image = new ImageIcon("images/moon.jpeg");
            imageLabel.setIcon(image);
		}
        button.addMouseListener(new GoFirstMain());
		setSize(500,500);
		setVisible(true);
	}
	
	private class GoFirstMain implements MouseListener{
		 public void mousePressed(MouseEvent e) {
			 new first_main();
	         setVisible(false);
	         }
		 public void mouseClicked(MouseEvent e) {}
		 public void mouseReleased(MouseEvent e) {}
	     public void mouseEntered(MouseEvent e) {}
	     public void mouseExited(MouseEvent e) {}
	}
	
	
/*	public class first_main extends JFrame{
		   private Container contentPane;
		   public first_main() {
		      super("main화면");
		      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      contentPane = getContentPane();
		      createMenu();
		      ButtomButton();
		      setSize(400,400);
		      setVisible(true);
		   }
		   private void createMenu() {
		      JMenuBar mainMenuBar = new JMenuBar();
		      JMenu mainMenu = new JMenu("Screen");
		      
		   }
		   private void ButtomButton() {
			   JPanel ButtomButton = new JPanel(new GridLayout(1, 3));
			   ButtomButton.add(new JButton("Previous Page"));
			   ButtomButton.add(new JButton("Current Page"));
			   ButtomButton.add(new JButton("Next Page"));
		   }
	}
	*/
	public static void main(String[] args) {
		LocalTime now = LocalTime.now();       
		System.out.println(now);  
		int hour = now.getHour();
		new start(now);
	}
	
	
}
