package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Класс тестирует поведение класса Cat с использованием Mockito для мокирования зависимости Feline.
 */
@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    /**
     * Мок объекта Feline, используемый для изоляции тестируемого класса Cat от реальной реализации.
     */
    @Mock
    private Feline feline;
    private Cat cat;

    /**
     * Инициализация тестового объекта перед запуском каждого теста.
     */
    @Before
    public void setUp() {
        cat = new Cat(feline);
    }

    /**
     * Тест проверяет, что метод getSound возвращает строку "Мяу".
     */
    @Test
    public void getSoundTest() {
        String sound = cat.getSound();
        assertEquals("Кот должен говорить 'Мяу'", "Мяу", sound);
    }

    /**
     * Тест проверяет, что метод getFood возвращает список еды, полученный от Feline.eatMeat.
     *
     * @throws Exception если метод eatMeat выбрасывает исключение
     */
    @Test
    public void getFoodTest() throws Exception {
        List<String> expectedFood = List.of("Мясо", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);

        List<String> food = cat.getFood();

        assertEquals("Должен возвращать еду для хищника", expectedFood, food);
        verify(feline).eatMeat();
    }

    /**
     * Проверка создания объекта Cat с реальной зависимостью Feline.
     */
    @Test
    public void constructorWithFelineTest() {
        Feline realFeline = new Feline();
        Cat specialCat = new Cat(realFeline);

        assertNotNull("Кот должен создаваться с Feline", specialCat);
    }

    /**
     * Проверка создания объекта Cat с мок-объектом Feline.
     */
    @Test
    public void catUsesPredatorInterfaceTest() {
        Feline mockFeline = mock(Feline.class);
        Cat specialCat = new Cat(mockFeline);
        assertNotNull("Кот должен работать с Feline", specialCat);
    }

    @Test(expected = Exception.class)
    public void getFoodThrowsExceptionTest() throws Exception {
        when(feline.eatMeat()).thenThrow(new Exception("Нет еды"));
        cat.getFood();
    }
}