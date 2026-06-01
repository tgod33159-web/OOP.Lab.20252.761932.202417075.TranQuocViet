package hust.soict.dsai.aims.screen;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private final JTextField tfDirector = new JTextField();
    private final JTextField tfLength = new JTextField();

    public AddDigitalVideoDiscToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add DVD");
        initializeScreen("Add DVD");
    }

    public AddDigitalVideoDiscToStoreScreen(Store store, Cart cart, StoreScreen owner) {
        super(store, cart, "Add DVD", owner);
        initializeScreen("Add DVD");
    }

    @Override
    protected void addSpecificFields(JPanel fields) {
        fields.add(new JLabel("Director"));
        fields.add(tfDirector);
        fields.add(new JLabel("Length"));
        fields.add(tfLength);
    }

    @Override
    protected Media createMedia() {
        DigitalVideoDisc disc = new DigitalVideoDisc(tfTitle.getText().trim(), tfCategory.getText().trim(), tfDirector.getText().trim(),
                readInt(tfLength), readCost());
        disc.setId(readId());
        return disc;
    }
}
