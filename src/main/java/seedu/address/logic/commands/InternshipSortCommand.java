package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all internships based on specified field"
            + " (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: FIELD\n"
            + "Example: " + COMMAND_WORD + " name\n";
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

