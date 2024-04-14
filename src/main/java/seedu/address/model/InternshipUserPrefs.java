package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class InternshipUserPrefs implements ReadOnlyInternshipUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path internshipDataFilePath = Paths.get("data" , "internshipdata.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public InternshipUserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public InternshipUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyInternshipUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setInternshipDataFilePath(newUserPrefs.getInternshipDataFilePath());
    }

    /**
     * Returns the GuiSettings of the UserPrefs.
     * @return GuiSettings of the UserPrefs.
     */
    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    /**
     * Sets the GuiSettings of the UserPrefs.
     * @param guiSettings GuiSettings to be set.
     */
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    /**
     * Returns the Path of the internship data file.
     * @return Path of the internship data file.
     */
    public Path getInternshipDataFilePath() {
        return internshipDataFilePath;
    }

    /**
     * Sets the Path of the internship data file.
     * @param internshipDataFilePath Path of the internship data file to be set.
     */
    public void setInternshipDataFilePath(Path internshipDataFilePath) {
        requireNonNull(internshipDataFilePath);
        this.internshipDataFilePath = internshipDataFilePath;
    }

    /**
     * Returns true if both UserPrefs have the same GUI settings and internship data file path.
     * This defines a stronger notion of equality between two UserPrefs.
     * @param other Other UserPrefs to compare with.
     * @return True if both UserPrefs have the same GUI settings and internship data file path.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipUserPrefs)) {
            return false;
        }

        InternshipUserPrefs otherUserPrefs = (InternshipUserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && internshipDataFilePath.equals(otherUserPrefs.internshipDataFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, internshipDataFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + internshipDataFilePath);
        return sb.toString();
    }

}
