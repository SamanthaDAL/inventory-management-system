import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
public class CarrierSearchPanel extends JPanel{
    private String[] columnNames
            = {"Name","Plate","Phone/Fax","Email"};
    private Object[][] carrierList = {
            {"ABX Express","SS 1234","012-333 4444","abx_express@gmail.com"},
            {"Poslaju","SAA 2234","013-224 3356","poslaju@gmail.com"},
            {"J&T Express","SD 7890","016-112 8765","jtexpress@gmail.com"},
            {"DHL","SA 6543","019-665 6654","dhl@gmail.com"},
    };
    private DefaultTableModel model5 = new DefaultTableModel(carrierList, columnNames);
    private JTable jTable = new JTable(model5);
    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());
    private JTextField jtfFilter = new JTextField();
    private JButton jbtFilter = new JButton("Filter");
    public CarrierSearchPanel() {
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
    public void viewCarrierList(String[] carrierData) {
        model5.addRow(carrierData);
}
}