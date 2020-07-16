/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import my.app.skincarerecommender.entities.Guest;
import my.app.skincarerecommender.entities.GuestMakeupSet;
import my.app.skincarerecommender.entities.GuestSkincareSet;
import my.app.skincarerecommender.entities.PreferenceEntity;
import my.app.skincarerecommender.models.GuestModel;
import my.app.skincarerecommender.models.MakeupPreference;
import my.app.skincarerecommender.models.Preference;
import my.app.skincarerecommender.repositories.UnitOfWork;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

@Service
public class GuestService {

    private final UnitOfWork unit;
    private final BCryptPasswordEncoder encoder;

    public GuestService(UnitOfWork unit, BCryptPasswordEncoder encoder) {
        this.unit = unit;
        this.encoder = encoder;
    }

    public List<FieldError> addNewGuest(GuestModel g) {
        List<FieldError> errors = new ArrayList<>();
        FieldError error;
        Guest guest = unit.findGuestById(g.getGuestid());

        if (guest != null) {
            error = new FieldError("guestmodel", "guestid", "Username must be unique");
            errors.add(error);
        }

        if (errors.isEmpty()) {
            g.setPassword(encoder.encode(g.getPassword()));
            unit.saveGuest(g);
        }
        
        return errors;
    }
    
    public void saveProducts(String[] itemnumbers, Principal p){
        unit.saveGuestProducts(itemnumbers, p.getName());
    }
    
    public Guest findByUsername(String name){
        return unit.findGuestByGuestId(name);
    }
    
    public Preference convertGuestPreferenceToModel(PreferenceEntity pe){
        return unit.convertGuestPreferenceToModel(pe);
    }
    public MakeupPreference convertGuestPreferenceToMakeupModel(PreferenceEntity pe){
        return unit.convertGuestPreferenceToMakeupModel(pe);
    }
    
    public void saveSimpleRecs(String[] savedRecs, Principal p){
        unit.saveSimpleRecs(savedRecs, p.getName());
    }
    
    public List<GuestSkincareSet> findSetsByUsername(Guest guest){
        return unit.findSetsByUsername(guest);
    }
    
    public void saveFullMakeupRecs(String[] savedRecs, Principal principal){
        unit.saveFullMakeupRecs(savedRecs,principal.getName());
    }
    
    public List<GuestMakeupSet> findMakeupSetsByGuest(Guest g){
       return unit.findMakeupSetsByGuest(g);
    }
}
