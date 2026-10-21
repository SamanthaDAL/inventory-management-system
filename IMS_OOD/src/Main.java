import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        String uid, upw, input;
        String b;
        String poid, doid;
        //Initializing the product location
        Location [] location = new Location[11];
        location[0] = new Location("KT14303","FKI",'1','A');
        location[1] = new Location("KT14503","FKI",'1','B');
        location[2] = new Location("KT14203","FKI",'2','A');
        location[3] = new Location("KT14403","FKI",'2','B');
        location[4] = new Location("KT14603","FKI",'2','C');
        location[5] = new Location("KT14803","FKI",'2','D');
        location[6] = new Location("KK14202","SE ",'2','A');
        location[7] = new Location("KP14102","NE ",'1','A');
        location[8] = new Location("KP14203","NE ",'2','A');
        location[9] = new Location("KD14203","DS ",'2','A');
        location[10] = new Location("KD14403","DS ",'2','B');
        //Initializing the product detail
        Stock [] product = new Stock[11];
        product[0] = new Stock("PP   ","KT14303",350,60.0,location[0]);
        product[1] = new Stock("MFC  ","KT14503",350,60.0,location[1]);
        product[2] = new Stock("CAO  ","KT14203",100,60.0,location[2]);
        product[3] = new Stock("DS   ","KT14403",350,60.0,location[3]);
        product[4] = new Stock("DSA  ","KT14603",250,60.0,location[4]);
        product[5] = new Stock("NF   ","KT14803",250,60.0,location[5]);
        product[6] = new Stock("SPM  ","KK14202",150,40.0,location[6]);
        product[7] = new Stock("SE   ","KP14102",100,40.0,location[7]);
        product[8] = new Stock("OOP  ","KP14203",100,60.0,location[8]);
        product[9] = new Stock("OOPDS","KD14203",100,60.0,location[9]);
        product[10] = new Stock("FDS  ","KD14403",100,60.0,location[10]);
        //Initializing supplier
        Supplier [] supplier = new Supplier[4];
        supplier[0] = new Supplier("UMS FKI","012-345-6789","ums_fki@iluv.ums.edu.my","Product 1 - 6  ");
        supplier[1] = new Supplier("UMS SE ","012-345-6789","ums_se@iluv.ums.edu.my ","Product 7      ");
        supplier[2] = new Supplier("UMS NE ","012-345-6789","ums_ne@iluv.ums.edu.my ","Product 8 - 9  ");
        supplier[3] = new Supplier("UMS DS ","012-345-6789","ums_ds@iluv.ums.edu.my ","Product 10 - 11");
        //Initializing purchase order product
        String[][][] purchaseProduct = {
                {{"Product1", "280"}, {"Product2", "280"}, {"Product3", "280"}, {"Product4", "280"}, {"Product5", "280"}, {"Product6", "280"}},
                {{"Product7", "120"}},
                {{"Product8", "100"}, {"Product9", "100"}},
                {{"Product10", "100"}, {"Product11", "100"}}
        };
        //Initializing purchase order total price
        double [] total = new double[4];
        total[0] = getPurchaseTotal(purchaseProduct[0], product);
        total[1] = getPurchaseTotal(purchaseProduct[1], product);
        total[2] = getPurchaseTotal(purchaseProduct[2], product);
        total[3] = getPurchaseTotal(purchaseProduct[3], product);
        //Initializing purchase order
        PurchaseOrder [] pOrder = new PurchaseOrder[4];
        pOrder[0] = new PurchaseOrder("PO001", 20230607, purchaseProduct[0], total[0], "Pending", supplier[0]);
        pOrder[1] = new PurchaseOrder("PO002", 20230607, purchaseProduct[1], total[1], "Pending", supplier[1]);
        pOrder[2] = new PurchaseOrder("PO003", 20230607, purchaseProduct[2], total[2], "Pending", supplier[2]);
        pOrder[3] = new PurchaseOrder("PO004", 20230607, purchaseProduct[3], total[3], "Pending", supplier[3]);
        //Initializing delivery order product
        String[][][] deliveryProduct = {
                {{"Product1", "277"}, {"Product2", "277"}, {"Product3", "277"}, {"Product4", "277"}, {"Product5", "277"}, {"Product6", "277"}},
                {{"Product7", "115"}},
                {{"Product8", "97"}, {"Product9", "97"}},
                {{"Product10", "83"}, {"Product11", "83"}}
        };
        //Initializing delivery order total price
        double [] deliveryTotal = new double[4];
        deliveryTotal[0] = getDeliveryTotal(deliveryProduct[0], product);
        deliveryTotal[1] = getDeliveryTotal(deliveryProduct[1], product);
        deliveryTotal[2] = getDeliveryTotal(deliveryProduct[2], product);
        deliveryTotal[3] = getDeliveryTotal(deliveryProduct[3], product);
        //Initializing carrier
        Carrier [] carrier = new Carrier[4];
        carrier[0] = new Carrier("ABX Express","SS 1234 ","012-333 4444","abx_express@gmail.com");
        carrier[1] = new Carrier("Poslaju    ","SAA 2234","013-224 3356","poslaju@gmail.com");
        carrier[2] = new Carrier("J&T Express","SD 7890 ","016-112 8765","jtexpress@gmail.com");
        carrier[3] = new Carrier("DHL        ","SA 6543 ","019-665 6654","dhl@gmail.com" );
        //Initializing customer
        Customer [] customer = new Customer[4];
        customer[0] = new Customer("Alice","Taman A","012-367 4434","alice@gmail.com");
        customer[1] = new Customer("Ben  ","Taman B","013-224 3126","ben@gmail.com");
        customer[2] = new Customer("Cin  ","Taman C","016-113 8755","cin@gmail.com");
        customer[3] = new Customer("Dora ","Taman D","013-121 4543","dora@gmail.com" );
        //Initializing delivery order
        DeliveryOrder [] dOrder = new DeliveryOrder[4];
        dOrder[0] = new DeliveryOrder("DO001", 20230606, deliveryProduct[0], deliveryTotal[0], "Order being prepared.", customer[0], carrier[0], 1);
        dOrder[1] = new DeliveryOrder("DO002", 20230606, deliveryProduct[1], deliveryTotal[1], "Order being prepared.", customer[1], carrier[1], 1);
        dOrder[2] = new DeliveryOrder("DO003", 20230606, deliveryProduct[2], deliveryTotal[2], "Order being prepared.", customer[2], carrier[2], 2);
        dOrder[3] = new DeliveryOrder("DO004", 20230606, deliveryProduct[3], deliveryTotal[3], "Order being prepared.", customer[3], carrier[3], 2);
        //New objects of every relevant class so that can take user input to instance the object
        Location [] newLocation;
        Stock [] newProduct;
        String [][] newPurchaseProduct;
        String [] newPurchaseQuantity;
        String [] newPurchaseItem;
        PurchaseOrder newOrder = new PurchaseOrder();
        String [][] newDeliveryProduct;
        String [] newDeliveryQuantity;
        String [] newDeliveryItem;
        Customer newCustomer = new Customer();
        DeliveryOrder newCOrder = new DeliveryOrder();
        //Initializing admins where they are also the only users
        User [] user = new User[1];
        user[0] = new Admin("demo", "demo123");
        User newUser = new Admin();
        //Front page where user is required to sign up or log in
        int option;
        do {
            input = JOptionPane.showInputDialog("Please choose an option:"
                    + "\n(1) Sign Up"
                    + "\n(2) Log In");
            option = Integer.parseInt(input);
            if (option == 1) {
                newUser.userSignUp();
            }
            else if (option == 2) {
                do {
                    uid = userLoginID();
                    upw = userLoginPassword();
                    if (isUserLoginSuccess(user, newUser, uid, upw)) {
                        JOptionPane.showMessageDialog(null, "Welcome " + uid);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Either your id or password is wrong." + "\nPlease re-login.");
                    }
                }while(!(isUserLoginSuccess(user, newUser, uid, upw)));
            }
        }while (option == 1);
        //User require to choose a module
        char choice;
        do{
            input = JOptionPane.showInputDialog("Please choose a module:"
                    +"\n(1) Stock management"
                    +"\n(2) Barcode Scanning"
                    +"\n(3) Purchase Order"
                    +"\n(4) Delivery Order"
                    +"\n(5) Report");
            int problem = Integer.parseInt(input);
            if(problem == 1){
                int p1;
                char p1c;
                do{
                    input = JOptionPane.showInputDialog("Please choose the feature you would like to access."
                            +"\n(1) View product list."
                            +"\n(2) Add new product and view new product list.");
                    p1 = Integer.parseInt(input);
                    if(p1==1){
                        System.out.println("\n");
                        productList(product);
                        System.out.println("---------------------------------------------------------------------------------------------------");
                    }
                    else if(p1==2){
                        productList(product);
                        int sum = 0;
                        char addMore;
                        do {
                            int newProductSize;
                            input = JOptionPane.showInputDialog("Please enter the number of new product you like to add:");
                            newProductSize = Integer.parseInt(input);
                            sum+=newProductSize;
                            newLocation = new Location[sum];
                            newProduct = new Stock[sum];
                            addNewProduct(newProduct, newLocation, newProductSize, sum);
                            newProductList(newProduct, newProductSize, sum);
                            input = JOptionPane.showInputDialog("Would you like to add more product?"
                                    +"\nPress Y to continue."
                                    +"\nPress N to return to Stock Management.");
                            addMore = input.charAt(0);
                        }while(addMore == 'Y'||addMore == 'y');
                        System.out.println("---------------------------------------------------------------------------------------------------");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Invalid feature!");
                    }
                    input = JOptionPane.showInputDialog("Would you like to go to another feature? Please enter F."
                            +"\nWould you like to go to another module? Please enter M.");
                    p1c = input.charAt(0);
                }while(p1c == 'F'||p1c == 'f');
            }
            else if(problem == 2){
                char searchAnotherProductLocation;
                do {
                    b = JOptionPane.showInputDialog("Please enter the barcode of the product: ");
                    if(isValidBarcode(location, b)) {
                        searchProductLocation(location, b);
                    }
                    else if(!isValidBarcode(location, b)){
                        JOptionPane.showMessageDialog(null,"Invalid barcode!"+"\nPlease try again.");
                    }
                    input = JOptionPane.showInputDialog("Would you like to search for another product's location?"
                            +"\nPress Y to continue."
                            +"\nPress N to exit.");
                    searchAnotherProductLocation = input.charAt(0);
                }while(searchAnotherProductLocation=='Y'||searchAnotherProductLocation=='y');
            }
            else if(problem == 3){
                int p3;
                char p3c;
                do{
                    input = JOptionPane.showInputDialog("Please choose the feature you would like to access."
                            +"\n(1) View purchase order list."
                            +"\n(2) View purchase order."
                            +"\n(3) View supplier list."
                            +"\n(4) Add purchase order.");
                    p3 = Integer.parseInt(input);
                    if(p3==1){
                        System.out.println("\n");
                        viewPurchaseOrderList(product, purchaseProduct, pOrder);
                    }
                    else if(p3==2){
                        char anotherPO;
                        do {
                            poid = JOptionPane.showInputDialog("Please enter the purchase order ID:");
                            System.out.println("\n");
                            if(isValidPOID (pOrder, poid)){
                                viewPurchaseOrder(product, purchaseProduct, pOrder, poid);
                            }
                            else if(!isValidPOID (pOrder, poid)){
                                JOptionPane.showMessageDialog(null,"Invalid ID!");
                            }
                            input = JOptionPane.showInputDialog("Would you like to search for another purchase order?"
                                    +"\nPress Y to continue."
                                    +"\nPress N to exit.");
                            anotherPO = input.charAt(0);
                        }while(anotherPO=='Y'||anotherPO=='y');
                    }
                    else if(p3==3){
                        System.out.println("\n");
                        viewSupplierList(supplier);
                    }
                    else if(p3==4){
                        int snpo;
                        input = JOptionPane.showInputDialog("Please enter number of purchase product:");
                        snpo = Integer.parseInt(input);
                        newPurchaseProduct = new String[snpo][2];
                        newPurchaseQuantity = new String[newPurchaseProduct.length];
                        newPurchaseItem = new String[newPurchaseProduct.length];
                        addPurchaseOrder(newPurchaseProduct, newOrder, supplier);
                        double newTotal = getPurchaseTotal(newPurchaseProduct, product);
                        newOrder.setPurchaseTotalPrice(newTotal);
                        System.out.println("\n");
                        poid = JOptionPane.showInputDialog("Please enter the purchase order ID to view new purchase order:");
                        viewNewPurchaseOrder(product, newPurchaseProduct, newPurchaseItem, newPurchaseQuantity, newOrder, poid);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Invalid feature!");
                    }
                    input = JOptionPane.showInputDialog("Would you like to go to another feature? Please enter F."
                            +"\nWould you like to go to another module? Please enter M.");
                    p3c = input.charAt(0);
                }while(p3c == 'F'||p3c == 'f');
            }
            else if(problem == 4){
                int p4;
                char p4c;
                do{
                    input = JOptionPane.showInputDialog("Please choose the feature you would like to access."
                            +"\n(1) View delivery order list."
                            +"\n(2) View delivery order."
                            +"\n(3) View customer list."
                            +"\n(4) View carrier list."
                            +"\n(5) Add delivery order.");
                    p4 = Integer.parseInt(input);
                    if(p4==1){
                        System.out.println("\n");
                        viewDeliveryOrderList(product, deliveryProduct, dOrder);
                    }
                    else if(p4==2){
                        char anotherDO;
                        do {
                            doid = JOptionPane.showInputDialog("Please enter the delivery order ID:");
                            System.out.println("\n");
                            if(isValidDOID (dOrder, doid)){
                                viewDeliveryOrder(product, deliveryProduct, dOrder, doid);
                            }
                            else if(!isValidDOID (dOrder, doid)){
                                JOptionPane.showMessageDialog(null,"Invalid ID!");
                            }
                            input = JOptionPane.showInputDialog("Would you like to search for another delivery order?"
                                    +"\nPress Y to continue."
                                    +"\nPress N to exit.");
                            anotherDO = input.charAt(0);
                        }while(anotherDO=='Y'||anotherDO=='y');
                    }
                    else if(p4==3){
                        System.out.println("\n");
                        viewCustomerList(customer);
                    }
                    else if(p4==4){
                        System.out.println("\n");
                        viewCarrierList(carrier);
                    }
                    else if(p4==5){
                        int snpo;
                        input = JOptionPane.showInputDialog("Please enter customer's name:");
                        newCustomer.setCustomerName(input);
                        input = JOptionPane.showInputDialog("Please enter customer's address:");
                        newCustomer.setCustomerAddress(input);
                        input = JOptionPane.showInputDialog("Please enter customer's email:");
                        newCustomer.setCustomerEmail(input);
                        input = JOptionPane.showInputDialog("Please enter customer's phone:");
                        newCustomer.setCustomerPhone(input);
                        input = JOptionPane.showInputDialog("Please enter number of delivery product:");
                        snpo = Integer.parseInt(input);
                        newDeliveryProduct = new String[snpo][2];
                        newDeliveryQuantity = new String[newDeliveryProduct.length];
                        newDeliveryItem = new String[newDeliveryProduct.length];
                        addDeliveryOrder(newDeliveryProduct, newCOrder, newCustomer, carrier);
                        double newTotal = getDeliveryTotal(newDeliveryProduct, product);
                        newCOrder.setTotalPrice(newTotal);
                        System.out.println("\n");
                        doid = JOptionPane.showInputDialog("Please enter the delivery order ID to view new delivery order:");
                        viewNewDeliveryOrder(product, newDeliveryProduct, newDeliveryItem, newDeliveryQuantity, newCOrder, doid);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Invalid feature!");
                    }
                    input = JOptionPane.showInputDialog("Would you like to go to another feature? Please enter F."
                            +"\nWould you like to go to another module? Please enter M.");
                    p4c = input.charAt(0);
                }while(p4c == 'F'||p4c == 'f');
            }
            else if(problem == 5){
                System.out.println("\n");
                printSalesReport(product, purchaseProduct, pOrder, deliveryProduct, dOrder);
            }
            input = JOptionPane.showInputDialog("Would you like to continue using this IMS?"
                    +"\nPress Y to continue."
                    +"\nPress N to exit.");
            choice = input.charAt(0);
            if (choice=='N'||choice=='n'){
                JOptionPane.showMessageDialog(null,"Thank you for using IMS."
                        +"\nYou are now logged out.");
            }
        }while(choice == 'Y'||choice == 'y');
    }
    //Function that returns user username
    public static String userLoginID() {
        String uid;
        uid = JOptionPane.showInputDialog("Please enter your ID:");
        return uid;
    }
    //Function that returns user password
    public static String userLoginPassword() {
        String upw;
        upw = JOptionPane.showInputDialog("Please enter your password:");
        return upw;
    }
    //Function that returns validation of login
    public static boolean isUserLoginSuccess(User [] user, User newUser, String uid, String upw){
        boolean success = true;
        if (uid.equals(user[0].getUserID())&&upw.equals(user[0].getUserPassword())){
            success = true;
        }
        else if (uid.equals(user[1].getUserID())&&upw.equals(user[1].getUserPassword())){
            success = true;
        }
        else if (uid.equals(user[2].getUserID())&&upw.equals(user[2].getUserPassword())){
            success = true;
        }
        else if (uid.equals(user[3].getUserID())&&upw.equals(user[3].getUserPassword())){
            success = true;
        }
        else if (uid.equals(user[4].getUserID())&&upw.equals(user[4].getUserPassword())){
            success = true;
        }
        else if (uid.equals(newUser.getUserID()) && upw.equals(newUser.getUserPassword())) {
            success = true;
        }
        else {
            success = false;
        }
        return success;
    }
    //Function that prints product list
    public static void productList(Stock [] product){
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("|                                          PRODUCT LIST                                           |");
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("|Name    |Barcode   |Quantity   |Price/Unit   |Customer Price/Unit   |Aisle   |Section   |Shelf   |");
        for (int i = 0; i < product.length; i++) {
            System.out.println(product[i]);
        }
    }
    //Function that prints new product details
    public static void newProductList(Stock [] newProductItem, int nps, int sum){
        for (int i = (sum-nps);i<sum;i++) {
            System.out.println(newProductItem[i]);
        }
    }
    //Function that prompts user to add new product
    public static void addNewProduct(Stock [] newProduct, Location [] newLocation, int nps, int sum){
        String n, b, a, input;
        int q;
        double p;
        char st, s;
        for (int i = (sum-nps); i<sum ; i++){
            if (newProduct[i]==null&&newLocation[i]==null) {
                newProduct[i] = new Stock();
                n = JOptionPane.showInputDialog("Please enter new product name ");
                newProduct[i].setProductName(n);
                b = JOptionPane.showInputDialog("Please enter new product barcode ");
                newProduct[i].setProductBarcode(b);
                input = JOptionPane.showInputDialog("Please enter new product quantity ");
                q = Integer.parseInt(input);
                newProduct[i].setProductQuantity(q);
                input = JOptionPane.showInputDialog("Please enter new product price per unit ");
                p = Double.parseDouble(input);
                newProduct[i].setProductPrice(p);
                newProduct[i].setProductCustomerPrice(p);
                newLocation[i] = new Location();
                JOptionPane.showMessageDialog(null, "Please enter the location of the product");
                newLocation[i].setProductBarcode(b);
                a = JOptionPane.showInputDialog("Please enter aisle:");
                newLocation[i].setAisle(a);
                input = JOptionPane.showInputDialog("Please enter section:");
                st = input.charAt(0);
                newLocation[i].setSection(st);
                input = JOptionPane.showInputDialog("Please enter shelf:");
                s = input.charAt(0);
                newLocation[i].setShelf(s);
                newProduct[i].setLocation(newLocation[i]);
                JOptionPane.showMessageDialog(null,"New product "+(i+1)+" is added.");
            }
        }
    }
    //Function that returns validation of barcode
    public static boolean isValidBarcode(Location [] location, String pb){
        boolean valid = true;
        int i;
        for(i = 0; i<location.length; i++){
            if(pb.equals(location[i].getProductBarcode())){
                valid = true;
                break;
            }
            else{
                valid = true;
            }
        }
        if(i>=66){
            valid = false;
        }
        return valid;
    }
    //Function that prints product location
    public static void searchProductLocation(Location [] location, String pb){
        for(int i = 0; i<location.length; i++){
            if(pb.equals(location[i].getProductBarcode())){
                JOptionPane.showMessageDialog(null, location[i].displayLocation());
                break;
            }
        }
    }
    //Function that prints PO list
    public static void viewPurchaseOrderList(Stock [] product, String [][][] purchaseProduct, PurchaseOrder [] pOrder) {
        String tItem, tQuantity, tPrice;
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Purchase ID |Date     |Total item |Total Quantity |Total Price |Supplier | Status");
        System.out.println("---------------------------------------------------------------------------------------");
        for (int i = 0; i < pOrder.length; i++) {
            int sumPurchase = 0;
            int item = 0;
            String[] purchaseQuantity = new String[purchaseProduct[i].length];
            for (int j = 0; j < purchaseProduct[i].length; j++) {
                purchaseQuantity[j] = purchaseProduct[i][j][1];
                sumPurchase += Integer.parseInt(purchaseQuantity[j]);
                item++;
            }
            tItem = Integer.toString(item);
            tQuantity = Integer.toString(sumPurchase);
            tPrice = Double.toString(pOrder[i].getPurchaseTotalPrice());
            System.out.print(pOrder[i].getPurchaseId()+"       |"+pOrder[i].getPurchaseDate()+" |"+item);
            for(int j = tItem.length();j<11;j++){
                System.out.print(" ");
            }
            System.out.print("|"+sumPurchase);
            for(int j = tQuantity.length();j<15;j++){
                System.out.print(" ");
            }
            System.out.print("|"+pOrder[i].getPurchaseTotalPrice());
            for(int j = tPrice.length();j<12;j++){
                System.out.print(" ");
            }
            System.out.print("|");
            System.out.println(pOrder[i].getSupplier().getSupplierName()+"  |"+pOrder[i].getPurchaseStatus());
            System.out.println("---------------------------------------------------------------------------------------");
        }
    }
    //Function that prints PO invoice
    public static void viewPurchaseOrder(Stock [] product, String [][][] purchaseProduct, PurchaseOrder [] pOrder, String poid) {
        for (int i = 0; i < pOrder.length; i++) {
            if (poid.equals(pOrder[i].getPurchaseId())) {
                int sumPurchase = 0;
                double totalPrice = 0.0;
                String[] purchaseQuantity = new String[purchaseProduct[i].length];
                String[] purchaseItem = new String[purchaseProduct[i].length];
                System.out.println("Purchase ID: " + pOrder[i].getPurchaseId());
                System.out.println("Purchase Date: " + pOrder[i].getPurchaseDate());
                System.out.println("Purchase Product:");
                System.out.println("Name |Barcode |Price per unit|Quantity|Total Price");
                for (int j = 0; j < purchaseProduct[i].length; j++) {
                    purchaseItem[j] = purchaseProduct[i][j][0];
                    purchaseQuantity[j] = purchaseProduct[i][j][1];
                    String productNumber = purchaseItem[j].replaceAll("\\D", "");
                    int a = Integer.parseInt(productNumber);
                    totalPrice = Integer.parseInt(purchaseQuantity[j]) * product[a - 1].getProductPrice();
                    System.out.println(product[a - 1].productPurchaseDetails() + "          |" + purchaseQuantity[j] + "     |" + totalPrice);
                    sumPurchase += Integer.parseInt(purchaseQuantity[j]);
                }
                System.out.println("Total Price: " + pOrder[i].getPurchaseTotalPrice());
                System.out.println("Purchase Status: " + pOrder[i].getPurchaseStatus());
                System.out.println("Supplier: " + pOrder[i].getSupplier().getSupplierName());
                System.out.println("Product Quantity: " + sumPurchase);
                break;
            }
        }
    }
    //Function that returns PO total price
    public static double getPurchaseTotal(String [][] purchaseProduct, Stock [] product){
        double total = 0.0;
        double totalPrice;
        String[] purchaseQuantity = new String[purchaseProduct.length];
        String[] purchaseItem = new String[purchaseProduct.length];
        for (int i = 0; i < purchaseProduct.length; i++) {
            purchaseItem[i] = purchaseProduct[i][0];
            purchaseQuantity[i] = purchaseProduct[i][1];
            String productNumber = purchaseItem[i].replaceAll("\\D", "");
            int a = Integer.parseInt(productNumber);
            totalPrice = Integer.parseInt(purchaseQuantity[i])*product[a-1].getProductPrice();
            total += totalPrice;
        }
        return total;
    }
    //Function that returns validation of PO id
    public static boolean isValidPOID (PurchaseOrder [] pOrder,String poid){
        boolean valid = true;
        int i;
        for (i = 0; i < pOrder.length; i++) {
            if (poid.equals(pOrder[i].getPurchaseId())) {
                valid = true;
                break;
            }
            else{
                valid = true;
            }
        }
        if(i>=pOrder.length){
            valid = false;
        }
        return valid;
    }
    //Function that prints supplier list
    public static void viewSupplierList(Supplier [] supplier){
        System.out.println("Name    |Phone/Fax    |Email                   |Product");
        System.out.println("----------------------------------------------------------------------");
        for(int i = 0; i< supplier.length; i++){
            System.out.println(supplier[i].supplierDetails());
        }
    }
    //Function that prompts user to add new PO
    public static void addPurchaseOrder(String [][] newPurchaseProduct, PurchaseOrder newOrder, Supplier [] supplier){
        String pon, poq, input, s;
        int d, ss;
        for(int i = 0; i<newPurchaseProduct.length; i++){
            for(int j = 0; j<2; j++){
                if(j==0){
                    pon = JOptionPane.showInputDialog("Please enter product number you wish to purchase:"
                            +"\nExample: Product1");
                    newPurchaseProduct[i][j] = pon;
                }
                else if(j==1){
                    poq = JOptionPane.showInputDialog("Please enter product quantity:");
                    newPurchaseProduct[i][j] = poq;
                }
            }
        }
        newOrder.setPurchaseProduct(newPurchaseProduct);
        newOrder.setPurchaseId("PO005");
        input = JOptionPane.showInputDialog("Please enter today's date:"
                +"\nExample: YYYYMMDD");
        d = Integer.parseInt(input);
        newOrder.setPurchaseDate(d);
        s = JOptionPane.showInputDialog("Please enter purchase order status:");
        newOrder.setPurchaseStatus(s);
        input = JOptionPane.showInputDialog("Please choose a supplier:"
                +"\n(1) UMS FKI"
                +"\n(2) UMS SE"
                +"\n(3) UMS NE"
                +"\n(4) UMS DS");
        ss = Integer.parseInt(input);
        newOrder.setSupplier(supplier[ss-1]);
        JOptionPane.showMessageDialog(null,"Purchase order "+newOrder.getPurchaseId()+" has been added!");
    }
    //Function that prints new PO
    public static void viewNewPurchaseOrder(Stock [] product, String [][] purchaseProduct, String [] purchaseItem, String [] purchaseQuantity, PurchaseOrder order, String poid){
        int sumPurchase = 0;
        double totalPrice = 0.0;
        if(poid.equals(order.getPurchaseId())) {
            System.out.println("Purchase ID: " + order.getPurchaseId());
            System.out.println("Order Date: " + order.getPurchaseDate());
            System.out.println("Purchase Product:");
            System.out.println("Name |Barcode |Price per unit|Quantity|Total Price");
            for (int i = 0; i < purchaseProduct.length; i++) {
                purchaseItem[i] = purchaseProduct[i][0];
                purchaseQuantity[i] = purchaseProduct[i][1];
                String productNumber = purchaseItem[i].replaceAll("\\D", "");
                int a = Integer.parseInt(productNumber);
                totalPrice = Integer.parseInt(purchaseQuantity[i]) * product[a - 1].getProductPrice();
                System.out.println(product[a - 1].productPurchaseDetails() + "          |" + purchaseQuantity[i] + "     |" + totalPrice);
                sumPurchase += Integer.parseInt(purchaseQuantity[i]);
            }
            System.out.println("Total Price: " + order.getPurchaseTotalPrice());
            System.out.println("Purchase Status: " + order.getPurchaseStatus());
            System.out.println("Supplier: " + order.getSupplier().getSupplierName());
            System.out.println("Product Quantity: " + sumPurchase);
        }
        else {
            System.out.println("Invalid ID!");
        }
    }
    //Function that prints DO list
    public static void viewDeliveryOrderList(Stock [] product, String [][][] deliveryProduct, DeliveryOrder [] dOrder) {
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("Delivery ID |Date     |Total item |Total Quantity |Total Price |Customer |Carrier     |Status");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < dOrder.length; i++) {
            int sumPurchase = 0;
            int item = 0;
            String[] deliveryQuantity = new String[deliveryProduct[i].length];
            for (int j = 0; j < deliveryProduct[i].length; j++) {
                deliveryQuantity[j] = deliveryProduct[i][j][1];
                sumPurchase += Integer.parseInt(deliveryQuantity[j]);
                item++;
            }
            String tItem, tQuantity, tPrice;
            tItem = Integer.toString(item);
            tQuantity = Integer.toString(sumPurchase);
            tPrice = Double.toString(dOrder[i].getTotalPrice());
            System.out.print(dOrder[i].getOrderID()+"       |"+dOrder[i].getOrderDate()+" |"+item);
            for(int j = tItem.length();j<11;j++){
                System.out.print(" ");
            }
            System.out.print("|"+sumPurchase);
            for(int j = tQuantity.length();j<15;j++){
                System.out.print(" ");
            }
            System.out.print("|"+dOrder[i].getTotalPrice());
            for(int j = tPrice.length();j<12;j++){
                System.out.print(" ");
            }
            System.out.print("|");
            System.out.println(dOrder[i].getCustomer().getCustomerName()+"    |"+dOrder[i].getCarrier().getCarrierName()+" |"+dOrder[i].getOrderStatus());
            System.out.println("------------------------------------------------------------------------------------------------------------");
        }
    }
    //Function that prints delivery order invoice
    public static void viewDeliveryOrder(Stock [] product, String [][][] deliveryProduct, DeliveryOrder [] dOrder, String doid) {
        for (int i = 0; i < dOrder.length; i++) {
            if (doid.equals(dOrder[i].getOrderID())) {
                int sumPurchase = 0;
                double totalPrice = 0.0;
                String[] deliveryQuantity = new String[deliveryProduct[i].length];
                String[] deliveryItem = new String[deliveryProduct[i].length];
                System.out.println("Customer Name: "+dOrder[i].getCustomer().getCustomerName());
                System.out.println("Customer Address: "+dOrder[i].getCustomer().getCustomerAddress());
                System.out.println("Customer Email: "+dOrder[i].getCustomer().getCustomerEmail());
                System.out.println("Customer Phone: "+dOrder[i].getCustomer().getCustomerPhone());
                System.out.println("Delivery ID: " + dOrder[i].getOrderID());
                System.out.println("Order Date: " + dOrder[i].getOrderDate());
                System.out.println("Delivery Product:");
                System.out.println("Name |Barcode |Price per unit|Quantity|Total Price");
                for (int j = 0; j < deliveryProduct[i].length; j++) {
                    deliveryItem[j] = deliveryProduct[i][j][0];
                    deliveryQuantity[j] = deliveryProduct[i][j][1];
                    String productNumber = deliveryItem[j].replaceAll("\\D", "");
                    int a = Integer.parseInt(productNumber);
                    totalPrice = Integer.parseInt(deliveryQuantity[j]) * product[a - 1].getProductCustomerPrice();
                    System.out.println(product[a - 1].productDeliveryDetails() + "          |" + deliveryQuantity[j] + "     |" + totalPrice);
                    sumPurchase += Integer.parseInt(deliveryQuantity[j]);
                }
                System.out.println("Total Price: " + dOrder[i].getTotalPrice());
                System.out.println("Purchase Status: " + dOrder[i].getOrderStatus());
                System.out.println("Carrier: " + dOrder[i].getCarrier().getCarrierName());
                System.out.println("Product Quantity: " + sumPurchase);
                break;
            }
        }
    }
    //Function that returns delivery order total price
    public static double getDeliveryTotal(String [][] deliveryProduct, Stock [] product){
        double total = 0.0;
        double totalPrice;
        String[] purchaseQuantity = new String[deliveryProduct.length];
        String[] purchaseItem = new String[deliveryProduct.length];
        for (int i = 0; i < deliveryProduct.length; i++) {
            purchaseItem[i] = deliveryProduct[i][0];
            purchaseQuantity[i] = deliveryProduct[i][1];
            String productNumber = purchaseItem[i].replaceAll("\\D", "");
            int a = Integer.parseInt(productNumber);
            totalPrice = Integer.parseInt(purchaseQuantity[i])*product[a-1].getProductCustomerPrice();
            total += totalPrice;
        }
        return total;
    }
    //Function that return validation of DO id
    public static boolean isValidDOID (DeliveryOrder [] dOrder,String doid){
        boolean valid = true;
        int i;
        for (i = 0; i < dOrder.length; i++) {
            if (doid.equals(dOrder[i].getOrderID())) {
                valid = true;
                break;
            }
            else{
                valid = true;
            }
        }
        if(i>=dOrder.length){
            valid = false;
        }
        return valid;
    }
    //Function that prints customer list
    public static void viewCustomerList(Customer [] customer){
        System.out.println("Name    |Address    |Phone        |Email");
        System.out.println("----------------------------------------------------------------");
        for(int i = 0; i< customer.length; i++){
            System.out.println(customer[i].customerDetails());
        }
    }
    //Function that prints carrier list
    public static void viewCarrierList(Carrier [] carrier){
        System.out.println("Name        |Plate    |Phone/Fax    |Email");
        System.out.println("----------------------------------------------------------------");
        for(int i = 0; i< carrier.length; i++){
            System.out.println(carrier[i].carrierDetails());
        }
    }
    //Function that adds new DO
    public static void addDeliveryOrder(String [][] newDeliveryProduct, DeliveryOrder newOrder, Customer newCustomer, Carrier [] carrier){
        String don, doq, input, s;
        int d, cc;
        for(int i = 0; i<newDeliveryProduct.length; i++){
            for(int j = 0; j<2; j++){
                if(j==0){
                    don = JOptionPane.showInputDialog("Please enter product number the customer ordered:"
                            +"\nExample: Product1");
                    newDeliveryProduct[i][j] = don;
                }
                else if(j==1){
                    doq = JOptionPane.showInputDialog("Please enter product quantity:");
                    newDeliveryProduct[i][j] = doq;
                }
            }
        }
        newOrder.setCustomer(newCustomer);
        newOrder.setOrderProduct(newDeliveryProduct);
        newOrder.setOrderID("DO005");
        input = JOptionPane.showInputDialog("Please enter today's date:"
                +"\nExample: YYYYMMDD");
        d = Integer.parseInt(input);
        newOrder.setOrderDate(d);
        s = JOptionPane.showInputDialog("Please enter delivery order status:");
        newOrder.setOrderStatus(s);
        input = JOptionPane.showInputDialog("Please choose a carrier:"
                +"\n(1) ABX Express"
                +"\n(2) Poslaju"
                +"\n(3) J&T Express"
                +"\n(4) DHL");
        cc = Integer.parseInt(input);
        newOrder.setCarrier(carrier[cc-1]);
        JOptionPane.showMessageDialog(null,"Delivery order "+newOrder.getOrderID()+" has been added!");
    }
    //Function that prints new delivery order invoice
    public static void viewNewDeliveryOrder(Stock [] product, String [][] deliveryProduct, String [] deliveryItem, String [] deliveryQuantity, DeliveryOrder order, String doid){
        int sumPurchase = 0;
        double totalPrice = 0.0;
        if (doid.equals(order.getOrderID())) {
            System.out.println("Customer Name: "+order.getCustomer().getCustomerName());
            System.out.println("Customer Address: "+order.getCustomer().getCustomerAddress());
            System.out.println("Customer Email: "+order.getCustomer().getCustomerEmail());
            System.out.println("Customer Phone: "+order.getCustomer().getCustomerPhone());
            System.out.println("Delivery ID: " + order.getOrderID());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Delivery Product:");
            System.out.println("Name |Barcode |Price per unit|Quantity|Total Price");
            for (int i = 0; i < deliveryProduct.length; i++) {
                deliveryItem[i] = deliveryProduct[i][0];
                deliveryQuantity[i] = deliveryProduct[i][1];
                String productNumber = deliveryItem[i].replaceAll("\\D", "");
                int a = Integer.parseInt(productNumber);
                totalPrice = Integer.parseInt(deliveryQuantity[i]) * product[a - 1].getProductCustomerPrice();
                System.out.println(product[a - 1].productDeliveryDetails() + "          |" + deliveryQuantity[i] + "       |" + totalPrice);
                sumPurchase += Integer.parseInt(deliveryQuantity[i]);
            }
            System.out.println("Total Price: " + order.getTotalPrice());
            System.out.println("Purchase Status: " + order.getOrderStatus());
            System.out.println("Carrier: " + order.getCarrier().getCarrierName());
            System.out.println("Product Quantity: " + sumPurchase);
        }
        else {
            System.out.println("Invalid ID!");
        }
    }
    //Function that prints sales report
    public static void printSalesReport(Stock [] product, String [][][] purchaseProduct, PurchaseOrder [] pOrder, String [][][] deliveryProduct, DeliveryOrder [] dOrder){
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Product Barcode |              SOLD              |            PURCHASE            |SALES");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("                |Total Quantity  |Total Price    |Total Quantity  |Total Price    |");
        System.out.println("---------------------------------------------------------------------------------------------");
        for(int i = 0; i<pOrder.length&&i<dOrder.length; i++){
            double totalPriceSold = 0.0;
            double totalPricePurchase = 0.0;
            double sales = 0.0;
            String sp, pp;
            String[] purchaseQuantity = new String[purchaseProduct[i].length];
            String[] purchaseItem = new String[purchaseProduct[i].length];
            String[] deliveryQuantity = new String[deliveryProduct[i].length];
            String[] deliveryItem = new String[deliveryProduct[i].length];
            for (int j = 0; j < purchaseProduct[i].length; j++) {
                purchaseItem[j] = purchaseProduct[i][j][0];
                purchaseQuantity[j] = purchaseProduct[i][j][1];
                String productNumber = purchaseItem[j].replaceAll("\\D", "");
                int a = Integer.parseInt(productNumber);
                totalPricePurchase = Integer.parseInt(purchaseQuantity[j]) * product[a - 1].getProductPrice();
                deliveryItem[j] = deliveryProduct[i][j][0];
                deliveryQuantity[j] = deliveryProduct[i][j][1];
                totalPriceSold = Integer.parseInt(deliveryQuantity[j]) * product[a - 1].getProductCustomerPrice();
                sp = Double.toString(totalPriceSold);
                pp = Double.toString(totalPricePurchase);
                System.out.print(product[a - 1].getProductBarcode()+ "         |" + deliveryQuantity[j]);
                for(int k = deliveryQuantity[j].length();k<16;k++){
                    System.out.print(" ");
                }
                System.out.print("|"+totalPriceSold);
                for(int k = sp.length();k<15;k++){
                    System.out.print(" ");
                }
                System.out.print("|"+purchaseQuantity[j]);
                for(int k = purchaseQuantity[j].length();k<16;k++){
                    System.out.print(" ");
                }
                System.out.print("|"+totalPricePurchase);
                for(int k = pp.length();k<15;k++){
                    System.out.print(" ");
                }
                System.out.print("|");
                if(totalPriceSold>totalPricePurchase){
                    sales = (totalPriceSold-totalPricePurchase)/totalPricePurchase*100;
                    System.out.println("+"+String.format("%.2f",sales)+"%");
                }
                else if(totalPricePurchase>totalPriceSold){
                    sales = (totalPricePurchase-totalPriceSold)/totalPricePurchase*100;
                    System.out.println("-"+String.format("%.2f",sales)+"%");
                }
                System.out.println("---------------------------------------------------------------------------------------------");
            }
        }
    }
}