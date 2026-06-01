package hust.soict.dsai.aims.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;

public class MediaStore extends JPanel {
    private final Media media;
    private final Cart cart;

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;
        setLayout(new GridLayout(4, 1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel title = new JLabel(media.getTitle(), JLabel.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        JLabel cost = new JLabel(media.getCost() + " $", JLabel.CENTER);

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(1, 2));

        JButton addToCart = new JButton("Add to cart");
        addToCart.addActionListener(e -> {
            cart.addMedia(media);
            JOptionPane.showMessageDialog(this, media.getTitle() + " has been added to cart.");
        });
        container.add(addToCart);

        if (media instanceof Playable) {
            JButton play = new JButton("Play");
            play.addActionListener(e -> showPlayDialog());
            container.add(play);
        }

        add(Box.createRigidArea(new Dimension(0, 15)));
        add(title);
        add(cost);
        add(container);
    }

    private void showPlayDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Play media");
        dialog.setSize(320, 160);
        dialog.setLocationRelativeTo(this);

        try {
            ((Playable) media).play();
            dialog.add(new JLabel("Playing: " + media.getTitle(), JLabel.CENTER));
            dialog.setVisible(true);
        } catch (PlayerException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Player error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
