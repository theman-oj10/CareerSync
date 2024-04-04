package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;

import org.junit.jupiter.api.Test;

class PrefixTest {
    @Test
    void getPrefixesAsString() {
        // EP: Empty Prefix[]
        Prefix[] emptyPrefixes = new Prefix[]{};
        assertEquals("", Prefix.getPrefixesAsString(", ", emptyPrefixes));

        // EP: Single Prefix
        Prefix[] singlePrefixes = new Prefix[]{PREFIX_COMPANY};
        assertEquals("/com", Prefix.getPrefixesAsString(", ", singlePrefixes));

        // EP: Multiple Prefixes
        Prefix[] multiplePrefixes = new Prefix[]{PREFIX_COMPANY, PREFIX_CONTACT_NAME, PREFIX_LOCATION};
        assertEquals("/com, /poc, /loc", Prefix.getPrefixesAsString(", ", multiplePrefixes));
    }
}
