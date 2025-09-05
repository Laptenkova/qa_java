package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Параметризованный тестовый класс для проверки метода {@link Feline#getKittens(int)}.
 * Тестирует различные варианты входных параметров и ожидаемых результатов.
 * Проверяет граничные случаи и корректность возвращаемого значения.
 */

@RunWith(Parameterized.class)
public class FelineGetKittensParameterizedTest {

    /**
     * Параметр для передачи в getKittens(int)
     */
    private final int input;

    /**
     * Ожидаемый результат работы метода
     */
    private final int expected;

    /**
     * Конструктор для параметризованного теста.
     * Инициализирует тестовые данные для каждого набора параметров.
     */
    public FelineGetKittensParameterizedTest(int input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    /**
     * Набор тестовых данных для параметризованного тестирования.
     * Каждый массив объектов представляет отдельный тестовый случай:
     * - первый элемент: входное значение
     * - второй элемент: ожидаемое значение
     *
     * @return коллекция наборов тестовых параметров
     */
    @Parameterized.Parameters(name = "Тестовые данные: ожидание={0}, результат={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 1}, {5, 5}, {0, 0}, {-3, -3}
        });
    }

    /**
     * Тест проверяет, что метод {@link Feline#getKittens(int)} возвращает значение,
     * равное переданному параметру, для различных входных данных.
     * Проверяет корректность работы метода с разными типами числовых значений.
     */
    @Test
    public void getKittensWithParameterTest() {
        Feline feline = new Feline();
        int result = feline.getKittens(input);

        assertEquals(expected, result);
    }

}
