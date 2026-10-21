public class Supplier {
    private String supplierName;
    private String supplierPhoneNumber;
    private String supplierEmail;
    private String supplierProduct;
    public Supplier() {
    }
    public Supplier(String sn, String spn, String se, String sp) {
        supplierName = sn;
        supplierPhoneNumber = spn;
        supplierEmail = se;
        supplierProduct = sp;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String name) {
        supplierName = name;
    }
    public String getSupplierPhoneNumber() {
        return supplierPhoneNumber;
    }
    public void setSupplierPhoneNumber(String phone) {
        supplierPhoneNumber = phone;
    }
    public String getSupplierEmail() {
        return supplierEmail;
    }
    public void setSupplierEmail(String email) {
        supplierEmail = email;
    }
    public String getSupplierProduct() {
        return supplierProduct;
    }
    public void setSupplierProduct(String product) {
        supplierProduct = product;
    }
    public String supplierDetails(){
        String sd =supplierName+" |"+supplierPhoneNumber+" |"+supplierEmail+" |"+supplierProduct;
        return sd;
    }
}