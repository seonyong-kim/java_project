package mypage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import mypage.*;

public class previus extends JFrame{
	private Container contentPane;
	previus(){
		super("previus");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    contentPane = getContentPane();
	    contentPane.setLayout(new BorderLayout(10,10));
	    contentPane.add(calendar(), BorderLayout.CENTER);
	    contentPane.add(NorthButton(), BorderLayout.NORTH);
	    contentPane.add(SouthButton(), BorderLayout.SOUTH);
	    setSize(800,800);
	    setVisible(true);
	}

	private JPanel NorthButton() {
        JPanel NorthButton = new JPanel(new GridLayout(1, 3));
        JButton button1 = new JButton("Last month");
        JButton button2 = new JButton("main");
        JButton button3 = new JButton("Next month");
        
        NorthButton.add(button1);
        NorthButton.add(button2);
        NorthButton.add(button3);
        
        button2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
     			 new first_main();
    	         setVisible(false);
        	}
        });
        return NorthButton;
	}
	
    private JPanel calendar() {
        JPanel calendars = new JPanel(new GridLayout(5, 7, 5, 5));

        for (int i = 0; i < 5; i++) {
            calendars.add(new JLabel(""));
        }
        for (int i = 1; i <= 30; i++) {
            calendars.add(new JButton(Integer.toString(i)));
        }
        return calendars;
    }
    
	private JPanel SouthButton() {
        JPanel SouthButton = new JPanel(new GridLayout(1, 3));
        SouthButton.add(new JButton("All diaries"));
        SouthButton.add(new JButton("All Photos"));
        SouthButton.add(new JButton("bookmark"));
        return SouthButton;
	}
	
	public static void main(String[] args) {
		new previus();
	}
	
}
