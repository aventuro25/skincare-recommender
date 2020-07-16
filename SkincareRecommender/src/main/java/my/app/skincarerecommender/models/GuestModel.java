/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import my.app.skincarerecommender.entities.Coverage;
import my.app.skincarerecommender.entities.Finish;
import my.app.skincarerecommender.entities.SkinType;


public class GuestModel {
    @NotBlank(message = "Username cannot be blank.")
    @Size(max = 50, message = "Username cannot be longer than 50 characters.")
    private String guestid;
    @NotBlank(message = "Your name cannot be blank.")
    @Size(max = 100, message = "Name cannot be longer than 100 characters.")
    private String guestname;
    @NotBlank(message = "Password cannot be blank.")
    @Size(max = 200, message = "Password cannot be longer than 200 characters.")
    private String password;
    
    private SkinType skintype;
    private int[] c;
    private int[]a;
    private boolean crueltyfree;
    private Coverage coverage;
    private Finish finish;

    
    public String getGuestid() {
        return guestid;
    }

    public void setGuestid(String guestid) {
        this.guestid = guestid;
    }
    
    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SkinType getSkintype() {
        return skintype;
    }

    public void setSkintype(SkinType skintype) {
        this.skintype = skintype;
    }

    public int[] getC() {
        return c;
    }

    public void setC(int[] c) {
        this.c = c;
    }

    public int[] getA() {
        return a;
    }

    public void setA(int[] a) {
        this.a = a;
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
    
    
}
