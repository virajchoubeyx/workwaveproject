import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;


// import statements
public class Main extends JFrame{

    private JPanel panel;       // -- Variables are private to protect the state of your objects - in object-oriented programming terms, this is
                                // called encapsulation.
    private JLabel title;
    private JTextField addWorkField;
    private JTextField timeFeild;
    private JTextField priorityFeild;
    private JButton addBtn;
    private JLabel ques,ques2;
    private JTextArea para,para2;
    private int work_id;

    private Conn connection;

//    --
    public Main(String username){
//        constructor
        setTitle("Work Wave");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int width = screen.width;
        int height = screen.height;

        setBounds(width/4,15,width/2,height-80);
//      -- Panel --
        panel = new JPanel(null);
        panel.setSize(width/2,height);;
        panel.setBackground(new Color(1,24,43));
        add(panel);
//      --

        title = new JLabel("WORK WAVE");
        title.setBounds(15,0,200,40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Sans-serif",Font.PLAIN,21));
        panel.add(title);
//      --

        JLabel hello = new JLabel("Hello "+username+ " :)");
        hello.setBounds(15,45,300,25);
        hello.setFont(new Font("Sans-serif",Font.PLAIN,18));
        hello.setForeground(Color.WHITE);
        panel.add(hello);

//      --
        JLabel addWork = new JLabel("Add your work here!");
        addWork.setBounds(15,80,width/4,25);
        addWork.setFont(new Font("Sans-serif",Font.PLAIN,18));
        addWork.setForeground(Color.WHITE);
        panel.add(addWork);
//      --
        addWorkField = new JTextField();
        addWorkField.setToolTipText("e.g. I want to do study...");
        addWorkField.replaceSelection("work: ");
        addWorkField.setFont(new Font("Sans-serif",Font.PLAIN,17));
        addWorkField.setForeground(Color.DARK_GRAY);
        addWorkField.setBounds(15,120,width/2-150,35);
        panel.add(addWorkField);

//      --
        JLabel time = new JLabel("How much time you want to dedicate in this work and what is the priority?");
        time.setBounds(15,170,width/2,25);
        time.setFont(new Font("Sans-serif",Font.PLAIN,18));
        time.setForeground(Color.WHITE);
        panel.add(time);
//      --
        timeFeild = new JTextField();
        timeFeild.setToolTipText("integer values only");
//        timeFeild.replaceSelection("enter time in minutes");
        JLabel label1 = new JLabel("enter time in minutes: ");
        label1.setBounds(15,210,135,35);
        label1.setForeground(Color.white);
        panel.add(label1);
        timeFeild.setFont(new Font("Sans-serif",Font.PLAIN,17));
        timeFeild.setForeground(Color.DARK_GRAY);
        timeFeild.setBounds(160,210,width/10,35);
        panel.add(timeFeild);
//      --
        priorityFeild = new JTextField();
        priorityFeild.setToolTipText("enter priority number (1-10)");
        JLabel label2 = new JLabel("enter priority: ");
        label2.setBounds(335,210,100,35);
        label2.setForeground(Color.white);
        panel.add(label2);
//        priorityFeild.replaceSelection("enter a number as priority");
        priorityFeild.setFont(new Font("Sans-serif",Font.PLAIN,17));
        priorityFeild.setForeground(Color.DARK_GRAY);
        priorityFeild.setBounds(450,210,width/10,35);
        panel.add(priorityFeild);

//      --
        addBtn = new JButton("ADD");
        addBtn.setBounds(15,265,120,40);
        addBtn.setFont(new Font("Sans-serif",Font.BOLD,19));
        addBtn.setBackground(Color.lightGray);
        addBtn.setForeground(new Color(1,24,43));
        panel.add(addBtn);

//      --
        JButton knowBtn = new JButton("Check WorkWave");
        knowBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckWorkList(username).setVisible(true);
            }
        });
        knowBtn.setBounds(15,330,180,25);
        knowBtn.setFont(new Font("Sans-serif",Font.BOLD,16));
        knowBtn.setBackground(Color.lightGray);
        knowBtn.setForeground(new Color(1,24,43));
        panel.add(knowBtn);
//      --
        JButton timerBtn = new JButton("Go To Timer");
        timerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Timerx(username).setVisible(true);
            }
        });
        timerBtn.setBounds(215,330,180,25);
        timerBtn.setFont(new Font("Sans-serif",Font.BOLD,16));
        timerBtn.setBackground(Color.lightGray);
        timerBtn.setForeground(new Color(1,24,43));
        panel.add(timerBtn);

//      --
        JButton profileBtn = new JButton("About Application");
        profileBtn.setBounds(415,330,180,25);
        profileBtn.setFont(new Font("Sans-serif",Font.BOLD,16));
        profileBtn.setBackground(Color.lightGray);
        profileBtn.setForeground(new Color(1,24,43));
        panel.add(profileBtn);

        profileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Profile(username).setVisible(true);
            }
        });

//      --
        ques = new JLabel("What is Pomodoro Technique?");
        ques.setBounds(15,420,width/2,30);
        ques.setForeground(Color.lightGray);
        ques.setFont(new Font("Sans-serif",Font.BOLD,16));
        panel.add(ques);
//      --
        para = new JTextArea("The Pomodoro Technique is a time management method that involves breaking work into " +
                "25  minute intervals called \"Pomodoros\" separated by short breaks. After four Pomodoros," +
                " take a longer break. " +
                "It aims to improve focus and productivity while reducing burnout.");
        para.setLineWrap(true);
        para.setWrapStyleWord(true);
        para.setBounds(15,465,width/3+150,70);
        para.setFont(new Font("Sans-serif",Font.PLAIN,16));
        para.setForeground(Color.lightGray);
        para.setBackground(new Color(1,24,43));
        para.setEditable(false);
        panel.add(para);
//      --
        ques2 = new JLabel("What is Work Wave?");
        ques2.setBounds(15,550,width/2,30);
        ques2.setForeground(Color.lightGray);
        ques2.setFont(new Font("Sans-serif",Font.BOLD,16));
        panel.add(ques2);
//      --
        para2 = new JTextArea("Work Wave is a productivity tool that combines goal setting, work planning, and Pomodoro" +
                "     timer techniques to help users increase their productivity and focus. It allows users to input" +
                "     their daily goals and work tasks, customize the timer duration and break intervals, receive" +
                "      motivational messages, and track progress. Work Wave is a user-friendly and effective tool" +
                "    for anyone looking to improve their productivity and stay focused on their goals.");


        para2.setLineWrap(true);
        para2.setWrapStyleWord(true);
        para2.setBounds(15,595,width/3+150,120);
        para2.setFont(new Font("Sans-serif",Font.PLAIN,16));
        para2.setForeground(Color.lightGray);
        para2.setBackground(new Color(1,24,43));
        para2.setEditable(false);
        panel.add(para2);
//      --

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==addBtn){
                    connection = new Conn();

                    String work = addWorkField.getText();
                    int time = 0;
                    int priority = 0;
                    try{
                        time = Integer.parseInt(timeFeild.getText());
                        priority = Integer.parseInt(priorityFeild.getText());
                    }catch(Exception xyz){
                        JOptionPane.showMessageDialog(null,"Only integer values are allowed!!");
                        return;
                    }
                    if(time<0){
                        JOptionPane.showMessageDialog(null,"time must be positive value only");
                        return;
                    }
                    if(priority<1 || priority>10){
                        JOptionPane.showMessageDialog(null, "priority should be in between 1-10 only");
                        return;
                    }
                    String user = username;
                    String progress = "working on it";

                    try{
                        try{
                        Random rand = new Random();
                        work_id =  Math.abs(rand.nextInt())%10000000;
                        }catch(Exception abc){
                            JOptionPane.showMessageDialog(null,"Try again!");
                        }
//              --
                        if(!work.isEmpty() && time == 0 && priority == 0){
                            JOptionPane.showMessageDialog(null,"enter all the fields please.");
                            return;
                        }
                        String query = "insert into work_table values('"+work+"','"+time+"','"+priority+"','"+user+"','"+progress+"','"+work_id+"')";

                        connection.statement.executeUpdate(query);
                        JOptionPane.showMessageDialog(null,"Your work wave has new work in it.");

                        addWorkField.setText("");
                        timeFeild.setText("");
                        priorityFeild.setText("");
                    }
                    catch (Exception abc){
                        JOptionPane.showMessageDialog(null,"exception : please enter all the fields.");
                    }
                }
            }
        });

    }


    public static void main(String[] args) {
//      main
        new Main("");
    }

}
