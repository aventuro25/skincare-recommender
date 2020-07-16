/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.math.BigDecimal;
import java.util.List;
import my.app.skincarerecommender.entities.Base;
import my.app.skincarerecommender.entities.Brand;
import my.app.skincarerecommender.entities.Category;
import my.app.skincarerecommender.entities.Coverage;
import my.app.skincarerecommender.entities.Finish;
import my.app.skincarerecommender.entities.Guest;
import my.app.skincarerecommender.entities.GuestMakeupSet;
import my.app.skincarerecommender.entities.GuestSkincareSet;
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.entities.SkinType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GuestSkincareSetRepositoryTest {
    private final GuestSkincareSetRepository setrepo;
    private final ProductRepository prepo;
    private final GuestRepository grepo;
    private final UnitOfWork unit;
    private final GuestMakeupSetRepository makeupsetrepo;
    
    @Autowired
    public GuestSkincareSetRepositoryTest(GuestSkincareSetRepository setrepo, 
            ProductRepository prepo, GuestRepository grepo, UnitOfWork unit, 
            GuestMakeupSetRepository makeupsetrepo) {
        this.setrepo = setrepo;
        this.prepo = prepo;
        this.grepo = grepo;
        this.unit = unit;
        this.makeupsetrepo = makeupsetrepo;
    }
    
    @BeforeEach
    public void setUpClass() {
        List<GuestMakeupSet> msets = makeupsetrepo.findAll();
        for(GuestMakeupSet ms : msets){
            makeupsetrepo.deleteById(ms.getGuestmakeupsetid());
        }
        List<GuestSkincareSet> sets = setrepo.findAll();
        for(GuestSkincareSet s : sets){
            setrepo.deleteById(s.getGuestskincaresetid());
        }
         List<Guest> guests = grepo.findAll();
        for(Guest g : guests){
            grepo.deleteById(g.getGuestid());
        }
        
         List<Product> prods = prepo.findAll();
        for(Product p : prods){
            prepo.deleteById(p.getItemnumber());
        }
       
        
        
        
       
        
    }
    
    

    /**
     * Test of findByGuest method, of class GuestSkincareSetRepository.
     */
    @Test
    public void testFindByGuest() {
        //save guest
        Guest g = new Guest();
        g.setGuestid("testguest");
        g.setGuestname("test");
        g.setPassword("password");
        g = grepo.save(g);
        
        //save products
        List<Coverage> coverages = unit.getAllCoverages();
        Finish finish = unit.findFinishByType("Illuminating");
        Brand b = unit.findBrandByName("Smashbox");
        Base oil = unit.findBaseByType("Oil");
        Base silicone = unit.findBaseByType("Silicone");
        Category primer = unit.findCategoryByType("Primer");
        Category foundation = unit.findCategoryByType("Foundation");
        List<SkinType> skintypes = unit.findAllSkinTypes();
        
        Product cleanser = new Product();
        cleanser.setCoveragetypes(coverages);
        cleanser.setFinish(finish);
        cleanser.setBrand(b);
        cleanser.setPrice(new BigDecimal("1.00"));
        cleanser.setBase(silicone);
        cleanser.setCategory(primer);
        cleanser.setSkintypes(skintypes);
        cleanser.setItemnumber("1");
        cleanser.setProductname("Cleanser");
        cleanser.setUrl("n/a");
        cleanser = prepo.save(cleanser);
        
        Product cleanser2 = new Product();
        cleanser2.setCoveragetypes(coverages);
        cleanser2.setFinish(finish);
        cleanser2.setBrand(b);
        cleanser2.setPrice(new BigDecimal("1.00"));
        cleanser2.setBase(silicone);
        cleanser2.setCategory(primer);
        cleanser2.setSkintypes(skintypes);
        cleanser2.setItemnumber("2");
        cleanser2.setProductname("Cleanser 2");
        cleanser2.setUrl("n/a");
        cleanser2 = prepo.save(cleanser2);
        
          Product moisturizer = new Product();
        moisturizer.setCoveragetypes(coverages);
        moisturizer.setFinish(finish);
        moisturizer.setBrand(b);
        moisturizer.setPrice(new BigDecimal("1.00"));
        moisturizer.setBase(oil);
        moisturizer.setCategory(foundation);
        moisturizer.setSkintypes(skintypes);
        moisturizer.setItemnumber("3");
        moisturizer.setProductname("Moisturizer");
        moisturizer.setUrl("n/a");

        moisturizer = prepo.save(moisturizer);
        
         Product moisturizer2 = new Product();
        moisturizer2.setCoveragetypes(coverages);
        moisturizer2.setFinish(finish);
        moisturizer2.setBrand(b);
        moisturizer2.setPrice(new BigDecimal("1.00"));
        moisturizer2.setBase(oil);
        moisturizer2.setCategory(foundation);
        moisturizer2.setSkintypes(skintypes);
        moisturizer2.setItemnumber("4");
        moisturizer2.setProductname("Moisturizer 2");
        moisturizer2.setUrl("n/a");

        moisturizer2 = prepo.save(moisturizer2);
        
        Product eyecream = new Product();
        eyecream.setCoveragetypes(coverages);
        eyecream.setFinish(finish);
        eyecream.setBrand(b);
        eyecream.setPrice(new BigDecimal("1.00"));
        eyecream.setBase(silicone);
        eyecream.setCategory(primer);
        eyecream.setSkintypes(skintypes);
        eyecream.setItemnumber("5");
        eyecream.setProductname("Eye cream");
        eyecream.setUrl("n/a");
        eyecream = prepo.save(eyecream);
        
          Product eyecream2 = new Product();
        eyecream2.setCoveragetypes(coverages);
        eyecream2.setFinish(finish);
        eyecream2.setBrand(b);
        eyecream2.setPrice(new BigDecimal("1.00"));
        eyecream2.setBase(oil);
        eyecream2.setCategory(foundation);
        eyecream2.setSkintypes(skintypes);
        eyecream2.setItemnumber("6");
        eyecream2.setProductname("Eye cream 2");
        eyecream2.setUrl("n/a");

        eyecream2 = prepo.save(eyecream2);
        //save makeupset
        GuestSkincareSet set = new GuestSkincareSet();
        set.setGuest(g);
        set.setProduct1(cleanser);
        set.setProduct2(moisturizer);
        set.setProduct3(eyecream);
        
        setrepo.save(set);
        
        GuestSkincareSet set2 = new GuestSkincareSet();
        set2.setGuest(g);
        set2.setProduct1(cleanser2);
        set2.setProduct2(moisturizer2);
        set2.setProduct3(eyecream2);
        
        setrepo.save(set2);
        //pull makeupset
        
        List<GuestSkincareSet> setsDB = setrepo.findByGuest(g);
        
        assertEquals(2, setsDB.size());
        
    }

//    public class GuestSkincareSetRepositoryImpl implements GuestSkincareSetRepository {
//
//        public List<GuestSkincareSet> findByGuest(Guest guest) {
//            return null;
//        }
//    }
//    
}
