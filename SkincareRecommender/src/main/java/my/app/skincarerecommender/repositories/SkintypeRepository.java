/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.util.List;
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.entities.SkinType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SkintypeRepository extends JpaRepository<SkinType, Integer>{
    SkinType findBySkintypename(String name);
    
    
//    @Query(value="SELECT * FROM Skintype s INNER JOIN ProductSkintype ps ON s.skintypeid = ps.skintypeid WHERE ps.itemnumber = ?1;", nativeQuery=true)
//    List<SkinType> findByProductId(String i);
}
