package com.example.BiddingBlitz.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.BiddingBlitz.model.auction.Item;

public class CatalogueService {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/auction.db";
    private static final String USER = "yourUsername";
    private static final String PASS = "yourPassword";

    // Method to search for auctions based on a keyword
    public List<Item> searchAuctions(String keyword) {
        List<Item> auctionItems = new ArrayList<>();

        String query = "SELECT * FROM auctions WHERE title LIKE ? OR description LIKE ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(query)) {

            // Setting the search parameter with wildcards for partial match
            String searchParam = "%" + keyword + "%";
            stmt.setString(1, searchParam);
            stmt.setString(2, searchParam);

            ResultSet rs = stmt.executeQuery();

            // Process each result
            while (rs.next()) {
                int itemId = rs.getInt("item_id");
                String name = rs.getString("name");
                String auctionType = rs.getString("auction_type");
                double itemPrice = rs.getDouble("item_price");
                double shippingPrice = rs.getDouble("shipping_price");
                double expeditedShipping = rs.getDouble("expeditedShipping");

                // Create and add AuctionItem object to the list
                Item item = new Item(itemId, name, auctionType, itemPrice, shippingPrice, expeditedShipping);
                auctionItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auctionItems;
    }
}
