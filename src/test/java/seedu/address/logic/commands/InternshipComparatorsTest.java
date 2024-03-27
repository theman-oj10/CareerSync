package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.internship.Internship;
import seedu.address.testutil.InternshipBuilder;

public class InternshipComparatorsTest {
    private InternshipBuilder internshipBuilder;

    @BeforeEach
    public void setUp() {
        internshipBuilder = new InternshipBuilder();
    }
    @Test
    public void byApplicationStatus_ascComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byApplicationStatus(true);
        Internship internship1 = internshipBuilder.withApplicationStatus("to_apply").build();
        Internship internship2 = internshipBuilder.withApplicationStatus("rejected").build();
        assertEquals(-1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byApplicationStatus_descComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byApplicationStatus(false);
        Internship internship1 = internshipBuilder.withApplicationStatus("to_apply").build();
        Internship internship2 = internshipBuilder.withApplicationStatus("rejected").build();
        assertEquals(1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byCompanyName_descComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byCompanyName(false);
        Internship internship1 = internshipBuilder.withCompanyName("AAA").build();
        Internship internship2 = internshipBuilder.withCompanyName("BBB").build();
        assertEquals(1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byCompanyName_ascComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byCompanyName(true);
        Internship internship1 = internshipBuilder.withCompanyName("AAA").build();
        Internship internship2 = internshipBuilder.withCompanyName("BBB").build();
        assertEquals(-1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byDescription_ascComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byDescription(true);
        Internship internship1 = internshipBuilder.withDescription("aaa").build();
        Internship internship2 = internshipBuilder.withDescription("bbb").build();
        assertEquals(-1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byDescription_descComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byDescription(false);
        Internship internship1 = internshipBuilder.withDescription("aaa").build();
        Internship internship2 = internshipBuilder.withDescription("bbb").build();
        assertEquals(1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byPhone_ascComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byPhone(true);
        Internship internship1 = internshipBuilder.withContactNumber("12345678").build();
        Internship internship2 = internshipBuilder.withContactNumber("23456789").build();
        assertEquals(-1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byPhone_descComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byPhone(false);
        Internship internship1 = internshipBuilder.withContactNumber("12345678").build();
        Internship internship2 = internshipBuilder.withContactNumber("23456789").build();
        assertEquals(1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byLocation_descComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byLocation(false);
        Internship internship1 = internshipBuilder.withLocation("remote").build();
        Internship internship2 = internshipBuilder.withLocation("overseas").build();
        assertEquals(-3, comparator.compare(internship1, internship2));
    }

    @Test
    public void byRole_ascComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byRole(true);
        Internship internship1 = internshipBuilder.withRole("aaa").build();
        Internship internship2 = internshipBuilder.withRole("bbb").build();
        assertEquals(-1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byRole_descComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byRole(false);
        Internship internship1 = internshipBuilder.withRole("aaa").build();
        Internship internship2 = internshipBuilder.withRole("bbb").build();
        assertEquals(1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byContactName_ascComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byContactName(true);
        Internship internship1 = internshipBuilder.withContactName("aaa").build();
        Internship internship2 = internshipBuilder.withContactName("bbb").build();
        assertEquals(-1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byContactName_descComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byContactName(false);
        Internship internship1 = internshipBuilder.withContactName("aaa").build();
        Internship internship2 = internshipBuilder.withContactName("bbb").build();
        assertEquals(1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byContactEmail_ascComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byContactEmail(true);
        Internship internship1 = internshipBuilder.withContactEmail("email1@gmail.com").build();
        Internship internship2 = internshipBuilder.withContactEmail("email2@gmail.com").build();
        assertEquals(-1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byContactEmail_descComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byContactEmail(false);
        Internship internship1 = internshipBuilder.withContactEmail("email1@gmail.com").build();
        Internship internship2 = internshipBuilder.withContactEmail("email2@gmail.com").build();
        assertEquals(1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byRemark_ascComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byRemark(true);
        Internship internship1 = internshipBuilder.withRemark("aaa").build();
        Internship internship2 = internshipBuilder.withRemark("bbb").build();
        assertEquals(-1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byRemark_descComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byRemark(false);
        Internship internship1 = internshipBuilder.withRemark("aaa").build();
        Internship internship2 = internshipBuilder.withRemark("bbb").build();
        assertEquals(1, comparator.compare(internship1, internship2));
    }
}

