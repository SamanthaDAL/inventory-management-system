public class Location implements BarcodeScanning{
    private String productBarcode;
    private String aisle;
    private char section;
    private char shelf;
    public Location(){
        this.productBarcode = "";
        this.aisle = "";
        this.section = ' ';
        this.shelf = ' ';
    }
    public Location(String pb, String a, char st, char s){
        this.productBarcode = pb;
        this.aisle = a;
        this.section = st;
        this.shelf = s;
    }
    public Location(Location location){
        this.productBarcode = location.productBarcode;
        this.aisle = location.aisle;
        this.section = location.section;
        this.shelf = location.shelf;
    }
    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
    }
    public void setAisle(String aisle) {
        this.aisle = aisle;
    }
    public void setSection(char section) {
        this.section = section;
    }
    public void setShelf(char shelf) {
        this.shelf = shelf;
    }
    public String getProductBarcode() {
        return productBarcode;
    }
    public String getAisle() {
        return aisle;
    }
    public char getSection() {
        return section;
    }
    public char getShelf() {
        return shelf;
    }
    public String displayLocation() {
        String location = "The product with barcode "+this.productBarcode+" is located at: "+"\nAisle: "+this.aisle+"\nSection: "+this.section+"\nShelf: "+this.shelf;
        return location;
    }
    public String toString(){
        String product = this.aisle+"     |"+this.section+"         |"+this.shelf+"       |";
        return product;
    }
}