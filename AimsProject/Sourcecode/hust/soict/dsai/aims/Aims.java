package hust.soict.dsai.aims;

import java.util.Scanner;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Khởi tạo dữ liệu mẫu cho Store
        initData();
        
        int choice;
        while (true) {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: viewStore(); break;
                case 2: updateStore(); break;
                case 3: viewCart(); break;
                case 0: 
                    System.out.println("Goodbye!");
                    System.exit(0);
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // --- CÁC HÀM HIỂN THỊ MENU THEO MẪU TÀI LIỆU ---
    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: ");
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: ");
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: ");
    }

    // --- LOGIC XỬ LÝ CHI TIẾT ---

    public static void viewStore() {
        store.printStore();
        while (true) {
            storeMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                Media m = store.search(title);
                if (m != null) {
                    System.out.println(m.toString());
                    // Sub-menu for details
                    System.out.println("1. Add to cart | 2. Play (if available) | 0. Back");
                    int subChoice = scanner.nextInt();
                    if (subChoice == 1) cart.addMedia(m);
                    else if (subChoice == 2 && m instanceof Playable) ((Playable) m).play();
                } else System.out.println("Media not found.");
            } 
            else if (choice == 2) {
                System.out.print("Enter title: ");
                Media m = store.search(scanner.nextLine());
                if (m != null) cart.addMedia(m);
            }
            else if (choice == 3) {
                Media m = store.search(scanner.nextLine());
                if (m instanceof Playable) ((Playable) m).play();
            }
            else if (choice == 4) viewCart();
            else if (choice == 0) break;
        }
    }

    public static void viewCart() {
        cart.print();
        while (true) {
            cartMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) { /* Filter logic (searchById/Title) */ }
            else if (choice == 2) {
                System.out.println("1. Sort by Title | 2. Sort by Cost");
                if (scanner.nextInt() == 1) cart.sortByTitle(); else cart.sortByCost();
                cart.print();
            }
            else if (choice == 3) {
                System.out.print("Enter title: ");
                Media m = store.search(scanner.nextLine()); // Tạm tìm từ store để lấy object
                cart.removeMedia(m);
            }
            else if (choice == 5) {
                System.out.println("Order has been created!");
                cart = new Cart(); // Clear cart
                break;
            }
            else if (choice == 0) break;
        }
    }

    public static void updateStore() {
        System.out.println("1. Add Media | 2. Remove Media");
        // Logic thêm/xóa sản phẩm vào Store tùy chọn
    }

    private static void initData() {
        store.addMedia(new DigitalVideoDisc("Sci-Fi", "Inception", 25.0f));
        store.addMedia(new Book(2, "Java Design Patterns", "Education", 40.0f));
        CompactDisc cd = new CompactDisc(3, "Thriller", "Pop", 15.0f, "John Landis", 0, "Michael Jackson");
        cd.addTrack(new Track("Billie Jean", 5));
        store.addMedia(cd);
    }
}