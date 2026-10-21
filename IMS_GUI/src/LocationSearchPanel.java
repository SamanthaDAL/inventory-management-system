import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class LocationSearchPanel extends JPanel {
    private String[] columnNames
            = {"Barcode","Aisle","Section","Shelf"};
    private Object[][] data = {{"KT14303","FKI",1,'A'},
                               {"KT14503","FKI",1,'B'},
                               {"KT14203","FKI",2,'A'},
                               {"KT14403","FKI",2,'B'},
                               {"KT14603","FKI",2,'C'},
                               {"KT14803","FKI",2,'D'},
                               {"KK14202","SE",2,'A'},
                               {"KP14102","NE",1,'A'},
                               {"KP14203","NE",2,'A'},
                               {"KD14203","DS",2,'A'},
                               {"KD14403","DS",2,'B'}
    };
    private DefaultTableModel model = new DefaultTableModel(data, columnNames);
    private JTable jTable = new JTable(model);
    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());
    private JTextField jtfFilter = new JTextField();
    private JButton jbtFilter = new JButton("Filter");
    public LocationSearchPanel() {
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