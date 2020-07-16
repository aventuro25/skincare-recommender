/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import my.app.skincarerecommender.models.GuestModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.FieldError;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GuestServiceTest {
    private final GuestService service;
    
    @Autowired
    public GuestServiceTest(GuestService service) {
        this.service = service;
    }  

    /**
     * Test of addNewGuest method, of class GuestService.
     */
    @Test
    public void testAddNewGuest() {
        GuestModel g1 = new GuestModel();
        g1.setGuestid("linaa");
        g1.setGuestname("lina");
        g1.setPassword("test");
        
        List<FieldError> errors = service.addNewGuest(g1);
        
        assertEquals(0, errors.size());
        
        GuestModel g2 = new GuestModel();
        g2.setGuestid("linaa");
        g2.setGuestname("test");
        g2.setPassword("test2");
        
        List<FieldError> err = service.addNewGuest(g2);
        
        assertEquals(1, err.size());
    }
    
}
