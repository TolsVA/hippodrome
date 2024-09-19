import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorseTest {

//    @Mock
//    Horse horse;

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
        Horse horse = new Horse("Horse", expectedSpeed);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    void getDistance() {
    }

    @Test
    void move() {
    }

    @Test
    void getRandomDouble() {
    }
}