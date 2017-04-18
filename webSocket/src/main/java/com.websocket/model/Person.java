package com.websocket.model;

import sun.plugin2.message.GetNameSpaceMessage;

/**
 * Created by Administrator on 2017/04/18.
 */
public class Person {
    private  String name;
    private String surname;

    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name=name;
    }

    public String getSurName()
    {
        return this.surname;
    }
    public void setSurName(String surname)
    {
        this.surname=surname;
    }

}
