import java.util.*;

public class Order {
   private int ID;
   private double total;
   private String date;
   private String time;
   private String payType;
   private String employee;
   //private int customerID ???
   private List<Product> productList;
   private List<OrderDetail> detailList;

   Order(int ID, double total, String date, String time, String payType, String employee, List<Product> productList) {
      this.ID = ID;
      this.total = total;
      this.date = date;
      this.time = time;
      this.payType = payType;
      this.employee = employee;
      this.productList = productList;
      detailList = new ArrayList<OrderDetail>();
      populateDetails();
   }

   public int getID() {
      return ID;
   }

   public void setID(int ID) {
      this.ID = ID;
   }

   public double getTotal() {
      return total;
   }

   public void setTotal(double total) {
      this.total = total;
   }
   
   public String getDate() {
      return date;
   }
   
   public void setDate(String date) {
      this.date = date;
   }
   
   public String getTime() {
      return time;
   }
   
   public void setTime(String time) {
      this.time = time;
   }
   
   public String getPayType() {
      return payType;
   }
   
   public void setPayType(String payType) {
      this.payType = payType;
   }
   
   public String getEmployee() {
      return employee;
   }
   
   public void setEmployee(String employee) {
      this.employee = employee;
   }
   
   public List<Product> getProducts() {
      return productList;
   }
   
   public void setProducts(List<Product> productList) {
      this.productList = productList;
   }
   
   public List<OrderDetail> getDetails() {
      return detailList;
   }
   
   public void setDetails(List<OrderDetail> detailList) {
      this.detailList = detailList;
   }
   
   private void populateDetails() {
      int i = 0;
      String lastID = SmithsGroceries.dataAdapter.getLastID("OrderDetail");
      int detailID = (lastID == null) ? 0 : Integer.parseInt(lastID) + 1;
      while (i < productList.size()) {
         detailList.add(new OrderDetail(detailID, ID, productList.get(i).getID(), productList.get(i).getQuantity()));
         detailID++;
         i++;
      }
   }
}