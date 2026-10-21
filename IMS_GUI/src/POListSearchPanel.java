import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class POListSearchPanel extends JPanel {
    private ArrayList<ArrayList<Object>> productList = new ArrayList<>();
    private ProductSearchPanel productSearchPanel = new ProductSearchPanel();
    private String[] columnNames
            = {"PurchaseID", "Date", "Total Item", "Total Quantity", "Total Price", "Supplier", "Status", "View Order"};
    private Object[][] data = {
            {"PO001", "20230607", 6, 1680, 75600.0, "UMS FKI", "Pending", "View"},
            {"PO002", "20230607", 1, 120, 3600.0, "UMS SE", "Pending", "View"},
            {"PO003", "20230607", 2, 200, 7500.0, "UMS NE", "Pending", "View"},
            {"PO004", "20230607", 2, 200, 9000.0, "UMS DS", "Pending", "View"},
    };
    public Object[][][] product = {{{"KT14303",280},{"KT14503",280},{"KT14203",280},{"KT14403",280},{"KT14603",280},{"KT14803",280}},
                                   {{"KK14202",120}},
                                   {{"KP14102",100},{"KP14203",100}},
                                   {{"KD14203",100},{"KD14403",100}}};
    private DefaultTableModel model = new DefaultTableModel(data, columnNames);
    private JTable jTable = new JTable(model);
    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());
    private JTextField jtfFilter = new JTextField();
    private JButton jbtFilter = new JButton("Filter");
    public void addPOProductList(ArrayList<Object> itemData) {
        productList.add(itemData);
    }
    public ArrayList<ArrayList<Object>> getProductList() {
        return productList;
    }
    public int getPOProductQuantity(String barcode){
        int quantity = 0;
        for (int i = 0; i < product.length; i++) {
            for (int j = 0; j<product[i].length; j++) {
                if (barcode.equals(product[i][j][0])) {
                    quantity += (int) product[i][j][1];
                    break;
                }
            }
        }
        if(productList.size()!=0) {
            for (int i = 0; i < productList.size(); i++) {
                ArrayList<Object> addProduct = productList.get(i);
                if (barcode.equals(addProduct.get(0))){
                    quantity += (int)addProduct.get(1);
                    break;
                }
            }
        }
        return quantity;
    }

    public double getPOProductPrice(String barcode){
        double price = 0.0;
        for (int i = 0; i < product.length; i++) {
            for (int j = 0; j<product[i].length; j++) {
                if (barcode.equals(product[i][j][0])) {
                    price += (productSearchPanel.getStockPrice(barcode)) * ((int) product[i][j][1]);
                    break;
                }
            }
        }
        if(productList.size()!=0) {
            for (int i = 0; i < productList.size(); i++) {
                ArrayList<Object> addProduct = productList.get(i);
                if (barcode.equals(addProduct.get(0))){
                    price += (double)addProduct.get(2);
                    break;
                }
            }
        }
        return price;
    }
    public POListSearchPanel() {
        jTable.setRowSorter(rowSorter);
        jTable.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        jTable.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor());
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
    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            // Set button text
            setText(value.toString());
            return this;
        }
    }
    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isButtonClicked;
        public ButtonEditor() {
            super(new JTextField());
            setClickCountToStart(1);
            // Create button
            button = new JButton();
            button.setOpaque(true);
            // Set button action
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = jTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String selectedValue = jTable.getValueAt(selectedRow, 0).toString();
                        String fileName = "PO/"+selectedValue+".txt";
                        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                            String line;
                            String paragraph = "";
                            while ((line = reader.readLine()) != null) {
                                paragraph += line + "\n";
                            }
                            JOptionPane.showMessageDialog(null, paragraph);
                        } catch (IOException a) {
                            a.printStackTrace();
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "No row selected!");
                    }
                }
            });
        }
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                                     int row, int column) {
            // Set button text
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isButtonClicked = false;
            return button;
        }
        public Object getCellEditorValue() {
            return label;
        }
        public boolean stopCellEditing() {
            isButtonClicked = true;
            return super.stopCellEditing();
        }
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}