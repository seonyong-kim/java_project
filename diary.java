package mypage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.nio.charset.StandardCharsets;
import mypage.*;

public class diary extends JFrame{
	private Container c;
	JTextArea todayDiary = new JTextArea();
	diary(){
		super("CurrentPage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(CurrentNorth(), BorderLayout.NORTH);
		c.add(todayDiary, BorderLayout.CENTER);
		c.add(CurrentSouth(), BorderLayout.SOUTH);
	    setSize(400,400);
	    setVisible(true);
	}
	
	private JToolBar CurrentNorth() {
		JToolBar diaryNorthBar = new JToolBar();
		
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
		Date now = new Date();
		String nowStr = format.format(now);
		
		diaryNorthBar.add(new JLabel(nowStr));
		
		JMenuBar diaryNorthMenu = new JMenuBar();
		
		String weather = "weather";
		JMenu weatherMenu = new JMenu(weather);
		JMenuItem[] weatherItem= new JMenuItem[5];
		String[] weatherTitle = {"Sunny", "Rainny", "Cloudy", "Snowy","Disaster"};
		for(int i=0;i<5;i++) {
			weatherItem[i] = new JMenuItem(weatherTitle[i]); 
			weatherItem[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
                    JMenuItem clickedItem = (JMenuItem) e.getSource();
                    weatherMenu.setText(clickedItem.getText());
				}
			});
			weatherMenu.add(weatherItem[i]); 
		}//콤보박스있다.
		diaryNorthMenu.add(weatherMenu);
		
				
		String feelings = "feelings";
		JMenu feelingsMenu = new JMenu(feelings);
		JMenuItem[] feelingsItem= new JMenuItem[6];
		String[] feelingsTitle = {"happy", "excited", "fantastic", "sad", "angry", "bad"};
		
		for(int i=0;i<6;i++) {
			feelingsItem[i] = new JMenuItem(feelingsTitle[i]); 
			feelingsItem[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
                    JMenuItem clickedItem = (JMenuItem) e.getSource();
                    feelingsMenu.setText(clickedItem.getText());
				}
			});
			feelingsMenu.add(feelingsItem[i]); 
		}
		diaryNorthMenu.add(feelingsMenu);
		diaryNorthBar.add(diaryNorthMenu);
		

		String event = "event";
		JMenu eventMenu = new JMenu(event);
		JMenuItem[] eventItem= new JMenuItem[3];
		String[] eventTitle = {"travel", "familly", "test"};
		for(int i=0;i<3;i++) {
			eventItem[i] = new JMenuItem(eventTitle[i]); 
			eventItem[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
                    JMenuItem clickedItem = (JMenuItem) e.getSource();
                    eventMenu.setText(clickedItem.getText());
				}
			});
			eventMenu.add(eventItem[i]); 
		}
		diaryNorthMenu.add(eventMenu);
		
		JButton back = new JButton("back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 new first_main();
		         setVisible(false);
			}
		});
		diaryNorthBar.add(back);
		
		return diaryNorthBar;
	}
	
	private JPanel CurrentSouth() {		
		JPanel diaryPanel = new JPanel();
		JButton diaryPicture = new JButton("add picture");
		JButton diarySave = new JButton("save");
		diaryPanel.add(diaryPicture);
		diaryPanel.add(diarySave);
		diarySave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File diary = new File("C:/Users/user/eclipse-workspace/java/images/diary.txt");
				FileWriter writer = null;
				String text = todayDiary.getText();
				try {
					writer = new FileWriter(diary); 
					writer.write(text); 
					writer.flush();
					writer.close();
					System.out.println("Completed"); // 성공하면 save문구 띄우기
				} catch (IOException e1) {
					e1.printStackTrace(); // 실패시 에러메시지를 출력한다.
				} 
			}
		});
		return diaryPanel;
	}
	
	public static void main(String[] args) {
		new diary();
	}
}
