package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.InternshipLogic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class DetailedInternshipWindow extends UiPart<Stage> {

    private static final String FXML = "DetailedInternshipWindow.fxml";
    private Stage primaryStage;
    private InternshipLogic logic;
    private DetailedInternshipListPanel selectedInternshipListPanel;


    // Independent Ui parts residing in this Ui container

    private ResultDisplay resultDisplay;
    @FXML
    private StackPane selectedInternshipListPanelPlaceholder;
    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public DetailedInternshipWindow(Stage primaryStage, InternshipLogic logic) {
        super(FXML, new Stage());

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        fillInnerParts();
    }

    /**
     * Fills up all the placeholders of this window except for the internship list panel.
     */
    void fillInnerParts() {
        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getInternshipDataFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }


    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    public void hide() {
        getRoot().hide();
    }

    void show() {
        getRoot().show();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.InternshipLogic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException {
        String notImplementedMessage = "Commands are not implemented yet in detailed internship window";
        resultDisplay.setFeedbackToUser(notImplementedMessage);
        throw new CommandException(notImplementedMessage);
    }

    /**
     * Populates the detailed internship window with the selected internship details.
     */
    public void populateInternshipDetails() {
        selectedInternshipListPanel = new DetailedInternshipListPanel(logic.getSelectedInternship());
        selectedInternshipListPanelPlaceholder.getChildren().add(selectedInternshipListPanel.getRoot());
    }
}
