package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyInternshipData;
import seedu.address.model.ReadOnlyInternshipUserPrefs;

/**
 * API of the InternshipStorage component
 */
public interface InternshipStorage extends InternshipUserPrefsStorage, InternshipDataStorage {

    /**
     * Returns the file path of the data file.
     */
    @Override
    Optional<InternshipUserPrefs> readUserPrefs() throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyInternshipUserPrefs} to the storage.
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    void saveUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) throws IOException;

    /**
     * Returns the file path of the data file.
     */
    @Override
    Path getInternshipDataFilePath();

    /**
     * Returns InternshipData data as a {@link ReadOnlyInternshipData}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    @Override
    Optional<ReadOnlyInternshipData> readInternshipData() throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyInternshipData} to the storage.
     * @param internshipData cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    void saveInternshipData(ReadOnlyInternshipData internshipData) throws IOException;

}
