import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AnalyticsPanel extends JPanel {
    private POListSearchPanel pOrder;
    private DOListSearchPanel dOrder;
    private String[] labels = {"KT14303","KT14503","KT14203","KT14403","KT14603","KT14803","KK14202","KP14102","KP14203","KD14203","KD14403"};
    private JButton quantity;
    private JButton income;
    private JLabel label;
    AnalyticsPanel(){
        dOrder = new DOListSearchPanel();
        pOrder = new POListSearchPanel();
        label = new JLabel("Please choose the type of analytic\nyou would like to view");
        quantity = new JButton("Quantity analytics");
        income = new JButton("Income analytics");
        add(label);
        add(quantity);
        add(income);
        quantity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] values1 = new int[labels.length];
                for (int i=0; i<values1.length; i++){
                    values1[i] = dOrder.getDOProductQuantity(labels[i]);
                }
                int[] values2 = new int[labels.length];
                for (int i=0; i<values2.length; i++){
                    values2[i] = pOrder.getPOProductQuantity(labels[i]);
                }
                SwingUtilities.invokeLater(() -> new QuantityAnalyticsPanel(labels, values1, values2));
            }
        });
        income.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[] values1 = new double[labels.length];
                for (int i=0; i<values1.length; i++){
                    values1[i] = dOrder.getDOProductPrice(labels[i]);
                }
                double[] values2 = new double[labels.length];
                for (int i=0; i<values2.length; i++){
                    values2[i] = pOrder.getPOProductPrice(labels[i]);
                }
                SwingUtilities.invokeLater(() -> new IncomeAnalyticsPanel(labels, values1, values2));
            }
        });
    }
}