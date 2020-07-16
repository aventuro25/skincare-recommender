/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import my.app.skincarerecommender.entities.Category;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryRepositoryTest {
    private final CategoryRepository crepo;
    
    @Autowired
    public CategoryRepositoryTest(CategoryRepository crepo) {
        this.crepo = crepo;
    }
    
   
    @BeforeEach
    public void setUp(){
        
    }

    /**
     * Test of findByCategorytype method, of class CategoryRepository.
     */
    @Test
    public void testFindByCategorytype() {
        Category c = new Category();
        c.setCategorytype("Test");
        
        c = crepo.save(c);
        
        Category cDB = crepo.findByCategorytype("Test");
        
        assertEquals(c, cDB);
    }

//    public class CategoryRepositoryImpl implements CategoryRepository {
//
//        public Category findByCategorytype(String type) {
//            return null;
//        }
//    }
//    
}
