/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Product implements Serializable {

    @Id
    private String itemnumber;

    @Column(nullable = false)
    private String productname;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String details;
    
    @Column
    private String url;
    
    @Column
    private String imageurl;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "finishid")
    private Finish finish;
    
    @ManyToMany
    @JoinTable(name = "productcoverage",
            joinColumns = {
                @JoinColumn(name = "itemnumber")},
            inverseJoinColumns = {
                @JoinColumn(name = "coverageid")})
    private List<Coverage> coveragetypes;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "brandid")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name="baseid")
    private Base base;
    
    
    @ManyToMany
    @JoinTable(name = "productingredient",
            joinColumns = {
                @JoinColumn(name = "itemnumber")},
            inverseJoinColumns = {
                @JoinColumn(name = "ingredientid")})
    private List<Ingredient> ingredients;
    
    @ManyToMany
    @JoinTable(name = "productconcern",
            joinColumns = {
                @JoinColumn(name= "itemnumber")},
            inverseJoinColumns = {
                @JoinColumn(name = "concernid")})
    private List<Concern> concerns;
    
    @ManyToMany
    @JoinTable(name = "productskintype",
            joinColumns = {
                @JoinColumn(name = "itemnumber")},
            inverseJoinColumns = {
                @JoinColumn(name = "skintypeid")})
    private List<SkinType> skintypes;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getItemnumber() {
        return itemnumber;
    }

    public void setItemnumber(String itemnumber) {
        this.itemnumber = itemnumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }        

    public List<Concern> getConcerns() {
        return concerns;
    }

    public void setConcerns(List<Concern> concerns) {
        this.concerns = concerns;
    }

    public List<SkinType> getSkintypes() {
        return skintypes;
    }

    public void setSkintypes(List<SkinType> skintypes) {
        this.skintypes = skintypes;
    }

    public Finish getFinish() {
        return finish;
    }

    public void setFinish(Finish finish) {
        this.finish = finish;
    }

    public List<Coverage> getCoveragetypes() {
        return coveragetypes;
    }

    public void setCoveragetypes(List<Coverage> coveragetypes) {
        this.coveragetypes = coveragetypes;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.itemnumber);
        hash = 11 * hash + Objects.hashCode(this.productname);
        hash = 11 * hash + Objects.hashCode(this.price);
        hash = 11 * hash + Objects.hashCode(this.details);
        hash = 11 * hash + Objects.hashCode(this.url);
        hash = 11 * hash + Objects.hashCode(this.imageurl);
        hash = 11 * hash + Objects.hashCode(this.finish);
        hash = 11 * hash + Objects.hashCode(this.coveragetypes);
        hash = 11 * hash + Objects.hashCode(this.brand);
        hash = 11 * hash + Objects.hashCode(this.category);
        hash = 11 * hash + Objects.hashCode(this.base);
        hash = 11 * hash + Objects.hashCode(this.ingredients);
        hash = 11 * hash + Objects.hashCode(this.concerns);
        hash = 11 * hash + Objects.hashCode(this.skintypes);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.itemnumber, other.itemnumber)) {
            return false;
        }
        if (!Objects.equals(this.productname, other.productname)) {
            return false;
        }
        if (!Objects.equals(this.details, other.details)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.imageurl, other.imageurl)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.finish, other.finish)) {
            return false;
        }
        if (!Objects.equals(this.coveragetypes, other.coveragetypes)) {
            return false;
        }
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.base, other.base)) {
            return false;
        }
        if (!Objects.equals(this.ingredients, other.ingredients)) {
            return false;
        }
        if (!Objects.equals(this.concerns, other.concerns)) {
            return false;
        }
        if (!Objects.equals(this.skintypes, other.skintypes)) {
            return false;
        }
        return true;
    }

    
    
}

