package mypage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;

public class Calendar extends JFrame{
	private JPanel calendarNorth = new JPanel();
	private JPanel calendarCenter = new JPanel(new BorderLayout());
	private JPanel dayTitle = new JPanel(new GridLayout(1,7));
	private String dayTitleStr[] = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
	private JPanel diaryDate = new JPanel(new GridLayout(0,7));
	private int year, month, day;
	private int todayYear, todayMonth;
	private JButton[] dayButton;
	
	Calendar(){
		super("Calendar");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		LocalDate TodayDate = LocalDate.now();
		year = TodayDate.getYear(); todayYear = year;
		month = TodayDate.getMonthValue(); todayMonth = month;
		day = TodayDate.getDayOfMonth();
		
		for(int i=0; i<dayTitleStr.length; i++) {
			JLabel diaryList = new JLabel(dayTitleStr[i], JLabel.CENTER);
			dayTitle.add(diaryList);
		}
		
		JButton previusButton = new JButton("<");
		JButton nextButton = new JButton(">");
		JLabel blank1 = new JLabel("     ");
		JLabel blank2 = new JLabel("     ");
		JLabel blank3 = new JLabel("     ");
		JButton goMain = new JButton("main");
		JLabel diaryYear = new JLabel(String.valueOf(year) + "  - ");
		JLabel diaryMonth = new JLabel(String.valueOf(month));
		calendarNorth.add(previusButton);
		calendarNorth.add(blank1);
		calendarNorth.add(diaryYear);
		calendarNorth.add(diaryMonth);
		calendarNorth.add(blank2);
		calendarNorth.add(nextButton);
		calendarNorth.add(blank3);
		calendarNorth.add(goMain);
		add(BorderLayout.NORTH, calendarNorth);
		
		dateChange();
		calendarCenter.add(dayTitle,"North");
		calendarCenter.add(diaryDate);
		add(BorderLayout.CENTER, calendarCenter);
		setSize(400,400);
	    setResizable(false);
		setVisible(true);
		
		previusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(month > 1) {
					month--;
					System.out.println(month);
					diaryMonth.setText(String.valueOf(month));
					dateChange();
				}
				else {
					month = 12;
					year--;
					diaryMonth.setText(String.valueOf(month));
					diaryYear.setText(String.valueOf(year) + "  - ");
					dateChange();
				}
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(year == todayYear && todayMonth == month) {
					 new NextPage();
					 dispose();
				}
				else{
					if(month <= 11) {
						month++;
						diaryMonth.setText(String.valueOf(month)); 
						dateChange();
					}
					else {
						month = 1;
						year++;
						diaryMonth.setText(String.valueOf(month));
						diaryYear.setText(String.valueOf(year)+ "  - ");
						dateChange();
					}
				}
			}
		});
		
		goMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FirstMain();
				dispose();
			}
		});
		
	}
	
	public void dateChange() {
		diaryDate.removeAll();
		LocalDate firstDayOfMonth = LocalDate.of(year,month,1);
		int firstDay = firstDayOfMonth.getDayOfWeek().getValue();
		
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());
        int lastDay = lastDayOfMonth.getDayOfMonth();
        
        for (int i = 0; i < firstDay -1 ; i++) {
        	diaryDate.add(new JLabel(" "));
        }
        dayButton = new JButton[lastDay];
		for (day = 1; day <= lastDay; day++) {
			final int selectedDay = day;
			if(month == todayMonth && day > LocalDate.now().getDayOfMonth()) {
				dayButton[day-1] = new JButton(String.valueOf(day));
				dayButton[day-1].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Sorry, It's a future, you can open a time capsule that day", "Message", JOptionPane.ERROR_MESSAGE );
					}
				});		
				
				File path = new File("images/" + year + "-" + month + "-" + day + ".txt");
				if(path.exists()) {
					dayButton[day - 1].setForeground(Color.GREEN);
				}
			}
			else if(month == todayMonth && day == LocalDate.now().getDayOfMonth()) {
				dayButton[day-1] = new JButton(String.valueOf(day));
				dayButton[day-1].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Let's move on to today's diary.", "Message", JOptionPane.INFORMATION_MESSAGE);
						new Diary(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(selectedDay));
					}
				});
				
				dayButton[day - 1].setForeground(Color.ORANGE);
			}
			else {
				dayButton[day-1] = new JButton(String.valueOf(day));
				dayButton[day-1].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Let's move on to " + String.valueOf(year) + "-" + String.valueOf(month) + "-" +
					String.valueOf(selectedDay) +" diary.", "Message", JOptionPane.INFORMATION_MESSAGE);
						new Diary(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(selectedDay));
					}
				});
				
				File path = new File("images/" + year + "-" + month + "-" + day + ".txt");
				if(path.exists()) {
					dayButton[day - 1].setForeground(Color.BLUE);
				}
			}
			dayButton[day-1].setContentAreaFilled(false);  
			diaryDate.add(dayButton[day-1]);
		}
		diaryDate.revalidate();
		diaryDate.repaint();
	}
	
	public static void main(String[] args) {
		new Calendar();
	}
}
