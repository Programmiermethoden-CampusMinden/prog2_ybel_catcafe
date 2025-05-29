import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import catcafe.CatCafe;
import catcafe.FelineOverLord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CatCafeTest {

    private CatCafe cafe;

    @BeforeEach
    void setUp() {
        cafe = new CatCafe();
        cafe.addCat(new FelineOverLord("Miss Chief Sooky", 2));
        cafe.addCat(new FelineOverLord("Gwenapurr Esmeralda", 3));
        cafe.addCat(new FelineOverLord("Morticia", 3));
        cafe.addCat(new FelineOverLord("Fitzby Darnsworth", 5));
    }

    @Test
    void testAddCatIncreasesCount() {
        long before = cafe.getCatCount();
        cafe.addCat(new FelineOverLord("New Cat", 1));
        assertEquals(before + 1, cafe.getCatCount());
    }

    @Test
    void testGetCatCount() {
        assertEquals(4, cafe.getCatCount());
    }

    @Test
    void testGetCatByNameFound() {
        Optional<FelineOverLord> cat = cafe.getCatByName("Morticia");
        assertTrue(cat.isPresent());
        assertEquals("Morticia", cat.get().name());
    }

    @Test
    void testGetCatByNameNotFound() {
        Optional<FelineOverLord> cat = cafe.getCatByName("Unknown");
        assertFalse(cat.isPresent());
    }

    @Test
    void testGetCatByNameNull() {
        Optional<FelineOverLord> cat = cafe.getCatByName(null);
        assertFalse(cat.isPresent());
    }

    @Test
    void testGetCatByWeightFound() {
        Optional<FelineOverLord> cat = cafe.getCatByWeight(3, 4);
        assertTrue(cat.isPresent());
        int weight = cat.get().weight();
        assertTrue(weight >= 3 && weight < 4);
    }

    @Test
    void testGetCatByWeightNoMatch() {
        Optional<FelineOverLord> cat = cafe.getCatByWeight(10, 20);
        assertFalse(cat.isPresent());
    }

    @Test
    void testGetCatByWeightInvalidRange() {
        Optional<FelineOverLord> cat = cafe.getCatByWeight(5, 2);
        assertFalse(cat.isPresent());
    }

    @Test
    void testGetCatByWeightNegativeMin() {
        Optional<FelineOverLord> cat = cafe.getCatByWeight(-1, 5);
        assertFalse(cat.isPresent());
    }

    @Test
    void testAddDuplicateCat() {
        FelineOverLord duplicate = new FelineOverLord("Morticia", 3);
        long before = cafe.getCatCount();

        cafe.addCat(duplicate);
        // Da Duplikate nicht erlaubt sind, bleibt die Anzahl gleich
        assertEquals(before, cafe.getCatCount());
    }

}

