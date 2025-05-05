import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    public void epicsWithEqualIdShouldBeEqual() {
        Epic epic1 = new Epic(10, "Сделать перестановку", "Нужно успеть пока зп не закончилась");
        Epic epic2 = new Epic(10, "Подготовиться к собеседованию", "Когда-то там");
        assertEquals(epic1, epic2,
                "Ошибка! Наследники класса Task должны быть равны друг другу, если равен их id;");
    }
}