package com.allion.issuetracker.model;

import lombok.Data;

@Data
public class User{
    public int userId;
    public int id;
    public String title;
    public boolean completed;
}
