package hust.soict.dsai.aims.screen;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private final JTextField tfDirector = new JTextField();
    private final JTextField tfArtist = new JTextField();
    private final JTextField tfTracks = new JTextField();

    public AddCompactDiscToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add CD");
    }

    @Override
    protected void addSpecificFields(JPanel fields) {
        fields.add(new JLabel("Director"));
        fields.add(tfDirector);
        fields.add(new JLabel("Artist"));
        fields.add(tfArtist);
        fields.add(new JLabel("Tracks title:length, ..."));
        fields.add(tfTracks);
    }

    @Override
    protected Media createMedia() {
        CompactDisc cd = new CompactDisc(Integer.parseInt(tfId.getText().trim()), tfTitle.getText().trim(),
                tfCategory.getText().trim(), Float.parseFloat(tfCost.getText().trim()), tfDirector.getText().trim(), 0,
                tfArtist.getText().trim());
        String tracks = tfTracks.getText();
        if (tracks != null && !tracks.trim().isEmpty()) {
            for (String item : tracks.split(",")) {
                String[] parts = item.trim().split(":");
                if (parts.length == 2) {
                    cd.addTrack(new Track(parts[0].trim(), Integer.parseInt(parts[1].trim())));
                }
            }
        }
        return cd;
    }
}
