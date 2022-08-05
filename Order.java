import java.util.ArrayList;

public class Order extends Thread {

    private ArrayList<OrderItem> starters = null;
    private ArrayList<OrderItem> mainCourse = null;
    private ArrayList<OrderItem> drinks = null;
    private ArrayList<OrderItem> desserts = null;
    private double totalPrice = 0;
    public final int orderNo;

    public Order(int orderNo) {
        starters = new ArrayList<OrderItem>();
        mainCourse = new ArrayList<OrderItem>();
        drinks = new ArrayList<OrderItem>();
        desserts = new ArrayList<OrderItem>();
        this.orderNo = orderNo;

    }

    public void addItem(OrderItem i) {
        if (i.category.equals("Starters")) {
            starters.add(i);

        }
        if (i.category.equals("MainCourse")) {
            mainCourse.add(i);

        }
        if (i.category.equals("Desserts")) {
            desserts.add(i);

        }
        if (i.category.equals("Drinks")) {
            drinks.add(i);

        }

    }

    public double getOrderPrice() {
        for (OrderItem i : starters) {
            totalPrice += i.price;

        }
        for (OrderItem i : desserts) {
            totalPrice += i.price;

        }
        for (OrderItem i : drinks) {
            totalPrice += i.price;

        }
        for (OrderItem i : mainCourse) {
            totalPrice += i.price;

        }

        return this.totalPrice;
    }

    @Override
    public void run() {

        if (orderNo > 1) {
            while (!Restaurant.orderList.get(orderNo - 2).isServed("Drinks"))
                ;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (OrderItem i : drinks) {
            Kitchen.drinkUnit(i, orderNo);
        }

        if (orderNo > 1) {
            while (!Restaurant.orderList.get(orderNo - 2).isServed("Starters"))
                ;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (OrderItem i : starters) {
            Kitchen.starterUnit(i, orderNo);
        }

        if (orderNo > 1) {
            while (!Restaurant.orderList.get(orderNo - 2).isServed("MainCourse"))
                ;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (OrderItem i : mainCourse) {
            Kitchen.mainCourseUnit(i, orderNo);
        }

        if (orderNo > 1) {
            while (!Restaurant.orderList.get(orderNo - 2).isServed("Desserts"))
                ;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (OrderItem i : desserts) {
            Kitchen.dessertUnit(i, orderNo);
        }
    }

    public boolean isServed(String category) {
        if (category.equals("Starters"))
            return isServed(starters);

        if (category.equals("MainCourse"))
            return isServed(mainCourse);

        if (category.equals("Drinks"))
            return isServed(drinks);

        if (category.equals("Desserts"))
            return isServed(desserts);

        return true;
    }

    private boolean isServed(ArrayList<OrderItem> list) {
        for (OrderItem i : list) {
            if (!(i.isServed()))
                return false;
        }
        return true;
    }

    public void printBill() {
        System.out.println("Bill:");
        System.out.println("Order Number: " + orderNo);

        for (OrderItem i : starters)
            System.out.println(
                    "Item Name: " + i.name + " Item quantity: " + i.getItemQuantity() + " Price: " + i.getPrice());
        for (OrderItem i : mainCourse)
            System.out.println(
                    "Item Name: " + i.name + " Item quantity: " + i.getItemQuantity() + " Price: " + i.getPrice());
        for (OrderItem i : drinks)
            System.out.println(
                    "Item Name: " + i.name + " Item quantity: " + i.getItemQuantity() + " Price: " + i.getPrice());
        for (OrderItem i : desserts)
            System.out.println(
                    "Item Name: " + i.name + " Item quantity: " + i.getItemQuantity() + " Price: " + i.getPrice());
        System.out.println();
        System.out.println("Total Price = " + getOrderPrice() + "\n");

    }

    public void printState() {
        for (OrderItem i : starters)
            System.out.println(i.name + " " + i.getCookState());
        for (OrderItem i : mainCourse)
            System.out.println(i.name + " " + i.getCookState());
        for (OrderItem i : drinks)
            System.out.println(i.name + " " + i.getCookState());
        for (OrderItem i : desserts)
            System.out.println(i.name + " " + i.getCookState());

    }

}
