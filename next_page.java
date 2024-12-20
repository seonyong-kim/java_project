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
import java.util.Queue;
import java.util.LinkedList;

public class next_page extends JFrame{
	private Container contentPane;
	private int ddayYear = 0, ddayMonth = 0, ddayDay = 0, dayOfMonth = 0;
	private JTextField yearText, monthText, dayText, scheduleText;
	private int saveYear, saveMonth, saveDay;
	private String saveSchedule;
	//private int d_dayCounter = 0;
	private LocalDate today = LocalDate.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private int nowYear = today.getYear(), nowMonth = today.getMonthValue(), nowDay = today.getDayOfMonth();
	private int year, month, day;
	private Queue<JPanel> dDayQueue = new LinkedList<>();
	private JPanel centerFrmae = new JPanel(new BorderLayout());
	private JPanel fiveFiveMatrix = new JPanel(new GridLayout(5, 5, 5, 0));
	
	next_page(){
		super("next_page");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    contentPane = getContentPane();
	    
	    contentPane.add(nextPageNorth(), BorderLayout.NORTH);
	    contentPane.add(centerFrmae, BorderLayout.CENTER);
	    contentPane.add(nextPageSouth(), BorderLayout.SOUTH);
	    setSize(600,600);
	    setResizable(false);
	    setVisible(true);
	}
	
	
	private JPanel nextPageNorth() {		
		JPanel northFrmae = new JPanel(new GridLayout(2, 1 , 5, 5));
		JPanel notrhPanelButton = new JPanel(new GridLayout(1, 2, 5, 5));
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

		notrhPanelButton.add(previus);
		notrhPanelButton.add(main);
		
		JPanel notrhPanelText = new JPanel(new GridLayout(2, 1, 5, 5));
		ImageIcon dayImage = new ImageIcon("Images/day.png");
		JLabel frameNorth = new JLabel("D-day", dayImage, SwingConstants.CENTER);
		notrhPanelText.add(frameNorth);
		if(dDayQueue.peek() != null) {
			notrhPanelText.add(dDayQueue.peek());
		}
		
		northFrmae.add(notrhPanelButton);
		northFrmae.add(notrhPanelText);
	return northFrmae;
	}
	
	
	private JPanel nextPageCenter() {
		//GPT 참고부분
	    JPanel d_Day = new JPanel(new GridLayout(1, 5, 5, 0));

	    long dDay = moveToCenter();
	    JLabel dDayLabel = new JLabel(String.valueOf(dDay));
	    d_Day.add(dDayLabel);
	    System.out.println(dDay);
	    
	    LocalDate date = getDateSave();
	    String day = date.format(formatter);
	    JLabel dayLabel = new JLabel(day);
	    d_Day.add(dayLabel);
	    System.out.println(day);
	    
	    String text = getScheduleSave();
	    JLabel textLabel = new JLabel(text);
	    d_Day.add(textLabel);
	    System.out.println(text);
	    
	    JButton more = new JButton("more");
	    more.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            new DdayMore();
	        }
	    });
	    d_Day.add(more);
	    System.out.println("more");

	    JButton delete = new JButton("delete");
	    delete.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dDayQueue.remove(d_Day); // 큐에서 제거
	            centerFrmae.remove(d_Day); // UI에서 제거
	            centerFrmae.revalidate(); // 레이아웃 갱신
	            centerFrmae.repaint(); // 화면 갱신
	        }
	    });
	    d_Day.add(delete);

	    return d_Day;
		//GPT 참고부분
	}
	
	
	private JPanel nextPageSouth() {
		JPanel southFrmae = new JPanel(new BorderLayout());
		JPanel southPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		JPanel datePanel = new JPanel(new GridLayout(1, 3, 5, 5));
		
		yearText = new JTextField("year");  
		monthText = new JTextField("month");  
		dayText = new JTextField("day");
		datePanel.add(yearText);
		datePanel.add(monthText);
		datePanel.add(dayText);
		southPanel.add(datePanel, BorderLayout.NORTH);
		
		scheduleText = new JTextField("schedule");
		southPanel.add(scheduleText);
		
		southFrmae.add(southPanel, BorderLayout.CENTER);
		
		JButton save = new JButton("save");
		JPanel d_Day = new JPanel();
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(false) {
					//경고문구 띄우기(입력이 bad)
				}
				else if(dDayQueue.size() <= 5) {//counter < 5;
					//GPT 참고부분
		            JPanel d_Day = nextPageCenter(); // 새로운 D-day 패널 생성
		            dDayQueue.add(d_Day);// 큐에추가
		            
		            centerFrmae.removeAll();
		            for (JPanel panel : dDayQueue) {
		            	fiveFiveMatrix.add(panel,BorderLayout.CENTER);
		            }
		            centerFrmae.add(fiveFiveMatrix, BorderLayout.CENTER);
		            centerFrmae.revalidate(); // 레이아웃 갱신
		            centerFrmae.repaint(); // 화면 갱신
		    		//GPT 참고부분
				}
				else {
					//counter > 5, 경고문구 띄우기
				}
			}
		});
		
		southFrmae.add(save, BorderLayout.SOUTH);
		
		return southFrmae;
	}
	
	
	private long moveToCenter() {
		LocalDate inputDate = getDateSave();
		long dday = ChronoUnit.DAYS.between(today,inputDate);
		System.out.println("두 날짜 사이의 일수 차이: " + dday);
		return dday;
	}
	
	 private LocalDate getDateSave() {
		 saveYear = Integer.valueOf(yearText.getText());
		 saveMonth = Integer.valueOf(monthText.getText());
		 saveDay = Integer.valueOf(dayText.getText());
		System.out.println("Year:"  + saveYear + " Month:" + saveMonth + " Day:" + saveDay);
		 return LocalDate.of(saveYear, saveMonth, saveDay);
	 }
	 
	 private String getScheduleSave() {
		 saveSchedule = scheduleText.getText();
		System.out.println("saveSchedule:"  + saveSchedule);
		 return saveSchedule;
	 }
	 
	 
	public static void main(String[] args) {
		new next_page();
	}
}

