import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import java.sql.*;
import java.util.*;

public class SmithsGroceries extends Application {

   private static AnchorPane root;
   private static List<GridPane> grid = new ArrayList<GridPane>();
   private static Connection connection;
   private static int currentScreen = 0;
   
   public static DataAdapter dataAdapter;

   public void start(Stage primaryStage) throws Exception {
   
   // create SQLite database connection
      try {
         Class.forName("org.sqlite.JDBC");
         connection = DriverManager.getConnection("jdbc:sqlite:SmithsGroceries.db");
      }
      catch (ClassNotFoundException ex) {
         System.out.println("ERROR: SQLite is not installed");
         System.exit(1);
      }
      
      catch (SQLException ex) {
         System.out.println("ERROR: SQLite database is not ready");
         System.exit(2);
      }
   
      // Create data adapter
      dataAdapter = new DataAdapter(connection);
   
     // Window Icon
      primaryStage.getIcons().add(new Image("/images/Smith's Groceries Icon.png"));
      
      // Base Screen
      root = new AnchorPane();
   
      // Add screens to screen list
      grid.add(LoginScreen.getPane());     // 0 Login
      grid.add(AdminMenuScreen.getPane()); // 1 Admin Menu
      grid.add(CheckoutScreen.getPane());  // 2 Checkout
      grid.add(ItemScreen.getPane());      // 3 Item (Add / Edit)
      grid.add(BarcodeScreen.getPane());   // 4 Product Search (Pre-Edit)
      grid.add(WeighScreen.getPane());     // 5 Weigh Item
      grid.add(PayMenuScreen.getPane());   // 6 Payment Menu
      grid.add(CashScreen.getPane());      // 7 Cash
      grid.add(CardScreen.getPane());      // 8 Card (Debit / Credit)
      
      // Begin on first screen
      root.getChildren().add(grid.get(0));
      
      // Create scene and set window size
      Scene scene = new Scene(root, 1024, 768);
      
      // Add css stylesheet
      scene.getStylesheets().add("smithsgroceries.css");
      
      // Prepare and display program
      primaryStage.setScene(scene);
      primaryStage.setTitle("Smith's Groceries");
      primaryStage.setResizable(false);
      primaryStage.show();
   }
   
   // Switch screens to designated one
   public static void setPane(int newScreen) {
      root.getChildren().remove(grid.get(currentScreen));
      root.getChildren().add(grid.get(newScreen));
      currentScreen = newScreen;
   }

   public static void main(String[] args) {
      launch(args);
   }
   
   @Override
   public void init() throws Exception {
      super.init();
      // Perform necessary initializations
   }
   
   @Override
   public void stop() throws Exception {
      super.stop();
      //Destroy Resources / Perform Cleanup
   }
}