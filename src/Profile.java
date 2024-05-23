import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Profile extends JFrame{
    private JPanel panel;
    private JLabel title;

    private JLabel userField,emailField;
    private String user;
    private JTextArea para;

    private Conn connection;
    private ResultSet result;

    public Profile(String user_name){
        setTitle("Work Wave");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int width = screen.width;
        int height = screen.height;

        setBounds(width/4,15,width/2,height-80);
//      -- Panel --
        panel = new JPanel(null);
        panel.setSize(width/2,height-100);;
        panel.setBackground(new Color(1,24,43));
        add(panel);
//      --

        title = new JLabel("WORK WAVE");
        title.setBounds(15,0,200,40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Sans-serif",Font.PLAIN,21));
        panel.add(title);
//      --
//      --Login--
        JLabel heading = new JLabel("Profile");
        heading.setFont(new Font("Sans-serif",Font.BOLD,21));
        heading.setForeground(Color.lightGray);
        heading.setBounds(150,70,width/2,40);
        panel.add(heading);
//      --
        JSeparator line = new JSeparator();
        line.setBounds(150,120,450,15);
        panel.add(line);

//      --
        Font f = new Font("Sans-serif",Font.PLAIN,21);

        JLabel username = new JLabel("username : ");
        username.setFont(f);
        username.setForeground(Color.lightGray);
        username.setBounds(150,170,150,30);
        panel.add(username);

        JLabel email = new JLabel("email : ");
        email.setFont(f);
        email.setForeground(Color.lightGray);
        email.setBounds(150,220,150,30);
        panel.add(email);

        Font g = new Font("Sans-serif",Font.PLAIN,18);

        userField = new JLabel();
        userField.setFont(g);
        userField.setForeground(Color.lightGray);
        userField.setBounds(280,170,300,30);
        userField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        panel.add(userField);

        emailField = new JLabel();
        emailField.setFont(g);
        emailField.setForeground(Color.lightGray);
        emailField.setBounds(280,220,300,30);
        emailField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        panel.add(emailField);

//      --
        connection = new Conn();
        try{
            String query = "select * from account where username = '"+user_name+"'";
            result = connection.statement.executeQuery(query);

            if(result.next()){
                user = result.getString("username");
                userField.setText(result.getString("username"));
                emailField.setText(result.getString("email"));
            }
        }catch (Exception abc){
//            --
        }
//      --

        para = new JTextArea("Dear "+user+", I'm excited to tell you about the amazing features that WorkWave has to offer! WorkWave is a productivity application that helps you set and achieve your daily goals. With WorkWave, you can easily input your daily goals and the specific work you plan to do.\n" +
                "\n" +
                "One of the most exciting features of WorkWave is the motivational messages you'll receive as you work towards your goals. These messages will help keep you on track and focused throughout the day. Additionally, WorkWave uses the Pomodoro timer approach, which allows you to work in short, focused bursts and take regular breaks. This technique has been proven to increase productivity and focus, making it easier for you to achieve your goals.\n" +
                "\n" +
                "Another great feature of WorkWave is its intuitive user interface. You can easily navigate the app and customize it to suit your needs. You can also view your progress and track your achievements, helping you stay motivated and on track. Overall, WorkWave is an amazing productivity tool that can help you reach your full potential. Give it a try and see how it can transform your workday!");
        para.setLineWrap(true);
        para.setWrapStyleWord(true);
        para.setBounds(25,300,width/3+200,height/3+30);
        para.setFont(new Font("Sans-serif",Font.PLAIN,16));
        para.setForeground(Color.lightGray);
        para.setBackground(new Color(1,24,43));
        para.setEditable(false);
        panel.add(para);

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Main(user).setVisible(true);
            }
        });
        back.setBounds(25,height-230,150,30);
        back.setFont(f);
        back.setBackground(Color.lightGray);
        back.setForeground(Color.BLACK);
        panel.add(back);

        JButton delete = new JButton("Delete Account");

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    new Conn().statement.executeUpdate("Delete From account where username ='"+user_name+"'");
                    JOptionPane.showMessageDialog(null,"Your account is deleted sucessfully.");
                    System.exit(0);
                }catch (Exception abc){
//                    --
                }
            }
        });
        delete.setBounds(250,height-230,230,30);
        delete.setFont(f);
        delete.setBackground(Color.red);
        delete.setForeground(Color.white);
        panel.add(delete);
    }

    public static void main(String[] args) {
        new Profile("").setVisible(true);
    }

}
