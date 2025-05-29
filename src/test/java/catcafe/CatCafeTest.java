package catcafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CatCafeTest {

    private CatCafe cafe;

    @BeforeEach
    void setUp() {
        cafe = new CatCafe();
        cafe.addCat(new FelineOverLord("Miss Chief Sooky", 2));
        cafe.addCat(new FelineOverLord("Gwenapurr Esmeralda", 3));
        cafe.addCat(new FelineOverLord("Morticia", 4));
        cafe.addCat(new FelineOverLord("Fitzby Darnsworth", 5));
    }

    @Test
    void testGetCatCount() {
        assertEquals(4, cafe.getCatCount());
    }

    @Test
    void testGetCatByName_Found() {
        Optional<FelineOverLord> result = cafe.getCatByNameOptional("Morticia");
        assertTrue(result.isPresent());
        assertEquals("Morticia", result.get().name());
    }

    @Test
    void testGetCatByName_NotFound() {
        Optional<FelineOverLord> result = cafe.getCatByNameOptional("Garfield");
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetCatByName_Null() {
        Optional<FelineOverLord> result = cafe.getCatByNameOptional(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetCatByWeight_ValidRange() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(2, 5);
        assertTrue(result.isPresent());
        assertTrue(result.get().weight() >= 2 && result.get().weight() < 5);
    }

    @Test
    void testGetCatByWeight_ExactMatchLowerBound() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(4, 5);
        assertTrue(result.isPresent());
        assertEquals(4, result.get().weight());
    }

    @Test
    void testGetCatByWeight_UpperBoundExcluded() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(5, 5);
        assertTrue(result.isEmpty()); // nothing matches weight == 5 in range [5,5)
    }

    @Test
    void testGetCatByWeight_NoMatchInRange() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(10, 20);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetCatByWeight_NegativeMinWeight() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(-1, 5);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetCatByWeight_MaxLessThanMin() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(6, 3);
        assertTrue(result.isEmpty());
    }

    @Test
    void testAddCat_IncreasesCount() {
        int before = (int) cafe.getCatCount();
        cafe.addCat(new FelineOverLord("NewCat", 6));
        int after = (int) cafe.getCatCount();
        assertEquals(before + 1, after);
    }
}
