package catcafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse für {@link CatCafe}.
 */
public class CatCafeTest {

    private CatCafe cafe;
    private FelineOverLord cat1;
    private FelineOverLord cat2;
    private FelineOverLord cat3;

    @BeforeEach
    public void setUp() {
        cafe = new CatCafe();
        cat1 = new FelineOverLord("Gwenapurr Esmeralda", 3);
        cat2 = new FelineOverLord("Miss Chief Sooky", 2);
        cat3 = new FelineOverLord("Morticia", 3);

        cafe.addCat(cat1);
        cafe.addCat(cat2);
        cafe.addCat(cat3);
    }

    @Test
    public void testCatCount() {
        assertEquals(3, cafe.getCatCount());
    }

    @Test
    public void testGetCatByExactWeight() {
        assertEquals(Optional.of(cat1), cafe.getCatByWeight(3, 3));
        assertEquals(Optional.of(cat2), cafe.getCatByWeight(2, 2));
    }

    @Test
    public void testGetCatByWeightInRange() {
        assertEquals(Optional.of(cat1), cafe.getCatByWeight(2, 3));
    }

    @Test
    public void testGetCatByWeightNotFound() {
        assertEquals(Optional.empty(), cafe.getCatByWeight(4, 5));
    }

    @Test
    public void testGetCatByNameFound() {
        assertEquals(Optional.of(cat1), cafe.getCatByName("Gwenapurr Esmeralda"));
        assertEquals(Optional.of(cat2), cafe.getCatByName("Miss Chief Sooky"));
        assertEquals(Optional.of(cat3), cafe.getCatByName("Morticia"));
    }

    @Test
    public void testGetCatByNameNotFound() {
        assertEquals(Optional.empty(), cafe.getCatByName("Unbekannt"));
    }
}
