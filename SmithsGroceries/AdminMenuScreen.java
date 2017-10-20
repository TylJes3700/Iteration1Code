import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.*;
   
public class AdminMenuScreen {
      
   private static GridPane gridPane;
   private static ColumnConstraints column1, column2, column3;
   private static Label welcomeLabel;
   private static Button logoutButton, checkoutButton, addItemButton, editItemButton;
   private static ImageView logo;
      
   public static GridPane getPane() {
      gridPane = new GridPane();
      gridPane.setAlignment(Pos.CENTER);
      gridPane.setVgap(20);
      
      column1 = GUI.getColumn(25.0, HPos.LEFT);  
      column2 = GUI.getColumn(50.0, HPos.CENTER);
      column3 = GUI.getColumn(25.0, HPos.RIGHT);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3);
      
      addUIControls(gridPane);
      
      return gridPane;
   }
   
   private static void addUIControls(GridPane gridPane) {
      
      // Welcome Text
      welcomeLabel = new Label();
      welcomeLabel.setId("welcome-label");
      welcomeLabel.setPrefHeight(54);
      gridPane.add(welcomeLabel, 0, 0);
      gridPane.setHalignment(welcomeLabel, HPos.LEFT);
      gridPane.setValignment(welcomeLabel, VPos.TOP);
      gridPane.setMargin(welcomeLabel, new Insets(20, 0, 0, 20));
      
      // Footer (Logo)
      logo = GUI.getLogo();
      gridPane.add(logo, 2, 4);
      gridPane.setMargin(logo, new Insets(45, 20, 0, 0));
      
      // Logout Button
      logoutButton = GUI.getCancelButton("Logout");
      GridPane.setMargin(logoutButton, new Insets(20, 20, 93, 0));
      gridPane.add(logoutButton, 2, 0);
      logoutButton.setOnAction(GUI.changeScreen(0));
      
      // Checkout Button
      checkoutButton = GUI.getBigButton("Checkout");
      gridPane.add(checkoutButton, 1, 1);
      checkoutButton.setOnAction(GUI.changeScreen(2));
      
      // AddItem Button
      addItemButton = GUI.getBigButton("Add Item");
      gridPane.add(addItemButton, 1, 2);
      GridPane.setMargin(addItemButton, new Insets(10, 0, 10, 0));
      addItemButton.setOnAction(GUI.changeScreen(3));
      
      // EditItem Button
      editItemButton = GUI.getBigButton("Edit Item");
      gridPane.add(editItemButton, 1, 3);
      editItemButton.setOnAction(GUI.changeScreen(4));
   }
   
   public static void changeWelcome(String username) {
      welcomeLabel.setText("Welcome, " + username);
   }
}