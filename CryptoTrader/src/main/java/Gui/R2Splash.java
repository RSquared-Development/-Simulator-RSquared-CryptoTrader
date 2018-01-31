package Gui;

import javax.swing.*;
import java.awt.*;

public class R2Splash {

    private static JWindow splashScreen = new JWindow();
    private static Image favicon;


    public static void showSplash() {
        GridLayout test   = new GridLayout(3,3);
        JLabel iconHolder = new JLabel("", new ImageIcon("RSquared_Logo.png"), SwingConstants.CENTER);
        JLabel programText = new JLabel("RSquared Cryptocurrency Trading App");
        programText.setFont(new Font(programText.getFont(), Font.PLAIN, 36));
        double hWidth  = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double hHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        JPanel test2 = new JPanel();
        favicon = Toolkit.getDefaultToolkit().getImage("RSquared_Logo.png");
        test2.add(iconHolder);
        test2.add(programText);
        splashScreen.setContentPane(test2);

        //favicon = Toolkit.getDefaultToolkit().getImage("RSquared_Logo.png");
        //splashScreen.getContentPane().add( iconHolder);
        //splashScreen.getContentPane().add(new JLabel("RSquared Cryptocurrency Trading App"));
        //splashScreen.getContentPane().add(new JLabel("Automate your trades today",SwingConstants.));
        //splashScreen.getContentPane().add(new JLabel("RESTRICTED ALPHA"), SwingConstants.RIGHT);
        splashScreen.setBounds(((int)hWidth)/2-360, (((int)hHeight-100)/2), 720, 200);
        splashScreen.setIconImage(favicon);
        splashScreen.setVisible(true);


    }

    public static void hideSplash() {

        splashScreen.setVisible(false);

    }
}
