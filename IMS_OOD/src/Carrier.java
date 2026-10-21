public class Carrier{
    private String carrierName;
    private String carrierPlate;
    private String carrierPhone;
    private String carrierEmail;
    private String status;
    public Carrier(){
        this.carrierName = "";
        this.carrierPlate = "";
        this.carrierPhone = "";
        this.carrierEmail = "";
        this.status = "";
    }
    public Carrier(String carrierName,String carrierPlate, String carrierPhone, String carrierEmail,String status){
        this.carrierName = carrierName;
        this.carrierPlate = carrierPlate;
        this.carrierPhone = carrierPhone;
        this.carrierEmail = carrierEmail;
        this.status = status;
    }
    public Carrier(String carrierName,String carrierPlate, String carrierPhone, String carrierEmail){
        this.carrierName = carrierName;
        this.carrierPlate = carrierPlate;
        this.carrierPhone = carrierPhone;
        this.carrierEmail = carrierEmail;
    }
    public Carrier (Carrier carrier){
        this.carrierName = carrier.carrierName;
        this.carrierPlate =carrier.carrierPlate ;
        this.carrierPhone =carrier.carrierPhone;
        this.carrierEmail =carrier.carrierEmail;
        this.status = carrier.status;
    }
    public void setCarrierName(String carrierName){
        this.carrierName = carrierName;
    }
    public String getCarrierName(){
        return carrierName;
    }
    public void setCarrierPlate(String carrierPlate){
        this.carrierPlate = carrierPlate;
    }
    public String getCarrierPlate(){
        return carrierPlate;
    }
    public void setCarrierPhone(String carrierPhone){
        this.carrierPhone = carrierPhone;
    }
    public String getCarrierPhone(){
        return carrierPhone;
    }
    public void setCarrierEmail(String carrierEmail){
        this.carrierEmail = carrierEmail;
    }
    public String getCarrierEmail(){
        return carrierEmail;
    }
    public void setStatus(String status){this.status = status;}
    public String getStatus(){return status;}
    public void updateOrderStatus(){
        status= "Order placed and on delivery";
    }
    public String carrierDetails(){
        String cd =carrierName+" |"+carrierPlate+" |"+carrierPhone+" |"+carrierEmail;
        return cd;
    }
}