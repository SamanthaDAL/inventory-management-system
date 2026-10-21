import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class ReportGUI extends JFrame {
    private JLabel titleLabel;
    private JLabel contentLabel1;
    private JButton sales;
    private JButton analytics;
    private SalesPanel salesPanel;
    private AnalyticsPanel analyticsPanel;
    public ReportGUI() {
        salesPanel = new SalesPanel();
        analyticsPanel = new AnalyticsPanel();
        setSize(400, 200);
        setTitle("SamLam's Inventory Management System");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(ReportGUI.this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }
        });
        setLayout(new BorderLayout());
        titleLabel = new JLabel("Welcome to SamLam's Report Generator");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        contentLabel1 = new JLabel("Please choose the action you will like to do");
        contentLabel1.setHorizontalAlignment(JLabel.CENTER);
        sales = new JButton("Sales");
        analytics = new JButton("Analytics");
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.add(titleLabel);
        titlePanel.add(contentLabel1);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sales);
        buttonPanel.add(analytics);
        add(titlePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
        sales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JFrame frame = new JFrame("Sales Report");
                        frame.add(salesPanel);
                        frame.pack();
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });
            }
        });
        analytics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JFrame frame = new JFrame("Analytics Report");
                        frame.add(analyticsPanel);
                        frame.pack();
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });
            }
        });
    }
}