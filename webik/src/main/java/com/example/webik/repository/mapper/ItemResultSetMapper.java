package com.example.webik.repository.mapper;

import com.example.webik.models.Configuration;
import com.example.webik.models.Item;
import com.example.webik.models.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemResultSetMapper {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BASE_PRICE = "base_price";
    public static final String COLUMN_IMAGE_URL = "image_url";
    public static final String COLUMN_CONFIGURATION = "configuration";

    public static Item mapToPojo(ResultSet resultSet) throws SQLException {
        return mapToPojo(resultSet, null);
    }

    public static Item mapToPojo(ResultSet resultSet, String alias) throws SQLException {
        alias = alias == null ? "" : alias + "_";
        String id = resultSet.getString(alias + COLUMN_ID);
        String name = resultSet.getString(alias + COLUMN_NAME);
        int basePrice = resultSet.getInt(alias + COLUMN_BASE_PRICE);
        String image_url = resultSet.getString(alias + COLUMN_IMAGE_URL);
        Configuration.Resolution resolution = Configuration.Resolution.values()
                [Integer.parseInt(resultSet.getString(alias + COLUMN_CONFIGURATION))];
        return new Stock(id, name, image_url, basePrice, resolution);
    }
}
