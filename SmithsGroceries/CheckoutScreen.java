import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.*;
import java.util.*;
   
public class CheckoutScreen {
      
   private static double total = 0.00;
   private static GridPane gridPane, scrollContents;
   private static ColumnConstraints column1, column2, column3, column4, column5, scrollColumn1, scrollColumn2;
   private static Label headerLabel, productLabel, priceLabel, totalLabel, totalValueLabel, idLabel;
   private static TextField idField;
   private static Button cancelButton, clearButton, searchButton, weighButton, payButton;
   private static List<Product> productList = new ArrayList<Product>(); 
   private static int scrollRow = 0;
   private static ScrollPane productView;
      
   public static GridPane getPane() {
      gridPane = new GridPane();
      gridPane.setAlignment(Pos.CENTER);
      
      column1 = GUI.getColumn(12.5, HPos.LEFT);
      column2 = GUI.getColumn(25.0, HPos.LEFT);
      column3 = GUI.getColumn(25.0, HPos.LEFT); 
      column4 = GUI.getColumn(12.5, HPos.LEFT);
      column5 = GUI.getColumn(25.0, HPos.RIGHT);
      
      gridPane.getColumnConstraints().addAll(column1, column2, column3, column4, column5);
      
      addUIControls(gridPane);
      
      return gridPane;
   }
   
   private static void addUIControls(GridPane gridPane) {
      
      // Header
      headerLabel = GUI.getHeader("Checkout");
      gridPane.add(headerLabel, 1, 0, 2, 1);
      gridPane.setMargin(headerLabel, new Insets(0, 0, 20, 0));
      
      // Product Label
      productLabel = new Label("                         Product                             ");
      productLabel.setId("checkout-product-title");
      gridPane.add(productLabel, 1, 1, 2, 1);
      gridPane.setValignment(productLabel, VPos.BOTTOM);
      
      // Price Label
      priceLabel = new Label("      Price      ");
      priceLabel.setId("checkout-price-title");
      gridPane.add(priceLabel, 2, 1);
      gridPane.setHalignment(priceLabel, HPos.RIGHT);
      gridPane.setValignment(priceLabel, VPos.BOTTOM);
      
      // Footer (Logo)
      ImageView logo = GUI.getLogo();
      gridPane.add(logo, 4, 5);
      gridPane.setMargin(logo, new Insets(45, 20, 0, 0));
      
      // Total Label
      totalLabel = new Label(" Total :                               ");
      totalLabel.setId("checkout-total-label");
      gridPane.add(totalLabel, 1, 5, 2, 1);
      gridPane.setHalignment(totalLabel, HPos.RIGHT);
      gridPane.setValignment(totalLabel, VPos.TOP);
      
      // Total Value Label
      totalValueLabel = new Label("$ " + GUI.formatter.format(total));
      totalValueLabel.setId("checkout-total-value-label");
      gridPane.add(totalValueLabel, 1, 5, 2, 1);
      gridPane.setHalignment(totalValueLabel, HPos.RIGHT);
      gridPane.setValignment(totalValueLabel, VPos.TOP);
      gridPane.setMargin(totalValueLabel, new Insets(0, 37, 0, 0));
      
      // Cancel Button
      cancelButton = GUI.getCancelButton("Cancel");
      gridPane.add(cancelButton, 4, 0);
      cancelButton.setOnAction(GUI.changeScreen(1));
      
      // Clear Button
      clearButton = GUI.getCancelButton("Cancel");
      clearButton.setText("Clear");
      gridPane.add(clearButton, 3, 0, 2, 1);
      GridPane.setMargin(clearButton, new Insets(20, 168, 108, 0));
      clearButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               clearScroll();
            }
         });
      
      // ID Search
      idLabel = new Label("Product ID");
      idLabel.setId("id-search-label");
      gridPane.add(idLabel, 4, 2);
      GridPane.setMargin(idLabel, new Insets(0, 20, 0, 0));
      GridPane.setHalignment(idLabel, HPos.CENTER);
      GridPane.setValignment(idLabel, VPos.TOP);
      
      // ID Field
      idField = GUI.getField("");
      gridPane.add(idField, 4, 2);
      idField.setMaxWidth(100);
      GridPane.setMargin(idField, new Insets(58, 0, 10, 0));
      GridPane.setValignment(idField, VPos.BOTTOM);
      
      // Search Button
      searchButton = GUI.getConfirmButton("Add");
      gridPane.add(searchButton, 4, 2);
      GridPane.setMargin(searchButton, new Insets(0, 20, 10, 120));
      GridPane.setHalignment(searchButton, HPos.LEFT);
      GridPane.setValignment(searchButton, VPos.BOTTOM);
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
                  if (product.getUnit().equals("Single")) {
                     product.setQuantity(1);
                     total += product.getPrice()*(1 + product.getTax()/100);
                  }
                  else {
                     product.setQuantity(weightDialog(product.getName(), product.getUnit()));
                     total += product.getPrice()*(1 + product.getTax()/100)*product.getQuantity();
                  }
                  updateScroll(product);
                  totalValueLabel.setText("$ " + GUI.formatter.format(total));
                  return;
               }
            }
         });
      
      // Weigh Button
      weighButton = GUI.getMediumButton("Weigh Item");
      gridPane.add(weighButton, 4, 3);
      weighButton.setOnAction(GUI.changeScreen(5));
      
      // Pay Button
      payButton = GUI.getMediumButton("Pay");
      gridPane.add(payButton, 4, 4);
      payButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if (total == 0) {
                  GUI.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Checkout Error", "Your cart is empty");
                  return;
               }
               CashScreen.updateTotalLabel();
               SmithsGroceries.setPane(6);
            }
         });
         
      // Product ScrollPane
      productView = new ScrollPane();
      productView.setPrefViewportHeight(364);
      gridPane.add(productView, 1, 2, 2, 3);
      gridPane.setHgap(20);
      scrollContents = new GridPane();
      scrollColumn1 = GUI.getColumn(374, HPos.CENTER);
      scrollColumn2 = GUI.getColumn(100, HPos.RIGHT);
      scrollContents.getColumnConstraints().addAll(scrollColumn1, scrollColumn2);
      productView.setContent(scrollContents);
   }
   
   private static void updateScroll(Product product) {
      addProduct(product);
      String unit = product.getUnit();
      String unitLabel = (unit.equals("Single")) ? "" : ((unit.equals("Ounce")) ? " ( " + product.getQuantity() + " oz)" : " ( " + product.getQuantity() + " lb)");
      Label name = new Label(product.getName() + unitLabel);
      GridPane.setMargin(name, new Insets(0, 20, 0, 0));
      scrollContents.add(name, 0, scrollRow);
      
      Label price = new Label(GUI.formatter.format(product.getPrice()*product.getQuantity()));
      gridPane.setHalignment(price, HPos.RIGHT);
      GridPane.setMargin(price, new Insets(0, 20, 0, 0));
      scrollContents.add(price, 1, scrollRow);
      
      scrollRow++;
   }
   
   public static void clearScroll() {
      productList.clear();
      scrollContents.getChildren().clear();
      total = 0;
      totalValueLabel.setText("$ 0.00");
      idField.setText("");
   }
   
   public static double getTotal() {
      return total;
   }

   public static List<Product> getProducts() {
      return productList;
   }

   private static void addProduct(Product product) {
      int i = 0;
      while (i < productList.size()) {
         if (productList.get(i).getID() == product.getID()) {
            productList.get(i).addQuantity(product.getQuantity());
            return;
         }
         i++;
      }
      productList.add(product);
   }

   private static double weightDialog(String name, String unit) {
      TextInputDialog dialog = new TextInputDialog("");
      dialog.setTitle("Weight of Product");
      dialog.setHeaderText(name);
      dialog.setContentText("Weight (" + unit + "s) : ");
   
      Optional<String> result = dialog.showAndWait();
      try {
         String str = result.toString();
         String answer = str.substring(9,str.length()-1);
         double weight = Double.parseDouble(answer);
         if (weight > 0) {
            return weight;
         }
         else {
            throw new NumberFormatException();
         }
      }
      catch (NumberFormatException e) {
         return weightDialog(name, unit);
      }
   }
}