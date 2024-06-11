package catcafe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tree.Empty;
import tree.Node;
import tree.Tree;
import tree.TreeVisitor;

import java.util.Optional;

public class CatCafeTest {

    private CatCafe catCafe;
    private FelineOverLord cat1;
    private FelineOverLord cat2;

    @BeforeEach
    void setUp() {
        catCafe = new CatCafe();
        cat1 = new FelineOverLord("Whiskers", 10);
        cat2 = new FelineOverLord("Paws", 15);
    }

    /**
     * Test adding a cat to the CatCafe.
     */
    @Test
    void testAddCat() {
        catCafe.addCat(cat1);
        assertEquals(1, catCafe.getCatCount());
    }

    /**
     * Test getting the count of cats in the CatCafe.
     */
    @Test
    void testGetCatCount() {
        assertEquals(0, catCafe.getCatCount());
        catCafe.addCat(cat1);
        catCafe.addCat(cat2);
        assertEquals(2, catCafe.getCatCount());
    }

    /**
     * Test getting a cat by name.
     */
    @Test
    void testGetCatByName() {
        catCafe.addCat(cat1);
        catCafe.addCat(cat2);
        Optional<FelineOverLord> result = catCafe.getCatByName("Whiskers");
        assertTrue(result.isPresent());
        assertEquals(cat1, result.get());
    }

    /**
     * Test getting a cat by name that does not exist.
     */
    @Test
    void testGetCatByNameNotFound() {
        catCafe.addCat(cat1);
        Optional<FelineOverLord> result = catCafe.getCatByName("Unknown");
        assertFalse(result.isPresent());
    }

    /**
     * Test getting a cat by null name.
     */
    @Test
    void testGetCatByNameNull() {
        catCafe.addCat(cat1);
        Optional<FelineOverLord> result = catCafe.getCatByName(null);
        assertFalse(result.isPresent());
    }

    /**
     * Test getting a cat by weight range.
     */
    @Test
    void testGetCatByWeight() {
        catCafe.addCat(cat1);
        catCafe.addCat(cat2);
        Optional<FelineOverLord> result = catCafe.getCatByWeight(10, 16);
        assertTrue(result.isPresent());
        assertEquals(cat1, result.get());
    }

    /**
     * Test getting a cat by weight range that does not exist.
     */
    @Test
    void testGetCatByWeightNotFound() {
        catCafe.addCat(cat1);
        Optional<FelineOverLord> result = catCafe.getCatByWeight(20, 30);
        assertFalse(result.isPresent());
    }

    /**
     * Test getting a cat by invalid weight range.
     */
    @Test
    void testGetCatByWeightInvalidRange() {
        catCafe.addCat(cat1);
        Optional<FelineOverLord> result = catCafe.getCatByWeight(-1, 10);
        assertFalse(result.isPresent());
    }

    /**
     * Test getting a cat by weight range where minWeight is greater than maxWeight.
     */
    @Test
    void testGetCatByWeightMinGreaterThanMax() {
        catCafe.addCat(cat1);
        Optional<FelineOverLord> result = catCafe.getCatByWeight(15, 10);
        assertFalse(result.isPresent());
    }

    /**
     * Test the accept method with a TreeVisitor.
     */
    @Test
    void testAccept() {
        catCafe.addCat(cat1);
        TreeVisitor<FelineOverLord> visitor = new TreeVisitor<>() {
            @Override
            public String visit(Empty<FelineOverLord> empty) {
                return "Empty";
            }

            @Override
            public String visit(Node<FelineOverLord> node) {
                return "Node";
            }
        };
        assertEquals("Tree", catCafe.accept(visitor));
    }
}
