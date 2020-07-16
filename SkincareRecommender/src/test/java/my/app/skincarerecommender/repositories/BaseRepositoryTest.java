/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import my.app.skincarerecommender.entities.Base;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BaseRepositoryTest {
    private final BaseRepository brepo;
    @Autowired
    public BaseRepositoryTest(BaseRepository brepo) {
        this.brepo = brepo;
    }
    
//    @BeforeEach
//    public void setUp(){
//        
//    }

    /**
     * Test of findByBasetype method, of class BaseRepository.
     */
    @Test
    public void testFindByBasetype() {
        Base base = brepo.findByBasetype("Oil");
        
        assertNotNull(base);
    }

//    public class BaseRepositoryImpl implements BaseRepository {
//
//        public Base findByBasetype(String type) {
//            return null;
//        }
//    }
    
}
