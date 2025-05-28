package tree;

import static org.junit.jupiter.api.Assertions.*;

import catcafe.CatCafe;
import catcafe.FelineOverLord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Testing the {@link CatCafe} Class. */
public class CatCafeTest {

    private CatCafe c;
    private FelineOverLord hector;
    private FelineOverLord jerry;

    /** sets up the CatCafe Objekt used for Testing. */
    @BeforeEach
    public void setup() {
        c = new CatCafe();
        hector = new FelineOverLord("Hector", 15);
        jerry = new FelineOverLord("Jerry", 18);

        c.addCat(hector);
        c.addCat(jerry);
    }

    /** Test, if the Method addCat() can accept null. */
    @Test
    public void testCtorAddNull() {
        assertThrows(NullPointerException.class, () -> c.addCat(null));
    }

    /** Test, if the Method CatCount() gives the correct value. */
    @Test
    public void testGetCatCount() {
        assertEquals(2, (int)(c.getCatCount()));
    }

    /** Test, that the Method getCatByName() cant search for the Value null. */
    @Test
    public void testCtorGetCatByName() {
        assertNull(c.getCatByName(null));
    }

    /** Test, that the Method getCatByName() works as intended. */
    @Test
    public void testGetCatByName() {
        assertEquals(hector, c.getCatByName("Hector"));
    }

    /** Test, that the Method getCatByWeight() doesn't allow a negative Weight-Parameter. */
    @Test
    public void testGetCatByWeightNegativeMinWeight() {
        assertNull(c.getCatByWeight(-1, 20));
    }

    /** Test, that the Method getCatByWeight() works as intended. */
    @Test
    public void testGetCatByWeight() {
        assertEquals(jerry, c.getCatByWeight(18, 19));
    }

    /** Test, that the MaxWeight-Parameter of the Method getCatByWeight() isn't inclusive. */
    @Test
    public void testGetCatByWeightMaxWeightNotInclusive() {
        assertNull(c.getCatByWeight(16, 18));
    }

    /** Test, that the MinWeight-Parameter of the Method getCatByWeight() is inclusive. */
    @Test
    public void testGetCatByWeightMinWeightYesInclusive() {
        assertEquals(hector, c.getCatByWeight(15, 18));
    }

    /** Test, that the MinWeight-Parameter of the Method getCatByWeight() is always smaller, than the MaxWeight Parameter. */
    @Test
    public void testGetCatByWeightMinWeightSmallerThanMaxWeight() {
        assertNull(c.getCatByWeight(18, 12));
    }

    /** Test, if the getCat...() Methods select the first cat in the Tree, that matches the search values. */
    @Test
    public void testGetCatSelectsFirstInTree() {

        FelineOverLord terry = new FelineOverLord("Terry", 18);
        FelineOverLord hector2 = hector;

        c.addCat(terry);
        c.addCat(hector2);

        assertEquals(jerry, c.getCatByWeight(18, 19));
        assertEquals(hector, c.getCatByName("Hector"));

    }

}
