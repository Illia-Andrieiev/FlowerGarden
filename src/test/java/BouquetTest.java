import Atributes.Decoration;
import Flowers.Flower;
import Flowers.LivingFlower;
import Flowers.MultipleFlowers;
import org.example.Bouquet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BouquetTest {

    private Bouquet bouquet;

    @BeforeEach
    void setUp() {
        bouquet = new Bouquet();
        bouquet.addFlower(new LivingFlower(10, 15.0, "Lilia", Duration.ofSeconds(8)));
        bouquet.addFlower(new Flower(15, 20, "Tulip"));
        bouquet.addFlower(new MultipleFlowers(5,12,
                "Rose", Duration.ofSeconds(10),5));
        bouquet.addDecoration(new Decoration(25,"Ribbon"));
    }

    @Test
    void testCountCost() {
        double expectedCost = 10 + 5*5 + 15 + 25;
        double actualCost = bouquet.countCost();
        assertTrue(expectedCost >= actualCost && actualCost > 74, "Bouquet cost must be between 74 and 75");
    }

    @Test
    void testRemoveWiltedFlowers() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
        bouquet.print();
        bouquet.removeWiltedFlowers(42);
        bouquet.print();
        assertEquals(2, bouquet.getFlowers().size(), "Must stay 2 flowers");
        assertEquals("Tulip", bouquet.getFlowers().get(0).getName(), "First flower must be tulip");
        assertEquals("Rose", bouquet.getFlowers().get(1).getName(), "Second flower must be lilia");

    }

    @Test
    void testFindInStemDiapason() {
        ArrayList<Flower> foundFlowers = bouquet.findInStemDiapason(5.0, 12.0);
        assertEquals(1, foundFlowers.size(), "Must find 1 flower");
        assertEquals("Rose", foundFlowers.get(0).getName(), "must be rose");
    }

    @Test
    void testSortByFreshness() {
        bouquet.print();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
        bouquet.sortByFreshness();
        bouquet.print();
        assertTrue(bouquet.getFlowers().get(0).getFreshness() <=
                        bouquet.getFlowers().get(1).getFreshness(),
                "Flowers must be sorted by freshness");
        assertTrue(bouquet.getFlowers().get(1).getFreshness() <=
                        bouquet.getFlowers().get(2).getFreshness(),
                "Flowers must be sorted by freshness");
    }

}