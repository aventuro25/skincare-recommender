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

@Entity(name="preference")
public class PreferenceEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int preferenceid;

    @Column
    private boolean crueltyfree;
    
    @ManyToOne
    @JoinColumn(name = "skintypeid") 
    private SkinType skintype;
    
    
    @ManyToOne
    @JoinColumn(name = "itemnumber")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "coverageid")
    private Coverage coverage;

    @ManyToOne
    @JoinColumn(name = "finishid")
    private Finish finish;

    @ManyToMany
    @JoinTable(name = "preferenceattribute",
            joinColumns = {
                @JoinColumn(name = "preferenceid")},
            inverseJoinColumns = {
                @JoinColumn(name = "attributeid")})
    private List<Attribute> attributes;
    
    @ManyToMany
    @JoinTable(name = "preferenceconcern",
            joinColumns = {
                @JoinColumn(name = "preferenceid")},
            inverseJoinColumns = {
                @JoinColumn(name = "concernid")})
    private List<Concern> concerns;
    

    public int getPreferenceid() {
        return preferenceid;
    }

    public void setPreferenceid(int preferenceid) {
        this.preferenceid = preferenceid;
    }

    public boolean isCrueltyfree() {
        return crueltyfree;
    }

    public void setCrueltyfree(boolean crueltyfree) {
        this.crueltyfree = crueltyfree;
    }

    public Coverage getCoverage() {
        return coverage;
    }

    public void setCoverage(Coverage coverage) {
        this.coverage = coverage;
    }

    public Finish getFinish() {
        return finish;
    }

    public void setFinish(Finish finish) {
        this.finish = finish;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SkinType getSkintype() {
        return skintype;
    }

    public void setSkintype(SkinType skintype) {
        this.skintype = skintype;
    }

    public List<Concern> getConcerns() {
        return concerns;
    }

    public void setConcerns(List<Concern> concerns) {
        this.concerns = concerns;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.preferenceid;
        hash = 53 * hash + (this.crueltyfree ? 1 : 0);
        hash = 53 * hash + Objects.hashCode(this.skintype);
        hash = 53 * hash + Objects.hashCode(this.product);
        hash = 53 * hash + Objects.hashCode(this.coverage);
        hash = 53 * hash + Objects.hashCode(this.finish);
        hash = 53 * hash + Objects.hashCode(this.attributes);
        hash = 53 * hash + Objects.hashCode(this.concerns);
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
        final PreferenceEntity other = (PreferenceEntity) obj;
        if (this.preferenceid != other.preferenceid) {
            return false;
        }
        if (this.crueltyfree != other.crueltyfree) {
            return false;
        }
        if (!Objects.equals(this.skintype, other.skintype)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.coverage, other.coverage)) {
            return false;
        }
        if (!Objects.equals(this.finish, other.finish)) {
            return false;
        }
        if (!Objects.equals(this.attributes, other.attributes)) {
            return false;
        }
        if (!Objects.equals(this.concerns, other.concerns)) {
            return false;
        }
        return true;
    }

    
    
    
}
