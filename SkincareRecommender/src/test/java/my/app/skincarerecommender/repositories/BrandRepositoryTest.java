/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import my.app.skincarerecommender.entities.Brand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BrandRepositoryTest {
    private final BrandRepository brepo;
    
    @Autowired
    public BrandRepositoryTest(BrandRepository brepo) {
        this.brepo = brepo;
    }
    
    @BeforeEach
    public void setUp(){
        
    }

    /**
     * Test of findByBrandname method, of class BrandRepository.
     */
    @Test
    public void testFindByBrandname() {
        Brand b = new Brand();
        b.setBrandname("Test");
        b.setCrueltyfree(true);
        
        b = brepo.save(b);
        
        Brand brandDB = brepo.findByBrandname("Test");
        
        assertEquals(b, brandDB);
    }

//    public class BrandRepositoryImpl implements BrandRepository {
//
//        public Brand findByBrandname(String brandname) {
//            return null;
//        }
//    }
    
}
