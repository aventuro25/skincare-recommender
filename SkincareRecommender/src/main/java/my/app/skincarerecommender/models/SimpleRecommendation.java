/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.models;

import my.app.skincarerecommender.entities.Product;


public class SimpleRecommendation {
    private Product cleanser;
    private Product moisturizer;
    private Product eyecream;
    private int compatibility;

    public Product getCleanser() {
        return cleanser;
    }

    public void setCleanser(Product cleanser) {
        this.cleanser = cleanser;
    }

    public Product getMoisturizer() {
        return moisturizer;
    }

    public void setMoisturizer(Product moisturizer) {
        this.moisturizer = moisturizer;
    }

    public Product getEyecream() {
        return eyecream;
    }

    public void setEyecream(Product eyecream) {
        this.eyecream = eyecream;
    }

    public int getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(int compatibility) {
        this.compatibility = compatibility;
    }

    
    
    
}
