package mypage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class first_main extends JFrame{
   private Container contentPane;
   private Mypanel panel = new Mypanel();
   public first_main() {
      super("main");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setContentPane(panel);
      contentPane = getContentPane();
	  contentPane.setLayout(new BorderLayout(10,10));
	  contentPane.add(ButtomBar(), BorderLayout.SOUTH);
      setSize(400,400);
      setVisible(true);
   }
   
   class Mypanel extends JPanel {
	   private ImageIcon icon = new ImageIcon("images/diary.png");
	   private Image img= icon.getImage(); 
	   public void paintComponent(Graphics g) {
	   super.paintComponent(g);
	   g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	   }
   }
   
   private JPanel ButtomBar() {
       JPanel ButtomBar = new JPanel(new GridLayout(1, 3));
       JButton button1 = new JButton("Previous Page");
       JButton button2 = new JButton("Current Page");
       JButton button3 = new JButton("Next Page");
       
       button1.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
  			 new Calendar();
	         setVisible(false);
           }
       });
       
       button2.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   new diary();
    	       setVisible(false);
           }
       });

       button3.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   new next_page();
    	       setVisible(false);
           }
       });
       
       ButtomBar.add(button1);
       ButtomBar.add(button2);
       ButtomBar.add(button3);
       return ButtomBar;
   }
   
   public static void main(String[] args) {
      new first_main();
   }
}
