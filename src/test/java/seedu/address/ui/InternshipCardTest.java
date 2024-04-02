package seedu.address.ui;

import static org.testfx.assertions.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import seedu.address.model.internship.Internship;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.TypicalInternships;

@ExtendWith(ApplicationExtension.class)
class InternshipCardTest extends UiTestBase {
    private InternshipCard internshipCard;
    private Internship testInternship = TypicalInternships.AMY;

    @BeforeEach
    public void initInternshipCard() {
        internshipCard = new InternshipCard(testInternship, 1);
    }
    @Test
    void getLabels() {
        // Test the id label
        assertThat(this.internshipCard.getIdLabel()).hasText("1. ");

        // Test the company name and role label
        assertThat(this.internshipCard.getCompanyNameAndRoleLabel())
                .hasText(testInternship.getCompanyName().companyName + " -- " + testInternship.getRole().get());

        // Test the status label
        assertThat(this.internshipCard.getStatusLabel())
                .hasText(" * " + testInternship.getApplicationStatus().toString());

        // Test the description label
        assertThat(this.internshipCard.getDescriptionLabel())
                .hasText(testInternship.getDescription().toString());

        // Test the location label
        assertThat(this.internshipCard.getLocationLabel())
                .hasText(testInternship.getLocation().get().toString());

        // Test the POC label
        assertThat(this.internshipCard.getPocLabel())
                .hasText("POC: " + testInternship.getContactName().toString() + " | "
                        + testInternship.getContactEmail().toString() + " | "
                        + testInternship.getContactNumber().toString());

        // Test the remark label
        assertThat(this.internshipCard.getRemarkLabel())
                .hasText("Remark: " + testInternship.getRemark().toString());
    }


    @Test
    void getLocationLabel_locationNotSet_labelIsEmpty() {
        // Create an internship with a null location
        Internship internshipWithNoLocation = new InternshipBuilder(testInternship).withLocation(null).build();
        InternshipCard internshipCard = new InternshipCard(internshipWithNoLocation, 1);

        // Check that the location label is an empty string
        assertThat(internshipCard.getLocationLabel()).hasText("");
    }

    @Test
    void checkStatusColours() {
        // TO_APPLY status -> Style should be font colour cyan
        testInternship = new InternshipBuilder(TypicalInternships.AMY).withApplicationStatus("TO_APPLY").build();
        internshipCard = new InternshipCard(testInternship, 1);
        assertThat(this.internshipCard.getStatusLabel().getStyle())
                .contains(InternshipCard.STYLE_WITH_FONT_COLOUR_CYAN);

        // PENDING status -> Style should be font colour yellow
        testInternship = new InternshipBuilder(TypicalInternships.AMY).withApplicationStatus("PENDING").build();
        internshipCard = new InternshipCard(testInternship, 1);
        assertThat(this.internshipCard.getStatusLabel().getStyle())
                .contains(InternshipCard.STYLE_WITH_FONT_COLOUR_YELLOW);

        // REJECTED status -> Style should be font colour red
        testInternship = new InternshipBuilder(TypicalInternships.AMY).withApplicationStatus("REJECTED").build();
        internshipCard = new InternshipCard(testInternship, 1);
        assertThat(this.internshipCard.getStatusLabel().getStyle())
                .contains(InternshipCard.STYLE_WITH_FONT_COLOUR_RED);

        // ACCEPTED status -> Style should be font colour green
        testInternship = new InternshipBuilder(TypicalInternships.AMY).withApplicationStatus("ACCEPTED").build();
        internshipCard = new InternshipCard(testInternship, 1);
        assertThat(this.internshipCard.getStatusLabel().getStyle())
                .contains(InternshipCard.STYLE_WITH_FONT_COLOUR_GREEN);

        // ONGOING status -> Style should be font colour lightseagreen
        testInternship = new InternshipBuilder(TypicalInternships.AMY).withApplicationStatus("ONGOING").build();
        internshipCard = new InternshipCard(testInternship, 1);
        assertThat(this.internshipCard.getStatusLabel().getStyle())
                .contains(InternshipCard.STYLE_WITH_FONT_COLOUR_LIGHTSEAGREEN);
    }
}
