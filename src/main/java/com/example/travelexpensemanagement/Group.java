package com.example.travelexpensemanagement;

public class Group {
    private String Name,Password,GroupName,Type;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Group(){}

    public Group(String name, String password, String groupName) {
        Name = name;
        Password = password;
        GroupName = groupName;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
