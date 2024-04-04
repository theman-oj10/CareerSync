package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.InternshipSortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class InternshipSortCommandParserTest {
    private final InternshipSortCommandParser parser = new InternshipSortCommandParser();

    @Test
    public void parseValidInput() throws ParseException {
        InternshipSortCommand expectedCommand = new InternshipSortCommand(InternshipSortCommandParser.FieldEnum.COMPANY,
                InternshipSortCommandParser.OrderEnum.ASCENDING);
        InternshipSortCommand actualCommand = parser.parse(" " + PREFIX_COMPANY.getPrefix() + " asc");
        assertEquals(expectedCommand, actualCommand);

        // Valid input: "email desc"
        expectedCommand = new InternshipSortCommand(InternshipSortCommandParser.FieldEnum.CONTACT_EMAIL,
                InternshipSortCommandParser.OrderEnum.DESCENDING);
        actualCommand = parser.parse(" " + PREFIX_CONTACT_EMAIL.getPrefix() + " desc");
        assertEquals(expectedCommand, actualCommand);
    }
    @Test
    public void parseEmptyField() {
        // Invalid input: empty field
        assertThrows(ParseException.class, () -> parser.parse(" asc"));
    }

    @Test
    public void parseEmptyOrder() {
        // Invalid input: empty order
        assertThrows(ParseException.class, () -> parser.parse(" " + PREFIX_COMPANY.getPrefix()));
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
        assertThrows(ParseException.class, () -> parser.parse(" " + PREFIX_COMPANY.getPrefix() + " asc extra"));
    }

    @Test
    public void parseMultiplePrefix() {
        // Invalid input: multiple prefixes
        assertThrows(ParseException.class, () -> parser.parse(" " + PREFIX_COMPANY.getPrefix() + " "
                + PREFIX_CONTACT_EMAIL.getPrefix() + " asc"));
    }

    @Test
    public void parseInvalidField() {
        // Invalid input: invalid field
        assertThrows(ParseException.class, () -> parser.parse(" invalid_field asc"));
    }

    @Test
    public void parseInvalidOrder() {
        // Invalid input: invalid order
        assertThrows(ParseException.class, () -> parser.parse(" " + PREFIX_COMPANY.getPrefix() + " invalid_order"));
    }

    @Test
    public void parseInvalidFieldAndOrder() {
        // Invalid input: invalid field and order
        assertThrows(ParseException.class, () -> parser.parse(" invalid_field invalid_order"));
    }

    @Test
    public void parseMultipleFields() {
        // Invalid input: multiple fields
        assertThrows(ParseException.class, () -> parser.parse(" " + PREFIX_COMPANY.getPrefix()
                + " asc " + PREFIX_CONTACT_EMAIL + " asc"));
    }

    @Test
    public void parseValidInput_contactNameAsc() throws ParseException {
        InternshipSortCommand expectedCommand = new InternshipSortCommand(
                InternshipSortCommandParser.FieldEnum.CONTACT_NAME,
                InternshipSortCommandParser.OrderEnum.ASCENDING
        );
        InternshipSortCommand actualCommand = parser.parse(" " + PREFIX_CONTACT_NAME.getPrefix() + " asc");
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void getValue() {
        assertEquals(PREFIX_COMPANY.getPrefix(), InternshipSortCommandParser.FieldEnum.COMPANY.getValue());
    }

    @Test
    public void fromPrefixString() {
        assertThrows(ParseException.class,  () -> InternshipSortCommandParser.FieldEnum.fromPrefixString("c"));
    }

}
