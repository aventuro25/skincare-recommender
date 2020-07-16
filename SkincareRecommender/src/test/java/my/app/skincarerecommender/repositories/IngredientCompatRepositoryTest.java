/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.util.List;
import my.app.skincarerecommender.entities.Ingredient;
import my.app.skincarerecommender.entities.IngredientCompatibility;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IngredientCompatRepositoryTest {
    private final IngredientCompatRepository compatrepo;
    private final IngredientRepository irepo;
    
    @Autowired
    public IngredientCompatRepositoryTest(IngredientCompatRepository compatrepo, 
            IngredientRepository irepo) {
        this.compatrepo = compatrepo;
        this.irepo = irepo;
    }
    
//    @BeforeEach
//    public static void setUpClass() {
//    }
    
   

    /**
     * Test of findCompatibilityList method, of class IngredientCompatRepository.
     */
    @Test
    public void testFindCompatibilityList() {
        Ingredient i1 = new Ingredient();
        i1.setIngredientname("ingredient 1");
        i1 = irepo.save(i1);
        //add ingredient 2
       Ingredient i2 = new Ingredient();
        i2.setIngredientname("ingredient 2");
        i2 = irepo.save(i2);
        //add compatibility
        IngredientCompatibility ic = new IngredientCompatibility();
        ic.setIngredient1(i1);
        ic.setIngredient2(i2);
        ic.setRating(1);

        ic = compatrepo.save(ic);
        
        
        //add ingredient 3
        Ingredient i3 = new Ingredient();
        i3.setIngredientname("ingredient 3");
        i3 = irepo.save(i3);
        //add compat
        IngredientCompatibility ic2 = new IngredientCompatibility();
        ic2.setIngredient1(i2);
        ic2.setIngredient2(i3);
        ic2.setRating(2);

        ic2 = compatrepo.save(ic2);
        
        
        //pulll list by ingredient 1
        
        List<IngredientCompatibility> compats = compatrepo.findCompatibilityList(i2.getIngredientid());
        assertEquals(2, compats.size());
    }

    /**
     * Test of findCompatibility method, of class IngredientCompatRepository.
     */
    @Test
    public void testFindCompatibility() {
        //add ingredient 1
        
        Ingredient i1 = new Ingredient();
        i1.setIngredientname("ingredient 1");
        i1 = irepo.save(i1);
        //add ingredient 2
       Ingredient i2 = new Ingredient();
        i2.setIngredientname("ingredient 2");
        i2 = irepo.save(i2);
        //add compatibility
        IngredientCompatibility ic = new IngredientCompatibility();
        ic.setIngredient1(i1);
        ic.setIngredient2(i2);
        ic.setRating(1);

        ic = compatrepo.save(ic);
        
        
        //add ingredient 3
        Ingredient i3 = new Ingredient();
        i3.setIngredientname("ingredient 3");
        i3 = irepo.save(i3);
        //add compat
        IngredientCompatibility ic2 = new IngredientCompatibility();
        ic2.setIngredient1(i2);
        ic2.setIngredient2(i3);
        ic2.setRating(2);

        ic2 = compatrepo.save(ic2);
        //pull compat for 1 and 2
        
        IngredientCompatibility icDB = compatrepo.findCompatibility(i1.getIngredientid(), i2.getIngredientid());
        
        assertEquals(icDB.getRating(), 1);
        
    }

//    public class IngredientCompatRepositoryImpl implements IngredientCompatRepository {
//
//        public List<IngredientCompatibility> findCompatibilityList(int id) {
//            return null;
//        }
//
//        public IngredientCompatibility findCompatibility(int i, int i2) {
//            return null;
//        }
//    }
//    
}
