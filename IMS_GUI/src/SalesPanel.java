import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class SalesPanel extends JPanel {
    private POListSearchPanel pOrder = new POListSearchPanel();
    private DOListSearchPanel dOrder = new DOListSearchPanel();
    private String sale;
    private String[] labels = {"KT14303","KT14503","KT14203","KT14403","KT14603","KT14803","KK14202","KP14102","KP14203","KD14203","KD14403"};
    private int[] doQuantity = {dOrder.getDOProductQuantity(labels[0]),dOrder.getDOProductQuantity(labels[1]),
                                dOrder.getDOProductQuantity(labels[2]),dOrder.getDOProductQuantity(labels[3]),
                                dOrder.getDOProductQuantity(labels[4]),dOrder.getDOProductQuantity(labels[5]),
                                dOrder.getDOProductQuantity(labels[6]),dOrder.getDOProductQuantity(labels[7]),
                                dOrder.getDOProductQuantity(labels[8]),dOrder.getDOProductQuantity(labels[9]),
                                dOrder.getDOProductQuantity(labels[10])};
    private double[] doIncome = {dOrder.getDOProductPrice(labels[0]),dOrder.getDOProductPrice(labels[1]),
                                 dOrder.getDOProductPrice(labels[2]),dOrder.getDOProductPrice(labels[3]),
                                 dOrder.getDOProductPrice(labels[4]),dOrder.getDOProductPrice(labels[5]),
                                 dOrder.getDOProductPrice(labels[6]),dOrder.getDOProductPrice(labels[7]),
                                 dOrder.getDOProductPrice(labels[8]),dOrder.getDOProductPrice(labels[9]),
                                 dOrder.getDOProductPrice(labels[10])};
    private int[] poQuantity = {pOrder.getPOProductQuantity(labels[0]),pOrder.getPOProductQuantity(labels[1]),
                                pOrder.getPOProductQuantity(labels[2]),pOrder.getPOProductQuantity(labels[3]),
                                pOrder.getPOProductQuantity(labels[4]),pOrder.getPOProductQuantity(labels[5]),
                                pOrder.getPOProductQuantity(labels[6]),pOrder.getPOProductQuantity(labels[7]),
                                pOrder.getPOProductQuantity(labels[8]),pOrder.getPOProductQuantity(labels[9]),
                                pOrder.getPOProductQuantity(labels[10])};
    private double[] poIncome = {pOrder.getPOProductPrice(labels[0]),pOrder.getPOProductPrice(labels[1]),
                                 pOrder.getPOProductPrice(labels[2]),pOrder.getPOProductPrice(labels[3]),
                                 pOrder.getPOProductPrice(labels[4]),pOrder.getPOProductPrice(labels[5]),
                                 pOrder.getPOProductPrice(labels[6]),pOrder.getPOProductPrice(labels[7]),
                                 pOrder.getPOProductPrice(labels[8]),pOrder.getPOProductPrice(labels[9]),
                                 pOrder.getPOProductPrice(labels[10])};
    public String getSale(double doIncome, double poIncome){
        DecimalFormat newFormat = new DecimalFormat("0.00");
        sale = "";
        double difference = 0.0;
        double percent = 0.0;
        if(doIncome < poIncome){
            difference = poIncome - doIncome;
            percent = (difference)/poIncome*100;
            sale = "-"+newFormat.format(percent)+"%";
        }
        else if(doIncome > poIncome){
            difference = doIncome - poIncome;
            percent = (difference)/poIncome*100;
            sale = "+"+newFormat.format(percent)+"%";
        }
        return sale;
    }
    private String[] sales = {getSale(doIncome[0],poIncome[0]),getSale(doIncome[1],poIncome[1]),
                              getSale(doIncome[2],poIncome[2]),getSale(doIncome[3],poIncome[3]),
                              getSale(doIncome[4],poIncome[4]),getSale(doIncome[5],poIncome[5]),
                              getSale(doIncome[6],poIncome[6]),getSale(doIncome[7],poIncome[7]),
                              getSale(doIncome[8],poIncome[8]),getSale(doIncome[9],poIncome[9]),
                              getSale(doIncome[10],poIncome[10]),};
    private String[] columnNames = {"Product Barcode","SOLD","","PURCHASE","","SALES"};
    private Object[][] data = {{"","Total Quantity","Total Price","Total Quantity","Total Price",""},
                               {labels[0],doQuantity[0],doIncome[0],poQuantity[0],poIncome[0],sales[0]},
                               {labels[1],doQuantity[1],doIncome[1],poQuantity[1],poIncome[1],sales[1]},
                               {labels[2],doQuantity[2],doIncome[2],poQuantity[2],poIncome[2],sales[2]},
                               {labels[3],doQuantity[3],doIncome[3],poQuantity[3],poIncome[3],sales[3]},
                               {labels[4],doQuantity[4],doIncome[4],poQuantity[4],poIncome[4],sales[4]},
                               {labels[5],doQuantity[5],doIncome[5],poQuantity[5],poIncome[5],sales[5]},
                               {labels[6],doQuantity[6],doIncome[6],poQuantity[6],poIncome[6],sales[6]},
                               {labels[7],doQuantity[7],doIncome[7],poQuantity[7],poIncome[7],sales[7]},
                               {labels[8],doQuantity[8],doIncome[8],poQuantity[8],poIncome[8],sales[8]},
                               {labels[9],doQuantity[9],doIncome[9],poQuantity[9],poIncome[9],sales[9]},
                               {labels[10],doQuantity[10],doIncome[10],poQuantity[10],poIncome[10],sales[10]}};
    private DefaultTableModel model = new DefaultTableModel(data, columnNames);
    private JTable jTable = new JTable(model);
    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable.getModel());
    private JTextField jtfFilter = new JTextField();
    private JButton jbtFilter = new JButton("Filter");
    public SalesPanel() {
        dOrder = new DOListSearchPanel();
        pOrder = new POListSearchPanel();
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
}