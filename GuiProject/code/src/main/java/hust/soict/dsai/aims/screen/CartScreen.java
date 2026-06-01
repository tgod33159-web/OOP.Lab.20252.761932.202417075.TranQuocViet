package hust.soict.dsai.aims.screen;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

        Platform.runLater(() -> initFX(fxPanel));
    }

    private void initFX(JFXPanel fxPanel) {
        try {
            FXMLLoader loader = new FXMLLoader(resolveCartFxml());
            CartScreenController controller = new CartScreenController(cart, store, this);
            loader.setController(controller);
            Parent root = loader.load();
            fxPanel.setScene(new Scene(root));
            SwingUtilities.invokeLater(() -> {
                setVisible(true);
                toFront();
                requestFocus();
            });
        } catch (Exception e) {
            e.printStackTrace();
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this,
                    "Cannot open cart screen:\n" + e.getMessage(),
                    "Cart screen error",
                    JOptionPane.ERROR_MESSAGE));
        }
    }

    private URL resolveCartFxml() throws Exception {
        URL resource = CartScreen.class.getResource("/hust/soict/dsai/aims/screen/cart.fxml");
        if (resource != null) {
            return resource;
        }

        Path resourcePath = Path.of("src/main/resources/hust/soict/dsai/aims/screen/cart.fxml");
        if (Files.exists(resourcePath)) {
            return resourcePath.toUri().toURL();
        }

        Path sourcePath = Path.of("src/main/java/hust/soict/dsai/aims/screen/cart.fxml");
        if (Files.exists(sourcePath)) {
            return sourcePath.toUri().toURL();
        }

        throw new IllegalStateException("cart.fxml was not found on classpath or in src/main/resources.");
    }

    void closeOnSwingThread() {
        SwingUtilities.invokeLater(this::dispose);
    }
}
