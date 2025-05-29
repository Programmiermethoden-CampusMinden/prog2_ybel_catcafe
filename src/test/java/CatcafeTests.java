import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Optional;

import catcafe.CatCafe;
import catcafe.FelineOverLord;

class CatCafeTest {
    @Test
    void testAddCatIncreasesCount() {
        CatCafe cafe = new CatCafe();
        // eine Katze hinzufuegen, es sollte danach nur eine Katze existieren
        cafe.addCat(new FelineOverLord("Katze", 3));
        assertEquals(1, cafe.getCatCount());
    }

    @Test
    void testGetCatByNameFound() {
        CatCafe cafe = new CatCafe();
        // Katze hinzufuegen und dann mit Name finden
        cafe.addCat(new FelineOverLord("Katze", 3));
        assertTrue(cafe.getCatByName("Katze").isPresent());
    }

    @Test
    void testGetCatByNameNotFound() {
        CatCafe cafe = new CatCafe();
        // Katze hinzufuegen, aber nach anderem Namen suchen
        cafe.addCat(new FelineOverLord("cat", 3));
        assertTrue(cafe.getCatByName("catt").isEmpty());
    }

    @Test
    void testGetCatByWeightFound() {
        CatCafe cafe = new CatCafe();
        // Katze mit Gewicht 3 hinzufuegen und dann nach Gewicht 3 suchen
        cafe.addCat(new FelineOverLord("cat", 3));
        assertTrue(cafe.getCatByWeight(3, 4).isPresent());
    }

    @Test
    void testGetCatByWeightNotFound() {
        // Katze mit Gewicht 3 hinzufuegen, aber nach groeserem Gewicht suchen
        // sollte falsch sein, um den Test zu bestehen
        CatCafe cafe = new CatCafe();
        cafe.addCat(new FelineOverLord("Katze", 3));
        assertTrue(cafe.getCatByWeight(4, 10).isEmpty());
    }

    @Test
    void testGetCatByWeightEmptyCafe() {
        CatCafe cafe = new CatCafe();
        // Katze suchen, aber es gibt keine. Sollte leer sein
        assertTrue(cafe.getCatByWeight(1, 10).isEmpty());
    }

    @Test
    void testGetCatCountEmptyCafe() {
        CatCafe cafe = new CatCafe();
        // Katze suchen, aber es gibt keine. Sollte leer sein
        assertEquals(0, cafe.getCatCount());
    }

    @Test
    void testAddMultipleCatsSameWeight() {
        CatCafe cafe = new CatCafe();
        cafe.addCat(new FelineOverLord("KatzeEins", 3));
        cafe.addCat(new FelineOverLord("KatzeZwei", 3));
        // es darf nur eine Katze mit Gewicht 3 geben, weil es im Baum keine doppelten geben darf
        assertEquals(1, cafe.getCatCount());
        // maxWeight auf 4, weil getCatByWeight maxWeight exklusiv ist
        assertTrue(cafe.getCatByWeight(3, 4).isPresent());
    }

    @Test
    void testAddCatNullThrowsException() {
        CatCafe cafe = new CatCafe();
        // Null als Katze hinzufuegen, muss eine NullPointerException werfen
        assertThrows(NullPointerException.class, () -> cafe.addCat(null));
    }

    @Test
    void testGetCatByNameNullReturnsEmpty() {
        CatCafe cafe = new CatCafe();
        // Null als Name suchen, sollte ein leeres Optional zurueckgeben
        cafe.addCat(new FelineOverLord("A", 3));
        assertTrue(cafe.getCatByName(null).isEmpty());
    }
}