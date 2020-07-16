/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.service;

import java.util.List;
import my.app.skincarerecommender.entities.Guest;
import my.app.skincarerecommender.models.ProductModel;
import my.app.skincarerecommender.repositories.UnitOfWork;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final UnitOfWork unit;
    
    public AdminService(UnitOfWork unit){
        this.unit = unit;
    }
    
    public List<Guest> findAllGuests(){
        return unit.findAllGuests();
    }
    
    public Guest findGuestById(String id){
        return unit.findGuestById(id);
    }
    
    public void updateAdminById(String id, boolean admin){
        unit.setNewAdminById(id, admin);
    }
    
    public void saveProduct(ProductModel pm){
        
        unit.saveProduct(pm);
    }
}
