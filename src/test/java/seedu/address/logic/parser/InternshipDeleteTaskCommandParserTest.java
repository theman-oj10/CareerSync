package seedu.address.logic.parser;

import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.address.logic.commands.InternshipCommandTestUtil.DEADLINE_DESC_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELECT_TASK;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_SECOND_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.InternshipMessages;
import seedu.address.logic.commands.InternshipDeleteTaskCommand;

public class InternshipDeleteTaskCommandParserTest {
    // deletetask INDEX /selecttask TASKINDEX
    private static final Index INDEX_SECOND_TASK = Index.fromOneBased(2);
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipDeleteTaskCommand.MESSAGE_USAGE);
    private InternshipDeleteTaskCommandParser parser = new InternshipDeleteTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        Index internshipIndex = INDEX_SECOND_INTERNSHIP;
        Index taskIndex = INDEX_SECOND_TASK;

        String userInputWithoutIndex = String.format("%s %d", PREFIX_SELECT_TASK,
                taskIndex.getOneBased());
        String userInputWithoutPrefixSelectTask = String.format("%d %d", internshipIndex.getOneBased(),
                taskIndex.getOneBased());
        String userInputWithoutTaskIndex = String.format("%d %s %s", internshipIndex.getOneBased(),
                PREFIX_SELECT_TASK, DEADLINE_DESC_AMY);

        // no internship index specified
        assertParseFailure(parser, userInputWithoutIndex, MESSAGE_INVALID_FORMAT);

        // no prefix select task
        assertParseFailure(parser, userInputWithoutPrefixSelectTask, MESSAGE_INVALID_FORMAT);

        // no task index specified
        assertParseFailure(parser, userInputWithoutTaskIndex, InternshipMessages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        Index internshipIndex = INDEX_SECOND_INTERNSHIP;
        Index taskIndex = INDEX_SECOND_TASK;

        String userInputNegativeInternshipIndex = String.format("%d %s %d", -5, PREFIX_SELECT_TASK,
                taskIndex.getOneBased());

        String userInputZeroInternshipIndex = String.format("%d %s %d", 0, PREFIX_SELECT_TASK,
                taskIndex.getOneBased());

        String userInputNegativeTaskIndex = String.format("%d %s %d", internshipIndex.getOneBased(),
                PREFIX_SELECT_TASK, -5);

        String userInputZeroTaskIndex = String.format("%d %s %d", internshipIndex.getOneBased(), PREFIX_SELECT_TASK,
                0);

        // negative index
        assertParseFailure(parser, userInputNegativeInternshipIndex, MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, userInputZeroInternshipIndex, MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);

        // negative index
        assertParseFailure(parser, userInputNegativeTaskIndex, MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, userInputZeroTaskIndex, MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index internshipIndex = INDEX_SECOND_INTERNSHIP;
        Index taskIndex = INDEX_SECOND_TASK;

        String userInput = String.format("%d %s %d", internshipIndex.getOneBased(), PREFIX_SELECT_TASK,
                taskIndex.getOneBased());

        InternshipDeleteTaskCommand expectedCommand = new InternshipDeleteTaskCommand(internshipIndex, taskIndex);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
