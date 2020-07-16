/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.util.List;
import my.app.skincarerecommender.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
    Ingredient findByIngredientname(String name);
    
   @Query(value = "SELECT * FROM Ingredient i INNER JOIN IngredientBase ib ON i.ingredientid = ib.ingredientid "
            + "INNER JOIN Base b ON ib.baseid = b.baseid WHERE b.basetype = ?1", nativeQuery = true)
    List<Ingredient> findByBasetype(String type);
}
