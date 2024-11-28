package mypage;
import javax.swing.*;
import java.awt.*;

public class next_page extends JFrame{
	private Container contentPane;
	next_page(){
		super("next_page");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    contentPane = getContentPane();
	    setSize(400,400);
	    setVisible(true);
	}
	public static void main(String[] args) {
		new next_page();
	}
}
