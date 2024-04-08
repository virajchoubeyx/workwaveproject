import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserGuide extends JFrame{
    private JPanel panel;
    private JLabel title;

    private JTextArea para;

    public UserGuide() {
        setTitle("Work Wave");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int width = screen.width;
        int height = screen.height;

        setBounds(width / 4, 15, width / 2, height - 80);
//      -- Panel --
        panel = new JPanel(null);
        panel.setSize(width / 2, height - 100);
        ;
        panel.setBackground(new Color(1, 24, 43));
        add(panel);
//      --

        title = new JLabel("WORK WAVE");
        title.setBounds(15, 0, 200, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Sans-serif", Font.PLAIN, 21));
        panel.add(title);
//      --

        JLabel heading = new JLabel("USER-GUIDE");
        heading.setFont(new Font("Sans-serif",Font.BOLD,21));
        heading.setForeground(Color.lightGray);
        heading.setBounds(150,80,width/2,40);
        panel.add(heading);
//      --
        JSeparator line = new JSeparator();
        line.setBounds(150,130,450,15);
        panel.add(line);

//      --
        para = new JTextArea("1- Launch \"Timerx\".\n" +
                "2- The default timer is 35 minutes, which is the length of the working session.\n" +
                "3- Once the session ends, the application will prompt you to choose from three options:\n" +
                "    a. Pomodoro timer (25 minutes)\n" +
                "    b. Short break timer (5 minutes)\n" +
                "    c. Long break timer (10 minutes)\n" +
                "4- Choose the appropriate option based on your current task and mood.\n" +
                "5- The timer will start counting down, and you can begin your next work or break session.\n" +
                "6- One minute before the session ends, the application will notify you with a sound or a pop-up notification.\n" +
                "7- Take your break or continue working based on your choice.\n" +
                "8- Repeat the cycle until you complete your work or reach your goal for the day.\n" +
                "9- You can pause or stop the timer anytime during the session.\n" +
                "10- You can also reset the timer by clicking on the \"Reset\" button.\n" +
                "\nEnjoy a productive day with \"Timerx\"!");
        para.setLineWrap(true);
        para.setWrapStyleWord(true);
        para.setBounds(150,150,width/4,height/2+30);
        para.setFont(new Font("Sans-serif",Font.PLAIN,14));
        para.setForeground(Color.lightGray);
        para.setBackground(new Color(1,24,43));
        para.setEditable(false);
        panel.add(para);

        Font g = new Font("Sans-serif",Font.PLAIN,18);

        JButton dis = new JButton("DISPOSE");
        dis.setBounds(150,height/2+200,150,30);
        dis.setFont(g);
        dis.setForeground(Color.lightGray);
        dis.setBackground(new Color(1,24,43));
        panel.add(dis);

        dis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        new UserGuide().setVisible(true);
    }

}
