package com.example.onlinebookstore;

public class Book {

    String bid;
    String author;
    String bname;
    String price;



    public Book(String bid, String author, String name, String price) {
        this.bid = bid;
        this.author = author;
        this.bname = name;
        this.price = price;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return bname;
    }

    public void setName(String name) {
        this.bname = bname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }




}
