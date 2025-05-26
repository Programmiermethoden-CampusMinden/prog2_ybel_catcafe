package catcafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CatCafeTest
{
    private CatCafe catCafe;
    private FelineOverLord cat1;
    private FelineOverLord cat2;

    @BeforeEach
    void setup()
    {
        catCafe = new CatCafe();
        cat1 = new FelineOverLord("cat1",10);
        cat2 = new FelineOverLord("cat2",20);
    }

    @Test
    void addCat()
    {
        catCafe.addCat(cat1);
        catCafe.addCat(cat2);
    }

    @Test
    void getCatAmount()
    {
        catCafe.getCatCount();
        catCafe.addCat(cat1);
        catCafe.addCat(cat2);
        catCafe.getCatCount();
    }

    @Test
    void getCatByName()
    {
        catCafe.addCat(cat1);
        catCafe.getCatByName("cat1");
    }

    @Test
    void getCatByNameWithNoCat()
    {
        catCafe.getCatByName("cat1");
    }

}
