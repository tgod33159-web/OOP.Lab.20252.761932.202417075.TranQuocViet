package hust.soict.dsai.aims.cart;

import java.util.Collections;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
    private final ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public void addMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot add a null item.");
            return;
        }
        if (!itemsOrdered.contains(media)) {
            itemsOrdered.add(media);
            System.out.println("The media \"" + media.getTitle() + "\" has been added to the cart.");
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" is already in the cart.");
        }
    }

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        addMedia(disc);
    }

    public void removeMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot remove a null item.");
            return;
        }
        if (itemsOrdered.contains(media)) {
            itemsOrdered.remove(media);
            System.out.println("The media \"" + media.getTitle() + "\" has been removed from the cart.");
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" was not found in the cart.");
        }
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        removeMedia(disc);
    }

    public void sortByTitle() {
        Collections.sort(this.itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        System.out.println("Cart has been sorted by Title.");
    }

    public void sortByCost() {
        Collections.sort(this.itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        System.out.println("Cart has been sorted by Cost.");
    }

    public float totalCost() {
        float sum = 0;
        for (Media media : itemsOrdered) {
            sum += media.getCost();
        }
        return sum;
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public void clear() {
        itemsOrdered.clear();
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
        }
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }

    public void searchById(int id) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found match for ID " + id + ": " + media.toString());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No match found for ID: " + id);
        }
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.isMatch(title)) {
                System.out.println("Found match for title \"" + title + "\": " + media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No match found for title: " + title);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cart contents (").append(itemsOrdered.size()).append(" items):\n");
        for (Media media : itemsOrdered) {
            sb.append("\t").append(media.toString()).append("\n");
        }
        sb.append("Total cost: ").append(totalCost()).append(" $");
        return sb.toString();
    }
}
