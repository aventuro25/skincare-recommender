/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import my.app.skincarerecommender.entities.Base;
import my.app.skincarerecommender.entities.Ingredient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IngredientRepositoryTest {
    private final IngredientRepository irepo;
    private final BaseRepository brepo;
    
    @Autowired
    public IngredientRepositoryTest(IngredientRepository irepo, BaseRepository brepo) {
        this.irepo = irepo;
        this.brepo = brepo;
    }
    
//    @BeforeEach
//    public static void setUpClass() {
//    }
    
   
    /**
     * Test of findByIngredientname method, of class IngredientRepository.
     */
    @Test
    public void testFindByIngredientname() {
        Ingredient i = new Ingredient();
        i.setIngredientname("sillystring");
        i = irepo.save(i);
        
        Ingredient iDB = irepo.findByIngredientname("sillystring");
        
        assertEquals(i, iDB);
    }

    /**
     * Test of findByBasetype method, of class IngredientRepository.
     */
//    @Test
//    public void testFindByBasetype() {
//        List<Ingredient> baseIngredients = new ArrayList<>();
//        Ingredient i = new Ingredient();
//        i.setIngredientname("joebob");
//        i=irepo.save(i);
//        baseIngredients.add(i);
//        
//        Ingredient ii = new Ingredient();
//        ii.setIngredientname("billyjoe");
//        ii=irepo.save(ii);
//        baseIngredients.add(ii);
//        
//        Base b = new Base();
//        b.setBasetype("newbase");
//        b.setIngredients(baseIngredients);
//        brepo.save(b);
        
//        List<Ingredient> ingredients = irepo.findByBasetype("newbase");
//        
//        assertEquals(2, ingredients.size());
//    }

//    public class IngredientRepositoryImpl implements IngredientRepository {
//
//        public Ingredient findByIngredientname(String name) {
//            return null;
//        }
//
//        public List<Ingredient> findByBasetype(String type) {
//            return null;
//        }
//    }
    
}
