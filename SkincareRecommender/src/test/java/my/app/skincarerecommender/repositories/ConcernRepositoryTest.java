/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import my.app.skincarerecommender.entities.Concern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ConcernRepositoryTest {
    private final ConcernRepository crepo;
    
    @Autowired
    public ConcernRepositoryTest(ConcernRepository crepo) {
        this.crepo = crepo;
    }
    
    @BeforeEach
    public void setUp(){
        
    }

    /**
     * Test of findByConcerntype method, of class ConcernRepository.
     */
    @Test
    public void testFindByConcerntype() {
        Concern c = new Concern();
        c.setConcerntype("Test");
        
        c = crepo.save(c);
        
        Concern cDB = crepo.findByConcerntype("Test");
        
        assertEquals(c, cDB);
    }

//    public class ConcernRepositoryImpl implements ConcernRepository {
//
//        public Concern findByConcerntype(String type) {
//            return null;
//        }
//    }
    
}
