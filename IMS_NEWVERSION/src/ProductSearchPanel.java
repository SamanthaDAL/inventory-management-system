import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class ProductSearchPanel extends JPanel {
    private Stock [] stock = new Stock[11];
    private String[] columnNames
            = {"Name","Barcode","Quantity","Price Per Unit/RM","Customer Price Per Unit/RM"};
    private Object[][] data = {
            {"Programming Principles","KT14303",350,45.00,60.00},
            {"Mathematics for Computing","KT14503",350,45.00,60.00},
            {"Computer Architecture and Organization","KT14203",100,45.00,60.00},
            {"Discrete Structures","KT14403",350,45.00,60.00},
            {"Data Structures & Algorithms","KT14603",250,45.00,60.00},
            {"Network Fundamental","KT14803",150,45.00,60.00},
            {"Software Project Management","KK14202",100,30.00,40.00},
            {"Software Engineering","KP14102",100,30.00,40.00},
            {"Object Oriented Programming","KP14203",100,45.00,60.00},
            {"OOP for Data Science","KD14203",100,45.00,60.00},
            {"Fundamental of Data Science","KD14403",100,45.00,60.00}
    };
    private DefaultTableModel model = new DefaultTableModel(data, columnNames);
    private JTable jTable = new JTable(model);
    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());
    private JTextField jtfFilter = new JTextField();
    private JButton jbtFilter = new JButton("Filter");
    public double getStockPrice(String barcode){
        double price = 0.0;
        for (int i = 0; i < data.length; i++) {
            if(barcode.equals(stock[i].getProductBarcode())) {
                price = stock[i].getProductPrice();
                break;
            }
        }
        return price;
    }
    public double getStockCustomerPrice(String barcode){
        double customerPrice = 0.0;
        for (int i = 0; i < data.length; i++) {
            if(barcode.equals(stock[i].getProductBarcode())) {
                customerPrice = stock[i].getProductCustomerPrice();
                break;
            }
        }
        return customerPrice;
    }
    public ProductSearchPanel() {
        for (int i = 0; i < data.length; i++) {
            stock[i] = new Stock(((String) data[i][0]),((String) data[i][1]),((int) data[i][2]),((double) data[i][3]),((double) data[i][4]));
        }
        jTable.setRowSorter(rowSorter);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Specify a word to match:"), BorderLayout.WEST);
        panel.add(jtfFilter, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.SOUTH);
        add(new JScrollPane(jTable), BorderLayout.CENTER);
        jtfFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }
    public void addItem(String[] itemData) {
        model.addRow(itemData);
    }
    public Stock getStock(){
        return new Stock(stock[11]);
    }
}