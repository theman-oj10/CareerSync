package seedu.address.logic.commands;

import seedu.address.model.internship.Internship;

import java.util.Comparator;

public class InternshipComparators {

    public static Comparator<Internship> byCompanyName(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getCompanyName().toString(), String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    public static Comparator<Internship> byDescription(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getDescription().toString(), String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    public static Comparator<Internship> byRole(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getRole().toString(), String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    public static Comparator<Internship> byContactName(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getContactName().toString(), String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    public static Comparator<Internship> byContactEmail(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getContactEmail().toString(), String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    public static Comparator<Internship> byPhone(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getContactNumber().toString(), String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    public static Comparator<Internship> byApplicationStatus(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getApplicationStatus().toString(), String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    public static Comparator<Internship> byRemark(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getRemark().toString(), String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    public static Comparator<Internship> byLocation(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getLocation().toString(), String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }
}
