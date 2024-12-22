package mypage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter
;
import java.time.temporal.ChronoUnit;
import java.util.Queue;
import java.util.LinkedList;

public class next_page extends JFrame{
	private Container contentPane;
	private JTextField yearText, monthText, dayText, scheduleText;
	private int saveYear, saveMonth, saveDay;
	private String saveSchedule;
	private LocalDate today = LocalDate.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private int nowYear = today.getYear(), nowMonth = today.getMonthValue(), nowDay = today.getDayOfMonth();
	private int year, month, day;
	private Queue<JPanel> dDayQueue = new LinkedList<>();
	private JPanel centerFrmae = new JPanel(new BorderLayout());
	private JPanel fiveFiveMatrix = new JPanel(new GridLayout(5, 1));
	private JButton more = new JButton("more");
	private JButton delete = new JButton("delete");
	
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
	    LoadSaveDday();
	}
	
	private JPanel q() {
		JPanel Z = new JPanel(new GridLayout(2, 1));
		JPanel j = new JPanel(new GridLayout(1, 2));
		j.add(new JLabel("asd"));
		j.add(new JLabel("SDFAGDSF"));
		Z.add(j);
		JPanel j1 = new JPanel(new GridLayout(1, 2));
		j1.add(new JLabel("asd"));
		j1.add(new JLabel("SDFAGDSF"));
		Z.add(j1);
		return Z;
	}
	
	private JPanel nextPageNorth() {		
		JPanel northFrmae = new JPanel(new GridLayout(3, 1 , 5, 5));
		JToolBar notrhPanelButton = new JToolBar();
		notrhPanelButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JPanel notrhPanelJlabel = new JPanel(new GridLayout(1, 1, 5, 5));
		
		JButton previus = new JButton("previus");
		previus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Calendar();
		        setVisible(false);
			}
		});
		previus.setPreferredSize(new Dimension(70, 40));
		
		JButton main = new JButton("main");
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FirstMain();
		        setVisible(false);
			}
		});
		main.setPreferredSize(new Dimension(70, 40));
		
		notrhPanelButton.add(previus);
		notrhPanelButton.add(main);
		notrhPanelButton.setFloatable(false);
		
		JLabel dDayText = new JLabel("D-Day");
		notrhPanelJlabel.add(dDayText);
		JLabel dateText = new JLabel("Date");
		notrhPanelJlabel.add(dateText);
		JLabel sheduleText = new JLabel("Shedule");
		notrhPanelJlabel.add(sheduleText);
		JLabel moreText = new JLabel("More");
		notrhPanelJlabel.add(moreText);
		JLabel deleteText = new JLabel("deleteText");
		notrhPanelJlabel.add(deleteText);
		
		JPanel notrhPanelText = new JPanel(new GridLayout(2, 1, 5, 5));
		ImageIcon dayImage = new ImageIcon("Images/day.png");
		JLabel frameNorth = new JLabel("D-day", dayImage, SwingConstants.CENTER);
		notrhPanelText.add(frameNorth);
		if(dDayQueue.peek() != null) {
			notrhPanelText.add(dDayQueue.peek());
		}
		
		northFrmae.add(notrhPanelButton);
		northFrmae.add(notrhPanelText);
		northFrmae.add(notrhPanelJlabel);
	return northFrmae;
	}
	
	
	private JPanel nextPageCenter() {
		//GPT 참고부분
		//JButton more = new JButton("more");
		//JButton delete = new JButton("delete");
		
	    JPanel d_Day = new JPanel(new GridLayout(1, 5, 5, 0));

	    long dDay = moveToCenter();
	    JLabel dDayLabel = new JLabel(String.valueOf(dDay));
	    d_Day.add(dDayLabel);
	    
	    LocalDate date = getDateSave();
	    String day = date.format(formatter);
	    JLabel dayLabel = new JLabel(day);
	    d_Day.add(dayLabel);
	    
	    String text = getScheduleSave();
	    JLabel textLabel = new JLabel(text);
	    d_Day.add(textLabel);
	    
	    more.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            new DdayMore(dDay, text, date);
	            
		        setVisible(false);
	        }
	    });
	    d_Day.add(more);
	    System.out.println("more");

	    delete.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dDayQueue.remove(d_Day); // 큐에서 제거 삭제 확인
	        	centerFrmae.removeAll(); //화면에서 전부 지우기
	        	fiveFiveMatrix.removeAll(); //이거 초기화
	            for (JPanel panel : dDayQueue) {
	            	fiveFiveMatrix.add(panel,BorderLayout.CENTER);
	            }
	            centerFrmae.add(fiveFiveMatrix, BorderLayout.CENTER);
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
		
		yearText = new JTextField("2025");  
		monthText = new JTextField("5");  
		dayText = new JTextField("5");
		datePanel.add(yearText);
		datePanel.add(monthText);
		datePanel.add(dayText);
		southPanel.add(datePanel, BorderLayout.NORTH);
		
		scheduleText = new JTextField("schedule");
		southPanel.add(scheduleText);
		
		southFrmae.add(southPanel, BorderLayout.CENTER);
		
		JButton save = new JButton("save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(moveToCenter() < 0) {
					JOptionPane.showMessageDialog(null, "The value entered is before today's date.", "Message", JOptionPane.ERROR_MESSAGE);
				}
				else if(dDayQueue.size() < 5) {
					System.out.println("정상 실행");
					//GPT 참고부분
		            JPanel d_Day = nextPageCenter(); 
		            dDayQueue.add(d_Day);
		            
		            centerFrmae.removeAll();
		            for (JPanel panel : dDayQueue) {
		            	fiveFiveMatrix.add(panel,BorderLayout.CENTER);
		            }
		            centerFrmae.add(fiveFiveMatrix, BorderLayout.CENTER);
		            centerFrmae.revalidate(); 
		            centerFrmae.repaint(); 
		    		//GPT 참고부분
		            MoreText();
				}
				else {
					 JOptionPane.showMessageDialog(null, "입력이 5개를 초과했습니다!!" + dDayQueue.size(), "Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		southFrmae.add(save, BorderLayout.SOUTH);
		
		return southFrmae;
	}

	private void MoreText() {
		File more = new File("images/dDay.txt");
		try {
			FileWriter writer = new FileWriter(more, true); 
			writer.write(getDateSave() + " " + (int)moveToCenter() +  " " + getScheduleSave() + "\n");
			writer.flush();
			System.out.println("save:" + getDateSave()); 
			writer.close(); 
		} 
		catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "save error", "Message", JOptionPane.ERROR_MESSAGE);
		} 
	}
	   
	
	public long moveToCenter() {
		LocalDate inputDate = getDateSave();
		long dday = ChronoUnit.DAYS.between(today,inputDate);
		return dday;
	}
	
	private void LoadSaveDday() {
        JPanel d_Day = new JPanel(new GridLayout(1, 5, 5, 5));
	    for(String[] arr : FindDday()) {
	    	if(!(arr[0] == null)) {
	    		d_Day.add(new JLabel(arr[1]));
	    		d_Day.add(new JLabel(arr[0]));
	    		d_Day.add(new JLabel(arr[2]));
	    		
	    		JButton moreButton = new JButton("more");
	    		d_Day.add(moreButton);
	    	    more.addActionListener(new ActionListener() {
	    	        public void actionPerformed(ActionEvent e) {
	    	            new DdayMore(arr[1], arr[0], arr[2]);
	    	            
	    		        setVisible(false);
	    	        }
	    	    });
	    	    
	    	    JButton deleteButton = new JButton("delete");
	    	    d_Day.add(deleteButton);
	    	    delete.addActionListener(new ActionListener() {
	    	        public void actionPerformed(ActionEvent e) {
	    	        	dDayQueue.remove(d_Day); // 큐에서 제거 삭제 확인
	    	        	centerFrmae.removeAll(); //화면에서 전부 지우기
	    	        	fiveFiveMatrix.removeAll(); //이거 초기화
	    	            for (JPanel panel : dDayQueue) {
	    	            	fiveFiveMatrix.add(panel,BorderLayout.CENTER);
	    	            }
	    	            centerFrmae.add(fiveFiveMatrix);
	    	            centerFrmae.revalidate(); // 레이아웃 갱신
	    	            centerFrmae.repaint(); // 화면 갱신   
	    	        }
	    	    });
	    		dDayQueue.add(d_Day);
	    	    fiveFiveMatrix.add(d_Day);
	            centerFrmae.add(fiveFiveMatrix, BorderLayout.CENTER);
	            System.out.println(fiveFiveMatrix.getSize());
	            centerFrmae.revalidate(); 
	            centerFrmae.repaint(); 
	    	}
	    }
	}
	
	 public LocalDate getDateSave() {
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
	 
	 public String getScheduleSave() {
		 saveSchedule = scheduleText.getText();
		 return saveSchedule;
	 }
	 
	 private String[][] FindDday() {
		 File path = new File("images/dDay.txt");
		 String[][] ddayArray = new String[5][3];
		 int i = 0;
		 try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				 new FileInputStream(path), "UTF-8"))) {
			 String line;
		        while (!((line = reader.readLine()) == null) && i < 5) {
		            String[] parts = line.split(" ", 3); 
		            //if(line.equals("")) break;
		            if (parts.length > 0) {
		                ddayArray[i][0] = parts[0]; 
		            }
		            if (parts.length > 1) {
		                ddayArray[i][1] = parts[1]; 
		            }
		            if (parts.length > 2) {
		                ddayArray[i][2] = parts[2]; 
		            }
		            i++;
		        }
		    } catch (IOException e) {
		        e.printStackTrace(); 
		    }
		    return ddayArray;
	 }
	 
	public static void main(String[] args) {
		new next_page();
	}
}

