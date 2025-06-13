package com.example.customlistview;

public class Phone {
    private String namephone;
    private int imagephone;

    public int getImagephone() {
        return imagephone;
    }

    public void setImagephone(int imagephone) {
        this.imagephone = imagephone;
    }

    public String getNamephone() {
        return namephone;
    }

    public void setNamephone(String namephone) {
        this.namephone = namephone;
    }

    public Phone(int imagephone, String namephone) {
        this.imagephone = imagephone;
        this.namephone = namephone;
    }
}
