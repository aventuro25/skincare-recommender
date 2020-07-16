/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.models;

import my.app.skincarerecommender.entities.Product;


public class MakeupRecommendation {
    private Product primer;
    private Product foundation;
    private int compatibility;

    public Product getPrimer() {
        return primer;
    }

    public void setPrimer(Product primer) {
        this.primer = primer;
    }

    public Product getFoundation() {
        return foundation;
    }

    public void setFoundation(Product foundation) {
        this.foundation = foundation;
    }

    public int getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(int compatibility) {
        this.compatibility = compatibility;
    }
    
    
}
