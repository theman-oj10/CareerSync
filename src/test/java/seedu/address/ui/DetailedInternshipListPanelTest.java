package seedu.address.ui;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import seedu.address.logic.InternshipLogic;
import seedu.address.logic.InternshipLogicManager;
import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.internship.Internship;

@ExtendWith(ApplicationExtension.class)
public class DetailedInternshipListPanelTest extends UiTestBase {

    private DetailedInternshipListPanel detailedInternshipListPanel;
    private InternshipLogic testLogic;
    private InternshipModel testModel;

    @BeforeEach
    public void setUp() {
        testModel = new InternshipModelManager(getTypicalInternshipData(), new InternshipUserPrefs());
        testLogic = new InternshipLogicManager(testModel, null);
        detailedInternshipListPanel = new DetailedInternshipListPanel(testLogic.getFilteredInternshipList());
    }

    @Test
    public void selectInternship_index0_updatesLogicSelectedInternship() {
        detailedInternshipListPanel.getInternshipListView().getSelectionModel().select(1);
        Internship actualInternship = testLogic.getFilteredInternshipList().get(1);
        Internship selectedInternship = testLogic.getSelectedInternship().get(1);
        assertEquals(actualInternship, selectedInternship);
    }


}
