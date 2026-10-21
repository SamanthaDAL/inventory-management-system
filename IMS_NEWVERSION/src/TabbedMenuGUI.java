import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.function.Supplier;

/** Classic Swing appearance, two navigation rows and embedded details. */
public class TabbedMenuGUI extends JFrame {
    private static TabbedMenuGUI active;
    public static TabbedMenuGUI getActive() { return active; }
    private final JPanel mainNav = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 3));
    private final JPanel actionNav = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 5));
    private final JPanel workspace = new JPanel(new BorderLayout());
    private final JLabel message = new JLabel("", SwingConstants.CENTER);
    private final Map<String, java.util.List<JButton>> actionsByModule = new LinkedHashMap<>();
    private final Map<String, JButton> navButtons = new LinkedHashMap<>();
    private String currentModule = "Home";

    public TabbedMenuGUI() {
        super("SamLam Inventory Management System — New Version");
        active = this;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent event) {
                if (JOptionPane.showConfirmDialog(TabbedMenuGUI.this,
                        "Exit Inventory Management System?", "Exit", JOptionPane.YES_NO_OPTION)
                        == JOptionPane.YES_OPTION) { active = null; dispose(); }
            }
        });
        setSize(1000, 680);
        setMinimumSize(new Dimension(760, 480));
        setLocationRelativeTo(null);
        JPanel root = new JPanel(new BorderLayout());
        setContentPane(root);
        JPanel navigation = new JPanel(new BorderLayout());
        navigation.add(mainNav, BorderLayout.NORTH);
        navigation.add(actionNav, BorderLayout.SOUTH);
        root.add(navigation, BorderLayout.NORTH);
        root.add(workspace, BorderLayout.CENTER);
        register("Home", null);
        register("Stock", () -> new StockGUI(true));
        register("Barcode / Location", () -> new BarcodeScannerGUI(true));
        register("Purchase Orders", () -> new PurchaseOrderGUI(true));
        register("Delivery Orders", () -> new DeliveryOrderGUI(true));
        register("Reports", () -> new ReportGUI(true));
        selectModule("Home");
        setVisible(true);
    }

    private void register(String label, Supplier<? extends JFrame> factory) {
        JButton navigation = new JButton(label);
        navigation.addActionListener(e -> selectModule(label));
        mainNav.add(navigation);
        navButtons.put(label, navigation);
        java.util.List<JButton> buttons = new ArrayList<>();
        if (factory != null) {
            JFrame original = factory.get();
            collectButtons(original.getContentPane(), buttons);
            original.setContentPane(new JPanel());
            original.dispose();
        }
        actionsByModule.put(label, buttons);
    }

    private void collectButtons(Container panel, java.util.List<JButton> buttons) {
        for (Component c : panel.getComponents()) {
            if (c instanceof JButton) buttons.add((JButton)c);
            else if (c instanceof Container) collectButtons((Container)c, buttons);
        }
    }

    private void selectModule(String label) {
        currentModule = label;
        for (Map.Entry<String, JButton> entry : navButtons.entrySet())
            entry.getValue().setFont(entry.getValue().getFont().deriveFont(
                    entry.getKey().equals(label) ? Font.BOLD : Font.PLAIN));
        actionNav.removeAll();
        for (JButton source : actionsByModule.get(label)) {
            JButton button = new JButton(source.getText());
            button.addActionListener(e -> source.doClick());
            actionNav.add(button);
        }
        if (actionsByModule.get(label).isEmpty()) actionNav.add(new JLabel("Choose a feature above"));
        actionNav.revalidate(); actionNav.repaint();
        JPanel welcome = new JPanel(new GridBagLayout());
        String heading = label.equals("Home") ? "Inventory Management System" : "Welcome to SamLam's " + label;
        message.setText("<html><div style='text-align:center'><b>" + heading +
                "</b><p>" + (label.equals("Home") ? "Please choose a feature above" :
                "Please choose an action in the second row above") + "</p></div></html>");
        welcome.add(message);
        showContent(welcome);
    }

    private void showContent(Component content) {
        workspace.removeAll();
        workspace.add(new JScrollPane(content), BorderLayout.CENTER);
        workspace.revalidate(); workspace.repaint();
    }

    /** Existing legacy action handlers still work, but render here in the workspace. */
    public void showDetailTab(String title, Container panel) {
        showContent(panel);
    }
}
