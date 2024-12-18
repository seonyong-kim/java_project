package mypage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class next_page extends JFrame{
	private Container contentPane;
	private int ddayYear = 0, ddayMonth = 0, ddayDay = 0, dayOfMonth = 0;
	private JTextField yearText, monthText, dayText, scheduleText;
	private int saveYear, saveMonth, saveDay;
	private String saveSchedule;
	private int d_dayCounter = 0;
	private LocalDate today = LocalDate.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private int nowYear = today.getYear(), nowMonth = today.getMonthValue(), nowDay = today.getDayOfMonth();
	private int year, month, day;
	
	next_page(){
		super("next_page");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    contentPane = getContentPane();
	    
	    contentPane.add(nextPageNorth(), BorderLayout.NORTH);
	    contentPane.add(nextPageCenter(), BorderLayout.CENTER);
	    setSize(600,600);
	    setResizable(false);
	    setVisible(true);
	}
	
	private JPanel nextPageNorth() {
		JPanel northFrmae = new JPanel();
		JButton previus = new JButton("previus");
		previus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Calendar();
		        setVisible(false);
			}
		});
		
		JButton main = new JButton("main");
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new first_main();
		        setVisible(false);
			}
		});
		
		northFrmae.add(previus);
		northFrmae.add(main);
	return northFrmae;
	}
	
	
	private JPanel nextPageCenter() {
		JPanel centerFrmae = new JPanel(new BorderLayout());
		//-----------------------------North-------------------------------------
		ImageIcon dayImage = new ImageIcon("Images/day.png");
		JLabel frameNorth = new JLabel("D-day", dayImage, SwingConstants.CENTER);
		centerFrmae.add(frameNorth, BorderLayout.NORTH);
	
		//-----------------------------Center--------------------------------
		JPanel centerPanel = new JPanel(new GridLayout(6, 1, 5, 5));
		ImageIcon listImage = new ImageIcon("Images/list.png");
		JLabel centerNorth = new JLabel("D-day all List", listImage, SwingConstants.CENTER);
		centerPanel.add(centerNorth);
		
		JPanel[] d_DayPanel = new JPanel[5];
		for(int i=0; i<5; i++) {
			d_DayPanel[i] = new JPanel(new GridLayout(1, 5, 5, 5));
		}
		
		
		centerFrmae.add(centerPanel, BorderLayout.CENTER);
		// --------------------south------------------------------------------
		JPanel southFrmae = new JPanel(new BorderLayout());
		JPanel southPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		JPanel datePanel = new JPanel(new GridLayout(1, 3, 5, 5));
		
		yearText = new JTextField("year");  
		monthText = new JTextField("month");  
		dayText = new JTextField("day");
		datePanel.add(yearText);
		datePanel.add(monthText);
		datePanel.add(dayText);
		southPanel.add(datePanel);
		
		scheduleText = new JTextField("schedule");
		southPanel.add(scheduleText);
		
		JButton save = new JButton("save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(false) {
					//경고문구 띄우기(입력이 bad)
				}
				else if(d_dayCounter < 5) {
					getDateSave();
					getScheduleSave();
					d_dayCounter++;
					moveToCenter();
				}
				else {
					//counter > 5, 경고문구 띄우기
				}
			}
		});
		
		southFrmae.add(southPanel, BorderLayout.CENTER);
		southFrmae.add(save, BorderLayout.SOUTH);
		
		centerFrmae.add(southFrmae, BorderLayout.SOUTH);
		return centerFrmae;
	}
	
	private long moveToCenter() {
		LocalDate inputDate = getDateSave();
		long dday = ChronoUnit.DAYS.between(today, inputDate);
		System.out.println("두 날짜 사이의 일수 차이: " + dday);
		return dday;
	}
	
	 private LocalDate getDateSave() {
		 saveYear = Integer.valueOf(yearText.getText());
		 saveMonth = Integer.valueOf(monthText.getText());
		 saveDay = Integer.valueOf(dayText.getText());
		 return LocalDate.of(saveYear, saveMonth, saveDay);
	 }
	 
	 private String getScheduleSave() {
		 saveSchedule = scheduleText.getText();
		 return saveSchedule;
	 }
	 
	public static void main(String[] args) {
		new next_page();
	}
}

