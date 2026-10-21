public class DeliveryOrder {
    private String orderID;
    private int orderDate;
    private String[][] orderProduct;
    private double totalPrice;
    private String orderStatus;
    private Customer customer;
    private Carrier carrier;
    public DeliveryOrder(){

    }
    public DeliveryOrder(String orderID, int orderDate, String[][] orderProduct, double totalPrice, String orderStatus, Customer customer, Carrier carrier){
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderProduct = orderProduct;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.customer = customer;
        this.carrier = carrier;
    }
    public DeliveryOrder(String orderID, int orderDate, double totalPrice, String orderStatus, Customer customer){
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.customer = customer;
    }
    public DeliveryOrder(DeliveryOrder deliveryOrder){
        this.orderID = deliveryOrder.orderID;
        this.orderDate = deliveryOrder.orderDate;
        this.totalPrice = deliveryOrder.totalPrice;
        this.orderStatus = deliveryOrder.orderStatus;
    }
    public String getOrderID(){
        return this.orderID;
    }
    public int getOrderDate(){
        return this.orderDate;
    }
    public String[][] getOrderProduct(){
        return this.orderProduct;
    }
    public double getTotalPrice(){
        return this.totalPrice;
    }
    public String getOrderStatus(){
        return this.orderStatus;
    }
    public Customer getCustomer(){
        return new Customer(customer);
    }
    public Carrier getCarrier(){
        return new Carrier(carrier);
    }
    public void setOrderID(String orderID){
        this.orderID = orderID;
    }
    public void setOrderDate(int orderDate){
        this.orderDate = orderDate;
    }
    public void setOrderProduct(String[][] orderProduct){
        this.orderProduct = orderProduct;
    }
    public void setTotalPrice(double totalPrice){
        this.totalPrice = totalPrice;
    }
    public void setOrderStatus(String orderStatus){
        this.orderStatus = orderStatus;
    }
    public void setCustomer(Customer customer){
        this.customer = new Customer(customer);
    }
    public void setCarrier(Carrier carrier){
        this.carrier = new Carrier(carrier);
    }
}