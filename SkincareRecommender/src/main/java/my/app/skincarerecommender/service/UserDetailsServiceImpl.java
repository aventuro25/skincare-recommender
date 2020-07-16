/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.service;

import java.util.ArrayList;
import java.util.List;
import my.app.skincarerecommender.entities.Guest;
import my.app.skincarerecommender.repositories.UnitOfWork;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    private final UnitOfWork unit;
    
    public UserDetailsServiceImpl(UnitOfWork unit){
        
        this.unit = unit;
    }

    @Override
    public UserDetails loadUserByUsername(String guestid) throws UsernameNotFoundException {
        Guest g = unit.findGuestById(guestid);
        if(g == null){
            throw new UsernameNotFoundException(guestid);
        }
        
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        if(g.isAdministrator()){
            roles.add(new SimpleGrantedAuthority("ADMIN"));
            roles.add(new SimpleGrantedAuthority("USER"));
        } else {
            roles.add(new SimpleGrantedAuthority("USER"));
//            roles.add(new SimpleGrantedAuthority("GUEST"));
        }
         return new org.springframework.security.core.userdetails.User(g.getGuestid(), g.getPassword(), roles);
    }
}
