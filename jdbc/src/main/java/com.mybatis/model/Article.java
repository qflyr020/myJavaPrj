package com.mybatis.model;

public class Article {
    private int id;
    //注意一下，文章的用户是怎么定义的，是直接定义的一个User对象。而不是int类型。
    private User user; //关联user
    private String title; //标题
    private String content;//内容
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
