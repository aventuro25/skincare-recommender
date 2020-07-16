/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import my.app.skincarerecommender.entities.Brand;
import my.app.skincarerecommender.entities.Coverage;
import my.app.skincarerecommender.entities.Guest;
import my.app.skincarerecommender.entities.PreferenceEntity;
import my.app.skincarerecommender.entities.Product;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CoverageDaoDBTest {
    private final CoverageDao dao;
    private final CoverageRepository crepo;
    private final ProductRepository prepo;
    private final BrandRepository brepo;
    private final BaseRepository baserepo;
    private final CategoryRepository catrepo;
    private final PreferenceRepository prefrepo;
    private final GuestRepository grepo;
    
    @Autowired
    public CoverageDaoDBTest(CoverageDao dao, ProductRepository prepo, 
            CoverageRepository crepo, BrandRepository brepo, 
            BaseRepository baserepo, CategoryRepository catrepo,
            PreferenceRepository prefrepo, GuestRepository grepo) {
        this.dao = dao;
        this.prepo = prepo;
        this.crepo = crepo;
        this.brepo = brepo;
        this.baserepo = baserepo;
        this.catrepo = catrepo;
        this.prefrepo = prefrepo;
        this.grepo = grepo;
    }
    
    @BeforeEach
    public void setUp(){
        List<Guest> guests = grepo.findAll();
        for(Guest g : guests){
            grepo.deleteById(g.getGuestid());
        }
        
        List<PreferenceEntity> pe = prefrepo.findAll();
        for(PreferenceEntity e : pe){
            prefrepo.deleteById(e.getPreferenceid());
        }
        List<Product> p = prepo.findAll();
        
        for(Product prod : p){
            prepo.deleteById(prod.getItemnumber());
        }
    }

    /**
     * Test of findByProductId method, of class CoverageDaoDB.
     */
    @Test
    public void testFindByProductId() {
        List<Coverage> coverages = crepo.findAll();
        List<Coverage> productCoverages = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            productCoverages.add(coverages.get(i));
        }
        assertEquals(2, productCoverages.size());
        
        
        Brand b = brepo.findByBrandname("Smashbox");
        Product p = new Product();
        p.setItemnumber("1");
        p.setBrand(b);
        p.setPrice(new BigDecimal("1.00"));
        p.setProductname("P1");
        p.setUrl("n/a");
        p.setBase(baserepo.findByBasetype("Oil"));
        p.setCategory(catrepo.findByCategorytype("Primer"));
        p.setCoveragetypes(productCoverages);
        
        
        Product pDB = prepo.save(p);
        
        List<Coverage> pDBCoverages = dao.findByProductId(pDB.getItemnumber());
        
        assertEquals(2, pDBCoverages.size());
    }
    
}
