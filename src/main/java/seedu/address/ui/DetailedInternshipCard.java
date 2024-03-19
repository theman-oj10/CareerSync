package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Role;

/**
 * A UI component that displays information of a {@code Internship}.
 */
public class DetailedInternshipCard extends UiPart<Region> {

    private static final String FXML = "DetailedInternshipCard.fxml";
    private static final String preferredFontSizeStyle = "-fx-font-size: 16px";
    private static final String titleFontSizeStyle = "-fx-font-size: 22px";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private HBox cardPane;
    @FXML
    private Label companyNameAndRole;
    @FXML
    private Label status;
    @FXML
    private Label description;
    @FXML
    private Label loc;
    @FXML
    private Label cName;
    @FXML
    private Label cEmail;
    @FXML
    private Label cNum;
    @FXML
    private Label detailed;

    /**
     * Creates a {@code InternshipCard} with the given {@code Internship}.
     */
    public DetailedInternshipCard(Internship internship) {
        super(FXML);
        setStyles(true, companyNameAndRole, status, description, loc, cName, cEmail, cNum, detailed);
        setCompanyNameAndRoleLabel(internship.getCompanyName(), internship.getRole());
        setStatusLabel(internship.getApplicationStatus());
        setDescriptionLabel(internship.getDescription());
        setLocationLabel(internship.getLocation());
        setContactNameLabel(internship.getContactName());
        setContactEmailLabel(internship.getContactEmail());
        setContactNumberLabel(internship.getContactNumber());
        detailed.setText("DETAILED VIEW.\n Currently, the original view of the internship includes all fields"
                + "already. This is a placeholder for future implementation where detailed view will contain more"
                + "information than the original view.");
    }

    /**
     * Sets the company name and role label to the given company name and role.
     *
     * @param companyName name of the company under the Internship entry
     * @param role role of the internship under the Internship entry
     */
    private void setCompanyNameAndRoleLabel(CompanyName companyName, Role role) {
        String stringToSet = companyName.toString() + " -- " + role.toString();
        companyNameAndRole.setText(stringToSet);
    }

    /**
     * Sets the status label to the given application status.
     *
     * @param applicationStatus status of the application under the Internship entry
     */
    private void setStatusLabel(ApplicationStatus applicationStatus) {
        status.setText(" * " + applicationStatus.toString());
        setStatusLabelColour(applicationStatus.getStatus());
    }

    /**
     * Sets the description label to the given description.
     *
     * @param desc description of the internship
     */
    private void setDescriptionLabel(Description desc) {
        description.setText("Description:\t\t" + desc.toString());
    }

    /**
     * Sets the location label to the given location.
     *
     * @param loc location of the internship
     */
    private void setLocationLabel(Location loc) {
        this.loc.setText("Location:\t\t\t" + loc.toString());
    }

    /**
     * Sets the contact name label to the given contact name.
     * 
     * @param contactName name of the contact person under the Internship entry
     */
    private void setContactNameLabel(ContactName contactName) {
        cName.setText("Contact name:\t\t" + contactName.toString());
    }

    /**
     * Sets the contact email label to the given contact email.
     * 
     * @param contactEmail email of the contact person under the Internship entry
     */
    private void setContactEmailLabel(ContactEmail contactEmail) {
        cEmail.setText("Contact email:\t\t" + contactEmail.toString());
    }

    /**
     * Sets the contact number label to the given contact number.
     * 
     * @param contactNumber phone number of the contact person under the Internship entry
     */
    private void setContactNumberLabel(ContactNumber contactNumber) {
        cNum.setText("Contact number:\t" + contactNumber.toString());
    }

    /**
     * Sets the colour of the status label based on the status of the application.
     *
     * @param statusEnum status of the application under the Internship entry
     */
    private void setStatusLabelColour(ApplicationStatus.StatusEnum statusEnum) {
        switch (statusEnum) {
        case TO_APPLY:
            status.setTextFill(Color.GREEN);
            break;
        case PENDING:
            status.setTextFill(Color.YELLOW);
            break;
        case REJECTED:
            status.setTextFill(Color.RED);
            break;
        case ACCEPTED:
            status.setTextFill(Color.CYAN);
            break;
        case ONGOING:
            status.setTextFill(Color.LIGHTSEAGREEN);
            break;
        default:
            throw new IllegalArgumentException("Unexpected application status: "
                    + statusEnum);
        }
    }

    /**
     * Sets font size style for the given labels.
     * @param labels labels to set the style for
     */
    private void setStyles(boolean isFirstTitle, Label... labels) {
        for (Label label : labels) {
            if (isFirstTitle) {
                label.setStyle(titleFontSizeStyle);
                isFirstTitle = false;
            } else {
                label.setStyle(preferredFontSizeStyle);
            }
        }
    }

}
