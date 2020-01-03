package com.sarath;

import java.util.Date;

public class Task {
    public Task() {
        this.setStatus(Status.Initial);
    }

    public String name;
    public String description;
    public int id;
    public Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    Status status;

    @Override
    public String toString() {
        return ("Id:"+id+"Name:"+name+"description:"+description+"Date:"+date+"Status:"+status);
    }
}
