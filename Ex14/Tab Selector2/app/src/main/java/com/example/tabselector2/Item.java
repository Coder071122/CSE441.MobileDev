package com.example.tabselector2;

public class Item {
    private String id, title;
    private Integer like;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Item(String id, Integer like, String title) {
        this.id = id;
        this.like = like;
        this.title = title;
    }
}
