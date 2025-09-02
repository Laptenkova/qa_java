package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Класс для тестирования функционала класса {@link Feline}.
 * Содержит тесты всех публичных методов и проверки наследования.
 */
public class FelineTest {

    /**
     * Объект Feline, который будем тестировать.
     */
    private Feline feline;

    /**
     * Метод выполняется перед каждым тестом.
     * Создаёт новый объект Feline для обеспечения чистоты тестов.
     */
    @Before
    public void setUp() {
        feline = new Feline();
    }

    /**
     * Тест метода eatMeat().
     * Проверяет, что возвращается правильный список мяса для хищника.
     *
     * @throws Exception если метод eatMeat выбрасывает исключение
     */
    @Test
    public void eatMeatShouldReturnPredatorFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, feline.eatMeat());
    }

    /**
     * Тест метода getFamily().
     * Проверяет, что возвращается строка "Кошачьи".
     */
    @Test
    public void getFamilyShouldReturnFeline() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    /**
     * Тест метода getKittens() без параметров.
     * Должен возвращать 1, что соответствует значению по умолчанию.
     */
    @Test
    public void getKittensWithoutParameterShouldReturnOne() {
        assertEquals(1, feline.getKittens());
    }

    /**
     * Тест метода getKittens(int) с разными значениями параметров.
     * Метод должен возвращать переданное значение (воспроизводить параметр без изменений).
     */
    @Test
    public void getKittensWithParameterShouldReturnInput() {
        assertEquals(5, feline.getKittens(5));
        assertEquals(0, feline.getKittens(0));
        assertEquals(-3, feline.getKittens(-3));
    }

    /**
     * Тест наследования класса Feline от класса Animal.
     * Проверяет, что объект feline является экземпляром Animal.
     */
    @Test
    public void shouldInheritFromAnimal() {
        assertTrue(feline instanceof Animal);
    }

    /**
     * Тест реализации интерфейса Predator классом Feline.
     * Проверяет, что объект feline реализует интерфейс Predator.
     */
    @Test
    public void shouldImplementPredatorInterface() {
        assertTrue(feline instanceof Predator);
    }
}