package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.internship.Internship;

/**
 * Panel containing the list of internships.
 */
public class InternshipListPanel extends UiPart<Region> {
    private static Internship lastSelectedInternship;
    private static final String FXML = "InternshipListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InternshipListPanel.class);

    @FXML
    private ListView<Internship> internshipListView;

    /**
     * Creates a {@code InternshipListPanel} with the given {@code ObservableList}.
     */
    public InternshipListPanel(ObservableList<Internship> internshipList) {
        super(FXML);
        internshipListView.setItems(internshipList);
        internshipListView.setCellFactory(listView -> new InternshipListViewCell());
    }

    /**
     * Handles the event when the user clicks on an internship in the list.
     * InternshipListViewCell updates the lastSelectedInternship whenever a cell is pressed. All we have to do
     * here is to return the lastSelectedInternship.
     * @return selected index
     */
    @FXML
    public Internship handleMouseClick() {
        if (lastSelectedInternship != null) {
            return lastSelectedInternship;
        }
        return null;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Internship} using a {@code InternshipCard}.
     */
    class InternshipListViewCell extends ListCell<Internship> {
        public InternshipListViewCell() {
            setOnMousePressed((MouseEvent event) -> {
                lastSelectedInternship = getItem();
                logger.info("User clicked on internship index: " + getIndex() + 1 + '\n' + lastSelectedInternship);
            });
        }

        @Override
        protected void updateItem(Internship internship, boolean empty) {
            super.updateItem(internship, empty);

            if (empty || internship == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new InternshipCard(internship, getIndex() + 1).getRoot());
            }
        }
    }
}
