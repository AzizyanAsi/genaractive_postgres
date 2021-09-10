package com.example.webik.repository;

import com.example.webik.database.DatabaseConnection;
import com.example.webik.models.Group;
import com.example.webik.models.Item;
import com.example.webik.repository.mapper.GroupResultSetMapper;
import com.example.webik.repository.mapper.ItemResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupJdbcRepo {
    public Group getGroup(long groupId) {
        Group group = null;
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            // define select query
            String query = "SELECT g.id as group_id, g.name as group_name, i.id, i.name, i.base_price " +
                    "FROM \"group\" g " +
                    "left join item i on g.id = i.group_id " +
                    "where g.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, groupId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                if (group == null) {
                    group = GroupResultSetMapper.mapToPojo(resultSet);
                }
                Item item = ItemResultSetMapper.mapToPojo(resultSet);
                group.addItem(item);
            }
            return group;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Group> findGroups(String pName) {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "select * from group where name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, pName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                groups.add(GroupResultSetMapper.mapToPojo(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return groups;
    }

    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "select * from group ";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                groups.add(GroupResultSetMapper.mapToPojo(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return groups;
    }


    public Group getOneById(String groupId) {
        Group group;
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "select * from group where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            group = null;
            if (resultSet.next()) {
                group = GroupResultSetMapper.mapToPojo(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return group;
    }

    public Group updateGroup(String itemId) {
        Group group;
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "UPDATE  group SET \"name\"=? where id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            group = null;
            if (resultSet.next()) {
                group = GroupResultSetMapper.mapToPojo(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return group;
    }

    public Group deleteGroup(String groupId) {
        Group group;
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "SELECT *\n" +
                    "FROM \"item\"\n" +
                    "         LEFT JOIN public.\"group\" g on g.id = item.parentGroup;\n" +
                    "DELETE  FROM   public.item WHERE parentgroup=?;\n" +
                    "DELETE  FROM   public.group WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString('1', groupId);
            ResultSet resultSet = statement.executeQuery();
            group = null;
            if (resultSet.next()) {
                group = GroupResultSetMapper.mapToPojo(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return group;
    }

    public Group createGroup(String groupId) {
        Group group;
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "INSERT INTO public.group\n" +
                    "VALUES (id=?,name=?) ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString('1', groupId);
            ResultSet resultSet = statement.executeQuery();
            group = null;
            if (resultSet.next()) {
                group = GroupResultSetMapper.mapToPojo(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return group;
    }


}
