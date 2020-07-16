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
public class Concern {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int concernid;
    
    @Column
    private String concerntype;

    public int getConcernid() {
        return concernid;
    }

    public void setConcernid(int concernid) {
        this.concernid = concernid;
    }

    public String getConcerntype() {
        return concerntype;
    }

    public void setConcerntype(String concerntype) {
        this.concerntype = concerntype;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.concernid;
        hash = 79 * hash + Objects.hashCode(this.concerntype);
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
        final Concern other = (Concern) obj;
        if (this.concernid != other.concernid) {
            return false;
        }
        if (!Objects.equals(this.concerntype, other.concerntype)) {
            return false;
        }
        return true;
    }
    
    
    
}
