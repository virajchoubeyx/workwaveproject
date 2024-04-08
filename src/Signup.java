import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener{
    private JPanel panel;
    private JLabel title;
    private JButton signup;
    private JTextField userField,passField,emailField;

    private Conn conn;
    private ResultSet result;

    public Signup(){
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
        JLabel heading = new JLabel("SIGN UP");
        heading.setFont(new Font("Sans-serif",Font.BOLD,21));
        heading.setForeground(Color.lightGray);
        heading.setBounds(150,160,width/2,40);
        panel.add(heading);
//      --
        JSeparator line = new JSeparator();
        line.setBounds(150,210,450,15);
        panel.add(line);

//      -- Login Form --
        Font f = new Font("Sans-serif",Font.PLAIN,21);

        JLabel username = new JLabel("username : ");
        username.setFont(f);
        username.setForeground(Color.lightGray);
        username.setBounds(150,240,150,30);
        panel.add(username);

        JLabel passcode = new JLabel("passcode : ");
        passcode.setFont(f);
        passcode.setForeground(Color.lightGray);
        passcode.setBounds(150,290,150,30);
        panel.add(passcode);

        JLabel email = new JLabel("email : ");
        email.setFont(f);
        email.setForeground(Color.lightGray);
        email.setBounds(150,340,150,30);
        panel.add(email);

        Font g = new Font("Sans-serif",Font.PLAIN,18);

        userField = new JTextField();
        userField.setFont(g);
        userField.setForeground(Color.darkGray);
        userField.setBounds(280,240,300,30);
        userField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        panel.add(userField);

        passField = new JTextField();
        passField.setFont(g);
        passField.setForeground(Color.darkGray);
        passField.setBounds(280,290,300,30);
        passField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        panel.add(passField);

        emailField = new JTextField();
        emailField.setFont(g);
        emailField.setForeground(Color.darkGray);
        emailField.setBounds(280,340,300,30);
        emailField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        panel.add(emailField);

//      --
        signup = new JButton("Create");
        signup.addActionListener(this);
        signup.setBounds(150,400,100,30);
        signup.setFont(f);
        signup.setBackground(Color.lightGray);
        signup.setForeground(Color.BLACK);
        panel.add(signup);

//      --
        JMenuBar menu = new JMenuBar();
        menu.setBounds(150,500,450,40);
        menu.setBackground(new Color(1,24,43));

        JMenu m1 = new JMenu("Already have a account?");
        m1.setForeground(Color.lightGray);
        menu.add(m1);
//      --
        JMenuItem i1 = new JMenuItem("Login");
        m1.add(i1);

        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login().setVisible(true);
            }
        });

        panel.add(menu);

        //      --implementation--

    }

    public static void main(String[] args) {
        new Signup().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==signup){
            conn = new Conn();

            String user = userField.getText();
            String code = passField.getText();
            String email = emailField.getText();
            try{
                String query = "insert into account values('"+user+"','"+code+"','"+email+"')";
                if(user.isEmpty()||code.isEmpty()||email.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Both the fields are important, kindly fill them.");
                    return;
                }
                String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
                Boolean check = email.matches(regex);
                if(!check){
                    JOptionPane.showMessageDialog(null,"enter a valid email-address.");
                    return;
                }
                conn.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null,"Account created successfully.");
                setVisible(false);
                new Login().setVisible(true);

            }catch (Exception abc){
//
                JOptionPane.showMessageDialog(null,"exception : "+abc);
            }
        }
    }
}
