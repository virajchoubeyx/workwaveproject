import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener{
    private JPanel panel;
    private JLabel title;
    private JButton login;
    private JTextField userField;
    private JPasswordField passField;

    private Conn conn;
    private ResultSet result;

    public Login(){
        setTitle("Work Wave");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        JLabel heading = new JLabel("LOGIN");
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

        Font g = new Font("Sans-serif",Font.PLAIN,18);

        userField = new JTextField();
        userField.setFont(g);
        userField.setForeground(Color.darkGray);
        userField.setBounds(280,240,300,30);
        userField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        panel.add(userField);

        passField = new JPasswordField();
        passField.setFont(g);
        passField.setForeground(Color.darkGray);
        passField.setBounds(280,290,300,30);
        passField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        panel.add(passField);

//      --
        login = new JButton("Login");
        login.addActionListener(this);
        login.setBounds(150,350,100,30);
        login.setFont(f);
        login.setBackground(Color.lightGray);
        login.setForeground(Color.BLACK);
        panel.add(login);

//      --
        JMenuBar menu = new JMenuBar();
        menu.setBounds(150,450,450,40);
        menu.setBackground(new Color(1,24,43));

        JMenu m1 = new JMenu("Don't have a account?");
        m1.setForeground(Color.lightGray);
        menu.add(m1);
//      --
        JMenuItem i1 = new JMenuItem("Sign-up");
        m1.add(i1);

        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Signup().setVisible(true);
            }
        });

        JMenu m2 = new JMenu("Forgot Password?");
        m2.setForeground(Color.lightGray);
        menu.add(m2);
//      --
        JMenuItem i2 = new JMenuItem("Know Password");
        m2.add(i2);

        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Forgot().setVisible(true);
            }
        });

        panel.add(menu);

    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login){
            try{
                conn = new Conn();
                String user_name = userField.getText();
                String code = passField.getText();
                if(user_name.isEmpty()||code.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Both the fields are important, kindly fill them.");
                    return;
                }
                String query = "SELECT * FROM account WHERE username='" + user_name + "' AND passcode='" + code + "'";
                result = conn.statement.executeQuery(query);
//
                if(result.next()){
//                     -- successful --
                    JOptionPane.showMessageDialog(null,"Login successful.");
                    new Main(user_name).setVisible(true);
                    setVisible(false);
                }else
                    JOptionPane.showMessageDialog(null,"Invalid username or passcode.");
            }catch(Exception abc){
//                   --
            }
        }
    }
}
