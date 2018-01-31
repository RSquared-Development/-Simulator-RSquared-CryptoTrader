package Gui;

import javax.swing.*;
import java.awt.*;

public class R2Splash {

    private static JWindow splashScreen = new JWindow();
    private static Image favicon;

    public static void showSplash() {

        favicon = Toolkit.getDefaultToolkit().getImage("RSquared_Logo.png");
        splashScreen.getContentPane().add(new JLabel("", new ImageIcon("RSquared_Logo.png"), SwingConstants.LEFT));
        splashScreen.setBounds((1920)/2-360, ((1080-100)/2), 720, 200);
        splashScreen.setIconImage(favicon);
        splashScreen.setVisible(true);


    }

    public static void hideSplash() {

        splashScreen.setVisible(false);

    }
}
