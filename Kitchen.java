
import java.io.FileWriter;
import java.io.PrintWriter;

public class Kitchen {

    synchronized public static void starterUnit(OrderItem i, int orderNo) {

        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Servings.txt", true));

            i.setCookState("Preparing");
            Thread.sleep(20000 * (i.getItemQuantity() / 4+1));
            i.setCookState("Served");
            pw.println("Order Number: " + orderNo + " " + i.name + " Served");
            pw.flush();
            pw.close();

        } catch (Exception e) {
            System.out.println("Problem in Starter Unit");
        }

    }

    synchronized public static void dessertUnit(OrderItem i, int orderNo) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Servings.txt", true));
            i.setCookState("Preparing");
            Thread.sleep(15000 * (i.getItemQuantity() / 4+1));
            i.setCookState("Served");
            pw.println("Order Number: " + orderNo + " " + i.name + " Served");
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Problem in Dessert Unit");
        }

    }

    synchronized public static void drinkUnit(OrderItem i, int orderNo) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Servings.txt", true));
            i.setCookState("Preparing");
            Thread.sleep(5000 * (i.getItemQuantity() / 4+1));
            i.setCookState("Served");
            pw.println("Order Number: " + orderNo + " " + i.name + " Served");
            pw.flush();
            pw.close();

        } catch (Exception e) {
            System.out.println("Problem in Drink Unit");
        }

    }

    synchronized public static void mainCourseUnit(OrderItem i, int orderNo) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Servings.txt", true));
            i.setCookState("Preparing");
            Thread.sleep(25000 * (i.getItemQuantity() / 4+1));
            i.setCookState("Served");
            pw.println("Order Number: " + orderNo + " " + i.name + " Served");
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Problem in Main Course Unit\n");
        }

    }

}
