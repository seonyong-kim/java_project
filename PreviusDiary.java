package mypage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import mypage.*;
import java.util.Scanner;

public class PreviusDiary extends JFrame{
	private String Date;
	private JTextArea previusDiary = new JTextArea();
	private JScrollPane previusDiaryScroll = new JScrollPane(previusDiary);
	private Container c;
    String weatherString = "";
    String feelingsString = "", eventString = "";
	JPanel diaryPanel = new JPanel();

	PreviusDiary(){
		super("previus Diary");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		System.out.println(Date);
		Calendar choiceDate = new Calendar();
		
		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(PreviusNorth(), BorderLayout.NORTH);
		c.add(previusDiary, BorderLayout.CENTER);
		c.add(previusSouth(), BorderLayout.SOUTH);
		System.out.println(Date);
	    setSize(400,400);
	    setVisible(true);
		// 파일 불러오기
		// 날짜 가져오기
		// 위의 3가지 불러오기 
	}
	
	PreviusDiary(String calendarDate){
		super("previus Diary");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		Calendar choiceDate = new Calendar();
		Date = calendarDate;
		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(PreviusNorth(), BorderLayout.NORTH);
		c.add(previusDiary, BorderLayout.CENTER);
		c.add(previusSouth(), BorderLayout.SOUTH);
	    setSize(400,400);
	    setVisible(true);
		// 파일 불러오기
		// 낧짜 가져오기
		// 위의 3가지 불러오기 
	}
	
	private JScrollPane CurrentCenter() {
		File path = new File("C:/Users/user/eclipse-workspace/java/images/" + Date + ".txt");
		int c;
		String memory = "";
		boolean skip = true;
		if(path.exists()) {
			try {
				 FileInputStream today = new  FileInputStream (path);
				 InputStreamReader reader = new InputStreamReader(today, "UTF-8"); 
				 while ((c = reader.read()) != -1) { 
					 memory += (char)c;
					 } 
				 
				reader.close();
				today.close();
				previusDiary.append(memory);
				previusDiaryScroll = new JScrollPane(previusDiary);
				return previusDiaryScroll;
			}
			catch(IOException e) {
				return previusDiaryScroll;
			}
		}else {
			 System.out.println("Completed1");
			return previusDiaryScroll;
		}
	}
	
	private JToolBar PreviusNorth() {
		JToolBar diaryNorthBar = new JToolBar();
		
        
		diaryNorthBar.add(new JLabel(Date));
		
		JMenuBar diaryNorthMenu = new JMenuBar();
		
		String weather = "weather";
		JMenu weatherMenu = new JMenu(weather);
		JMenuItem[] weatherItem= new JMenuItem[4];
		String[] weatherTitle = {"Sunny", "Rainny", "Cloudy", "Snow"};
		for(int i=0;i<4;i++) {
			weatherItem[i] = new JMenuItem(weatherTitle[i]); 
			weatherItem[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
                    JMenuItem clickedItem = (JMenuItem) e.getSource();
                    weatherMenu.setText(clickedItem.getText());
                    weatherString = clickedItem.getText();
				}
			});
			weatherMenu.add(weatherItem[i]); 
		}//콤보박스있다.
		diaryNorthMenu.add(weatherMenu);
		
				
		String feelings = "feelings";
		JMenu feelingsMenu = new JMenu(feelings);
		JMenuItem[] feelingsItem= new JMenuItem[4];
		String[] feelingsTitle = {"happy", "excited", "sad", "angry"};
		
		for(int i=0;i<4;i++) {
			feelingsItem[i] = new JMenuItem(feelingsTitle[i]); 
			feelingsItem[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
                    JMenuItem clickedItem = (JMenuItem) e.getSource();
                    feelingsMenu.setText(clickedItem.getText());
                    feelingsString = clickedItem.getText();
				}
			});
			feelingsMenu.add(feelingsItem[i]); 
		}
		diaryNorthMenu.add(feelingsMenu);
		diaryNorthBar.add(diaryNorthMenu);
		

		String event = "event";
		JMenu eventMenu = new JMenu(event);
		JMenuItem[] eventItem= new JMenuItem[6];
		String[] eventTitle = {"travel", "familly", "test", "festival", "nothing"};
		for(int i=0;i<5;i++) {
			eventItem[i] = new JMenuItem(eventTitle[i]); 
			eventItem[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
                    JMenuItem clickedItem = (JMenuItem) e.getSource();
                    eventMenu.setText(clickedItem.getText());
                    eventString = clickedItem.getText();
				}
			});
			eventMenu.add(eventItem[i]); 
		}
		diaryNorthMenu.add(eventMenu);
		
		JLabel blank = new JLabel("                                   ");
		diaryNorthBar.add(blank);
		
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
	
	private JPanel previusSouth() {		
		JButton diarySave = new JButton("save");
		
		ImageIcon icon = new ImageIcon("images/gray.png");
		diaryPanel.add(diarySave);
		
		diarySave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File diary = new File("C:/Users/user/eclipse-workspace/java/images/" + Date + ".txt");
				FileWriter writer = null;
				String text = previusDiary.getText();
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
		new PreviusDiary();
	}
}
