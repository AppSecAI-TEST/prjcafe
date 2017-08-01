package com.spring.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ModelTeam {
    
    private String email = "";
    private String pw = "";
    private String phone = "";
    private String address = "";
    private String sex = "";
    private String emailselect = "";
    
    
    public ModelTeam() {
        super();
    }
    public ModelTeam(String email, String pw) {
        super();
        this.email = email;
        this.pw = pw;
    }
    public ModelTeam(String email, String pw, String phone, String address,
            String sex, String emailselect) {
        super();
        this.email = email;
        this.pw = pw;
        this.phone = phone;
        this.address = address;
        this.sex = sex;
        this.emailselect = emailselect;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getEmailselect() {
        return emailselect;
    }
    public void setEmailselect(String emailselect) {
        this.emailselect = emailselect;
    }
    
    
    
    
    
  
 
    
  
   
}
