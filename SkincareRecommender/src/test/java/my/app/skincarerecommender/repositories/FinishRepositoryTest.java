/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import my.app.skincarerecommender.entities.Finish;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FinishRepositoryTest {
    private final FinishRepository frepo;
    @Autowired
    public FinishRepositoryTest(FinishRepository frepo) {
        this.frepo = frepo;
    }
    
  
    @BeforeEach
    public void setUp(){
        
    }
    

    /**
     * Test of findByFinishtype method, of class FinishRepository.
     */
    @Test
    public void testFindByFinishtype() {
        
        Finish f = new Finish();
        f.setFinishtype("Test");
        
        f = frepo.save(f);
        
        Finish fDB = frepo.findByFinishtype("Test");
        
        assertEquals(f, fDB);
    }

//    public class FinishRepositoryImpl implements FinishRepository {
//
//        public Finish findByFinishtype(String type) {
//            return null;
//        }
//    }
    
}
