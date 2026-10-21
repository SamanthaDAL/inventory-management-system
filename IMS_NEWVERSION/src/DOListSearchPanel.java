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

public class DOListSearchPanel extends JPanel {
    private ArrayList<ArrayList<Object>> productList = new ArrayList<>();
    private ProductSearchPanel productSearchPanel = new ProductSearchPanel();
    private String[] columnNames = { "Delivery ID", "Date", "Total Item", "Total Quantity", "Total Price", "Customer",
            "Carrier", "Status", "View Order" };
    private Object[][] data = {
            { "DO001", "20230606", 6, 1662, 99720.0, "Alice", "ABX Express", "Order being prepared.", "View" },
            { "DO002", "20230606", 1, 115, 4600.0, "Ben", "Poslaju", "Order being prepared.", "View" },
            { "DO003", "20230606", 2, 194, 9700.0, "Cin", "J&T Express", "Order being prepared.", "View" },
            { "DO004", "20230606", 2, 166, 9960.0, "Dora", "DHL", "Order being prepared.", "View" },
    };
    public Object[][][] product = {
            { { "KT14303", 277 }, { "KT14503", 277 }, { "KT14203", 277 }, { "KT14403", 277 }, { "KT14603", 277 },
                    { "KT14803", 277 } },
            { { "KK14202", 115 } },
            { { "KP14102", 97 }, { "KP14203", 97 } },
            { { "KD14203", 83 }, { "KD14403", 83 } } };
    private DefaultTableModel model = new DefaultTableModel(data, columnNames);
    private JTable jTable = new JTable(model);
    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());
    private JTextField jtfFilter = new JTextField();
    private JButton jbtFilter = new JButton("Filter");

    public void addDOProductList(ArrayList<Object> itemData) {
        productList.add(itemData);
    }

    public ArrayList<ArrayList<Object>> getProductList() {
        return productList;
    }

    public int getDOProductQuantity(String barcode) {
        int quantity = 0;
        for (int i = 0; i < product.length; i++) {
            for (int j = 0; j < product[i].length; j++) {
                if (barcode.equals(product[i][j][0])) {
                    quantity += (int) product[i][j][1];
                    break;
                }
            }
        }
        if (productList.size() != 0) {
            for (int i = 0; i < productList.size(); i++) {
                ArrayList<Object> addProduct = productList.get(i);
                if (barcode.equals(addProduct.get(0))) {
                    quantity += (int) addProduct.get(1);
                    break;
                }
            }
        }
        return quantity;
    }

    public double getDOProductPrice(String barcode) {
        double price = 0.0;
        for (int i = 0; i < product.length; i++) {
            for (int j = 0; j < product[i].length; j++) {
                if (barcode.equals(product[i][j][0])) {
                    price += (productSearchPanel.getStockCustomerPrice(barcode)) * ((int) product[i][j][1]);
                    break;
                }
            }
        }
        if (productList.size() != 0) {
            for (int i = 0; i < productList.size(); i++) {
                ArrayList<Object> addProduct = productList.get(i);
                if (barcode.equals(addProduct.get(0))) {
                    price += (double) addProduct.get(2);
                    break;
                }
            }
        }
        return price;
    }

    public DOListSearchPanel() {
        jTable.setRowSorter(rowSorter);
        jTable.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
        jTable.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor());
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
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(text)));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(text)));
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
                    int selectedRow = jTable.getEditingRow();
                    if (selectedRow < 0)
                        selectedRow = jTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String selectedValue = jTable.getValueAt(selectedRow, 0).toString();
                        String fileName = "DO/" + selectedValue + ".txt";
                        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                            String line;
                            String paragraph = "";
                            while ((line = reader.readLine()) != null) {
                                paragraph += line + "\n";
                            }
                            JTextArea textArea = new JTextArea(paragraph);
                            textArea.setEditable(false);
                            textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
                            textArea.setCaretPosition(0);

                            JScrollPane scrollPane = new JScrollPane(textArea);
                            scrollPane.setPreferredSize(new Dimension(650, 450));

                            JOptionPane.showMessageDialog(
                                    null,
                                    scrollPane,
                                    "Delivery Order Details",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException a) {
                            // Included demonstration rows do not ship with a matching text file.
                            // Display the selected table data rather than silently failing.
                            StringBuilder summary = new StringBuilder("Order details (sample/table record)\n\n");
                            for (int column = 0; column < jTable.getColumnCount() - 1; column++) {
                                summary.append(jTable.getColumnName(column)).append(": ")
                                        .append(jTable.getValueAt(selectedRow, column)).append("\n");
                            }
                            summary.append("\nNo saved text file exists for this order yet.");
                            JOptionPane.showMessageDialog(jTable, summary.toString(),
                                    "Order Details", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
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