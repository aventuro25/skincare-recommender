/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.entities;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Ingredient {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int ingredientid;
    
    @Column
    private String ingredientname;
    
   

    public int getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(int ingredientid) {
        this.ingredientid = ingredientid;
    }

    public String getIngredientname() {
        return ingredientname;
    }

    public void setIngredientname(String ingredientname) {
        this.ingredientname = ingredientname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.ingredientid;
        hash = 89 * hash + Objects.hashCode(this.ingredientname);
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
        final Ingredient other = (Ingredient) obj;
        if (this.ingredientid != other.ingredientid) {
            return false;
        }
        if (!Objects.equals(this.ingredientname, other.ingredientname)) {
            return false;
        }
        return true;
    }

}
