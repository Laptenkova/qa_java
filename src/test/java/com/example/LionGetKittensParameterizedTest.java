package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Параметризованный тестовый класс для проверки метода {@link Lion#getKittens()}.
 * Тестирует различные варианты количества котят, возвращаемых от {@link Feline}.
 * Проверяет граничные случаи и корректность делегирования вызова.
 */
@RunWith(Parameterized.class)
public class LionGetKittensParameterizedTest {

    /**
     * Входные данные - количество котят, которое возвращает Feline.
     * Используется для настройки поведения mock-объекта Feline.
     */
    private final int inputKittens;

    /**
     * Ожидаемый результат - количество котят, которое должен вернуть Lion.
     * Должен совпадать с inputKittens, так как Lion делегирует вызов Feline.
     */
    private final int expectedKittens;


    /**
     * Конструктор для параметризованного теста.
     * Инициализирует тестовые данные для каждого набора параметров.
     *
     * @param inputKittens    количество котят, которое возвращает Feline
     * @param expectedKittens ожидаемое количество котят от Lion
     */
    public LionGetKittensParameterizedTest(int inputKittens, int expectedKittens) {
        this.inputKittens = inputKittens;
        this.expectedKittens = expectedKittens;
    }

    /**
     * Параметры, которые будут передаваться в конструктор тестового класса.
     * Каждый массив содержит набор параметров (inputKittens и expectedKittens)
     * для отдельного запуска теста.
     *
     * @return коллекция наборов тестовых параметров
     */
    @Parameterized.Parameters(name = "Тестовые данные: ожидание={0}, результат={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 1},
                {5, 5},
                {0, 0},
                {-3, -3},
        });
    }

    /**
     * Тест проверяет, что метод {@link Lion#getKittens()} возвращает количество котят,
     * совпадающее с количеством, возвращаемым мок-объектом {@link Feline#getKittens()}.
     *
     * @throws Exception если в процессе выполнения метода возникнет исключение
     */
    @Test
    public void testGetKittensWithDifferentValues() throws Exception {
        Feline feline = mock(Feline.class);
        when(feline.getKittens()).thenReturn(inputKittens);

        Lion lion = new Lion("Самец", feline);
        int actualKittens = lion.getKittens();

        assertEquals("Lion должен возвращать количество котят, полученных от Feline",
                expectedKittens, actualKittens);
    }
}

