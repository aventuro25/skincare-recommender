/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import my.app.skincarerecommender.entities.SkinType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SkintypeRepositoryTest {
    private final SkintypeRepository skinrepo;
    
    @Autowired
    public SkintypeRepositoryTest(SkintypeRepository skinrepo) {
        this.skinrepo = skinrepo;
    }
    
//    @BeforeEach
//    public static void setUpClass() {
//    }


    /**
     * Test of findBySkintypename method, of class SkintypeRepository.
     */
    @Test
    public void testFindBySkintypename() {
        SkinType st = new SkinType();
        st.setSkintypename("testskintype");
        st = skinrepo.save(st);
        
        SkinType stDB = skinrepo.findBySkintypename("testskintype");
        
        assertEquals(st, stDB);
    }

//    public class SkintypeRepositoryImpl implements SkintypeRepository {
//
//        public SkinType findBySkintypename(String name) {
//            return null;
//        }
//    }
    
}
