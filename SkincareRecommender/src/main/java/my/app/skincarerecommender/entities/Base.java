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
import javax.persistence.OneToMany;

@Entity
public class Base {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int baseid;
    
    @Column
    private String basetype;
    
    @OneToMany
    @JoinColumn(name = "ingredientid")
    private List<Ingredient> ingredients;

    public int getBaseid() {
        return baseid;
    }

    public void setBaseid(int baseid) {
        this.baseid = baseid;
    }

    public String getBasetype() {
        return basetype;
    }

    public void setBasetype(String basetype) {
        this.basetype = basetype;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.baseid;
        hash = 89 * hash + Objects.hashCode(this.basetype);
        hash = 89 * hash + Objects.hashCode(this.ingredients);
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
        final Base other = (Base) obj;
        if (this.baseid != other.baseid) {
            return false;
        }
        if (!Objects.equals(this.basetype, other.basetype)) {
            return false;
        }
        if (!Objects.equals(this.ingredients, other.ingredients)) {
            return false;
        }
        return true;
    }
    
    
}
