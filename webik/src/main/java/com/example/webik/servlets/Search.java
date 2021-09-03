package com.example.webik.servlets;

import com.example.webik.models.Item;
import com.example.webik.service.ItemNotFoundException;
import com.example.webik.service.Storage;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "searchServlet", value = "/search")
public class Search extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    public static int priceFrom = 0;
    public static int priceTo = 0;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        priceFrom = Integer.parseInt(req.getParameter("priceFrom"));
        priceTo = Integer.parseInt(req.getParameter("priceTo"));
        List<Item> sortedPriceItems = new ArrayList<>();
        for (Item item : Storage.items) {
            if (item.getPrice() >= priceTo && item.getPrice() <= priceFrom) {
                sortedPriceItems.add(item);
            }
        }
        resp.getWriter().write(objectMapper.writeValueAsString(sortedPriceItems));


    }
}
