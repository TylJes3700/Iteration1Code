import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.*;
   
public class WeighScreen {
      
   private static GridPane gridPane;
   private static ColumnConstraints column1, column2, column3, column4;
   private static ImageView logo;
   private static Label headerLabel;
   private static Button cancelButton;
      
   public static GridPane getPane() {
      gridPane = new GridPane();
      gridPane.setAlignment(Pos.CENTER);
      //gridPane.setHgap(10);
      //gridPane.setVgap(10);
      
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
      headerLabel = GUI.getHeader("Weigh Item");
      gridPane.add(headerLabel, 1, 0, 2, 1);
      
      // Cancel Button
      cancelButton = GUI.getCancelButton("Cancel");
      gridPane.add(cancelButton, 2, 0);
      cancelButton.setOnAction(GUI.changeScreen(2));
      
      // Footer (Logo)
      logo = GUI.getLogo();
      gridPane.add(logo, 3, 4);
      gridPane.setMargin(logo, new Insets(40, 20, 0, 0));
   }
}