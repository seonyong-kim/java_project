package mypage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mypage.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Calendar extends JFrame{
	private JPanel calendarNorth = new JPanel();
	private JPanel calendarCenter = new JPanel(new BorderLayout());
	private JPanel dayTitle = new JPanel(new GridLayout(1,7));
	private String dayTitleStr[] = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
	private JPanel diaryDate = new JPanel(new GridLayout(0,7));
	private int year, month, day;
	private int todayYear, todayMonth;
	private JButton[] lbl;
	
	Calendar(){
		super("Calendar");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
					diaryMonth.setText(String.valueOf(month)); //JLabel 변경하기 참고
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
					 new next_page();
			         setVisible(false);
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
				new first_main();
    			setVisible(false);
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
        lbl = new JButton[lastDay];
		for (day = 1; day <= lastDay; day++) {
			final int selectedDay = day;
			lbl[day-1] = new JButton(String.valueOf(day));
			lbl[day-1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	    			new diary(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(selectedDay));
	    			setVisible(false);
	    	        return ;
				}
			});
			diaryDate.add(lbl[day-1]);
		}
		diaryDate.revalidate();
		diaryDate.repaint();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Calendar();
	}
}
