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
    public void byCompanyName_descComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byCompanyName(false);
        Internship internship1 = internshipBuilder.withCompanyName("AAA").build();
        Internship internship2 = internshipBuilder.withCompanyName("BBB").build();
        assertEquals(1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byDescription_ascComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byDescription(true);
        Internship internship1 = internshipBuilder.withDescription("aaa").build();
        Internship internship2 = internshipBuilder.withDescription("bbb").build();
        assertEquals(-1, comparator.compare(internship1, internship2));
    }

    @Test
    public void byPhone_ascComparator_correctOrder() {
        Comparator<Internship> comparator = InternshipComparators.byPhone(true);
        Internship internship1 = internshipBuilder.withContactNumber("12345678").build();
        Internship internship2 = internshipBuilder.withContactNumber("23456789").build();
        assertEquals(-1, comparator.compare(internship1, internship2));
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

}

