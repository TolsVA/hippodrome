import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorseTest {

    @Test
    void constructorPassNameEqualToNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 3, 0)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    void constructorWePassAnEmptyName(String name) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, 3, 0)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorWePassNegativeSpeed() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("name", -1, 0)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorWePassNegativeDistance() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("name", 3, -1)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        String expectedName = "Pegasus";
        Horse horse = new Horse(expectedName, 3, 1);
        assertEquals(expectedName, horse.getName());
    }

    @Test
    void getSpeed() {
        double expectedSpeed = 3;
        Horse horse = new Horse("Pegasus", expectedSpeed);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    void getDistanceConstructorWithThreeParameters() {
        double expectedDistance = 3;
        Horse horse = new Horse("Pegasus", 2, expectedDistance);
        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    void getDistanceConstructorWithTwoParameters() {
        Horse horse = new Horse("Pegasus", 2);
        assertEquals(0, horse.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5})
    void move(double randomDouble) {
        try (MockedStatic<Horse> utilities =  Mockito.mockStatic(Horse.class)) {

            //добавляем правило
            utilities.when(() -> Horse.getRandomDouble(0.2d, 0.9d)).thenReturn(randomDouble);

            Horse horse = new Horse("Pegasus", 2, 3);
            double dist = horse.getDistance();
            horse.move();

            utilities.verify(() -> Horse.getRandomDouble(0.2d, 0.9d));

            //проверяем, что правило работает
            assertEquals(dist + horse.getSpeed()*randomDouble, horse.getDistance());
        }
    }
}