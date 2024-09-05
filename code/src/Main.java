import java.util.Scanner;
//Orders Class
class Orders{
    private String orderId;
    private String customerId;
    private String size;
    private int quantity;
    private int orderStatus;
    private double amount;

    public void setOrderId(String orderId){
        this.orderId = orderId;
    }

    public void setCustomerId(String customerId){
        if (customerId.charAt(0)=='0'&&customerId.length()==10){
            this.customerId = customerId;
        }
    }
    public void setSize(String size){
        size = size.toLowerCase();
        if (size.equals("xs")||size.equals("s")||size.equals("m")||size.equals("l")||size.equals("xl")||size.equals("xxl")){
            this.size = size.toUpperCase();
        }
    }

    public void setQty(int qty){
        if (qty>0){
            this.quantity = qty;
        }
    }

    public void setStatus(int status){
        this.orderStatus = status;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public String getOrderId(){
        return orderId;
    }

    public String getCustomerId(){
        return customerId;
    }

    public String getSize(){
        return size;
    }

    public int getQty(){
        return quantity;
    }

    public String getStatus(){
        String status=" ";
        switch (orderStatus){
            case 0:{
                status="PROCESSING";
            }
            case 1:{
                status="DELIVERING";
            }
            case 2:{
                status="DELIVERED";
            }
        }
        return status;
    }

    public double getAmount(){
        return amount;
    }

}

//Main Class
public class Main {

    public static Scanner input = new Scanner(System.in);

    private static Orders[] orders = new Orders[0];

    private static final int PROCESSING = 0;
    private static final int DELIVERING = 1;
    private static final int DELIVERED = 2;

    private static int lastOrderId = 0;

    private static final double xs = 600.00;
    private static final double s = 800.00;
    private static final double m = 900.00;
    private static final double l = 1000.00;
    private static final double xl = 1100.00;
    private static final double xxl = 1200.00;

    //Home
    public static void home() {
        System.out.println("\t\t+---------------+-------+-------+-------+-------+-------+-------+---------------+");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\tFashion Shop");
        System.out.println("\t\t+---------------+-------+-------+-------+-------+-------+-------+---------------+");
        System.out.print("\t\t[1] Place Order\t\t\t\t");
        System.out.println("[2] Search Customer\n");
        System.out.print("\t\t[3] Search Order\t\t\t");
        System.out.println("[4] View Reports\n");
        System.out.print("\t\t[5] Change Order Status\t\t");
        System.out.println("[6] Delete order\n");
        System.out.print("\t\tInput Option :");

        do {
            int option = input.nextInt();

            switch (option) {

                case (1):
                    placeOrder();
                    break;
                case (2):
                    searchCustomer();
                    break;
                case (3):
                    searchOrder();
                    break;
                case (4):
                    viewReports();
                    break;
                case (5):
                    changeOrderStatus();
                    break;
                case (6):
                    deleteOrder();
                    break;
                default:
                    System.out.println("Invalid Input......!!");
            }
        }
        while (true);
    }

    //Place the Order
    public static void placeOrder() {

       L1: do {
            String orderId = generateOrderId();
            System.out.println("\n\t\tEnter Order ID :" + orderId);
            String customerId;

           do {
                Scanner input = new Scanner(System.in);
                System.out.print("\n\t\tEnter Customer Phone Number :");
                customerId = input.nextLine();
                    if (customerId.length() == 10 && isContainZero(customerId)) {
                        break;
                    } else {
                        System.out.println("\n\t\tInvalid Phone number.. Try again");
                        do {
                            System.out.print("\n\t\tDo you want to enter phone number again? (y/n) : ");
                            String yN = input.next().toLowerCase();
                            if (yN.equals("y")) {
                                break;
                            } else if (yN.equals("n")) {
                                //clearConsole();
                                home();
                            }
                        } while (true);
                    }
            }while (true);
            String tshirtSize=" ";
           do {
                Scanner input= new Scanner(System.in);
               System.out.print("\n\t\tEnter T-Shirt Size (XS/S/M/L/XL/XXL) : ");
               tshirtSize = input.nextLine().toLowerCase();

               if (validateSize(tshirtSize)) {
                   break;
               }
           } while (true);

            int quantity;
            do {
                System.out.print("\n\t\tEnter QTY :");
                quantity = input.nextInt();
            } while (quantity <= 0);
            double amount = calculateAmount(quantity, tshirtSize);
            System.out.println("\n\t\tAmount : " + amount);
            do {
                System.out.print("\n\t\tDo you want to place this order? (y/n) : ");
                String yn = input.next().toLowerCase();
                switch (yn) {
                    case "y":
                        System.out.println("\n\t\t\tOrder Placed..");
                        extendArray();
                        orders[orders.length - 1].setOrderId(orderId);
                        orders[orders.length - 1].setCustomerId(customerId);
                        orders[orders.length - 1].setSize(tshirtSize.toUpperCase());
                        orders[orders.length - 1].setQty(quantity);
                        orders[orders.length - 1].setAmount(amount);
                        orders[orders.length-1].setStatus(0);

                        do {
                            System.out.print("\n\t\tDo you want to place another order? (y/n) : ");
                            String yn_1 = input.next().toLowerCase();

                            if (yn_1.equals("y")) {
                                clearConsole();
                                placeOrder();
                            } else if (yn_1.equals("n")) {
                                clearConsole();
                                home();
                            } else {
                                System.out.println("\n\t\t\tInvalid input.. Try again");
                            }
                        } while (true);
                    case "n":
                        System.out.println("Order not Placed..");
                        do {
                            System.out.print("\n\t\tDo you want to place another order? (y/n) : ");
                            String yn_1 = input.next().toLowerCase();

                            if (yn_1.equals("y")) {
                                clearConsole();
                                placeOrder();
                            } else if (yn_1.equals("n")) {
                                clearConsole();
                                home();
                            } else {
                                System.out.println("\n\t\t\tInvalid input.. Try again");
                            }
                        } while (true);
                    default:
                        System.out.println("\t\tInvalid input.. Try again");
                }
            } while (true);
        }while (true);
    }
    //Generate the Order Id
    public static String generateOrderId() {
        lastOrderId++;
        String orderId = "ODR#" + String.format("%05d", lastOrderId);
        return orderId;
    }

    //validate the phone number
    public static boolean isValidOrderId(String orderId) {
        for (int i = 0; i < orders.length; i++) {
            if (orderId.equals(orders[i].getOrderId())) {
                return false;
            }
        }
        return true;
    }

    //check whether phone number start with a "0"
    public static boolean isContainZero(String phoneNumber) {
        if (phoneNumber.startsWith("0")) {
            return true;
        }
        return false;
    }

    //Extend the String Array
    public static void extendArray() {
        Orders[] tempOrders = new Orders[orders.length + 1];
        for (int i = 0; i < orders.length; i++) {
            tempOrders[i] = orders[i];
        }
        tempOrders[tempOrders.length - 1] = new Orders();
        orders = tempOrders;
    }
    //Validate the t-shirt size
    public static boolean validateSize(String tshirtSize) {
        String[] validSizes = {"XS", "S", "M", "L", "XL", "XXL"};
        boolean isValidSize = false;
        for (String size : validSizes) {
            if (size.equalsIgnoreCase(tshirtSize)) {
                return true;
            }
        }
        return false;
    }

    //Calculate the amount of the tshirts
    public static double calculateAmount(int quantity, String size) {

        double amount = (double)quantity*(size.equals("xs")? xs:size.equals("s")? s:size.equals("m")? m:size.equals("l")? l:size.equals("xl")? xl:size.equals("xxl")? xxl:0.00);
        return amount;
    }

    //Search the customer
    public static void searchCustomer() {
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Customer Phone Number : ");
            String searchphoneNumber = input.nextLine();

            printCustomerReport(searchphoneNumber);
        }while (true);

    }

    //print the searched customer Report
    public static void printCustomerReport(String customerNumber){
        boolean isAvailable = false;
        int[] sizesQty = new int[6];
        String[] tempSize = {"XS","S","M","L","XL","XXL"};

        for (int i = 0; i < orders.length; i++){
            if (customerNumber.equals(orders[i].getCustomerId())){
                isAvailable = true;
                switch (orders[i].getSize()){
                    case "XS": sizesQty[0]+=orders[i].getQty();break;
                    case "S": sizesQty[1]+=orders[i].getQty();break;
                    case "M": sizesQty[2]+=orders[i].getQty();break;
                    case "L": sizesQty[3]+=orders[i].getQty();break;
                    case "XL": sizesQty[4]+=orders[i].getQty();break;
                    case "XXL": sizesQty[5]+=orders[i].getQty();break;
                }
            }
        }

        if (isAvailable){
            for (int i = 0; i < sizesQty.length-1; i++){
                for (int j = 0; j < sizesQty.length-1; j++){
                    String size = tempSize[j];
                    int qty = sizesQty[j];
                    if (qty<sizesQty[j+1]){
                        tempSize[j] = tempSize[j+1];
                        tempSize[j+1] = size;
                        sizesQty[j] = sizesQty[j+1];
                        sizesQty[j+1] = qty;
                    }
                }
            }

            System.out.println("\n\t\t\t+----------+----------+---------------+");
            System.out.printf("\t\t\t|%10s|%10s|%15s|%n","   Size   ","   QTY    ","     Amount    ");
            System.out.println("\t\t\t+----------+----------+---------------+");
            System.out.println("\t\t\t|          |          |               |");
            for (int i = 0; i < sizesQty.length; i++){
                System.out.printf("\t\t\t|%-10s|%10s|%13.2f  |%n","  "+tempSize[i]+"  ","  "+sizesQty[i]+"   ",(double)sizesQty[i]*(tempSize[i].equals("XS")? xs:tempSize[i].equals("S")? s:tempSize[i].equals("M")? m:tempSize[i].equals("L")? l:tempSize[i].equals("XL")? xl:tempSize[i].equals("XXL")? xxl:0.00));
                System.out.println("\t\t\t|          |          |               |");
            }

            System.out.println("\t\t\t+----------+----------+---------------+");
            double tot = 0;
            for (int i = 0; i < sizesQty.length; i++){
                tot+= (double)sizesQty[i]*(tempSize[i].equals("XS")? xs:tempSize[i].equals("S")? s:tempSize[i].equals("M")? m:tempSize[i].equals("L")? l:tempSize[i].equals("XL")? xl:tempSize[i].equals("XXL")? xxl:0.00);
            }

            System.out.printf("\t\t\t|%-21s|%13.2f  |%n","  Total Amount",tot);
            System.out.println("\t\t\t+---------------------+---------------+");

            do{
                System.out.print("\n\n\tDo you want to search another customer report? (y/n) : ");
                String yn = input.next().toLowerCase();
                if (yn.equals("y")){
                    clearConsole();
                    searchCustomer();
                }else if (yn.equals("n")){
                    clearConsole();
                    home();
                }


            } while (true);
        }
        System.out.println("\n\t\tInvalid input..");
        do{
            System.out.print("\n\n\tDo you want to search another customer report? (y/n) : ");
            String yn = input.next().toLowerCase();
            if (yn.equals("y")){
                clearConsole();
                searchCustomer();
            }else if (yn.equals("n")){
                clearConsole();
                home();
            }
        } while (true);

    }

    //Search the order
    public static void searchOrder() {
        L1:do{
            System.out.print("\n\tEnter order ID : ");
            String id = input.next();

            if (!isValidOrderId(id)){
                for (int i = 0; i < orders.length; i++){
                    if (orders[i].getOrderId().equals(id)){
                        System.out.println("\n\tPhone Number : "+orders[i].getCustomerId());
                        System.out.println("\tSize         : "+orders[i].getSize());
                        System.out.println("\tQTY          : "+orders[i].getQty());
                        System.out.printf("\tAmount       : %.2f%n",orders[i].getAmount());
                        System.out.println("\tStatus       : "+(orders[i].getStatus()=="PROCESSING" ? "PROCESSING" : orders[i].getStatus()=="DELIVERING" ? "DELIVERING" : "DELIVERED")+"\n");

                        do{
                            System.out.print("\n\tDo you want to search another order? (y/n) : ");
                            String yesOrNo = input.next();

                            yesOrNo = yesOrNo.toLowerCase();
                            if (yesOrNo.equals("y")){
                                clearConsole();
                                searchOrder();
                            }else if (yesOrNo.equals("n")){
                                clearConsole();
                                home();
                            }else{
                                System.out.println("\n\t\tInvalid input..");
                            }
                        } while (true);
                    }
                }

            }else{
                do{
                    System.out.println("\n\t\tInvalid ID..");
                    System.out.print("\n\tDo you want to search another order? (y/n) : ");
                    String yesOrNo = input.next();

                    yesOrNo = yesOrNo.toLowerCase();
                    if (yesOrNo.equals("y")){
                        clearConsole();
                        searchOrder();
                    }else if (yesOrNo.equals("n")){
                        clearConsole();
                        home();
                    }else{
                        System.out.println("\n\t\tInvalid input..");
                    }
                } while (true);
            }
        } while (true);

    }

    //Best in customers
    public static void bestInCustomers(){
        Orders[] tempCustomerIdArray=new Orders[orders.length];
        Orders[] tempOrderArray=new Orders[0];
        for(int i=0;i<orders.length;i++){
            tempCustomerIdArray[i]=orders[i];
        }

        for(int i=0;i<orders.length;i++){
            for(int j=0;j<orders.length;j++){
                if(orders[i].getCustomerId().equals(tempCustomerIdArray[j].getCustomerId())){
                    extendArray();
                    tempOrderArray[tempOrderArray.length-1]=orders[i];
                }
            }
        }
    }

    //View Customers
    public static void viewCustomers(){}

    //All customers' Reports
    public static void allcustomersReport(){}

    //Customer's Report
    public static void customerReports(){
        System.out.println("\t\t+---------------+-------+-------+-------+-------+-------+-------+---------------+");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\tCustomers Reports");
        System.out.println("\t\t+---------------+-------+-------+-------+-------+-------+-------+---------------+");
        System.out.println("\n\t\t[1] Best in Customers\t\t\t\t");
        System.out.println("\n\t\t[2] View Reports\t\t\t\t");
        System.out.println("\n\t\t[3] All Customer Reports\t\t\t\t");
        System.out.print("\n\t\tInput Option :");

        do {
            int option = input.nextInt();
            switch (option) {
                case (1):
                    bestInCustomers();
                    break;
                case (2):
                    viewCustomers();
                    break;
                case (3):
                    allcustomersReport();
                    break;
                default:
                    System.out.println("Invalid Input......!!");
            }
        }while (true);
    }

    //Sort quantity
    public static void sortbyQty(){
        boolean isAvailable = false;
        int[] sizesQty = new int[6];
        String[] tempSize = {"XS","S","M","L","XL","XXL"};

        Orders []tempOrderArray=new Orders[orders.length];
        for(int i=0;i<orders.length;i++){
            tempOrderArray[i]=orders[i];
        }

        for (int i = 0; i < orders.length; i++){
            for(int j=0;j<orders.length-1;j++){
                if (orders[i].getSize().equals(orders[j].getSize())){
                    isAvailable = true;
                    switch (orders[i].getSize()){
                        case "XS": sizesQty[0]+=orders[i].getQty();break;
                        case "S": sizesQty[1]+=orders[i].getQty();break;
                        case "M": sizesQty[2]+=orders[i].getQty();break;
                        case "L": sizesQty[3]+=orders[i].getQty();break;
                        case "XL": sizesQty[4]+=orders[i].getQty();break;
                        case "XXL": sizesQty[5]+=orders[i].getQty();break;
                    }
            }
        }
    }

        if (isAvailable){
            for (int i = 0; i < sizesQty.length-1; i++){
                for (int j = 0; j < sizesQty.length-1; j++){
                    String size = tempSize[j];
                    int qty = sizesQty[j];
                    if (qty<sizesQty[j+1]){
                        tempSize[j] = tempSize[j+1];
                        tempSize[j+1] = size;
                        sizesQty[j] = sizesQty[j+1];
                        sizesQty[j+1] = qty;
                    }
                }
            }

            System.out.println("\n\t\t\t+----------+----------+---------------+");
            System.out.printf("\t\t\t|%10s|%10s|%15s|%n","   Size   ","   QTY    ","     Amount    ");
            System.out.println("\t\t\t+----------+----------+---------------+");
            System.out.println("\t\t\t|          |          |               |");
            for (int i = 0; i < sizesQty.length; i++){
                System.out.printf("\t\t\t|%-10s|%10s|%13.2f  |%n","  "+tempSize[i]+"  ","  "+sizesQty[i]+"   ",(double)sizesQty[i]*(tempSize[i].equals("XS")? xs:tempSize[i].equals("S")? s:tempSize[i].equals("M")? m:tempSize[i].equals("L")? l:tempSize[i].equals("XL")? xl:tempSize[i].equals("XXL")? xxl:0.00));
                System.out.println("\t\t\t|          |          |               |");
            }

            System.out.println("\t\t\t+----------+----------+---------------+");
            double tot = 0;
            for (int i = 0; i < sizesQty.length; i++){
                tot+= (double)sizesQty[i]*(tempSize[i].equals("XS")? xs:tempSize[i].equals("S")? s:tempSize[i].equals("M")? m:tempSize[i].equals("L")? l:tempSize[i].equals("XL")? xl:tempSize[i].equals("XXL")? xxl:0.00);
            }



            System.out.print("To access the main,please enter 0:");
            int option=input.nextInt();
            do {
                switch (option) {
                    case 0: {
                        home();
                    }
                }
            }while(true);
        }
    }

    //Sort Amount
    public static void sortbyAmount(){
        boolean isAvailable = false;
        int[] sizesQty = new int[6];
        String[] tempSize = {"XS","S","M","L","XL","XXL"};
        double[] totAmount=new double[6];

        Orders []tempOrderArray=new Orders[orders.length];
        for(int i=0;i<orders.length;i++){
            tempOrderArray[i]=orders[i];
        }

        for (int i = 0; i < orders.length; i++){
            for(int j=0;j<orders.length-1;j++){
                if (orders[i].getSize().equals(orders[j].getSize())){
                    isAvailable = true;
                    switch (orders[i].getSize()){
                        case "XS": sizesQty[0]+=orders[i].getQty();break;
                        case "S": sizesQty[1]+=orders[i].getQty();break;
                        case "M": sizesQty[2]+=orders[i].getQty();break;
                        case "L": sizesQty[3]+=orders[i].getQty();break;
                        case "XL": sizesQty[4]+=orders[i].getQty();break;
                        case "XXL": sizesQty[5]+=orders[i].getQty();break;
                    }
                }
            }
        }
        for(int i=0;i<orders.length-1;i++){
                switch (orders[i].getSize()){
                    case "XS":  totAmount[0]+= sizesQty[0]*xs;break;
                    case "S":   totAmount[1]+= sizesQty[1]*s;break;
                    case "M":   totAmount[2]+= sizesQty[2]*m;break;
                    case "L":   totAmount[3]+= sizesQty[3]*l;break;
                    case "XL":  totAmount[4]+= sizesQty[4]*xl;break;
                    case "XXL": totAmount[5]+= sizesQty[5]*xxl;break;
                }
            }

        if (isAvailable){
            for (int i = 0; i < sizesQty.length-1; i++){
                for (int j = 0; j < sizesQty.length-1; j++){
                    String size = tempSize[j];
                    int qty = sizesQty[j];
                    double amount=totAmount[j];
                    if (amount<totAmount[j+1]){
                        totAmount[j]=totAmount[j+1];
                        totAmount[j+1]=amount;
                        tempSize[j] = tempSize[j+1];
                        tempSize[j+1] = size;
                        sizesQty[j] = sizesQty[j+1];
                        sizesQty[j+1] = qty;
                    }
                }
            }

            System.out.println("\n\t\t\t+----------+----------+---------------+");
            System.out.printf("\t\t\t|%10s|%10s|%15s|%n","   Size   ","   QTY    ","     Amount    ");
            System.out.println("\t\t\t+----------+----------+---------------+");
            System.out.println("\t\t\t|          |          |               |");
            for (int i = 0; i < sizesQty.length; i++){
                System.out.printf("\t\t\t|%-10s|%10s|%13.2f  |%n","  "+tempSize[i]+"  ","  "+sizesQty[i]+"   ",(double)sizesQty[i]*(tempSize[i].equals("XS")? xs:tempSize[i].equals("S")? s:tempSize[i].equals("M")? m:tempSize[i].equals("L")? l:tempSize[i].equals("XL")? xl:tempSize[i].equals("XXL")? xxl:0.00));
                System.out.println("\t\t\t|          |          |               |");
            }

            System.out.println("\t\t\t+----------+----------+---------------+");
            double tot = 0;
            for (int i = 0; i < sizesQty.length; i++){
                tot+= (double)sizesQty[i]*(tempSize[i].equals("XS")? xs:tempSize[i].equals("S")? s:tempSize[i].equals("M")? m:tempSize[i].equals("L")? l:tempSize[i].equals("XL")? xl:tempSize[i].equals("XXL")? xxl:0.00);
            }

            System.out.print("To access the main,please enter 0:");
            int option=input.nextInt();
            do {
                switch (option) {
                    case 0: {
                        home();
                    }
                }
            }while(true);



        }

    }

    //Item Reports
    public static void itemReports(){
        System.out.println("\t\t+---------------+-------+-------+-------+-------+-------+-------+---------------+");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\tItem Reports");
        System.out.println("\t\t+---------------+-------+-------+-------+-------+-------+-------+---------------+");
        System.out.println("\n\t\t[1] Best Selling Categories sorted by Qty\t\t\t\t");
        System.out.println("\n\t\t[2] Best Selling Categories sorted by Amount\t\t\t\t");
        System.out.print("\n\t\tInput Option :");

        do {
            int option = input.nextInt();
            switch (option) {
                case (1):
                    sortbyQty();
                    break;
                case (2):
                    sortbyAmount();
                    break;
                default:
                    System.out.println("Invalid Input......!!");
            }
        }while (true);
    }

    //All Orders
    public static void allOrders() {
        System.out.println("\n\t+--------------+---------------+---------------+---------------+-------------------+-------------------+");
        System.out.printf("\t|%10s|%10s|%10s|%10s|%10s|%15s|%n","  Order ID    "," Customer ID  ","  Size  ","   QTY    ","     Amount    ","   Status   ");
        System.out.println("\t+--------------+---------------+---------------+---------------+-------------------+-------------------+");

        for (int i = 0; i < orders.length; i++) {
            System.out.printf("\t|\t"+ orders[i].getOrderId() + "\t|\t"+orders[i].getCustomerId() + "\t|\t"+orders[i].getSize()+ "\t|\t" +orders[i].getQty()+ "\t|\t" +orders[i].getAmount()+ "\t|\t" +orders[i].getStatus()+"\t|\t\n");
        }



        System.out.println("\t+--------------+---------------+---------------+---------------+-------------------+-------------------+");


    }

    //Orders Amount by descending order
    public static void ordersByAmount() {
        System.out.println("+-------------------------------------------------+");
        System.out.printf("|%50s|%n","      Orders by Amount                     ");
        System.out.println("+-------------------------------------------------+");

        Orders[] tempArray = new Orders[orders.length];
        for (int i = 0; i < orders.length; i++){
            tempArray[i]=orders[i];
        }
        for(int i=orders.length-1; i>0; i--){
            for(int j=0; j<i; j++){
                Orders o1=tempArray[j];
                Orders o2=tempArray[j+1];
                if(o1 != null && o2 != null && o2.getAmount()>o1.getAmount()){
                    Orders temp=tempArray[j];
                    tempArray[j]=tempArray[j+1];
                    tempArray[j+1]=temp;
                }
            }
        }
        System.out.println();
        System.out.println("\n\t+---------------+---------------+----------+--------+-------------+-----------------+");
        System.out.printf("\t|%15s|%15s|%12s|%12s|%10s|%15s|%n","  Order ID   ","Customer ID","   Size   ","   QTY    ","     Amount    ","   Status   ");
        System.out.println("\t+---------------+---------------+----------+--------+-------------+-----------------+");
        for (int i = 0; i < orders.length; i++) {
            System.out.println("\t|\t" + tempArray[i].getOrderId() + "\t|\t" + tempArray[i].getCustomerId() +"\t|\t" +tempArray[i].getSize()+ "\t\t|\t" +tempArray[i].getQty()+"\t\t|\t" + "\t|\t" +tempArray[i].getStatus()+"\t|" );
        }

        System.out.println("\n\t+---------------+---------------+----------+--------+-------------+-----------------+");
    }

    //Orders Reports
    public static void ordersReports(){
        System.out.println("\t\t+---------------+-------+-------+-------+-------+-------+-------+---------------+");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\tOrder Reports");
        System.out.println("\t\t+---------------+-------+-------+-------+-------+-------+-------+---------------+");
        System.out.println("\n\t\t[1] All Orders\t\t\t\t");
        System.out.println("\n\t\t[2] Orders by Amount\t\t\t\t");
        System.out.print("\n\t\tInput Option :");

        do {
            int option = input.nextInt();
            switch (option) {
                case (1):
                    allOrders();
                    break;
                case (2):
                    ordersByAmount();
                    break;
                default:
                    System.out.println("Invalid Input......!!");
            }
        }while (true);
    }

    //View Reports

    public static void viewReports() {
        System.out.println("\t\t+---------------+-------+-------+-------+-------+-------+-------+---------------+");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\tReports");
        System.out.println("\t\t+---------------+-------+-------+-------+-------+-------+-------+---------------+");
        System.out.println("\n\t\t[1] Customers Reports\t\t\t\t");
        System.out.println("\n\t\t[2] Item Reports\t\t\t\t");
        System.out.println("\n\t\t[3] Orders Reports\t\t\t\t");
        System.out.print("\n\t\tInput Option :");

        do {
            int option = input.nextInt();
            switch (option) {
                case (1):
                    customerReports();
                    break;
                case (2):
                    itemReports();
                    break;
                case (3):
                    ordersReports();
                    break;
                default:
                    System.out.println("Invalid Input......!!");
            }
        }while (true);
    }

    //Change Order Status
    public static void changeOrderStatus() {

        L1:do{
            Scanner input = new Scanner(System.in);
            System.out.print("\n\tEnter order ID : ");
            String id = input.nextLine();

            if (!isValidOrderId(id)){
                for (int i = 0; i < orders.length; i++){
                    if (orders[i].getOrderId().equals(id)) {
                        System.out.println("\n\tPhone Number : " + orders[i].getCustomerId());
                        System.out.println("\tSize         : " + orders[i].getSize());
                        System.out.println("\tQTY          : " + orders[i].getQty());
                        System.out.printf("\tAmount       : %.2f%n", orders[i].getAmount());
                        System.out.println("\tStatus       : " + (orders[i].getStatus() == "PROCESSING" ? "PROCESSING" : orders[i].getStatus() == "DELIVERING" ? "DELIVERING" : "DELIVERED") + "\n");
                    }
                        do{
                            System.out.print("\n\tDo you want to change this order status? (y/n):");
                            String yn=input.nextLine();
                            yn = yn.toLowerCase();

                            switch (yn){
                                case "y": {
                                    if(orders[i].getStatus()=="PROCESSING"){
                                        System.out.println("\n\t\t[1]Order Delivering");
                                        System.out.println("\n\t\t[2]Order Delivered");

                                        System.out.println("\n\tEnter Option:");
                                        int option=input.nextInt();

                                        switch (option){
                                            case 1:{
                                                orders[i].setStatus(1);
                                            }
                                            case 2:{
                                                orders[i].setStatus(2);
                                            } default:{
                                                System.out.println("Invalid Input...");
                                            }
                                        }
                                    } else if (orders[i].getStatus()=="DELIVERING") {
                                        System.out.println("\n\t\t[1]Order Delivered");
                                        System.out.println("\n\tEnter Option:");
                                        int option=input.nextInt();
                                        switch (option){
                                            case 1:{
                                                orders[i].setStatus(2);
                                            }default:{
                                                System.out.println("Invalid Input...");
                                            }
                                        }

                                    } else if (orders[i].getStatus()=="DELIVERED") {
                                        System.out.println("\n\t\tCan't change this order status,Order already delivered..!");
                                    }else{
                                        System.out.println("\n\t\tInvalid input..Try again");
                                    }
                                    System.out.print("\n\tDo you want to change another order status? (y/n) : ");
                                    String yn_1 = input.next();

                                    yn_1 = yn_1.toLowerCase();
                                    if (yn_1.equals("y")){
                                        clearConsole();
                                        changeOrderStatus();
                                    }else if (yn_1.equals("n")){
                                        clearConsole();
                                        home();
                                    }else{
                                        System.out.println("\n\t\tInvalid input..");
                                    }
                                } case "n":{
                                    home();
                                } default:{
                                    System.out.println("\n\t\tInvalid input..");
                                }
                            }
                        } while (true);
                }

            }else{
                do{
                    System.out.println("\n\t\tInvalid ID..");
                    System.out.print("\n\tDo you want to change another order status? (y/n) : ");
                    String yn_1 = input.next();

                    yn_1 = yn_1.toLowerCase();
                    if (yn_1.equals("y")){
                        clearConsole();
                        changeOrderStatus();
                    }else if (yn_1.equals("n")){
                        clearConsole();
                        home();
                    }else{
                        System.out.println("\n\t\tInvalid input..");
                    }
                } while (true);
            }
        } while (true);

    }

    //Delete a Order
    public static void deleteOrder() {
        do{
            Scanner input = new Scanner(System.in);
            System.out.print("\n\tEnter order ID : ");
            String id = input.nextLine();

            if (!isValidOrderId(id)) {
                for (int i = 0; i < orders.length; i++) {
                    if (orders[i].getOrderId().equals(id)) {
                        System.out.println("\n\tPhone Number : " + orders[i].getCustomerId());
                        System.out.println("\tSize         : " + orders[i].getSize());
                        System.out.println("\tQTY          : " + orders[i].getQty());
                        System.out.printf("\tAmount       : %.2f%n", orders[i].getAmount());
                        System.out.println("\tStatus       : " + (orders[i].getStatus() == "PROCESSING" ? "PROCESSING" : orders[i].getStatus() == "DELIVERING" ? "DELIVERING" : "DELIVERED") + "\n");
                    }
                    System.out.print("\n\tDo you want to delete this order? (y/n):");
                    String yn = input.nextLine();
                    yn = yn.toLowerCase();

                    switch (yn) {
                        case "y": {
                            for (int j = i; j <orders.length-1; j++){
                                orders[j]=orders[j+1];
                            }
                            Orders[] temArray=new Orders[orders.length-1];
                            for (int j = i; j <orders.length-1; j++){
                                temArray[j]=orders[j];
                            }
                            orders=temArray;
                            System.out.println("\n\t\tOrder deleted...");
                            do {
                                System.out.print("\n\tDo you want to change another order status? (y/n) : ");
                                String yn_1 = input.next();
                                yn_1 = yn_1.toLowerCase();

                                switch (yn_1){
                                    case "y":{
                                        clearConsole();
                                        deleteOrder();
                                    }
                                    case "n":{
                                        clearConsole();
                                        home();
                                    } default:{
                                        System.out.println("\n\t\tInvalid input..");
                                    }

                                }
                            } while (true);
                        } case "n":{
                            home();
                        }
                    }
                }
            }else {
                do {
                    System.out.println("\n\t\tInvalid ID...");
                    System.out.print("\n\tDo you want to delete another order? (y/n) : ");
                    String yn_1 = input.next();
                    yn_1 = yn_1.toLowerCase();

                    switch (yn_1){
                        case "y":{
                            clearConsole();
                            deleteOrder();
                        }
                        case "n":{
                            clearConsole();
                            home();
                        } default:{
                            System.out.println("\n\t\tInvalid input..");
                        }

                    }
                } while (true);
            }
        }while(true);
    }

    //Main method

    public static void main(String[] args) {
        home();
    }

    //Clear the Console
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }
}
//ICM104357