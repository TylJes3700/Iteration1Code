import java.text.DecimalFormat;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.collections.*;
import javafx.stage.*;

public class GUI {

   public static DecimalFormat formatter = new DecimalFormat("###,###,##0.00");

    // Returns switch screen action handler to reduce number of anonymous classes
   public static EventHandler<ActionEvent> changeScreen(int newPane) {
      return 
         new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               SmithsGroceries.setPane(newPane);
               if (newPane == 0) {
                  CheckoutScreen.clearScroll();
               }
            }
         };
   }

    // Returns %-width column properties
   public static ColumnConstraints getColumn(double width, HPos position) {
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(width);
      column.setHalignment(position);
      return column;
   }
   
    // Returns pixel-width column properties
   public static ColumnConstraints getColumn(int width, HPos position) {
      ColumnConstraints column = new ColumnConstraints();
      column.setMinWidth(width);
      column.setHalignment(position);
      return column;
   }
   
    // Returns standard confirm/submit/continue button, centered, top-aligned (100 x 40)
   public static Button getConfirmButton(String label) {
      Button confirmButton = new Button(label);
      confirmButton.setId("confirm-button");
      confirmButton.setPrefHeight(40);
      confirmButton.setDefaultButton(true);
      confirmButton.setPrefWidth(100);
      GridPane.setValignment(confirmButton, VPos.TOP);
      GridPane.setHalignment(confirmButton, HPos.CENTER);
      return confirmButton;
   }
   
    // Returns standard cancel/logout/back button, right-aligned (128 x 54) (inset 20, 20, 108, 0)
   public static Button getCancelButton(String label) {
      Button cancelButton = new Button(label);
      cancelButton.setId("cancel-button");
      cancelButton.setPrefHeight(54);
      cancelButton.setPrefWidth(128);
      cancelButton.setCancelButton(true);
      GridPane.setHalignment(cancelButton, HPos.RIGHT);
      GridPane.setMargin(cancelButton, new Insets(20, 20, 108, 0));
      return cancelButton;
   }
   
    // Returns medium-size button, centered (236 x 108) (inset 20, 20, 0, 0)
   public static Button getMediumButton(String label) {
      Button medium = new Button(label);
      medium.setPrefHeight(108);
      medium.setPrefWidth(236);
      GridPane.setHalignment(medium, HPos.CENTER);
      GridPane.setMargin(medium, new Insets(20, 20, 0, 0));
      return medium;
   }
   
    // Returns large button, centered (512 x 108)
   public static Button getBigButton(String label) {
      Button big = new Button(label);
      big.setPrefHeight(108);
      big.setPrefWidth(512);
      GridPane.setHalignment(big, HPos.CENTER);
      return big;
   }
    // Returns radio button placed within Toggle Group (h: 40)
   public static RadioButton getRadioButton(String label, ToggleGroup group) {
      RadioButton button = new RadioButton(label);
      button.setToggleGroup(group);
      button.setPrefHeight(40);
      return button;
   }
    
    // Returns logo, right-aligned, bottom-aligned (236 x 112)
   public static ImageView getLogo() {
      Image image = new Image("/images/Smith's Groceries Logo.png");
      ImageView logo = new ImageView(image);
      logo.setFitHeight(112);
      logo.setFitWidth(236);
      GridPane.setHalignment(logo, HPos.RIGHT);
      GridPane.setValignment(logo, VPos.BOTTOM);
      return logo;
   }
   
    // Returns screen header, centered (fontsize 40px)
   public static Label getHeader(String title) {
      Label header = new Label(title);
      header.setId("header");
      GridPane.setHalignment(header, HPos.CENTER);
      return header;
   }
   
    // Returns text-centered, left-aligned field (h: 40)
   public static TextField getField(String label) {
      TextField field = new TextField(label);
      field.setPrefHeight(40);
      field.setAlignment(Pos.CENTER);
      GridPane.setHalignment(field, HPos.LEFT);
      return field;
   }
   
    // Returns 3-digit-length, text-centered, left-aligned field (60 x 40)
   public static TextField getContactField1() {
      TextField contact1 = getField("");
      contact1.setMaxWidth(60);
      GridPane.setHalignment(contact1, HPos.LEFT);
      return contact1;
   }
   
    // Returns 3-digit-length, text-centered, left-aligned field (60 x 40) (inset 0, 0, 0, 80)
   public static TextField getContactField2() {
      TextField contact2 = getField("");
      contact2.setMaxWidth(60);
      GridPane.setHalignment(contact2, HPos.LEFT);
      GridPane.setMargin(contact2, new Insets(0, 0, 0, 80));
      return contact2;
   }
   
    // Returns 4-digit-length, left-aligned field (70 x 40) (inset 0, 0, 0, 160)
   public static TextField getContactField3() {
      TextField contact3 = getField("");
      contact3.setMaxWidth(70);
      GridPane.setHalignment(contact3, HPos.LEFT);
      GridPane.setMargin(contact3, new Insets(0, 0, 0, 160));
      return contact3;
   }
   
    // Returns dash, left-aligned, inset on left as indicated
   public static Label getDash(int indent) {
      Label dash = new Label(" - ");
      GridPane.setHalignment(dash, HPos.LEFT);
      GridPane.setMargin(dash, new Insets(0, 0, 0, indent));
      return dash;
   }
   
    // Returns year drop-down, left-aligned, (w: 100)
   public static ChoiceBox<String> getYearBox() {
      ChoiceBox<String> expYear = new ChoiceBox<>(FXCollections.observableArrayList("YYYY", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"));
      expYear.getSelectionModel().selectFirst();
      GridPane.setHalignment(expYear, HPos.LEFT);
      expYear.setMinWidth(100);
      return expYear;
   }
   
    // Returns month drop-down, left-aligned, (w: 80)
   public static ChoiceBox<String> getMonthBox() {
      ChoiceBox<String> expMonth = new ChoiceBox<>(FXCollections.observableArrayList("MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"));
      expMonth.getSelectionModel().selectFirst();
      GridPane.setHalignment(expMonth, HPos.LEFT);
      expMonth.setMinWidth(80);
      return expMonth;
   }
   
    // Returns day drop-down, left-aligned, (w: 80)
   public static ChoiceBox<String> getDayBox() {
      ChoiceBox<String> expDay = new ChoiceBox<>(FXCollections.observableArrayList("DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"));
      expDay.getSelectionModel().selectFirst();
      GridPane.setHalignment(expDay, HPos.LEFT);
      expDay.setMinWidth(80);
      return expDay;
   }
   
   // Returns state drop-down, left-aligned, (w: 80)
   public static ChoiceBox<String> getStateBox() {
      ChoiceBox<String> stateChoice = new ChoiceBox<>(FXCollections.observableArrayList("--", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY", "GU", "PR", "VI"));
      stateChoice.getSelectionModel().selectFirst();
      stateChoice.setMinWidth(80);
      return stateChoice;
   }
   
    // Displays pop-up message with indicated alert type, title, and message
   public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
      Alert alert = new Alert(alertType);
      alert.setTitle(title);
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.initOwner(owner);
      alert.show();
   }
   
}