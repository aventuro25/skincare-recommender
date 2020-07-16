/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import my.app.skincarerecommender.entities.Category;
import my.app.skincarerecommender.entities.Finish;


public class ProductModel {
    @NotBlank(message = "I need a url to add the product to the database!")
    @Size(max = 500, message ="Url cannot be longer than 500 characters.")
    private String url;
    
    private Category category;
    
    private Finish finish;
    
    private int[] skintypes;
    
    private int[] coverages;
    
    private int[] concerns;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Finish getFinish() {
        return finish;
    }

    public void setFinish(Finish finish) {
        this.finish = finish;
    }

    public int[] getSkintypes() {
        return skintypes;
    }

    public void setSkintypes(int[] skintypes) {
        this.skintypes = skintypes;
    }

    public int[] getCoverages() {
        return coverages;
    }

    public void setCoverages(int[] coverages) {
        this.coverages = coverages;
    }

    public int[] getConcerns() {
        return concerns;
    }

    public void setConcerns(int[] concerns) {
        this.concerns = concerns;
    }
    
    
    
    
    
}
