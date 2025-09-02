package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тестовый класс для проверки функциональности класса {@link Lion}.
 * Тестирует конструктор, методы и взаимодействие с зависимостью {@link Feline}.
 * Использует Mockito для изоляции тестов от реальной реализации Feline.
 */
@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    /**
     * Mock-объект для имитации поведения Feline.
     * Используется для изоляции тестов Lion от реальной реализации Feline.
     * Позволяет контролировать возвращаемые значения и проверять вызовы методов.
     */
    @Mock
    private Feline feline;

    /**
     * Экземпляр тестируемого класса Lion.
     * Инициализируется в методе setUp() перед каждым тестом.
     * Используется в тестах методов для проверки базовой функциональности.
     */
    private Lion lion;

    /**
     * Метод, выполняемый перед каждым тестом.
     * Инициализирует mock-объект Feline и создает экземпляр Lion.
     * Настраивает стандартное поведение mock-объекта для большинства тестов.
     *
     * @throws Exception если возникает ошибка при создании Lion
     */
    @Before
    public void setUp() throws Exception {
        // Настраиваем mock для большинства тестов
        when(feline.getKittens()).thenReturn(3);
        when(feline.getFood("Хищник")).thenReturn(List.of("Мясо", "Птицы"));

        // Создаем льва для тестов, которые не требуют особой настройки
        lion = new Lion("Самец", feline);
    }

    // Тесты конструктора

    /**
     * Тестирует конструктор Lion с параметром "Самец".
     * Проверяет, что лев-самец имеет гриву.
     *
     * @throws Exception если возникает ошибка при создании Lion
     */
    @Test
    public void maleLionHasManeTest() throws Exception {
        Lion maleLion = new Lion("Самец", feline);
        assertTrue("Самец должен иметь гриву", maleLion.doesHaveMane());
    }

    /**
     * Тестирует конструктор Lion с параметром "Самка".
     * Проверяет, что львица не имеет гривы.
     *
     * @throws Exception если возникает ошибка при создании Lion
     */
    @Test
    public void femaleLionNoManeTest() throws Exception {
        Lion femaleLion = new Lion("Самка", feline);
        assertFalse("Самка не должна иметь гриву", femaleLion.doesHaveMane());
    }

    /**
     * Тестирует конструктор Lion с недопустимым параметром пола.
     * Проверяет, что при некорректном поле выбрасывается исключение.
     *
     * @throws Exception ожидаемое исключение при некорректном поле
     */
    @Test(expected = Exception.class)
    public void invalidSexThrowsException() throws Exception {
        new Lion("Неизвестно", feline);
    }

    // Тесты методов

    /**
     * Проверяет, что метод {@link Lion#getKittens()} возвращает количество котят,
     * заданное мок-объектом {@link Feline}.
     *
     */
    @Test
    public void getKittensTest() {
        int actualKittens = lion.getKittens();

        assertEquals("Lion должен возвращать количество котят, полученных от Feline", 3, actualKittens);
        verify(feline).getKittens();
    }

    /**
     * Проверяет, что метод {@link Lion#getFood()} возвращает список еды для хищника,
     * полученный от мок-объекта {@link Feline}.
     *
     * @throws Exception если метод выбрасывает исключение
     */
    @Test
    public void getFoodTest() throws Exception {
        List<String> expectedFood = List.of("Мясо", "Птицы");
        List<String> actualFood = lion.getFood();

        assertEquals("Lion должен возвращать еду для хищника, полученную от Feline", expectedFood, actualFood);
        verify(feline).getFood("Хищник");
    }
}