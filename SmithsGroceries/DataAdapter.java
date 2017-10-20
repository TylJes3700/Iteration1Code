import java.sql.*;
import java.util.*;

public class DataAdapter {
   private Connection connection;
   
   public DataAdapter(Connection connection) {
      this.connection = connection;
   }
   
    // Get product info
   public Product loadProduct(int id) {
      try {
         String query = "SELECT * FROM Product WHERE ID = " + id;
      
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query);
         if (resultSet.next()) {
            Product product = new Product(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9));
            resultSet.close();
            statement.close();
         
            return product;
         }
      } 
      catch (SQLException e) {
         System.out.println("Database access error!");
         e.printStackTrace();
      }
      return null;
   }

    // Save product info
   public boolean saveProduct(Product product) {
      try {
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM Product WHERE ID = ?");
         statement.setInt(1, product.getID());
      
         ResultSet resultSet = statement.executeQuery();
      
         if (resultSet.next()) { // Product exists - edit info
            statement = connection.prepareStatement("UPDATE Product SET Name = ?, Unit = ?, Price = ?, Tax = ?, Quantity = ?, ExpDate = ?, Vendor = ?, Contact = ?  WHERE ID = ?");
            statement.setString(1, product.getName());
            statement.setString(2, product.getUnit());
            statement.setDouble(3, product.getPrice());
            statement.setDouble(4, product.getTax());
            statement.setDouble(5, product.getQuantity());
            statement.setString(6, product.getExpDate());
            statement.setString(7, product.getVendor());
            statement.setString(8, product.getContact());
            statement.setInt(9, product.getID());
         }
         else { // Product does not exist - create new
            statement = connection.prepareStatement("INSERT INTO Product VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, product.getName());
            statement.setInt(2, product.getID());
            statement.setString(3, product.getUnit());
            statement.setDouble(4, product.getPrice());
            statement.setDouble(5, product.getTax());
            statement.setDouble(6, product.getQuantity());
            statement.setString(7, product.getExpDate());
            statement.setString(8, product.getVendor());
            statement.setString(9, product.getContact());
         }
         statement.execute();
      
         resultSet.close();
         statement.close();
         return true;
      } 
      catch (SQLException e) {
         return false;
      }
   }
   
    // Save order info
   public boolean saveOrder(Order order) {
      try {
         PreparedStatement statement = connection.prepareStatement("INSERT INTO \"Order\" VALUES (?, ?, ?, ?, ?, ?)");
         statement.setInt(1, order.getID());
         statement.setDouble(2, order.getTotal());
         statement.setString(3, order.getDate());
         statement.setString(4, order.getTime());
         statement.setString(5, order.getPayType());
         statement.setString(6, order.getEmployee());
         statement.execute();
         statement.close();
         return (updateStock(order.getProducts()) && saveOrderDetails(order.getDetails()));
      } 
      catch (SQLException e) {
         return false;
      }
   }
   
    // Get latest ID of indicated table to determine what newest ID should be
   public String getLastID(String table) {
      ResultSet resultSet;
      try {
         PreparedStatement statement = connection.prepareStatement("SELECT MAX(ID) FROM " + table);
         resultSet = statement.executeQuery();
         return resultSet.getString(1);
      }
      catch (SQLException e) {
         return "-1";
      }
   }
   
    // Save user info (assuming username is new - check beforehand with findUser)
   public boolean saveUser(String userName, String password) {
      try {
         PreparedStatement statement = connection.prepareStatement("INSERT INTO User VALUES (?, ?)");
         statement.setString(1, userName);
         statement.setString(2, password);
         statement.execute();
         statement.close();
         return true;
      } 
      catch (SQLException e) {
         return false;
      }
   }
   
    // Check if password is correct for given username
   public boolean validateLogin(String userName, String password) {
      try {
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE Username = ?");
         statement.setString(1, userName);
         ResultSet resultSet = statement.executeQuery();
         if (resultSet.next()) { // User exists
            boolean valid = resultSet.getString(2).equals(password);
            resultSet.close();
            statement.close();
            return valid;
         }
         else { // User does not exist
            throw new SQLException();
         }
      } 
      catch (SQLException e) {
         return false;
      }
   }
   
    // Check if username already exists
   public boolean findUser(String userName) {
      try {
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE Username = ?");
         statement.setString(1, userName);
         ResultSet resultSet = statement.executeQuery();
      
         if (resultSet.next()) { // User exists
            resultSet.close();
            statement.close();
            return true;
         }
         else { // User does not exist
            throw new SQLException();
         }
      } 
      catch (SQLException e) {
         return false;
      }
   }

    // Update quantity of product after some amount is bought
   private boolean updateStock(List<Product> productList) {
      int i = 0;
      while (i < productList.size()) {
         Product product = productList.get(i);
         try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Product WHERE ID = ?");
            statement.setInt(1, product.getID());
         
            ResultSet resultSet = statement.executeQuery();
         
            statement = connection.prepareStatement("UPDATE Product SET Quantity = ? WHERE ID = ?");
            statement.setDouble(1, resultSet.getDouble(6) - product.getQuantity());
            statement.setInt(2, product.getID());
            statement.execute();
            resultSet.close();
            statement.close();
         }
         catch (SQLException e) {
            return false;
         }
         i++;
      }
      return true;
   }
   
   private boolean saveOrderDetails(List<OrderDetail> detailList) {
      int i = 0;
      while (i < detailList.size()) {
         OrderDetail detail = detailList.get(i);
         try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO OrderDetail VALUES (?, ?, ?, ?)");
            statement.setInt(1, detail.getID());
            statement.setInt(2, detail.getOrderID());
            statement.setInt(3, detail.getProductID());
            statement.setDouble(4, detail.getQuantity());
            statement.execute();
            statement.close();
         }
         catch (SQLException e) {
            return false;
         }
         i++;
      }
      return true;
   }
}