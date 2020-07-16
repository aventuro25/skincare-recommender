/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.util.List;
import my.app.skincarerecommender.entities.Brand;
import my.app.skincarerecommender.entities.Category;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BrandDaoDBTest {
    private final CategoryRepository crepo;
    private final BrandDao bdao;
    @Autowired
    public BrandDaoDBTest(CategoryRepository crepo, BrandDao bdao) {
        this.crepo = crepo;
        this.bdao = bdao;
    }
    
//    @BeforeEach
//    public void setUp(){
//        
//    }

    /**
     * Test of findBrandsByCategory method, of class BrandDaoDB.
     */
    @Test
    public void testFindBrandsByCategory() {
        Category c = crepo.findByCategorytype("Primer");
        
        List<Brand> brands = bdao.findBrandsByCategory(c.getCategoryid());
        
        assertTrue(!brands.isEmpty());
    }
    
}
