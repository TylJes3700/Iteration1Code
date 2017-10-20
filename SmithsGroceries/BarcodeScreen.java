import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.*;
   
public class BarcodeScreen {
      
   private static GridPane gridPane;
   private static ColumnConstraints column1, column2, column3;
   private static ImageView logo;
   private static Label headerLabel;
   private static TextField idField;
   private static Button searchButton, cancelButton;
      
   public static GridPane getPane() {
      gridPane = new GridPane();
      gridPane.setAlignment(Pos.CENTER);
      gridPane.setVgap(20);
      
      column1 = GUI.getColumn(25.0, HPos.CENTER);
      column2 = GUI.getColumn(50.0, HPos.CENTER);
      column3 = GUI.getColumn(25.0, HPos.CENTER);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3);
      
      addUIControls(gridPane);
      
      return gridPane;
   }
   
   private static void addUIControls(GridPane gridPane) {
      
      // Header
      headerLabel = GUI.getHeader("Product ID");
      gridPane.add(headerLabel, 1, 1);
      
      // ID Field
      idField = GUI.getField("");
      GridPane.setHalignment(idField, HPos.CENTER);
      idField.setMaxWidth(100);
      gridPane.add(idField, 1, 2);
      
      // Search Button
      searchButton = GUI.getConfirmButton("Search");
      gridPane.add(searchButton, 1, 3);
      searchButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               int id;
               Alert.AlertType alert = Alert.AlertType.ERROR;
               String title = "Form Error!";
               String message = "";
               if (idField.getText().isEmpty()) {
                  message = "Please enter a product ID";
                  GUI.showAlert(alert, gridPane.getScene().getWindow(), title, message);
                  return;
               }
               else {
                  try { 
                     id = Integer.parseInt(idField.getText());
                     if (id < 0) {
                        throw new NumberFormatException();
                     }
                  }
                  catch (NumberFormatException e) {
                     message = "Product ID must be a positive integer";
                     GUI.showAlert(alert, gridPane.getScene().getWindow(), title, message);
                     return;
                  }
               }
               Product product = SmithsGroceries.dataAdapter.loadProduct(id);
               if (product == null) {
                  message = "Invalid product ID";
                  GUI.showAlert(alert, gridPane.getScene().getWindow(), title, message);
                  return;
               }
               if (message.equals("")) {
                  ItemScreen.loadProductInfo(product);
                  SmithsGroceries.setPane(3);
               }
            }
         });
      
      // Footer (Logo)
      logo = GUI.getLogo();
      gridPane.add(logo, 2, 4);
      gridPane.setMargin(logo, new Insets(150, 20, 0, 0));
      
      // Cancel Button
      cancelButton = GUI.getCancelButton("Cancel");
      gridPane.add(cancelButton, 2, 0);
      GridPane.setMargin(cancelButton, new Insets(20, 20, 205, 0));
      cancelButton.setOnAction(GUI.changeScreen(1));
   }
}