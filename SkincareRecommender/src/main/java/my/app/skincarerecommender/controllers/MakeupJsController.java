/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.controllers;

import java.util.List;
import my.app.skincarerecommender.entities.PreferenceEntity;
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.models.MakeupPreference;
import my.app.skincarerecommender.service.MakeupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MakeupJsController {
    private final MakeupService makeupService;
    
    @Autowired
    public MakeupJsController(MakeupService makeupService){
        this.makeupService = makeupService;
    }
    
    
    @GetMapping("/chooseBrand/{id}")
    public ResponseEntity<List<Product>> findAllProductsByBrandId(@PathVariable int id){
        
        List<Product> products = makeupService.findProductsByBrandidAndCategory(id, "Foundation");
        
        if(products.isEmpty()){
             return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
         return new ResponseEntity(products, HttpStatus.OK);
    }
       
    @PostMapping("/calculate")
    public int calculateMatches(@RequestBody MakeupPreference mp){       
          PreferenceEntity pref = makeupService.savePreference(mp);
        return pref.getPreferenceid();
    }
  
}
