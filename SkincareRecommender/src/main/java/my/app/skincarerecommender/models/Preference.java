/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.models;

import my.app.skincarerecommender.entities.SkinType;

public class Preference {

    private SkinType skintype;
    private boolean crueltyfree;
    private int[] a;
    private int[] c;

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

    public int[] getC() {
        return c;
    }

    public void setC(int[] c) {
        this.c = c;
    }

    public boolean hasConcern(int r) {
        if (c != null) {
            for (int i =0; i< c.length; i++) {
                if (c[i] == r) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasAttribute(int r) {
        if (a != null) {
            for (int i =0; i< a.length; i++) {
                if (a[i] == r) {
                    return true;
                }
            }
        }
        return false;
    }

}
