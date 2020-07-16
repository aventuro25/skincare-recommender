/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.service;

import java.util.List;
import my.app.skincarerecommender.entities.Attribute;
import my.app.skincarerecommender.entities.Category;
import my.app.skincarerecommender.entities.Concern;
import my.app.skincarerecommender.entities.SkinType;
import my.app.skincarerecommender.repositories.UnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkincareService {
    
    private final UnitOfWork unit;
    
    @Autowired
    public SkincareService(UnitOfWork unit){
        this.unit = unit;
    }
    
    public List<SkinType> getAllSkinTypes(){
        return unit.findAllSkinTypes();
    }
    
    public List<Concern> getAllConcerns(){
        return unit.findAllConcerns();
    }
    
    public List<Attribute> getAllAttributes(){
        return unit.findAllAttributes();
    }
    
    public List<Category> getAllCategories(){
        return unit.findAllCategories();
    }
}
