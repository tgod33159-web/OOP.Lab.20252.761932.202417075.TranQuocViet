package hust.soict.dsai.aims.screen;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartScreenController {
    private final Cart cart;
    private final Store store;
    private final CartScreen screen;
    private FilteredList<Media> filteredMedia;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Label totalCostLabel;

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    public CartScreenController(Cart cart, Store store, CartScreen screen) {
        this.cart = cart;
        this.store = store;
        this.screen = screen;
    }

    @FXML
    public void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

        filteredMedia = new FilteredList<Media>(cart.getItemsOrdered(), media -> true);
        tblMedia.setItems(filteredMedia);

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
        updateTotalCost();

        tblMedia.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateButtonBar(newValue);
        });
        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> showFilteredMedia());
        radioBtnFilterId.selectedProperty().addListener((observable, oldValue, newValue) -> showFilteredMedia());
        radioBtnFilterTitle.selectedProperty().addListener((observable, oldValue, newValue) -> showFilteredMedia());
        cart.getItemsOrdered().addListener((ListChangeListener<Media>) change -> updateTotalCost());
    }

    private void updateButtonBar(Media media) {
        btnRemove.setVisible(media != null);
        btnPlay.setVisible(media instanceof Playable);
    }

    private void updateTotalCost() {
        totalCostLabel.setText(String.format("%.2f $", cart.totalCost()));
    }

    private void showFilteredMedia() {
        String filter = tfFilter.getText();
        if (filter == null || filter.trim().isEmpty()) {
            filteredMedia.setPredicate(media -> true);
            return;
        }

        String normalizedFilter = filter.trim().toLowerCase();
        filteredMedia.setPredicate(media -> {
            if (radioBtnFilterId.isSelected()) {
                return Integer.toString(media.getId()).contains(normalizedFilter);
            }
            return media.getTitle() != null && media.getTitle().toLowerCase().contains(normalizedFilter);
        });
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();
        if (selected != null) {
            cart.removeMedia(selected);
        }
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();
        if (selected instanceof Playable) {
            try {
                ((Playable) selected).play();
                new Alert(Alert.AlertType.INFORMATION, "Playing: " + selected.getTitle()).showAndWait();
            } catch (PlayerException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            }
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        cart.clear();
        new Alert(Alert.AlertType.INFORMATION, "Order has been created.").showAndWait();
    }

    @FXML
    void viewStore(ActionEvent event) {
        screen.closeOnSwingThread();
        SwingUtilities.invokeLater(() -> new StoreScreen(store, cart));
    }

    @FXML
    void addBook(ActionEvent event) {
        screen.closeOnSwingThread();
        SwingUtilities.invokeLater(() -> new AddBookToStoreScreen(store, cart));
    }

    @FXML
    void addCD(ActionEvent event) {
        screen.closeOnSwingThread();
        SwingUtilities.invokeLater(() -> new AddCompactDiscToStoreScreen(store, cart));
    }

    @FXML
    void addDVD(ActionEvent event) {
        screen.closeOnSwingThread();
        SwingUtilities.invokeLater(() -> new AddDigitalVideoDiscToStoreScreen(store, cart));
    }
}
