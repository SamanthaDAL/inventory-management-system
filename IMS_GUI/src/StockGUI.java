import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class StockGUI extends JFrame {
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel contentLabel1;
    private JButton addItem;
    private JButton searchItem;
    private JLabel name;
    private JTextField itemName;
    private JLabel barcode;
    private JTextField itemBarcode;
    private JLabel quantity;
    private JTextField itemQuantity;
    private JLabel price;
    private JTextField itemPrice;
    private String addItemName;
    private String addItemBarcode;
    private String addItemQuantity;
    private String addItemPrice;
    private String addItemCustomerPrice;
    private JButton confirm;
    private ProductSearchPanel productSearchPanel;
    private Stock stock;
    public StockGUI() {
        setSize(400, 200);
        setTitle("SamLam's Inventory Management System");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(StockGUI.this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }
        });
        setLayout(new BorderLayout());
        titleLabel = new JLabel("Welcome to SamLam's Stock Management");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        contentLabel1 = new JLabel("Please choose the action you will like to do");
        contentLabel1.setHorizontalAlignment(JLabel.CENTER);
        addItem = new JButton("Add Item");
        searchItem = new JButton("Search Item");
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.add(titleLabel);
        titlePanel.add(contentLabel1);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(searchItem);
        buttonPanel.add(addItem);
        add(titlePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
        productSearchPanel = new ProductSearchPanel();
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JFrame frame = new JFrame("Add Product");
                        setSize(400,600);
                        setLayout(new BorderLayout());
                        GridLayout grid = new GridLayout(5, 2);
                        panel = new JPanel();
                        panel.setLayout(grid);
                        name = new JLabel("Product name:");
                        itemName = new JTextField(20);
                        barcode = new JLabel("Product barcode:");
                        itemBarcode = new JTextField(20);
                        quantity = new JLabel("Product quantity:");
                        itemQuantity = new JTextField(20);
                        price = new JLabel("Product Price:");
                        itemPrice = new JTextField(20);
                        confirm = new JButton("Confirm");
                        confirm.addActionListener(new ConfirmButtonListener());
                        panel.add(name);
                        panel.add(itemName);
                        panel.add(barcode);
                        panel.add(itemBarcode);
                        panel.add(quantity);
                        panel.add(itemQuantity);
                        panel.add(price);
                        panel.add(itemPrice);
                        panel.add(confirm, BorderLayout.SOUTH);
                        frame.add(panel);
                        frame.pack();
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });
            }
            private class ConfirmButtonListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    addItemName=itemName.getText();
                    addItemBarcode=itemBarcode.getText();
                    addItemQuantity=itemQuantity.getText();
                    addItemPrice=itemPrice.getText();
                    JOptionPane.showMessageDialog(null, "Item added successfully");
                    stock = new Stock(addItemName, addItemBarcode, Integer.parseInt(addItemQuantity), Double.parseDouble(addItemPrice));
                    String[] itemData = {stock.getProductName(), stock.getProductBarcode(), Integer.toString(stock.getProductQuantity()), Double.toString(stock.getProductPrice()), Double.toString(stock.getProductCustomerPrice()),};
                    productSearchPanel.addItem(itemData);
                }
            }
        });
        searchItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JFrame frame = new JFrame("Stock Inventory");
                        frame.add(productSearchPanel);
                        frame.pack();
                        frame.setSize(1300, 700);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });
            }
        });
    }
}