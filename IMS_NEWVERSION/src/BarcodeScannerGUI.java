import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class BarcodeScannerGUI extends JFrame {
    private JLabel titleLabel;
    private JLabel contentLabel1;
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 200;
    private JPanel panel1;
    private JButton search, addLocation;
    private String  addBarcode, addAisle, addShelf, addSection;
    private JLabel barcode;
    private JTextField barcodeText;
    private JLabel aisle;
    private JTextField aisleText;
    private JLabel shelf;
    private JTextField shelfText;
    private JLabel section;
    private JTextField sectionText;
    private JButton confirm;
    private JPanel panel;
    private LocationSearchPanel locationSearchPanel;
    private Location location;
    public BarcodeScannerGUI() {
        this(false);
    }

    public BarcodeScannerGUI(boolean embedded) {
        locationSearchPanel = new LocationSearchPanel();
        setTitle("SamLam's Inventory Management System");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(BarcodeScannerGUI.this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }
        });
        setLayout(new BorderLayout());
        titleLabel = new JLabel("Welcome to SamLam's Barcode Scanner");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        contentLabel1 = new JLabel("Please choose the action you will like to do");
        contentLabel1.setHorizontalAlignment(JLabel.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        search = new JButton("Search Product Location");
        addLocation = new JButton("Add Product Location");
        panel1 = new JPanel();
        panel1.add(search);
        panel1.add(addLocation);
        JPanel titlePanel = new JPanel(new GridLayout(4, 2));
        titlePanel.add(titleLabel);
        titlePanel.add(contentLabel1);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(search);
        buttonPanel.add(addLocation);
        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        if (!embedded) setVisible(true);
        search.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JFrame frame = new EmbeddedFrames.TabFrame("Product Location");
                        frame.add(locationSearchPanel);
                        frame.pack();
                        frame.setSize(1300, 700);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });
            }
        });
        addLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JFrame frame = new EmbeddedFrames.TabFrame("Add Product Location");
                        setSize(400,600);
                        setLayout(new BorderLayout());
                        GridLayout grid = new GridLayout(5, 2);
                        panel = new JPanel();
                        panel.setLayout(grid);
                        barcode = new JLabel("Product Barcode:");
                        barcodeText = new JTextField(20);
                        aisle = new JLabel("Aisle:");
                        aisleText = new JTextField(20);
                        shelf = new JLabel("Shelf:");
                        shelfText = new JTextField(20);
                        section = new JLabel("Section:");
                        sectionText = new JTextField(20);
                        confirm = new JButton("Confirm");
                        confirm.addActionListener(new ConfirmButtonListener());
                        panel.add(barcode);
                        panel.add(barcodeText);
                        panel.add(aisle);
                        panel.add(aisleText);
                        panel.add(shelf);
                        panel.add(shelfText);
                        panel.add(section);
                        panel.add(sectionText);
                        panel.add(confirm, BorderLayout.SOUTH);
                        frame.add(panel);
                        frame.pack();
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });
            }
            private class ConfirmButtonListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    addBarcode=barcodeText.getText();
                    addAisle=aisleText.getText();
                    addShelf=shelfText.getText();
                    addSection=sectionText.getText();
                    JOptionPane.showMessageDialog(null, "Product location added successfully!");
                    location = new Location(addBarcode, addAisle, addSection.charAt(0), addShelf.charAt(0));
                    String[] itemData = {location.getProductBarcode(), location.getAisle(), Character.toString(location.getSection()), Character.toString(location.getShelf())};
                    locationSearchPanel.addItem(itemData);
                }
            }
        });
    }
}