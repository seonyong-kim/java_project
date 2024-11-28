package mypage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import mypage.*;

public class diary extends JFrame{
	private Container c;
	diary(){
		super("CurrentPage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(CurrentNorth(), BorderLayout.NORTH);
//		c.add(CurrentCenter(), BorderLayout.CENTER);
//		c.add(CurrentCenter(), BorderLayout.SOUTH);\
	    setSize(400,400);
	    setVisible(true);
	}
	
	private JToolBar CurrentNorth() {
		JToolBar diaryNorthBar = new JToolBar();
		diaryNorthBar.add(new JLabel("1   ")); // 날짜 받아오기
		
		String weather = "weather";
		JMenuBar diaryNorthMenu = new JMenuBar();
		JMenu weatherMenu = new JMenu(weather);
		JMenuItem[] weatherItem= new JMenuItem[5];
		String[] weatherTitle = {"Sunny", "Rainny", "Cloudy", "Snowy","Disaster"};
		MenuActionListener listener = new MenuActionListener();
		for(int i=0;i<5;i++) {
			weatherItem[i] = new JMenuItem(weatherTitle[i]); 
			weatherItem[i].addActionListener(listener);
			weatherMenu.add(weatherItem[i]); 
		}
		diaryNorthMenu.add(weatherMenu);
		
		String feelings = "feelings";
		JMenu feelingsMenu = new JMenu(feelings);
		feelingsMenu.add(new JMenuItem("Happy"));
		feelingsMenu.add(new JMenuItem("Sad"));
		feelingsMenu.add(new JMenuItem("Angry"));
		feelingsMenu.add(new JMenuItem("excited"));
		feelingsMenu.add(new JMenuItem("Proud"));
		feelingsMenu.add(new JMenuItem("excited"));
		diaryNorthMenu.add(feelingsMenu);
		diaryNorthBar.add(diaryNorthMenu);
		
		return diaryNorthBar;
	}
	
	class MenuActionListener implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 
		 }
		 } 
	
	public static void main(String[] args) {
		new diary();
	}
}
