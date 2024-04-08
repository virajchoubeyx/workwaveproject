import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.proteanit.sql.DbUtils;


public class CheckWorkList extends JFrame{
    private JPanel panel;
    private JLabel title;

    private JTable table;

    public CheckWorkList(String username){
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

        JLabel heading = new JLabel("Work to do as per the priority");
        heading.setFont(new Font("Sans-serif",Font.BOLD,21));
        heading.setForeground(Color.lightGray);
        heading.setBounds(25,80,width/2,40);
        panel.add(heading);
//      --
        JSeparator line = new JSeparator();
        line.setBounds(25,130,width/2-70,15);
        panel.add(line);

//      --
        // Create a scroll pane to hold the table
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 160, width/2 - 70, height/2);
        panel.add(scrollPane);

        // Create the table
        table = new JTable();
        table.setFillsViewportHeight(true);

//      Table headers
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.setBackground(Color.WHITE);
        header.setForeground(Color.BLACK);

        table.setBackground(new Color(1,24, 43));
        table.setForeground(Color.lightGray);
        table.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        // Add the table to the scroll pane
        scrollPane.setViewportView(table);

        // Load data into the table
        try {
            Conn c = new Conn();
            String display_query = "SELECT DISTINCT workID,work,time,priority,progress FROM work_table where username = '"+username+"' order by priority DESC";
            ResultSet rs = c.statement.executeQuery(display_query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//      --
        JMenuBar menu = new JMenuBar();
        menu.setBounds(25,height/2+180,width/2-150,40);
        menu.setBackground(new Color(1,24,43));

        JMenu m1 = new JMenu("Want to reset the work table?");
        m1.setForeground(Color.lightGray);
        menu.add(m1);
//      --
        JMenuItem i1 = new JMenuItem("Reset");
        m1.add(i1);

        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Conn().statement.executeUpdate("Delete From work_table");
                    JOptionPane.showMessageDialog(null,"Reset successful.");
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        JMenu m2 = new JMenu("Want to Add new work?");
        m2.setForeground(Color.lightGray);
        menu.add(m2);
//      --
        JMenuItem i2 = new JMenuItem("Move the main page.");
        m2.add(i2);

        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        JMenu m3 = new JMenu("Move back to Timerx");
        m3.setForeground(Color.lightGray);
        menu.add(m3);
//      --
        JMenuItem i3 = new JMenuItem("Timerx");
        m3.add(i3);

        i3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Timerx(username).setVisible(true);
                dispose();
            }
        });

        panel.add(menu);

//      --
        Font g = new Font("Sans-serif",Font.PLAIN,18);

        JLabel progress = new JLabel("workID : ");
        progress.setFont(g);
        progress.setForeground(Color.lightGray);
        progress.setBounds(25,height/2+230,100,30);
        panel.add(progress);

        JTextField progressField = new JTextField();
        progressField.setFont(g);
        progressField.setForeground(Color.darkGray);
        progressField.setBounds(125,height/2+230,200,30);
        progressField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        panel.add(progressField);

        JMenuBar menu2 = new JMenuBar();
        menu2.setBounds(400,height/2+230,245,30);
        menu2.setBackground(new Color(1,24,43));

        JMenu mp1 = new JMenu("want to change the progress of this work?");
        mp1.setForeground(Color.lightGray);
        menu2.add(mp1);
//      --
        JMenuItem ip1 = new JMenuItem("Completed");
        mp1.add(ip1);

        ip1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int progress_edit = Integer.parseInt(progressField.getText());
                    String x = "completed";

                    new Conn().statement.executeUpdate("Update work_table set progress = '"+x+"' where username = '"+username+"' AND workID ='"+progress_edit+"'");
                    JOptionPane.showMessageDialog(null,"Update successful, kindly refresh once.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JMenuItem ip2 = new JMenuItem("Drop out");
        mp1.add(ip2);

        ip2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int progress_edit = Integer.parseInt(progressField.getText());
                    String x = "not doing anymore";

                    new Conn().statement.executeUpdate("Update work_table set progress = '"+x+"' where username = '"+username+"' AND workID ='"+progress_edit+"'");
                    JOptionPane.showMessageDialog(null,"Update successful, kindly refresh once.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(menu2);


    }

    public static void main(String[] args) {
        new CheckWorkList("").setVisible(true);
    }

}
