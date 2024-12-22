package mypage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DdayMore extends JFrame{
   private Container contentPane;
   private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
   private long dDay;
   private String schedule;
   private LocalDate date;
   private JTextArea More = new JTextArea();
   private JScrollPane MoreScroll = new JScrollPane(More);
   private boolean dateIsString = false;
   private String stringDate;
	
   public DdayMore(long dDay, String schedule, LocalDate date) {
	   super("DdayMore"); // JFrame의 생성자를 호출
	   contentPane = getContentPane();
	   contentPane.setLayout(new BorderLayout());
	   this.dDay = dDay;
	   this.schedule = schedule;
	   this.date = date;
	   createToolBar();	
	   MoreText();
	   setSize(400, 400);
	   setVisible(true);
	}

   public DdayMore(String dDay, String schedule, String date) {
	   super("DdayMore"); // JFrame의 생성자를 호출
	   contentPane = getContentPane();
	   contentPane.setLayout(new BorderLayout());
	   this.dDay = Long.parseLong(dDay);
	   this.schedule = schedule;
	   stringDate = date;
	   dateIsString = true;
	   createToolBar();	
	   MoreText();
	   setSize(400, 400);
	   setVisible(true);
	}
   
   private void createToolBar() {
	   JToolBar toolBar = new JToolBar("KitaeMenu");
	   toolBar.setBackground(Color.LIGHT_GRAY);
	   
	   toolBar.add(new JLabel("D-Day : " + dDay));
	   toolBar.addSeparator();
	   if(dateIsString) {
		   toolBar.add(new JLabel("Date : " + dateIsString));
	   }else {
		   String day = date.format(formatter);
		   toolBar.add(new JLabel("Date : " + day));
	   }
	   toolBar.addSeparator();
	   toolBar.add(new JLabel("Shedule : " + schedule));
	   toolBar.addSeparator();
	   JButton moreSave = new JButton("Save");
	   moreSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File more = new File("images/" + date + ".txt");
				OutputStreamWriter writer;
				String text = More.getText();
				try {
					FileOutputStream fos = new FileOutputStream(more);
					writer = new OutputStreamWriter(fos, "UTF-8"); 
					writer.write(text); 
					writer.flush();
					System.out.println("Completed"); // 성공하면 save문구 띄우기
					new next_page();
			        setVisible(false);
				} catch (IOException e1) {
					e1.printStackTrace(); // 실패시 에러메시지를 출력한다.
				} 
			}
		});
	   toolBar.add(moreSave);
	   contentPane.add(toolBar, BorderLayout.NORTH);
	   toolBar.setFloatable(false);
	   }
   
   private void MoreText() {
	   File path = new File("images/" + date + ".txt");
	   int c;
	   String memory = "";
	   if(path.exists()) {
			try {
				 FileInputStream today = new  FileInputStream (path);
				 InputStreamReader reader = new InputStreamReader(today, "UTF-8"); 
				 while ((c = reader.read()) != -1) { 
					 memory += (char)c;
					 } 
				 
				reader.close();
				today.close();
				More.append(memory);
				MoreScroll = new JScrollPane(More);
			}
			catch(IOException e) {
			}
		}else {
			 System.out.println("Completed1");
		}
		contentPane.add(MoreScroll, BorderLayout.CENTER);
	}
   
   public static void main(String[] args) {
      //new DdayMore(dDay schedule, date));
   }
}

