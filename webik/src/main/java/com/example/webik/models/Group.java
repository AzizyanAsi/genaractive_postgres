package com.example.webik.models;

import java.util.ArrayList;

public class Group {
    private String id;
    private String name;
    private ArrayList<Group> groups;
    private ArrayList<Item> items;
    private Group groupParent;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append('{')
                .append(id)
                .append("_")
                .append(name)
                .append("_")
                .append(items)
                .append("}").toString();
    }

    public void printContent() {
        System.out.printf("Current group name: %s id: %s%n [", name, id);
        System.out.println("Items: ");
        for (Item item : items) {
            System.out.println(item);
        }
//        System.out.println("]");
        for (Group x : groups) {
            x.printContent();
        }
        System.out.println("]");
    }

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
        groups = new ArrayList<>();
        items = new ArrayList<>();
    }

    public Group getGroupParent() {
        return groupParent;
    }

    public void setGroupParent(Group groupParent) {
        this.groupParent = groupParent;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
