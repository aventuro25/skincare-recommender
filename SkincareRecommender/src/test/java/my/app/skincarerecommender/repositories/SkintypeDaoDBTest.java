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
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.entities.SkinType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SkintypeDaoDBTest {
    private final SkintypeDao skintypedao;
    private final UnitOfWork unit;
    private final ProductRepository prepo;
    
    @Autowired
    public SkintypeDaoDBTest(SkintypeDao skintypedao, UnitOfWork unit, ProductRepository prepo) {
        this.skintypedao = skintypedao;
        this.unit = unit;
        this.prepo = prepo;
    }
    
//    @BeforeEach
//    public static void setUpClass() {
//    }
    
    

    /**
     * Test of findByProductId method, of class SkintypeDaoDB.
     */
    @Test
    public void testFindByProductId() {
        List<Coverage> coverages = unit.getAllCoverages();
        Finish finish = unit.findFinishByType("Illuminating");
        Brand b = unit.findBrandByName("Smashbox");       
        Base silicone = unit.findBaseByType("Silicone");
        Category primer = unit.findCategoryByType("Primer");        
        List<SkinType> skintypes = unit.findAllSkinTypes();
        
        Product cleanser = new Product();
        cleanser.setCoveragetypes(coverages);
        cleanser.setFinish(finish);
        cleanser.setBrand(b);
        cleanser.setPrice(new BigDecimal("1.00"));
        cleanser.setBase(silicone);
        cleanser.setCategory(primer);
        cleanser.setSkintypes(skintypes);
        cleanser.setItemnumber("testitemm");
        cleanser.setProductname("Cleanser");
        cleanser.setUrl("n/a");
        cleanser = prepo.save(cleanser);
        
        List<SkinType> pSkintypes = skintypedao.findByProductId("testitemm");
        
        assertEquals(skintypes.size(), pSkintypes.size());
        
    }
    
}
