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
        cafe.addCat(new FelineOverLord("Morticia", 3));
        cafe.addCat(new FelineOverLord("Fitzby Darnsworth", 5));
    }

    @Test
    void testGetCatCount() {
        assertEquals(4, cafe.getCatCount());
    }

    @Test
    void testGetCatByNameMorticia() {
        Optional<FelineOverLord> result = cafe.getCatByName("Morticia");
        assertTrue(result.isPresent());
        assertEquals("Morticia", result.get().name());
    }

    @Test
    void testGetCatByNameNotFound() {
        Optional<FelineOverLord> result = cafe.getCatByName("Fluffy");
        assertFalse(result.isPresent());
    }

    @Test
    void testGetCatByNameMissChiefSooky() {
        Optional<FelineOverLord> result = cafe.getCatByName("Miss Chief Sooky");
        assertTrue(result.isPresent());
        assertEquals("Miss Chief Sooky", result.get().name());
        assertEquals(2, result.get().weight());
    }

    @Test
    void testGetCatByWeight34() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(3, 4);
        assertTrue(result.isPresent());
        assertEquals(3, result.get().weight());
    }

    @Test
    void testGetCatByWeightNotFound() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(10, 20);
        assertFalse(result.isPresent());
    }

    @Test
    void testGetCatByWeightExact() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(5, 6);
        assertTrue(result.isPresent());
        assertEquals("Fitzby Darnsworth", result.get().name());
    }

    @Test
    void testGetCatByWeightInvalidNegative() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(-1, 1);
        assertFalse(result.isPresent());
    }

    @Test
    void testGetCatByWeightInvalidRange() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(6, 3);
        assertFalse(result.isPresent());
    }

    @Test
    void testGetCatByNameNull() {
        Optional<FelineOverLord> result = cafe.getCatByName(null);
        assertFalse(result.isPresent());
    }
}
