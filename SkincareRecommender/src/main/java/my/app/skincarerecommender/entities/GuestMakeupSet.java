/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GuestMakeupSet {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int guestmakeupsetid;
    
    @ManyToOne
    @JoinColumn(name = "guestid")
    private Guest guest;
    
    @ManyToOne
    @JoinColumn(name = "itemnumber1")
    private Product product1;
    
    @ManyToOne
    @JoinColumn(name = "itemnumber2")
    private Product product2;

    public int getGuestmakeupsetid() {
        return guestmakeupsetid;
    }

    public void setGuestmakeupsetid(int guestmakeupsetid) {
        this.guestmakeupsetid = guestmakeupsetid;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Product getProduct1() {
        return product1;
    }

    public void setProduct1(Product product1) {
        this.product1 = product1;
    }

    public Product getProduct2() {
        return product2;
    }

    public void setProduct2(Product product2) {
        this.product2 = product2;
    }
    
    
}
