public class Product {
   private String name;
   private int ID;
   private String unit;
   private double price;
   private double taxRate;
   private double quantity;
   private String expDate;
   private String vendor;
   private String contact;

   Product(String name, int ID, String unit, double price, double taxRate, double quantity, String expDate, String vendor, String contact) {
      this.name = name;
      this.ID = ID;
      this.unit = unit;
      this.price = price;
      this.taxRate = taxRate;
      this.quantity = quantity;
      this.expDate = expDate;
      this.vendor = vendor;
      this.contact = contact;
   }

   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }

   public int getID() {
      return ID;
   }

   public void setID(int ID) {
      this.ID = ID;
   }
   
   public String getUnit() {
      return unit;
   }
   
   public void setUnit(String unit) {
      this.unit = unit;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }
   
   public double getTax() {
      return taxRate;
   }
   
   public void setTax(double taxRate) {
      this.taxRate = taxRate;
   }

   public double getQuantity() {
      return quantity;
   }

   public void setQuantity(double quantity) {
      this.quantity = quantity;
   }
   
   public void addQuantity(double quantity) {
      this.quantity += quantity;
   }
   
   public String getExpDate() {
      return expDate;
   }
   
   public void setExpDate(String expDate) {
      this.expDate = expDate;
   }
   
   public String getVendor() {
      return vendor;
   }
   
   public void setVendor(String vendor) {
      this.vendor = vendor;
   }
   
   public String getContact() {
      return contact;
   }
   
   public void setContact(String contact) {
      this.contact = contact;
   }
}