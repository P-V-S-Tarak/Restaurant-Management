import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Admin extends Menu {

    public static boolean verifyPassword(String username, String password) {
        String cur = "";
        String cp = "";
        try {
            Scanner ur = new Scanner(new FileReader("C:\\Users\\pvsta\\Restaurant_Management_System\\Usernaname.txt"));
            cur = ur.nextLine();
            ur.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Scanner pr = new Scanner(new FileReader("C:\\Users\\pvsta\\Restaurant_Management_System\\Password.txt"));
            cp = pr.nextLine();
            pr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (cur.equals(username) && cp.equals(password))
            return true;
        else
            return false;

    }

    public static boolean changePassword(String username, String oldPassword, String newPassword) {
        if (verifyPassword(username, oldPassword)) {
            try {
                PrintWriter pw = new PrintWriter(
                        new FileWriter("C:\\Users\\pvsta\\Restaurant_Management_System\\Password.txt", false));
                pw.println(newPassword);
                pw.flush();
                pw.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    public static int adminMenu() {
        Scanner sca = new Scanner(System.in);
        int ch = -1;

        while (ch != 5) {
            try {

                System.out.println("Admin Menu");
                System.out.println("1. Add item to Menu.");
                System.out.println("2. Change Price of Menu Item.");
                System.out.println("3. Change Password.");
                System.out.println("4. View Revenue. ");
                System.out.println("5. Logout.");
                System.out.println("6. Close Restaurant.");

                System.out.println();
                System.out.println("Enter your choice.");

                ch = Integer.parseInt(sca.next());

                try {
                    switch (ch) {
                    case 1:
                        Menu.printMenu();
                        System.out.println("Enter Item name. The name should conatin NO spaces.");
                        sca.nextLine();
                        String name = sca.nextLine();
                        for (int i = 0; i < name.length(); i++) {
                            if (name.charAt(i) == ' ')
                                throw new Exception();
                        }
                        String s = "";
                        int id;
                        while (true) {
                            System.out.println("Choose item Category");
                            System.out.println("1.Starters");
                            System.out.println("2.MainCourse");
                            System.out.println("3.Desserts");
                            System.out.println("4.Drinks");

                            int a = sca.nextInt();

                            switch (a) {
                            case 1:
                                s = "Starters";
                                id = (starters.size() + 1) * 10;
                                for (int i = 0; i < starters.size(); i++) {
                                    System.out.println(starters.get(i));
                                }
                                break;
                            case 2:
                                s = "MainCourse";
                                id = (mainCourse.size() + 1) * 10 + 1;
                                break;
                            case 3:
                                s = "Desserts";
                                id = (desserts.size() + 1) * 10 + 2;
                                break;
                            case 4:
                                s = "Drinks";
                                id = (drinks.size() + 1) * 10 + 3;
                                break;
                            default:
                                throw new Exception();
                            }
                            break;
                        }

                        System.out.println("Enter price");
                        double price = Double.parseDouble(sca.next());

                        System.out.println("Enter Description");
                        sca.nextLine();
                        String desc = sca.nextLine();

                        Menu.addItem(name, desc, price, id, s);
                        break;

                    case 2: {
                        Menu.printMenu();
                        System.out.println("Please Enter ID of the item");
                        int menuid = Integer.parseInt(sca.next());
                        System.out.println("Enter new Price");
                        double newPrice = Double.parseDouble(sca.next());
                        Menu.changePrice(menuid, newPrice);

                    }
                        break;

                    case 3: {
                        System.out.println("Enter your Username:");
                        sca.nextLine();
                        String username = sca.nextLine();
                        System.out.println("Enter your current password:");

                        String oldPassword = sca.nextLine();
                        System.out.println("Enter new Password:");

                        String newPassword = sca.nextLine();

                        if (changePassword(username, oldPassword, newPassword)) {
                            System.out.println("Password changed!\n");

                        } else
                            System.out.println("Error! Please Try Again\n");

                    }
                        break;

                    case 4:
                        System.out.print("Revenue is: ");
                        Restaurant.getRevenue(true);
                        break;

                    case 5:
                        return 0;
                    case 6:
                        Restaurant.setRestaurantState("CLOSED");
                        System.exit(0);
                    default:
                        throw new Exception();

                    }

                } catch (Exception e) {
                    System.out.println("Please Enter correct Input\n");
                    continue;
                }

            } catch (Exception e) {
                System.out.println("Please Enter correct Input!");
                continue;
            }
        }

        sca.close();
        return 0;

    }

}
