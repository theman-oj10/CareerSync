package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
public class TaskListTest {

    @Test
    public void constructor_invalidTaskList_throwsIllegalArgumentException() {
        // invalid deadline
        assertThrows(IllegalArgumentException.class, () -> new TaskList("Submit Supporting Documents"
                + "(Invalid Deadline)"));
    }
    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Submit Supporting Documents"));
        taskList.addTask(new Task("Finish Resume"));
        assertEquals(taskList.getTaskListSize(), 2);
    }

    @Test
    public void deleteTaskTest() {
        TaskList taskList = new TaskList();
        Task task = new Task("Submit Supporting Documents");
        Task task2 = new Task("Finish Resume");
        taskList.addTask(task);
        taskList.addTask(task2);
        taskList.deleteTask(0);
        assertEquals(taskList.getTaskListSize(), 1);
        taskList.deleteTask(0);
        assertEquals(taskList.getTaskListSize(), 0);
    }

    @Test
    public void getTaskTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Submit Supporting Documents"));
        taskList.addTask(new Task("Finish Resume"));
        assertEquals(taskList.getTask(0), new Task("Submit Supporting Documents"));
        assertEquals(taskList.getTask(1), new Task("Finish Resume"));
    }

    @Test
    public void getTaskListSizeTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Submit Supporting Documents"));
        taskList.addTask(new Task("Finish Resume"));
        assertEquals(taskList.getTaskListSize(), 2);
    }

    @Test
    public void copyTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Submit Supporting Documents"));
        taskList.addTask(new Task("Finish Resume"));
        TaskList taskListCopy = taskList.copy();
        assertEquals(taskList, taskListCopy);
    }

    @Test
    public void equals() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Submit Supporting Documents", "24/04/2024"));
        taskList.addTask(new Task("Finish Resume", "25/05/2025"));

        // same values -> returns true
        TaskList taskList2 = new TaskList();
        taskList2.addTask(new Task("Submit Supporting Documents", "24/04/2024"));
        taskList2.addTask(new Task("Finish Resume", "25/05/2025"));
        assertTrue(taskList2.equals(taskList));

        // one task has a different deadline -> returns false
        TaskList taskList3 = new TaskList();
        taskList2.addTask(new Task("Submit Supporting Documents", "26/06/2026"));
        taskList2.addTask(new Task("Finish Resume", "25/05/2025"));
        assertFalse(taskList3.equals(taskList));

        // same object -> returns true
        assertTrue(taskList.equals(taskList));

        // null -> returns false
        assertFalse(taskList.equals(null));

        // different types -> returns false
        assertFalse(taskList.equals(5.0f));

        // different values -> returns false
        TaskList taskList4 = new TaskList();
        taskList3.addTask(new Task("Submit Supporting Documents"));
        assertFalse(taskList4.equals(taskList));
    }

    @Test
    public void string() {
        // without deadline
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Submit Supporting Documents"));
        taskList.addTask(new Task("Finish Resume"));
        assertEquals(taskList.toString(), "1. Submit Supporting Documents\n2. Finish Resume\n");

        // with deadline
        TaskList taskList2 = new TaskList();
        taskList2.addTask(new Task("Submit Supporting Documents", "24/04/2024"));
        taskList2.addTask(new Task("Finish Resume"));
        assertEquals(taskList2.toString(), "1. Submit Supporting Documents Deadline: 24/04/2024"
                + "\n2. Finish Resume\n");
    }
}
