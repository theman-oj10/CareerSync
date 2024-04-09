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

    @Override
    Optional<InternshipUserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) throws IOException;

    @Override
    Path getInternshipDataFilePath();

    @Override
    Optional<ReadOnlyInternshipData> readInternshipData() throws DataLoadingException;

    @Override
    void saveInternshipData(ReadOnlyInternshipData internshipData) throws IOException;

}
