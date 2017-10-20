public class OrderDetail {

   private int ID;
   private int orderID;
   private int productID;
   private double quantity;

   OrderDetail(int ID, int orderID, int productID, double quantity) {
      this.ID = ID;
      this.orderID = orderID;
      this.productID = productID;
      this.quantity = quantity;
   }

   public int getID() {
      return ID;
   }

   public void setID(int ID) {
      this.ID = ID;
   }

   public int getOrderID() {
      return orderID;
   }

   public void setOrderID(int orderID) {
      this.orderID = orderID;
   }

   public int getProductID() {
      return productID;
   }

   public void setProductID(int productID) {
      this.productID = productID;
   }

   public double getQuantity() {
      return quantity;
   }

   public void setQuantity(double quantity) {
      this.quantity = quantity;
   }

}