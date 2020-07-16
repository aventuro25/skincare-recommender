/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import my.app.skincarerecommender.entities.Base;
import my.app.skincarerecommender.entities.Brand;
import my.app.skincarerecommender.entities.Category;
import my.app.skincarerecommender.entities.Coverage;
import my.app.skincarerecommender.entities.Finish;
import my.app.skincarerecommender.entities.Ingredient;
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
public class IngredientDaoDBTest {
    private final IngredientDao dao;
    private final UnitOfWork unit;
    private final ProductRepository prepo;
    private final IngredientRepository irepo;
    
    @Autowired
    public IngredientDaoDBTest(IngredientDao dao, UnitOfWork unit, 
            ProductRepository prepo, IngredientRepository irepo) {
        this.dao = dao;
        this.unit = unit;
        this.prepo = prepo;
        this.irepo = irepo;
    }
    
//    @BeforeEach
//    public static void setUpClass() {
//    }
    

    /**
     * Test of findByProductId method, of class IngredientDaoDB.
     */
    @Test
    public void testFindByProductId() {
        //save products
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
        cleanser.setItemnumber("1");
        cleanser.setProductname("Cleanser");
        cleanser.setUrl("n/a");
        cleanser = prepo.save(cleanser);
        
        List<Ingredient> productIngredients = new ArrayList<>();
        Ingredient i1 = new Ingredient();
        i1.setIngredientname("one");
        i1 = irepo.save(i1);
        productIngredients.add(i1);
        Ingredient i2 = new Ingredient();
        i2.setIngredientname("one");
        i2 = irepo.save(i2);
        productIngredients.add(i2);
        
        cleanser.setIngredients(productIngredients);
        
        prepo.save(cleanser);
        
        List<Ingredient> iDBs = dao.findByProductId(cleanser.getItemnumber());
        
        assertEquals(2, iDBs.size());
        
    }
    
}
