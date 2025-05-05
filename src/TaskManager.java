import java.util.HashMap;
import java.util.List;

public interface TaskManager {

    void addTask(Task task);

    void updateTask(Task task);

    Task getTask(int id);

    HashMap<Integer, Task> getTasks();

    void deleteTask(int id);

    void deleteAllTasks();

    void addEpic(Epic epic);

    void updateEpic(Epic epic);

    Epic getEpic(int id);

    HashMap<Integer, Epic> getEpics();

    void deleteEpic(int id);

    void deleteAllEpics();

    void addSubtask(Subtask subtask);

    void updateSubtask(Subtask subtask);

    Subtask getSubtask(int id);

    HashMap<Integer, Subtask> getSubtasks();

    void deleteSubtask(int id);

    void deleteAllSubtask();

    List<Task> history();

}