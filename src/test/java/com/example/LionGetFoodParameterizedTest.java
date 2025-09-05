package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Параметризованный тестовый класс для проверки метода {@link Lion#getFood()}.
 * Тестирует различные варианты возвращаемой еды от {@link Feline}.
 */
@RunWith(Parameterized.class)
public class LionGetFoodParameterizedTest {

    /**
     * Входной список еды, который должен возвращать метод getFood() у Feline.
     */
    private final List<String> inputFood;

    /**
     * Ожидаемый список еды, который должен вернуть метод getFood() у Lion.
     */
    private final List<String> expectedFood;

    /**
     * Конструктор для параметризованного теста.
     *
     * @param inputFood    список еды, который возвращает Feline
     * @param expectedFood ожидаемый список еды от Lion
     */
    public LionGetFoodParameterizedTest(List<String> inputFood, List<String> expectedFood) {
        this.inputFood = inputFood;
        this.expectedFood = expectedFood;
    }

    /**
     * Метод предоставляет тестовые данные для параметризованного теста.
     * Каждый массив Object[] содержит пару: входные данные и ожидаемый результат.
     *
     * @return коллекция тестовых данных в формате {inputFood, expectedFood}
     */
    @Parameterized.Parameters(name = "Тестовые данные: ожидание={0}, результат={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {List.of("Мясо"), List.of("Мясо")},
                {Arrays.asList("Рыба", "Птица"), Arrays.asList("Рыба", "Птица")},
                {Arrays.asList("Оленина", "Кабан"), Arrays.asList("Оленина", "Кабан")}
        });
    }

    /**
     * Параметризованный тест метода {@link Lion#getFood()}.
     * Проверяет, что Lion корректно возвращает еду, полученную от Feline.
     * Тестирует различные варианты списков еды разного размера и содержания.
     *
     * @throws Exception если возникает ошибка при получении еды
     */
    @Test
    public void testGetFoodWithDifferentMeat() throws Exception {
        Feline feline = new Feline() {
            @Override
            public List<String> getFood(String predator) {
                return inputFood;
            }
        };

        Lion lion = new Lion("Самец", feline);
        assertEquals(expectedFood, lion.getFood());
    }
}
