/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import my.app.skincarerecommender.entities.Attribute;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AttributeRepositoryTest {
    private final AttributeRepository arepo;
    
    @Autowired
    public AttributeRepositoryTest(AttributeRepository arepo) {
        this.arepo = arepo;
    }
    
//    @BeforeEach()
//    public void setUp(){
//        
//    }

    /**
     * Test of findByAttributename method, of class AttributeRepository.
     */
    @Test
    public void testFindByAttributename() {
        Attribute a = arepo.findByAttributename("Gluten");
        
        assertNotNull(a);
    }

//    public class AttributeRepositoryImpl implements AttributeRepository {
//
//        public Attribute findByAttributename(String name) {
//            return null;
//        }
//    }
    
}
