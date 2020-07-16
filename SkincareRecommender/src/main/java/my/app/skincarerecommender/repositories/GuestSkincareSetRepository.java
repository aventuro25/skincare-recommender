/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.util.List;
import my.app.skincarerecommender.entities.Guest;
import my.app.skincarerecommender.entities.GuestSkincareSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestSkincareSetRepository extends JpaRepository<GuestSkincareSet, Integer>{
    List<GuestSkincareSet> findByGuest(Guest guest);
}
