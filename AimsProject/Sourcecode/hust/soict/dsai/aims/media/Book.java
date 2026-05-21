package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    // Thuộc tính lưu trữ danh sách tác giả (Bài 3)
    private List<String> authors = new ArrayList<String>();

    // Constructor không tham số (Bài 3)
    public Book() {
        super();
    }

    // Constructor đầy đủ tham số gọi lên lớp cha Media (Bài 4)
    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    // Phương thức getter cho danh sách tác giả (Bài 3)
    public List<String> getAuthors() { 
        return authors; 
    }

    // Phương thức thêm tác giả (Bài 3): Kiểm tra trùng lặp trước khi thêm
    public void addAuthor(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
            System.out.println("Author name cannot be empty.");
            return;
        }
        if (!authors.contains(authorName)) {
            authors.add(authorName);
            System.out.println("Author \"" + authorName + "\" has been added to the book.");
        } else {
            System.out.println("Author \"" + authorName + "\" already exists in the list.");
        }
    }

    // Phương thức xóa tác giả (Bài 3): Kiểm tra sự tồn tại trước khi xóa
    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
            System.out.println("Author \"" + authorName + "\" has been removed from the book.");
        } else {
            System.out.println("Author \"" + authorName + "\" does not exist in the list.");
        }
    }

    // Ghi đè phương thức toString() theo đúng yêu cầu Bài 11 (Tính đa hình)
    @Override
    public String toString() {
        // Kiểm tra nếu danh sách tác giả trống thì hiển thị "Unknown"
        String authorsList = (authors != null && !authors.isEmpty()) ? authors.toString() : "Unknown";
        
        return "Book - ID: " + getId() 
                + " - Title: " + getTitle() 
                + " - Category: " + getCategory() 
                + " - Authors: " + authorsList 
                + " - Cost: " + getCost() + " $";
    }
}