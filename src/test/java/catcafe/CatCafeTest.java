package catcafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CatCafeTest {

    private CatCafe cafe;

    @BeforeEach
    public void setUp() {
        cafe = new CatCafe();
        cafe.addCat(new FelineOverLord("Miss Chief Sooky", 2));
        cafe.addCat(new FelineOverLord("Gwenapurr Esmeralda", 3));
        cafe.addCat(new FelineOverLord("Morticia", 3));
        cafe.addCat(new FelineOverLord("Fitzby Darnsworth", 5));
    }

    @Test
    public void testCatCount() {
        assertEquals(4, cafe.getCatCount());
    }

    @Test
    public void testGetCatByNameFound() {
        Optional<FelineOverLord> result = cafe.getCatByName("Morticia");
        assertTrue(result.isPresent());
        assertEquals("Morticia", result.get().name());
    }

    @Test
    public void testGetCatByNameNotFound() {
        Optional<FelineOverLord> result = cafe.getCatByName("Garfield");
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetCatByNameNull() {
        Optional<FelineOverLord> result = cafe.getCatByName(null);
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetCatByWeightFound() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(3, 4);
        assertTrue(result.isPresent());
        assertEquals(3, result.get().weight());
    }

    @Test
    public void testGetCatByWeightNoMatch() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(6, 10);
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetCatByWeightNegativeMin() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(-1, 2);
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetCatByWeightMinGreaterThanMax() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(5, 2);
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetCatByWeightAtUpperBoundary() {
        Optional<FelineOverLord> result = cafe.getCatByWeight(5, 6);
        assertTrue(result.isPresent());
        assertEquals(5, result.get().weight());
    }

    @Test
    public void testMultipleCatsWithSameName() {
        cafe.addCat(new FelineOverLord("Morticia", 4));
        Optional<FelineOverLord> result = cafe.getCatByName("Morticia");
        assertTrue(result.isPresent());
        assertEquals(3, result.get().weight()); // Die zuerst eingefügte bleibt vorn
    }
}
