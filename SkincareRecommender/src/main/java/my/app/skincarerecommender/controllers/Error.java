/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.controllers;

import java.time.LocalDateTime;


public class Error {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    
    public LocalDateTime getTimestamp(){
        return timestamp;
    }
    public String getMesage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
}
