package mypage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class start extends JFrame{
	private Container c = getContentPane();
	private JButton button = new JButton("click_start");
	start(LocalTime now){
		super("First page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(null);
		int hour = now.getHour();
		
		c.add(button);
		button.setLocation(180,350);
		button.setSize(100,30);
		
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
            button.addMouseListener(new GoFirstMain());
		}
		else {
			ImageIcon image = new ImageIcon("images/moon.jpeg");
            imageLabel.setIcon(image);
            button.addMouseListener(new GoFirstMain());
		}
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
	
	
	public class first_main extends JFrame{
		   private Container contentPane;
		   public first_main() {
		      super("main화면");
		      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      contentPane = getContentPane();
		      createMenu();
		      createToolBar();
		      setSize(400,400);
		      setVisible(true);
		   }
		   private void createMenu() {
		      JMenuBar mainMenuBar = new JMenuBar();
		      JMenu mainMenu = new JMenu("Screen");
		      
		   }
		   private void createToolBar() {
		      JToolBar toolBar = new JToolBar("Kitae Menu");
		      toolBar.setBackground(Color.LIGHT_GRAY);
		      String[] mainToolbar = {"이전 page로", "지금", "다음 page로"};
		      for(int i=0;i<3;i++) {
		         toolBar.add(new JButton(mainToolbar[i]));
		         toolBar.addSeparator();
		      }// 일단 추가로 해야할점 이 3개로 툴바를 가득차게 만들고 싶다. 
		      
		      contentPane.add(toolBar, BorderLayout.SOUTH);   
		   }
	}
	public static void main(String[] args) {
		LocalTime now = LocalTime.now();       
		System.out.println(now);  
		int hour = now.getHour();
		new start(now);
	}
}
