package hust.soict.dsai.aims.store;

import java.util.ArrayList;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;

public class Store {
    private final ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot add a null item to the store.");
            return;
        }
        if (!itemsInStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("The media \"" + media.getTitle() + "\" has been added to the store.");
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" already exists in the store.");
        }
    }

    public void addDVD(DigitalVideoDisc disc) {
        addMedia(disc);
    }

    public void removeMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot remove a null item from the store.");
            return;
        }
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
            System.out.println("The media \"" + media.getTitle() + "\" has been removed from the store.");
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" was not found in the store.");
        }
    }

    public void removeDVD(DigitalVideoDisc disc) {
        removeMedia(disc);
    }

    public Media search(String title) {
        if (title == null || title.trim().isEmpty()) {
            return null;
        }
        for (Media media : itemsInStore) {
            if (media.getTitle() != null && media.getTitle().equalsIgnoreCase(title.trim())) {
                return media;
            }
        }
        return null;
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }

    public void printStore() {
        System.out.println("***********************STORE***********************");
        System.out.println("Available Items in Store:");
        if (itemsInStore.isEmpty()) {
            System.out.println("The store is currently empty.");
        } else {
            for (int i = 0; i < itemsInStore.size(); i++) {
                System.out.println((i + 1) + ". " + itemsInStore.get(i).toString());
            }
        }
        System.out.println("***************************************************");
    }
}
