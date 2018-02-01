package Gui;

import Coins.BinanceCoin;
import Coins.Bitcoin;
import Coins.Cardano;
import Coins.Litecoin;
import DataHandling.DataHandler;
import Exceptions.CryptoTraderException;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;

import DataHandling.BinanceAdapters.BinanceAccountInformation;
import DataHandling.BinanceAdapters.BinancePriceDataAccessor;
import com.webcerebrium.binance.api.BinanceApiException;
import org.knowm.xchange.currency.Currency;

import static java.lang.System.out;

public class GUITest {
    private JButton button_Account;
    private JButton button_Main;
    private JButton button_Settings;
    private JPanel Outter;
    private JPanel content_Home;
    private JButton button_Stop;
    private JButton button_Start;
    private JPanel content;
    private JTable table_Transactions;
    private static Image favicon;
    private JTextField accountNetWorth17TextField;


    //vars that need to be passed between the gui classes
    static JFrame frame;
    public static String username;

    //vars that are private to the main window
    private static String page;
    private static boolean stop = true;

    //coins
    private static Bitcoin btc;
    private static Cardano ada;
    private static BinanceCoin bnb;
    private static Litecoin ltc;

    //windows
    static CurrencySettings cs;
    static Account as;
    static GUITest gs;

    //test variables
    public static double amountBTC;

    static void init() throws BinanceApiException, CryptoTraderException, IOException {

        //TESTING WITH THESE VARS
        //@TODO when login is finished, get rid of these
        username = "rocketrice";

        favicon = Toolkit.getDefaultToolkit().getImage("RSquared_Logo.png");
        cs = new CurrencySettings();
        as = new Account();
        gs = new GUITest();
        btc = new Bitcoin();
        ada = new Cardano(username);
        bnb = new BinanceCoin();
        ltc = new Litecoin(username);
        amountBTC = 1;
    }

    public GUITest() throws BinanceApiException, CryptoTraderException, IOException {

        //instaiate vars
        page = "home";

        //GUI init stuff
        button_Stop.setEnabled(false);
        calculateNetWorth();


        button_Main.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchScreens("home");
                page = "home";
                out.println("Page Change Success");

            }
        });
        button_Settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchScreens("settings");
                out.println("Page Change Success");
            }
        });
        button_Account.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchScreens("account");
                out.println("Page Change Success");
            }
        });
        button_Stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("stop");

                stop = true;

                //enable/disable stuff
                button_Start.setEnabled(true);
                cs.getBitCoinCheckBox().setEnabled(true);
                cs.cardanoCheckBox.setEnabled(true);
                cs.binanceCheckBox.setEnabled(true);
                cs.litecoinCheckBox.setEnabled(true);

                button_Stop.setEnabled(false);
            }
        });
        button_Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop = false;
                boolean tradeBTC = false;
                boolean tradeADA = false;
                boolean tradeBNB = false;
                boolean tradeLTC = false;

                accountNetWorth17TextField.setText(amountBTC + "");

                if (cs.bitCoinCheckBox.isSelected()) {
                    out.println("Wow! were trading Bitcoin!");
                    tradeBTC = true;
                }
                if (cs.cardanoCheckBox.isSelected()) {
                    out.println("Wow! were trading Cardano!");
                    tradeADA = true;
                    ada.initTrade();
                }
                if (cs.binanceCheckBox.isSelected()) {
                    out.println("Wow! were trading Binance!");
                    tradeBNB = true;
                }
                if (cs.litecoinCheckBox.isSelected()) {
                    out.println("Wow! were trading Litecoin!");
                    tradeLTC = true;
                }

                // disable start button and checkboxes so things dont mess up
                button_Start.setEnabled(false);
                cs.bitCoinCheckBox.setEnabled(false);
                cs.cardanoCheckBox.setEnabled(false);
                cs.binanceCheckBox.setEnabled(false);
                cs.litecoinCheckBox.setEnabled(false);

                // enable the stop button so you can stop it of course
                button_Stop.setEnabled(true);

                double startTime = (double) System.currentTimeMillis();
                double threshold = 30000;

                try {
                    Writer fw = new BufferedWriter(new FileWriter("TestingData.log", true));
                    fw.append("\n\n\n***Starting new log***\n\n\n");
                    fw.close();
                } catch (Exception ex) {
                    out.println("oof");
                }
                while (!stop) {
                    accountNetWorth17TextField.setText(amountBTC + "");
                    while (!stop) {
                        if (((double) System.currentTimeMillis()) - startTime >= threshold) {
                            if (tradeBTC) {

                            }
                            if (tradeADA) {
                                ada.checkTrade();
                            }
                            if (tradeBNB) {

                            }
                            if (tradeLTC) {

                            }
                            startTime = (double) System.currentTimeMillis();
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) throws BinanceApiException, CryptoTraderException, IOException, InterruptedException {
        R2Splash.showSplash();
        init();
        frame = new JFrame("RSqaured");
        frame.setIconImage(favicon);
        frame.setContentPane(new GUITest().Outter);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Thread.sleep(3000);
        R2Splash.hideSplash();
        frame.setVisible(true);
    }


    public static void switchScreens(String screen) {
        out.println(page);

        //in case of clicking the same button mor than once
        if (page == screen) return;

        if (screen == "home") {
            //set which screens
            frame.setContentPane(gs.Outter);
            frame.pack();
            frame.setVisible(true);
        } else if (screen == "settings") {
            //set which screens
            frame.setContentPane(cs.Settings_Outter);
            frame.pack();
            frame.setVisible(true);
        } else if (screen == "account") {
            //set which screens
            frame.setContentPane(as.Outter);
            frame.pack();
            frame.setVisible(true);

        }
        page = screen;
    }

    public void calculateNetWorth() throws BinanceApiException, CryptoTraderException, IOException {
        //@TODO add in the code to read in the data from binance

        //BinanceAccountInformation.init();
        double worth = 0.0;
        double temp;
        BinanceAccountInformation bai = new BinanceAccountInformation(DataHandler.openAccountSettings(username)[2], DataHandler.openAccountSettings(username)[3]);
        bai.init();
        BigDecimal[] vals = bai.getPortfolioValue();
        Currency[] support = BinanceAccountInformation.getSupportedCurrenciesInCurrency();
        for (int i = 0; i < support.length; i++) {

            out.println(vals[i].doubleValue() + "\n\n");
            if (support[i].equals(Currency.BTC)) worth += vals[i].doubleValue();
            else {
                out.println(vals[i].multiply(new BigDecimal(BinancePriceDataAccessor.getValueInBTC(support[i]))));
                temp = vals[i].doubleValue() * BinancePriceDataAccessor.getValueInBTC(support[i]);
                worth += temp;
                out.println(worth + "\n" + temp);
            }

        }

        accountNetWorth17TextField.setText("Account Net Worth: " + worth);
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        Outter = new JPanel();
        Outter.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        Outter.setBackground(new Color(-16747077));
        Outter.setForeground(new Color(-16747077));
        Outter.setMinimumSize(new Dimension(1280, 720));
        Outter.setPreferredSize(new Dimension(1280, 720));
        final Spacer spacer1 = new Spacer();
        Outter.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        button_Account = new JButton();
        button_Account.setBackground(new Color(-16729220));
        Font button_AccountFont = this.$$$getFont$$$("Verdana", Font.PLAIN, 30, button_Account.getFont());
        if (button_AccountFont != null) button_Account.setFont(button_AccountFont);
        button_Account.setForeground(new Color(-16777216));
        button_Account.setText("Account");
        Outter.add(button_Account, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 255), null, 0, false));
        button_Main = new JButton();
        button_Main.setBackground(new Color(-16750009));
        Font button_MainFont = this.$$$getFont$$$("Verdana", Font.PLAIN, 30, button_Main.getFont());
        if (button_MainFont != null) button_Main.setFont(button_MainFont);
        button_Main.setForeground(new Color(-16777216));
        button_Main.setText("Home");
        Outter.add(button_Main, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 225), null, 0, false));
        button_Settings = new JButton();
        button_Settings.setBackground(new Color(-16729220));
        Font button_SettingsFont = this.$$$getFont$$$("Verdana", Font.PLAIN, 30, button_Settings.getFont());
        if (button_SettingsFont != null) button_Settings.setFont(button_SettingsFont);
        button_Settings.setForeground(new Color(-16777216));
        button_Settings.setText("Settings");
        Outter.add(button_Settings, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 225), null, 0, false));
        content = new JPanel();
        content.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Outter.add(content, new GridConstraints(1, 1, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        content_Home = new JPanel();
        content_Home.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        content_Home.setBackground(new Color(-9079691));
        content_Home.setEnabled(true);
        content_Home.setVisible(true);
        content.add(content_Home, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        content_Home.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), null));
        table_Transactions = new JTable();
        table_Transactions.setEnabled(false);
        Font table_TransactionsFont = this.$$$getFont$$$("Verdana", -1, 20, table_Transactions.getFont());
        if (table_TransactionsFont != null) table_Transactions.setFont(table_TransactionsFont);
        content_Home.add(table_Transactions, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        button_Stop = new JButton();
        button_Stop.setBackground(new Color(-16747077));
        Font button_StopFont = this.$$$getFont$$$("Verdana", -1, 30, button_Stop.getFont());
        if (button_StopFont != null) button_Stop.setFont(button_StopFont);
        button_Stop.setForeground(new Color(-16777216));
        button_Stop.setText("Stop");
        content_Home.add(button_Stop, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button_Start = new JButton();
        button_Start.setBackground(new Color(-16747077));
        Font button_StartFont = this.$$$getFont$$$("Verdana", -1, 30, button_Start.getFont());
        if (button_StartFont != null) button_Start.setFont(button_StartFont);
        button_Start.setForeground(new Color(-16777216));
        button_Start.setText("Start");
        content_Home.add(button_Start, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accountNetWorth17TextField = new JTextField();
        accountNetWorth17TextField.setEditable(false);
        Font accountNetWorth17TextFieldFont = this.$$$getFont$$$("Verdana", Font.BOLD, 48, accountNetWorth17TextField.getFont());
        if (accountNetWorth17TextFieldFont != null) accountNetWorth17TextField.setFont(accountNetWorth17TextFieldFont);
        accountNetWorth17TextField.setText("Account Net Worth: 17.46578 BTC");
        content_Home.add(accountNetWorth17TextField, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return Outter;
    }
}
