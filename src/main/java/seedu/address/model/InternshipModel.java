package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.internship.Internship;

/**
 * The API of the Model component.
 */
public interface InternshipModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Internship> PREDICATE_SHOW_ALL_INTERNSHIPS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyInternshipUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyInternshipUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getInternshipDataFilePath();

    /**
     * Sets the user prefs 'address book file path.
     */
    void setInternshipDataFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setInternshipData(ReadOnlyInternshipData internshipData);

    /** Returns the AddressBook */
    ReadOnlyInternshipData getInternshipData();

    /**
     * Returns true if an internship with the same identity as {@code internship} exists in the internship list.
     */
    boolean hasInternship(Internship internship);

    /**
     * Deletes the given internship.
     * The internship must exist in the address book.
     */
    void deleteInternship(Internship target);

    /**
     * Adds the given internship.
     * {@code internship} must not already exist in the address book.
     */
    void addInternship(Internship internship);

    /**
     * Replaces the given internship {@code target} with {@code editedInternship}.
     * {@code target} must exist in the address book.
     * The internship identity of {@code editedInternship} must not be the same as another existing internship in the
     * internship list.
     */
    void setInternship(Internship target, Internship editedInternship);

    /** Returns an unmodifiable view of the filtered internship list */
    ObservableList<Internship> getFilteredInternshipList();

    /**
     * Updates the filter of the filtered internship list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternshipList(Predicate<Internship> predicate);

    /**
     * Sorts the filtered internship list according to the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortFilteredInternshipList(Comparator<Internship> comparator);
    /**
     * Sets the selected {@code internship} in the model selected internship.
     * This represents the internship that the user is currently viewing in the detailed internship window.
     */
    void setSelectedInternship(Internship internship);

    /**
     * Gets the model's selected internship, which represents the internship that the user is currently viewing in the
     * detailed internship window.
     */
    ObservableList<Internship> getSelectedInternship();
}
