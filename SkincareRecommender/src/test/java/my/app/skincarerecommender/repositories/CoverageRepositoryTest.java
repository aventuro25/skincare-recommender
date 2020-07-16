/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import my.app.skincarerecommender.entities.Coverage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CoverageRepositoryTest {

    private final CoverageRepository crepo;

    @Autowired
    public CoverageRepositoryTest(CoverageRepository crepo) {
        this.crepo = crepo;
    }

    @BeforeEach
    public void setUp() {

    }

    /**
     * Test of findByCoveragetype method, of class CoverageRepository.
     */
    @Test
    public void testFindByCoveragetype() {
        Coverage c = new Coverage();
        c.setCoveragetype("Test");

        c = crepo.save(c);
        
        Coverage cDB = crepo.findByCoveragetype("Test");
        
        assertEquals(c, cDB);
    }

//    public class CoverageRepositoryImpl implements CoverageRepository {
//
//        public Coverage findByCoveragetype(String type) {
//            return null;
//        }
//    }
}
