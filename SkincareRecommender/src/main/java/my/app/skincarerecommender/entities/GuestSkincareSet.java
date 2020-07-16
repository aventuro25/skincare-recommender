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
public class GuestSkincareSet {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int guestskincaresetid;
    
    @ManyToOne
    @JoinColumn(name = "guestid")
    private Guest guest;
    
    @ManyToOne
    @JoinColumn(name = "itemnumber1")
    private Product product1;
    
    @ManyToOne
    @JoinColumn(name = "itemnumber2")
    private Product product2;
    
    @ManyToOne
    @JoinColumn(name = "itemnumber3")
    private Product product3;

    public int getGuestskincaresetid() {
        return guestskincaresetid;
    }

    public void setGuestskincaresetid(int guestskincaresetid) {
        this.guestskincaresetid = guestskincaresetid;
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

    public Product getProduct3() {
        return product3;
    }

    public void setProduct3(Product product3) {
        this.product3 = product3;
    }
    
    
}
