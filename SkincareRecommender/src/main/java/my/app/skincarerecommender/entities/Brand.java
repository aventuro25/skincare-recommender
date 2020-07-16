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
public class Brand {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int brandid;
    
    @Column
    private String brandname;
    
    @Column
    boolean crueltyfree;

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public boolean isCrueltyfree() {
        return crueltyfree;
    }

    public void setCrueltyfree(boolean crueltyfree) {
        this.crueltyfree = crueltyfree;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.brandid;
        hash = 67 * hash + Objects.hashCode(this.brandname);
        hash = 67 * hash + (this.crueltyfree ? 1 : 0);
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
        final Brand other = (Brand) obj;
        if (this.brandid != other.brandid) {
            return false;
        }
        if (this.crueltyfree != other.crueltyfree) {
            return false;
        }
        if (!Objects.equals(this.brandname, other.brandname)) {
            return false;
        }
        return true;
    }
    
    
}
