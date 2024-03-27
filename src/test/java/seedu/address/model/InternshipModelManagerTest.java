package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.InternshipComparators;
import seedu.address.logic.parser.InternshipSortCommandParser;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Remark;
import seedu.address.model.internship.Role;
import seedu.address.testutil.InternshipBuilder;

public class InternshipModelManagerTest {

    private final InternshipModelManager internshipModelManager = new InternshipModelManager();

    private final Internship internship = new Internship(
            new CompanyName("Amazon"),
            new ContactName("Mark Johnson"),
            new ContactEmail("markjohnson@example.com"),
            new ContactNumber("45678901"),
            new Location("remote"),
            new ApplicationStatus("rejected"),
            new Description("Business Development Internship"),
            new Role("Business Development Associate"),
            new Remark("")
    );

    @Test
    public void constructor() {
        ReadOnlyInternshipData internshipData = new InternshipData();
        ReadOnlyInternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipModelManager modelManager = new InternshipModelManager(internshipData, userPrefs);
        assertEquals(internshipData, modelManager.getInternshipData());
        assertEquals(userPrefs, modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(Paths.get("data", "internshipdata.json"), modelManager.getInternshipDataFilePath());
    }

    @Test
    public void addInternship() {
        internshipModelManager.addInternship(internship);
        assertTrue(internshipModelManager.hasInternship(internship));
    }

    @Test
    public void setInternshipDataFilePath() {
        Path path = Paths.get("test.json");
        internshipModelManager.setInternshipDataFilePath(path);
        assertEquals(path, internshipModelManager.getInternshipDataFilePath());
    }

    @Test
    public void getFilteredInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                internshipModelManager.getFilteredInternshipList().remove(0));
    }

    @Test
    public void updateFilteredInternshipList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internshipModelManager.updateFilteredInternshipList(null));
    }

    @Test
    public void sortInternshipList_nullComparator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internshipModelManager.sortFilteredInternshipList(null));
    }

    @Test
    public void sortInternshipList_validComparator_success() {
        internshipModelManager.addInternship(internship);
        internshipModelManager.sortFilteredInternshipList(Internship.getComparator(
                InternshipSortCommandParser.FieldEnum.COMPANY, true));
        assertEquals(internship, internshipModelManager.getFilteredInternshipList().get(0));
    }
    @Test
    public void equals() {
        InternshipData internshipData = new InternshipData();
        internshipData.addInternship(internship);
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipModelManager internshipModelManager = new InternshipModelManager(internshipData, userPrefs);

        // same values -> returns true
        InternshipModelManager modelManagerCopy = new InternshipModelManager(internshipData, userPrefs);
        assertTrue(internshipModelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(internshipModelManager.equals(internshipModelManager));
    }

    @Test
    public void hasInternship() {
        internshipModelManager.addInternship(internship);
        assertTrue(internshipModelManager.hasInternship(internship));
    }

    @Test
    public void deleteInternship() {
        internshipModelManager.addInternship(internship);
        internshipModelManager.deleteInternship(internship);
        assertTrue(!internshipModelManager.hasInternship(internship));
    }

    @Test
    public void setInternship() {
        Internship internship1 = new Internship(
                new CompanyName("Amazon"),
                new ContactName("Mark Johnson"),
                new ContactEmail("markjohnson@example.com"),
                new ContactNumber("45678901"),
                new Location("remote"),
                new ApplicationStatus("rejected"),
                new Description("Business Development Internship"),
                new Role("Business Development Associate"),
                new Remark("")
        );
        Internship internship2 = new Internship(
                new CompanyName("Google"),
                new ContactName("John Doe"),
                new ContactEmail("johndoe@example.com"),
                new ContactNumber("12345678"),
                new Location("remote"),
                new ApplicationStatus("pending"),
                new Description("Software Engineering Internship"),
                new Role("Software Engineer"),
                new Remark("")
        );
        internshipModelManager.addInternship(internship1);
        internshipModelManager.setInternship(internship1, internship2);
        assertTrue(!internshipModelManager.hasInternship(internship1));
        assertTrue(internshipModelManager.hasInternship(internship2));
    }

    @Test
    public void getInternshipData() {
        InternshipData internshipData = new InternshipData();
        internshipData.addInternship(internship);
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipModelManager internshipModelManager = new InternshipModelManager(internshipData, userPrefs);
        assertEquals(internshipData, internshipModelManager.getInternshipData());
    }

    @Test
    public void getInternshipUserPrefs() {
        InternshipData internshipData = new InternshipData();
        internshipData.addInternship(internship);
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipModelManager internshipModelManager = new InternshipModelManager(internshipData, userPrefs);
        assertEquals(userPrefs, internshipModelManager.getUserPrefs());
    }

    @Test
    public void getGuiSettings() {
        InternshipData internshipData = new InternshipData();
        internshipData.addInternship(internship);
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipModelManager internshipModelManager = new InternshipModelManager(internshipData, userPrefs);
        assertEquals(new GuiSettings(), internshipModelManager.getGuiSettings());
    }

    @Test
    public void setGuiSettings() {
        InternshipData internshipData = new InternshipData();
        internshipData.addInternship(internship);
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipModelManager internshipModelManager = new InternshipModelManager(internshipData, userPrefs);
        GuiSettings guiSettings = new GuiSettings(1000, 1000, 100, 100);
        internshipModelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, internshipModelManager.getGuiSettings());
    }

    @Test
    public void setInternshipData() {
        InternshipData internshipData = new InternshipData();
        internshipData.addInternship(internship);
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipModelManager internshipModelManager = new InternshipModelManager(internshipData, userPrefs);
        InternshipData newInternshipData = new InternshipData();
        newInternshipData.addInternship(internship);
        internshipModelManager.setInternshipData(newInternshipData);
        assertEquals(newInternshipData, internshipModelManager.getInternshipData());
    }

    @Test
    public void getCompanyNameComparator() {
        InternshipBuilder internshipBuilder = new InternshipBuilder();
        Internship internship1 = internshipBuilder.withCompanyName("AAA").build();
        Internship internship2 = internshipBuilder.withCompanyName("BBB").build();
        Comparator<Internship> testComparator = Internship.getComparator(
                InternshipSortCommandParser.FieldEnum.COMPANY, true);
        Comparator<Internship> expectedComparator = InternshipComparators.byCompanyName(true);
        assertEquals(testComparator.compare(internship1, internship2),
                expectedComparator.compare(internship1, internship2));
    }

    @Test
    public void getDescriptionComparator() {
        InternshipBuilder internshipBuilder = new InternshipBuilder();
        Internship internship1 = internshipBuilder.withDescription("aaa").build();
        Internship internship2 = internshipBuilder.withDescription("bbb").build();
        Comparator<Internship> testComparator = Internship.getComparator(
                InternshipSortCommandParser.FieldEnum.DESCRIPTION, true);
        Comparator<Internship> expectedComparator = InternshipComparators.byDescription(true);
        assertEquals(testComparator.compare(internship1, internship2),
                expectedComparator.compare(internship1, internship2));
    }

    @Test
    public void getRoleComparator() {
        InternshipBuilder internshipBuilder = new InternshipBuilder();
        Internship internship1 = internshipBuilder.withRole("aaa").build();
        Internship internship2 = internshipBuilder.withRole("bbb").build();
        Comparator<Internship> testComparator = Internship.getComparator(
                InternshipSortCommandParser.FieldEnum.ROLE, true);
        Comparator<Internship> expectedComparator = InternshipComparators.byRole(true);
        assertEquals(testComparator.compare(internship1, internship2),
                expectedComparator.compare(internship1, internship2));
    }

    @Test
    public void getContactNameComparator() {
        InternshipBuilder internshipBuilder = new InternshipBuilder();
        Internship internship1 = internshipBuilder.withContactName("aaa").build();
        Internship internship2 = internshipBuilder.withContactName("bbb").build();
        Comparator<Internship> testComparator = Internship.getComparator(
                InternshipSortCommandParser.FieldEnum.CONTACT_NAME, true);
        Comparator<Internship> expectedComparator = InternshipComparators.byContactName(true);
        assertEquals(testComparator.compare(internship1, internship2),
                expectedComparator.compare(internship1, internship2));
    }

    @Test
    public void getContactNumberComparator() {
        InternshipBuilder internshipBuilder = new InternshipBuilder();
        Internship internship1 = internshipBuilder.withContactNumber("12345678").build();
        Internship internship2 = internshipBuilder.withContactNumber("23456789").build();
        Comparator<Internship> testComparator = Internship.getComparator(
                InternshipSortCommandParser.FieldEnum.CONTACT_NUMBER, true);
        Comparator<Internship> expectedComparator = InternshipComparators.byPhone(true);
        assertEquals(testComparator.compare(internship1, internship2),
                expectedComparator.compare(internship1, internship2));
    }

    @Test
    public void getLocationComparator() {
        InternshipBuilder internshipBuilder = new InternshipBuilder();
        Internship internship1 = internshipBuilder.withLocation("remote").build();
        Internship internship2 = internshipBuilder.withLocation("overseas").build();
        Comparator<Internship> testComparator = Internship.getComparator(
                InternshipSortCommandParser.FieldEnum.LOCATION, true);
        Comparator<Internship> expectedComparator = InternshipComparators.byLocation(true);
        assertEquals(testComparator.compare(internship1, internship2),
                expectedComparator.compare(internship1, internship2));
    }

    @Test
    public void getRemarkComparator() {
        InternshipBuilder internshipBuilder = new InternshipBuilder();
        Internship internship1 = internshipBuilder.withRemark("aaa").build();
        Internship internship2 = internshipBuilder.withRemark("bbb").build();
        Comparator<Internship> testComparator = Internship.getComparator(
                InternshipSortCommandParser.FieldEnum.REMARK, true);
        Comparator<Internship> expectedComparator = InternshipComparators.byRemark(true);
        assertEquals(testComparator.compare(internship1, internship2),
                expectedComparator.compare(internship1, internship2));
    }

    @Test
    public void getStatusComparator() {
        InternshipBuilder internshipBuilder = new InternshipBuilder();
        Internship internship1 = internshipBuilder.withApplicationStatus("to_apply").build();
        Internship internship2 = internshipBuilder.withApplicationStatus("rejected").build();
        Comparator<Internship> testComparator = Internship.getComparator(
                InternshipSortCommandParser.FieldEnum.STATUS, true);
        Comparator<Internship> expectedComparator = InternshipComparators.byApplicationStatus(true);
        assertEquals(testComparator.compare(internship1, internship2),
                expectedComparator.compare(internship1, internship2));
    }

    @Test
    public void getContactEmailComparator() {
        InternshipBuilder internshipBuilder = new InternshipBuilder();
        Internship internship1 = internshipBuilder.withContactEmail("email1@gmail.com").build();
        Internship internship2 = internshipBuilder.withContactEmail("email2@gmail.com").build();
        Comparator<Internship> testComparator = Internship.getComparator(
                InternshipSortCommandParser.FieldEnum.CONTACT_EMAIL, true);
        Comparator<Internship> expectedComparator = InternshipComparators.byContactEmail(true);
        assertEquals(testComparator.compare(internship1, internship2),
                expectedComparator.compare(internship1, internship2));
    }

    @Test
    public void getFilteredInternshipList() {
        assertEquals(internshipModelManager.getFilteredInternshipList(),
                internshipModelManager.getFilteredInternshipList());
    }
}
