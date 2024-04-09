package seedu.address.logic.parser;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 */
public class Prefix {
    private final String prefix;

    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public String toString() {
        return getPrefix();
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    public static String getPrefixesAsString(String delimiter, Prefix[] prefixes) {
        return Arrays.stream(prefixes)
                .map(Prefix::toString)
                .collect(Collectors.joining(delimiter));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Prefix)) {
            return false;
        }

        Prefix otherPrefix = (Prefix) other;
        return prefix.equals(otherPrefix.prefix);
    }
}
