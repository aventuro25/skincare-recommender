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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class IngredientCompatibility {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int ingredientcompatibilityid;
    
    @ManyToOne
    @JoinColumn(name="ingredientid")
    private Ingredient ingredient1;
    
    @ManyToOne
    @JoinColumn(name="ingredientid2")
    private Ingredient ingredient2;
    
    @Column
    private int rating;

    public int getIngredientcompatibilityid() {
        return ingredientcompatibilityid;
    }

    public void setIngredientcompatibilityid(int ingredientcompatibilityid) {
        this.ingredientcompatibilityid = ingredientcompatibilityid;
    }

    public Ingredient getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(Ingredient ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public Ingredient getIngredient2() {
        return ingredient2;
    }

    public void setIngredient2(Ingredient ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.ingredientcompatibilityid;
        hash = 29 * hash + Objects.hashCode(this.ingredient1);
        hash = 29 * hash + Objects.hashCode(this.ingredient2);
        hash = 29 * hash + this.rating;
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
        final IngredientCompatibility other = (IngredientCompatibility) obj;
        if (this.ingredientcompatibilityid != other.ingredientcompatibilityid) {
            return false;
        }
        if (this.rating != other.rating) {
            return false;
        }
        if (!Objects.equals(this.ingredient1, other.ingredient1)) {
            return false;
        }
        if (!Objects.equals(this.ingredient2, other.ingredient2)) {
            return false;
        }
        return true;
    }
    
    
}
