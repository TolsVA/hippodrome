import com.javarash.hippodrome.Main;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@ExtendWith(MockitoExtension.class)
class MainTest {

    @Disabled("This way it won't take up time when starting up.")
    @Test
//    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void mainTest()  {
        assertTimeout(
                ofSeconds(22),
                () -> Main.main(new String[]{})
        );
    }
}