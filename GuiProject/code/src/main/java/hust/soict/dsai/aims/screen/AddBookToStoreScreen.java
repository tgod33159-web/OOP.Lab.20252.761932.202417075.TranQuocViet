package hust.soict.dsai.aims.screen;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private final JTextField tfAuthors = new JTextField();

    public AddBookToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add Book");
        initializeScreen("Add Book");
    }

    public AddBookToStoreScreen(Store store, Cart cart, StoreScreen owner) {
        super(store, cart, "Add Book", owner);
        initializeScreen("Add Book");
    }

    @Override
    protected void addSpecificFields(JPanel fields) {
        fields.add(new JLabel("Authors (comma separated)"));
        fields.add(tfAuthors);
    }

    @Override
    protected Media createMedia() {
        Book book = new Book(readId(), tfTitle.getText().trim(), tfCategory.getText().trim(), readCost());
        String authors = tfAuthors.getText();
        if (authors != null && !authors.trim().isEmpty()) {
            for (String author : authors.split(",")) {
                book.addAuthor(author.trim());
            }
        }
        return book;
    }
}
