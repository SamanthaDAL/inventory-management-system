import javax.swing.*;
import java.awt.*;

/** Allows legacy secondary screens to appear as tabs rather than desktop windows. */
public final class EmbeddedFrames {
    private EmbeddedFrames() {}

    public static class TabFrame extends JFrame {
        private boolean hosted = false;
        public TabFrame() { super(); }
        public TabFrame(String title) { super(title); }
        @Override public void setVisible(boolean visible) {
            if (visible && !hosted && TabbedMenuGUI.getActive() != null) {
                hosted = true;
                Container body = getContentPane();
                setContentPane(new JPanel());
                TabbedMenuGUI.getActive().showDetailTab(getTitle(), body);
                super.dispose();
            } else if (!hosted) {
                super.setVisible(visible);
            }
        }
    }
}
