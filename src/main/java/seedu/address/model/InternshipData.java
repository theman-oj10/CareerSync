package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.UniqueInternshipList;

/**
 * Wraps all data at the internship-display level
 * Duplicates are not allowed (by .isSameInternship comparison)
 */
public class InternshipData implements ReadOnlyInternshipData {

    private final UniqueInternshipList internshipList;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        internshipList = new UniqueInternshipList();
    }

    public InternshipData() {}

    /**
     * Creates an InternshipData using the Internships in the {@code toBeCopied}
     */
    public InternshipData(ReadOnlyInternshipData toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the internship list with {@code internships}.
     * {@code internships} must not contain duplicate internships.
     */
    public void setInternships(List<Internship> internships) {
        this.internshipList.setInternships(internships);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyInternshipData newData) {
        requireNonNull(newData);
        setInternships(newData.getInternshipList());
    }

    //// internship-level operations

    /**
     * Returns true if a internship with the same identity as {@code internship} exists in the address book.
     */
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return internshipList.contains(internship);
    }

    /**
     * Adds a internship to the address book.
     * The internship must not already exist in the address book.
     */
    public void addInternship(Internship i) {
        internshipList.add(i);
    }

    /**
     * Replaces the given internship {@code target} in the list with {@code editedInternship}.
     * {@code target} must exist in the address book.
     * The internship identity of {@code editedInternship} must not be the same as another existing internship in the
     * address book.
     */
    public void setInternship(Internship target, Internship editedInternship) {
        requireNonNull(editedInternship);

        internshipList.setInternship(target, editedInternship);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeInternship(Internship key) {
        internshipList.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Internship list", internshipList)
                .toString();
    }

    @Override
    public ObservableList<Internship> getInternshipList() {
        return internshipList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipData)) {
            return false;
        }

        InternshipData otherAddressBook = (InternshipData) other;
        return internshipList.equals(otherAddressBook.internshipList);
    }

    @Override
    public int hashCode() {
        return internshipList.hashCode();
    }
}
