import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUITest {
    private JButton button_Account;
    private JButton button_Main;
    private JButton button_Settings;


    public GUITest() {
        button_Main.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: make it switch to Main panel
            }
        });
        button_Settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: make it switch to Settings Panel
            }
        });
        button_Account.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: make it switch to Account Settings Panel
            }
        });
    }

    

}
