public class PurchaseOrder{
    private String purchaseId;
    private int purchaseDate;
    private String[][] purchaseProduct;
    private double purchaseTotalPrice;
    private String purchaseStatus;
    private Supplier supplier;
    public PurchaseOrder() {

    }
    public PurchaseOrder(String purchaseId, int purchaseDate, String [][] purchaseProduct,
                         double purchaseTotalPrice, String purchaseStatus, Supplier supplier) {
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.purchaseProduct = purchaseProduct;
        this.purchaseTotalPrice = purchaseTotalPrice;
        this.purchaseStatus = purchaseStatus;
        this.supplier = supplier;
    }
    public PurchaseOrder(String purchaseId, int purchaseDate, double purchaseTotalPrice, String purchaseStatus) {
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.purchaseTotalPrice = purchaseTotalPrice;
        this.purchaseStatus = purchaseStatus;
    }
    public String getPurchaseId() {
        return purchaseId;
    }
    public void setPurchaseId(String id) {
        purchaseId = id;
    }
    public int getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(int date) {
        purchaseDate = date;
    }
    public String [][] getPurchaseProduct() {
        return purchaseProduct;
    }
    public void setPurchaseProduct(String [][] item) {
        purchaseProduct = item;
    }
    public double getPurchaseTotalPrice() {
        return purchaseTotalPrice;
    }
    public void setPurchaseTotalPrice(double totalPrice) {
        purchaseTotalPrice = totalPrice;
    }
    public String getPurchaseStatus() {
        return purchaseStatus;
    }
    public void setPurchaseStatus(String status) {
        purchaseStatus = status;
    }
    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}