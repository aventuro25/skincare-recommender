/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.entities;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Guest {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String guestid;

    @Column
    private String guestname;

    @Column
    private String password;

    @Column
    private boolean administrator;
    
    @ManyToOne
    @JoinColumn(name = "preferenceid")
    private PreferenceEntity preference;

//    @ManyToOne
//    @JoinColumn(name = "skintypeid")
//    private SkinType skintype;
//
//    @ManyToMany
//    @JoinTable(name = "guestconcern",
//            joinColumns = {
//                @JoinColumn(name = "guestid")},
//            inverseJoinColumns = {
//                @JoinColumn(name = "concernid")})
//    private List<Concern> concerns;

    @ManyToMany
    @JoinTable(name = "guestproduct",
            joinColumns = {
                @JoinColumn(name = "guestid")},
            inverseJoinColumns = {
                @JoinColumn(name = "itemnumber")})
    private List<Product> products;

//    @ManyToMany
//    @JoinTable(name = "guestpreference",
//            joinColumns = {
//                @JoinColumn(name = "guestid")},
//            inverseJoinColumns = {
//                @JoinColumn(name = "preferenceid")})
//    private List<PreferenceEntity> preferences;

//    public int getGuestid() {
//        return guestid;
//    }
//
//    public void setGuestid(int guestid) {
//        this.guestid = guestid;
//    }

    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

//   
//    public List<Concern> getConcerns() {
//        return concerns;
//    }
//
//    public void setConcerns(List<Concern> concerns) {
//        this.concerns = concerns;
//    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

//    public SkinType getSkintype() {
//        return skintype;
//    }
//
//    public void setSkintype(SkinType skintype) {
//        this.skintype = skintype;
//    }
//
//    public List<PreferenceEntity> getPreferences() {
//        return preferences;
//    }
//
//    public void setPreferences(List<PreferenceEntity> preferences) {
//        this.preferences = preferences;
//    }

    public String getGuestid() {
        return guestid;
    }

    public void setGuestid(String guestid) {
        this.guestid = guestid;
    }

    public PreferenceEntity getPreference() {
        return preference;
    }

    public void setPreference(PreferenceEntity preference) {
        this.preference = preference;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.guestid);
        hash = 13 * hash + Objects.hashCode(this.guestname);
        hash = 13 * hash + Objects.hashCode(this.password);
        hash = 13 * hash + (this.administrator ? 1 : 0);
        hash = 13 * hash + Objects.hashCode(this.preference);
        hash = 13 * hash + Objects.hashCode(this.products);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Guest other = (Guest) obj;
        if (this.administrator != other.administrator) {
            return false;
        }
        if (!Objects.equals(this.guestid, other.guestid)) {
            return false;
        }
        if (!Objects.equals(this.guestname, other.guestname)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.preference, other.preference)) {
            return false;
        }
        if (!Objects.equals(this.products, other.products)) {
            return false;
        }
        return true;
    }
    
    

}
