package seedu.address.ui;

import static org.testfx.assertions.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import seedu.address.model.internship.Internship;
import seedu.address.testutil.TypicalInternships;

@ExtendWith(ApplicationExtension.class)
public class DetailedInternshipCardTest extends UiTestBase {

    private DetailedInternshipCard detailedInternshipCard;
    private Internship testInternship = TypicalInternships.AMY;

    @BeforeEach
    public void initInternshipCard() {
        detailedInternshipCard = new DetailedInternshipCard(testInternship);
    }
    @Test
    void getLabels() {
        // Test the company name and role label
        assertThat(this.detailedInternshipCard.companyNameAndRole)
                .hasText(testInternship.getCompanyName().companyName + " -- " + testInternship.getRole().get());

        // Test the status label
        assertThat(this.detailedInternshipCard.status)
                .hasText(" * " + testInternship.getApplicationStatus().toString());

        // Test the description label
        assertThat(this.detailedInternshipCard.description)
                .hasText("Description:\t\t" + testInternship.getDescription().toString());

        // Test the location label
        assertThat(this.detailedInternshipCard.loc)
                .hasText("Location:\t\t\t" + testInternship.getLocation().get());

        // Test the POC name label
        assertThat(this.detailedInternshipCard.cName)
                .hasText("Contact name:\t\t" + testInternship.getContactName().toString());

        // Test the POC email label
        assertThat(this.detailedInternshipCard.cEmail)
                .hasText("Contact email:\t\t" + testInternship.getContactEmail().toString());

        // Test the POC number label
        assertThat(this.detailedInternshipCard.cNum)
                .hasText("Contact number:\t" + testInternship.getContactNumber().toString());

        // Test the remark label
        assertThat(this.detailedInternshipCard.remark)
                .hasText("Remark:\t\t\t" + testInternship.getRemark().toString());

        // Test the tasks label
        assertThat(this.detailedInternshipCard.tasks)
                .hasText("\nTasks:\n" + testInternship.getTaskList().toString());
    }
}
