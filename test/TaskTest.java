import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


class TaskTest {

    @Test
    public void tasksWithEqualIdShouldBeEqual() {
        Task task1 = new Task(10, "Купить хлеб", "В Дикси у дома", Status.NEW);
        Task task2 = new Task(10, "Купить молоко", "В Пятерочке", Status.DONE);
        assertEquals("Ошибка! Экземпляры класса Task должны быть равны друг другу, если равен их id;", task1,
                task2);
    }
}