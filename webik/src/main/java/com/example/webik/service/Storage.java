package com.example.webik.service;

import com.example.webik.models.Basket;
import com.example.webik.models.Group;
import com.example.webik.models.Item;
import com.sun.org.apache.bcel.internal.generic.IREM;

import java.util.*;

public class Storage {
    private static List<Group> roots = new ArrayList<>();
    public static Basket basket = new Basket();
    public static List<Group> groups = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();

    public static void addToRoots(Group group) {
        roots.add(group);
    }

    public static boolean addGroup(Group addedGroup, String id, List<Group> groups, boolean addToRoot) {
        boolean isAdded = false;
        List<Group> copyGroup = new ArrayList<>(groups);
        Group parent;
        Iterator<Group> iterator = copyGroup.iterator();
        while (iterator.hasNext()) {
            parent = iterator.next();
            if (parent.getId().equals(id)) {
                parent.getGroups().add(addedGroup);//parent group add sub
                Storage.groups.add(addedGroup);
                addedGroup.setGroupParent(parent);//sub group add parent
                return true;
            } else {
                isAdded = addGroup(addedGroup, id, parent.getGroups(), false);
                if (isAdded) return true;
            }
        }

        if (addToRoot && !isAdded) {
            Storage.groups.add(addedGroup);
            roots.add(addedGroup);
        }

        return false;
    }

    public static Item addItem(Item item) {
        items.add(item);
        return item;
    }

    public static boolean addItem(Item addedItem, String id, List<Group> groups) {
        boolean isAdded = false;
        List<Group> copyGroup = new ArrayList<>(groups);
        Group g;
        Iterator<Group> iterator = copyGroup.iterator();
        while (iterator.hasNext()) {
            g = iterator.next();
            if (g.getId().equals(id)) {
                g.getItems().add(addedItem);
                basket.addToBasket(addedItem);
                addedItem.setParentGroup(g);
                return true;
            } else {
                isAdded = addItem(addedItem, id, g.getGroups());
                if (isAdded) return true;
            }
        }
        return false;
    }

    public static Optional<Group> findGroupByName(String name) {
        return groups.stream()
                .filter(a -> a.getName().equals(name))
                .findFirst();
    }

    public static List<Item> findAllItems() {
        return items;
    }

    public static Item findItemById(String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }

        }
        return null;
    }

    public static Item findItemByTitle(String name) throws ItemNotFoundException {
        for (Group group : groups) {
            Optional<Item> item = group.getItems()
                    .stream()
                    .filter(b -> b.getName().equals(name))
                    .findFirst();
            if (item.isPresent())
                return item.get();
        }
        throw new ItemNotFoundException();
    }

    public static Optional<Item> findHighestPricedItemInTheDirectGroup(Group group) {
        return group.getItems()
                .stream()
                .max(Comparator.comparing(Item::getPrice));

    }

    public static Optional<Item> findHighestPricedItem() {
        List<Item> maxes = new ArrayList<>();
        for (Group group : groups) {
            Optional<Item> item = group.getItems()
                    .stream()
                    .max(Comparator.comparing(Item::calculatePrice));
            item.ifPresent(maxes::add);
        }

        return maxes.stream()
                .max(Comparator.comparing(Item::calculatePrice));

    }

    public static Optional<Item> findMinPricedItem() {
        List<Item> mines = new ArrayList<>();
        for (Group group : groups) {
            Optional<Item> item = group.getItems()
                    .stream()
                    .min(Comparator.comparing(Item::calculatePrice));
            item.ifPresent(mines::add);
        }

        return mines.stream()
                .min(Comparator.comparing(Item::calculatePrice));

    }


    public static Double findAveragePriceRateInTheGroup(Group group) {
        return group.getItems()
                .stream()
                .mapToDouble(Item::calculatePrice)
                .average()
                .orElse(Double.NaN);
    }

    public static void deleteItem(String id) throws ItemNotFoundException {
        for (Group group : groups) {
            Optional<Item> item = group.getItems()
                    .stream()
                    .filter(b -> b.getId().equals(id))
                    .findFirst();
            if (item.isPresent()) {
                group.getItems().remove(item.get());
                return;
            }
        }
        throw new ItemNotFoundException();
    }

    public static void updateItem(String id, String newName) throws ItemNotFoundException {
        for (Group group : groups) {
            Optional<Item> item = group.getItems()
                    .stream()
                    .filter(b -> b.getId().equals(id))
                    .findFirst();

            if (item.isPresent()) {
                item.get().setName(newName);
                return;
            }
        }
        throw new ItemNotFoundException();
    }

    public static List<Group> getRoots() {
        return roots;
    }
}