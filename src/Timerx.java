import com.mysql.cj.exceptions.StreamingNotifiable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.spec.ECField;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Timer;
import java.util.Date;

public class Timerx extends JFrame{
    private JPanel panel;
    private JLabel title;

    private int timeRemaining;
    private Timer timer;
    private JLabel timeLabel;
    private JButton startButton,stopButton,setButton,finishButton;
    private JRadioButton pomodoro,shortbreak,longbreak;
    private int x,w;
    private JLabel work;
    private int totalTime;
    private String setWork;
    private ButtonGroup radioGroup;
    private String startTime,endTime;

    public Timerx(String username) {
        setTitle("Work Wave");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        JLabel heading = new JLabel("Timerx - A Focus");
        heading.setFont(new Font("Sans-serif", Font.BOLD, 21));
        heading.setForeground(Color.lightGray);
        heading.setBounds(25, 80, width / 2, 40);
        panel.add(heading);
//      --
        JSeparator line = new JSeparator();
        line.setBounds(25, 130, 175, 15);
        panel.add(line);

//      --
        Font g = new Font("Sans-serif",Font.PLAIN,18);

        work = new JLabel("enter the workID : ");
        work.setBounds(25,150,150,30);
        work.setFont(g);
        work.setForeground(Color.lightGray);
        panel.add(work);

        JTextField progressField = new JTextField();
        progressField.setFont(g);
        progressField.setForeground(Color.darkGray);
        progressField.setBounds(180,150,300,30);
        progressField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        panel.add(progressField);

        setButton = new JButton("SET");
        setButton.setBounds(525,150,100,30);
        setButton.setFont(g);
        setButton.setForeground(Color.lightGray);
        setButton.setBackground(new Color(1,24,43));
        panel.add(setButton);

        JLabel workField = new JLabel();
        workField.setFont(g);
        workField.setForeground(Color.lightGray);
        workField.setBounds(width / 8, 230, 300, 30);
        workField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        panel.add(workField);

        JLabel timeField = new JLabel();
        timeField.setFont(g);
        timeField.setForeground(Color.lightGray);
        timeField.setBounds(width / 8, 270, 300, 30);
        timeField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        panel.add(timeField);

        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    w = Integer.parseInt(progressField.getText());
                    ResultSet result = new Conn().statement.executeQuery("select * from work_table where workID = '"+w+"' AND username = '"+username+"'");
                    if(result.next())
                    totalTime = result.getInt("time");
                    setWork = result.getString("work");
                    workField.setText("-> "+setWork);
                    timeField.setText(" Total Time : "+ totalTime +" minutes");

                    startTime = ""+new Date();
//                    System.out.println(startTime);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"set the work please.");
                    ex.printStackTrace();
                }
            }
        });

//      --
        JMenuBar menu = new JMenuBar();
        menu.setBounds(25,190,width/2-150,30);
        menu.setBackground(new Color(1,24,43));

        JMenu m1 = new JMenu("Do you want to know the workID of a work you want to set?");
        m1.setForeground(Color.lightGray);
        menu.add(m1);
//      --
        JMenuItem i1 = new JMenuItem("Know WorkID");
        m1.add(i1);

        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckWorkList(username).setVisible(true);
            }
        });

        panel.add(menu);
//      --
        pomodoro = new JRadioButton("Pomodoro");
        pomodoro.setBounds(90+15,340,150,30);
        pomodoro.setBackground(Color.WHITE);
        pomodoro.setForeground(Color.darkGray);
        pomodoro.setFont(g);
        panel.add(pomodoro);

        shortbreak = new JRadioButton("Short Break");
        shortbreak.setBounds(290+15,340,150,30);
        shortbreak.setBackground(Color.WHITE);
        shortbreak.setForeground(Color.darkGray);
        shortbreak.setFont(g);
        panel.add(shortbreak);

        longbreak = new JRadioButton("Long Break");
        longbreak.setBounds(490+15,340,150,30);
        longbreak.setBackground(Color.WHITE);
        longbreak.setForeground(Color.darkGray);
        longbreak.setFont(g);
        panel.add(longbreak);

//      --
        radioGroup = new ButtonGroup();
        radioGroup.add(pomodoro);
        radioGroup.add(shortbreak);
        radioGroup.add(longbreak);

//      -- Timer --
        timeRemaining = 2 * 60; // 35-minute work session -- 1500 seconds = 25 minutes | for demo -> 2mins
        timer = new Timer(1000, new Timerx.TimerListener()); // Timer is an in-built class | 1000 delay means 1 sec

        timeLabel = new JLabel();
        timeLabel.setBounds(width/4-80,420,160,60);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 58));
        timeLabel.setForeground(Color.lightGray);
        timeLabel.setBackground(new Color(1,24,43));
        panel.add(timeLabel);
//      -- method here --
        updateLabel();

        // start button
        startButton = new JButton("START");
        startButton.setBounds(width/4-80,500,150,30);
        startButton.setFont(g);
        startButton.setForeground(Color.lightGray);
        startButton.setBackground(new Color(1,24,43));
        panel.add(startButton);
        startButton.addActionListener(new StartListener());
        startButton.setVisible(true);

        // stop button
        stopButton = new JButton("STOP");
        stopButton.setBounds(width/4-80,500,150,30);
        stopButton.setFont(g);
        stopButton.setForeground(Color.lightGray);
        stopButton.setBackground(new Color(1,24,43));
        panel.add(stopButton);
        stopButton.addActionListener(new StopListener());
        stopButton.setVisible(false);

        // Finish Button
        finishButton = new JButton("FINISH");
        finishButton.setBounds(width/4-80,550,150,30);
        finishButton.setFont(g);
        finishButton.setForeground(Color.lightGray);
        finishButton.setBackground(new Color(1,24,43));
        panel.add(finishButton);

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimer();
                endTime = "" + new Date();

                try{
                    String query = "insert into timerx_report values('"+username+"','"+w+"','"+setWork+"','"+startTime+"','"+endTime+"')";
                    new Conn().statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Great Job! You completed your job, check your Timerx Report from below.");
                }
                catch (Exception abc){
//                    --
                }

            }
        });

//      --

        JMenuBar menu2 = new JMenuBar();
        menu2.setBounds(25,620,width/2-150,40);
        menu2.setBackground(new Color(1,24,43));

        JMenu m2 = new JMenu("Move back to Main Page.");
        m2.setForeground(Color.lightGray);
        menu2.add(m2);
//      --
        JMenuItem i2 = new JMenuItem("Back");
        m2.add(i2);

        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Main(username).setVisible(true);
            }
        });

        JMenu m3 = new JMenu("Want to Update Progress?");
        m3.setForeground(Color.lightGray);
        menu2.add(m3);
//      --
        JMenuItem i3 = new JMenuItem("Update");
        m3.add(i3);

        i3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CheckWorkList(username).setVisible(true);
            }
        });

        JMenu m4 = new JMenu("Want to know features?");
        m4.setForeground(Color.lightGray);
        menu2.add(m4);
//      --
        JMenuItem i4 = new JMenuItem("User Guide");
        m4.add(i4);

        i4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserGuide().setVisible(true);
            }
        });

        JMenu m5 = new JMenu("Timerx Progress Report");
        m5.setForeground(Color.lightGray);
        menu2.add(m5);
//      --
        JMenuItem i5 = new JMenuItem("OPEN");
        m5.add(i5);

        i5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TimerxReport(username).setVisible(true);
            }
        });

        panel.add(menu2);

    }


    public static void main(String[] args) {
//      main
        new Timerx("viraj choubey").setVisible(true);
    }

//  -- other classes --
    private class TimerListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
            // action performed
            timeRemaining--;
            updateLabel();
            if(timeRemaining==20 && shortbreak.isSelected())
                JOptionPane.showMessageDialog(null,"Hey, are you done with your short break? if Yes then select the pomodoro as a option to get back to work!");
            else if(timeRemaining==20 && longbreak.isSelected())
                JOptionPane.showMessageDialog(null,"Hey, looks like you enjoyed your long break, lets get back to the work. Please chose pomodoro as option to begin your working session.");
            else if(timeRemaining==20 && pomodoro.isSelected())
                JOptionPane.showMessageDialog(null,"Do you want to take rest, if YES then reset the timer by selecting choices given in application.");
            else if(timeRemaining==20)
                JOptionPane.showMessageDialog(null,"Sorry to interrupt but can you please tell us whether you will like to continue work or want to have some break-time. You can do this by choosing one of the radio buttons, please do this!");
            if(timeRemaining==0){
                stopTimer();
                JOptionPane.showMessageDialog(null,"Great going, keep it up! (Re-Press the start button)");
                if(longbreak.isSelected())
                    x=2; // 10 minutes | demo -> 2 mins of long break
                else if(shortbreak.isSelected())
                    x=1;  // 5 minutes | demo -> 1 min of short break
                else
                    x=3; // 25 minutes | demo -> 3 mins of work time
                timeRemaining = x * 60;
                updateLabel();
            }
        }
    }

    private class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            startTimer();
        }
    }

    private class StopListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stopTimer();
        }
    }

//  -- needed methods --
    private void updateLabel(){
        int minutes = timeRemaining / 60;
        int seconds = timeRemaining % 60;
        String text = String.format("%02d:%02d", minutes, seconds);
        timeLabel.setText(text);
    }

    private void startTimer() {
        timer.start();
        startButton.setVisible(false);
        stopButton.setVisible(true);
    }

    private void stopTimer() {
        timer.stop();
        stopButton.setVisible(false);
        startButton.setVisible(true);
    }

}
