package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.InternshipMessages;
import seedu.address.logic.parser.InternshipSortCommandParser;
import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.InternshipBuilder;

import java.util.Comparator;

public class InternshipSortCommandTest {
    private InternshipModel model;
    private InternshipSortCommandParser.FieldEnum fieldCompany;
    private InternshipSortCommandParser.FieldEnum fieldLocation;


    private InternshipSortCommandParser.FieldEnum fieldContactNumber;
    private InternshipSortCommandParser.OrderEnum ascending;
    private InternshipSortCommandParser.OrderEnum descending;

    private InternshipBuilder internshipBuilder;

    @BeforeEach
    public void setUp() {
        fieldCompany = InternshipSortCommandParser.FieldEnum.COMPANY;
        fieldLocation = InternshipSortCommandParser.FieldEnum.LOCATION;
        fieldContactNumber = InternshipSortCommandParser.FieldEnum.CONTACT_NUMBER;
        ascending = InternshipSortCommandParser.OrderEnum.ASCENDING;
        descending = InternshipSortCommandParser.OrderEnum.DESCENDING;
        model = new InternshipModelManager(getTypicalInternshipData(), new InternshipUserPrefs());
        internshipBuilder = new InternshipBuilder();
    }
    @Test
    public void constructor_nullField_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InternshipSortCommand(null, ascending));
    }

    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InternshipSortCommand(fieldCompany, null));
    }

    @Test
    public void constructor_validFieldAndOrder_success() {
        InternshipSortCommand sortNameCommand = new InternshipSortCommand(fieldCompany, ascending);
        InternshipSortCommand sortLocationCommand = new InternshipSortCommand(fieldLocation, descending);
        assertSame(fieldCompany, sortNameCommand.getField());
        assertSame(ascending, sortNameCommand.getOrder());
        assertSame(fieldLocation, sortLocationCommand.getField());
        assertSame(descending, sortLocationCommand.getOrder());
    }
    @Test
    public void equals() {
        InternshipSortCommand sortCompanyNameCommand = new InternshipSortCommand(fieldCompany, ascending);
        InternshipSortCommand sortLocationCommand = new InternshipSortCommand(fieldLocation, descending);

        // same object -> returns true
        assertTrue(sortCompanyNameCommand.equals(sortCompanyNameCommand));

        // same values -> returns true
        InternshipSortCommand sortNameCommandCopy = new InternshipSortCommand(fieldCompany, ascending);
        assertTrue(sortCompanyNameCommand.equals(sortNameCommandCopy));

        // different types -> returns false
        assertFalse(sortCompanyNameCommand.equals(1));

        // null -> returns false
        assertFalse(sortCompanyNameCommand.equals(null));

        // different field -> returns false
        assertFalse(sortCompanyNameCommand.equals(sortLocationCommand));
    }

    @Test
    public void execute_sortCompanyName_success() {
        InternshipModel expectedModel = new InternshipModelManager();
        Internship internship1 = internshipBuilder.withCompanyName("AAA").build();
        model.addInternship(internship1);
        Comparator<Internship> comparator = Internship.getComparator(fieldCompany, true);
        model.sortFilteredInternshipList(comparator);
        expectedModel.sortFilteredInternshipList(comparator);
        assertEquals("AAA", model.getFilteredInternshipList().get(0).getCompanyName().toString());
    }

    @Test
    public void execute_sortContactNumber_success() {
        InternshipModel expectedModel = new InternshipModelManager();
        Internship internship1 = internshipBuilder.withContactNumber("11111111").build();
        model.addInternship(internship1);
        Comparator<Internship> comparator = Internship.getComparator(fieldContactNumber, true);
        model.sortFilteredInternshipList(comparator);
        expectedModel.sortFilteredInternshipList(comparator);
        assertEquals("11111111", model.getFilteredInternshipList().get(0).getContactNumber().toString());
    }

    @Test
    public void execute_sortStatus_success() {
        InternshipModel expectedModel = new InternshipModelManager();
        Internship internship1 = internshipBuilder.withApplicationStatus("APPLIED").build();
        model.addInternship(internship1);
        Comparator<Internship> comparator = Internship.getComparator(InternshipSortCommandParser.FieldEnum.STATUS, true);
        model.sortFilteredInternshipList(comparator);
        expectedModel.sortFilteredInternshipList(comparator);
        assertEquals("APPLIED", model.getFilteredInternshipList().get(0).getApplicationStatus().toString());
    }
}