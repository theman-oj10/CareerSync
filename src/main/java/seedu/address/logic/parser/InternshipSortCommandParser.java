package seedu.address.logic.parser;

import seedu.address.logic.commands.InternshipFindCommand;
import seedu.address.logic.commands.InternshipSortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.InternshipFindCommand.MODE_WITHALL;
import static seedu.address.logic.commands.InternshipFindCommand.MODE_WITHANY;
import static seedu.address.logic.commands.InternshipSortCommand.ORDER_ASCENDING;
import static seedu.address.logic.commands.InternshipSortCommand.ORDER_DESCENDING;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

/**
 * Parses input arguments and creates a new InternshipSortCommand object
 */
public class InternshipSortCommandParser implements InternshipParser<InternshipSortCommand> {
    private static final Prefix[] supportedPrefixes = {PREFIX_COMPANY, PREFIX_CONTACT_NAME, PREFIX_LOCATION,
            PREFIX_STATUS, PREFIX_DESCRIPTION, PREFIX_ROLE, PREFIX_REMARK};
    /** Enum of fields to sort by */
    public enum FieldEnum {
        COMPANY, CONTACT_NAME, CONTACT_NUMBER, CONTACT_EMAIL, DESCRIPTION, STATUS, LOCATION, ROLE, REMARK
    }

    /** Enum of order to sort by */
    public enum OrderEnum {
        ASCENDING(ORDER_ASCENDING), DESCENDING(ORDER_DESCENDING);
        private final String value;

        public static final String MESSAGE_INVALID_ORDER = "Invalid order specified. Please specify either " + ORDER_ASCENDING
                + " or " + ORDER_DESCENDING + ".";;

        OrderEnum(String order) {
            this.value = order;
        }

        public String getValue() {
            return value;
        }
        public static boolean isValidOrder(String trimmedOrder) {
            return trimmedOrder.equals(ORDER_ASCENDING) || trimmedOrder.equals(ORDER_DESCENDING);
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the InternshipSortCommand
     * and returns an InternshipSortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InternshipSortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipSortCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, supportedPrefixes);

        if (!anyPrefixesPresent(argMultimap, supportedPrefixes)) {
            throw new ParseException(InternshipSortCommand.MESSAGE_INVALID_FIELD);
        }

        String order = trimmedArgs.split(" ")[1].trim();
        System.out.println(order);
        OrderEnum parsedOrder = InternshipParserUtil.parseOrder(order);
        FieldEnum field = assignField(argMultimap);
        return new InternshipSortCommand(field, parsedOrder);
    }

    /**
     * Assigns the field to sort by based on the prefixes present in the {@code ArgumentMultimap}.
     * @param argMultimap map of prefixes and their search keywords
     * @return the field to sort by
     */
    protected FieldEnum assignField(ArgumentMultimap argMultimap) {
        FieldEnum parsedField = FieldEnum.COMPANY; // default sort by company_name
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            parsedField = FieldEnum.COMPANY;
        } else if (argMultimap.getValue(PREFIX_CONTACT_NAME).isPresent()) {
            parsedField = FieldEnum.CONTACT_NAME;
        } else if (argMultimap.getValue(PREFIX_CONTACT_NUMBER).isPresent()) {
            parsedField = FieldEnum.CONTACT_NUMBER;
        } else if (argMultimap.getValue(PREFIX_CONTACT_EMAIL).isPresent()) {
            parsedField = FieldEnum.CONTACT_EMAIL;
        } else if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            parsedField = FieldEnum.STATUS;
        } else if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            parsedField = FieldEnum.LOCATION;
        } else if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            parsedField = FieldEnum.ROLE;
        } else if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            parsedField = FieldEnum.REMARK;
        } else if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            parsedField = FieldEnum.DESCRIPTION;
        }
        return parsedField;
    }

    /**
     * Returns true if any of the prefixes contains non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent() && !argumentMultimap
                .getValue(prefix).get().isEmpty());
    }
}
