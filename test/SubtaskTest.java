import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    @Test
    public void SubtasksWithEqualIdShouldBeEqual() {
        Epic epic1 = new Epic("Эпик №1", "День кота");
        Subtask subtask1 = new Subtask(10, "Купить хлеб", "В Дикси у дома", Status.NEW, epic1);
        Subtask subtask2 = new Subtask(10, "Купить молоко", "В Пятерочке", Status.DONE, epic1);
        assertEquals(subtask1, subtask2,
                "Ошибка! Наследники класса Task должны быть равны друг другу, если равен их id;");
    }
}