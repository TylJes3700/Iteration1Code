import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.*;
   
public class LoginScreen {
      
   private static String user;
   private static GridPane gridPane;
   private static ColumnConstraints column1, column2, column3, column4;
   private static Label headerLabel, userLabel, passwordLabel;
   private static TextField userField;
   private static PasswordField passwordField;
   private static Button submitButton;
   private static Image image;
   private static ImageView logo;
      
   public static GridPane getPane() {
      gridPane = new GridPane();
      gridPane.setAlignment(Pos.CENTER);
      gridPane.setPadding(new Insets(0, 0, 0, 40));
      gridPane.setHgap(20);
      gridPane.setVgap(20);
      
      column1 = GUI.getColumn(25.0, HPos.CENTER); 
      column2 = GUI.getColumn(25.0, HPos.RIGHT);  
      column3 = GUI.getColumn(25.0, HPos.LEFT);
      column4 = GUI.getColumn(25.0, HPos.CENTER);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3, column4);
      
      addUIControls(gridPane);
      
      return gridPane;
   }
   
   private static void addUIControls(GridPane gridPane) {
      // Header (Logo)
      image = new Image("/images/Smith's Groceries Text Logo.png");
      logo = new ImageView(image);
      logo.setFitHeight(118);
      logo.setFitWidth(951);
      gridPane.add(logo, 0, 0, 4, 1);
      gridPane.setHalignment(logo, HPos.CENTER);
      gridPane.setValignment(logo, VPos.CENTER);
      gridPane.setMargin(logo, new Insets(40, 0, 56, 0));
      
      // Header Text
      headerLabel = GUI.getHeader("Login");
      gridPane.add(headerLabel, 1, 1, 2, 1);
      gridPane.setMargin(headerLabel, new Insets(0, 0, 20, 0));
      
      // User ID Label
      userLabel = new Label("User ID : ");
      GridPane.setHalignment(userLabel, HPos.RIGHT);
      gridPane.add(userLabel, 1, 2);
      
      // User ID Text Field (max 12 characters?)
      userField = GUI.getField("");
      userField.setMaxWidth(100);
      gridPane.add(userField, 2, 2);
      
      // Password Label
      passwordLabel = new Label("Password : ");
      GridPane.setHalignment(passwordLabel, HPos.RIGHT);
      gridPane.add(passwordLabel, 1, 4);
      
      // Password Field
      passwordField = new PasswordField();
      passwordField.setPrefHeight(40);
      passwordField.setMaxWidth(100);
      passwordField.setAlignment(Pos.CENTER);
      GridPane.setHalignment(passwordField, HPos.LEFT);
      gridPane.add(passwordField, 2, 4);
      
      // Submit Button
      submitButton = GUI.getConfirmButton("Submit");
      gridPane.setMargin(submitButton, new Insets(20, 0, 0, 0));
      gridPane.add(submitButton, 1, 5, 2, 1);
      submitButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Alert.AlertType alert = Alert.AlertType.ERROR;
               String title = "Form Error!";
               String message = "";
               if (userField.getText().isEmpty()) {
                  message = "Please enter your User ID";
               }
               else if (passwordField.getText().isEmpty()) {
                  message = "Please enter your password";  
               }
               else if (!SmithsGroceries.dataAdapter.findUser(userField.getText())) {
                  message = "Invalid User ID";   
               }
               else if (!SmithsGroceries.dataAdapter.validateLogin(userField.getText(), passwordField.getText())) {
                  message = "Invalid Password"; 
               }
               else {
                  user = userField.getText();
                  AdminMenuScreen.changeWelcome(user); // Possibly change to real name if thats included in SQL db with the user id
                  SmithsGroceries.setPane(1);
                  return;
               }
               // Display appropriate error pop-up
               GUI.showAlert(alert, gridPane.getScene().getWindow(), title, message); 
            }
         });
   }
   
   public static String getUser() {
      return user;
   }
}