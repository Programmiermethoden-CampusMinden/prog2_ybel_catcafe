package catcafe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatCafeTest {

    public CatCafe catCafe = new CatCafe();


    /**
     * Testet, ob die Methode getCatCount auch den richtigen Wert für die Anzahl an Katzen zurückgibt,
     * die zuvor mit der Methode addCat hinzugefügt wurden.
     */
    @Test
    void addCatUndGetCatCountTest() {
        FelineOverLord cat = new FelineOverLord("Max", 12);
        catCafe.addCat(cat);
        assertEquals(1, catCafe.getCatCount());
    }

    /**
     * Testet, ob die Methode getCatCount bei leerem Cafe 0 statt Null zurückgibt.
     */
    @Test
    void getCatCountShouldNotBeNullIfEmpty() {
        assertEquals(0, catCafe.getCatCount());
    }

    /**
     * Testet, ob die Methode null zurückgibt, wenn beim Aufrufen der Methode
     * nur leere Anführungszeichen mitgegeben werden.
     */
    @Test
    void getCatByNameShouldBeNullIfNameIsNull(){
        FelineOverLord cat = new FelineOverLord("Max", 12);
        catCafe.addCat(cat);
        assertNull(catCafe.getCatByName(""));
    }

    /**
     * Testet, ob die Methode die richtige Katze zurückgibt, wenn beim Aufrufen der Methode
     * der Name der richtigen Katze mitgegeben wird.
     */
    @Test
    void getCatByNameShouldReturnRightCat(){
        FelineOverLord cat1 = new FelineOverLord("Max", 12);
        FelineOverLord cat2 = new FelineOverLord("Felix", 12);
        FelineOverLord cat3 = new FelineOverLord("Tom", 12);
        catCafe.addCat(cat1);
        catCafe.addCat(cat2);
        catCafe.addCat(cat3);
        assertEquals(cat1, catCafe.getCatByName("Max"));
    }

    /**
     * Testet, ob die Methode null zurückgibt, wenn beim Aufrufen der Methode
     * der Name einer Katze mitgegeben wird, die nicht im Cafe ist.
     */
    @Test
    void getCatByNameShouldReturnNullIfCatNotFound(){
        FelineOverLord cat1 = new FelineOverLord("Max", 12);
        FelineOverLord cat2 = new FelineOverLord("Felix", 12);
        FelineOverLord cat3 = new FelineOverLord("Tom", 12);
        catCafe.addCat(cat1);
        catCafe.addCat(cat2);
        catCafe.addCat(cat3);
        assertNull(catCafe.getCatByName("Jerry"));
    }

    /**
     * Testet, ob die Methode null zurückgibt, wenn beim Aufrufen der Methode
     * minWeight negativ ist.
     */
    @Test
    void getCatByWeightShouldBeNullIfMinIsNegative() {
        FelineOverLord cat = new FelineOverLord("Max", -1);
        catCafe.addCat(cat);
        assertNull(catCafe.getCatByWeight(-1, 5));
    }

    /**
     * Testet, ob die Methode null zurückgibt, wenn beim Aufrufen der Methode
     * minWeight größer als maxWeight ist.
     */
    @Test
    void getCatByWeightShouldBeNullIfMinIsGreaterThanMax() {
        FelineOverLord cat = new FelineOverLord("Tom", 3);
        catCafe.addCat(cat);
        assertNull(catCafe.getCatByWeight(3, 2));
    }

    /**
     * Testet, ob die Methode die richtige Katze zurückgibt, wenn beim Aufrufen der Methode
     * die richtige range des Gewichts der Katze mitgegeben wird.
     */
    @Test
    void getCatByWeightShouldReturnCatIfRangeIsRight() {
        FelineOverLord cat = new FelineOverLord("Tom", 3);
        catCafe.addCat(cat);
        assertEquals(cat, catCafe.getCatByWeight(1, 4));
    }

    /**
     * Testet, ob die Methode die falsche Katze zurückgibt, wenn beim Aufrufen der Methode
     * die range des Gewichts der falschen Katze mitgegeben wird.
     */
    @Test
    void getCatByWeightShouldNotReturnWrongCat() {
        FelineOverLord tom = new FelineOverLord("Tom", 3);
        FelineOverLord thor = new FelineOverLord("Thor", 12);
        catCafe.addCat(tom);
        catCafe.addCat(thor);
        assertNotEquals(tom, catCafe.getCatByWeight(4, 13));
    }

    /**
     * Testet, ob die Methode null zurückgibt, wenn beim Aufrufen der Methode
     * eine range mitgegeben wird, die zu keiner Katze im Cafe passt.
     */
    @Test
    void getCatByWeightShouldBeNullIfRangeIsWrong() {
        FelineOverLord tom = new FelineOverLord("Tom", 3);
        FelineOverLord thor = new FelineOverLord("Thor", 12);
        catCafe.addCat(tom);
        catCafe.addCat(thor);
        assertNull(catCafe.getCatByWeight(1,2));
    }




}
