package hust.soict.dsai.aims.screen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

public abstract class AddItemToStoreScreen extends JFrame {
    protected final Store store;
    protected final Cart cart;
    private final StoreScreen owner;
    protected final JTextField tfId = new JTextField();
    protected final JTextField tfTitle = new JTextField();
    protected final JTextField tfCategory = new JTextField();
    protected final JTextField tfCost = new JTextField();

    protected AddItemToStoreScreen(Store store, Cart cart, String title) {
        this(store, cart, title, null);
    }

    protected AddItemToStoreScreen(Store store, Cart cart, String title, StoreScreen owner) {
        this.store = store;
        this.cart = cart;
        this.owner = owner;
    }

    protected void initializeScreen(String title) {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createMenuBar(), BorderLayout.NORTH);
        cp.add(createForm(), BorderLayout.CENTER);

        setTitle(title);
        setSize(640, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        toFront();
        requestFocus();
    }

    protected JPanel createForm() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel fields = new JPanel(new GridLayout(0, 2, 8, 8));

        addCommonFields(fields);
        addSpecificFields(fields);

        JButton addButton = new JButton("Add to store");
        addButton.setPreferredSize(new Dimension(140, 40));
        addButton.addActionListener(e -> addItem());

        panel.add(fields, BorderLayout.CENTER);
        panel.add(addButton, BorderLayout.SOUTH);
        return panel;
    }

    protected void addCommonFields(JPanel fields) {
        fields.add(new JLabel("ID"));
        fields.add(tfId);
        fields.add(new JLabel("Title"));
        fields.add(tfTitle);
        fields.add(new JLabel("Category"));
        fields.add(tfCategory);
        fields.add(new JLabel("Cost"));
        fields.add(tfCost);
    }

    protected abstract void addSpecificFields(JPanel fields);

    protected abstract Media createMedia();

    protected int readId() {
        String value = tfId.getText().trim();
        if (value.isEmpty()) {
            return store.getItemsInStore().size() + 1;
        }
        return Integer.parseInt(value);
    }

    protected float readCost() {
        String value = tfCost.getText().trim();
        if (value.isEmpty()) {
            return 0;
        }
        return Float.parseFloat(value);
    }

    protected int readInt(JTextField field) {
        String value = field.getText().trim();
        if (value.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(value);
    }

    private void addItem() {
        try {
            Media media = createMedia();
            store.addMedia(media);
            JOptionPane.showMessageDialog(this, media.getTitle() + " has been added to store.");
            if (owner != null) {
                owner.refresh();
            }
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot add item:\n" + e.getMessage(), "Input error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    protected JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(e -> {
            dispose();
            new StoreScreen(store, cart);
        });
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
        menuBar.add(menu);
        return menuBar;
    }
}
