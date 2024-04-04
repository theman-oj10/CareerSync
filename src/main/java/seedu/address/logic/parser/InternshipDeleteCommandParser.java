package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InternshipDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new InternshipDeleteCommand object
 */
public class InternshipDeleteCommandParser implements InternshipParser<InternshipDeleteCommand> {
    public InternshipDeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InternshipDeleteCommand.MESSAGE_USAGE));
        }

        try {
            index = InternshipParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX, pe);
        }

        return new InternshipDeleteCommand(index);
    }
}
