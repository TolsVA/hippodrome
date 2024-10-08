import com.javarash.hippodrome.Hippodrome;
import com.javarash.hippodrome.Horse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void passNullToConstructor() {
        IllegalArgumentException exception = getException(null);
        assertEquals("Horses cannot be null.", exception.getMessage());

    }

    @Test
    void passingAnEmptyListToTheConstructor() {
        IllegalArgumentException exception = getException(new ArrayList<>());
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    private IllegalArgumentException getException(ArrayList<Horse> horses) {
        return assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(horses)
        );
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + (i + 1), 2.4));
        }
        assertArrayEquals(horses.toArray(), new Hippodrome(horses).getHorses().toArray());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.spy(new Horse("Horse" + i, 1)));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : horses){
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(new Horse("Horse" + i, 1, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(49, hippodrome.getWinner().getDistance());
    }
}