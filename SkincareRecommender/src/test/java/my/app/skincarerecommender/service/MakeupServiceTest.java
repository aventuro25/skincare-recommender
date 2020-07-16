/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import my.app.skincarerecommender.entities.Attribute;
import my.app.skincarerecommender.entities.Base;
import my.app.skincarerecommender.entities.Brand;
import my.app.skincarerecommender.entities.Category;
import my.app.skincarerecommender.entities.Concern;
import my.app.skincarerecommender.entities.Coverage;
import my.app.skincarerecommender.entities.Finish;
import my.app.skincarerecommender.entities.Ingredient;
import my.app.skincarerecommender.entities.PreferenceEntity;
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.entities.SkinType;
import my.app.skincarerecommender.models.MakeupPreference;
import my.app.skincarerecommender.models.MakeupRecommendation;
import my.app.skincarerecommender.repositories.UnitOfWork;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MakeupServiceTest {

    private final MakeupService service;
//    private final SkincareService skin;
    private final UnitOfWork unit;

    @Autowired
    public MakeupServiceTest(MakeupService service, UnitOfWork unit) {
        this.service = service;
        this.unit = unit;
//        this.skin = skin;
    }

    /**
     * Test of findAllBrands method, of class MakeupService.
     */
//    @Test
//    public void testFindAllBrands() {
//    }
    /**
     * Test of findProductsByBrandidAndCategory method, of class MakeupService.
     */
//    @Test
//    public void testFindProductsByBrandidAndCategory() {
//    }
    /**
     * Test of getAllCoverages method, of class MakeupService.
     */
//    @Test
//    public void testGetAllCoverages() {
//    }
    /**
     * Test of getAllFinishes method, of class MakeupService.
     */
//    @Test
//    public void testGetAllFinishes() {
//    }
    /**
     * Test of calculateFoundationMatch method, of class MakeupService.
     */
    @Test
    public void testCalculateFoundationMatch() {
        Brand b = service.findBrandByName("Smashbox");
        Product foundation = new Product();
        foundation.setPrice(new BigDecimal("1.00"));
        foundation.setBase(service.findBaseByType("Silicone"));
        foundation.setBrand(b);
        foundation.setCategory(service.findCategoryByType("Foundation"));
        foundation.setItemnumber("2");
        foundation.setProductname("BaseProduct");
        foundation.setUrl("n/a");
        unit.saveSimpleProduct(foundation);
        PreferenceEntity pe = new PreferenceEntity();
        pe.setCrueltyfree(true);
        pe.setSkintype(service.findSkinTypeByType("Dry"));
        pe.setCoverage(service.findCoverageByType("n/a"));
        pe.setFinish(service.findFinishByType("Matte"));
        List<Attribute> att = new ArrayList<>();
        att.add(service.findAttributeByName("Gluten"));
        pe.setAttributes(att);
        List<Concern> concerns = new ArrayList<>();
        concerns.add(service.findConcernByType("Dryness"));
        pe.setConcerns(concerns);
        pe.setProduct(foundation);

        Product primer = new Product();
        primer.setBase(service.findBaseByType("Silicone"));
        primer.setPrice(new BigDecimal("1.00"));
        primer.setBrand(b);
        List<SkinType> st = new ArrayList<>();
        st.add(unit.findSkinTypeByType("Dry"));
        primer.setSkintypes(st);

        primer.setFinish(service.findFinishByType("Matte"));
        primer.setCategory(service.findCategoryByType("Primer"));
        List<Coverage> covs = new ArrayList<>();
        covs.add(unit.findCoverageByType("n/a"));
        primer.setCoveragetypes(covs);
        primer.setItemnumber("3");
        primer.setProductname("Primer1");
        primer.setUrl("n/a");
        unit.saveSimpleProduct(primer);

        Product primer2 = new Product();
        primer2.setBase(service.findBaseByType("Silicone"));
        primer2.setPrice(new BigDecimal("1.00"));
        primer2.setBrand(b);
        primer2.setFinish(service.findFinishByType("Matte"));
        primer2.setCategory(service.findCategoryByType("Primer"));
        primer2.setItemnumber("4");
        primer2.setProductname("Primer2");
        primer2.setUrl("n/a");
        List<Ingredient> ii = new ArrayList<>();
        ii.add(unit.findIngredientByName("Triticum"));
        primer2.setIngredients(ii);
        unit.saveSimpleProduct(primer2);

        Product primer3 = new Product();
        primer3.setBase(service.findBaseByType("Oil"));
        primer3.setPrice(new BigDecimal("1.00"));
        primer3.setBrand(b);
        primer3.setFinish(service.findFinishByType("Matte"));
        primer3.setCategory(service.findCategoryByType("Primer"));
        primer3.setItemnumber("5");
        primer3.setProductname("Primer3");
        primer3.setUrl("n/a");
        unit.saveSimpleProduct(primer3);

        List<Product> primerrecs = service.calculateFoundationMatch(pe);

        assertEquals(1, primerrecs.size());
    }

    /**
     * Test of savePreference method, of class MakeupService.
     */
//    @Test
//    public void testSavePreference() {
//    }
    /**
     * Test of getPreferenceById method, of class MakeupService.
     */
//    @Test
//    public void testGetPreferenceById() {
//    }
    /**
     * Test of calculateFullMatch method, of class MakeupService.
     */
    @Test
    public void testCalculateFullMatch() {

        //testing algorithm not sql calls - will use most exclusive call to test the algorithm
        Attribute gluten = unit.findAttributeByName("Gluten");
        int[] a = {gluten.getAttributeid()};

        MakeupPreference mp1 = new MakeupPreference();
        mp1.setCoverage(unit.findCoverageByType("Light"));
        mp1.setCrueltyfree(true);
        mp1.setSkintype(unit.findSkinTypeByType("Dry"));
        mp1.setFinish(unit.findFinishByType("Illuminating"));
        mp1.setA(a);

        List<Coverage> coverages = unit.getAllCoverages();
        Finish finish = unit.findFinishByType("Illuminating");
        Brand b = unit.findBrandByName("Smashbox");
        Base oil = unit.findBaseByType("Oil");
        Base silicone = unit.findBaseByType("Silicone");
        Category primer = unit.findCategoryByType("Primer");
        Category foundation = unit.findCategoryByType("Foundation");
        List<SkinType> skintypes = unit.findAllSkinTypes();

        Product primer1 = new Product();
        primer1.setCoveragetypes(coverages);
        primer1.setFinish(finish);
        primer1.setBrand(b);
        primer1.setPrice(new BigDecimal("1.00"));
        primer1.setBase(oil);
        primer1.setCategory(primer);
        primer1.setSkintypes(skintypes);
        primer1.setItemnumber("1");
        primer1.setProductname("Primer 1");
        primer1.setUrl("n/a");

        unit.saveSimpleProduct(primer1);

        Product primer2 = new Product();
        primer2.setCoveragetypes(coverages);
        primer2.setFinish(finish);
        primer2.setBrand(b);
        primer2.setPrice(new BigDecimal("1.00"));
        primer2.setBase(silicone);
        primer2.setCategory(primer);
        primer2.setSkintypes(skintypes);
        primer2.setItemnumber("2");
        primer2.setProductname("Primer 2");
        primer2.setUrl("n/a");
//        Product primer3 = new Product();
        unit.saveSimpleProduct(primer2);

        Product foundation1 = new Product();
        foundation1.setCoveragetypes(coverages);
        foundation1.setFinish(finish);
        foundation1.setBrand(b);
        foundation1.setPrice(new BigDecimal("1.00"));
        foundation1.setBase(oil);
        foundation1.setCategory(foundation);
        foundation1.setSkintypes(skintypes);
        foundation1.setItemnumber("3");
        foundation1.setProductname("Foundation 3");
        foundation1.setUrl("n/a");

        unit.saveSimpleProduct(foundation1);

        Product foundation2 = new Product();
        foundation2.setCoveragetypes(coverages);
        foundation2.setFinish(finish);
        foundation2.setBrand(b);
        foundation2.setBase(silicone);
        foundation2.setPrice(new BigDecimal("1.00"));
        foundation2.setCategory(foundation);
        foundation2.setSkintypes(skintypes);
        foundation2.setItemnumber("4");
        foundation2.setProductname("Foundation 4");
        foundation2.setUrl("n/a");
//        Product foundation3 = new Product();
        unit.saveSimpleProduct(foundation2);

        List<MakeupRecommendation> recs = service.calculateFullMatch(mp1);

        assertEquals(2, recs.size());
    }
}
