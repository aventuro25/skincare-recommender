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
import my.app.skincarerecommender.entities.Concern;
import my.app.skincarerecommender.entities.Coverage;
import my.app.skincarerecommender.entities.Finish;
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
public class ConcernDaoDBTest {
    private final UnitOfWork unit;
private final ConcernDao dao;
    @Autowired
    public ConcernDaoDBTest(ConcernDao dao, UnitOfWork unit) {
        this.dao = dao;
        this.unit = unit;
    }

    @BeforeEach
    public void setUp() {

    }

    /**
     * Test of findByProductId method, of class ConcernDaoDB.
     */
    @Test
    public void testFindByProductId() {
        List<Concern> concerns = unit.findAllConcerns();
        
       
        
        List<Coverage> coverages = unit.getAllCoverages();
        Finish finish = unit.findFinishByType("Illuminating");
        Brand b = unit.findBrandByName("Smashbox");
        Base oil = unit.findBaseByType("Oil");
       
        Category primer = unit.findCategoryByType("Primer");
        
        List<SkinType> skintypes = unit.findAllSkinTypes();
        Product primer1 = new Product();
        primer1.setCoveragetypes(coverages);
        primer1.setFinish(finish);
        primer1.setBrand(b);
        primer1.setPrice(new BigDecimal("1.00"));
        primer1.setBase(oil);
        primer1.setCategory(primer);
        primer1.setSkintypes(skintypes);
        primer1.setItemnumber("11");
        primer1.setProductname("Primer 1");
        primer1.setUrl("n/a");
        primer1.setConcerns(concerns);

        unit.saveSimpleProduct(primer1);
        
        List<Concern> concernsDB = dao.findByProductId("11");
        
        assertEquals(concerns.size(), concernsDB.size());
    }

}
