import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.out;

public class GUITest {
    private JButton button_Account;
    private JButton button_Main;
    private JButton button_Settings;
    private JPanel Outter;
    private JPanel content_Home;
    private JTable table_Transactions;
    private JTextField accountNetWorth17TextField;
    private JButton button_Stop;
    private JButton button_Start;
    private JPanel content;

    private String page;


    public GUITest() {

        //instaiate vars
        page = "home";

        button_Main.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: make it switch to Main panel
                switchScreens("home");
                page = "home";

            }
        });
        button_Settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: make it switch to Settings Panel
                switchScreens("settings");
                page = "settings";
            }
        });
        button_Account.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: make it switch to Account Settings Panel
                switchScreens("account");
                page = "account";
            }
        });
        button_Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: plug and chug once we get the backend done
                out.println("start");
            }
        });
        button_Stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: plug and chug once we get the backend done
                out.println("stop");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUITest");
        frame.setContentPane(new GUITest().Outter);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public void switchScreens(String screen){
        out.println(page);

        //in case of clicking the same button mor than once
        if (page==screen)return;

        if(screen == "home"){
            //set which screens
            content_Home.setVisible(true);

            //colors
            button_Main.setBackground(new Color(0,106,71));
            button_Settings.setBackground(new Color(0,187,124));
            button_Account.setBackground(new Color(0,187,124));
        } else if(screen == "settings"){
            //set which screens
            content_Home.setVisible(false);

            //colors
            button_Settings.setBackground(new Color(0,106,71));
            button_Main.setBackground(new Color(0,187,124));
            button_Account.setBackground(new Color(0,187,124));
        } else if(screen == "account"){
            //set which screens
            content_Home.setVisible(false);

            //colors
            button_Account.setBackground(new Color(0,106,71));
            button_Settings.setBackground(new Color(0,187,124));
            button_Main.setBackground(new Color(0,187,124));
        }
    }
}
