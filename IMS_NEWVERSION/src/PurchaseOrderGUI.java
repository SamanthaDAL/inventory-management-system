import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PurchaseOrderGUI extends JFrame {
    private PurchaseOrder pOrder;
    private ProductSearchPanel product;
    private JLabel titleLabel;
    private JLabel contentLabel1;
    private JButton addItem;
    private JButton searchItem1, searchItem2;
    private POListSearchPanel POListSearchPanel;
    private SupplierSearchPanel supplierSearchPanel;
    private JPanel panel;
    private JLabel id;
    private JTextField idText;
    private JLabel date;
    private JTextField dateText;
    private JLabel p1;
    private JTextField p1qText;
    private JLabel p2;
    private JTextField p2qText;
    private JLabel p3;
    private JTextField p3qText;
    private JLabel p4;
    private JTextField p4qText;
    private JLabel p5;
    private JTextField p5qText;
    private JLabel p6;
    private JTextField p6qText;
    private JLabel p7;
    private JTextField p7qText;
    private JLabel p8;
    private JTextField p8qText;
    private JLabel p9;
    private JTextField p9qText;
    private JLabel p10;
    private JTextField p10qText;
    private JLabel p11;
    private JTextField p11qText;
    private JLabel sta;
    private JComboBox staText;
    private JLabel sup;
    private JComboBox supText;
    private JButton confirm;
    public String poid;
    public String podate;
    public String poti;
    public String potq;
    public String potp;
    public String posup;
    public String posta;
    public PurchaseOrderGUI() {
        this(false);
    }

    public PurchaseOrderGUI(boolean embedded) {
        pOrder = new PurchaseOrder();
        product = new ProductSearchPanel();
        setSize(400, 200);
        setTitle("SamLam's Inventory Management System");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(PurchaseOrderGUI.this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }
        });
        setLayout(new BorderLayout());
        titleLabel = new JLabel("Welcome to SamLam's Purchase Order Management");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        contentLabel1 = new JLabel("Please choose the action you will like to do");
        contentLabel1.setHorizontalAlignment(JLabel.CENTER);
        searchItem1 = new JButton("View Purchase Order list");
        searchItem2 = new JButton("View Supplier list");
        addItem = new JButton("Add Purchase Order");
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.add(titleLabel);
        titlePanel.add(contentLabel1);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(searchItem1);
        buttonPanel.add(searchItem2);
        buttonPanel.add(addItem);
        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        if (!embedded) setVisible(true);
        POListSearchPanel = new POListSearchPanel();
        supplierSearchPanel = new SupplierSearchPanel();
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JFrame frame = new EmbeddedFrames.TabFrame("Add Purchase Order");
                        setSize(400, 600);
                        setLayout(new BorderLayout());
                        GridLayout grid = new GridLayout(16, 2);
                        String[] statusList = {"In Process", "Pending", "Ready for dispatch", "Partially dispatched", "Dispatched", "Delivered", "Archived"};
                        String[] supplierList = {"UMS FKI", "UMS SE", "UMS NE", "UMS DS"};
                        id = new JLabel("Purchase ID:");
                        idText = new JTextField(20);
                        date = new JLabel("Purchase Date:");
                        dateText = new JTextField(20);
                        p1 = new JLabel("KT14303 Quantity:");
                        p1qText = new JTextField(20);
                        p2 = new JLabel("KT14503 Quantity:");
                        p2qText = new JTextField(20);
                        p3 = new JLabel("KT14203 Quantity:");
                        p3qText = new JTextField(20);
                        p4 = new JLabel("KT14403 Quantity:");
                        p4qText = new JTextField(20);
                        p5 = new JLabel("KT14603 Quantity:");
                        p5qText = new JTextField(20);
                        p6 = new JLabel("KT14803 Quantity:");
                        p6qText = new JTextField(20);
                        p7 = new JLabel("KK14202 Quantity:");
                        p7qText = new JTextField(20);
                        p8 = new JLabel("KP14102 Quantity:");
                        p8qText = new JTextField(20);
                        p9 = new JLabel("KP14203 Quantity:");
                        p9qText = new JTextField(20);
                        p10 = new JLabel("KD14203 Quantity:");
                        p10qText = new JTextField(20);
                        p11 = new JLabel("KD14403 Quantity:");
                        p11qText = new JTextField(20);
                        sta = new JLabel("Purchase Status:");
                        staText = new JComboBox(statusList);
                        sup = new JLabel("Supplier:");
                        supText = new JComboBox(supplierList);
                        confirm = new JButton("Confirm");
                        confirm.addActionListener(new ConfirmButtonListener());
                        panel = new JPanel();
                        panel.setLayout(grid);
                        panel.add(id);
                        panel.add(idText);
                        panel.add(date);
                        panel.add(dateText);
                        panel.add(p1);
                        panel.add(p1qText);
                        panel.add(p2);
                        panel.add(p2qText);
                        panel.add(p3);
                        panel.add(p3qText);
                        panel.add(p4);
                        panel.add(p4qText);
                        panel.add(p5);
                        panel.add(p5qText);
                        panel.add(p6);
                        panel.add(p6qText);
                        panel.add(p7);
                        panel.add(p7qText);
                        panel.add(p8);
                        panel.add(p8qText);
                        panel.add(p9);
                        panel.add(p9qText);
                        panel.add(p10);
                        panel.add(p10qText);
                        panel.add(p11);
                        panel.add(p11qText);
                        panel.add(sta);
                        panel.add(staText);
                        panel.add(sup);
                        panel.add(supText);
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
                    final String filePath = "PO/";
                    new java.io.File(filePath).mkdirs();
                    String fileName = filePath + idText.getText() + ".txt";
                    double total, price;
                    double sum = 0.0;
                    int quan = 0;
                    int totalQuan = 0;
                    int item = 0;
                    String p, tp;
                    String barcode;
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                        writer.write("Purchase ID:" + idText.getText());
                        writer.newLine();
                        writer.write("Purchase Date:" + dateText.getText());
                        writer.newLine();
                        writer.write("Purchase Product:");
                        writer.newLine();
                        writer.write("---------------------------------------------");
                        writer.newLine();
                        writer.write("|Barcode|Price per unit|Quantity|Total Price|");
                        writer.newLine();
                        writer.write("---------------------------------------------");
                        if (Integer.parseInt(p1qText.getText()) != 0) {
                            ArrayList<Object> productList1 = new ArrayList<>();
                            barcode = "KT14303";
                            price = product.getStockPrice(barcode);
                            total = price * (Integer.parseInt(p1qText.getText()));
                            sum += total;
                            quan = Integer.parseInt(p1qText.getText());
                            totalQuan += quan;
                            item += 1;
                            p = Double.toString(price);
                            tp = Double.toString(total);
                            productList1.add(Arrays.asList(barcode, quan, total));
                            POListSearchPanel.addPOProductList(productList1);
                            writer.newLine();
                            writer.write("|" + barcode + "|" + price);
                            for (int i = p.length(); i < 14; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + p1qText.getText());
                            for (int i = p1qText.getText().length(); i < 8; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + total);
                            for (int i = tp.length(); i < 11; i++) {
                                writer.write(" ");
                            }
                            writer.write("|");
                        }
                        if (Integer.parseInt(p2qText.getText()) != 0) {
                            ArrayList<Object> productList2 = new ArrayList<>();
                            barcode = "KT14503";
                            price = product.getStockPrice(barcode);
                            total = price * (Integer.parseInt(p2qText.getText()));
                            sum += total;
                            quan = Integer.parseInt(p2qText.getText());
                            totalQuan += quan;
                            item += 1;
                            p = Double.toString(price);
                            tp = Double.toString(total);
                            productList2.add(Arrays.asList(barcode, quan, total));
                            POListSearchPanel.addPOProductList(productList2);
                            writer.newLine();
                            writer.write("|" + barcode + "|" + price);
                            for (int i = p.length(); i < 14; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + p2qText.getText());
                            for (int i = p2qText.getText().length(); i < 8; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + total);
                            for (int i = tp.length(); i < 11; i++) {
                                writer.write(" ");
                            }
                            writer.write("|");
                        }
                        if (Integer.parseInt(p3qText.getText()) != 0) {
                            ArrayList<Object> productList3 = new ArrayList<>();
                            barcode = "KT14203";
                            price = product.getStockPrice(barcode);
                            total = price * (Integer.parseInt(p3qText.getText()));
                            sum += total;
                            quan = Integer.parseInt(p3qText.getText());
                            totalQuan += quan;
                            item += 1;
                            p = Double.toString(price);
                            tp = Double.toString(total);
                            productList3.add(Arrays.asList(barcode, quan, total));
                            POListSearchPanel.addPOProductList(productList3);
                            writer.newLine();
                            writer.write("|" + barcode + "|" + price);
                            for (int i = p.length(); i < 14; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + p3qText.getText());
                            for (int i = p3qText.getText().length(); i < 8; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + total);
                            for (int i = tp.length(); i < 11; i++) {
                                writer.write(" ");
                            }
                            writer.write("|");
                        }
                        if (Integer.parseInt(p4qText.getText()) != 0) {
                            ArrayList<Object> productList4 = new ArrayList<>();
                            barcode = "KT14403";
                            price = product.getStockPrice(barcode);
                            total = price * (Integer.parseInt(p4qText.getText()));
                            sum += total;
                            quan = Integer.parseInt(p4qText.getText());
                            totalQuan += quan;
                            item += 1;
                            p = Double.toString(price);
                            tp = Double.toString(total);
                            productList4.add(Arrays.asList(barcode, quan, total));
                            POListSearchPanel.addPOProductList(productList4);
                            writer.newLine();
                            writer.write("|" + barcode + "|" + price);
                            for (int i = p.length(); i < 14; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + p4qText.getText());
                            for (int i = p4qText.getText().length(); i < 8; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + total);
                            for (int i = tp.length(); i < 11; i++) {
                                writer.write(" ");
                            }
                            writer.write("|");
                        }
                        if (Integer.parseInt(p5qText.getText()) != 0) {
                            ArrayList<Object> productList5 = new ArrayList<>();
                            barcode = "KT14603";
                            price = product.getStockPrice(barcode);
                            total = price * (Integer.parseInt(p5qText.getText()));
                            sum += total;
                            quan = Integer.parseInt(p5qText.getText());
                            totalQuan += quan;
                            item += 1;
                            p = Double.toString(price);
                            tp = Double.toString(total);
                            productList5.add(Arrays.asList(barcode, quan, total));
                            POListSearchPanel.addPOProductList(productList5);
                            writer.newLine();
                            writer.write("|" + barcode + "|" + price);
                            for (int i = p.length(); i < 14; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + p5qText.getText());
                            for (int i = p5qText.getText().length(); i < 8; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + total);
                            for (int i = tp.length(); i < 11; i++) {
                                writer.write(" ");
                            }
                            writer.write("|");
                        }
                        if (Integer.parseInt(p6qText.getText()) != 0) {
                            ArrayList<Object> productList6 = new ArrayList<>();
                            barcode = "KT14803";
                            price = product.getStockPrice(barcode);
                            total = price * (Integer.parseInt(p6qText.getText()));
                            sum += total;
                            quan = Integer.parseInt(p6qText.getText());
                            totalQuan += quan;
                            item += 1;
                            p = Double.toString(price);
                            tp = Double.toString(total);
                            productList6.add(Arrays.asList(barcode, quan, total));
                            POListSearchPanel.addPOProductList(productList6);
                            writer.newLine();
                            writer.write("|" + barcode + "|" + price);
                            for (int i = p.length(); i < 14; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + p6qText.getText());
                            for (int i = p6qText.getText().length(); i < 8; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + total);
                            for (int i = tp.length(); i < 11; i++) {
                                writer.write(" ");
                            }
                            writer.write("|");
                        }
                        if (Integer.parseInt(p7qText.getText()) != 0) {
                            ArrayList<Object> productList7 = new ArrayList<>();
                            barcode = "KK14202";
                            price = product.getStockPrice(barcode);
                            total = price * (Integer.parseInt(p7qText.getText()));
                            sum += total;
                            quan = Integer.parseInt(p7qText.getText());
                            totalQuan += quan;
                            item += 1;
                            p = Double.toString(price);
                            tp = Double.toString(total);
                            productList7.add(Arrays.asList(barcode, quan, total));
                            POListSearchPanel.addPOProductList(productList7);
                            writer.newLine();
                            writer.write("|" + barcode + "|" + price);
                            for (int i = p.length(); i < 14; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + p7qText.getText());
                            for (int i = p7qText.getText().length(); i < 8; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + total);
                            for (int i = tp.length(); i < 11; i++) {
                                writer.write(" ");
                            }
                            writer.write("|");
                        }
                        if (Integer.parseInt(p8qText.getText()) != 0) {
                            ArrayList<Object> productList8 = new ArrayList<>();
                            barcode = "KP14102";
                            price = product.getStockPrice(barcode);
                            total = price * (Integer.parseInt(p8qText.getText()));
                            sum += total;
                            quan = Integer.parseInt(p8qText.getText());
                            totalQuan += quan;
                            item += 1;
                            p = Double.toString(price);
                            tp = Double.toString(total);
                            productList8.add(Arrays.asList(barcode, quan, total));
                            POListSearchPanel.addPOProductList(productList8);
                            writer.newLine();
                            writer.write("|" + barcode + "|" + price);
                            for (int i = p.length(); i < 14; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + p8qText.getText());
                            for (int i = p8qText.getText().length(); i < 8; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + total);
                            for (int i = tp.length(); i < 11; i++) {
                                writer.write(" ");
                            }
                            writer.write("|");
                        }
                        if (Integer.parseInt(p9qText.getText()) != 0) {
                            ArrayList<Object> productList9 = new ArrayList<>();
                            barcode = "KP14203";
                            price = product.getStockPrice(barcode);
                            total = price * (Integer.parseInt(p9qText.getText()));
                            sum += total;
                            quan = Integer.parseInt(p9qText.getText());
                            totalQuan += quan;
                            item += 1;
                            p = Double.toString(price);
                            tp = Double.toString(total);
                            productList9.add(Arrays.asList(barcode, quan, total));
                            POListSearchPanel.addPOProductList(productList9);
                            writer.newLine();
                            writer.write("|" + barcode + "|" + price);
                            for (int i = p.length(); i < 14; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + p9qText.getText());
                            for (int i = p9qText.getText().length(); i < 8; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + total);
                            for (int i = tp.length(); i < 11; i++) {
                                writer.write(" ");
                            }
                            writer.write("|");
                        }
                        if (Integer.parseInt(p10qText.getText()) != 0) {
                            ArrayList<Object> productList10 = new ArrayList<>();
                            barcode = "KD14203";
                            price = product.getStockPrice(barcode);
                            total = price * (Integer.parseInt(p10qText.getText()));
                            sum += total;
                            quan = Integer.parseInt(p10qText.getText());
                            totalQuan += quan;
                            item += 1;
                            p = Double.toString(price);
                            tp = Double.toString(total);
                            productList10.add(Arrays.asList(barcode, quan, total));
                            POListSearchPanel.addPOProductList(productList10);
                            writer.newLine();
                            writer.write("|" + barcode + "|" + price);
                            for (int i = p.length(); i < 14; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + p10qText.getText());
                            for (int i = p10qText.getText().length(); i < 8; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + total);
                            for (int i = tp.length(); i < 11; i++) {
                                writer.write(" ");
                            }
                            writer.write("|");
                        }
                        if (Integer.parseInt(p11qText.getText()) != 0) {
                            ArrayList<Object> productList11 = new ArrayList<>();
                            barcode = "KD14403";
                            price = product.getStockPrice(barcode);
                            total = price * (Integer.parseInt(p11qText.getText()));
                            sum += total;
                            quan = Integer.parseInt(p11qText.getText());
                            totalQuan += quan;
                            item += 1;
                            p = Double.toString(price);
                            tp = Double.toString(total);
                            productList11.add(Arrays.asList(barcode, quan, total));
                            POListSearchPanel.addPOProductList(productList11);
                            writer.newLine();
                            writer.write("|" + barcode + "|" + price);
                            for (int i = p.length(); i < 14; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + p11qText.getText());
                            for (int i = p11qText.getText().length(); i < 8; i++) {
                                writer.write(" ");
                            }
                            writer.write("|" + total);
                            for (int i = tp.length(); i < 11; i++) {
                                writer.write(" ");
                            }
                            writer.write("|");
                        }
                        writer.newLine();
                        writer.write("---------------------------------------------");
                        writer.newLine();
                        writer.write("Total Item: " + item);
                        writer.newLine();
                        writer.write("Total Quantity: " + totalQuan);
                        writer.newLine();
                        writer.write("Total Price: " + sum);
                        writer.newLine();
                        writer.write("Purchase Status: " + staText.getSelectedItem());
                        writer.newLine();
                        writer.write("Supplier: " + supText.getSelectedItem());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Item added successfully");
                    poid = idText.getText();
                    podate = dateText.getText();
                    poti = Integer.toString(item);
                    potq = Integer.toString(totalQuan);
                    potp = Double.toString(sum);
                    posup = supText.getSelectedItem().toString();
                    posta = staText.getSelectedItem().toString();
                    pOrder = new PurchaseOrder(poid, Integer.parseInt(podate), Double.parseDouble(potp), posta);
                    String[] itemData = {pOrder.getPurchaseId(), Integer.toString(pOrder.getPurchaseDate()), poti, potq, Double.toString(pOrder.getPurchaseTotalPrice()), posup, pOrder.getPurchaseStatus(), "View"};
                    POListSearchPanel.addItem(itemData);
                }
            }
        });
        searchItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JFrame frame = new EmbeddedFrames.TabFrame("View Purchase Order list");
                        frame.add(POListSearchPanel);
                        frame.pack();
                        frame.setSize(1300, 700);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });
            }
        });
        searchItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JFrame frame = new EmbeddedFrames.TabFrame("Supplier List");
                        frame.add(supplierSearchPanel);
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