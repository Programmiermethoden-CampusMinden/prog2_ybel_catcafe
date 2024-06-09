package catcafe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the CatCafe class.
 */
public class CatCafeTest {
    private CatCafe catCafe;
    private FelineOverLord cat1;
    private FelineOverLord cat2;

    @BeforeEach
    public void setUp() {
        catCafe = new CatCafe();
        cat1 = new FelineOverLord("Whiskers", 4);
        cat2 = new FelineOverLord("Fluffy", 5);
    }

    /**
     * Test adding a cat and checking the count.
     */
    @Test
    public void testAddCat() {
        catCafe.addCat(cat1);
        assertEquals(1, catCafe.getCatCount());
    }

    /**
     * Test adding multiple cats and checking the count.
     */
    @Test
    public void testAddMultipleCats() {
        catCafe.addCat(cat1);
        catCafe.addCat(cat2);
        assertEquals(2, catCafe.getCatCount());
    }

    /**
     * Test finding a cat by name.
     */
    @Test
    public void testGetCatByName() {
        catCafe.addCat(cat1);
        FelineOverLord foundCat = catCafe.getCatByName("Whiskers");
        assertNotNull(foundCat);
        assertEquals(cat1, foundCat);
    }

    /**
     * Test finding a cat by name that does not exist.
     */
    @Test
    public void testGetCatByNameNotFound() {
        catCafe.addCat(cat1);
        FelineOverLord foundCat = catCafe.getCatByName("Unknown");
        assertNull(foundCat);
    }

    /**
     * Test finding a cat by weight.
     */
    @Test
    public void testGetCatByWeight() {
        catCafe.addCat(cat1);
        catCafe.addCat(cat2);
        FelineOverLord foundCat = catCafe.getCatByWeight(3, 5);
        assertNotNull(foundCat);
        assertEquals(cat1, foundCat);
    }

    /**
     * Test finding a cat by weight with no match.
     */
    @Test
    public void testGetCatByWeightNotFound() {
        catCafe.addCat(cat1);
        catCafe.addCat(cat2);
        FelineOverLord foundCat = catCafe.getCatByWeight(1, 3);
        assertNull(foundCat);
    }

    /**
     * Test finding a cat by weight with invalid range (minWeight < 0).
     */
    @Test
    public void testGetCatByWeightInvalidRange1() {
        catCafe.addCat(cat1);
        FelineOverLord foundCat = catCafe.getCatByWeight(-1, 5);
        assertNull(foundCat);
    }

    /**
     * Test finding a cat by weight with invalid range (maxWeight < minWeight).
     */
    @Test
    public void testGetCatByWeightInvalidRange2() {
        catCafe.addCat(cat1);
        FelineOverLord foundCat = catCafe.getCatByWeight(5, 4);
        assertNull(foundCat);
    }

    /**
     * Test finding a cat by name with null name.
     */
    @Test
    public void testGetCatByNameNull() {
        catCafe.addCat(cat1);
        FelineOverLord foundCat = catCafe.getCatByName(null);
        assertNull(foundCat);
    }

    /**
     * Test finding a cat by weight with exact matching upper bound.
     */
    @Test
    public void testGetCatByWeightUpperBound() {
        catCafe.addCat(cat2);
        FelineOverLord foundCat = catCafe.getCatByWeight(4, 5);
        assertNull(foundCat);
    }

}
