public class Stock {
    private String productName;
    private String productBarcode;
    private int productQuantity;
    private double productPrice;
    private double productCustomerPrice;
    private Location location;
    public Stock(){
        this.productName="";
        this.productBarcode="";
        this.productQuantity=0;
        this.productPrice=0.00;
        this.productCustomerPrice=0.00;
    }
    public Stock(String n, double cp){
        this.productName=n;
        this.productPrice=cp;
    }
    public Stock(String b, Location l){
        this.productBarcode=b;
        location = new Location(l);
    }
    public Stock(String n, String b, int q, double cp, Location l){
        this.productName=n;
        this.productBarcode=b;
        this.productQuantity=q;
        this.productPrice=(cp*0.75);
        this.productCustomerPrice=cp;
        location = new Location(l);
    }
    public void setProductName(String n) {
        this.productName = n;
    }
    public void setProductBarcode(String b) {
        this.productBarcode = b;
    }
    public void setProductQuantity(int q) {
        this.productQuantity = q;
    }
    public void setProductPrice(double p) {
        this.productPrice = p;
    }
    public void setProductCustomerPrice(double p) {
        this.productCustomerPrice = p*100/75;
    }
    public void setLocation(Location l) {
        this.location = l;
    }
    public String getProductName() {
        return productName;
    }
    public String getProductBarcode() {
        return productBarcode;
    }
    public int getProductQuantity() {
        return productQuantity;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public double getProductCustomerPrice() { return productCustomerPrice; }
    public Location getLocation() {
        return location == null ? null : new Location(location);
    }
    public String toString(){
        String product = "|"+this.productName+"   |"+this.productBarcode+"   |"+this.productQuantity+"        |"+this.productPrice+"         |"+this.productCustomerPrice+"                  |"+location;
        return product;
    }
    public String productPurchaseDetails(){
        String productDetails = this.productName+"|"+this.productBarcode+" |"+this.productPrice;
        return productDetails;
    }
    public String productDeliveryDetails(){
        String productDetails = this.productName+"|"+this.productBarcode+" |"+this.productCustomerPrice;
        return productDetails;
    }
}