package seedu.address.logic.parser;

import seedu.address.logic.commands.InternshipSortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new InternshipSortCommand object
 */
public class InternshipSortCommandParser implements InternshipParser<InternshipSortCommand> {
    public enum field {
        COMPANY, CONTACT_NAME, CONTACT_NUMBER, CONTACT_EMAIL, DESCRIPTION, STATUS, LOCATION, ROLE, REMARK
    }
    public enum order {
        ASCENDING, DESCENDING
    }

    public InternshipSortCommand parse(String args) throws ParseException {
        String[] argsSplit = args.trim().split(" ");
        if (argsSplit.length != 2) {
            throw new ParseException("Invalid sort command format, enter both field and order.");
        }
        String inputField = argsSplit[0].toLowerCase().trim();
        field parsedField = field.COMPANY; // default sort by company_name
        String inputOrder = argsSplit[1].toLowerCase().trim();
        order parsedOrder = order.ASCENDING; // default sort in ascending order
        switch (inputField) {
        case "com":
            parsedField = field.COMPANY;
            break;
        case "poc":
            parsedField = field.CONTACT_NAME;
            break;
        case "phone":
            parsedField = field.CONTACT_NUMBER;
            break;
        case "email":
            parsedField = field.CONTACT_EMAIL;
            break;
        case "status":
            parsedField = field.STATUS;
            break;
        case "loc":
            parsedField = field.LOCATION;
            break;
        case "role":
            parsedField = field.ROLE;
            break;
        case "remark":
            parsedField = field.REMARK;
            break;
        case "desc":
            parsedField = field.DESCRIPTION;
            break;
        }
        if (inputOrder.equals("asc")) {
            parsedOrder = order.ASCENDING;
        } else if (inputOrder.equals("desc")) {
            parsedOrder = order.DESCENDING;
        } else {
            throw new ParseException("Invalid sort command format");
        }
        return new InternshipSortCommand(parsedField, parsedOrder);
    }

}
