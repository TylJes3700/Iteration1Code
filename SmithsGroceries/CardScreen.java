import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
   
public class CardScreen {
      
   private static boolean debit = false;
   private static String card, phone;
   private static GridPane gridPane;
   private static ColumnConstraints column1, column2, column3, column4;
   private static Label headerLabel, cardNumLabel, cardDash1, cardDash2, cardDash3, expDateLabel, cscLabel, firstNameLabel, lastNameLabel, addressLabel, cityLabel, stateLabel, zipLabel, pinLabel;
   private static TextField cardField1, cardField2, cardField3, cardField4, cscField, firstNameField, lastNameField, addressField, cityField, zipField, pinField, contactField1, contactField2, contactField3;
   private static ChoiceBox<String> expMonth, expYear, stateChoice;
   private static Alert.AlertType alert = Alert.AlertType.ERROR;
      
   public static GridPane getPane() {
      gridPane = new GridPane();
      gridPane.setAlignment(Pos.CENTER);
      gridPane.setVgap(20);
      
      column1 = GUI.getColumn(256, HPos.CENTER);
      column2 = GUI.getColumn(142, HPos.RIGHT);
      column3 = GUI.getColumn(350, HPos.LEFT);
      column4 = GUI.getColumn(276, HPos.CENTER);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3, column4);
      
      addUIControls(gridPane);
      
      return gridPane;
   }
   
   private static void addUIControls(GridPane gridPane) {
      
      // Header
      headerLabel = GUI.getHeader("Card Payment");
      gridPane.add(headerLabel, 1, 0, 2, 1);
      
      // Card # Label
      cardNumLabel = new Label("Card # : ");
      gridPane.add(cardNumLabel, 1, 1);
      
      // Card # Fields
      cardField1 = GUI.getContactField3();
      gridPane.add(cardField1, 2, 1);
      GridPane.setMargin(cardField1, new Insets(0, 0, 0, 0));
      
      cardDash1 = GUI.getDash(70);
      gridPane.add(cardDash1, 2, 1);
      
      cardField2 = GUI.getContactField3();
      GridPane.setMargin(cardField2, new Insets(0, 0, 0, 90));
      gridPane.add(cardField2, 2, 1);
      
      cardDash2 = GUI.getDash(160);
      gridPane.add(cardDash2, 2, 1);
      
      cardField3 = GUI.getContactField3();
      GridPane.setMargin(cardField3, new Insets(0, 0, 0, 180));
      gridPane.add(cardField3, 2, 1);
      
      cardDash3 = GUI.getDash(250);
      gridPane.add(cardDash3, 2, 1);
      
      cardField4 = GUI.getContactField3();
      GridPane.setMargin(cardField4, new Insets(0, 0, 0, 270));
      gridPane.add(cardField4, 2, 1);
      
      // Expiration Date Label
      expDateLabel = new Label("Exp. Date : ");
      gridPane.add(expDateLabel, 1, 2);
      
      // Expiration Date Drop-Down
      expMonth = GUI.getMonthBox();
      expYear = GUI.getYearBox();
      GridPane.setMargin(expYear, new Insets(0, 0, 0, 100));
      gridPane.add(expMonth, 2, 2);
      gridPane.add(expYear, 2, 2);
      
      // CSC Label
      cscLabel = new Label("CSC : ");
      gridPane.setHalignment(cscLabel, HPos.RIGHT);
      GridPane.setMargin(cscLabel, new Insets(0, 60, 0, 0));
      gridPane.add(cscLabel, 2, 2);
      
      // CSC Field
      cscField = GUI.getContactField1();
      gridPane.setHalignment(cscField, HPos.RIGHT);
      gridPane.add(cscField, 2, 2);
      
      // First Name Label
      firstNameLabel = new Label("First Name : ");
      gridPane.add(firstNameLabel, 1, 3);
      
      // First Name Field
      firstNameField = new TextField();
      firstNameField.setPrefHeight(40);
      gridPane.add(firstNameField, 2, 3);
      
      // Last Name Label
      lastNameLabel = new Label("Last Name : ");
      gridPane.add(lastNameLabel, 1, 4);
      
      // Last Name Field
      lastNameField = new TextField();
      lastNameField.setPrefHeight(40);
      gridPane.add(lastNameField, 2, 4);
      
      // Address Label
      addressLabel = new Label("Address : ");
      gridPane.add(addressLabel, 1, 5);
      
      // Address Field
      addressField = new TextField();
      addressField.setPrefHeight(40);
      gridPane.add(addressField, 2, 5);
      
      // City Label
      cityLabel = new Label("City : ");
      gridPane.add(cityLabel, 1, 6);
      
      // City Field
      cityField = new TextField();
      cityField.setPrefHeight(40);
      gridPane.setHalignment(cityField, HPos.LEFT);
      cityField.setMaxWidth(160);
      gridPane.add(cityField, 2, 6);
      
      // State Label
      stateLabel = new Label("State : ");
      gridPane.setHalignment(stateLabel, HPos.RIGHT);
      GridPane.setMargin(stateLabel, new Insets(0, 80, 0, 0));
      gridPane.add(stateLabel, 2, 6);
      
      // State Drop-Down
      stateChoice = GUI.getStateBox();
      gridPane.setHalignment(stateChoice, HPos.RIGHT);
      gridPane.add(stateChoice, 2, 6);
      
      // ZipCode Label
      zipLabel = new Label("Zip Code : ");
      gridPane.add(zipLabel, 1, 7);
      
      // ZipCode Field
      zipField = GUI.getField("");
      gridPane.setHalignment(zipField, HPos.LEFT);
      zipField.setMaxWidth(80);
      gridPane.add(zipField, 2, 7);
      
      // Pin # Label
      pinLabel = new Label("PIN : ");
      gridPane.setHalignment(pinLabel, HPos.RIGHT);
      GridPane.setMargin(pinLabel, new Insets(0, 70, 0, 0));
      gridPane.add(pinLabel, 2, 7);
      
      // Pin # Field
      pinField = GUI.getField("");
      gridPane.setHalignment(pinField, HPos.RIGHT);
      pinField.setMaxWidth(70);
      gridPane.add(pinField, 2, 7);
      
      // Contact Label
      Label contactLabel = new Label("Contact # : ");
      gridPane.add(contactLabel, 1, 8);
      
      // Contact Text Field
      contactField1 = GUI.getContactField1();
      gridPane.add(contactField1, 2, 8);
      
      Label phoneDash1 = GUI.getDash(60);
      gridPane.add(phoneDash1, 2, 8);
      
      contactField2 = GUI.getContactField2();
      gridPane.add(contactField2, 2, 8);
      
      Label phoneDash2 = GUI.getDash(140);
      gridPane.add(phoneDash2, 2, 8);
      
      contactField3 = GUI.getContactField3();
      gridPane.add(contactField3, 2, 8);
      
      // Footer (Logo)
      ImageView logo = GUI.getLogo();
      gridPane.add(logo, 3, 9);
      gridPane.setMargin(logo, new Insets(4, 20, 0, 0));
      
      // Cancel Button
      Button cancelButton = GUI.getCancelButton("Cancel");
      GridPane.setMargin(cancelButton, new Insets(20, 20, 58, 0));
      gridPane.add(cancelButton, 3, 0);
      cancelButton.setOnAction(GUI.changeScreen(6));
      
      // Confirm Button
      Button confirmButton = GUI.getConfirmButton("Confirm");
      gridPane.add(confirmButton, 1, 9, 2, 1);
      confirmButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if (anyEmpty() || anyBadLengths() || anyBadNumbers()) {
                  return;
               }
               else {
                  String expDate = expYear.getValue() + "/" + expMonth.getValue();
                  String payType = debit ? "debit" : "credit";
                  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                  String lastID = SmithsGroceries.dataAdapter.getLastID("\"Order\"");
                  int id = (lastID == null) ? 0 : Integer.parseInt(lastID) + 1;
                  
               // Create order
                  Order order = new Order(id, CheckoutScreen.getTotal(), dateFormat.format(new Date()).toString().substring(0,10), dateFormat.format(new Date()).toString().substring(11,19), payType, LoginScreen.getUser(), CheckoutScreen.getProducts());
               
                  // Add order/details/payment??/customer?? to order table
                  if (SmithsGroceries.dataAdapter.saveOrder(order)) {
                     GUI.showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), "Success!", "Database Updated");
                     resetFields();
                     CheckoutScreen.clearScroll();
                  
                  // Return to menu
                     SmithsGroceries.setPane(1);
                  }
                  else {
                     GUI.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "SQL ERROR!", "Database Access Error");
                  }
               }
            }
         });
   }
   
   private static boolean anyEmpty() {
      String message;
      if (cardField1.getText().isEmpty() || cardField2.getText().isEmpty() || cardField3.getText().isEmpty() || cardField4.getText().isEmpty()) {
         message = "Please enter a card number \n#### - #### - #### - ####";   
      }
      else if (expMonth.getValue().equals("MM") || expYear.getValue().equals("YYYY")) {
         message = "Please select an expiration date";
      }
      else if (cscField.getText().isEmpty()) {
         message = "Please enter a CSC";
      }
      else if (firstNameField.getText().isEmpty()) {
         message = "Please enter a first name";
      }
      else if (lastNameField.getText().isEmpty()) {
         message = "Please enter a last name";
      }
      else if (addressField.getText().isEmpty()) {
         message = "Please enter an address"; 
      }
      else if (cityField.getText().isEmpty()) {
         message = "Please enter a city";  
      }
      else if (stateChoice.getValue().equals("--")) {
         message = "Please select a state";  
      }
      else if (zipField.getText().isEmpty()) {
         message = "Please enter a zip code"; 
      }
      else if (debit && pinField.getText().isEmpty()) {
         message = "Please enter a PIN"; 
      }
      else if (contactField1.getText().isEmpty() || contactField2.getText().isEmpty() || contactField3.getText().isEmpty()) {
         message = "Please enter a phone number \n(***) - (***) - (****)";   
      }
      else {
         return false;
      }
      GUI.showAlert(alert, gridPane.getScene().getWindow(), "Form Error", message);
      return true;
   }
   
   private static boolean anyBadLengths() {
      String message;
      if (firstNameField.getText().length() > 30) {
         message = "First name must be between 1 and 30 characters"; 
      }
      else if (lastNameField.getText().length() > 30) {
         message = "Last name must be between 1 and 30 characters"; 
      }
      else if (addressField.getText().length() > 30) {
         message = "Address must be between 1 and 30 characters"; 
      }
      else if (cityField.getText().length() > 12) {
         message = "City must be between 1 and 12 characters"; 
      }
      else {
         return false;
      }
      GUI.showAlert(alert, gridPane.getScene().getWindow(), "Form Error", message);
      return true;
   }
   
   private static boolean anyBadNumbers() {
      String message = "";
      int c1 = 0, c2 = 0, c3 = 0, card1 = 0, card2 = 0, card3 = 0, card4 = 0, csc, zip, pin;
      try {
         c1 = Integer.parseInt(contactField1.getText());
         c2 = Integer.parseInt(contactField2.getText());
         c3 = Integer.parseInt(contactField3.getText());
         if (c1 < 100 || c1 > 999 || c2 < 100 || c2 > 999 || c3 < 1000 || c3 > 9999) {
            throw new NumberFormatException();
         }
      }
      catch (NumberFormatException e) {
         message = "Phone # must be 10 digits \n(***) - (***) - (****)";
      }
      if (debit) {
         try {
            pin = Integer.parseInt(pinField.getText());
            if (pin < 1000 || pin > 9999) {
               throw new NumberFormatException();
            }
         }
         catch (NumberFormatException e) {
            message = "PIN must be 4 digits ####";
         }
      }
      try {
         zip = Integer.parseInt(zipField.getText());
         if (zip < 10000 || zip > 99999) {
            throw new NumberFormatException();
         }
      }
      catch (NumberFormatException e) {
         message = "Zip Code must be 5 digits #####";
      }
      try {
         csc = Integer.parseInt(cscField.getText());
         if (csc < 100 || csc > 999) {
            throw new NumberFormatException();
         }
      }
      catch (NumberFormatException e) {
         message = "CSC must be 3 digits ###";
      }
      try {
         card1 = Integer.parseInt(cardField1.getText());
         card2 = Integer.parseInt(cardField2.getText());
         card3 = Integer.parseInt(cardField3.getText());
         card4 = Integer.parseInt(cardField4.getText());
         if (card1 < 1000 || card1 > 9999 || card2 < 1000 || card2 > 9999 || card3 < 1000 || card3 > 9999 || card4 < 1000 || card4 > 9999) {
            throw new NumberFormatException();
         }
      }
      catch (NumberFormatException e) {
         message = "Card number must be 16 digits \n#### - #### - #### - ####";
      }
      if (message.equals("")) {
         phone = Integer.toString(c1) + "-" + Integer.toString(c2) + "-" + Integer.toString(c3);
         card = Integer.toString(card1) + "-" + Integer.toString(card2) + "-" + Integer.toString(card3) + "-" + Integer.toString(card4);
         return false;
      }
      GUI.showAlert(alert, gridPane.getScene().getWindow(), "Form Error", message);
      return true;
   }
   
   private static void resetFields() {
      cardField1.setText("");
      cardField2.setText("");
      cardField3.setText("");
      cardField4.setText("");
      expYear.setValue("YYYY");
      expMonth.setValue("MM");
      cscField.setText("");
      firstNameField.setText("");
      lastNameField.setText("");
      addressField.setText("");
      cityField.setText("");
      stateChoice.setValue("--");
      zipField.setText("");
      pinField.setText("");
      contactField1.setText("");
      contactField2.setText("");
      contactField3.setText("");
   }
   
}