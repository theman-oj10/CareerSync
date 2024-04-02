package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;
import static seedu.address.testutil.TypicalInternships.getTypicalInternships;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.InternshipMessages;
import seedu.address.model.InternshipData;
import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Task;

public class InternshipDeleteTaskCommandTest {
    private static final Index INDEX_FIRST_TASK = Index.fromOneBased(1);
    private static final Index INDEX_SECOND_TASK = Index.fromOneBased(2);

    private final InternshipModel model = new InternshipModelManager(getTypicalInternshipData(),
            new InternshipUserPrefs());

    @Test
    public void execute_internshipTask_success() {
        Internship internshipWithDeletedTask = getTypicalInternships().get(0);
        internshipWithDeletedTask.deleteTask(INDEX_FIRST_TASK.getZeroBased());
        Task taskToDelete = internshipWithDeletedTask.getTaskList().getTask(INDEX_FIRST_TASK.getZeroBased());
        InternshipDeleteTaskCommand deleteTaskCommand = new InternshipDeleteTaskCommand(INDEX_FIRST_INTERNSHIP,
                INDEX_FIRST_TASK);

        String expectedMessage = String.format(InternshipDeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS,
                taskToDelete);

        InternshipModel expectedModel = new InternshipModelManager(new InternshipData(model.getInternshipData()),
                new InternshipUserPrefs());
        expectedModel.setInternship(model.getFilteredInternshipList().get(0), internshipWithDeletedTask);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidInternshipIndexUnfilteredList_failure() {
        InternshipModel model = new InternshipModelManager(getTypicalInternshipData(),
                new InternshipUserPrefs());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        InternshipDeleteTaskCommand deleteTaskCommand = new InternshipDeleteTaskCommand(outOfBoundIndex,
                INDEX_FIRST_TASK);

        assertCommandFailure(deleteTaskCommand, model, InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTaskIndex_failure() {
        InternshipModel model = new InternshipModelManager(getTypicalInternshipData(),
                new InternshipUserPrefs());
        Internship internshipWithTask = getTypicalInternships().get(0);
        Index outOfBoundIndex = Index.fromOneBased(internshipWithTask.getTaskListSize() + 1);
        InternshipDeleteTaskCommand deleteTaskCommand = new InternshipDeleteTaskCommand(INDEX_FIRST_INTERNSHIP,
                outOfBoundIndex);

        assertCommandFailure(deleteTaskCommand, model, InternshipMessages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final InternshipDeleteTaskCommand standardCommand = new
                InternshipDeleteTaskCommand(INDEX_FIRST_INTERNSHIP, INDEX_FIRST_TASK);

        // same values -> returns true
        InternshipDeleteTaskCommand commandWithSameValues = new InternshipDeleteTaskCommand(INDEX_FIRST_INTERNSHIP,
                INDEX_FIRST_TASK);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new InternshipClearCommand()));

        // different internship index -> returns false
        assertFalse(standardCommand.equals(new InternshipDeleteTaskCommand(INDEX_SECOND_INTERNSHIP, INDEX_FIRST_TASK)));

        // different task index -> returns false
        assertFalse(standardCommand.equals(new InternshipDeleteTaskCommand(INDEX_FIRST_INTERNSHIP, INDEX_SECOND_TASK)));
    }

    @Test
    public void toStringMethod() {
        Index internshipIndex = Index.fromOneBased(1);
        Index taskIndex = Index.fromOneBased(1);
        InternshipDeleteTaskCommand deleteTaskCommand = new InternshipDeleteTaskCommand(internshipIndex, taskIndex);
        String expected = InternshipDeleteTaskCommand.class.getCanonicalName() + "{internshipIndex=" + internshipIndex
                + ", taskIndex=" + taskIndex + "}";
        assertEquals(expected, deleteTaskCommand.toString());
    }
}
