
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Scanner;

public class Restaurant {
    private static int tablesForTwo = 1;
    private static int tablesForFour = 0;
    private static int tablesForSix = 0;

    private static String restaurantState = "OPEN";
    public static ArrayList<Order> orderList = new ArrayList<Order>();

    private static double revenue;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            PrintWriter prtw = new PrintWriter(new FileWriter("Servings.txt"));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            prtw.println(dtf.format(now));
            prtw.flush();
            prtw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        getRevenue(false);

        while (true) {
            System.out.println("Welcome To the restaurant Smart Kitchen!");

            System.out.println("The Restaurant is " + restaurantState + ".");
            System.out.println();

            int choice = -1;

            while (choice != 3) {

                System.out.println("Please choose one of the following options to proceed further.");
                System.out.println();
                if (restaurantState.equals("FULL"))
                    System.out.println("Restaurant full . Please wait to book a table");
                else
                    System.out.println("To book a table enter 0");

                System.out.println("To view menu and place order enter 1");
                System.out.println("To check order state enter 2");
                System.out.println("To free table enter 3");
                System.out.println("For Admin Login enter 4");
                System.out.println();

                try {
                    choice = Integer.parseInt(sc.next());
                    if (choice >= 0 && choice <= 4)
                        break;
                    else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Incorrect Input! Please Try Again.\n");
                }
            }

            switch (choice) {
            case 0: {
                if (restaurantState.equals("FULL"))
                    System.out.println("Restaurant full . Please wait to book a table");
                else {
                    bookTable();
                    if (tablesForFour == 0 && tablesForSix == 0 && tablesForTwo == 0)
                        restaurantState = "FULL";
                }

            }
                break;

            case 1: {
                boolean cancelled = false;
                boolean ordered = false;
                Order order = new Order(orderList.size() + 1);
                int k = 0;

                while (!(cancelled || ordered)) {
                    Menu.printMenu();
                    System.out.println();
                    System.out.println("Please Enter ID of the Item to Order the Item");
                    System.out.println("Please enter 0 to complete the Order");
                    System.out.println("Please enter -2 to cancel the order");

                    try {

                        int entry = Integer.parseInt(sc.next());
                        if (entry == 0) {
                            if (k == 0) {
                                cancelled = true;
                                ordered = false;
                            } else
                                ordered = true;
                        } else if (entry == -2) {
                            cancelled = true;
                        } else {

                            MenuItem mi = Menu.getItem(entry);
                            System.out.println("Please Enter Item Quantity");
                            int itemQuantity = sc.nextInt();
                            if (itemQuantity <= 0)
                                throw new Exception();
                            else {
                                OrderItem oi = new OrderItem(mi, itemQuantity, mi.category);
                                order.addItem(oi);
                                k = 1;

                            }

                        }

                    } catch (Exception e) {
                        System.out.println("Please Enter Correct Input\n");
                    }

                }
                if (ordered) {
                    orderList.add(order);
                    order.printBill();
                    updateRevenue(order.getOrderPrice());

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    order.start();
                }

            }
                break;

            case 2: {
                int orderno = -1;
                while (orderno != 0) {
                    try {
                        System.out.println("Please Enter Order Number: ");
                        orderno = Integer.parseInt(sc.next());
                        orderList.get(orderno - 1).printState();
                        break;

                    } catch (Exception e) {
                        System.out.println("Please Enter correct input or enter 0 to exit\n");
                        continue;
                    }

                }

            }
                break;

            case 3: {
                while (true) {
                    System.out.println("Enter table size");
                    try {
                        int siz = Integer.parseInt(sc.next());
                        if (!(siz == 2 || siz == 4 || siz == 6)) {
                            throw new Exception();
                        }
                        freeTable(siz);
                        System.out.println("Thank you!");
                    } catch (Exception e) {
                        System.out.println("Enter correct input!\n");
                        continue;
                    }

                    break;

                }
            }
                break;

            case 4: {
                System.out.println("Please Enter Username");
                sc.nextLine();
                String username = sc.nextLine();
                System.out.println("Please Enter password");

                String password = sc.nextLine();

                if (Admin.verifyPassword(username, password)) {
                    Admin.adminMenu();
                } else
                    System.out.println("Incorrect Input\n");

            }

            }
            continue;

        }

    }

    private static boolean bookTable() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("Available Tables:");
            System.out.println("Size 2: " + tablesForTwo);
            System.out.println("Size 4: " + tablesForFour);
            System.out.println("Size 6: " + tablesForSix);
            System.out.println();
            System.out.println("To book a table enter the table size: ");
            System.out.println("Enter 0 to exit");

            try {

                choice = Integer.parseInt(sc.next());

                switch (choice) {
                case 0:
                    return false;

                case 2: {
                    if (tablesForTwo >= 1) {
                        tablesForTwo--;
                        System.out.println("Table for two booked\n");
                        return true;
                    } else {
                        System.out.println("Please wait or try with a different table size\n");
                        return false;
                    }
                }

                case 4: {
                    if (tablesForFour >= 1) {
                        tablesForFour--;
                        System.out.println("Table for four booked\n");
                        return true;
                    } else {
                        System.out.println("Please wait or try with a different table size\n");
                        return false;
                    }
                }

                case 6: {
                    if (tablesForSix >= 1) {
                        tablesForSix--;
                        System.out.println("Table for six booked\n");
                        return true;
                    } else {
                        System.out.println("Please wait or try with a different table size\n");
                        return false;
                    }
                }

                default:
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("Please Enter correct Table size!\n");
                continue;
            }

        }
        sc.close();

        return false;

    }

    private static void freeTable(int tableSize) {
        switch (tableSize) {
        case 2:
            if (restaurantState.equals("FULL"))
                restaurantState = "OPEN";
            tablesForTwo++;
            break;
        case 4:
            if (restaurantState.equals("FULL"))
                restaurantState = "OPEN";
            tablesForFour++;
            break;
        case 6:
            if (restaurantState.equals("FULL"))
                restaurantState = "OPEN";
            tablesForSix++;
            break;

        }

    }

    public static void getRevenue(boolean b) {
        try {
            Scanner ab = new Scanner(new FileReader("Revenue.txt"));
            revenue = Double.parseDouble(ab.next());
            if (b)
                System.out.println(revenue);
            ab.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

    }

    private static void updateRevenue(double orderPrice) {
        revenue += orderPrice;
        PrintWriter prw;
        try {
            prw = new PrintWriter(new FileWriter("revenue.txt", false));
            prw.println(revenue);
            prw.flush();
            prw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public static void setRestaurantState(String state) {
        restaurantState = state;
    }

}
