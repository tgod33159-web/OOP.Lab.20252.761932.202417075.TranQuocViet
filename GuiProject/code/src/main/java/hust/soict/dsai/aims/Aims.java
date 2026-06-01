package hust.soict.dsai.aims;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.screen.StoreScreen;
import hust.soict.dsai.aims.store.Store;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initData();
        SwingUtilities.invokeLater(() -> new StoreScreen(store, cart));
    }

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

    public static void viewStore() {
        store.printStore();
        while (true) {
            storeMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                Media media = store.search(title);
                if (media != null) {
                    System.out.println(media.toString());
                } else {
                    System.out.println("Media not found.");
                }
            } else if (choice == 2) {
                System.out.print("Enter title: ");
                Media media = store.search(scanner.nextLine());
                if (media != null) {
                    cart.addMedia(media);
                }
            } else if (choice == 3) {
                System.out.print("Enter title: ");
                Media media = store.search(scanner.nextLine());
                playMedia(media);
            } else if (choice == 4) {
                viewCart();
            } else if (choice == 0) {
                break;
            }
        }
    }

    public static void viewCart() {
        cart.print();
        while (true) {
            cartMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 2) {
                System.out.println("1. Sort by Title | 2. Sort by Cost");
                if (scanner.nextInt() == 1) {
                    cart.sortByTitle();
                } else {
                    cart.sortByCost();
                }
                cart.print();
            } else if (choice == 3) {
                System.out.print("Enter title: ");
                Media media = store.search(scanner.nextLine());
                cart.removeMedia(media);
            } else if (choice == 4) {
                System.out.print("Enter title: ");
                Media media = store.search(scanner.nextLine());
                playMedia(media);
            } else if (choice == 5) {
                System.out.println("Order has been created!");
                cart.clear();
                break;
            } else if (choice == 0) {
                break;
            }
        }
    }

    public static void updateStore() {
        System.out.println("Use the GUI menu Options > Update store to add Book, CD, or DVD.");
    }

    private static void playMedia(Media media) {
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                System.err.println(e.getMessage());
                System.err.println(e.toString());
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Player error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("This media cannot be played.");
        }
    }

    private static void initData() {
        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f));
        store.addMedia(new DigitalVideoDisc("Aladdin", "Animation", "Ron Clements", 90, 18.99f));

        Book book = new Book(4, "Java Design Patterns", "Education", 40.0f);
        book.addAuthor("Erich Gamma");
        book.addAuthor("Richard Helm");
        store.addMedia(book);

        CompactDisc cd = new CompactDisc(5, "Thriller", "Pop", 15.0f, "John Landis", 0, "Michael Jackson");
        cd.addTrack(new Track("Billie Jean", 5));
        cd.addTrack(new Track("Beat It", 4));
        store.addMedia(cd);
    }
}
