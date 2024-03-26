package seedu.address.logic.commands;

import java.util.Comparator;
import java.util.Map;

import seedu.address.model.internship.ApplicationStatus.StatusEnum;

import seedu.address.model.internship.Internship;

/**
 * Contains utility methods for sorting internships.
 */
public class InternshipComparators {
    private static final Map<StatusEnum, Integer> statusOrder = Map.of(
            StatusEnum.TO_APPLY, 0,
            StatusEnum.ONGOING, 1,
            StatusEnum.PENDING, 2,
            StatusEnum.ACCEPTED, 3,
            StatusEnum.REJECTED, 4
    );

    /**
     * Returns a comparator that compares two internships based on the application status.
     * @param ascending Whether to sort in ascending order.
     * @return A comparator that compares two internships based on the application status.
     */
    public static Comparator<Internship> byApplicationStatus(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship ->
                statusOrder.getOrDefault(internship.getApplicationStatus().getStatus(), Integer.MAX_VALUE));
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }
    /**
     * Returns a comparator that compares two internships based on the company name.
     * @param ascending Whether to sort in ascending order.
     * @return A comparator that compares two internships based on the company name.
     */
    public static Comparator<Internship> byCompanyName(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getCompanyName().toString(),
                String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    /**
     * Returns a comparator that compares two internships based on the description.
     * @param ascending Whether to sort in ascending order.
     * @return A comparator that compares two internships based on the description.
     */
    public static Comparator<Internship> byDescription(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getDescription().toString(),
                String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    /**
     * Returns a comparator that compares two internships based on the role.
     * @param ascending Whether to sort in ascending order.
     * @return A comparator that compares two internships based on the role.
     */
    public static Comparator<Internship> byRole(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getRole().toString(),
                String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    /**
     * Returns a comparator that compares two internships based on the contact name.
     * @param ascending Whether to sort in ascending order.
     * @return A comparator that compares two internships based on the contact name.
     */
    public static Comparator<Internship> byContactName(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getContactName().toString(),
                String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    /**
     * Returns a comparator that compares two internships based on the contact email.
     * @param ascending Whether to sort in ascending order.
     * @return A comparator that compares two internships based on the contact email.
     */
    public static Comparator<Internship> byContactEmail(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getContactEmail().toString(),
                String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    /**
     * Returns a comparator that compares two internships based on the contact number.
     * @param ascending Whether to sort in ascending order.
     * @return A comparator that compares two internships based on the contact number.
     */
    public static Comparator<Internship> byPhone(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getContactNumber().toString(),
                String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    /**
     * Returns a comparator that compares two internships based on the remark.
     * @param ascending Whether to sort in ascending order.
     * @return A comparator that compares two internships based on the remark.
     */
    public static Comparator<Internship> byRemark(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getRemark().toString(),
                String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    /**
     * Returns a comparator that compares two internships based on the location.
     * @param ascending Whether to sort in ascending order.
     * @return A comparator that compares two internships based on the location.
     */
    public static Comparator<Internship> byLocation(boolean ascending) {
        Comparator<Internship> comparator = Comparator.comparing(internship -> internship.getLocation().toString(),
                String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return comparator;
    }
}
