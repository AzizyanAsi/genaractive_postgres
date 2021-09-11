package com.example.webik.repository;

import com.example.webik.database.DatabaseConnection;
import com.example.webik.models.Item;
import com.example.webik.repository.mapper.ItemResultSetMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemJdbcRepo {
    public List<Item> findItems(String pName) {
        List<Item> items = new ArrayList<>();
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "select * from item where name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, pName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(ItemResultSetMapper.mapToPojo(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return items;
    }
    public List<Item> getAllItems() {
        List<Item> items= new ArrayList<>();
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "select * from item";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(ItemResultSetMapper.mapToPojo(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return items;
    }

    public Item getOneById(String itemId) {
        Item item;
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "select * from item where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            item = null;
            if (resultSet.next()) {
                item = ItemResultSetMapper.mapToPojo(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return item;
    }

    public Item updateItem(String itemId) {
        Item items;
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "UPDATE  item  SET \"name\"=? where id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            items = null;
            if (resultSet.next()) {
                items= ItemResultSetMapper.mapToPojo(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return items;
    }
    public Item deleteItem(String itemId) {
        Item items;
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "SELECT *\n" +
                    "    FROM \"stock_item\"\n" +
                    "        LEFT JOIN item i on i.id = stock_item.id ;\n" +
                    "DELETE FROM stock_item WHERE id=? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString('1', itemId);
            ResultSet resultSet = statement.executeQuery();
            items = null;
            if (resultSet.next()) {
                items= ItemResultSetMapper.mapToPojo(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return items;
    }
    public long createItem(Item item) {
        long newItem= -1;
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "insert into item(name, base_price,image_url,configuration) values("
                    + "'" + item.getName()
                    + "','" + item.getPrice()
                    + "','" + item.getImageUrl()
                    + "','" + item.getConfiguration()
                    + "')";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            System.out.println(query);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            newItem = generatedKeys.next() ? generatedKeys.getLong(1) : -1;

            return newItem;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
