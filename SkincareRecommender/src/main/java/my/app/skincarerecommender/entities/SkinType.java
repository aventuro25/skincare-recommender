/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SkinType {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int skintypeid;
    
    @Column
    private String skintypename;

    public int getSkintypeid() {
        return skintypeid;
    }

    public void setSkintypeid(int skintypeid) {
        this.skintypeid = skintypeid;
    }

    public String getSkintypename() {
        return skintypename;
    }

    public void setSkintypename(String skintypename) {
        this.skintypename = skintypename;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.skintypeid;
        hash = 37 * hash + Objects.hashCode(this.skintypename);
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
        final SkinType other = (SkinType) obj;
        if (this.skintypeid != other.skintypeid) {
            return false;
        }
        if (!Objects.equals(this.skintypename, other.skintypename)) {
            return false;
        }
        return true;
    }
    
    
}
