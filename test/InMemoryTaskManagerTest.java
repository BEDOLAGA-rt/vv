
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    private InMemoryTaskManager taskManager;
    private Task task1;
    private Task task2;
    private Epic epic1;
    private Epic epic2;
    private Subtask subtask1;
    private Subtask subtask2;



    @Test
    void addTask() {
        taskManager.addTask(task1);
        assertNotNull(task1.getId());
        assertEquals(1, taskManager.getTasks().size());
        assertTrue(taskManager.getTasks().containsKey(task1.getId()));
        assertEquals(task1, taskManager.getTasks().get(task1.getId()));
    }

    @Test
    void updateTask() {
        taskManager.addTask(task1);
        Task updatedTask = new Task(task1.getId(), "Обновленная задача 1", "Обновленное описание", Status.NEW);
        taskManager.updateTask(updatedTask);
        assertEquals(1, taskManager.getTasks().size());
        assertEquals(updatedTask, taskManager.getTask(task1.getId()));
    }

    @Test
    void getTask() {
        taskManager.addTask(task1);
        Task retrievedTask = taskManager.getTask(task1.getId());
        assertEquals(task1, retrievedTask);
        assertEquals(1, taskManager.history().size());
        assertEquals(task1, taskManager.history().get(0));
    }

    @Test
    void getTask_nonExistentId() {
        assertNull(taskManager.getTask(999));
        assertEquals(0, taskManager.history().size());
    }

    @Test
    void getTasks() {
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        HashMap<Integer, Task> tasks = taskManager.getTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.containsValue(task1));
        assertTrue(tasks.containsValue(task2));
    }

    @Test
    void deleteTask() {
        taskManager.addTask(task1);
        taskManager.deleteTask(task1.getId());
        assertEquals(0, taskManager.getTasks().size());
        assertEquals(0, taskManager.history().size());
    }

    @Test
    void deleteTask_nonExistentId() {
        taskManager.deleteTask(999);
        assertEquals(0, taskManager.getTasks().size());
        assertEquals(0, taskManager.history().size());
    }

    @Test
    void deleteAllTasks() {
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.deleteAllTasks();
        assertEquals(0, taskManager.getTasks().size());
    }

    @Test
    void addEpic() {
        taskManager.addEpic(epic1);
        assertNotNull(epic1.getId());
        assertEquals(1, taskManager.getEpics().size());
        assertTrue(taskManager.getEpics().containsKey(epic1.getId()));
        assertEquals(epic1, taskManager.getEpics().get(epic1.getId()));
        assertEquals(Status.NEW, epic1.getStatus());
    }

    @Test
    void updateEpic() {
        taskManager.addEpic(epic1);
        Epic updatedEpic = new Epic(epic1.getId(), "Обновленный эпик 1", "Обновленное описание");
        taskManager.updateEpic(updatedEpic);
        assertEquals(1, taskManager.getEpics().size());
        assertEquals(updatedEpic, taskManager.getEpic(epic1.getId()));
        assertEquals(Status.NEW, updatedEpic.getStatus()); // Статус должен остаться NEW, т.к. нет подзадач
    }

    @Test
    void getEpic() {
        taskManager.addEpic(epic1);
        Epic retrievedEpic = taskManager.getEpic(epic1.getId());
        assertEquals(epic1, retrievedEpic);
        assertEquals(1, taskManager.history().size());
        assertEquals(epic1, taskManager.history().get(0));}

    @Test
    void getEpic_nonExistentId() {
        assertNull(taskManager.getEpic(999));
        assertEquals(0, taskManager.history().size());
    }

    @Test
    void getEpics() {
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        HashMap<Integer, Epic> epics = taskManager.getEpics();
        assertEquals(2, epics.size());
        assertTrue(epics.containsValue(epic1));
        assertTrue(epics.containsValue(epic2));
    }

    @Test
    void deleteEpic() {
        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.deleteEpic(epic1.getId());
        assertEquals(0, taskManager.getEpics().size());
        assertEquals(0, taskManager.getSubtasks().size());
        assertEquals(0, taskManager.history().size()); // Удаление эпика удаляет и подзадачи из истории
    }

    @Test
    void deleteEpic_nonExistentId() {
        taskManager.deleteEpic(999);
        assertEquals(0, taskManager.getEpics().size());
        assertEquals(0, taskManager.getSubtasks().size());
        assertEquals(0, taskManager.history().size());
    }

    @Test
    void deleteAllEpics() {
        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addEpic(epic2);
        taskManager.deleteAllEpics();
        assertEquals(0, taskManager.getEpics().size());
        assertEquals(0, taskManager.getSubtasks().size());
    }

    @Test
    void addSubtask() {
        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        assertNotNull(subtask1.getId());
        assertEquals(1, taskManager.getSubtasks().size());
        assertTrue(taskManager.getSubtasks().containsKey(subtask1.getId()));
        assertEquals(subtask1, taskManager.getSubtasks().get(subtask1.getId()));
        assertTrue(epic1.getEpicSubtasks().contains(subtask1.getId()));
        assertEquals(Status.NEW, epic1.getStatus());
    }

    @Test
    void updateSubtask() {
        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        Subtask updatedSubtask = new Subtask(subtask1.getId(), "Обновленная подзадача 1", "Обновленное описание", Status.DONE, epic1);
        taskManager.updateSubtask(updatedSubtask);
        assertEquals(1, taskManager.getSubtasks().size());
        assertEquals(updatedSubtask, taskManager.getSubtask(subtask1.getId()));
        assertEquals(Status.DONE, epic1.getStatus());
    }

    @Test
    void getSubtask() {
        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        Subtask retrievedSubtask = taskManager.getSubtask(subtask1.getId());
        assertEquals(subtask1, retrievedSubtask);
        assertEquals(1, taskManager.history().size());
        assertEquals(subtask1, taskManager.history().get(0));
    }

    @Test
    void getSubtask_nonExistentId() {
        assertNull(taskManager.getSubtask(999));
        assertEquals(0, taskManager.history().size());
    }

    @Test
    void getSubtasks() {
        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        HashMap<Integer, Subtask> subtasks = taskManager.getSubtasks();
        assertEquals(2, subtasks.size());
        assertTrue(subtasks.containsValue(subtask1));
        assertTrue(subtasks.containsValue(subtask2));
    }

    @Test
    void deleteSubtask() {
        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        taskManager.deleteSubtask(subtask1.getId());
        assertEquals(0, taskManager.getSubtasks().size());
        assertFalse(epic1.getEpicSubtasks().contains(subtask1.getId()));
        assertEquals(Status.NEW, epic1.getStatus());
        assertEquals(0, taskManager.history().size());
    }

    @Test
    void deleteSubtask_nonExistentId() {
        taskManager.deleteSubtask(999);
        assertEquals(0, taskManager.getSubtasks().size());
        assertEquals(0, taskManager.history().size());
    }

    @Test
    void deleteAllSubtask() {taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.deleteAllSubtask();
        assertEquals(0, taskManager.getSubtasks().size());
        assertEquals(Status.NEW, epic1.getStatus());
    }

    @Test
    void history() {
        taskManager.addTask(task1);
        taskManager.getTask(task1.getId());
        taskManager.addEpic(epic1);
        taskManager.getEpic(epic1.getId());
        taskManager.addSubtask(subtask1);
        taskManager.getSubtask(subtask1.getId());
        assertEquals(6, taskManager.history().size());
        assertEquals(task1, taskManager.history().get(0));
        assertEquals(task1, taskManager.history().get(1));
        assertEquals(epic1, taskManager.history().get(2));
        assertEquals(epic1, taskManager.history().get(3));
        assertEquals(subtask1, taskManager.history().get(4));
        assertEquals(subtask1, taskManager.history().get(5));
    }

    @Test
    void calcEpicStatus_noSubtasks() {
        taskManager.addEpic(epic1);
        assertEquals(Status.NEW, taskManager.getEpic(epic1.getId()).getStatus());
    }

    @Test
    void calcEpicStatus_allSubtasksNew() {
        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание", epic1.getStatus());
        taskManager.addSubtask(subtask2);
        assertEquals(Status.NEW, taskManager.getEpic(epic1.getId()).getStatus());
    }

    @Test
    void calcEpicStatus_allSubtasksDone() {
        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание", Status.DONE, epic1);
        taskManager.addSubtask(subtask2);
        subtask1.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);
        assertEquals(Status.DONE, taskManager.getEpic(epic1.getId()).getStatus());
    }

    @Test
    void calcEpicStatus_someSubtasksInProgress() {
        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание", Status.IN_PROGRESS, epic1);
        taskManager.addSubtask(subtask2);
        assertEquals(Status.IN_PROGRESS, taskManager.getEpic(epic1.getId()).getStatus());
    }

    @Test
    void calcEpicStatus_someSubtasksNewAndDone() {
        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание", Status.DONE, epic1);
        taskManager.addSubtask(subtask2);
        assertEquals(Status.IN_PROGRESS, taskManager.getEpic(epic1.getId()).getStatus());
    }
}