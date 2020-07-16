/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.util.List;
import my.app.skincarerecommender.entities.IngredientCompatibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientCompatRepository extends JpaRepository<IngredientCompatibility, Integer>{
    
    @Query(value = "SELECT * FROM IngredientCompatibility WHERE ingredientid = ?1 OR ingredientid2 = ?1", nativeQuery=true)
    List<IngredientCompatibility> findCompatibilityList(int id);
    
    @Query(value = "SELECT * FROM IngredientCompatibility WHERE ingredientid = ?1 AND ingredientid2 = ?2", nativeQuery=true)
    IngredientCompatibility findCompatibility(int i, int i2);
}
