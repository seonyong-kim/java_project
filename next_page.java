package mypage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class next_page extends JFrame{
	private Container contentPane;
	private int ddayYear = 0, ddayMonth = 0, ddayDay = 0, dayOfMonth = 0;
	private JTextField yearText, monthText, dayText, scheduleText;
	private int saveYear, saveMonth, saveDay;
	private String saveSchedule;
	
	next_page(){
		super("next_page");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    contentPane = getContentPane();
	    
	    //contentPane.add(nextPageNorth(), BorderLayout.NORTH);
	    contentPane.add(nextPageWest(), BorderLayout.WEST);
	    contentPane.add(nextPageEast(), BorderLayout.EAST);
	    setSize(600,600);
	    setVisible(true);
	}
	//private JPanel nextPageNorth() {
		
	//	return 
	//}
	private JPanel nextPageWest() {
		ImageIcon dayImage = new ImageIcon("Images/day.png");
		JPanel westFrmae = new JPanel(new BorderLayout());
		JLabel frameNorth = new JLabel("D-day", dayImage, SwingConstants.CENTER);
		westFrmae.add(frameNorth, BorderLayout.NORTH);
		
		
		westFrmae.add(new JLabel("D-day"), BorderLayout.CENTER);
		
		// --------------------south---------------------------------//
		JPanel southPanel = new JPanel(new GridLayout(2, 2));
		yearText = new JTextField("year");  monthText = new JTextField("month");  dayText = new JTextField("day");
		southPanel.add(yearText);
		southPanel.add(monthText);
		southPanel.add(dayText);
		scheduleText = new JTextField("schedule");
		southPanel.add(scheduleText);
		JButton save = new JButton("save");
		southPanel.add(save);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveTextToVariable();
			}
		});
		westFrmae.add(southPanel, BorderLayout.SOUTH);
		return westFrmae;
	}
	
	 private void saveTextToVariable() {
		 saveYear = Integer.valueOf(yearText.getText());
		 saveMonth = Integer.valueOf(monthText.getText());
		 saveDay = Integer.valueOf(dayText.getText());
		 saveSchedule = scheduleText.getText();
	 }
	
	private JPanel nextPageEast() {
		JPanel eastFrmae = new JPanel();
		
		
		return eastFrmae;
	}
	
	public static void main(String[] args) {
		new next_page();
	}
}

