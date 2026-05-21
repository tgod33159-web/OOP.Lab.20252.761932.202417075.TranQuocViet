package hust.soict.dsai.aims.store;

import java.util.ArrayList;
import hust.soict.dsai.aims.media.Media;

public class Store {
    // Sử dụng ArrayList để lưu trữ đa hình các đối tượng Media
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    // Phương thức thêm một phương tiện (Media) vào cửa hàng
    public void addMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot add a null item to the store.");
            return;
        }
        // Kiểm tra xem sản phẩm đã tồn tại trong cửa hàng chưa (dựa trên hàm equals() đã override)
        if (!itemsInStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("The media \"" + media.getTitle() + "\" has been added to the store.");
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" already exists in the store.");
        }
    }

    // Phương thức xóa một phương tiện (Media) khỏi cửa hàng
    public void removeMedia(Media media) {
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
            System.out.println("The media \"" + media.getTitle() + "\" has been removed from the store.");
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" was not found in the store.");
        }
    }

    // --- ĐÂY LÀ ĐOẠN THÊM VÀO ĐỂ PHỤC VỤ MENU BÀI 13 ---
    // Tìm kiếm một sản phẩm trong Store dựa theo tiêu đề (Title)
    public Media search(String title) {
        if (title == null || title.trim().isEmpty()) {
            return null;
        }
        for (Media media : itemsInStore) {
            // So sánh không phân biệt chữ hoa chữ thường
            if (media.getTitle().equalsIgnoreCase(title.trim())) {
                return media;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }
    // --------------------------------------------------

    // Phương thức helper lấy danh sách sản phẩm trong cửa hàng (phục vụ cho việc hiển thị menu)
    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }

    // Phương thức in ra các sản phẩm hiện có trong cửa hàng
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