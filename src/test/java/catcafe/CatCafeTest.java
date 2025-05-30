package catcafe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CatCafeTest {
    @Test
    public void testAdd() {
        CatCafe cafe = new CatCafe();
        
        assertEquals(cafe.getCatCount(), 0);
        
        cafe.addCat(new FelineOverLord("a", 1));

        assertEquals(cafe.getCatCount(), 1);
    }

    @Test
    public void testGetByName() {
        CatCafe cafe = new CatCafe();

        cafe.addCat(new FelineOverLord("a", 1));
        FelineOverLord lookingFor =new FelineOverLord("b", 2); 
        cafe.addCat(lookingFor);
        cafe.addCat(new FelineOverLord("c", 3));

        assertEquals(cafe.getCatByName("b"),lookingFor);
    }
    
    @Test
    public void testMissingCatName() {
        CatCafe cafe = new CatCafe();

        cafe.addCat(new FelineOverLord("a", 1));
        cafe.addCat(new FelineOverLord("b", 2));
        cafe.addCat(new FelineOverLord("c", 3));

        assertNull(cafe.getCatByName("d"));
    }

    @Test
    public void testGetByWeigth() {
        CatCafe cafe = new CatCafe();

        cafe.addCat(new FelineOverLord("a", 1));
        FelineOverLord lookingFor =new FelineOverLord("b", 2); 
        cafe.addCat(lookingFor);
        cafe.addCat(new FelineOverLord("c", 3));

        assertEquals(cafe.getCatByWeight(2, 2),lookingFor);
    }
    
    @Test
    public void testMissingCatWeight() {
        CatCafe cafe = new CatCafe();

        cafe.addCat(new FelineOverLord("a", 1));
        cafe.addCat(new FelineOverLord("b", 2));
        cafe.addCat(new FelineOverLord("c", 3));

        assertNull(cafe.getCatByWeight(4,4));
    }
    
    @Test
    public void testDuplicatNames() {
        CatCafe cafe = new CatCafe();

        cafe.addCat(new FelineOverLord("a", 1));
        cafe.addCat(new FelineOverLord("a", 2));
        cafe.addCat(new FelineOverLord("a", 3));

        assertEquals(3, cafe.getCatCount());
    }

    
    @Test
    public void testDuplicateWeight() {
        CatCafe cafe = new CatCafe();

        cafe.addCat(new FelineOverLord("a", 1));
        cafe.addCat(new FelineOverLord("b", 1));
        cafe.addCat(new FelineOverLord("c", 1));

        assertEquals(3, cafe.getCatCount());
    }
    
    @Test
    public void testNegativeWeight() {
        CatCafe cafe = new CatCafe();

        cafe.addCat(new FelineOverLord("a", -1));
        cafe.addCat(new FelineOverLord("b", 2));
        cafe.addCat(new FelineOverLord("c", -3));

        assertEquals(3, cafe.getCatCount());
    }

    @Test
    public void testAddNull() {
        CatCafe cafe = new CatCafe();

        assertThrows(NullPointerException.class, () -> cafe.addCat(null));
    }
    
    @Test
    public void testGetNullName() {
        CatCafe cafe = new CatCafe();

        assertNull(cafe.getCatByName(null));
    }
}
