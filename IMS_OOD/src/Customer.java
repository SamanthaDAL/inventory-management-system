public class Customer {
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;
    public Customer(){
        this.customerName = " ";
        this.customerAddress = " ";
        this.customerPhone = " ";
        this.customerEmail = " ";
    }
    public Customer(String customerName, String customerAddress, String customerPhone, String customerEmail){
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
    }
    public Customer (Customer customer){
        this.customerName = customer.customerName;
        this.customerAddress = customer.customerAddress;
        this.customerPhone = customer.customerPhone;
        this.customerEmail = customer.customerEmail;;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    public String getCustomerName() {
        return this.customerName;
    }
    public String getCustomerAddress() {
        return this.customerAddress;
    }
    public String getCustomerPhone() {
        return this.customerPhone;
    }
    public String getCustomerEmail() {
        return this.customerEmail;
    }
    public String customerDetails(){
        String cd =customerName+"   |"+customerAddress+"    |"+customerPhone+" |"+customerEmail;
        return cd;
    }
}