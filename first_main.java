package mypage;
import javax.swing.*;
import java.awt.*;

public class first_main extends JFrame{
   private Container contentPane;
   public first_main() {
      super("main화면");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      contentPane = getContentPane();
      createMenu();
      createToolBar();
      setSize(400,400);
      setVisible(true);
   }
   private void createMenu() {
      JMenuBar mainMenuBar = new JMenuBar();
      JMenu mainMenu = new JMenu("Screen");
      
   }
   private void createToolBar() {
      JToolBar toolBar = new JToolBar("Kitae Menu");
      toolBar.setBackground(Color.LIGHT_GRAY);
      String[] mainToolbar = {"이전 page로", "지금", "다음 page로"};
      for(int i=0;i<3;i++) {
         toolBar.add(new JButton(mainToolbar[i]));
         toolBar.addSeparator();
      }// 일단 추가로 해야할점 이 3개로 툴바를 가득차게 만들고 싶다. 
      
      contentPane.add(toolBar, BorderLayout.SOUTH);   
   }
   public static void main(String[] args) {
      new first_main();
   }
}
