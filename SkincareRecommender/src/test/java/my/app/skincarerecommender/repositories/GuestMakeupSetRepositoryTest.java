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
public class GuestMakeupSetRepositoryTest {
    private final GuestMakeupSetRepository gmsrepo;
    private final GuestRepository grepo;
    private final UnitOfWork unit;
    private final ProductRepository prepo;
    
    @Autowired
    public GuestMakeupSetRepositoryTest(GuestMakeupSetRepository gmsrepo, 
            GuestRepository grepo, UnitOfWork unit, ProductRepository prepo) {
        this.gmsrepo = gmsrepo;
        this.grepo = grepo;
        this.unit = unit;
        this.prepo = prepo;
    }
    
    @BeforeEach
    public void setUpClass() {
        List<GuestMakeupSet> sets = gmsrepo.findAll();
        for(GuestMakeupSet s : sets){
            gmsrepo.deleteById(s.getGuestmakeupsetid());
        }
        
        List<Product> p = unit.findAllProducts();
        for(Product prod : p){
            unit.deleteProductById(prod.getItemnumber());
        }
    }
    
    

    /**
     * Test of findByGuest method, of class GuestMakeupSetRepository.
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
        
        Product primer2 = new Product();
        primer2.setCoveragetypes(coverages);
        primer2.setFinish(finish);
        primer2.setBrand(b);
        primer2.setPrice(new BigDecimal("1.00"));
        primer2.setBase(silicone);
        primer2.setCategory(primer);
        primer2.setSkintypes(skintypes);
        primer2.setItemnumber("20");
        primer2.setProductname("Primer 2");
        primer2.setUrl("n/a");
        primer2 = prepo.save(primer2);
        
          Product foundation1 = new Product();
        foundation1.setCoveragetypes(coverages);
        foundation1.setFinish(finish);
        foundation1.setBrand(b);
        foundation1.setPrice(new BigDecimal("1.00"));
        foundation1.setBase(oil);
        foundation1.setCategory(foundation);
        foundation1.setSkintypes(skintypes);
        foundation1.setItemnumber("30");
        foundation1.setProductname("Foundation 3");
        foundation1.setUrl("n/a");

        foundation1 = prepo.save(foundation1);
        
        Product primer1 = new Product();
        primer1.setCoveragetypes(coverages);
        primer1.setFinish(finish);
        primer1.setBrand(b);
        primer1.setPrice(new BigDecimal("1.00"));
        primer1.setBase(silicone);
        primer1.setCategory(primer);
        primer1.setSkintypes(skintypes);
        primer1.setItemnumber("2");
        primer1.setProductname("Primer 2");
        primer1.setUrl("n/a");
        primer1 = prepo.save(primer1);
        
          Product foundation2 = new Product();
        foundation2.setCoveragetypes(coverages);
        foundation2.setFinish(finish);
        foundation2.setBrand(b);
        foundation2.setPrice(new BigDecimal("1.00"));
        foundation2.setBase(oil);
        foundation2.setCategory(foundation);
        foundation2.setSkintypes(skintypes);
        foundation2.setItemnumber("3");
        foundation2.setProductname("Foundation 11");
        foundation2.setUrl("n/a");

        foundation2 = prepo.save(foundation2);
        //save makeupset
        GuestMakeupSet set = new GuestMakeupSet();
        set.setGuest(g);
        set.setProduct1(primer2);
        set.setProduct2(foundation1);
        
        gmsrepo.save(set);
        
        GuestMakeupSet set2 = new GuestMakeupSet();
        set2.setGuest(g);
        set2.setProduct1(primer1);
        set2.setProduct2(foundation2);
        
        gmsrepo.save(set2);
        //pull makeupset
        
        List<GuestMakeupSet> setsDB = gmsrepo.findByGuest(g);
        
        assertEquals(2, setsDB.size());
    }

//    public class GuestMakeupSetRepositoryImpl implements GuestMakeupSetRepository {
//
//        public List<GuestMakeupSet> findByGuest(Guest guest) {
//            return null;
//        }
//    }
    
}
