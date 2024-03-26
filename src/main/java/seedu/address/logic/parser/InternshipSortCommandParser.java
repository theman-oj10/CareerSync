package seedu.address.logic.parser;

import seedu.address.logic.commands.InternshipSortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new InternshipSortCommand object
 */
public class InternshipSortCommandParser implements InternshipParser<InternshipSortCommand> {

    /** Enum of fields to sort by */
    public enum FieldEnum {
        COMPANY, CONTACT_NAME, CONTACT_NUMBER, CONTACT_EMAIL, DESCRIPTION, STATUS, LOCATION, ROLE, REMARK
    }

    /** Enum of order to sort by */
    public enum OrderEnum {
        ASCENDING, DESCENDING
    }

    /**
     * Parses the given {@code String} of arguments in the context of the InternshipSortCommand
     * and returns an InternshipSortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InternshipSortCommand parse(String args) throws ParseException {
        String[] argsSplit = args.trim().split(" ");
        if (argsSplit.length != 2) {
            throw new ParseException("Invalid sort command format, enter both field and order.");
        }
        String inputField = argsSplit[0].toLowerCase().trim();
        FieldEnum parsedField = FieldEnum.COMPANY; // default sort by company_name
        String inputOrder = argsSplit[1].toLowerCase().trim();
        OrderEnum parsedOrder = OrderEnum.ASCENDING; // default sort in ascending order
        switch (inputField) {
        case "com":
            parsedField = FieldEnum.COMPANY;
            break;
        case "poc":
            parsedField = FieldEnum.CONTACT_NAME;
            break;
        case "phone":
            parsedField = FieldEnum.CONTACT_NUMBER;
            break;
        case "email":
            parsedField = FieldEnum.CONTACT_EMAIL;
            break;
        case "status":
            parsedField = FieldEnum.STATUS;
            break;
        case "loc":
            parsedField = FieldEnum.LOCATION;
            break;
        case "role":
            parsedField = FieldEnum.ROLE;
            break;
        case "remark":
            parsedField = FieldEnum.REMARK;
            break;
        case "desc":
            parsedField = FieldEnum.DESCRIPTION;
            break;
        default:
            throw new ParseException("Invalid sort command format");
        }
        if (inputOrder.equals("asc")) {
            parsedOrder = OrderEnum.ASCENDING;
        } else if (inputOrder.equals("desc")) {
            parsedOrder = OrderEnum.DESCENDING;
        } else {
            throw new ParseException("Invalid sort command format");
        }
        return new InternshipSortCommand(parsedField, parsedOrder);
    }

}
