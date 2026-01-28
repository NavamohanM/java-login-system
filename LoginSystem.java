import java.io.*;
import java.util.*;

public class LoginSystem {

    static final String FILE_NAME = "users.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== User Login System ====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    register(sc);
                    break;
                case 2:
                    login(sc);
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }

    static void register(Scanner sc) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME, true);

            System.out.print("Enter username: ");
            String username = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            fw.write(username + "," + password + "\n");
            fw.close();

            System.out.println("✅ Registration successful!");

        } catch (IOException e) {
            System.out.println("❌ Error while registering user.");
        }
    }

    static void login(Scanner sc) {
        try {
            System.out.print("Enter username: ");
            String username = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            File file = new File(FILE_NAME);

            if (!file.exists()) {
                System.out.println("❌ No users registered yet.");
                return;
            }

            Scanner fileScanner = new Scanner(file);
            boolean found = false;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                if (parts[0].equals(username) && parts[1].equals(password)) {
                    found = true;
                    break;
                }
            }

            fileScanner.close();

            if (found) {
                System.out.println("✅ Login successful! Welcome " + username);
            } else {
                System.out.println("❌ Invalid username or password.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error during login.");
        }
    }
}
