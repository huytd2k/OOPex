import java.util.Scanner;

public class Program {
    static private int option;
    private static Library library;
    public static void main(String args[]) {
        Program.library = new Library("My Library");
        showMenu();
        Scanner sc = new Scanner(System.in);
        while(option != 6) {
            switch(option) {
                case 1: {
                    Program.library.showLibraryInfo();
                    showMenu();
                    break;
                }
                case 2: {
                    Program.library.addNewBook();
                    showMenu();
                    break;
                }
                case 3: {
                    System.out.println("Id or title:");
                    String query = sc.nextLine();
                    if(tryParseInt(query) != -1) {
                        int q = tryParseInt(query);
                        Program.library.findBook(q);
                        showMenu();
                        break;
                    }
                    Program.library.findBook(query);
                    showMenu();
                    break;
                }
                case 4: {
                    int id = askId();
                    Program.library.borrowBook(id);
                    showMenu();
                    break;
                }
                case 5: {
                    int id = askId();
                    Program.library.returnBook(id);
                    showMenu();
                    break;
                }
                case 6: {
                    System.out.println("Thank you! See you again!");
                    System.exit(1);
                    break;
                }
                default:
                    System.out.println("Wrong option !");
                    showMenu();
            }
        }
    }
    public static void showMenu() {
        System.out.println("======== Main menu =========");
        System.out.println("1. Show library information");
        System.out.println("2. Add new book");
        System.out.println("3. Find book");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a book");
        System.out.println("6.Exit");
        System.out.println("============================");
        askOption();


    }

    public static void askOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu ID (1-6): ");
        int opt = 0;
        try {
            opt = scanner.nextInt();
        }catch(Exception exp) {
            System.out.println("Invalid option!");
            askOption();
        }
        Program.setOption(opt);
        return;
    }

    public static int askId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book'id:");
        int opt = 0;
        try {
            opt = scanner.nextInt();
        }catch(Exception exp) {
            System.out.println("Invalid option!");
        }
        return opt;
    }
    public static int tryParseInt(String i) {
        try {
            return Integer.parseInt(i);
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getOption() {
        return option;
    }

    public static void setOption(int option) {
        Program.option = option;
    }
}
