import Flowers.LivingFlower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FreshnessTest {

    private LivingFlower instance;

    @BeforeEach
    void setUp() {
        instance = new LivingFlower(100,100,"rose", Duration.ofSeconds(10));
    }

    @Test
    void testGetFreshness() {
        double expectedFreshness = 49.85;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
        double actualFreshness = instance.getFreshness();
        assertEquals(expectedFreshness, actualFreshness, "Freshness must be 49.85%");
    }
}