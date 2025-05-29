package catCafe;

import static org.junit.jupiter.api.Assertions.*;


import catcafe.FelineOverLord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catcafe.CatCafe;

import java.util.Optional;

public class CatCafeTest {

    private CatCafe cafe;

    @BeforeEach
    public void setup() {
        cafe = new CatCafe();
    }
    @Test
    void testLeeresCafe() {
        assertEquals(0, cafe.getCatCount());
    }
    @Test
    void testNichtLeeresCafe() {
        //when
        cafe.addCat(new FelineOverLord("Miss Chief Sooky", 2));
        //then
        assertEquals(1, cafe.getCatCount());
    }
    @Test
    void testExceptionNameNull() {
        assertThrows(NullPointerException.class, () -> {
            cafe.getCatByName(null);  // erwartet NullPointerException
        });
    }
    @Test
    void testExceptionMin() {
        assertThrows(IllegalArgumentException.class, () -> {
            Optional<FelineOverLord> meow = cafe.getCatByWeight(-3, 4);
        });
    }
    @Test
    void testExceptionMax() {
        assertThrows(IllegalArgumentException.class, () -> {
            Optional<FelineOverLord> meow = cafe.getCatByWeight(3, 1);
        });
    }
    @Test
    void testKatzeImCafe() {
        //when
        cafe.addCat(new FelineOverLord("Felix", 5));
        //then
        Optional<FelineOverLord> cat = cafe.getCatByName("Felix");
        assertTrue(cat.isPresent());
    }
    @Test
    void testKatzeNichtImCafe() {
        //when
        cafe.addCat(new FelineOverLord("Felix", 5));
        //then
        Optional<FelineOverLord> cat = cafe.getCatByName("Fussel");
        assertFalse(cat.isPresent());
    }
    @Test
    void testKatzeMitGewichtGefunden() {
        cafe.addCat(new FelineOverLord("Miezi", 4));
        Optional<FelineOverLord> cat = cafe.getCatByWeight(3, 5);
        assertTrue(cat.isPresent());
    }
    @Test
    void testKatzeMitGewichtNichtGefunden() {
        cafe.addCat(new FelineOverLord("Tiger", 7));
        Optional<FelineOverLord> cat = cafe.getCatByWeight(1, 5);
        assertFalse(cat.isPresent());
    }
    @Test
    void testAddNullKatze() {
        assertThrows(NullPointerException.class, () -> {
            cafe.addCat(null);
        });
    }
}
