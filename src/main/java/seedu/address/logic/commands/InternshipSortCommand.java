package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.InternshipMessages;
import seedu.address.logic.parser.InternshipSortCommandParser;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.Internship;

/**
 * Sorts all internships in the Internship Data based on specified field and displays them as a list with index numbers.
 */
public class InternshipSortCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "sort";
    public static final String ORDER_ASCENDING = "asc";
    public static final String ORDER_DESCENDING = "desc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all internships based on specified field"
            + " (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: FIELD (One of " + PREFIX_COMPANY + " COMPANY_NAME_KEYWORD\n"
            + PREFIX_CONTACT_NAME + " CONTACT_NAME_KEYWORD\n"
            + PREFIX_CONTACT_NUMBER + " CONTACT_NUMBER_KEYWORD\n"
            + PREFIX_CONTACT_EMAIL + " CONTACT_EMAIL_KEYWORD\n"
            + PREFIX_LOCATION + " LOCATION_KEYWORD\n"
            + PREFIX_STATUS + " STATUS_KEYWORD\n"
            + PREFIX_DESCRIPTION + " DESCRIPTION_KEYWORD\n"
            + PREFIX_ROLE + " ROLE_KEYWORD\n"
            + PREFIX_REMARK + " REMARK_KEYWORD\n"
            + "ORDER(" + ORDER_ASCENDING + " or " + ORDER_DESCENDING + ", to specify ascending or descending order)\n"
            + "Example: " + COMMAND_WORD + " /com " + ORDER_ASCENDING + "\n";
    public static final String MESSAGE_INVALID_FIELD = "Invalid field specified. Please specify one of the following:\n"
            + PREFIX_COMPANY + ": Company Name\n"
            + PREFIX_CONTACT_NAME + ": Contact Name\n"
            + PREFIX_CONTACT_NUMBER + ": Contact Number\n"
            + PREFIX_CONTACT_EMAIL + ": Contact Email\n"
            + PREFIX_LOCATION + ": Location\n"
            + PREFIX_STATUS + ": Status\n"
            + PREFIX_DESCRIPTION + ": Description\n"
            + PREFIX_ROLE + ": Role\n"
            + PREFIX_REMARK + ": Remark\n";
    public static final String MESSAGE_INVALID_ORDER = "Invalid order specified. Please specify either "
            + ORDER_ASCENDING + " to sort in ascending order or " + ORDER_DESCENDING
            + " to sort in descending order.";
    public static final String MESSAGE_NO_FIELD = "Please specify one of the following fields to sort by: "
            + PREFIX_COMPANY + ": Company Name\n"
            + PREFIX_CONTACT_NAME + ": Contact Name\n"
            + PREFIX_CONTACT_NUMBER + ": Contact Number\n"
            + PREFIX_CONTACT_EMAIL + ": Contact Email\n"
            + PREFIX_LOCATION + ": Location\n"
            + PREFIX_STATUS + ": Status\n"
            + PREFIX_DESCRIPTION + ": Description\n"
            + PREFIX_ROLE + ": Role\n"
            + PREFIX_REMARK + ": Remark\n";;

    public static final String MESSAGE_NO_ORDER = "Please specify the order to sort by: "
            + ORDER_ASCENDING + " to sort in ascending order or " + ORDER_DESCENDING + " to sort in descending order.";
    private final InternshipSortCommandParser.FieldEnum field;
    private final InternshipSortCommandParser.OrderEnum order;

    /**
     * Creates an InternshipSortCommand to sort internships based on the specified field and order.
     */
    public InternshipSortCommand(InternshipSortCommandParser.FieldEnum field,
                                 InternshipSortCommandParser.OrderEnum order) {
        requireNonNull(field);
        requireNonNull(order);
        this.field = field;
        this.order = order;
    }

    @Override
    public CommandResult execute(InternshipModel model) {
        requireNonNull(model);
        Comparator<Internship> comparator;

        if (order == InternshipSortCommandParser.OrderEnum.DESCENDING) {
            comparator = Internship.getComparator(field, false);
        } else {
            comparator = Internship.getComparator(field, true);
        }
        model.sortFilteredInternshipList(comparator);
        return new CommandResult(
                String.format(InternshipMessages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW,
                        model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof InternshipSortCommand)) {
            return false;
        }

        InternshipSortCommand otherCommand = (InternshipSortCommand) other;
        return this.field.equals(otherCommand.getField()) && this.order.equals(otherCommand.getOrder());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("field", field)
                .add("order", order)
                .toString();
    }

    public InternshipSortCommandParser.FieldEnum getField() {
        return field;
    }

    public InternshipSortCommandParser.OrderEnum getOrder() {
        return order;
    }
}

