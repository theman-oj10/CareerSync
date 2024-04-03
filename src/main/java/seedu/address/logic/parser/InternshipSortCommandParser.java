package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.InternshipSortCommand.MESSAGE_EXTRA_ARGUMENTS;
import static seedu.address.logic.commands.InternshipSortCommand.ORDER_ASCENDING;
import static seedu.address.logic.commands.InternshipSortCommand.ORDER_DESCENDING;
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
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.InternshipComparators;
import seedu.address.logic.commands.InternshipSortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.Internship;

/**
 * Parses input arguments and creates a new InternshipSortCommand object
 */
public class InternshipSortCommandParser implements InternshipParser<InternshipSortCommand> {
    public static final Prefix[] SUPPORTED_PREFIXES = {
        PREFIX_COMPANY, PREFIX_CONTACT_NAME, PREFIX_CONTACT_NUMBER, PREFIX_CONTACT_EMAIL,
        PREFIX_LOCATION, PREFIX_STATUS, PREFIX_DESCRIPTION, PREFIX_ROLE, PREFIX_REMARK
    };

    private static final Logger logger = LogsCenter.getLogger(InternshipSortCommandParser.class);
    /** Enum of fields to sort by */
    public enum FieldEnum {
        COMPANY(PREFIX_COMPANY.getPrefix()),
        CONTACT_NAME(PREFIX_CONTACT_NAME.getPrefix()),
        CONTACT_NUMBER(PREFIX_CONTACT_NUMBER.getPrefix()),
        CONTACT_EMAIL(PREFIX_CONTACT_EMAIL.getPrefix()),
        DESCRIPTION(PREFIX_DESCRIPTION.getPrefix()),
        STATUS(PREFIX_STATUS.getPrefix()),
        LOCATION(PREFIX_LOCATION.getPrefix()),
        ROLE(PREFIX_ROLE.getPrefix()),
        REMARK(PREFIX_REMARK.getPrefix());

        private final String value;

        FieldEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        /**
         * Returns the {@code FieldEnum} based on the given prefix string.
         * @param prefixString prefix string
         * @return the {@code FieldEnum} based on the given prefix string
         */
        public static FieldEnum fromPrefixString(String prefixString) throws ParseException {
            for (FieldEnum field : FieldEnum.values()) {
                if (field.getValue().equals(prefixString)) {
                    return field;
                }
            }
            throw new ParseException(InternshipSortCommand.MESSAGE_NO_FIELD);
        }
    }

    /** Enum of order to sort by */
    public enum OrderEnum {
        ASCENDING(ORDER_ASCENDING), DESCENDING(ORDER_DESCENDING);
        private final String value;

        OrderEnum(String order) {
            this.value = order;
        }

        public String getValue() {
            return value;
        }

        /**
         * Returns the {@code OrderEnum} based on the given order.
         * @param order order to sort by
         * @return the {@code OrderEnum} based on the given order
         */
        public static OrderEnum getOrderEnum(String order) throws ParseException {
            requireNonNull(order);
            isValidOrder(order);
            if (order.equals(ORDER_ASCENDING)) {
                return ASCENDING;
            } else {
                return DESCENDING;
            }
        }

        /**
         * Returns true if the given order is valid.
         * @param trimmedOrder order to sort by
         * @return true if the given order is valid
         */
        public static boolean isValidOrder(String trimmedOrder) throws ParseException {
            requireNonNull(trimmedOrder);
            if (trimmedOrder.equals(ORDER_ASCENDING) || trimmedOrder.equals(ORDER_DESCENDING)) {
                return true;
            } else {
                throw new ParseException(InternshipSortCommand.MESSAGE_INVALID_ORDER);
            }
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the InternshipSortCommand
     * and returns an InternshipSortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InternshipSortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        logger.info("Parsing internship sort command with args: " + args);
        String trimmedArgs = args.trim();
        String[] splitArgs = trimmedArgs.split(" ");
        if (trimmedArgs.isEmpty()) {
            logger.warning("Internship sort command has no arguments");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipSortCommand.MESSAGE_INVALID_FIELD));
        }
        if (splitArgs.length != 2) {
            logger.warning("Internship sort command has invalid number of arguments");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_EXTRA_ARGUMENTS));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, SUPPORTED_PREFIXES);
        argMultimap.verifyNoDuplicatePrefixesFor(SUPPORTED_PREFIXES);

        if (!anyPrefixesPresent(argMultimap, SUPPORTED_PREFIXES)) {
            logger.warning("Internship sort command has no valid prefixes");
            throw new ParseException(InternshipSortCommand.MESSAGE_INVALID_FIELD);
        }
        OrderEnum parsedOrder = assignOrder(argMultimap);
        FieldEnum field = assignField(argMultimap);
        try {
            argMultimap.verifyNoExtraArguments(2);
        } catch (IllegalArgumentException e) {
            throw new ParseException(MESSAGE_EXTRA_ARGUMENTS);
        }
        return new InternshipSortCommand(field, parsedOrder);
    }

    /**
     * Assigns the field to sort by based on the prefixes present in the {@code ArgumentMultimap}.
     * @param argMultimap map of prefixes and their search keywords
     * @return the field to sort by
     */
    protected FieldEnum assignField(ArgumentMultimap argMultimap) throws ParseException {
        for (Prefix prefix : SUPPORTED_PREFIXES) {
            if (argMultimap.getValue(prefix).isPresent()) {
                String prefixString = prefix.getPrefix();
                return FieldEnum.fromPrefixString(prefixString);
            }
        }
        logger.warning("Internship sort command has no field entered");
        throw new ParseException(InternshipSortCommand.MESSAGE_INVALID_FIELD);
    }

    protected OrderEnum assignOrder(ArgumentMultimap argMultimap) throws ParseException {
        for (Prefix prefix : SUPPORTED_PREFIXES) {
            if (argMultimap.getValue(prefix).isPresent()) {
                String order = argMultimap.getValue(prefix).get();
                return OrderEnum.getOrderEnum(order);
            }
        }
        logger.warning("Internship sort command has no field entered");
        throw new ParseException(InternshipSortCommand.MESSAGE_NO_ORDER);
    }

    /**
     * Returns true if any of the prefixes are present in the {@code ArgumentMultimap}.
     * @param argumentMultimap map of prefixes and their search keywords
     * @param prefixes prefixes to check for
     * @return true if any of the prefixes are present in the {@code ArgumentMultimap}
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes)
            throws ParseException {
        for (Prefix prefix : prefixes) {
            Optional<String> value = argumentMultimap.getValue(prefix);
            if (value.isPresent()) {
                if (OrderEnum.isValidOrder(value.get())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Comparator<Internship> getComparator(InternshipSortCommandParser.FieldEnum field,
                                                       boolean isAscending) {
        switch (field) {
        case COMPANY:
            return InternshipComparators.byCompanyName(isAscending);
        case CONTACT_NAME:
            return InternshipComparators.byContactName(isAscending);
        case CONTACT_NUMBER:
            return InternshipComparators.byPhone(isAscending);
        case CONTACT_EMAIL:
            return InternshipComparators.byContactEmail(isAscending);
        case STATUS:
            return InternshipComparators.byApplicationStatus(isAscending);
        case LOCATION:
            return InternshipComparators.byLocation(isAscending);
        case ROLE:
            return InternshipComparators.byRole(isAscending);
        case REMARK:
            return InternshipComparators.byRemark(isAscending);
        case DESCRIPTION:
            return InternshipComparators.byDescription(isAscending);
        default:
            throw new IllegalArgumentException("Invalid field for sorting: " + field);
        }
    }
}
