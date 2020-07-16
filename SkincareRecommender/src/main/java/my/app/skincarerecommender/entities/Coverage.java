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
public class Coverage {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int coverageid;
    
    @Column
    private String coveragetype;

    public int getCoverageid() {
        return coverageid;
    }

    public void setCoverageid(int coverageid) {
        this.coverageid = coverageid;
    }

    public String getCoveragetype() {
        return coveragetype;
    }

    public void setCoveragetype(String coveragetype) {
        this.coveragetype = coveragetype;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.coverageid;
        hash = 47 * hash + Objects.hashCode(this.coveragetype);
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
        final Coverage other = (Coverage) obj;
        if (this.coverageid != other.coverageid) {
            return false;
        }
        if (!Objects.equals(this.coveragetype, other.coveragetype)) {
            return false;
        }
        return true;
    }
    
    
}
