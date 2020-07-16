/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.service;


import my.app.skincarerecommender.entities.Ingredient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecommendationServiceTest {
    private final RecommendationService service;
    
    @Autowired
    public RecommendationServiceTest(RecommendationService service) {
        this.service = service;
    }
    


    /**
     * Test of calculateGuestSimpleRecommendations method, of class RecommendationService.
     */
    @Test
    public void testCalculateGuestSimpleRecommendations() {
        Ingredient i = new Ingredient();
        
    }
    
}
