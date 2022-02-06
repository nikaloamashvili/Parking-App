package com.example.mylogin;

public class User {
    String ID;
    String email;
    String password;
    String phone;
    Integer add_park;
    Integer likes;
    //String my_info;

    public User(String id, String email, String phone,Integer likes,Integer add_park) {
        this.ID = id;
        this.email = email;
        this.phone = phone;
        this.likes=likes;
        this.add_park=add_park;

    }
//    public User(String id, String email, String phone) {
//        this.ID = id;
//        this.email = email;
//        this.phone = phone;
//
//    }


    public User() {
        this.add_park=0;
        this.likes=0;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
    public String getID() {
        return ID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAdd_park() {
        return add_park;
    }

    public void setAdd_park(Integer add_park) {
        this.add_park = add_park;
    }

}
