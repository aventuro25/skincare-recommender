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

@Entity
public class Finish {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int finishid;
    
    @Column
    private String finishtype;

    public int getFinishid() {
        return finishid;
    }

    public void setFinishid(int finishid) {
        this.finishid = finishid;
    }

    public String getFinishtype() {
        return finishtype;
    }

    public void setFinishtype(String finishtype) {
        this.finishtype = finishtype;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.finishid;
        hash = 79 * hash + Objects.hashCode(this.finishtype);
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
        final Finish other = (Finish) obj;
        if (this.finishid != other.finishid) {
            return false;
        }
        if (!Objects.equals(this.finishtype, other.finishtype)) {
            return false;
        }
        return true;
    }
    
    
}
