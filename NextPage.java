package mypage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class NextPage extends JFrame{
	private Container contentPane;
	private JTextField yearText, monthText, dayText;
	private int saveYear, saveMonth, saveDay;
	private LocalDate today = LocalDate.now();
	private LocalDate saveDate;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private String Date = today.format(formatter);
	private JTextArea nextPageDiary = new JTextArea();
	private JScrollPane diaryScroll = new JScrollPane(nextPageDiary);
	
	NextPage(){
		super("NextPage");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    contentPane = getContentPane();
	    contentPane.add(NextPageNorth(), BorderLayout.NORTH);
	    contentPane.add(NextPageCenter(), BorderLayout.CENTER);
	    contentPane.add(NextPageSouth(), BorderLayout.SOUTH);
	    
	    setSize(600,600);
	    setResizable(false);
	    setVisible(true);
	}
	
	private JPanel NextPageNorth() {
		JPanel northFrmae = new JPanel(new GridLayout(2, 1 , 5, 5));
		
		JToolBar notrhPanelButton = new JToolBar();
		notrhPanelButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton previous = new JButton("previous");

		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Calendar();
		        setVisible(false);
			}
		});
		previous.setPreferredSize(new Dimension(70, 40));
		
		JButton main = new JButton("main");
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FirstMain();
		        setVisible(false);
			}
		});
		main.setPreferredSize(new Dimension(70, 40));
		
		JButton save = new JButton("save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveDate = GetDateSave();
				String timeCapsuleDate = saveDate.format(formatter);
				File diary = new File("images/" + timeCapsuleDate + ".txt");
				OutputStreamWriter writer; 
				JTextArea textArea = (JTextArea) diaryScroll.getViewport().getView();
				String text = textArea.getText();
				if(GetDday() >0) {
					try {
						FileOutputStream fos = new FileOutputStream(diary);
						writer = new OutputStreamWriter(fos, "UTF-8"); 
						writer.write(text); 
						writer.flush();
						writer.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					} 
					JOptionPane.showMessageDialog(null, "Your time capsule has been saved.", 
							"Message", JOptionPane. INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "This is not a future date.", 
							"Message", JOptionPane. ERROR_MESSAGE);
				}
			}
		});
		save.setPreferredSize(new Dimension(70, 40));
		
		notrhPanelButton.add(save);
		notrhPanelButton.add(previous);
		notrhPanelButton.add(main);
		notrhPanelButton.setFloatable(false);
		
		JPanel notrhPanelText = new JPanel(new GridLayout(1, 1, 5, 5));
		JLabel frameNorth = new JLabel("Time Capsule", SwingConstants.CENTER);
		notrhPanelText.add(frameNorth);
		
		northFrmae.add(notrhPanelButton);
		northFrmae.add(notrhPanelText);
		
		return northFrmae;
	}
	
	private JPanel NextPageSouth() {
		JPanel centerFrmae = new JPanel(new GridLayout(3, 1, 5, 5));
		JPanel datePanel = new JPanel(new GridLayout(1, 3, 5, 5));
		
		yearText = new JTextField(String.valueOf(today.getYear()));  
		monthText = new JTextField(String.valueOf(today.getMonthValue()));  
		dayText = new JTextField(String.valueOf(today.getDayOfMonth()));
		datePanel.add(yearText);
		datePanel.add(monthText);
		datePanel.add(dayText);
		centerFrmae.add(datePanel);
		
		JLabel Dday = new JLabel("D-day : " + String.valueOf(GetDday()));
		centerFrmae.add(Dday);
		JButton Calculation = new JButton("Calculation");
		Calculation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GetDday() < 0) {
					JOptionPane.showMessageDialog(null, "The value entered is before today's date.", 
							"Message", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Dday.setText("D-day : " + String.valueOf(GetDday()));
				}
			}
		});
		centerFrmae.add(Calculation);
		
		return centerFrmae;
	}
	
	private JScrollPane NextPageCenter() {
			return diaryScroll;
	}
	
	public long GetDday() {
		long dday = ChronoUnit.DAYS.between(today,GetDateSave());
		return dday;
	}
	
	 public LocalDate GetDateSave() {
		 try {
			 saveYear = Integer.valueOf(yearText.getText());
			 saveMonth = Integer.valueOf(monthText.getText());
			 saveDay = Integer.valueOf(dayText.getText());
			 return LocalDate.of(saveYear, saveMonth, saveDay);
		 }
		 catch(NumberFormatException e) {
			 JOptionPane.showMessageDialog(null, "The value entered is not a number", "Message", JOptionPane.ERROR_MESSAGE);
		 }
		 catch (IllegalArgumentException e) {
			 JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		 }
		 catch (DateTimeException e) {
			 JOptionPane.showMessageDialog(null, "The date entered is invalid.", "Input Error", JOptionPane.ERROR_MESSAGE);
			 }
		 return null;
	 }
	 
	public static void main(String[] args) {
		new NextPage();
	}
}

