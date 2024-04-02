package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.InternshipMessages;
import seedu.address.logic.parser.InternshipFindCommandParser;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.InternshipContainsKeywordsPredicate;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Finds and lists all internships in internship data whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class InternshipFindCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "find";
    public static final String MODE_WITHALL = "withall";
    public static final String MODE_WITHANY = "withany";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all internships whose fields match the keywords "
            + "and displays them as a list with index numbers. \n"
            + "Parameters:\n"
            + "MODE: 'withall' or 'withany'. To specify if the search has to match every "
            + "prefix-keywords predicate or any prefix-keywords predicate.\n"
            + "KEYWORDS: One or more keywords separated by whitespace that will be matched against "
            + "the preceding prefix. With more than one keyword, internships with fields matching any of the keywords"
            + " will be accepted for the predicate, regardless of mode.\n"
            + "[" + PREFIX_COMPANY + " KEYWORDS]\n"
            + "[" + PREFIX_CONTACT_NAME + " KEYWORDS]\n"
            + "[" + PREFIX_LOCATION + " KEYWORDS]\n"
            + "[" + PREFIX_STATUS + " KEYWORDS]\n"
            + "[" + PREFIX_DESCRIPTION + " KEYWORDS]\n"
            + "[" + PREFIX_ROLE + " KEYWORDS]\n"
            + "[" + PREFIX_REMARK + " KEYWORDS]\n"
            + "Example: " + COMMAND_WORD + " withany "
            + PREFIX_COMPANY + " Tiktok Google " + PREFIX_STATUS + " accepted";
    public static final String NO_SEARCH_KEY_SPECIFIED = "At least one support field prefix and keyword "
            + "must be specified to be searched.\nSupported prefixes are "
            + Arrays.stream(InternshipFindCommandParser.getSupportedPrefixes())
                    .map(Prefix::toString).collect(Collectors.joining(", "));
    public static final String INVALID_MODE_SPECIFIED = "Invalid mode specified. "
            + "Please specify either 'withall' or 'withany'.";
    public static final String NO_KEYWORD_SPECIFIED = "At least one keyword must be specified for each field prefix.";

    private final InternshipContainsKeywordsPredicate predicate;

    public InternshipFindCommand(InternshipContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(InternshipModel model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(predicate);
        return new CommandResult(
                String.format(InternshipMessages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW,
                        model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipFindCommand)) {
            return false;
        }

        InternshipFindCommand otherFindCommand = (InternshipFindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
