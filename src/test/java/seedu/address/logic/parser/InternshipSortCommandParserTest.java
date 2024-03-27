package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.InternshipSortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class InternshipSortCommandParserTest {
    private final InternshipSortCommandParser parser = new InternshipSortCommandParser();

    @Test
    public void parseValidInput() throws ParseException {
        // Valid input: "com asc"
        InternshipSortCommand expectedCommand = new InternshipSortCommand(InternshipSortCommandParser.FieldEnum.COMPANY,
                InternshipSortCommandParser.OrderEnum.ASCENDING);
        assertEquals(expectedCommand, parser.parse("com asc"));

        // Valid input: "email desc"
        expectedCommand = new InternshipSortCommand(InternshipSortCommandParser.FieldEnum.CONTACT_EMAIL,
                InternshipSortCommandParser.OrderEnum.DESCENDING);
        assertEquals(expectedCommand, parser.parse("email desc"));
    }

    @Test
    public void parseInvalidInput() {
        // Invalid input: missing order
        assertThrows(ParseException.class, () -> parser.parse("com"));

        // Invalid input: invalid field
        assertThrows(ParseException.class, () -> parser.parse("invalid_field asc"));

        // Invalid input: invalid order
        assertThrows(ParseException.class, () -> parser.parse("com invalid_order"));
    }

    @Test
    public void parseEmptyField() {
        // Invalid input: empty field
        assertThrows(ParseException.class, () -> parser.parse(" asc"));
    }

    @Test
    public void parseEmptyOrder() {
        // Invalid input: empty order
        assertThrows(ParseException.class, () -> parser.parse("com "));
    }

    @Test
    public void parseEmptyFieldAndOrder() {
        // Invalid input: empty field and order
        assertThrows(ParseException.class, () -> parser.parse(" "));
    }

    @Test
    public void parseEmptyInput() {
        // Invalid input: empty input
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parseExtraArguments() {
        // Invalid input: extra arguments
        assertThrows(ParseException.class, () -> parser.parse("com asc extra"));
    }

}

