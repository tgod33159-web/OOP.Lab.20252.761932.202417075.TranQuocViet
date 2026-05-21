package hust.soict.dsai.aims.cart;

import java.util.ArrayList;
import java.util.Collections; // Thêm import này để sử dụng Collections.sort()
import hust.soict.dsai.aims.media.Media;

public class Cart {
    // Sử dụng ArrayList để lưu trữ đa hình các đối tượng Media
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    // Phương thức thêm một Media (Book, DVD, CD) vào giỏ hàng
    public void addMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot add a null item.");
            return;
        }
        // Kiểm tra trùng lặp dựa trên phương thức equals() đã override ở Bài 10
        if (!itemsOrdered.contains(media)) {
            itemsOrdered.add(media);
            System.out.println("The media \"" + media.getTitle() + "\" has been added to the cart.");
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" is already in the cart.");
        }
    }

    // Phương thức xóa một Media khỏi giỏ hàng
    public void removeMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            itemsOrdered.remove(media);
            System.out.println("The media \"" + media.getTitle() + "\" has been removed from the cart.");
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" was not found in the cart.");
        }
    }

    // --- ĐÂY LÀ ĐOẠN THÊM VÀO CHO BÀI 12 ---
    
    // Sắp xếp giỏ hàng theo Tiêu đề (nếu trùng tên thì xếp theo Giá giảm dần)
    public void sortByTitle() {
        Collections.sort(this.itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        System.out.println("Cart has been sorted by Title.");
    }

    // Sắp xếp giỏ hàng theo Giá giảm dần (nếu trùng giá thì xếp theo Tiêu đề)
    public void sortByCost() {
        Collections.sort(this.itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        System.out.println("Cart has been sorted by Cost.");
    }
    
    // ---------------------------------------

    // Tính tổng chi phí các mặt hàng trong giỏ hàng
    public float totalCost() {
        float sum = 0;
        for (Media media : itemsOrdered) {
            sum += media.getCost();
        }
        return sum;
    }

    // In toàn bộ thông tin giỏ hàng ra màn hình console
    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
        }
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }

    // Tìm kiếm sản phẩm trong giỏ hàng theo ID
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

    // Tìm kiếm sản phẩm trong giỏ hàng theo tiêu đề (Title)
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