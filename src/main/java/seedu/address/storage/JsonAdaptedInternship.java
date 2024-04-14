package seedu.address.storage;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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
import seedu.address.model.internship.Task;
import seedu.address.model.internship.TaskList;

/**
 * Jackson-friendly version of {@link Internship}.
 */
public class JsonAdaptedInternship {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Internship's %s field is missing!";
    private final String companyName;
    private final String contactName;
    private final String contactEmail;
    private final String contactNumber;
    private final String applicationStatus;
    private final String location;
    private final String description;
    private final String role;
    private final String remark;
    private final ArrayList<Task> taskList;

    /**
     * Constructs a {@code JsonAdaptedInternship} with the given internship details.
     */
    @JsonCreator
    public JsonAdaptedInternship(@JsonProperty("companyName") String companyName,
                                 @JsonProperty("contactName") String contactName,
                                 @JsonProperty("contactEmail") String contactEmail,
                                 @JsonProperty("contactNumber") String contactNumber,
                                 @JsonProperty("location") String location,
                                 @JsonProperty("status") String applicationStatus,
                                 @JsonProperty("description") String description,
                                 @JsonProperty("role") String role,
                                 @JsonProperty("remark") String remark,
                                 @JsonProperty("taskList") ArrayList<Task> taskList) {
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.applicationStatus = applicationStatus;
        this.location = location;
        this.description = description;
        this.role = role;
        this.remark = remark;
        this.taskList = taskList;
    }

    /**
     * Converts a given {@code Internship} into this class for Jackson use.
     */
    public JsonAdaptedInternship(Internship source) {
        // Mandatory fields
        companyName = source.getCompanyName().companyName;
        contactName = source.getContactName().contactName;
        contactEmail = source.getContactEmail().value;
        contactNumber = source.getContactNumber().value;
        applicationStatus = source.getApplicationStatus().toString();
        description = source.getDescription().description;

        // Handle optional fields
        location = source.getLocation().toString();
        role = source.getRole().role;

        // Remark field
        remark = source.getRemark().toString();
        taskList = source.getTaskList().getArrayListTaskList();
    }

    /**
     * Converts this Jackson-friendly adapted internship object into the model's {@code Internship} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted internship.
     */
    public Internship toModelType() throws IllegalValueException {
        return new Internship(
                getValidCompanyName(),
                getValidContactName(),
                getValidContactEmail(),
                getValidContactNumber(),
                getValidLocation(),
                getValidApplicationStatus(),
                getValidDescription(),
                getValidRole(),
                getValidRemark(),
                getValidTaskList()
        );
    }

    private CompanyName getValidCompanyName() throws IllegalValueException {
        String companyName = this.companyName;
        if (companyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidCompanyName(companyName)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(companyName);
    }

    private ContactName getValidContactName() throws IllegalValueException {
        String contactName = this.contactName;
        if (contactName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ContactName.class.getSimpleName()));
        }
        if (!ContactName.isValidContactName(contactName)) {
            throw new IllegalValueException(ContactName.MESSAGE_CONSTRAINTS);
        }
        return new ContactName(contactName);
    }

    private ContactEmail getValidContactEmail() throws IllegalValueException {
        String contactEmail = this.contactEmail;
        if (contactEmail == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ContactEmail.class.getSimpleName()));
        }
        if (!ContactEmail.isValidContactEmail(contactEmail)) {
            throw new IllegalValueException(ContactEmail.MESSAGE_CONSTRAINTS);
        }
        return new ContactEmail(contactEmail);
    }

    private ContactNumber getValidContactNumber() throws IllegalValueException {
        String contactNumber = this.contactNumber;
        if (contactNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ContactNumber.class.getSimpleName()));
        }
        if (!ContactNumber.isValidContactNumber(contactNumber)) {
            throw new IllegalValueException(ContactNumber.MESSAGE_CONSTRAINTS);
        }
        return new ContactNumber(contactNumber);
    }

    private Location getValidLocation() throws IllegalValueException {
        String location = this.location;
        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(location);
    }

    private ApplicationStatus getValidApplicationStatus() throws IllegalValueException {
        String applicationStatus = this.applicationStatus;
        if (applicationStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ApplicationStatus.class.getSimpleName()));
        }
        if (!ApplicationStatus.isValidApplicationStatus(applicationStatus)) {
            throw new IllegalValueException(ApplicationStatus.MESSAGE_CONSTRAINTS);
        }
        return new ApplicationStatus(applicationStatus);
    }

    private Description getValidDescription() throws IllegalValueException {
        String description = this.description;
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(description);
    }

    private Role getValidRole() throws IllegalValueException {
        String role = this.role;
        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Role.class.getSimpleName()));
        }
        if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(role);
    }

    private Remark getValidRemark() throws IllegalValueException {
        String remark = this.remark;
        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Remark.class.getSimpleName()));
        }
        return new Remark(remark);
    }

    private TaskList getValidTaskList() throws IllegalValueException {
        ArrayList<Task> taskList = this.taskList;
        if (taskList == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskList.class.getSimpleName()));
        }
        return new TaskList(taskList);
    }
}
