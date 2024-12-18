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

public class diary extends JFrame{
	private Container c;
	private JTextArea Diary = new JTextArea();
	private JScrollPane DiaryScroll = new JScrollPane(Diary);
	private LocalDate today = LocalDate.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private String Date = today.format(formatter);
	private String weatherString = "", feelingsString = "", eventString = "";
	private JPanel diaryPanel = new JPanel();
	private JButton back;
	private boolean current = true;
	
	diary(){
		super("CurrentPage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(CurrentNorth(), BorderLayout.NORTH);		
		c.add(CurrentCenter(), BorderLayout.CENTER);
		c.add(CurrentSouth(), BorderLayout.SOUTH);
		Diary.setLineWrap(true);
	    setSize(400,500);
	    setResizable(false);
	    setVisible(true);
	}
	
	diary(String calendarDate){
		super("previus Diary");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		Calendar choiceDate = new Calendar();
		Date = calendarDate;
		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(PreviusNorth(), BorderLayout.NORTH);
		c.add(CurrentCenter(), BorderLayout.CENTER);
		c.add(CurrentSouth(), BorderLayout.SOUTH);
		Diary.setLineWrap(true);
	    setSize(400,500);
	    setResizable(false);
	    setVisible(true);
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
				Diary.append(memory);
				DiaryScroll = new JScrollPane(Diary);
				return DiaryScroll;
			}
			catch(IOException e) {
				return DiaryScroll;
			}
		}else {
			 System.out.println("Completed1");
			return DiaryScroll;
		}
	}
	
	private JToolBar PreviusNorth() {
		current = false;
		JToolBar previusDiaryBar = new JToolBar();
		previusDiaryBar = CurrentNorth();
		
		back =  new JButton("back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 new Calendar();
		         setVisible(false);
			}
		});
		previusDiaryBar.add(back);
		
		JButton main = new JButton("main");
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 new first_main();
		         setVisible(false);
			}
		});
		previusDiaryBar.add(main);
		
		return previusDiaryBar;
	}
	
	private JToolBar CurrentNorth() {
		JToolBar diaryNorthBar = new JToolBar();
		diaryNorthBar.add(new JLabel(Date));
		JMenuBar diaryNorthMenu = new JMenuBar();
		String weather = "weather"; 
		String feelings = "feelings"; 
		String event = "event";
		System.out.println("Current_North");
		File path = new File("C:/Users/user/eclipse-workspace/java/images/" + Date + "imo.txt");
		int b=0;
        if (path.exists()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
                String line = reader.readLine(); // 첫 번째 줄 읽기
                if (line != null) {
                    String[] parts = line.split(" ", 3); // 공백 기준으로 나누기
                    if (parts.length > 0) {
                    	weather = parts[0];
                    	weatherString = weather;
                    	if(weather.equals("")) {
                    		weather = "weather"; 
                    	}
                    }
                    if (parts.length > 1) {
                    	feelings = parts[1];
                    	feelingsString = feelings;
                    	if(feelings.equals("")) {
                    		feelings = "feelings"; 
                    	}
                    }
                    if (parts.length > 2) {
                    	event = parts[2];
                    	eventString = event;
                    	if(event.equals("")) {
                    		event = "event"; 
                    	}
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		JMenu weatherMenu = new JMenu(weather);
		JMenuItem[] weatherItem= new JMenuItem[5];
		String[] weatherTitle = {"Sunny", "Rainny", "Cloudy", "Snowy", "stormy"};
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
		System.out.println(weather); 
				
		JMenu feelingsMenu = new JMenu(feelings);
		JMenuItem[] feelingsItem= new JMenuItem[6];
		String[] feelingsTitle = {"happy", "excited", "Serenity", "sad", "angry", "tired"};
		
		for(int i=0;i<4;i++) {
			feelingsItem[i] = new JMenuItem(feelingsTitle[i]); 
			feelingsItem[i].addActionListener(new ActionListener() {
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
		

		JMenu eventMenu = new JMenu(event);
		JMenuItem[] eventItem= new JMenuItem[6];
		String[] eventTitle = {"travel", "familly", "test", "festival", "sad_thing","nothing"};
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
		
		JLabel blank = new JLabel("                    ");
		diaryNorthBar.add(blank);
		
		if(current) {
			back = new JButton("back");
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new first_main();
					setVisible(false);
				}
			});
			diaryNorthBar.add(back);
		}
		
		return diaryNorthBar;
	}

	private JPanel CurrentSouth() {		
		JButton diarySave = new JButton("save");
		
		ImageIcon icon = new ImageIcon("images/gray.png");
		diaryPanel.add(diarySave);
		
		diarySave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File diary = new File("C:/Users/user/eclipse-workspace/java/images/" + Date + ".txt");
				File imoticon = new File("C:/Users/user/eclipse-workspace/java/images/" + Date + "imo" +".txt");
				OutputStreamWriter writer, writer2; 
				String text = Diary.getText();
				try {
					FileOutputStream fos = new FileOutputStream(diary);
					FileOutputStream fos2 = new FileOutputStream(imoticon);
					writer = new OutputStreamWriter(fos, "UTF-8"); 
					writer2 = new OutputStreamWriter(fos2, "UTF-8");
					writer2.write(getweatherImoticon() + " " + getfeelingsImoticon() + " " + geteventImoticon() + "\n");
					writer.write(text); 
					writer.flush();
					writer.close();
					writer2.flush();
					writer2.close();
					System.out.println("Completed"); // 성공하면 save문구 띄우기
				} catch (IOException e1) {
					e1.printStackTrace(); // 실패시 에러메시지를 출력한다.
				} 
			}
		});
		return diaryPanel;
	}
	
	public String getweatherImoticon() {
		return weatherString;
	}
	public String getfeelingsImoticon() {
		return feelingsString;
	}
	public String geteventImoticon() {
		return eventString;
	}
	
	public static void main(String[] args) {
	    new diary();
	}
}