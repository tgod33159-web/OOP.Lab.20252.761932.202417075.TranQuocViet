package hust.soict.dsai.aims.screen;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CartScreen extends JFrame {
    private final Cart cart;
    private final Store store;

    public CartScreen(Cart cart, Store store) {
        this.cart = cart;
        this.store = store;

        JFXPanel fxPanel = new JFXPanel();
        add(fxPanel);

        setTitle("Cart");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        Platform.runLater(() -> initFX(fxPanel));
    }

    private void initFX(JFXPanel fxPanel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
            CartScreenController controller = new CartScreenController(cart, store, this);
            loader.setController(controller);
            Parent root = loader.load();
            fxPanel.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void closeOnSwingThread() {
        SwingUtilities.invokeLater(this::dispose);
    }
}
