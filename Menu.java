import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    protected static ArrayList<MenuItem> starters = new ArrayList<MenuItem>();
    protected static ArrayList<MenuItem> mainCourse = new ArrayList<MenuItem>();
    protected static ArrayList<MenuItem> desserts = new ArrayList<MenuItem>();
    protected static ArrayList<MenuItem> drinks = new ArrayList<MenuItem>();

    public static void readMenu(String category, ArrayList<MenuItem> list, boolean printNecessary) {
        try {

            Scanner sc = new Scanner(new FileReader(category + ".txt"));
            list.clear();

            while (sc.hasNextLine()) {
                int id = Integer.parseInt(sc.next());
                String name = sc.next();
                double price = Double.parseDouble(sc.next());
                String description = sc.nextLine();
                MenuItem m = new MenuItem(name, description, price, id, category);
                list.add(m);
                if (printNecessary)
                    System.out.println(m);
            }

            sc.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void printMenu() {
        System.out.println("Menu:");
        System.out.println();
        System.out.println("Items are in the format:");
        System.out.println("<ID> <Item Name> <ItemPrice>");
        System.out.println("Starters:");
        readMenu("Starters", starters, true);
        System.out.println("Main Course:");
        readMenu("MainCourse", mainCourse, true);
        System.out.println("Desserts:");
        readMenu("Desserts", desserts, true);
        System.out.println("Drinks:");
        readMenu("Drinks", drinks, true);

    }

    protected static void addItem(String name, String description, double price, int id, String category) {
        ArrayList<MenuItem> cat = null;
        if (category.equals("Starters")) {
            cat = starters;
        }

        if (category.equals("MainCourse")) {
            cat = mainCourse;
        }

        if (category.equals("Desserts")) {
            cat = desserts;
        }

        if (category.equals("Drinks")) {
            cat = drinks;
        }
        MenuItem newItem = new MenuItem(name, description, price, id, category);

        cat.add(newItem);

        updateFile(category, cat);

    }

    public static MenuItem getItem(int id) {
        int cat = id % 10;
        int pos = id / 10 - 1;

        try {
            switch (cat) {
            case 0:
                return starters.get(pos);
            case 1:
                return mainCourse.get(pos);
            case 2:
                return desserts.get(pos);
            case 3:
                return drinks.get(pos);
            default:
                System.out.println("Enter Correct ID");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    protected static void changePrice(int id, double newPrice) {
        int cat = id % 10;
        int pos = id / 10 - 1;

        try {
            switch (cat) {
            case 0:
                starters.get(pos).setPrice(newPrice);
                updateFile("Starters", starters);
                break;
            case 1:
                mainCourse.get(pos).setPrice(newPrice);
                updateFile("MainCourse", mainCourse);
                break;
            case 2:
                desserts.get(pos).setPrice(newPrice);
                updateFile("Desserts", desserts);
                break;
            case 3:
                drinks.get(pos).setPrice(newPrice);
                updateFile("Drinks", drinks);
                break;
            default:
                System.out.println("Enter Correct ID");

            }
        } catch (Exception e) {
            System.out.println("Enter Correct ID");

        }

    }

    public static void updateFile(String category, ArrayList<MenuItem> list) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(category + ".txt", false));

            for (int i = 0; i < list.size(); i++) {
                MenuItem p = list.get(i);
                pw.println(p.id + " " + p.name + " " + p.getPrice() + " " + p.getDescription());
            }

            pw.flush();
            pw.close();
        }

        catch (Exception e) {
            System.out.println(e);
        }
    }

}
