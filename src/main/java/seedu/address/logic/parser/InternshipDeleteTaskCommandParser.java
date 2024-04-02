package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELECT_TASK;
import static seedu.address.logic.parser.InternshipParserUtil.MESSAGE_INVALID_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InternshipDeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new InternshipDeleteTaskCommand object
 */
public class InternshipDeleteTaskCommandParser implements InternshipParser<InternshipDeleteTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the InternshipDeleteTaskCommand
     * and returns an InternshipDeleteTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InternshipDeleteTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SELECT_TASK);

        Index internshipIndex;
        Index taskIndex;

        if (argMultimap.getPreamble().isEmpty() || argMultimap.getValue(PREFIX_SELECT_TASK).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InternshipDeleteTaskCommand.MESSAGE_USAGE));
        }

        try {
            internshipIndex = InternshipParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_INDEX, pe);
        }

        try {
            taskIndex = InternshipParserUtil.parseIndex(argMultimap.getValue(PREFIX_SELECT_TASK).get());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_INDEX, pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SELECT_TASK);

        return new InternshipDeleteTaskCommand(internshipIndex, taskIndex);
    }
}
