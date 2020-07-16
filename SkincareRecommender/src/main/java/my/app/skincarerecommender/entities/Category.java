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
public class Category {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int categoryid;
    
    @Column
    private String categorytype;

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategorytype() {
        return categorytype;
    }

    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.categoryid;
        hash = 67 * hash + Objects.hashCode(this.categorytype);
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
        final Category other = (Category) obj;
        if (this.categoryid != other.categoryid) {
            return false;
        }
        if (!Objects.equals(this.categorytype, other.categorytype)) {
            return false;
        }
        return true;
    }
    
    
}
