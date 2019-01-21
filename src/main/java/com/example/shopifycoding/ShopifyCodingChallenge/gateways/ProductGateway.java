package com.example.shopifycoding.ShopifyCodingChallenge.gateways;

import com.example.shopifycoding.ShopifyCodingChallenge.connection.Connector;
import com.example.shopifycoding.ShopifyCodingChallenge.connection.DbConnection;
import com.example.shopifycoding.ShopifyCodingChallenge.models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductGateway {

    private Connector connector;
    private static ProductGateway productGateway;

    //language=SQL
    private final String SQL_GET_ALL_BOOKS = "SELECT  * from marketplacedb.product WHERE inventory_count > 0 ";


    /**
     * Singleton pattern for ProductGateway
     * @return ProductGateway
     */
    public static ProductGateway getProductGateway() {
        if (productGateway == null) {
            productGateway = new ProductGateway();
        }
        return productGateway;
    }

    /**
     * Query all {@link Product} from the database
     * @return list of products
     */
    public List<Product> getAll() {
        List<Product> productArrayList = new ArrayList<>();
        connector = DbConnection.get(SQL_GET_ALL_BOOKS);
        ResultSet resultSet = connector.getResultSet();
        buildFromResultSet(productArrayList, resultSet);
        return productArrayList;
    }

    /**
     * Helper method that builds from queried ResultSet into a {@link Product} object
     * Adds it to the productArrayList
     * @param productArrayList
     * @param resultSet
     */
    private void buildFromResultSet(List<Product> productArrayList, ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("inventory_count")
                );
                productArrayList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Unable to query from result set.");
            e.printStackTrace();
        } finally {
            connector.close();
        }
    }

    private Product buildResultSet(ResultSet resultSet) {
        Product product = null;
        try {
            while (resultSet.next()) {
                product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("inventory_count")
                );
            }
        } catch (SQLException e) {
            System.out.println("Unable to query from result set.");
            e.printStackTrace();
        } finally {
            connector.close();
        }
        return product;
    }

    /**
     * Updates a {@link Product} in the database
     * @param product
     */
    public void update(Product product) {
        try {
            Statement stmt = connector.getStatement();
            stmt.executeUpdate("UPDATE marketplacedb.product SET inventory_count = '" + product.getInventory_count()  + "' WHERE id = " + product.getId());
            connector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Product getById(int id){
        connector = DbConnection.get("SELECT  * from testdb.book WHERE id = '" + id + "'");
        ResultSet resultSet = connector.getResultSet();
        return buildResultSet(resultSet);
    }
}
