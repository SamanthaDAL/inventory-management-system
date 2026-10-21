import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class SupplierSearchPanel extends JPanel {
    private String[] columnNames
            = {"Name","Phone/Fax","Email"};
    private Object[][] data = {
            {"UMS FKI","012-345-6789","ums_fki@iluv.ums.edu.my"},
            {"UMS SE","012-345-6789","ums_se@iluv.ums.edu.my"},
            {"UMS NE","012-345-6789","ums_ne@iluv.ums.edu.my"},
            {"UMS DS","012-345-6789","ums_ds@iluv.ums.edu.my"},
    };
    private DefaultTableModel model = new DefaultTableModel(data, columnNames);
    private JTable jTable = new JTable(model);
    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());
    private JTextField jtfFilter = new JTextField();
    private JButton jbtFilter = new JButton("Filter");
    public SupplierSearchPanel() {
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
}