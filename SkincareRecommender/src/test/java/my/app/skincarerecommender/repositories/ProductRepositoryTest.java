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
public class ProductRepositoryTest {
    private final ProductRepository prepo;
    private final UnitOfWork unit;
    private final BrandRepository brepo;
    
    @Autowired
    public ProductRepositoryTest(ProductRepository prepo, UnitOfWork unit,
            BrandRepository brepo) {
        this.prepo = prepo;
        this.unit = unit;
        this.brepo = brepo;
    }
    
//    @BeforeEach
//    public static void setUpClass() {
//    }
//    

    /**
     * Test of findByBrandAndCategory method, of class ProductRepository.
     */
    @Test
    public void testFindByBrandAndCategory() {
        List<Coverage> coverages = unit.getAllCoverages();
        Finish finish = unit.findFinishByType("Illuminating");
        Brand b = new Brand();
        b.setBrandname("testbrandya");
        b = brepo.save(b);
        
       
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
        cleanser.setItemnumber("cleansertest");
        cleanser.setProductname("Cleanser");
        cleanser.setUrl("n/a");
        cleanser = prepo.save(cleanser);
        
        List<Product> pDB = prepo.findByBrandAndCategory(b,primer);
        
        assertEquals(1, pDB.size());
        
    }

    /**
     * Test of findByProductname method, of class ProductRepository.
     */
    @Test
    public void testFindByProductname() {
        List<Coverage> coverages = unit.getAllCoverages();
        Finish finish = unit.findFinishByType("Illuminating");
        Brand b = new Brand();
        b.setBrandname("testbrandya");
        b = brepo.save(b);
        
       
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
        cleanser.setItemnumber("cleansertest40");
        cleanser.setProductname("Cleanser303");
        cleanser.setUrl("n/a");
        cleanser = prepo.save(cleanser);
        
        Product cleanserDB = prepo.findByProductname("Cleanser303");
    }

//    public class ProductRepositoryImpl implements ProductRepository {
//
//        public List<Product> findByBrandAndCategory(Brand brand, Category category) {
//            return null;
//        }
//
//        public Product findByProductname(String name) {
//            return null;
//        }
//    }
    
}
