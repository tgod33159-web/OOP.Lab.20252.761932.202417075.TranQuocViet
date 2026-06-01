package hust.soict.dsai.aims.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;

public class StoreScreen extends JFrame {
    private final Store store;
    private final Cart cart;

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(e -> refresh());
        menu.add(viewStore);

        JMenu updateStore = new JMenu("Update store");
        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(e -> {
            dispose();
            new AddBookToStoreScreen(store, cart);
        });
        JMenuItem addCd = new JMenuItem("Add CD");
        addCd.addActionListener(e -> {
            dispose();
            new AddCompactDiscToStoreScreen(store, cart);
        });
        JMenuItem addDvd = new JMenuItem("Add DVD");
        addDvd.addActionListener(e -> {
            dispose();
            new AddDigitalVideoDiscToStoreScreen(store, cart);
        });
        updateStore.add(addBook);
        updateStore.add(addCd);
        updateStore.add(addDvd);
        menu.add(updateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));
        menuBar.add(menu);
        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cartButton = new JButton("View cart");
        cartButton.setPreferredSize(new Dimension(100, 50));
        cartButton.setMaximumSize(new Dimension(100, 50));
        cartButton.addActionListener(e -> new CartScreen(cart, store));

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cartButton);
        header.add(Box.createRigidArea(new Dimension(10, 10)));
        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));
        for (int i = 0; i < Math.min(store.getItemsInStore().size(), 9); i++) {
            center.add(new MediaStore(store.getItemsInStore().get(i), cart));
        }
        return center;
    }

    private void refresh() {
        getContentPane().removeAll();
        getContentPane().add(createNorth(), BorderLayout.NORTH);
        getContentPane().add(createCenter(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static Store createSampleStore() {
        Store sampleStore = new Store();
        sampleStore.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        sampleStore.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f));
        sampleStore.addMedia(new Book(3, "Java Design Patterns", "Education", 40.0f));
        CompactDisc cd = new CompactDisc(4, "Thriller", "Pop", 15.0f, "John Landis", 0, "Michael Jackson");
        cd.addTrack(new Track("Billie Jean", 5));
        cd.addTrack(new Track("Beat It", 4));
        sampleStore.addMedia(cd);
        return sampleStore;
    }

    public static void main(String[] args) {
        new StoreScreen(createSampleStore(), new Cart());
    }
}
