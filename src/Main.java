public class Main {
    public static void main(String[] args) {

        TaskManager manager = Manager.getDefault();

        manager.addTask(new Task("Задача №1", "Чай", Status.DONE));
        manager.addTask(new Task("Задача №2", "Кофе", Status.NEW));
        manager.addTask(new Task("Задача №3", "Сода", Status.NEW));
        manager.addTask(new Task("Задача №4", "Хлебушек", Status.DONE));

        Epic epic1 = new Epic("Эпик №1", "День кота");
        manager.addEpic(epic1);

        Subtask subtask11 = new Subtask("Эпик1 Подзадача1", "кормление", Status.DONE, epic1);
        manager.addSubtask(subtask11);
        Subtask subtask12 = new Subtask("Эпик1 Подзадача2", "сон", Status.IN_PROGRESS, epic1);
        manager.addSubtask(subtask12);
        Subtask subtask13 = new Subtask("Эпик1 Подзадача3", "чесание", Status.NEW, epic1);
        manager.addSubtask(subtask13);
        Subtask subtask12New = new Subtask("Эпик1 Подзадача2 изменена", "тыгыдык", Status.NEW, epic1);
        subtask12New.setId(12);
        manager.updateSubtask(subtask12New);

        Epic epic2 = new Epic("Эпик №2", "ЯндексПрактикум");
        manager.addEpic(epic2);

        //5/ выводим на экран все виды задач
        System.out.println("\n Пересчет всех Эпиков : \n" + manager.getEpics());
        System.out.println("\n Пересчет всех Задач : \n" +  manager.getTasks());
        System.out.println("\n Пересчет всех Подзадач : \n" + manager.getSubtasks());

        //5/ запросить созданные задачи несколько раз с повтором в разном порядке
        System.out.println("\n Запрос рандомной задачи : \n" + manager.getTask(1));
        System.out.println("Запрос рандомной задачи : \n" + manager.getTask(2));
        System.out.println("Запрос рандомной задачи : \n" + manager.getTask(1));

        //5/ удаляем задачи
        manager.deleteAllEpics();
        manager.deleteAllTasks();
        manager.deleteAllSubtask();
        System.out.println("Отчищаем все задачи");

        //5/ проверяем что осталось
        System.out.println("\n Итого Эпиков : \n" + manager.getEpics());
        System.out.println("\n Итого Задач : \n" +  manager.getTasks());
        System.out.println("\n Итого Подзадач : \n" + manager.getSubtasks());

        //5/ показать историю
        System.out.println("\n Показать историю : \n" + manager.history());

        //6/ загрузка из файла data TaskManager fileBackedTasksManager = FileBackedTasksManager.loadFromFile(new File("data/data.csv"));

    }
}