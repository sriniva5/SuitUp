package com.example.akila.myapplication.model;

/**
 * Model representation of the application user
 */

public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String add1;
    private String city;
    private String state;
    private String zipcode;
    private String password;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getFirst_name(){
        return first_name;
    }

    public void setFirst_name(String first_name){
        this.first_name=first_name;
    }

    public String getLast_name(){
        return last_name;
    }

    public void setLast_name(String last_name){
        this.last_name=last_name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getAdd1(){
        return add1;
    }

    public void setAdd1(String add1){
        this.add1=add1;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city=city;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state=state;
    }

    public String getZipcode(){
        return zipcode;
    }

    public void setZipcode(String zipcode){
        this.zipcode=zipcode;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }
}
