import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MenuGUI extends JFrame {
    private JLabel messageLabel;
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 200;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu stockMenu;
    private JMenu barcodeMenu;
    private JMenu purchaseMenu;
    private JMenu deliveryMenu;
    private JMenu reportMenu;
    private JMenuItem exitItem;
    private JRadioButtonMenuItem stock;
    private JRadioButtonMenuItem scanner;
    private JRadioButtonMenuItem PO;
    private JRadioButtonMenuItem DO;
    private JRadioButtonMenuItem report;
    public MenuGUI() {
            setTitle("Main Menu");
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
            @Override
              public void windowClosing(WindowEvent e) {
                   int result = JOptionPane.showConfirmDialog(MenuGUI.this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                   if (result == JOptionPane.YES_OPTION) {
                       JOptionPane.showMessageDialog(null,"Thank You For Using SamLam's IMS.\nBye Bye!");
                       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  }
             }
             });
            messageLabel = new JLabel("Welcome to SamLam's Inventory Management System", SwingConstants.CENTER);
            messageLabel.setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
            messageLabel.setForeground(Color.BLACK);
            add(messageLabel);
            buildMenuBar();
            pack();
            setVisible(true);
    }
    private void buildMenuBar() {
        menuBar = new JMenuBar();
        buildFileMenu();
        buildStockMenu();
        buildBarcodeMenu();
        buildPurchaseMenu();
        buildDeliveryMenu();
        buildReportMenu();
        menuBar.add(fileMenu);
        menuBar.add(stockMenu);
        menuBar.add(barcodeMenu);
        menuBar.add(purchaseMenu);
        menuBar.add(deliveryMenu);
        menuBar.add(reportMenu);
        add(menuBar, BorderLayout.NORTH);
    }
    private void buildFileMenu() {
        exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.addActionListener(new ExitListener());
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(exitItem);
    }
    private void buildStockMenu() {
        stock = new JRadioButtonMenuItem("Stock", false);
        stock.setMnemonic(KeyEvent.VK_S);
        stockMenu = new JMenu("Stock Management");
        stockMenu.setMnemonic(KeyEvent.VK_T);
        stockMenu.add(stock);
        stock.addActionListener(new ButtonListener());
    }
    private void buildBarcodeMenu() {
        scanner = new JRadioButtonMenuItem("Scanner", false);
        scanner.setMnemonic(KeyEvent.VK_P);
        barcodeMenu = new JMenu("Barcode Scanning", true);
        barcodeMenu.setMnemonic(KeyEvent.VK_B);
        barcodeMenu.add(scanner);
        scanner.addActionListener(new ButtonListener());
    }
    private void buildPurchaseMenu() {
        PO = new JRadioButtonMenuItem("Purchase Order", false);
        PO.setMnemonic(KeyEvent.VK_P);
        purchaseMenu = new JMenu("PO Management", true);
        purchaseMenu.setMnemonic(KeyEvent.VK_U);
        purchaseMenu.add(PO);
        PO.addActionListener(new ButtonListener());
    }
    private void buildDeliveryMenu() {
        DO = new JRadioButtonMenuItem("Delivery Order", false);
        DO.setMnemonic(KeyEvent.VK_L);
        deliveryMenu = new JMenu("DO Management", true);
        deliveryMenu.setMnemonic(KeyEvent.VK_Y);
        deliveryMenu.add(DO);
        DO.addActionListener(new ButtonListener());
    }
    private void buildReportMenu() {
        report = new JRadioButtonMenuItem("Show Report", false);
        report.setMnemonic(KeyEvent.VK_A);
        reportMenu = new JMenu("Report", true);
        reportMenu.setMnemonic(KeyEvent.VK_E);
        reportMenu.add(report);
        report.addActionListener(new ButtonListener());
    }
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            ButtonGroup group = new ButtonGroup();
            group.add(stock);
            group.add(scanner);
            group.add(PO);
            group.add(DO);
            group.add(report);
            if(stock.isSelected()){
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new StockGUI();
                    }
                });
            }
            if(scanner.isSelected()){
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new BarcodeScannerGUI();
                    }
                });
            }
            if(PO.isSelected()){
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new PurchaseOrderGUI();
                    }
                });
            }
            if(DO.isSelected()){
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new DeliveryOrderGUI();
                    }
                });
            }
            if(report.isSelected()){
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new ReportGUI();
                    }
                });
            }
        }
    }
    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}