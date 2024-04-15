package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.internship.Internship;

/**
 * Panel containing the list of internships.
 */
public class DetailedInternshipListPanel extends UiPart<Region> {
    private static final String FXML = "InternshipListPanel.fxml";
    @FXML
    private ListView<Internship> internshipListView;

    /**
     * Creates a {@code DetailedInternshipListPanel} with the given {@code ObservableList}.
     */
    public DetailedInternshipListPanel(ObservableList<Internship> internshipList) {
        super(FXML);
        internshipListView.setItems(internshipList);
        internshipListView.setCellFactory(listView -> new InternshipListViewCell());
    }

    protected ListView<Internship> getInternshipListView() {
        return internshipListView;
    }
    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Internship} using a {@code InternshipCard}.
     */
    class InternshipListViewCell extends ListCell<Internship> {
        @Override
        protected void updateItem(Internship internship, boolean empty) {
            super.updateItem(internship, empty);

            if (empty || internship == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DetailedInternshipCard(internship).getRoot());
            }
        }
    }
}
