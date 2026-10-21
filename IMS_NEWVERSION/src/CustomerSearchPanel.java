import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CustomerSearchPanel extends JPanel {
    private String[] columnNames = { "Name", "Address", "Phone", "Email", "Order ID", "View Order" };
    private Object[][] data = {
            { "Alice", "Taman A", "012-367 4434", "alice@gmail.com", "DO001", "View" },
            { "Ben", "Taman B", "013-224 3126", "ben@gmail.com", "DO002", "View" },
            { "Cin", "Taman C", "016-113 8755", "cin@gmail.com", "DO003", "View" },
            { "Dora", "Taman D", "013-121 4543", "dora@gmail.com", "DO004", "View" },
    };
    private DefaultTableModel model = new DefaultTableModel(data, columnNames);
    private JTable jTable = new JTable(model);
    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());
    private JTextField jtfFilter = new JTextField();
    private JButton jbtFilter = new JButton("Filter");

    public CustomerSearchPanel() {
        jTable.setRowSorter(rowSorter);
        jTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        jTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor());
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

    public void addCustomerList(String[] customerData) {
        model.addRow(customerData);
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
                        String selectedValue = jTable.getValueAt(selectedRow, 4).toString();
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
                                    "Purchase Order Details by Customer",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException a) {
                            a.printStackTrace();
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