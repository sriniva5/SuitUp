package com.example.akila.myapplication.model;

/**
 * Model representation each clothing donation's attributes
 */

public class Clothing {
    private int id;
    private String item;
    private String description;
    private int size;

    public Clothing(){}

    public Clothing(int id, String item, String description, int size){
        this.id = id;
        this.item = item;
        this.description = description;
        this.size = size;
    }

    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getItem(){return item;}
    public void setItem(String item){this.item=item;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}
    public int getSize(){return size;}
    public void setSize(int size){this.size=size;}
}
