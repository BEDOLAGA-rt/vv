public class Subtask extends Task {
    private Epic epic;

    public Subtask(String title, String description, Status status) {
        super(title, description, status);
    }

    public Subtask(String title, String description, Status status, Epic epic) {
        super(title, description, status);
        this.epic = epic;
        this.taskType = TaskType.SUBTASK;
    }

    public Subtask(int id, String title, String description, Status status, Epic epic) {
        super(id, title, description, status);
        this.epic = epic;
        this.taskType = TaskType.SUBTASK;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    @Override
    public String toString() {
        return "Подзадача{" +
                "№=" + id +
                ", Название='" + title + '\'' +
                ", Описание='" + description + '\'' +
                ", Статус='" + status + '\'' +
                '}';
    }

    @Override
    public String toStringFromFile() {
        return String.format("%s,%s,%s,%s,%s,%s", id, taskType, title, status, description, epic.getId());
    }
}