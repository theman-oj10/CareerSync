package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.InternshipMessages;
import seedu.address.logic.parser.InternshipSortCommandParser;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.Internship;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;
/**
 * Sorts all internships in the Internship Data based on specified field and displays them as a list with index numbers.
 */
public class InternshipSortCommand extends InternshipCommand {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all internships based on specified field"
            + " (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: FIELD\n"
            + "Example: " + COMMAND_WORD + " name\n";
    private final InternshipSortCommandParser.field field;
    private final InternshipSortCommandParser.order order;

    public InternshipSortCommand(InternshipSortCommandParser.field field, InternshipSortCommandParser.order order) {
        this.field = field;
        this.order = order;
    }

    @Override
    public CommandResult execute(InternshipModel model) {
        requireNonNull(model);
        Comparator<Internship> comparator = Internship.getComparator(field, true);
        if (order == InternshipSortCommandParser.order.DESCENDING) {
            comparator = Internship.getComparator(field, false);
        }
        model.sortFilteredPersonList(comparator);
        return new CommandResult(
                String.format(InternshipMessages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipSortCommand)) {
            return false;
        }

        InternshipSortCommand otherFindCommand = (InternshipSortCommand) other;
        return field.equals(otherFindCommand.field);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("field", field)
                .toString();
    }
}

