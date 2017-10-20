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
   
public class CashScreen {

   private static GridPane gridPane;
   private static ColumnConstraints column1, column2, column3, column4;
   private static Label headerLabel, totalLabel, totalValueLabel, paymentLabel, changeLabel, changeValueLabel;
   private static TextField paymentField;
   private static Button cancelButton, confirmButton, endButton;
   private static double payment, change = 0;
      
   public static GridPane getPane() {
      gridPane = new GridPane();
      gridPane.setAlignment(Pos.CENTER);
      gridPane.setVgap(20);
      
      column1 = GUI.getColumn(25.0, HPos.CENTER);
      column2 = GUI.getColumn(25.0, HPos.CENTER);
      column3 = GUI.getColumn(25.0, HPos.CENTER);
      column4 = GUI.getColumn(25.0, HPos.CENTER);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3, column4);
      
      addUIControls(gridPane);
      
      return gridPane;
   }
   
   private static void addUIControls(GridPane gridPane) {
      
      // Header
      headerLabel = GUI.getHeader("Cash Payment");
      gridPane.add(headerLabel, 1, 0, 2, 1);
      
      // Total Label
      totalLabel = new Label("Total : ");
      totalLabel.setId("cash-payment-label");
      gridPane.add(totalLabel, 1, 1);
      GridPane.setHalignment(totalLabel, HPos.RIGHT);
      
      // Total Value Label
      totalValueLabel = new Label("$ " + GUI.formatter.format(CheckoutScreen.getTotal()));
      totalValueLabel.setId("cash-payment-label");
      gridPane.add(totalValueLabel, 2, 1);
      GridPane.setHalignment(totalValueLabel, HPos.LEFT);
      
      // Payment Label
      paymentLabel = new Label("Payment : ");
      paymentLabel.setId("cash-payment-label");
      gridPane.add(paymentLabel, 1, 2);
      GridPane.setHalignment(paymentLabel, HPos.RIGHT);
      
      // Payment Field
      paymentField = GUI.getField("");
      paymentField.setMaxWidth(120);
      gridPane.add(paymentField, 2, 2);
      
      // Change Label
      changeLabel = new Label("Change : ");
      changeLabel.setId("cash-payment-label");
      gridPane.add(changeLabel, 1, 3);
      GridPane.setHalignment(changeLabel, HPos.RIGHT);
      
      // Change Value Label
      changeValueLabel = new Label();
      changeValueLabel.setId("cash-payment-label");
      gridPane.add(changeValueLabel, 2, 3);
      GridPane.setHalignment(changeValueLabel, HPos.LEFT);
      
      // Confirm Button
      confirmButton = new Button("Confirm");
      confirmButton.setId("cash-payment-button");
      confirmButton.setPrefHeight(54);
      confirmButton.setDefaultButton(true);
      confirmButton.setPrefWidth(150);
      gridPane.add(confirmButton, 1, 4, 2, 1);
      GridPane.setHalignment(confirmButton, HPos.CENTER);
      
      // End Transaction Button
      endButton = new Button("End Transaction");
      endButton.setId("cash-payment-button");
      endButton.setPrefHeight(54);
      endButton.setDefaultButton(true);
      endButton.setPrefWidth(200);
      endButton.setVisible(false);
      endButton.setDisable(true);
      gridPane.add(endButton, 1, 4, 2, 1);
      GridPane.setHalignment(endButton, HPos.CENTER);
      
      confirmButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Alert.AlertType alert = Alert.AlertType.ERROR;
               String title = "Form Error!";
               String message = "";
               if (paymentField.getText().isEmpty()) {
                  message = "Please enter a payment amount";
               }
               else {
                  try {
                     payment = Double.parseDouble(paymentField.getText());
                     if (payment < Double.valueOf(GUI.formatter.format(CheckoutScreen.getTotal()))) {
                        message = "Payment must be >= Total";
                     }
                     if (payment > 999999999) {
                        message = "No billionares allowed";
                     }
                  }
                  catch (NumberFormatException e) {
                     message = "Payment must be numerical";
                  }
               }
               if (message.equals("")) {
                  change = payment - CheckoutScreen.getTotal(); 
                  updateChangeLabel(); 
                  paymentField.setText("$ " + paymentField.getText());
                  switchButtons();
                  return;
               }
               
               // Display appropriate error pop-up
               GUI.showAlert(alert, gridPane.getScene().getWindow(), title, message);
            }
         });
         
      endButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
               String lastID = SmithsGroceries.dataAdapter.getLastID("\"Order\"");
               int id = (lastID == null) ? 0 : Integer.parseInt(lastID) + 1;
                  
               // Create order
               Order order = new Order(id, CheckoutScreen.getTotal(), dateFormat.format(new Date()).toString().substring(0,10), dateFormat.format(new Date()).toString().substring(11,19), "cash", LoginScreen.getUser(), CheckoutScreen.getProducts());
               if (SmithsGroceries.dataAdapter.saveOrder(order)) {
                  GUI.showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), "Success!", "Payment Successful\nOrder logged");
                  SmithsGroceries.setPane(1);
                  resetFields();
                  CheckoutScreen.clearScroll();
               }
               else {
                  GUI.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "SQL ERROR!", "Database Access Error");
               }
            }
         });
      
      // Footer (Logo)
      ImageView logo = GUI.getLogo();
      gridPane.add(logo, 3, 5);
      gridPane.setMargin(logo, new Insets(91, 20, 0, 0));
      
      // Cancel Button
      cancelButton = GUI.getCancelButton("Cancel");
      gridPane.add(cancelButton, 3, 0);
      GridPane.setMargin(cancelButton, new Insets(20, 20, 205, 0));
      
      cancelButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if (endButton.isVisible()) {
                  resetFields();
               }
               SmithsGroceries.setPane(6);
            }
         });
   }
   
   private static void resetFields() {
      switchButtons();
      paymentField.setText("");
      changeValueLabel.setText("");
   }
   
   private static void switchButtons() {
      if(paymentField.isDisabled()) {
         paymentField.setDisable(false);
         confirmButton.setDisable(false);
         confirmButton.setVisible(true);
         endButton.setDisable(true);
         endButton.setVisible(false);
      }
      else {
         paymentField.setDisable(true);
         confirmButton.setDisable(true);
         confirmButton.setVisible(false);
         endButton.setDisable(false);
         endButton.setVisible(true);
      }
   }
   
   private static void updateChangeLabel() {
      changeValueLabel.setText("$ " + GUI.formatter.format(change));
   }
   
   public static void updateTotalLabel() {
      totalValueLabel.setText("$ " + GUI.formatter.format(CheckoutScreen.getTotal()));
   }
}