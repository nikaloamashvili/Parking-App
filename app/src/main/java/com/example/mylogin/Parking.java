package com.example.mylogin;

public class Parking {
    Double longitude, latitude;
    int isTaken;
    String name;
    String owner;
    int likes;

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Parking(int isTaken,Double latitude, Double longitude,String name,String owner) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.isTaken = isTaken;
        this.name=name;
        this.owner=owner;

    }

    public Parking() {
        this.likes=0;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public int getIsTaken() {
        return isTaken;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setIsTaken(int isTaken) {
        this.isTaken = isTaken;
    }

    public String getName() {
        return name;
    }
}
