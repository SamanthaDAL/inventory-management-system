import javax.swing.*;
import java.awt.*;
public class QuantityAnalyticsPanel extends EmbeddedFrames.TabFrame {
    private String[] labels;
    private int[] values1;
    private int[] values2;
    public QuantityAnalyticsPanel(String[] labels, int[] values1, int[] values2) {
        this.labels = labels;
        this.values1 = values1;
        this.values2 = values2;
        setTitle("Quantity against product");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1600, 800);
        setLocationRelativeTo(null);
        JPanel chartPanel = new ChartPanel();
        getContentPane().add(chartPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    private class ChartPanel extends JPanel {
        private int[] barHeights1;
        private int[] barHeights2;
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            int barWidth = width / (labels.length * 4); // Adjust the divisor for narrower bars
            int maxBarHeight = height - 80; // Adjust the constant for shorter heights
            int maxValue = 1;
            for (int value : values1) {
                if (value > maxValue) {
                    maxValue = value;
                }
            }
            for (int value : values2) {
                if (value > maxValue) {
                    maxValue = value;
                }
            }
            barHeights1 = new int[labels.length];
            barHeights2 = new int[labels.length];
            for (int i = 0; i < labels.length; i++) {
                barHeights1[i] = (int) (((double) values1[i] / maxValue) * maxBarHeight);
                barHeights2[i] = (int) (((double) values2[i] / maxValue) * maxBarHeight);
                int x1 = i * barWidth * 4 + 50;
                int x2 = i * barWidth * 4 + barWidth + 50;
                int y1 = height - barHeights1[i] - 60; // Adjust the constant for shorter heights
                int y2 = height - barHeights2[i] - 60; // Adjust the constant for shorter heights
                g2d.setColor(Color.BLUE);
                g2d.fillRect(x1, y1, barWidth - 10, barHeights1[i]);
                g2d.setColor(Color.RED);
                g2d.fillRect(x2, y2, barWidth - 10, barHeights2[i]);
            }
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            for (int i = 0; i < labels.length; i++) {
                int x = i * barWidth * 4 + 50 + barWidth / 2;
                int y = height - 30;
                g2d.setColor(Color.BLACK);
                g2d.drawString(labels[i], x, y);
                g2d.setColor(Color.BLUE);
                String value1Label = String.valueOf(values1[i]);
                int value1Width = g2d.getFontMetrics().stringWidth(value1Label);
                g2d.drawString(value1Label, x - value1Width / 2-7, height - (int) barHeights1[i] - 65);
                g2d.setColor(Color.RED);
                String value2Label = String.valueOf(values2[i]);
                int value2Width = g2d.getFontMetrics().stringWidth(value2Label);
                g2d.drawString(value2Label, x - value2Width / 2+34, height - (int) barHeights2[i] - 65);
            }
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            g2d.setColor(Color.BLUE);
            g2d.drawString("Sold Quantity", 1, height / 2);
            g2d.setColor(Color.RED);
            g2d.drawString("Purchase Quantity", 1, height / 2 + 20);
            // Draw y-axis labels
            int maxValueLabel = Math.max(maxValue, 10); // Adjust if maxValue is too small
            int labelStep = maxValueLabel / 5;
            for (int i = 0; i <= 5; i++) {
                int value = labelStep * i;
                int y = height - (int) (((double) value / maxValueLabel) * maxBarHeight) - 55; // Adjust the constant for shorter heights
                g2d.setColor(Color.BLACK);
                g2d.drawString(String.valueOf(value), 30, y);
            }
            // Draw x-axis arrow
            int arrowX = width - 20;
            int arrowY1 = height - 70; // Adjust the constant for shorter heights
            int arrowY2 = height - 60; // Adjust the constant for shorter heights
            g2d.drawLine(arrowX, arrowY1, arrowX, arrowY2);
            g2d.drawLine(arrowX - 5, arrowY2, arrowX, arrowY2 + 5);
            g2d.drawLine(arrowX + 5, arrowY2, arrowX, arrowY2 + 5);
            // Draw y-axis arrow
            int arrowX1 = 40;
            int arrowX2 = 50;
            int arrowY = height - maxBarHeight - 50; // Adjust the constant for shorter heights
            g2d.drawLine(arrowX1, arrowY, arrowX2, arrowY);
            g2d.drawLine(arrowX1, arrowY, arrowX1 + 5, arrowY - 5);
            g2d.drawLine(arrowX1, arrowY, arrowX1 + 5, arrowY + 5);
        }
    }
}