import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.*;
   
public class PayMenuScreen {
   
   private static GridPane gridPane;
   private static ColumnConstraints column1, column2, column3; 
   private static ImageView logo;
   private static Label headerLabel;
   private static Button cancelButton, cashButton, debitButton, creditButton;
      
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
      
      // Header
      headerLabel = GUI.getHeader("Payment Method");
      gridPane.add(headerLabel, 1, 0);
      
      // Footer (Logo)
      logo = GUI.getLogo();
      gridPane.add(logo, 2, 4);
      gridPane.setMargin(logo, new Insets(40, 20, 0, 0));
      
      // Cancel Button
      cancelButton = GUI.getCancelButton("Cancel");
      GridPane.setMargin(cancelButton, new Insets(20, 20, 98, 0));
      gridPane.add(cancelButton, 2, 0);
      cancelButton.setOnAction(GUI.changeScreen(2));
      
      // Cash Button
      cashButton = GUI.getBigButton("Cash");
      gridPane.add(cashButton, 1, 1);
      cashButton.setOnAction(GUI.changeScreen(7));
      
      // Debit Button
      debitButton = GUI.getBigButton("Debit");
      gridPane.add(debitButton, 1, 2);
      GridPane.setMargin(debitButton, new Insets(10, 0, 10, 0));
      debitButton.setOnAction(GUI.changeScreen(8));
      
      // Credit Button
      creditButton = GUI.getBigButton("Credit");
      gridPane.add(creditButton, 1, 3);
      creditButton.setOnAction(GUI.changeScreen(8));
   }
}