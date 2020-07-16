/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.math.BigDecimal;
import java.util.List;
import my.app.skincarerecommender.entities.Attribute;
import my.app.skincarerecommender.entities.Base;
import my.app.skincarerecommender.entities.Brand;
import my.app.skincarerecommender.entities.Category;
import my.app.skincarerecommender.entities.Concern;
import my.app.skincarerecommender.entities.Coverage;
import my.app.skincarerecommender.entities.Finish;
import my.app.skincarerecommender.entities.Guest;
import my.app.skincarerecommender.entities.GuestMakeupSet;
import my.app.skincarerecommender.entities.GuestSkincareSet;
import my.app.skincarerecommender.entities.PreferenceEntity;
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.entities.SkinType;
import my.app.skincarerecommender.models.GuestModel;
import my.app.skincarerecommender.models.MakeupPreference;
import my.app.skincarerecommender.models.Preference;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UnitOfWorkTest {

    private final UnitOfWork unit;
    private final ProductRepository prepo;
    private final GuestMakeupSetRepository gmsrepo;
    private final GuestSkincareSetRepository gssrepo;
    private final GuestRepository grepo;

    @Autowired
    public UnitOfWorkTest(UnitOfWork unit, ProductRepository prepo,
            GuestMakeupSetRepository gmsrepo, GuestSkincareSetRepository gssrepo,
            GuestRepository grepo) {
        this.prepo = prepo;
        this.unit = unit;
        this.gmsrepo = gmsrepo;
        this.gssrepo = gssrepo;
        this.grepo = grepo;
    }

    @BeforeEach
    public void setUp() {
        List<GuestMakeupSet> makeupsets = gmsrepo.findAll();
        for (GuestMakeupSet g : makeupsets) {
            gmsrepo.deleteById(g.getGuestmakeupsetid());
        }

        List<GuestSkincareSet> skinsets = gssrepo.findAll();

        for (GuestSkincareSet set : skinsets) {
            gssrepo.deleteById(set.getGuestskincaresetid());
        }
        List<PreferenceEntity> prefs = unit.findAllPreferences();

        for (PreferenceEntity pe : prefs) {
            unit.deletePreferenceEntityById(pe.getPreferenceid());
        }
        List<Guest> guests = unit.findAllGuests();

        for (Guest g : guests) {
            unit.deleteGuestById(g.getGuestid());
        }

        List<Product> products = unit.findAllProducts();

        for (Product p : products) {
            unit.deleteProductById(p.getItemnumber());
        }

    }

//    /**
//     * Test of findAllSkinTypes method, of class UnitOfWork.
//     */
//    @Test
//    public void testFindAllSkinTypes() {
//    }
//
//    /**
//     * Test of findAllConcerns method, of class UnitOfWork.
//     */
//    @Test
//    public void testFindAllConcerns() {
//    }
//
//    /**
//     * Test of findAllAttributes method, of class UnitOfWork.
//     */
//    @Test
//    public void testFindAllAttributes() {
//    }
//
//    /**
//     * Test of findCrueltyFreeProductsBySkinType method, of class UnitOfWork.
//     */
//    @Test
//    public void testFindCrueltyFreeProductsBySkinType() {
//        
//    }
//
    /**
     * Test of findProductsBySkintype method, of class UnitOfWork.
     */
//    @Test
//    public void testFindProductsBySkintype() {
//    }
//
    /**
     * Test of findCrueltyFreeProductsBasedOnAttributesAndConcerns method, of
     * class UnitOfWork.
     */
//    @Test
//    public void testFindCrueltyFreeProductsBasedOnAttributesAndConcerns() {
//    }
//
    /**
     * Test of findProductsBasedOnAttributesAndConcerns method, of class
     * UnitOfWork.
     */
//    @Test
//    public void testFindProductsBasedOnAttributesAndConcerns() {
//    }
//
    /**
     * Test of findCrueltyFreeProductsBasedOnAttributes method, of class
     * UnitOfWork.
     */
//    @Test
//    public void testFindCrueltyFreeProductsBasedOnAttributes() {
//    }
//
    /**
     * Test of findProductsNotInAttributes method, of class UnitOfWork.
     */
//    @Test
//    public void testFindProductsNotInAttributes() {
//    }
//
    /**
     * Test of findProductsByConcern method, of class UnitOfWork.
     */
//    @Test
//    public void testFindProductsByConcern() {
//    }
//
    /**
     * Test of findCrueltyFreeProductsByConcern method, of class UnitOfWork.
     */
//    @Test
//    public void testFindCrueltyFreeProductsByConcern() {
//    }
//
//    /**
//     * Test of findAllBrands method, of class UnitOfWork.
//     */
//    @Test
//    public void testFindAllBrands() {
//    }
//
    /**
     * Test of findProductsByBrandidAndCategorytype method, of class UnitOfWork.
     */
//    @Test
//    public void testFindProductsByBrandidAndCategorytype() {
//    }
//
//    /**
//     * Test of getAllCoverages method, of class UnitOfWork.
//     */
//    @Test
//    public void testGetAllCoverages() {
//    }
//
//    /**
//     * Test of getAllFinishes method, of class UnitOfWork.
//     */
//    @Test
//    public void testGetAllFinishes() {
//    }
//
//    /**
//     * Test of findProductById method, of class UnitOfWork.
//     */
//    @Test
//    public void testFindProductById() {
//    }
//
    /**
     * Test of
     * findCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType
     * method, of class UnitOfWork.
     */
//    @Test
//    public void testFindCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType() {
//    }
//
    /**
     * Test of
     * findProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType
     * method, of class UnitOfWork.
     */
//    @Test
//    public void testFindProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType() {
//    }
//
    /**
     * Test of
     * findCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinishAndBaseType
     * method, of class UnitOfWork.
     */
//    @Test
//    public void testFindCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinishAndBaseType() {
//    }
//
    /**
     * Test of findProductsBasedOnCategoryAndCoverageAndFinishAndBaseType
     * method, of class UnitOfWork.
     */
//    @Test
//    public void testFindProductsBasedOnCategoryAndCoverageAndFinishAndBaseType() {
//    }
//
//    /**
//     * Test of findCategoryByType method, of class UnitOfWork.
//     */
//    @Test
//    public void testFindCategoryByType() {
//    }
    /**
     * Test of savePreference method, of class UnitOfWork.
     */
    @Test
    public void testSavePreference() {
        MakeupPreference mp = new MakeupPreference();

        Coverage c = unit.findCoverageByType("Light");

        mp.setCoverage(c);

        mp.setCrueltyfree(true);

        Finish f = unit.findFinishByType("Matte");
        mp.setFinish(f);

        Brand b = unit.findBrandByName("Smashbox");
        Base base = unit.findBaseByType("Silicone");
        Product p = new Product();
        Category cat = unit.findCategoryByType("Foundation");
        p.setBrand(b);
        p.setCategory(cat);
        p.setBase(base);
        p.setItemnumber("1");
        p.setProductname("Test Product");
        p.setPrice(new BigDecimal("1.00"));
        p.setUrl("n/a");

        Product pDB = unit.saveSimpleProduct(p);
        mp.setProduct(pDB);

        List<Attribute> att = unit.findAllAttributes();
        int[] attr = new int[att.size()];
        for (int i = 0; i < att.size(); i++) {
            attr[i] = att.get(i).getAttributeid();
        }
        mp.setA(attr);

        SkinType st = unit.findSkinTypeByType("Dry");
        mp.setSkintype(st);
        PreferenceEntity pe = unit.savePreference(mp);
        assertEquals(mp.getCoverage(), pe.getCoverage());
        assertEquals(mp.getFinish(), pe.getFinish());
        assertEquals(mp.getSkintype(), pe.getSkintype());
        assertEquals(mp.isCrueltyfree(), pe.isCrueltyfree());
//       assertEquals(mp.getProduct(), pe.getProduct());

    }

//    /**
//     * Test of findPreferenceById method, of class UnitOfWork.
//     */
//    @Test
//    public void testFindPreferenceById() {
//    }
//
//    /**
//     * Test of findGuestById method, of class UnitOfWork.
//     */
//    @Test
//    public void testFindGuestById() {
//    }
    /**
     * Test of saveGuest method, of class UnitOfWork.
     */
    @Test
    public void testSaveGuest() {
        GuestModel gm = new GuestModel();
        PreferenceEntity pe = new PreferenceEntity();

        List<Attribute> att = unit.findAllAttributes();
        int[] attr = new int[att.size()];
        for (int i = 0; i < att.size(); i++) {
            attr[i] = att.get(i).getAttributeid();
        }
        gm.setA(attr);
        List<Concern> concerns = unit.findAllConcerns();
        int[] c = new int[concerns.size()];
        for (int i = 0; i < concerns.size(); i++) {
            c[i] = concerns.get(i).getConcernid();
        }
        gm.setC(c);

        gm.setCrueltyfree(true);
        SkinType st = unit.findSkinTypeByType("Dry");
        gm.setSkintype(st);
        Coverage cov = unit.findCoverageByType("Full");
        Finish f = unit.findFinishByType("Matte");
        gm.setCoverage(cov);
        gm.setFinish(f);
        gm.setGuestid("linaloo");
        gm.setPassword("test");
        gm.setGuestname("Lina");

        unit.saveGuest(gm);

        Guest g = unit.findGuestById("linaloo");

        assertEquals(gm.getGuestid(), g.getGuestid());

    }

//    /**
//     * Test of findAllGuests method, of class UnitOfWork.
//     */
//    @Test
//    public void testFindAllGuests() {
//    }
//
    /**
     * Test of setNewAdminById method, of class UnitOfWork.
     */
    @Test
    public void testSetNewAdminById() {

        GuestModel gm = new GuestModel();
        PreferenceEntity pe = new PreferenceEntity();

        List<Attribute> att = unit.findAllAttributes();
        int[] attr = new int[att.size()];
        for (int i = 0; i < att.size(); i++) {
            attr[i] = att.get(i).getAttributeid();
        }
        gm.setA(attr);
        List<Concern> concerns = unit.findAllConcerns();
        int[] c = new int[concerns.size()];
        for (int i = 0; i < concerns.size(); i++) {
            c[i] = concerns.get(i).getConcernid();
        }
        gm.setC(c);

        gm.setCrueltyfree(true);
        SkinType st = unit.findSkinTypeByType("Dry");
        gm.setSkintype(st);
        Coverage cov = unit.findCoverageByType("Full");
        Finish f = unit.findFinishByType("Matte");
        gm.setCoverage(cov);
        gm.setFinish(f);
        gm.setGuestid("linaloo");
        gm.setPassword("test");
        gm.setGuestname("Lina");

        unit.saveGuest(gm);

        Guest g = unit.findGuestById("linaloo");

        assertEquals(gm.getGuestid(), g.getGuestid());
        assertFalse(g.isAdministrator());

        unit.setNewAdminById("linaloo", true);

        Guest gDB = unit.findGuestById("linaloo");

        assertTrue(gDB.isAdministrator());

    }
//
//    /**
//     * Test of findAllCategories method, of class UnitOfWork.
//     */
//    @Test
//    public void testFindAllCategories() {
//    }

    /**
     * Test of saveProduct method, of class UnitOfWork.
     */
//    @Test
//    public void testSaveProduct() {
//    }
    /**
     * Test of parseProduct method, of class UnitOfWork.
     */
//    @Test
//    public void testParseProduct() {
//    }
//    /**
//     * Test of findCompatibilityList method, of class UnitOfWork.
//     */
//    @Test
//    public void testFindCompatibilityList() {
//    }
//
//    /**
//     * Test of findCompatibility method, of class UnitOfWork.
//     */
//    @Test
//    public void testFindCompatibility() {
//    }
//
//    /**
//     * Test of findConcernById method, of class UnitOfWork.
//     */
//    @Test
//    public void testFindConcernById() {
//    }
    
    //i debugged this and i know it works but jpa is giving a "lazy fetch" exception and so it fails.
//    @Test
//    public void testSaveGuestProducts() {
//
//        Guest g = new Guest();
//        g.setGuestid("testguest22");
//        g.setGuestname("guest");
//        g.setPassword("pass");
//        g = grepo.save(g);
//
//        List<Coverage> coverages = unit.getAllCoverages();
//        Finish finish = unit.findFinishByType("Illuminating");
//        Brand b = unit.findBrandByName("Smashbox");
//        Base silicone = unit.findBaseByType("Silicone");
//        Category cleanser = unit.findCategoryByType("Cleanser");
//        Category moisturizer = unit.findCategoryByType("Moisturizer");
//        Category eyecream = unit.findCategoryByType("Eye Cream");
//        List<SkinType> skintypes = unit.findAllSkinTypes();
//
//        Product p = new Product();
//        p.setCoveragetypes(coverages);
//        p.setFinish(finish);
//        p.setBrand(b);
//        p.setPrice(new BigDecimal("1.00"));
//        p.setBase(silicone);
//        p.setCategory(cleanser);
//        p.setSkintypes(skintypes);
//        p.setItemnumber("101");
//        p.setProductname("Cleanser");
//        p.setUrl("n/a");
//        p = prepo.save(p);
//
//        Product f = new Product();
//        f.setCoveragetypes(coverages);
//        f.setFinish(finish);
//        f.setBrand(b);
//        f.setPrice(new BigDecimal("1.00"));
//        f.setBase(silicone);
//        f.setCategory(moisturizer);
//        f.setSkintypes(skintypes);
//        f.setItemnumber("202");
//        f.setProductname("Cleanser 2");
//        f.setUrl("n/a");
//        f = prepo.save(f);
//
//        Product e = new Product();
//        e.setCoveragetypes(coverages);
//        e.setFinish(finish);
//        e.setBrand(b);
//        e.setPrice(new BigDecimal("1.00"));
//        e.setBase(silicone);
//        e.setCategory(eyecream);
//        e.setSkintypes(skintypes);
//        e.setItemnumber("203");
//        e.setProductname("Cleanser 2");
//        e.setUrl("n/a");
//        e = prepo.save(e);
//
//        String[] recs = {p.getItemnumber(), f.getItemnumber(), e.getItemnumber()};
//
//        unit.saveGuestProducts(recs, g.getGuestid());
//        Guest gDB = unit.findGuestByGuestId(g.getGuestid());
//        
//
//        assertTrue(!gDB.getProducts().isEmpty());
//    }

//    @Test
//    public void testFindCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinish(){
//        unit.findCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinish(sa, foundation, coverage, finish, skintype);
//    }
//    
//    @Test
//    public void findCrueltyFreeProductsBasedOnAttributesAndCategoryAndFinish(){
//        unit.findCrueltyFreeProductsBasedOnAttributesAndCategoryAndFinish(sa, primer, finish, skintype);
//    }
//    
//    @Test 
//    public void findProductsBasedOnAttributesAndCategoryAndCoverageAndFinish(){
//        unit.findProductsBasedOnAttributesAndCategoryAndCoverageAndFinish(sa, foundation, coverage, finish, skintype);
//    }
//    
//    @Test
//    public void findProductsBasedOnAttributesAndCategoryAndFinish(){
//        
//    }
//     @Test
//     public void findCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinish(){
//         
//     }
//     
//     @Test
//     public void findCrueltyFreeProductsBasedOnCategoryAndFinish(){
//         
//     }
//     
//     @Test
//     public void findProductsBasedOnCategoryAndCoverageAndFinish(){
//         
//     }
    //all my "find products" relies on the same hydration method so i'm only testing one.
    @Test
    public void findProductsBasedOnCategoryAndFinish() {
        List<Coverage> coverages = unit.getAllCoverages();
        Brand b = unit.findBrandByName("Smashbox");
        Base silicone = unit.findBaseByType("Silicone");
        List<SkinType> skintypes = unit.findAllSkinTypes();
        
        Category c = unit.findCategoryByType("Primer");
        Finish finish = unit.findFinishByType("Matte");
        Product primer = new Product();
        primer.setCoveragetypes(coverages);
        primer.setFinish(finish);
        primer.setBrand(b);
        primer.setPrice(new BigDecimal("1.00"));
        primer.setBase(silicone);
        primer.setCategory(c);
        primer.setSkintypes(skintypes);
        primer.setItemnumber("2");
        primer.setProductname("Cleanser 2");
        primer.setUrl("n/a");
        primer = prepo.save(primer);
        SkinType st = unit.findSkinTypeByType("Dry");
        List<Product> prods = unit.findProductsBasedOnCategoryAndFinish(c, finish, st);
        
        for(Product p : prods){
            assertNotNull(p.getBase());
            assertNotNull(p.getBrand());
            assertNotNull(p.getCategory());
            assertNotNull(p.getFinish());
            assertNotNull(p.getItemnumber());
            assertNotNull(p.getPrice());
            assertNotNull(p.getProductname());
            assertNotNull(p.getUrl());
           
           
        }
    }

    @Test
    public void convertGuestPreferenceToModel() {
        PreferenceEntity p = new PreferenceEntity();
        p.setCrueltyfree(true);
        p.setSkintype(unit.findSkinTypeByType("Dry"));
        p.setAttributes(unit.findAllAttributes());
        p.setConcerns(unit.findAllConcerns());

        Preference mp = unit.convertGuestPreferenceToModel(p);

        assertTrue(mp.isCrueltyfree());
        assertNotNull(mp.getSkintype());

        assertTrue(mp.getA() != null);
        assertTrue(mp.getC() != null);

    }

    @Test
    public void convertGuestPreferenceToMakeupModel() {
        PreferenceEntity p = new PreferenceEntity();
        p.setCrueltyfree(true);
        p.setSkintype(unit.findSkinTypeByType("Dry"));
        p.setFinish(unit.findFinishByType("Matte"));
        p.setCoverage(unit.findCoverageByType("Full"));
        p.setAttributes(unit.findAllAttributes());

        MakeupPreference mp = unit.convertGuestPreferenceToMakeupModel(p);

        assertTrue(mp.isCrueltyfree());
        assertNotNull(mp.getSkintype());
        assertNotNull(mp.getCoverage());
        assertNotNull(mp.getFinish());
        assertTrue(mp.getA() != null);

    }

    @Test
    public void saveSimpleRecs() {
        String username = "testguest";

        Guest g = unit.findGuestByGuestId(username);

        List<Coverage> coverages = unit.getAllCoverages();
        Finish finish = unit.findFinishByType("Illuminating");
        Brand b = unit.findBrandByName("Smashbox");
        Base silicone = unit.findBaseByType("Silicone");
        Category cleanser = unit.findCategoryByType("Cleanser");
        Category moisturizer = unit.findCategoryByType("Moisturizer");
        Category eyecream = unit.findCategoryByType("Eye Cream");
        List<SkinType> skintypes = unit.findAllSkinTypes();

        Product p = new Product();
        p.setCoveragetypes(coverages);
        p.setFinish(finish);
        p.setBrand(b);
        p.setPrice(new BigDecimal("1.00"));
        p.setBase(silicone);
        p.setCategory(cleanser);
        p.setSkintypes(skintypes);
        p.setItemnumber("101");
        p.setProductname("Cleanser");
        p.setUrl("n/a");
        p = prepo.save(p);

        Product f = new Product();
        f.setCoveragetypes(coverages);
        f.setFinish(finish);
        f.setBrand(b);
        f.setPrice(new BigDecimal("1.00"));
        f.setBase(silicone);
        f.setCategory(moisturizer);
        f.setSkintypes(skintypes);
        f.setItemnumber("202");
        f.setProductname("Cleanser 2");
        f.setUrl("n/a");
        f = prepo.save(f);

        Product e = new Product();
        e.setCoveragetypes(coverages);
        e.setFinish(finish);
        e.setBrand(b);
        e.setPrice(new BigDecimal("1.00"));
        e.setBase(silicone);
        e.setCategory(eyecream);
        e.setSkintypes(skintypes);
        e.setItemnumber("203");
        e.setProductname("Cleanser 2");
        e.setUrl("n/a");
        e = prepo.save(e);

        String[] recs = {p.getItemnumber() + "_" + f.getItemnumber() + "_" + e.getItemnumber()};

        unit.saveSimpleRecs(recs, username);

        List<GuestSkincareSet> sets = unit.findSetsByUsername(g);

        assertEquals(1, sets.size());
    }

    @Test
    public void saveFullMakeupRecs() {
        String username = "testguest";

        Guest g = unit.findGuestByGuestId(username);

        List<Coverage> coverages = unit.getAllCoverages();
        Finish finish = unit.findFinishByType("Illuminating");
        Brand b = unit.findBrandByName("Smashbox");
        Base silicone = unit.findBaseByType("Silicone");
        Category primer = unit.findCategoryByType("Primer");
        Category foundation = unit.findCategoryByType("Foundation");
        List<SkinType> skintypes = unit.findAllSkinTypes();

        Product p = new Product();
        p.setCoveragetypes(coverages);
        p.setFinish(finish);
        p.setBrand(b);
        p.setPrice(new BigDecimal("1.00"));
        p.setBase(silicone);
        p.setCategory(primer);
        p.setSkintypes(skintypes);
        p.setItemnumber("101");
        p.setProductname("Cleanser");
        p.setUrl("n/a");
        p = prepo.save(p);

        Product f = new Product();
        f.setCoveragetypes(coverages);
        f.setFinish(finish);
        f.setBrand(b);
        f.setPrice(new BigDecimal("1.00"));
        f.setBase(silicone);
        f.setCategory(foundation);
        f.setSkintypes(skintypes);
        f.setItemnumber("202");
        f.setProductname("Cleanser 2");
        f.setUrl("n/a");
        f = prepo.save(f);

        String[] recs = {p.getItemnumber() + "_" + f.getItemnumber()};

        unit.saveFullMakeupRecs(recs, username);

        List<GuestMakeupSet> sets = unit.findMakeupSetsByGuest(g);

        assertEquals(1, sets.size());
//        GuestMakeupSet gms  = new GuestMakeupSet();
//        gms.setGuest(g);
//        gms.setProduct1(p);
//        gms.setProduct2(f);
//        
//        gmsrepo.save(gms);

    }
}
