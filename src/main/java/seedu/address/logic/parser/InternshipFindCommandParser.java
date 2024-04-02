package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.InternshipFindCommand.MODE_WITHALL;
import static seedu.address.logic.commands.InternshipFindCommand.MODE_WITHANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.InternshipParserUtil.anyPrefixesPresent;
import static seedu.address.logic.parser.InternshipParserUtil.prefixesPresentAreNotEmpty;

import java.util.Objects;

import seedu.address.logic.commands.InternshipFindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.InternshipContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new InternshipFindCommand object
 */
public class InternshipFindCommandParser implements InternshipParser<InternshipFindCommand> {
    private static final Prefix[] supportedPrefixes = {PREFIX_COMPANY, PREFIX_CONTACT_NAME, PREFIX_LOCATION,
        PREFIX_STATUS, PREFIX_DESCRIPTION, PREFIX_ROLE, PREFIX_REMARK};
    /**
     * Parses the given {@code String} of arguments in the context of the InternshipFindCommand
     * and returns a InternshipFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InternshipFindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (args.trim().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipFindCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, InternshipFindCommandParser.supportedPrefixes);

        if (!anyPrefixesPresent(argMultimap, InternshipFindCommandParser.supportedPrefixes)) {
            throw new ParseException(InternshipFindCommand.NO_SEARCH_KEY_SPECIFIED);
        }

        String mode = argMultimap.getPreamble().trim();
        if (!mode.equals(MODE_WITHALL) && !mode.equals(MODE_WITHANY)) {
            throw new ParseException(InternshipFindCommand.INVALID_MODE_SPECIFIED);
        }

        if (!prefixesPresentAreNotEmpty(argMultimap, InternshipFindCommandParser.supportedPrefixes)) {
            throw new ParseException(InternshipFindCommand.NO_KEYWORD_SPECIFIED);
        }

        return new InternshipFindCommand(createPredicate(mode, argMultimap));
    }

    /**
     * @param argMultimap map of prefixes and their search keywords
     * @return a list of predicates that correspond to the prefixes present in the map
     */
    protected InternshipContainsKeywordsPredicate createPredicate(String mode, ArgumentMultimap argMultimap) {
        InternshipContainsKeywordsPredicate predicate = new InternshipContainsKeywordsPredicate(
                argMultimap.getValue(PREFIX_COMPANY).orElse(null),
                argMultimap.getValue(PREFIX_CONTACT_NAME).orElse(null),
                argMultimap.getValue(PREFIX_LOCATION).orElse(null),
                argMultimap.getValue(PREFIX_STATUS).orElse(null),
                argMultimap.getValue(PREFIX_DESCRIPTION).orElse(null),
                argMultimap.getValue(PREFIX_ROLE).orElse(null),
                argMultimap.getValue(PREFIX_REMARK).orElse(null),
                Objects.equals(mode, MODE_WITHALL));

        return predicate;
    }

    /**
     * @return an array of the supported prefixes
     */
    public static Prefix[] getSupportedPrefixes() {
        return supportedPrefixes.clone();
    }
}
