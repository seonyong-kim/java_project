package mypage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Diary extends JFrame{
	private Container container;
	private JTextArea Diary = new JTextArea();
	private JScrollPane DiaryScroll = new JScrollPane(Diary);
	private LocalDate today = LocalDate.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private String Date = today.format(formatter);
	private String weatherString = "", feelingsString = "", eventString = "";
	private JPanel diaryPanel = new JPanel();
	private JButton back;
	
	Diary(){
		super("CurrentPage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(CurrentNorth(), BorderLayout.NORTH);		
		container.add(CurrentCenter(), BorderLayout.CENTER);
		container.add(CurrentSouth(), BorderLayout.SOUTH);
		
		Diary.setLineWrap(true);
	    setSize(400,500);
	    setResizable(false);
	    setVisible(true);
	}
	
	Diary(String calendarDate){
		super("previus Diary");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
		Date = calendarDate;
		
		container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(PreviusNorth(calendarDate), BorderLayout.NORTH);
		container.add(CurrentCenter(), BorderLayout.CENTER);
		container.add(CurrentSouth(), BorderLayout.SOUTH);
		
		Diary.setLineWrap(true);
	    setSize(400,500);
	    setResizable(false);
	    setVisible(true);
	}
	
	private JScrollPane CurrentCenter() {
		File path = new File("images/" + Date + ".txt");
		int c;
		String memory = "";
		if(path.exists()) {
			try {
				 FileInputStream today = new FileInputStream(path);
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
			return DiaryScroll;
		}
	}
	
	private JToolBar PreviusNorth(String calendarDate) {
		JToolBar previusDiaryToolBar = new JToolBar();
		previusDiaryToolBar = CurrentNorth();
		JToolBar previusDiaryToolBarEast = new JToolBar();
		
		JLabel label;
		if(calendarDate.equals(today.format(formatter))) {
			label = new JLabel("Today");
		}
		else {
			label = new JLabel("memory");
		}
		previusDiaryToolBarEast.add(label);
		previusDiaryToolBarEast.addSeparator();
		
		back =  new JButton("back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		         setVisible(false);
			}
		});
		previusDiaryToolBarEast.add(back);
		
		previusDiaryToolBar.add(previusDiaryToolBarEast, BorderLayout.EAST);
		return previusDiaryToolBar;
	}

	private JToolBar CurrentNorth() {
		JToolBar diaryNorthToolBar = new JToolBar();
		diaryNorthToolBar.setLayout(new BorderLayout());
		diaryNorthToolBar.add(new JLabel(Date),BorderLayout.WEST);
		
		JMenuBar diaryNorthMenu = new JMenuBar();
		String weather = "weather"; 
		String feelings = "feelings"; 
		String event = "event";
		
		File path = new File("images/" + Date + "imo.txt");
        if (path.exists()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader
            		(new FileInputStream(path), "UTF-8"))) {
                String line = reader.readLine(); 
                if (line != null) {
                    String[] parts = line.split(" ", 3); 
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
				JOptionPane.showMessageDialog(null, "An error has occurred", "Message", 
						JOptionPane. ERROR_MESSAGE);
            }
        }
        
		JMenu weatherMenu = new JMenu(weather);
		JMenuItem[] weatherItem= new JMenuItem[5];
		String[] weatherTitle = {"Sunny", "Rainny", "Cloudy", "Snowy", "stormy"};
		for(int i=0;i<weatherTitle.length;i++) {
			weatherItem[i] = new JMenuItem(weatherTitle[i]); 
			weatherItem[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
                    JMenuItem clickedItem = (JMenuItem) e.getSource();
                    weatherMenu.setText(clickedItem.getText());
                    weatherString = clickedItem.getText();
				}
			});
			weatherMenu.add(weatherItem[i]); 
		}
		diaryNorthMenu.add(weatherMenu);
				
		JMenu feelingsMenu = new JMenu(feelings);
		JMenuItem[] feelingsItem= new JMenuItem[6];
		String[] feelingsTitle = {"happy", "excited", "Serenity", "sad", "angry", "tired"};
		for(int i=0;i<feelingsTitle.length;i++) {
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
		
		JMenu eventMenu = new JMenu(event);
		JMenuItem[] eventItem= new JMenuItem[6];
		String[] eventTitle = {"travel", "familly", "test", "festival", "sad_thing","nothing"};
		for(int i=0;i<eventTitle.length;i++) {
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
		diaryNorthToolBar.add(diaryNorthMenu,BorderLayout.CENTER);
		
		back = new JButton("back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FirstMain();
				setVisible(false);
			}
		});
		diaryNorthToolBar.add(back,BorderLayout.EAST);
		
		return diaryNorthToolBar;
	}
	
	private JPanel CurrentSouth() {		
		JButton diarySave = new JButton("save");
		diaryPanel.add(diarySave);
		diarySave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File diary = new File("images/" + Date + ".txt");
				File imoticon = new File("images/" + Date + "imo" +".txt");
				OutputStreamWriter writer, writer2; 
				String text = Diary.getText();
				try {
					FileOutputStream fos = new FileOutputStream(diary);
					writer = new OutputStreamWriter(fos, "UTF-8"); 
					writer.write(text); 
					writer.flush();
					writer.close();
					
					FileOutputStream fos2 = new FileOutputStream(imoticon);
					writer2 = new OutputStreamWriter(fos2, "UTF-8");
					writer2.write(getweatherImoticon() + " " + getfeelingsImoticon() 
					+ " " + geteventImoticon() + "\n");
					writer2.flush();
					writer2.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "An error has occurred", "Message", 
							JOptionPane. ERROR_MESSAGE);
				} 
				JOptionPane.showMessageDialog(null, "Your " + Date + " diary has been saved.", "Message", 
						JOptionPane. INFORMATION_MESSAGE);
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
	    new Diary();
	}
	
}