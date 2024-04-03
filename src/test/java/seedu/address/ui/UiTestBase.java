package seedu.address.ui;

import org.junit.jupiter.api.BeforeAll;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * An abstract GUI Test class. Contains common setup code for GUI tests.
 */
public abstract class UiTestBase extends ApplicationTest {
    @BeforeAll
    public static void setupHeadlessMode() {
        // Ensure that tests run in headless mode (i.e., without displaying the GUI).
        if (Boolean.getBoolean("headless")) { // headless mode is enabled
            System.out.println("Headless mode enabled! Running tests without displaying the GUI.");
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
        } else {
            System.out.println("Headful testing enabled! GUI will be shown.");
        }
    }
}
