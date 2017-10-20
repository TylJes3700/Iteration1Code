import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.collections.*;
   
public class ItemScreen {
      
   private static boolean edit = false;
   private static double quantity;
   private static double price, tax;
   private static String contact;
   private static GridPane gridPane;
   private static ColumnConstraints column1, column2, column3, column4;
   private static ImageView logo;
   private static Label headerLabel = GUI.getHeader("Add Item"), nameLabel, idLabel, quantityLabel, priceLabel, taxLabel, expDateLabel, unitLabel, contactLabel, phoneDash1, phoneDash2, vendorLabel;
   private static TextField nameField, idField, quantityField, priceField, taxField, vendorField, contactField1, contactField2, contactField3;
   private static Button cancelButton, confirmButton;
   private static RadioButton singleButton, ounceButton, poundButton;
   private static ToggleGroup group;
   private static ChoiceBox<String> expYear, expMonth, expDay;
   private static Alert.AlertType alert = Alert.AlertType.ERROR;
      
   public static GridPane getPane() {
      gridPane = new GridPane();
      gridPane.setAlignment(Pos.CENTER);
      gridPane.setVgap(20);
      
      column1 = GUI.getColumn(256, HPos.CENTER);
      column2 = GUI.getColumn(142, HPos.RIGHT);
      column3 = GUI.getColumn(350, HPos.CENTER);
      column4 = GUI.getColumn(276, HPos.CENTER);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3, column4);
      
      addUIControls(gridPane);
      
      return gridPane;
   }
   
   private static void addUIControls(GridPane gridPane) {
   
      // Header
      gridPane.add(headerLabel, 1, 0, 2, 1);
      gridPane.setMargin(headerLabel, new Insets(0, 0, 20, 0));
     
      // Footer (Logo)
      logo = GUI.getLogo();
      gridPane.add(logo, 3, 8);
      gridPane.setMargin(logo, new Insets(15, 20, 0, 0));
      
      // Cancel Button
      cancelButton = GUI.getCancelButton("Cancel");
      gridPane.add(cancelButton, 3, 0);
      cancelButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if (edit) {
                  SmithsGroceries.setPane(4);
                  edit = false;
                  headerLabel.setText("Add Item");
                  resetFields();
               }
               else {
                  SmithsGroceries.setPane(1);
               }
            }
         });
      
      // Name Label
      nameLabel = new Label("Name : ");
      gridPane.add(nameLabel, 1, 1);
      
      // Name Text Field
      nameField = new TextField();
      nameField.setPrefHeight(40);
      gridPane.add(nameField, 2, 1);
      nameField.requestFocus();
      
      // ID Label
      idLabel = new Label("ID : ");
      gridPane.add(idLabel, 1, 2);
      
      // ID Text Field
      idField = GUI.getField("");
      idField.setMaxWidth(80);
      idField.setDisable(true);
      resetID();
      gridPane.add(idField, 2, 2);
      
      // Quantity Label
      quantityLabel = new Label("Quantity : ");
      GridPane.setHalignment(quantityLabel, HPos.RIGHT);
      GridPane.setMargin(quantityLabel, new Insets(0, 80, 0, 0));
      gridPane.add(quantityLabel, 2, 2);
      
      // Quantity Text Field
      quantityField = GUI.getField("0");
      quantityField.setMaxWidth(80);
      GridPane.setHalignment(quantityField, HPos.RIGHT);
      gridPane.add(quantityField, 2, 2);
      
      // Unit Type Label
      unitLabel = new Label("Unit Type : ");
      gridPane.add(unitLabel, 1, 3);
      
      // Unit Type Radio Button Group
      group = new ToggleGroup();
      singleButton = GUI.getRadioButton("Single", group);
      singleButton.setSelected(true);
      GridPane.setMargin(singleButton, new Insets(0, 0, 0, 20));
      ounceButton = GUI.getRadioButton("Ounce", group);
      GridPane.setMargin(ounceButton, new Insets(0, 0, 0, 130));
      poundButton = GUI.getRadioButton("Pound", group);
      GridPane.setMargin(poundButton, new Insets(0, 0, 0, 240));
      gridPane.setHalignment(singleButton, HPos.LEFT);
      gridPane.setHalignment(ounceButton, HPos.LEFT);
      gridPane.setHalignment(poundButton, HPos.LEFT);
      gridPane.add(singleButton, 2, 3);
      gridPane.add(ounceButton, 2, 3);
      gridPane.add(poundButton, 2, 3);
      
      // Price Label
      priceLabel = new Label("Price ($) : ");
      gridPane.add(priceLabel, 1, 4);
      
      // Price Text Field
      priceField = GUI.getField("");
      priceField.setMaxWidth(120);
      gridPane.add(priceField, 2, 4);
      
      // Tax Label
      taxLabel = new Label("Tax (%) : ");
      gridPane.add(taxLabel, 2, 4);
      GridPane.setMargin(taxLabel, new Insets(0, 80, 0, 0));
      GridPane.setHalignment(taxLabel, HPos.RIGHT);
      
      // Tax Text Field
      taxField = GUI.getField("9");
      taxField.setMaxWidth(80);
      gridPane.add(taxField, 2, 4);
      GridPane.setHalignment(taxField, HPos.RIGHT);
      
      // Expiration Date Label
      expDateLabel = new Label("Exp. Date : ");
      gridPane.add(expDateLabel, 1, 5);
      
      // Expiration Date Drop Downs
      expMonth = GUI.getMonthBox();
      expDay = GUI.getDayBox();
      expYear = GUI.getYearBox();
      GridPane.setMargin(expDay, new Insets(0, 0, 0, 100));
      GridPane.setMargin(expYear, new Insets(0, 0, 0, 200));
      gridPane.add(expMonth, 2, 5);
      gridPane.add(expDay, 2, 5);
      gridPane.add(expYear, 2, 5);
      
      // Vendor Label
      vendorLabel = new Label("Vendor : ");
      gridPane.add(vendorLabel, 1, 6);
      
      // Vendor Text Field
      vendorField = new TextField();
      vendorField.setPrefHeight(40);
      gridPane.add(vendorField, 2, 6);
      
      // Contact Label
      contactLabel = new Label("Contact # : ");
      gridPane.add(contactLabel, 1, 7);
      
      // Contact Text Field
      contactField1 = GUI.getContactField1();
      gridPane.add(contactField1, 2, 7);
      
      phoneDash1 = GUI.getDash(60);
      gridPane.add(phoneDash1, 2, 7);
      
      contactField2 = GUI.getContactField2();
      gridPane.add(contactField2, 2, 7);
      
      phoneDash2 = GUI.getDash(140);
      gridPane.add(phoneDash2, 2, 7);
      
      contactField3 = GUI.getContactField3();
      gridPane.add(contactField3, 2, 7);
      
      // Confirm Button
      confirmButton = GUI.getConfirmButton("Confirm");
      gridPane.add(confirmButton, 1, 8, 2, 1);
      confirmButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if (anyEmpty() || anyBadLengths() || anyBadNumbers()) {
                  return;
               }
               else {
                  String year = expYear.getValue(), month = expMonth.getValue(), day = expDay.getValue();
                  if (year.equals("YYYY")) {
                     year = "----";
                  }
                  if (month.equals("MM")) {
                     month = "--";
                  }
                  if (day.equals("DD")) {
                     day = "--";
                  }
                  String date = year + "/" + month + "/" + day;
                  
                  // Add product to product Table (if ID is new) or edit existing product (if ID is old)
                  if (SmithsGroceries.dataAdapter.saveProduct(new Product(nameField.getText(), Integer.parseInt(idField.getText()), ((RadioButton)group.getSelectedToggle()).getText(), price, tax, quantity, date, vendorField.getText(), contact))) {
                     GUI.showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), "Success!", "Database Updated");
                     resetFields();
                  
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
      if (nameField.getText().isEmpty()) {
         message = "Please enter a name";
      }
      else if (quantityField.getText().isEmpty()) {
         message = "Please enter a quantity"; 
      }
      else if (priceField.getText().isEmpty()) {
         message = "Please enter a price";  
      }
      else if (taxField.getText().isEmpty()) {
         message = "Please enter a tax rate";  
      }
      else if (vendorField.getText().isEmpty()) {
         message = "Please enter a vendor"; 
      }
      else if (contactField1.getText().isEmpty() || contactField2.getText().isEmpty() || contactField3.getText().isEmpty()) {
         message = "Please enter vendor phone number (***) - (***) - (****)";   
      }
      else {
         return false;
      }
      GUI.showAlert(alert, gridPane.getScene().getWindow(), "Form Error", message);
      return true;
   }
   
   private static boolean anyBadLengths() {
      String message;
      if (nameField.getText().length() > 30) {
         message = "Name must be between 1 and 30 characters"; 
      }
      else if (vendorField.getText().length() > 30) {
         message = "Vendor must be between 1 and 30 characters";
      }
      else {
         return false;
      }
      GUI.showAlert(alert, gridPane.getScene().getWindow(), "Form Error", message);
      return true;
   }
   
   private static boolean anyBadNumbers() {
      String message = "";
      String c1Text = contactField1.getText(), c2Text = contactField2.getText(), c3Text = contactField3.getText();
      int c1 = 0, c2 = 0, c3 = 0;
      try {
         c1 = Integer.parseInt(c1Text);
         c2 = Integer.parseInt(c2Text);
         c3 = Integer.parseInt(c3Text);
         if (c1Text.length() != 3 || c2Text.length() != 3 || c3Text.length() != 4) {
            throw new NumberFormatException();
         }
      }
      catch (NumberFormatException e) {
         message = "Contact # must be 10 digits (***)-(***)-(****)";
      }
      try {
         tax = Double.parseDouble(taxField.getText());
         if (tax < 0 || tax > 100) {
            message = "Tax Rate must be between 0 and 100";
         }
      }
      catch (NumberFormatException e) {
         message = "Tax Rate must be a number";
      }
      try {
         price = Double.parseDouble(priceField.getText());
         if (price < 0.01 || price > 10000) {
            message = "Price must be between 0.01 and 10,000";
         }
      }
      catch (NumberFormatException e) {
         message = "Price must be a number";
      }
      try {
         quantity = Double.parseDouble(quantityField.getText());
         if (((RadioButton)group.getSelectedToggle()).getText().equals("Single")) {
            System.out.println("REACHED SINGLE");
            if (quantity < 0 || quantity > 99999 || quantity % 1 != 0) {
               message = "Quantity must be integer between 0 and 99,999";
            }
         }
         else {
            if (quantity < 0 || quantity > 99999) {
               message = "Quantity must be between 0 and 99,999";
            }
         }
      }
      catch (NumberFormatException e) {
         message = "Quantity must be an integer (single) or double (lb/oz)";
      }
      if (message.equals("")) {
         contact = c1Text + "-" + c2Text + "-" + c3Text;
         return false;
      }
      GUI.showAlert(alert, gridPane.getScene().getWindow(), "Form Error", message);
      return true;
   }
   
   private static void resetFields() {
      resetID();
      nameField.setText("");
      quantityField.setText("0");
      priceField.setText("");
      taxField.setText("9");
      expYear.setValue("YYYY");
      expMonth.setValue("MMMM");
      expDay.setValue("DD");
      vendorField.setText("");
      contactField1.setText("");
      contactField2.setText("");
      contactField3.setText("");
   }
   
   private static void resetID() {
      String id = SmithsGroceries.dataAdapter.getLastID("Product");
      if (id == null) { // Table empty
         idField.setText("0");
      }
      else {
         idField.setText(Integer.toString(Integer.parseInt(id) + 1));
      }
   }
   
   public static void loadProductInfo(Product product) {
      String contact = product.getContact();
      nameField.setText(product.getName());
      String year = product.getExpDate().substring(0,4);
      String month = product.getExpDate().substring(5,7);
      String day = product.getExpDate().substring(8,10);
      idField.setText(Integer.toString(product.getID()));
      if (product.getUnit().equals("Ounce")) {
         group.selectToggle(ounceButton);
      }
      else if (product.getUnit().equals("Pound")) {
         group.selectToggle(poundButton);
      }
      else {
         group.selectToggle(singleButton);
      }
      quantityField.setText(Double.toString(product.getQuantity()));
      priceField.setText(GUI.formatter.format(product.getPrice()));
      taxField.setText(Double.toString(product.getTax()));
      if (!year.equals("----")) {
         expYear.setValue(year);
      }
      else {
         expYear.setValue("Year");
      }
      if (!month.equals("--")) {
         expMonth.setValue(month);
      }
      else {
         expMonth.setValue("Month");
      }
      if (!day.equals("--")) {
         expDay.setValue(day);
      }
      else {
         expDay.setValue("Day");
      }
      vendorField.setText(product.getVendor());
      contactField1.setText(contact.substring(0,3));
      contactField2.setText(contact.substring(4,7));
      contactField3.setText(contact.substring(8,12));
      edit = true;
      headerLabel.setText("Edit Item");
   }
}