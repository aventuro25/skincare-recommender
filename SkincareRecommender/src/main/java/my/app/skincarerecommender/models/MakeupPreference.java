/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.models;

import java.util.Arrays;
import java.util.Objects;
import my.app.skincarerecommender.entities.Category;
import my.app.skincarerecommender.entities.Coverage;
import my.app.skincarerecommender.entities.Finish;
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.entities.SkinType;


public class MakeupPreference {
    private SkinType skintype;
    private boolean crueltyfree;
    private int[] a;
    private Finish finish;
    private Coverage coverage;
    private Product product;
    private Category category;

    public SkinType getSkintype() {
        return skintype;
    }

    public void setSkintype(SkinType skintype) {
        this.skintype = skintype;
    }

    public boolean isCrueltyfree() {
        return crueltyfree;
    }

    public void setCrueltyfree(boolean crueltyfree) {
        this.crueltyfree = crueltyfree;
    }

    public int[] getA() {
        return a;
    }

    public void setA(int[] a) {
        this.a = a;
    }

    public Finish getFinish() {
        return finish;
    }

    public void setFinish(Finish finish) {
        this.finish = finish;
    }

    public Coverage getCoverage() {
        return coverage;
    }

    public void setCoverage(Coverage coverage) {
        this.coverage = coverage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public boolean hasAttribute(int r){
        if (a != null) {
            for (int i =0; i< a.length; i++) {
                if (a[i] == r) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.skintype);
        hash = 43 * hash + (this.crueltyfree ? 1 : 0);
        hash = 43 * hash + Arrays.hashCode(this.a);
        hash = 43 * hash + Objects.hashCode(this.finish);
        hash = 43 * hash + Objects.hashCode(this.coverage);
        hash = 43 * hash + Objects.hashCode(this.product);
        hash = 43 * hash + Objects.hashCode(this.category);
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
        final MakeupPreference other = (MakeupPreference) obj;
        if (this.crueltyfree != other.crueltyfree) {
            return false;
        }
        if (!Objects.equals(this.skintype, other.skintype)) {
            return false;
        }
        if (!Arrays.equals(this.a, other.a)) {
            return false;
        }
        if (!Objects.equals(this.finish, other.finish)) {
            return false;
        }
        if (!Objects.equals(this.coverage, other.coverage)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }
    
    
    
}
