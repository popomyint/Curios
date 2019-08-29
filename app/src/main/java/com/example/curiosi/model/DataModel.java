package com.example.curiosi.model;

public class DataModel {
    public int did;
    public String title;
    public String desc;
    public String image;
    public String imgPath = "gs://curio-74e86.appspot.com/";

    public DataModel(){

    }

    public DataModel(int did, String title, String desc){
        this.did = did;
        this.title = title;
        this.desc = desc;
    }
}
