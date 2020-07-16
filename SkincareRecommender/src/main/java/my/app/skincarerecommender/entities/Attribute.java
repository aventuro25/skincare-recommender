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

@Entity
public class Attribute {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int attributeid;
    
    @Column
    private String attributename;
    
     @ManyToMany
    @JoinTable(name = "ingredientattribute",
            joinColumns = {
                @JoinColumn(name= "ingredientid")},
            inverseJoinColumns = {
                @JoinColumn(name = "attributeid")})
    private List<Ingredient> ingredients;

    public int getAttributeid() {
        return attributeid;
    }

    public void setAttributeid(int attributeid) {
        this.attributeid = attributeid;
    }

    public String getAttributename() {
        return attributename;
    }

    public void setAttributename(String attributename) {
        this.attributename = attributename;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.attributeid;
        hash = 13 * hash + Objects.hashCode(this.attributename);
        hash = 13 * hash + Objects.hashCode(this.ingredients);
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
        final Attribute other = (Attribute) obj;
        if (this.attributeid != other.attributeid) {
            return false;
        }
        if (!Objects.equals(this.attributename, other.attributename)) {
            return false;
        }
        if (!Objects.equals(this.ingredients, other.ingredients)) {
            return false;
        }
        return true;
    }
    
    
    
}
